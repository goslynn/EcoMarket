import axios from 'axios';
import React, { useState } from 'react';
const EditarProducto = ({ produ, cerrar })  => {
const [datosProducto, setDatosProducto] = useState({
    id: produ.idProducto ,
    nombre: produ.NombreProducto ,
    sku: produ.CodigoProducto ,
    subFamilia: produ.idSubFamilia,
    descripcion: produ.Descripcion ,
    precio: produ.Precio ,
    estado: produ.Activo ,
  });

    const handleConfirmar = () => {
        //axios.patch(`http://localhost:8080/api/v1/public/inventario/producto/${producto.idProducto}`)
        //.then(response => {
          //  console.log('Elemento eliminado:', response.data);
        //})
        //.catch(error => {
          //  console.error('Error al eliminar:', error);
        //});
        cerrar();
  };    

  return (
    <>
      {/* Fondo desenfocado */}
      <div
        className="position-fixed top-0 start-0 w-100 h-100"
        style={{
          backdropFilter: "blur(6px)",
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          zIndex: 1040,
        }}
      />

      {/* Modal Bootstrap conservado */}
      <div
        className="modal show d-block"
        tabIndex="-1"
        role="dialog"
        style={{ zIndex: 1050 }}
      >
        <div className="modal-dialog modal-lg">
          <div className="modal-content bg-dark text-white">
            <div className="modal-header">
              <h5 className="modal-title">Editar Datos del Producto</h5>
              <button
                type="button"
                className="btn-close btn-close-white"
                onClick={cerrar}
              ></button>
            </div>
            <div className="modal-body">
                        <form className="row g-3">
                        <div className="col-md-6">
                            <label  className="form-label">Nombre del producto</label>
                            <input type="text" className="form-control" value={datosProducto.nombre}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">SKU:</label>
                            <input type="text" className="form-control" value={datosProducto.sku} />
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Familia</label>
                             <select id="inputState" className="form-select" >
                                <option>Choose...</option>
                            </select>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Sub Familia:</label>
                            <select id="inputState" className="form-select">
                                <option>Choose...</option>
                            </select>
                        </div>
                        <div className="col-md-12">
                            <label  className="form-label">Descripcion</label>
                            <input type="text" className="form-control"value={datosProducto.descripcion}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Precio</label>
                            <input type="text" className="form-control" value={datosProducto.precio}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Estado</label>
                             <select id="inputState" className="form-select">
                                <option>Activo</option>
                                <option>Inactivo</option>
                            </select>
                        </div>
                    </form>
            </div>
            <div className="modal-footer">
              <button className="btn btn-secondary" onClick={cerrar}>
                Cancelar
              </button>
              <button className="btn btn-success" onClick={handleConfirmar}>
                Confirmar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default EditarProducto