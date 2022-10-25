import { OrderStatus } from '#/types';
export function getShippingStatus(current: OrderStatus, compare: OrderStatus) {
  const currentIdx = Object.values(OrderStatus).findIndex((e) => e === current);
  const compareIdx = Object.values(OrderStatus).findIndex((e) => e === compare);

  if (compareIdx - currentIdx < 0) return 'finish';
  if (compareIdx - currentIdx > 0) return 'wait';

  return 'process';
}
