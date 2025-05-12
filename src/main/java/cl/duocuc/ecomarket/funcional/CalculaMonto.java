package cl.duocuc.ecomarket.funcional;

import cl.duocuc.ecomarket.util.LineaDetalle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculaMonto {
    public static final BigDecimal IVA = new BigDecimal("0.19");


    public static Resultado calcular(List<LineaDetalle> lineasDetalle){
        BigDecimal totalNeto = BigDecimal.ZERO;

        for (LineaDetalle linea : lineasDetalle) {
            totalNeto = totalNeto.add(calcularLinea(linea));
        }

        BigDecimal totalIva = totalNeto.multiply(IVA).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalBruto = totalNeto.add(totalIva).setScale(2, RoundingMode.HALF_UP);

        return new Resultado(
                totalNeto.setScale(2, RoundingMode.HALF_UP),
                totalBruto,
                totalIva
        );
    }

    private static BigDecimal calcularLinea(LineaDetalle linea){
        return linea.getCantidad().multiply(linea.getPrecioUnitario());
    }


    public record Resultado(
        BigDecimal subtotal,
        BigDecimal total,
        BigDecimal totalIva
    ){}
}
