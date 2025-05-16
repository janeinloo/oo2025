import './App.css'
import MainPage from './pages/MainPage';
import SingleWord from './pages/SingleWord';
import Words from './pages/Words';
import { Route, Routes } from 'react-router-dom';
import Menu from './components/Menu';
import EditWord from './pages/EditWord';

function App() {

  return (
    <>

      <Menu />
      <Routes>
        <Route path="/words" element={<Words />} />
        <Route path="/" element={<MainPage />} />
        <Route path="/word/:wordId" element={<SingleWord />} />
        <Route path="edit-word/:wordId" element={<EditWord />} />
      </Routes>
    </>
  )
}

export default App
