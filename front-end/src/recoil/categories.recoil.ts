import { atom, useRecoilState } from 'recoil';

import { ProductCategory } from '#/types';

const categoriesAtom = atom<ProductCategory[]>({
  key: 'product-provider-categories',
  default: [],
});

export const useProductCategories = () => useRecoilState(categoriesAtom);
