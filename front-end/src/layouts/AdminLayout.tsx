import {
  ApartmentOutlined,
  BellOutlined,
  BlockOutlined,
  DashboardOutlined,
  DeploymentUnitOutlined,
  MenuOutlined,
  SmileOutlined,
} from '@ant-design/icons';
import styled from '@emotion/styled';
import {
  Avatar,
  Button,
  Col,
  Dropdown,
  Layout,
  Menu,
  Row,
  Typography,
} from 'antd';
import { ReactNode, useState } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';

import { useUserInfo } from '#/hooks/useUserInfo';
import { IconAdd, IconOrder } from '#/shared/icons';

const { Sider, Header, Content } = Layout;

const StyledSiderMenu = styled(Menu)`
  padding-top: 1em;
  border: none;
  padding-right: 1em;

  .ant-menu-item-group-title {
    color: #495584;
    font-weight: 600;
    text-transform: uppercase;
    font-size: 0.9em;
  }

  .ant-menu-item {
    color: #495584;
    &:hover {
      background-color: #f9fbfd;
      color: #495584;
    }

    &.ant-menu-item-selected {
      color: white;
      background: linear-gradient(135deg, #00aaff 0%, #00aaff99 100%);
      position: relative;
      box-shadow: 0 8px 24px rgb(229 228 230 / 40%);
      border-radius: 0 20px 20px 0;

      &::after {
        display: none;
      }
    }

    .anticon {
      font-size: 20px;
    }
  }
`;

interface Props {
  children?: ReactNode;
}

export const AdminLayout = ({ children }: Props) => {
  const navigate = useNavigate();

  const [user, setUser] = useUserInfo();

  const [collapsed, setCollapsed] = useState(false);

  if (!user) {
    return <Navigate to='/login' />;
  }

  return (
    <Layout className='h-screen'>
      <Sider
        theme='light'
        className='shadow-md'
        width={270}
        collapsible
        trigger={null}
        collapsed={collapsed}
      >
        <Row
          className='h-[75px] border-b border-solid border-gray-200 border-0'
          justify='center'
          align='middle'
        >
          {/*  brand here */}
        </Row>
        <StyledSiderMenu
          mode='inline'
          defaultSelectedKeys={['dashboard']}
          items={[
            {
              key: 'main',
              label: collapsed ? '' : 'Main',
              type: 'group',
            },
            {
              key: 'dashboard',
              icon: <DashboardOutlined />,
              label: 'Dashboard',
              onClick: () => navigate('/admin'),
            },
            {
              key: 'management',
              label: collapsed ? '' : 'Managements',
              type: 'group',
            },
            {
              key: 'services',
              icon: <DeploymentUnitOutlined />,
              label: 'Service Management',
              onClick: () => navigate('/admin/services'),
              className: user.role !== 'ADMIN' ? 'hidden' : '',
            },
            {
              key: 'products',
              icon: <BlockOutlined />,
              label: 'Product Management',
              onClick: () => navigate('/admin/products'),
              className: user.role !== 'ADMIN' ? 'hidden' : '',
            },
            {
              key: 'providers',
              icon: <ApartmentOutlined />,
              label: 'Provider Management',
              onClick: () => navigate('/admin/providers'),
              className: user.role !== 'ADMIN' ? 'hidden' : '',
            },
            {
              key: 'customers',
              icon: <SmileOutlined />,
              label: 'Service Management',
              onClick: () => navigate('/admin/customers'),
              className: user.role !== 'ADMIN' ? 'hidden' : '',
            },
            // Provider
            {
              key: 'orders',
              icon: <IconOrder className='w-5 h-5' />,
              label: 'Order Management',
              onClick: () => navigate('/admin/orders'),
              className: user.role !== 'PROVIDER' ? 'hidden' : '',
            },
            {
              key: 'p-product',
              icon: <IconAdd className='w-5 h-5' />,
              label: 'Register Product',
              onClick: () => navigate('/admin/p-products'),
              className: user.role !== 'PROVIDER' ? 'hidden' : '',
            },
          ]}
        />
      </Sider>
      <Layout>
        <Header className='bg-white px-4 flex gap-4 items-center h-[75px] justify-between'>
          <Button
            className='shadow-md text-gray-700 border-none'
            onClick={() => setCollapsed(!collapsed)}
            shape='circle'
            icon={<MenuOutlined />}
            size='large'
          />
          <Row gutter={20} align='middle'>
            <Col>
              <Button
                className='shadow-md text-gray-700 border-none'
                shape='circle'
                icon={<BellOutlined />}
                size='large'
              />
            </Col>
            <Col>
              <Dropdown
                overlay={
                  <div
                    className='bg-white rounded px-4 py-2 border cursor-pointer border-solid border-slate-400'
                    onClick={() => {
                      setUser(null);
                    }}
                  >
                    Log out
                  </div>
                }
                placement='bottomRight'
              >
                <div className='flex items-center gap-2'>
                  <Avatar
                    src='https://joeschmoe.io/api/v1/random'
                    size='large'
                    className='border-solid border-gray-300 border-[1px]'
                  />
                  <Typography>{user.role.toLowerCase()}</Typography>
                </div>
              </Dropdown>
            </Col>
          </Row>
        </Header>
        <Content className='px-4 overflow-y-auto'>{children}</Content>
      </Layout>
    </Layout>
  );
};
