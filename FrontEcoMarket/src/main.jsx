import { createBrowserRouter, RouterProvider} from 'react-router-dom'
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx' 
// paginas
import { Login }    from '../src/Routes/Login.jsx'
import { Registro } from '../src/Routes/Registro.jsx'
import NavBar from './Componentes/NavBar.jsx'
import Tienda from './Routes/Tienda.jsx'

//importacion del Context
import VistaProducto from './Routes/VistaProducto.jsx'
import Carrito from './Routes/Carrito.jsx'
import Footer from './Componentes/Footer.jsx'
import VistaAdmin from './Routes/RoutesAdmin/VistaAdmin.jsx'
const router = createBrowserRouter([
  {
    path:"/",
    element:<> 
        <NavBar/>
          <App/>
        <Footer></Footer>
        </>, 
    errorElement:<></>
  },
  {
    path:"/login",
    element:<Login/>, 
    errorElement:<></>
  },
  {
    path:"/registro",
    element:<Registro/>, 
    errorElement:<></>
  },
  {
    path:"/tienda",
    element:<> 
        <NavBar/>
          <Tienda/>
        <Footer></Footer>
        </>,  
    errorElement:<></>
  },
  {
    path:"/Producto/:id",
    element:<> 
        <NavBar/>
          <VistaProducto/>
        <Footer></Footer>
        </>,  
    errorElement:<></>
  },
  {
    path:"/carrito",
    element:<> 
        <NavBar/>
          <Carrito></Carrito>
          <Footer></Footer>
        </>,
    errorElement:<></>
  },
    {
    path:"/admin",
    element:<> 
          <VistaAdmin/>
        </>,
    errorElement:<></>
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <RouterProvider router={router}>
      </RouterProvider>
  </StrictMode>
)
