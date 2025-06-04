package cl.duocuc.ecomarket.security;

public interface ProveedorJWT {

    String generarToken(Number id, int permiso);

    boolean validarToken(String token);

    Number getIdUsuario(String token);

    int getPermiso(String token);
}
