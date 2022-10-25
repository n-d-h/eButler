import { useSessionStorage } from 'usehooks-ts';

import { User } from '#/types';

export function useUserInfo() {
  return useSessionStorage<User | null>('user', null);
}
