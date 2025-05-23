import { Link } from 'react-router-dom'
import TablaUsuarios from '../TablasCrud'

const Usuarios = () => {
  return (
      <div className="container my-4 mt-5">
        <div className="card">
          <div className="card-body">
            <h3 className="mb-3 display-5 fw-bold text-success">Buscar Usuario:</h3>
            <div className="row gy-2 gx-3 align-items-center">
              <div className="col-12 col-sm-6 col-md-3">
                  <input type="text" placeholder='Buscar ' className='form-control' />
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría 3">
                  <option>Buscar Rol</option>
                  <option value="1">Vendedor</option>
                  <option value="2">Bodeguero</option>
                  <option value="3">Gerente</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3 text-sm-end" >
                <button className="btn btn-success w-100">
                    Crear usuario
                </button>
              </div>
            </div>
          </div>
        </div>
        <TablaUsuarios tipo={1}/>
      </div>
  )
}

export default Usuarios