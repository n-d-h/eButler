import { useEffect, useState } from 'react';

import { api } from './api';

import { Manufacturer, ManufacturerResponse } from '#/types/index';

export const useGetManufacturer = () => {
  const [manufacturers, setManufacturers] = useState<Manufacturer[]>([]);
  const [loading, setLoading] = useState(false);

  const fetch = async () => {
    setLoading(true);
    const { data } = await api.get<ManufacturerResponse[]>(
      '/api/admin/manufacturer/list'
    );

    setManufacturers(
      data.map((e) => ({
        id: e.manufacturerId,
        name: e.manufacturerName,
        isActive: e.status,
      }))
    );

    setLoading(false);
  };

  useEffect(() => {
    fetch();
  }, []);

  return { manufacturers, refetch: fetch, loading };
};
