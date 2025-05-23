import { useState, useEffect } from "react";
import { useFetch } from "./useFetch";
import Skeleton from "react-loading-skeleton";

const TablaUsuarios = ({ tipo }) => {
  const [cargando, setCargando] = useState(true);

  // Simula tiempo de carga
  useEffect(() => {
    const timer = setTimeout(() => setCargando(false), 100);
  }, []);

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
            {usuarios.data.map((u, index) => (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>{u.nombre}</td>
                <td>{u.correo}</td>
                <td>{u.genero}</td>
                <td>{u.rol}</td>
                <td>{u.fechaCreacion}</td>
                <td>{u.activo ? "Sí" : "No"}</td>
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
            {productos.data.map((p, i) => (
              <tr key={p.idProducto}>
                <td>{i + 1}</td>
                <td>{p.CodigoProducto}</td>
                <td>{p.NombreProducto}</td>
                <td>{p.Descripcion}</td>
                <td>{p.Precio}</td>
                <td>{p.idSubFamilia}</td>
                <td>{p.Activo ? "Sí" : "No"}</td>
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
        <table className="table table-striped mt-3">
          <thead className="table-success">
            <tr>
              <th>#</th>
              <th>Código</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {familias.data.map((f, i) => (
              <tr key={f.id_familia}>
                <td>{i + 1}</td>
                <td>{f.codigo}</td>
                <td>{f.nombre}</td>
                <td>{f.descripcion}</td>
                <td>{f.activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2">Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      );

    case 4:
      return (
        <table className="table table-striped mt-3">
          <thead className="table-success">
            <tr>
              <th>#</th>
              <th>Código</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {subfamilias.data.map((sf, i) => (
              <tr key={sf.id_subfamilia}>
                <td>{i + 1}</td>
                <td>{sf.codigo}</td>
                <td>{sf.nombre}</td>
                <td>{sf.descripcion}</td>
                <td>{sf.activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2">Borrar</button>
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
