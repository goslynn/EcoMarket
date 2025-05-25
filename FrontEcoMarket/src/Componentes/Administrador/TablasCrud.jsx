import { useState, useEffect } from "react";
import { useFetch } from "./useFetch";
import Skeleton from "react-loading-skeleton";
import ModalEliminar from "../ModalEliminar";

const TablaUsuarios = ({ tipo }) => {
  const [cargando, setCargando] = useState(true);
  const [mostrar, setMostrar] = useState(false)
  useEffect(() => {
    const timer = setTimeout(() => setCargando(false), 1000);
  }, []);

  const mostrarModal =  ()=>{
    setMostrar(false)
  }


  // Hooks fuera del switch
  const usuarios = useFetch("http://localhost:8080/api/v1/public/usuario/getusuarios");
  const productos = useFetch("http://localhost:8080/api/v1/public/inventario/producto");
  const familias = useFetch("http://localhost:8080/api/v1/public/inventario/familia");
  const subfamilias = useFetch("http://localhost:8080/api/v1/public/inventario/subfamilia");

  if (cargando) {
    return (
        <table className="table table-striped mt-3">
      
                <thead className="table-success">
                  <tr>
                    <th><Skeleton/></th>
                    <th><Skeleton/></th>
                    <th><Skeleton/></th>
                    <th><Skeleton/></th>
                    <th><Skeleton/></th>
                    <th><Skeleton/></th>
                    <th><Skeleton/></th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td className="oculto">1</td>
                    <td> <Skeleton/></td>
                    <td><Skeleton/></td>
                    <td><Skeleton/></td>
                    <td><Skeleton/></td>
                    <td><Skeleton/></td>
                    <td><Skeleton/></td>
                    <td>
                      <button className="btn btn-success"><Skeleton/></button>
                      <button className="btn btn-danger ms-3"><Skeleton/></button>
                    </td>
                  </tr>
                </tbody>
              </table>
    );
  }

  switch (tipo) {
    case 1:
      return (
        <table className="table table-striped mt-3">
          <thead className="table-success">
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Correo</th>
              <th>Género</th>
              <th>Rol</th>
              <th>Fecha de Creación</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {usuarios.data.map((user, index) => (
              <tr key={user.idUsuario}>
                <td>{index + 1}</td>
                <td>{user.nombre}</td>
                <td>{user.correo}</td>
                <td>{user.genero}</td>
                <td>{user.rol}</td>
                <td>{user.fechaCreacion}</td>
                <td>{user.activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-3">Eliminar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      );

    case 2:
      return (
        <table className="table table-bordered table-dark table-hover mt-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Código</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Precio</th>
              <th>Subfamilias</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {productos.data.map((produ, i) => (
              <tr key={produ.idProducto}>
                <td>{i + 1}</td>
                <td>{produ.CodigoProducto}</td>
                <td>{produ.NombreProducto}</td>
                <td>{produ.Descripcion}</td>
                <td>{produ.Precio}</td>
                <td>{produ.idSubFamilia}</td>
                <td>{produ.Activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2">Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      );

    case 3:
      return (
      <>
         {mostrar && <ModalEliminar cerrar={() => setMostrar(false)} />}
        <table className="table table-bordered table-dark table-hover mt-3">
          <thead >
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {familias.data.map((fam, i) => (
              <tr key={fam.id_familia}>
                <td>{i + 1}</td>
                <td>{fam.nombre_familia}</td>
                <td>{fam.descripcion}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2" onClick={mostrarModal}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </>
      );

    case 4:
      return (
        <table className="table table-bordered table-dark table-hover mt-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Descripcion</th>
              <th>ID familia</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {subfamilias.data.map((sub, i) => (
              <tr key={sub.id_subfamilia}>
                <td>{i + 1}</td>
                <td>{sub.nombre_subfamilia}</td>
                <td>{sub.descripcion}</td>
                <td>{sub.id_familia}</td>
                <td>{sub.activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2" onClick={()=>{mostrarModal}}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      );

    default:
      return <p>No se ha seleccionado un tipo de tabla válido.</p>;
  }
};

export default TablaUsuarios;
