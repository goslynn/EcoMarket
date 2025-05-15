import React from 'react'
import CardCrud from '../CardCrud'
import producto from '../../../assets/svg/producto.svg'
import familia from '../../../assets/svg/familia.svg'
import subfamilia from '../../../assets/svg/subfamilia.svg'


const Inventario = () => {
    const descProducto = "gestionar los productos disponibles en el sistema. Puedes crear, visualizar, editar o eliminar productos de manera eficiente. Cada producto incluye información como nombre, descripción, precio, stock y su relación con la subfamilia correspondiente, facilitando así el control del inventario."
    const descFamilia = "La sección de Familias permite administrar las categorías principales de productos.."
    const descSubFamilia = "Este módulo permite gestionar las subfamilias. Divisiones dentro de cada familia."
  return (
    <div className='row gap-3 d-flex justify-content-center'>
                <div className='col-9'>  
            <CardCrud titulo={"Producto"} desc={descProducto} icono={producto}/>
        </div>
        <div className='col-5'>  
            <CardCrud titulo={"Familia de producto"} desc={descFamilia} icono={familia}/>
        </div>
        <div className='col-5'>  
            <CardCrud titulo={"Sub-Familia de producto"} desc={descSubFamilia} icono={subfamilia}/>
        </div>


    </div>
  )
}

export default Inventario