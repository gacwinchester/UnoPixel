package objects;

import enums.Cores;
import enums.TipoCarta;

public class Carta {

    private Cores cor = null;
    private TipoCarta tipo = null;
    private Integer numero = null;


    public Carta(Cores cor, TipoCarta tipo, Integer numero){
        this.cor = cor;
        this.tipo = tipo;
        this.numero = numero;
    }

    public Carta(Cores cor, TipoCarta tipo){
        this.cor = cor;
        this.tipo = tipo;
    }

    public Cores getCor() {
        return cor;
    }

    public void setCor(Cores cor) {
        this.cor = cor;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarta tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
