import {
  Button,
  Checkbox,
  Col,
  Form,
  Input,
  message,
  Row,
  Typography,
} from 'antd';
import { Link, useNavigate } from 'react-router-dom';

import { mockUsers } from '#/data/users';
import { useUserInfo } from '#/hooks/useUserInfo';

export const LoginPage = () => {
  const [form] = Form.useForm();
  const navigate = useNavigate();

  const [_, setUser] = useUserInfo();

  return (
    <section className='bg-gray-50 dark:bg-gray-900'>
      <div className='flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0'>
        <div className='w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700'>
          <div className='p-6 space-y-4 md:space-y-6 sm:p-8'>
            <h1 className='text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white'>
              Sign in to your account
            </h1>
            <Form
              form={form}
              className='space-y-4 md:space-y-6'
              layout='vertical'
            >
              <Form.Item
                label={
                  <Typography className='font-semibold'>Username</Typography>
                }
                name='username'
              >
                <Input required className='rounded-lg' size='large' />
              </Form.Item>
              <Form.Item
                label={
                  <Typography className='font-semibold'>Password</Typography>
                }
                name='password'
              >
                <Input
                  required
                  className='rounded-lg'
                  size='large'
                  type='password'
                />
              </Form.Item>
              <Row justify='space-between'>
                <Col className='flex items-center gap-2'>
                  <Checkbox />
                  <Typography>Remember me</Typography>
                </Col>
                <Col>
                  <Link to='/login'>
                    <Typography className='font-semibold text-sky-400'>
                      Forgot password !
                    </Typography>
                  </Link>
                </Col>
              </Row>
              <Button
                type='primary'
                className='bg-[#00aaff] border-none rounded-md text-white w-full'
                size='large'
                onClick={() => {
                  const { username, password } = form.getFieldsValue();
                  const user = mockUsers.find(
                    (e) => e.username === username && e.password === password
                  );
                  if (!user) {
                    message.error('Wrong username or password!');
                    return;
                  } else {
                    setUser(user);
                    message.success('Login successfully!');
                    navigate('/admin');
                  }
                }}
              >
                Sign In
              </Button>
              <p className='text-sm font-light text-gray-500 dark:text-gray-400'>
                Don’t have an account yet?{' '}
                <a
                  href='#'
                  className='font-medium text-primary-600 hover:underline dark:text-primary-500'
                >
                  Sign up
                </a>
              </p>
            </Form>
          </div>
        </div>
      </div>
    </section>
  );
};
