import './App.css'
import { Route, Routes } from 'react-router-dom'
import MainPage from './pages/MainPage'
import ManageWords from './pages/ManageWords'
import Editwords from './pages/EditWords'
import SingleWord from './pages/SingleWord'


function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/managewords" element={<ManageWords />} />

        <Route path="word/:wordId" element={<SingleWord />} />
        <Route path="/edit-word/:wordId" element={<Editwords />} />
      </Routes>    
    </>
  )
}

export default App
