export interface ProductResponse {
  productId: number;
  productName: string;
  description: string;
  image: string;
  status: boolean;
  productcategoryId: number;
  manufacturerId: number;
}

export interface OrderDetail {
  id: number;
  product: {
    id: number;
    name: string;
  };
  unitPrice: number;
  quantity: number;
}

export interface Order {
  id: number;
  orderDate: Date | string;
  paymentType: string;
  status: OrderStatus;
  totalPrice?: number;
  customer: {
    id: number;
    fullName: string;
  };

  detail: OrderDetail[];
}

export interface User {
  username: string;
  password: string;
  fullName?: string;
  role: 'ADMIN' | 'PROVIDER' | 'USER';
}

export enum OrderStatus {
  Cancel = 'Cancel',
  New = 'New',
  GatheringAndPacking = 'Gathering and Packing',
  PackedAndDelivering = 'Packed and Delivering',
  Done = 'Done',
}

export interface OrderDetailResponse {
  orderDetailId: number;
  unitPrice: number;
  quantity: number;
  orderId: number;
  productproviderId: number;
  productName: string;
}

export interface OrderResponse {
  orderId: number;
  totalPrice: number;
  orderDate: string;
  paymentType: string;
  shippingStatus: OrderStatus;
  customerId: 1;
  customerName: string;
  orderDetailList: OrderDetailResponse[];
}

export interface Manufacturer {
  id: number;
  name: string;
  isActive: boolean;
}

export interface ManufacturerResponse {
  manufacturerId: number;
  manufacturerName: string;
  status: boolean;
}

export interface ProductCategory {
  id: number;
  name: string;
  isActive: boolean;
}

export interface ProductCategoryResponse {
  productcategoryId: number;
  productcategoryName: string;
  status: boolean;
}

export interface Product {
  id: number;
  name: string;
  description: string;
  image: string;
  isActive: boolean;
  category: ProductCategory;
  manufacturer: Manufacturer;
}

export interface ProductProviderResponse {
  productproviderId: number;
  unitPrice: number;
  quantity: number;
  image: string;
  rating: number;
  status: boolean;
  product_id: number;
  provider_id: number;
  personalDescription: string;
  productName: string;
}

export interface ProductProvider extends Product {
  product: {
    id: number;
  };
  unitPrice: number;
  quantity: number;
  description: string;
}
