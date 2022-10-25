import { Link } from 'react-router-dom';

export const HomePage = () => {
  return (
    <div>
      HomePage
      <Link to='/admin'>Admin</Link>
    </div>
  );
};
