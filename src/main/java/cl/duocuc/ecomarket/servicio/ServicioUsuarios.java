package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.modelo.PersistenciaSP;
import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.modelo.entity.usuario.*;
import cl.duocuc.ecomarket.modelo.dto.usuario.signup.*;
import cl.duocuc.ecomarket.modelo.mapper.PermisoMapper;
import cl.duocuc.ecomarket.modelo.mapper.RolMapper;
import cl.duocuc.ecomarket.modelo.mapper.UsuarioMapper;
import cl.duocuc.ecomarket.modelo.repository.PermisoRepository;
import cl.duocuc.ecomarket.modelo.repository.RolRepository;
import cl.duocuc.ecomarket.modelo.repository.RolesPermisoRepository;
import cl.duocuc.ecomarket.modelo.repository.UsuarioRepository;
import cl.duocuc.ecomarket.tipodatos.TipoCuenta;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
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
    private final RolesPermisoRepository rolesPermisoRepo;

    @PersistenceContext
    private EntityManager em;
    private final EncriptadorChetado encriptador = new EncriptadorChetado();
    private PersistenciaSP persistencia;
    private static final Logger log = LoggerFactory.getLogger(ServicioUsuarios.class);

    public ServicioUsuarios(UsuarioRepository userRepo,
                            RolRepository rolRepo,
                            PermisoRepository permisoRepo,
                            RolesPermisoRepository rolesPermisoRepo) {
        this.userRepo = userRepo;
        this.rolRepo = rolRepo;
        this.permisoRepo = permisoRepo;
        this.rolesPermisoRepo = rolesPermisoRepo;
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
     * @param u
     * @throws ApiException
     */
    public CodigoDescripcion<Integer,String> actualizar(Integer id, UsuarioUpdateRequestDTO u) throws ApiException {
        if (!userRepo.existsById(id)){
            throw new RuntimeException("el usuario no existe");
        }
        persistencia.actualizarUsuario(id,
                u.nombre(),
                u.correo(),
                encriptador.encriptado(u.contrasenaHash()) ?
                u.contrasenaHash() : encriptador.encriptar(u.contrasenaHash()),
                u.fechaNacimiento(),
                String.valueOf(u.genero().toChar()),
                u.idRol(),
                u.telefono(),
                u.rutEmpleado(),
                u.fechaContratacion(),
                u.cargoEmpleado(),
                u.areaEmpleado());
        return new CodigoDescripcion<>() {
            @Override
            public Integer getCodigo() {
                return id;
            }

            @Override
            public String getDescripcion() {
                return "Usuario actualizado correctamente";
            }
        };
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

//    public void desactivarRol(Long id){
//
//    }







    @Transactional
    public RolDTO crearRol(RolPermisosRequestDTO dto) throws ApiException {
        Rol rol = new Rol();
        rol.setNombreRol(dto.nombre());
        rol.setDescripcion(dto.descripcion());
        rol = rolRepo.save(rol);

        for (Integer idPermiso : dto.permisos()) {
            Permiso permiso = permisoRepo.findById(idPermiso)
                    .orElseThrow(() -> new ApiException(404, "Permiso con ID " + idPermiso + " no encontrado"));
            RolesPermiso rp = new RolesPermiso();
            rp.setId(new RolesPermisoId(Math.toIntExact(rol.getId()), Math.toIntExact(permiso.getId())));
            rp.setRol(rol);
            rp.setPermiso(permiso);
            rolesPermisoRepo.save(rp);
        }

        return RolMapper.toDTO(rol);
    }

    @Transactional(readOnly = true)
    public RolPermisosResponseDTO obtenerRolConPermisos(Integer id) throws ApiException {
        Rol rol = rolRepo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ApiException(404, "Rol con ID " + id + " no encontrado"));

        List<PermisoResponseDTO> permisos = rolesPermisoRepo.findAllByRol_Id(Math.toIntExact(id)).stream()
                .map(rp -> PermisoMapper.toResponseDTO(rp.getPermiso()))
                .toList();

        return new RolPermisosResponseDTO(rol.getId(), rol.getNombreRol(), rol.getDescripcion(), permisos);
    }

    @Transactional(readOnly = true)
    public List<RolDTO> obtenerTodosLosRoles() throws ApiException {
        List<Rol> roles = rolRepo.findAll().stream().filter(Rol::getActivo).toList();
        if (roles.isEmpty()) {
            throw new ApiException(404, "No existen roles");
        }
        return roles.stream().map(RolMapper::toDTO).toList();
    }

    @Transactional
    public void eliminarRol(Integer id) throws ApiException {
        if (!rolRepo.existsById(id)) {
            throw new ApiException(404, "El rol no existe");
        }
        rolRepo.deleteById(id);
    }

    @Transactional
    public void actualizarRol(Integer id, RolPermisosRequestDTO dto) throws ApiException {
        Rol rol = rolRepo.findById(id)
                .orElseThrow(() -> new ApiException(404, "Rol con ID " + id + " no encontrado"));

        rol.setNombreRol(dto.nombre());
        rol.setDescripcion(dto.descripcion());
        rolRepo.save(rol);

        rolesPermisoRepo.deleteAll(rolesPermisoRepo.findAllByRol_Id(id));

        for (Integer idPermiso : dto.permisos()) {
            Permiso permiso = permisoRepo.findById(idPermiso)
                    .orElseThrow(() -> new ApiException(404, "Permiso con ID " + idPermiso + " no encontrado"));
            RolesPermiso rp = new RolesPermiso();
            rp.setId(new RolesPermisoId(id, idPermiso));
            rp.setRol(rol);
            rp.setPermiso(permiso);
            rolesPermisoRepo.save(rp);
        }
    }

    @Transactional
    public void agregarPermisoARol(Integer idRol, Integer idPermiso) throws ApiException {
        Rol rol = rolRepo.findById(idRol)
                .orElseThrow(() -> new ApiException(404, "Rol no encontrado"));
        Permiso permiso = permisoRepo.findById(idPermiso)
                .orElseThrow(() -> new ApiException(404, "Permiso no encontrado"));
        RolesPermiso rp = new RolesPermiso();
        rp.setId(new RolesPermisoId(idRol, idPermiso));
        rp.setRol(rol);
        rp.setPermiso(permiso);
        rolesPermisoRepo.save(rp);
    }

    @Transactional
    public void quitarPermisoARol(Integer idRol, Integer idPermiso) throws ApiException {
        rolesPermisoRepo.deleteByRol_IdAndPermiso_Id(idRol, idPermiso);
    }


}
