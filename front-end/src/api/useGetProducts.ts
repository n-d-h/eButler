import { useEffect, useState } from 'react';

import { api } from './api';

import { Product, ProductResponse } from '#/types';
export const useGetProducts = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(false);

  const fetch = async () => {
    setLoading(true);
    const { data } = await api.get<ProductResponse[]>(
      '/api/admin/product/list'
    );

    setProducts(
      data.map((e) => ({
        id: e.productId,
        description: e.description,
        image: e.image,
        name: e.productName,
        isActive: e.status,
        categoryId: e.productcategoryId,
        manufacturer: {
          id: e.manufacturerId,
          isActive: true,
          name: '',
        },
        category: {
          id: e.productcategoryId,
          name: '',
          isActive: true,
        },
      }))
    );

    setLoading(false);
  };

  useEffect(() => {
    fetch();
  }, []);

  return { products, loading, refetch: fetch };
};
