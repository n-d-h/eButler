import { PlusOutlined, SearchOutlined } from '@ant-design/icons';
import {
  Button,
  Col,
  Form,
  Input,
  Modal,
  Row,
  Select,
  Switch,
  Typography,
} from 'antd';
import { range } from 'lodash';
import { useState } from 'react';

import { ProductCard } from '#/components/ProductCard';
import { mockProducts } from '#/data/products';
import { Product } from '#/types';

export const AdminProductPage = () => {
  const [form] = Form.useForm();
  const [products, setProducts] = useState(mockProducts);

  const [filter, setFilter] = useState({ search: '' });

  const [showUpsertModal, setShowUpsertModal] = useState<
    | false
    | {
        title: string;
        product?: Partial<Product>;
      }
  >(false);

  const onOk = () => {
    const values = form.getFieldsValue();
    setProducts((prev) => {
      if (showUpsertModal && showUpsertModal.product) {
        return prev.map((e) =>
          e.id === showUpsertModal.product?.id
            ? { id: e.id, ...showUpsertModal.product }
            : e
        );
      } else {
        return [...prev, values];
      }
    });
    setShowUpsertModal(false);
  };

  return (
    <>
      <div>
        <Row
          justify='space-between'
          className='sticky top-0 py-6 bg-[#F0F2F5] z-40'
        >
          <Col>
            <Input
              size='large'
              placeholder='Search here ...'
              className='rounded-lg'
              suffix={<SearchOutlined />}
              onChange={(e) =>
                setFilter((prev) => ({
                  ...prev,
                  search: e.currentTarget?.value ?? '',
                }))
              }
            />
          </Col>
          <Col>
            <Button
              size='large'
              className='rounded-lg bg-[#00aaff] text-white border-none'
              icon={<PlusOutlined />}
              onClick={() =>
                setShowUpsertModal({
                  title: 'Add Product',
                })
              }
            >
              Add new
            </Button>
          </Col>
        </Row>

        <div className='p-6 bg-white mt-6 rounded-md'>
          <Typography.Title level={2}>Products Management</Typography.Title>
          <div className='grid grid-cols-4 gap-x-4 gap-y-6'>
            {products
              .filter((e) =>
                e.productName
                  .toLowerCase()
                  .includes(filter.search.toLowerCase())
              )
              .map((product) => (
                <ProductCard
                  key={product.id}
                  product={product as any}
                  // showPrice
                  showQuantity
                  showStatus
                  onEdit={(product) => {
                    setShowUpsertModal({
                      title: 'Update Product',
                      product,
                    });
                  }}
                  onDelete={(product) =>
                    setProducts((prev) =>
                      prev.filter((e) => e.id !== product.id)
                    )
                  }
                />
              ))}
          </div>
        </div>
      </div>

      <Modal
        title={showUpsertModal && showUpsertModal.title}
        closeIcon={null}
        open={!!showUpsertModal}
        onCancel={() => setShowUpsertModal(false)}
        okText={'Add'}
        onOk={onOk}
      >
        <Form
          labelCol={{ span: 8 }}
          wrapperCol={{ span: 16 }}
          form={form}
          initialValues={showUpsertModal ? showUpsertModal.product : undefined}
          autoComplete='false'
        >
          <Form.Item label='Product Name' name='productName' required>
            <Input />
          </Form.Item>
          <Form.Item label='Description' name='description' required>
            <Input />
          </Form.Item>
          <Form.Item label='Image link' name='image' required>
            <Input />
          </Form.Item>
          <Form.Item label='Manufacturer' name='manufacturerId' required>
            <Select
              options={range(0, 5).map((e, idx) => ({
                label: `Manufacturer ${idx}`,
                value: idx,
              }))}
            />
          </Form.Item>
          <Form.Item label='Status' name='status' valuePropName='checked'>
            <Switch />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
