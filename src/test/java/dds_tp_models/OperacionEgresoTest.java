package dds_tp_models;

import dds_tp_exceptions.NoSePuedeAgregarItemsException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OperacionEgresoTest {
    private Articulo articulo1;
    private Articulo articulo2;
    private Articulo articulo3;
    private Servicio servicio1;
    private Servicio servicio2;
    private Egreso operacionDeEgreso;
    private Compra operacionDeCompra;

    @Before
    public void setUp() {
        articulo1 = new Articulo();
        articulo2 = new Articulo();
        articulo3 = new Articulo();
        articulo1.setValor(50);
        articulo2.setValor(100);
        articulo3.setValor(100);

        servicio1 = new Servicio();
        servicio2 = new Servicio();
        servicio1.setValor(500);
        servicio2.setValor(1000);

        operacionDeEgreso = new Egreso();
        operacionDeCompra = new Compra();
    }

    @Test
    public void calcularPrecioDeEgreso_soloArticulos() throws Exception {

        operacionDeEgreso.agregarItem(articulo1);
        operacionDeEgreso.agregarItem(articulo2);

        operacionDeEgreso.calcularPrecio();
        operacionDeEgreso.agregarItem(articulo3);
        operacionDeEgreso.calcularPrecio();
        operacionDeEgreso.finalizarOperacion();

        assertEquals(operacionDeEgreso.getPrecio(), new Integer(250));
    }

    @Test
    public void obtenerDocumentoComercial_EgresoSoloArticulos_emiteComprobante() throws Exception {
        operacionDeEgreso.agregarItem(articulo1);
        operacionDeEgreso.agregarItem(articulo2);
        operacionDeEgreso.calcularPrecio();
        operacionDeEgreso.finalizarOperacion();

        assertEquals(operacionDeEgreso.getDocumentoComercial(), DocumentoComercial.COMPROBANTE);
    }

    @Test
    public void obtenerDocumentoComercial_CompraArticulosYServicios_emiteComprobante() throws Exception {
        operacionDeCompra.agregarItem(articulo1);
        operacionDeCompra.agregarItem(articulo2);
        operacionDeCompra.agregarItem(servicio1);
        operacionDeCompra.agregarItem(servicio2);

        operacionDeCompra.calcularPrecio();
        operacionDeCompra.finalizarOperacion();

        assertEquals(operacionDeCompra.getDocumentoComercial(), DocumentoComercial.COMPROBANTE);
    }

    @Test
    public void obtenerDocumentoComercial_CompraSoloArticulos_emiteRemitoYEstadoAbierto() throws Exception {
        operacionDeCompra.agregarItem(articulo1);
        operacionDeCompra.agregarItem(articulo2);

        operacionDeCompra.calcularPrecio();
        operacionDeCompra.finalizarOperacion();

        assertEquals(operacionDeCompra.getDocumentoComercial(), DocumentoComercial.REMITO);
        assertEquals(operacionDeCompra.getEstado(), Estado.ABIERTO);
    }

    /* Si un articulo cambia de precio se recalcula el valor de la operacion solo si el estado
    de la operacion esta abierto*/
    @Test
    public void recalcularPrecio_operacionDeCompra_estadoAbierto() throws Exception {

        operacionDeCompra.agregarItem(articulo1);

        operacionDeCompra.calcularPrecio();
        operacionDeCompra.finalizarOperacion();
        Integer precioInicial = operacionDeCompra.getPrecio();
        operacionDeCompra.agregarItem(articulo2);
        operacionDeCompra.actualizarPrecio();
        operacionDeCompra.finalizarOperacion();
        Integer precioRecalculado = operacionDeCompra.getPrecio();

        assertNotEquals(precioInicial, precioRecalculado);
    }

    @Test(expected = NoSePuedeAgregarItemsException.class)
    public void recalcularPrecio_operacionDeEgreso_estadoCerrado_retornaException() throws Exception {
        operacionDeEgreso.agregarItem(articulo1);
        operacionDeEgreso.calcularPrecio();
        operacionDeEgreso.finalizarOperacion();
        operacionDeEgreso.agregarItem(articulo2);
    }


}
