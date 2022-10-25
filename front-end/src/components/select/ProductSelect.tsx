import { Select } from 'antd';

import { useGetProducts } from '#/api';

interface Props {
  value?: number;
  onChange?: (id: number) => void;
  disabled?: boolean;
}

export const ProductSelect = ({ value, onChange, disabled }: Props) => {
  const { products, loading } = useGetProducts();

  return (
    <Select
      disabled={disabled}
      className='w-full'
      loading={loading}
      options={products.map((e) => ({
        label: e.name,
        value: e.id,
      }))}
      value={value}
      onChange={onChange}
      placeholder='Select product ...'
      showSearch
      filterOption={(input, option) =>
        !!option?.label.toLocaleLowerCase().includes(input.toLowerCase())
      }
    />
  );
};
