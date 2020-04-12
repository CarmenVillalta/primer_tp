package dds_tp_models;

import dds_tp_exceptions.NoSePuedeAgregarItemsException;

import java.util.ArrayList;
import java.util.List;

public abstract class Operacion {
    public List<Item> items;
    public Estado estado;
    public DocumentoComercial documentoComercial;
    public Integer precio;

    protected Operacion() {
        this.estado = Estado.ABIERTO;
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item item) throws Exception {
        if (this.getEstado() == Estado.ABIERTO)
            items.add(item);
        else
            throw new NoSePuedeAgregarItemsException();
    }

    public Integer getPrecio(){
        return this.precio;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public DocumentoComercial getDocumentoComercial() {
        return this.documentoComercial;
    }

    public void cambiarEstadoA(Estado estado){
        this.estado = estado;
    }

    public void calcularPrecio() {
        this.precio = items.stream().mapToInt(item -> item.valor()).sum();
    }

    public void actualizarPrecio() {
        if(this.estado == Estado.ABIERTO)
            this.calcularPrecio();
    }

    public void finalizarOperacion() {}

    public Boolean generaRemito() {
        return false;
    }

}
