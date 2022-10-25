import { useEffect, useState } from 'react';

import { api } from './api';

import { ProductCategory, ProductCategoryResponse } from '#/types';

export const useGetProductCategories = () => {
  const [productCategories, setProductCategories] = useState<ProductCategory[]>(
    []
  );
  const [loading, setLoading] = useState(false);

  const fetch = async () => {
    setLoading(true);
    const { data } = await api.get<ProductCategoryResponse[]>(
      '/api/admin/pcategory/list'
    );
    setProductCategories(
      data.map((e) => ({
        id: e.productcategoryId,
        name: e.productcategoryName,
        isActive: e.status,
      }))
    );
    setLoading(false);
  };

  useEffect(() => {
    fetch();
  }, []);

  return { productCategories, loading, refetch: fetch };
};
