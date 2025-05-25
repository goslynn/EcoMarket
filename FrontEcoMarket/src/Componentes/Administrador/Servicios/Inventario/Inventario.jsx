import producto from '../../../../assets/svg/producto.svg'
import familia from '../../../../assets/svg/familia.svg'
import subfamilia from '../../../../assets/svg/subfamilia.svg'
import CardCrud from '../CardCrud'
import { useState } from 'react'
import TablaCrud from '../../TablasCrud'

const Inventario = () => {
    const descProducto = "gestionar los productos disponibles en el sistema. Puedes crear, visualizar, editar o eliminar productos de manera eficiente."
    const descFamilia = "La sección de Familias permite administrar las categorías principales de productos.."
    const descSubFamilia = "Este módulo permite gestionar las subfamilias. Divisiones dentro de cada familia."
    const [navegacion, setNavegacion] = useState(0)


const CardFiltros = ({ text, onclick }) => {
    return (
      <div className="container my-4 mt-5">
        <div className="card">
          <div className="card-body">
            <h3 className="mb-3 display-5 fw-bold text-success">Filtrar por:</h3>
            <div className="row gy-2 gx-3 align-items-center">

              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría 3">
                  <option>Familia</option>
                  <option value="1">One</option>
                  <option value="2">Two</option>
                  <option value="3">Three</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría 3">
                  <option value="">Precio</option>
                  <option value="1">De mayor a menor</option>
                  <option value="2">De menor a mayor</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría 3">
                  <option value="">Estado</option>
                  <option value="1">Activo </option>
                  <option value="2">Inactivo</option>
                </select>
              </div>
                            <div className="col-12 col-sm-6 col-md-3">
                  <input type="text" placeholder='Buscar Producto' className='form-control' />
              </div>
              <div className="col-12 col-sm-6 col-md-3 text-sm-end">
                <button className="btn btn-success w-100" >
                  { text }
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
    };
    const NavElementos = () => {
        return (
            <div className='row gap-3 d-flex justify-content-center'>
                <div className='col-9 d-flex justify-content-center'>  
                    <CardCrud titulo={"Producto"} desc={descProducto} icono={producto} cambio={()=>{setNavegacion(1)}}/>
                </div>
                <div className='col-5'>  
                    <CardCrud titulo={"Familia de producto"} desc={descFamilia} icono={familia} cambio={()=>{setNavegacion(2)}}/>
                </div>
                <div className='col-5'>  
                    <CardCrud titulo={"Sub-Familia de producto"} desc={descSubFamilia} icono ={subfamilia} cambio={()=>{setNavegacion(3)}}/>
                </div>
            </div>
        )
    }

    switch(navegacion){
        case 0:
            console.log(navegacion)
            return <NavElementos/>
            break
        case 1:
            
            return (
                <div className='container'>
                    <CardFiltros text={"Crear"}/> 
                    <TablaCrud tipo={2} />
                </div>
            )
            break
        case 2:
            return (
                <div className='container'>
                    <CardFiltros text={"Crear"}/> 
                    <TablaCrud tipo={3} />
                </div>
            )
             break
        case 3:
            return (
                <div className='container'>
                    <CardFiltros text={"Crear"}/> 
                    <TablaCrud tipo={4} />
                </div>
            )
             break
    }
    
}
export default Inventario