package entities;

import entities.Util.Utils;
import entities.enums.TipoCama;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quarto {

    private Integer numeroQuarto;
    private Integer tipoCama;
    private String aceitaAnimais;

    private  List<Hospede> hospedeList = new ArrayList<>();
    private  List<Date> checkInDoQuarto = new ArrayList<>();
    private  List<Date> checkOutDoQuarto = new ArrayList<>();

    //Construtores
    public Quarto(){

    }

    public Quarto(Integer numeroQuarto, TipoCama tipoCama, String aceitaAnimais) {
        this.numeroQuarto = numeroQuarto;
        setTipoCama(tipoCama);
        this.aceitaAnimais = aceitaAnimais;
    }

    //Get and set
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

    public  List<Hospede> getHospedeList() {
        return hospedeList;
    }

    public  List<Date> getCheckInDoQuarto() {
        return checkInDoQuarto;
    }

    public  List<Date> getCheckOutDoQuarto() {
        return checkOutDoQuarto;
    }

    //Metodo
    public void adicionarHospAoQuarto(Hospede hospede){
        hospedeList.add(hospede);
        checkInDoQuarto.add(hospede.getDataCheckIn());
        checkOutDoQuarto.add(hospede.getGetDataCheckOut());
        System.out.println(hospedeList);
        System.out.println(checkInDoQuarto);
        System.out.println(checkOutDoQuarto);
    }

    //ToString
    @Override
    public String toString() {
        return "Quarto{" +
                "numeroQuarto=" + numeroQuarto +
                ", tipoCama=" + TipoCama.valueOf(tipoCama) +
                ", aceitaAnimais='" + aceitaAnimais + '\'' +
                ", hospedeList=" + hospedeList +
                '}';
    }
}
