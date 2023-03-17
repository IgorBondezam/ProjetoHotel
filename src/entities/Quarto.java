package entities;

import entities.enums.TipoCama;

public class Quarto {

    private Integer numeroQuarto;
    private Integer tipoCama;
    private String aceitaAnimais;


    public Quarto(){

    }

    public Quarto(Integer numeroQuarto, TipoCama tipoCama, String aceitaAnimais) {
        this.numeroQuarto = numeroQuarto;
        setTipoCama(tipoCama);
        this.aceitaAnimais = aceitaAnimais;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public TipoCama getTipoCama() {
        return TipoCama.valueOf(tipoCama);
    }

    public void setTipoCama(TipoCama tipoCama) {
        if(tipoCama!= null) {
            this.tipoCama = tipoCama.getCode();
        }
    }

    public String getAceitaAnimais() {
        return aceitaAnimais;
    }

    public void setAceitaAnimais(String aceitaAnimais) {
        this.aceitaAnimais = aceitaAnimais;
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "numeroQuarto=" + numeroQuarto +
                ", tipoCama=" + TipoCama.valueOf(tipoCama) +
                ", aceitaAnimais='" + aceitaAnimais + '\'' +
                '}';
    }
}
