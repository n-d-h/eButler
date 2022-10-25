import { OrderStatus } from './../types/index';
import { api } from './api';

interface Props {
  id: number;
  status: OrderStatus;
}

export const useUpdateOrderStatus = (callback?: () => void) => {
  return ({ id, status }: Props) => {
    api
      .post(
        `/api/order/changestatus?id=${id}&status=${Object.values(
          OrderStatus
        ).findIndex((e) => e === status)}`
      )
      .finally(callback);
  };
};
