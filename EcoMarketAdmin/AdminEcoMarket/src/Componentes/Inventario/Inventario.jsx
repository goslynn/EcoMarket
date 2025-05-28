import producto from '../../assets/svg/producto.svg'
import familia from '../../assets/svg/familia.svg'
import subfamilia from '../../assets/svg/subfamilia.svg'
import { useState } from 'react'
import CardCrud from '../CardCrud.jsx'
const Inventario = () => {
    const descProducto = "gestionar los productos disponibles en el sistema. Puedes crear, visualizar, editar o eliminar productos de manera eficiente."
    const descFamilia = "La sección de Familias permite administrar las categorías principales de productos.."
    const descSubFamilia = "Este módulo permite gestionar las subfamilias. Divisiones dentro de cada familia."
    const [navegacion, setNavegacion] = useState(0)

    const NavElementos = () => {
        return (
            <div className='row gap-3 d-flex justify-content-center'>
                <div className='col-9 d-flex justify-content-center w-75'>  
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

return(<NavElementos/>)

}
export default Inventario