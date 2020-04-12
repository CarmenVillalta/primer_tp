package dds_tp_exceptions;

public class NoSePuedeAgregarItemsException extends Exception{

    public NoSePuedeAgregarItemsException(String mensaje){
        super(mensaje);
    }

    public NoSePuedeAgregarItemsException(){
            super("OPERACION CERRADA. No es posible agregar mas articulos");
        }
}
