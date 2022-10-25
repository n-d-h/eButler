import { Navigate, Outlet, useRoutes } from 'react-router-dom';

import { AdminLayout } from './layouts';
import {
  AdminOrderManagePage,
  AdminProductPage,
  DashboardPage,
  HomePage,
  ProviderProductPage,
} from './pages';
import { LoginPage } from './pages/shared/LoginPage';

export const App = () => {
  return useRoutes([
    { path: '/', element: <HomePage /> },
    { path: '/login', element: <LoginPage /> },
    {
      path: '/admin',
      element: (
        <AdminLayout>
          <Outlet />
        </AdminLayout>
      ),
      children: [
        { path: '/admin/', element: <DashboardPage /> },
        { path: '/admin/products', element: <AdminProductPage /> },
        { path: '/admin/p-products', element: <ProviderProductPage /> },
        { path: '/admin/orders', element: <AdminOrderManagePage /> },
        { path: '/admin/*', element: <Navigate to='/admin' /> },
      ],
    },
  ]);
};
