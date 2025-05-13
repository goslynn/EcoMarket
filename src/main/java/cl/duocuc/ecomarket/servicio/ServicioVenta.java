package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.funcional.CalculaMonto;
import cl.duocuc.ecomarket.modelo.dto.venta.VentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.VentaResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;
import cl.duocuc.ecomarket.modelo.mapper.VentaMapper;
import cl.duocuc.ecomarket.modelo.repository.DetalleVentaRepository;
import cl.duocuc.ecomarket.modelo.repository.ProductoRepository;
import cl.duocuc.ecomarket.modelo.repository.VentaRepository;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.LineaDetalle;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioVenta {
    private final VentaRepository ventaRepo;
    private final DetalleVentaRepository detalleRepo;
    private final ProductoRepository productoRepo;
    private final ServicioInventario inventario;

    public ServicioVenta(VentaRepository venaRepo, DetalleVentaRepository detalleRepo, ServicioInventario inventario, ProductoRepository productoRepo) {
        this.ventaRepo = venaRepo;
        this.detalleRepo = detalleRepo;
        this.inventario = inventario;
        this.productoRepo = productoRepo;
    }

    public VentaResponseDTO obtenerVenta(Integer id) throws ApiException {
        Venta v = ventaRepo.findById(id).filter(Venta::getActivo).orElseThrow(
                () -> new ApiException(404, String.format("La venta con ID %d no existe", id))
        );
        return VentaMapper.toResponseDTO(v);
    }

    public CodigoDescripcion<Integer, String> registrarVenta(VentaDTO venta) {
        final Venta registroVenta = new Venta();
        final List<DetalleVenta> registroDetalle = new ArrayList<>();

        registroVenta.setIdCliente(venta.idCliente());
        registroVenta.setIdTipoDocumento(4); //Factura
        registroVenta.setIdEstadoVenta(6); //Pagada
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

        ventaRepo.save(registroVenta);

        venta.items().forEach(item ->{
           DetalleVenta det = new DetalleVenta();
           det.setIdVenta(registroVenta);
           det.setIdProducto(productoRepo.getReferenceById(item.idProducto()));
           det.setCantidad(item.cantidad());
           det.setPrecioUnitario(item.precioUnitario());
           registroDetalle.add(det);
        });

        detalleRepo.saveAll(registroDetalle);

        return new CodigoDescripcion<Integer, String>() {
            @Override
            public Integer getCodigo() {
                return registroVenta.getId();
            }

            @Override
            public String getDescripcion() {
                return "Venta registrada con éxito";
            }
        };
    }





}
