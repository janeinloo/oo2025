import './App.css'
import { Routes, Route } from 'react-router-dom'
import Menu from './components/Menu'
import ManageComments from './pages/ManageComments'
import SingleComment from './pages/SingleComment'

function App() {
  

  return (
    <>
      <Menu />
      <Routes>
        <Route path="/manage/comments" element={<ManageComments />} />
        <Route path="/comment/:commentId" element={<SingleComment />} />
      </Routes>
    </>
  );
}

export default App
