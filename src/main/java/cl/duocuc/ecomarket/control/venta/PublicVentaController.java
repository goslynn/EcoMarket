package cl.duocuc.ecomarket.control.venta;


import cl.duocuc.ecomarket.funcional.RequierePermiso;
import cl.duocuc.ecomarket.modelo.dto.venta.PeticionVentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.RespuestaVentaDTO;
import cl.duocuc.ecomarket.servicio.ServicioVenta;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/venta")
public class PublicVentaController {
    private final ServicioVenta servicio;

    public PublicVentaController(ServicioVenta servicioVenta) {
        this.servicio = servicioVenta;
    }

    @GetMapping("/{id}")
    @RequierePermiso(TipoPermiso.VER_VENTAS)
    public ResponseEntity<RespuestaVentaDTO> consultarVenta(@PathVariable Integer id){
        return ResponseEntity.ok(servicio.obtenerVenta(id));
    }

    @PostMapping
    public ResponseEntity<CodigoDescripcion<Number, String>> registrarVenta(@Valid @RequestBody PeticionVentaDTO venta){
        return ResponseEntity.ok(servicio.registrarVenta(venta));
    }


}
