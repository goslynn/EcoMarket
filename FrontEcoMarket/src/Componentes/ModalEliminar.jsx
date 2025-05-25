import React from 'react'


const ModalEliminar = () => {
  return (
    <div className="modal" tabindex="-1">
  <div className="modal-dialog">
    <div className="modal-content">
      <div className="modal-header">
        <h5 className="modal-title">¿Estas seguro que quieres <strong>Eliminar</strong> el producto?</h5>
        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div className="modal-body">
        <p>El producto seleccionado sera eliminado completamente y se dificultara su recuperacion...<br>¿Desea eliminarlo completamente?</br></p>
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-success">Estoy seguro</button>
        <button type="button" className="btn btn-danger">Cancelar</button>
      </div>
    </div>
  </div>
</div>
  )
}

export default ModalEliminar