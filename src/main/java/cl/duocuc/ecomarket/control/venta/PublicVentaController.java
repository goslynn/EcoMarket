package cl.duocuc.ecomarket.control.venta;

import cl.duocuc.ecomarket.modelo.dto.venta.VentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.VentaResponseDTO;
import cl.duocuc.ecomarket.servicio.ServicioVenta;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/venta")
public class PublicVentaController {
    private final ServicioVenta servicio;

    public PublicVentaController(ServicioVenta servicio){
        this.servicio = servicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> consultarVenta(@PathVariable Integer id){
        return ResponseEntity.ok(servicio.obtenerVenta(id));
    }

    @PostMapping
    public ResponseEntity<CodigoDescripcion<Integer, String>> registrarVenta(@Valid @RequestBody VentaDTO venta){
        return ResponseEntity.ok(servicio.registrarVenta(venta));
    }


}
