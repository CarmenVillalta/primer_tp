package dds_tp_models;

public class Articulo implements Item{

    private Integer valor;

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public Boolean generaCertificado() {
        return true;
    }

    @Override
    public Integer valor() {
        return this.valor;
    }
}