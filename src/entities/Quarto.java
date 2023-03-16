package entities;

import entities.enums.TipoCama;

public class Quarto {

    private Integer numeroQuarto;
    private TipoCama tipoCama;
    private String aceitaAnimais;


    public Quarto(){

    }

    public Quarto(Integer numeroQuarto, TipoCama tipoCama, String aceitaAnimais) {
        this.numeroQuarto = numeroQuarto;
        this.tipoCama = tipoCama;
        this.aceitaAnimais = aceitaAnimais;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public TipoCama getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(TipoCama tipoCama) {
        this.tipoCama = tipoCama;
    }

    public String getAceitaAnimais() {
        return aceitaAnimais;
    }

    public void setAceitaAnimais(String aceitaAnimais) {
        this.aceitaAnimais = aceitaAnimais;
    }
}
