package dds_tp_models;

public class Egreso extends Operacion{


    @Override
    public void finalizarOperacion() {
        documentoComercial = DocumentoComercial.COMPROBANTE;
        cambiarEstadoA(Estado.CERRADO);
    }

}