import { Select } from 'antd';

import { useGetProductCategories } from '#/api';

export const ProductCategorySelect = () => {
  const { productCategories, loading } = useGetProductCategories();

  return (
    <Select
      className='w-full'
      loading={loading}
      options={productCategories.map((e) => ({ label: e.name, value: e.id }))}
      placeholder='Select product categories ...'
      showSearch
      filterOption={(input, option) =>
        !!option?.label.toLocaleLowerCase().includes(input.toLowerCase())
      }
    />
  );
};
