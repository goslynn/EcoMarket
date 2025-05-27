const ModalEliminar = ({ cerrar, item, tipo }) => {
  const handleConfirmar = () => {
    console.log("Eliminar", item, "de tipo", tipo);
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
        <div className="modal-dialog">
          <div className="modal-content bg-dark text-white">
            <div className="modal-header">
              <h5 className="modal-title">Confirmar eliminación</h5>
              <button
                type="button"
                className="btn-close btn-close-white"
                onClick={cerrar}
              ></button>
            </div>
            <div className="modal-body">
              <p>
                ¿Estás seguro que deseas eliminar{" "}
                <strong>
                  {item?.nombre_familia ||
                    item?.NombreProducto ||
                    item?.nombre_subfamilia}
                </strong>
                ?
              </p>
            </div>
            <div className="modal-footer">
              <button className="btn btn-secondary" onClick={cerrar}>
                Cancelar
              </button>
              <button className="btn btn-danger" onClick={handleConfirmar}>
                Eliminar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ModalEliminar;
