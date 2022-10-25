import { Select } from 'antd';

import { useGetManufacturer } from '#/api';

export const ManufacturerSelect = () => {
  const { manufacturers, loading } = useGetManufacturer();

  const options = manufacturers.map((e) => ({
    label: e.name,
    value: e.id,
  }));

  return (
    <Select
      className='w-full'
      loading={loading}
      options={options}
      placeholder='Select manufacturer ...'
      showSearch
      filterOption={(input, option) =>
        !!option?.label.toLocaleLowerCase().includes(input.toLowerCase())
      }
    />
  );
};
