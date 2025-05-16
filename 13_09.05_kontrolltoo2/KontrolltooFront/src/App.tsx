import './App.css'
import { Routes, Route } from 'react-router-dom'
import Menu from './components/Menu'
import ManageComments from './pages/ManageComments'

function App() {
  

  return (
    <>
      <Menu />
      <Routes>
        <Route path="/manage/comments" element={<ManageComments />} />
      </Routes>
    </>
  );
}

export default App
