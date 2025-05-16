import './App.css'
import { Routes, Route } from 'react-router-dom'
import Menu from './components/Menu'
import ManageComments from './pages/ManageComments'
import SingleComment from './pages/SingleComment'
import AllUsers from './pages/AllUsers'
import UserComments from './pages/UserComments';

function App() {
  

  return (
    <>
      <Menu />
      <Routes>
        <Route path="/manage/comments" element={<ManageComments />} />
        <Route path="/comment/:commentId" element={<SingleComment />} />
        <Route path="/users" element={<AllUsers />} />
        <Route path="/user/:userId" element={<UserComments />} />
      </Routes>
    </>
  );
}

export default App
