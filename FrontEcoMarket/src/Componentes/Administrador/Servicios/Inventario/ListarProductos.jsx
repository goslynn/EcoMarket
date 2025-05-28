import CardFiltros from '../../../Tienda/CardFiltros.jsx'
import TablasCrud from '../../TablasCrud.jsx' 

const ListarProductos = () => {
  return (
    <div>
        <CardFiltros text={"Crear"}/> 
        <TablasCrud tipo={2} />
    </div>
  )
}

export default ListarProductos