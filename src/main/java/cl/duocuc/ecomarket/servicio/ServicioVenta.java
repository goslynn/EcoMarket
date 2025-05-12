package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.funcional.CalculaMonto;
import cl.duocuc.ecomarket.modelo.dto.venta.VentaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;
import cl.duocuc.ecomarket.modelo.repository.DetalleVentaRepository;
import cl.duocuc.ecomarket.modelo.repository.VentaRepository;
import cl.duocuc.ecomarket.util.LineaDetalle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioVenta {
    private final VentaRepository venaRepo;
    private final DetalleVentaRepository detalleRepo;
    private final ServicioInventario inventario;

    public ServicioVenta(VentaRepository venaRepo, DetalleVentaRepository detalleRepo, ServicioInventario inventario) {
        this.venaRepo = venaRepo;
        this.detalleRepo = detalleRepo;
        this.inventario = inventario;
    }

    public void registrarVenta(VentaDTO venta) {
        final Venta registroVenta = new Venta();
        final List<DetalleVenta> registroDetalle = new ArrayList<>();

        registroVenta.setIdCliente(venta.idCliente());
        registroVenta.setIdTipoDocumento(4); //Factura
        registroVenta.setIdFormaPago(venta.idFormaPago());
        registroVenta.setObservaciones(venta.observaciones());

        //Extraer los datos a la interfaz que usa la clase CalculaMonto
        List<LineaDetalle> lineas = venta.items().stream().map(item -> new LineaDetalle() {
            @Override
            public Integer getCantidad() {
                return item.cantidad();
            }

            @Override
            public BigDecimal getPrecioUnitario() {
                return item.precioUnitario();
            }
        }).collect(Collectors.toList());

        //Calcular los montos de la venta
        CalculaMonto.Resultado r = CalculaMonto.calcular(lineas);

        registroVenta.setTotalNeto(r.subtotal());
        registroVenta.setTotalBruto(r.total());
        registroVenta.setTotalIva(r.totalIva());

        venaRepo.save(registroVenta);

        venta.items().forEach(item ->{
           DetalleVenta det = new DetalleVenta();
           det.setIdVenta(registroVenta);
           //TODO: Obtener el producto desde la base de datos
           det.setIdProducto(item.idProducto());
           det.setCantidad(item.cantidad());
           det.setPrecioUnitario(item.precioUnitario());
           registroDetalle.add(det);
        });

        detalleRepo.saveAll(registroDetalle);
    }



}
