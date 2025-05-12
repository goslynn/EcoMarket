package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.modelo.PersistenciaSP;
import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.modelo.entity.usuario.*;
import cl.duocuc.ecomarket.modelo.dto.usuario.signup.*;
import cl.duocuc.ecomarket.modelo.mapper.PermisoMapper;
import cl.duocuc.ecomarket.modelo.mapper.RolMapper;
import cl.duocuc.ecomarket.modelo.mapper.UsuarioMapper;
import cl.duocuc.ecomarket.modelo.repository.Permiso.PermisoRepository;
import cl.duocuc.ecomarket.modelo.repository.Roles.RolRepository;
import cl.duocuc.ecomarket.modelo.repository.Usuario.UsuarioRepository;
import cl.duocuc.ecomarket.tipodatos.TipoCuenta;
import cl.duocuc.ecomarket.util.encriptacion.EncriptadorChetado;
import cl.duocuc.ecomarket.util.exception.ApiException;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioUsuarios {

    private final UsuarioRepository userRepo;
    private final RolRepository rolRepo;
    private final PermisoRepository permisoRepo;
    @PersistenceContext
    private EntityManager em;
    private final EncriptadorChetado encriptador = new EncriptadorChetado();
    private PersistenciaSP persistencia;
    private static final Logger log = LoggerFactory.getLogger(ServicioUsuarios.class);

    public ServicioUsuarios(UsuarioRepository userRepo, RolRepository rolRepo, PermisoRepository permisoRepo) {
        this.userRepo = userRepo;
        this.rolRepo = rolRepo;
        this.permisoRepo = permisoRepo;
    }

    @PostConstruct
    public void init() {
        this.persistencia = new PersistenciaSP(em);
    }



    public UsuarioResponseDTO obtenerUsuario(Integer id) throws ApiException{
        return userRepo.findById(id)
                       .filter(Usuario::getActivo)
                       .map(UsuarioMapper::getResponseDTO)
                       .orElseThrow(() -> new ApiException(404, String.format("el usuario con id {%d} no existe", id))
        );
    }

    public RolDTO obtenerRol(Integer id) throws ApiException{
        return rolRepo.findById(id)
                .filter(Rol::getActivo)
                .map(RolMapper::toDTO)
                .orElseThrow(() -> new ApiException(404, String.format("el rol con id {%d} no existe", id)));
    }

    public List<RolDTO> obtenerRoles() throws ApiException{
        List<Rol> roles = rolRepo.findAll().stream().filter(Rol::getActivo).toList();
        if (roles.isEmpty()){
            throw new ApiException(404, "no existen roles");
        }
        return roles.stream().map(RolMapper::toDTO).toList();
    }

    public List<PermisoResponseDTO> obtenerPermisos() throws ApiException{
        List<Permiso> permisos = permisoRepo.findAll().stream().filter(Permiso::getActivo).toList();
        if (permisos.isEmpty()){
            throw new ApiException(404, "no existen permisos");
        }
        return permisos.stream().map(PermisoMapper::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public RolPermisosDTO obtenerRolPermisos(Integer id) throws ApiException {
        Rol rol = rolRepo.findWithPermisosById(id)
                .orElseThrow(() -> new ApiException(404, String.format("El rol con ID %d no existe", id)));

        List<PermisoResponseDTO> permisos = rol.getRolesPermisos().stream()
                .map(RolesPermiso::getPermiso)
                .filter(Permiso::getActivo)
                .map(PermisoMapper::toResponseDTO)
                .toList();

        return new RolPermisosDTO(
                rol.getId(),
                rol.getNombreRol(),
                rol.getDescripcion(),
                permisos
        );
    }

    /**
     * Registra un empleado, llama al procedimiento almacenado
     * pasandole solo los datos correspondientes a empleado, dejando
     * nulos los correspondientes a cliente
     * @param e Empleado a registrar
     * @return 'JSON' de respuesta para la api
     * @throws ApiException Error lanzado ante error de validacion o de ejecucion del procedimiento
     */
    public UsuarioResponseDTO registrar(EmpleadoRegistroDTO e) throws ApiException {
        return persistencia.agregarUsuario(
                null,  // Teléfono (no aplica para empleados)
                e.nombre(),
                e.correo(),
                e.fechaNacimiento(),
                encriptador.encriptar(e.contrasena()),
                String.valueOf(e.genero().toChar()),
                e.rut(),
                e.fechaContratacion(),
                e.cargoEmpleado(),
                e.AreaEmpleado(),
                e.idRolUsuario(),
                TipoCuenta.EMPLEADO.getId()
        );
    }

    /**
     * Registra un cliente, llama al procedimiento almacenado
     * pasandole solo los datos correspondientes a cliente, dejando
     * nulos los correspondientes a empleado
     * @param c Cliente a registrar
     * @return 'JSON' de respuesta para la api
     * @throws ApiException Error lanzado ante error de validacion o de ejecucion del procedimiento
     */
    public UsuarioResponseDTO registrar(ClienteRegistroDTO c) throws ApiException {
        return persistencia.agregarUsuario(
                c.telefono(),
                c.nombre(),
                c.correo(),
                c.fechaNacimiento(),
                encriptador.encriptar(c.contrasena()),
                String.valueOf(c.genero().toChar()),
                "",  // RUT (no aplica para clientes)
                "",  // Fecha de contratación (no aplica para clientes)
                "",  // Cargo (no aplica para clientes)
                "",  // Área (no aplica para clientes)
                c.idRolUsuario(),
                TipoCuenta.CLIENTE.getId()
        );
    }

    /**
     * Aca se declara el metodo de el put y nos asguramos que cuando se pase una peticion con un id que no es el que
     * corresponda arroje error de que no existe
     * @param id
     * @param usuario
     * @throws ApiException
     */
    @Transactional
    public void actualizar(Integer id, UsuarioUpdateRequestDTO usuario) throws ApiException {
        if (!userRepo.existsById(id)){
            throw new RuntimeException("el usuario no existe");
        }
        persistencia.actualizarUsuario(id, usuario);
    }


    public void desactivarUsuario(Integer id){
        if (!userRepo.existsById(id)){
            throw new RuntimeException("el usuario no existe");
        }
        userRepo.findById(id).map(bd -> {
            bd.setActivo(false);
            return userRepo.save(bd);
        });
    }

    public void desactivarRol(Long id){

    }

}
