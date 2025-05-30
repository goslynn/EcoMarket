  import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import EditarProducto from './Componentes/Inventario/EditarProducto.jsx';
import './index.css';

// Componentes comunes

import Dashboard from './Componentes/Administrador/Dashboard.jsx';
import Administrador from './Routes/Administrador.jsx';
import NotFound from './Routes/NotFound.jsx';
import ListarProductos from './Componentes/Inventario/ListarProductos.jsx';
const router = createBrowserRouter([    
  {
    path: "",
    element: <Administrador />,
    errorElement: <NotFound/>,
    children: [
      {
        index: true,
        element: <Dashboard />
      },
      {
        path: "inventario/producto",
        element: <ListarProductos />,
      },

    ]
  }
]);


createRoot(document.getElementById('root')).render(
  <StrictMode>
      <RouterProvider router={router}>
      </RouterProvider>
  </StrictMode>
)
