import { atom, useRecoilState } from 'recoil';

import { User } from '#/types';

const userAtom = atom<User | null>({
  key: 'user-atom',
  default: null,
});

export const useUserStore = () => useRecoilState(userAtom);
