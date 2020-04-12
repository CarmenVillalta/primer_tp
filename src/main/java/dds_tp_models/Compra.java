package dds_tp_models;

public class Compra extends Operacion{

    @Override
    public Boolean generaRemito() {
        return  items.stream().allMatch(item -> item.generaCertificado() == true);
    }

    @Override
    public void finalizarOperacion() {
        if(generaRemito())
            documentoComercial = DocumentoComercial.REMITO;
        else {
            documentoComercial = DocumentoComercial.COMPROBANTE;
            estado = Estado.CERRADO;
        }
    }

}
