import CardCrud from "../CardCrud"
import producto from '../../../../assets/svg/producto.svg'
import familia from  '../../../../assets/svg/familia.svg'
import subfamilia from '../../../../assets/svg/subfamilia.svg'
import { useState } from "react"
import TablaUsuarios from "../../TablasCrud"
const CrudProducto = () => {
    const ver = "gestionar los productos disponibles en el sistema, podras visualizar productos de manera eficiente."
    const crear = "Crea nuevos los productos en el sistema, y genera stock acorde a ellos."
    const editar = "Edita los productos creados con aterioridad ."
    const eliminar = "Elimina los productos  ."
    const [navegacion, setNavegacion] = useState(0)

    switch(navegacion){
        case 0:
            console.log(navegacion)
            return <NavElementos/>
            break
        case 1:
            return <TablaCrud/>
        case 2:
            return <h1>familia</h1>
        case 3:
            return <h1>subfamilia</h1>
    }
}

export default CrudProducto