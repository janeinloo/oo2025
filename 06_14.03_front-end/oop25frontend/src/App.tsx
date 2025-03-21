// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import { Route, Routes } from 'react-router-dom'
import MainPage from './pages/MainPage';
import ManageProducts from './pages/ManageProducts';
import Arrayd from './pages/Arrayd';
import Menu from './components/Menu';
import ManageCategories from './pages/ManageCategories';
import Cart from './pages/Cart';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Orders from './pages/Orders';


function App() {
  // const [count, setCount] = useState(0)
  

  
  return (
    <>

{/* localhost:5173/ --- <div>MainPage</div>
    localhost:5173/manage/products --- <div>ManageProducts</div>*/}

        {/* Routes'dest uleval menuu */}
        
        <Menu />

        <Routes>
          <Route path="/" element={ <MainPage /> } />
          <Route path="/manage/products" element={ <ManageProducts /> } />
          <Route path="/manage/categories" element={ <ManageCategories/> } />

          <Route path="/arrays" element={ <Arrayd /> } />
          <Route path="/cart" element={ <Cart /> } />
          <Route path="/login" element={ <Login /> } />
          <Route path="/signup" element={ <Signup /> } />
          <Route path="/orders" element={ <Orders /> } />

          <Route path="/*" element={ <div>Page not found</div> } />
        </Routes>

        {/* kaib FOOTER */}
    </>
  )
}

// key={}
// React soovib koodi mällu jätta. Kui toimuvad re-renderdused, siis ta jätab kõik mällu v.a. tsükli sisud, sest tal pole aimu, mille järgi seda meelde jätta. Selle jaoks, et ta saaks array meelde jätta, lisame key={}

export default App
