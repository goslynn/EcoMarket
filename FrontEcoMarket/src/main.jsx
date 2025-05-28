import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import './index.css';
import App from './App.jsx';

// Componentes comunes
import NavBar from './Componentes/Navegacion/NavBar.jsx';
import Footer from './Componentes/Footer.jsx';
import Dashboard from './Componentes/Administrador/Dashboard.jsx';
import Inventario from './Componentes/Administrador/Servicios/Inventario/Inventario.jsx'
// Rutas
import { Login } from './Routes/Login.jsx';
import { Registro } from './Routes/Registro.jsx';
import Tienda from './Routes/Tienda.jsx';
import VistaProducto from './Routes/VistaProducto.jsx';
import Carrito from './Routes/Carrito.jsx';
import Administrador from './Routes/Administrador.jsx';
import Sucursal from './Componentes/Administrador/Servicios/Sucursal.jsx';
import Informes from './Componentes/Administrador/Servicios/Informes.jsx';
import Usuarios from './Componentes/Administrador/Servicios/Usuarios.jsx';
import ListarProductos from './Componentes/Administrador/Servicios/Inventario/ListarProductos.jsx';

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
    path: "/admin",
    element: <Administrador />,
    errorElement: <></>,
    children: [
      {
        path: "",
        element: <Dashboard/>
      },
      {
        path: "usuarios",
        element: <Usuarios />,
        
      },
      {
        path: "sucursales",
        element:<Sucursal/> 
      },
      {
        path: "inventario",
        element:<Inventario/> 
      },
            {
        path: "informes",
        element: <Informes/>
      },
      {
        path: "inventario/productos",
        element: <><ListarProductos/></>,
      },
          {
          path: "inventario/familias",
          element: <h1>sub ruta familia</h1>,
          },
          {
          path: "inventario/subfamilias",
          element: <h1>sub ruta subfamilia</h1>,
          },
    ]
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <RouterProvider router={router}>
      </RouterProvider>
  </StrictMode>
)
