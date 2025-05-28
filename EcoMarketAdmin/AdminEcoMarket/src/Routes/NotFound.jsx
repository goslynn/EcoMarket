import { Link } from 'react-router-dom';
import '../css/NotFound.css'; // Asegúrate de crear este archivo

const NotFound = () => {
  return (
    <div className="notfound-container">
      <div className="notfound-content">
        <div className="icon">⚠️</div>
        <h1 className="notfound-title">404</h1>
        <p className="notfound-message">Lo sentimos, esta página no existe.</p>
        <p className="notfound-subtext">
          Puede que el enlace esté roto o que se haya eliminado la página.
        </p>
        <Link to="/" className="notfound-button">
          Volver al inicio
        </Link>
      </div>
      <footer className="notfound-footer">
        © {new Date().getFullYear()} TuEmpresa. Todos los derechos reservados.
      </footer>
    </div>
  );
};

export default NotFound;
