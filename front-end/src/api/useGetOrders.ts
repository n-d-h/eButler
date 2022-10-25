import { useEffect, useState } from 'react';

import { api } from './api';

import { Order, OrderDetail, OrderResponse } from '#/types';

interface Props {
  by: 'provider' | 'cus' | 'product';
  id: number;
}

export const useGetAllOrder = ({ by, id }: Props) => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [loading, setLoading] = useState(false);

  const fetch = () =>
    api
      .get<OrderResponse[]>(`/api/order/listby${by}/${id}`)
      .then(({ data }) => {
        const convertedData = data.map((e) => ({
          customer: {
            fullName: e.customerName,
            id: e.customerId,
          },
          detail: e.orderDetailList.map<OrderDetail>((item) => ({
            id: item.orderDetailId,
            product: {
              id: item.productproviderId,
              name: item.productName,
            },
            quantity: item.quantity,
            unitPrice: item.unitPrice,
          })),
          id: e.orderId,
          orderDate: e.orderDate,
          paymentType: e.paymentType,
          status: e.shippingStatus,
          totalPrice: e.totalPrice,
        }));

        setOrders(convertedData);

        return convertedData;
      })
      .finally(() => {
        setLoading(false);
      });

  useEffect(() => {
    setLoading(true);
    fetch();
  }, []);

  return { refetch: fetch, orders, loading };
};
