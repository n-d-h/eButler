import {
  CarOutlined,
  CheckCircleOutlined,
  CodepenOutlined,
  FileDoneOutlined,
} from '@ant-design/icons';
import {
  Button,
  Col,
  Empty,
  Modal,
  Radio,
  Row,
  Steps,
  Table,
  Tag,
  Typography,
} from 'antd';
import { ColumnType } from 'antd/lib/table';
import dayjs from 'dayjs';
import { useState } from 'react';

import { useGetAllOrder } from '#/api/useGetOrders';
import { useUpdateOrderStatus } from '#/api/useUpdateOrderStatus';
import { Order, OrderDetail, OrderStatus } from '#/types';
import { getShippingStatus } from '#/utils/admin-order.util';

const columns: ColumnType<Order>[] = [
  {
    title: 'ID',
    dataIndex: 'id',
  },
  {
    title: 'Customer',
    dataIndex: ['customer', 'fullName'],
  },

  {
    title: 'Payment Type',
    dataIndex: 'paymentType',
  },
  {
    title: 'Status',
    dataIndex: 'status',
    render: (text) => (
      <Tag
        color={(() => {
          switch (text) {
            case OrderStatus.New:
              return 'cyan';

            case OrderStatus.GatheringAndPacking:
              return 'geekblue';

            case OrderStatus.PackedAndDelivering:
              return 'magenta';

            case OrderStatus.Done:
              return 'green';

            case OrderStatus.Cancel:
              return 'grey';
          }
        })()}
      >
        {text}
      </Tag>
    ),
  },
  {
    title: 'Total Price',
    render: (_, order) => (
      <div className='text-right'>
        {order.detail
          .reduce((acc, cur) => acc + cur.quantity * cur.unitPrice, 0)
          .toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
      </div>
    ),
  },
  {
    title: 'Order Date',
    dataIndex: 'orderDate',
    render: (val) => dayjs(val).format('DD/MM/YYYY'),
  },
];

export const AdminOrderManagePage = () => {
  const [loadingUpdate, setLoadingUpdate] = useState(false);
  const [filter, setFilter] = useState<{
    status: 'All' | OrderStatus;
  }>({ status: 'All' });

  const { orders, refetch, loading } = useGetAllOrder({
    by: 'provider',
    id: 1,
  });
  const [orderDetail, setOrderDetail] = useState<{
    orderList: OrderDetail[];
    customerName: string;
    orderId: number;
    status: OrderStatus;
  }>();

  const updateStatus = useUpdateOrderStatus(() => {
    setLoadingUpdate(true);
    refetch()
      .then((orders) => {
        const order = orders.find((e) => e.id === orderDetail?.orderId);
        if (order) {
          setOrderDetail({
            customerName: order.customer.fullName,
            orderId: order.id,
            orderList: order.detail,
            status: order.status,
          });
        }
      })
      .finally(() => setLoadingUpdate(false));
  });

  return (
    <>
      <div className='bg-white rounded p-6 my-6'>
        <Row justify='space-between'>
          <Col>
            <Typography.Title level={3}>Order Management</Typography.Title>
          </Col>
          <Col>
            <Radio.Group
              onChange={(e) => setFilter({ status: e.target.value })}
            >
              <Radio.Button value='All'>All</Radio.Button>
              <Radio.Button className='text-[cyan]' value={OrderStatus.New}>
                {OrderStatus.New}
              </Radio.Button>
              <Radio.Button
                className='text-blue-900'
                value={OrderStatus.GatheringAndPacking}
              >
                {OrderStatus.GatheringAndPacking}
              </Radio.Button>
              <Radio.Button
                className='text-[magenta]'
                value={OrderStatus.PackedAndDelivering}
              >
                {OrderStatus.PackedAndDelivering}
              </Radio.Button>
              <Radio.Button className='text-[green]' value={OrderStatus.Done}>
                {OrderStatus.Done}
              </Radio.Button>
              <Radio.Button className='text-[red]' value={OrderStatus.Cancel}>
                {OrderStatus.Cancel}
              </Radio.Button>
            </Radio.Group>
          </Col>
        </Row>

        <Table
          loading={loading}
          rowKey={(e) => e.id}
          columns={columns}
          dataSource={
            filter.status === 'All'
              ? orders
              : orders.filter((e) => e.status === filter.status)
          }
          pagination={{
            pageSize: 10,
          }}
          onRow={(order) => {
            return {
              onClick: () => {
                setOrderDetail({
                  customerName: order.customer.fullName,
                  orderId: order.id,
                  orderList: order.detail,
                  status: order.status,
                });
              },
            };
          }}
        />
      </div>
      <Modal
        className='w-[1200px]'
        closable={false}
        closeIcon={null}
        open={!!orderDetail}
        footer={
          <Button
            onClick={() => setOrderDetail(undefined)}
            type='primary'
            className='rounded border-none'
          >
            Close
          </Button>
        }
      >
        {orderDetail ? (
          <>
            <Row gutter={20} className='mb-4' align='middle'>
              <Col>
                <Typography.Title level={5}>
                  OrderId: {orderDetail.orderId}
                </Typography.Title>
              </Col>
              <Col>
                <Typography.Title level={5}>
                  Customer: {orderDetail.customerName}
                </Typography.Title>
              </Col>
              <Col flex={1} className='flex justify-end'>
                {orderDetail.status !== OrderStatus.Cancel &&
                  orderDetail.status !== OrderStatus.PackedAndDelivering && (
                    <Button
                      type='primary'
                      onClick={() => {
                        if (
                          orderDetail.status !== OrderStatus.PackedAndDelivering
                        ) {
                          updateStatus({
                            id: orderDetail.orderId,
                            status: OrderStatus.Cancel,
                          });
                        }
                      }}
                    >
                      Cancel Order
                    </Button>
                  )}
              </Col>
            </Row>
            <Steps className='mb-4'>
              <Steps.Step
                className='cursor-pointer'
                status={getShippingStatus(orderDetail.status, OrderStatus.New)}
                title={`1. ${OrderStatus.New}`}
                icon={<FileDoneOutlined />}
              />
              <Steps.Step
                className='cursor-pointer'
                status={getShippingStatus(
                  orderDetail.status,
                  OrderStatus.GatheringAndPacking
                )}
                title={`2. ${OrderStatus.GatheringAndPacking}`}
                icon={<CodepenOutlined />}
                onClick={() => {
                  if (orderDetail.status === OrderStatus.New)
                    updateStatus({
                      id: orderDetail.orderId,
                      status: OrderStatus.GatheringAndPacking,
                    });
                }}
              />
              <Steps.Step
                className='cursor-pointer'
                status={getShippingStatus(
                  orderDetail.status,
                  OrderStatus.PackedAndDelivering
                )}
                title={`3. ${OrderStatus.PackedAndDelivering}`}
                icon={<CarOutlined />}
                onClick={() => {
                  if (orderDetail.status === OrderStatus.GatheringAndPacking)
                    updateStatus({
                      id: orderDetail.orderId,
                      status: OrderStatus.PackedAndDelivering,
                    });
                }}
              />
              <Steps.Step
                className='cursor-pointer'
                status={getShippingStatus(orderDetail.status, OrderStatus.Done)}
                title={`4. ${OrderStatus.Done}`}
                icon={<CheckCircleOutlined />}
              />
            </Steps>
            <Table
              loading={loadingUpdate}
              rowKey={(e) => e.id}
              dataSource={orderDetail.orderList}
              columns={[
                { title: 'Product Name', dataIndex: ['product', 'name'] },
                { title: 'Unit Price', dataIndex: 'unitPrice' },
                { title: 'Quantity', dataIndex: 'quantity' },
              ]}
            />
          </>
        ) : (
          <Empty />
        )}
      </Modal>
    </>
  );
};
