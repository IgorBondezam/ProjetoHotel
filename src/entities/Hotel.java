package entities;

import entities.Util.Utils;
import entities.enums.TipoCama;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hotel {

    private static final Random geradorNum = new Random();


    private String nome;
    private final List<Quarto> quartos = new ArrayList<>();
    Integer contador;


    public Hotel(){

    }

    public Hotel(String nome) {
        this.nome = nome;
        this.contador = 100;
    }

    public String getNome() {
        return nome;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }


    public void adicionarQuarto(){

        Quarto umQuarto = new Quarto(contador, TipoCama.valueOf(geradorNum.nextInt(4)+1), "sim");
        contador++;
        quartos.add(umQuarto);
    }

    //Relaciona o hospede com o quarto
    public String criarReserva(Hospede hospede){

        return "Criar reserva de " + hospede.getNome() + " para o dia " + Utils.formatarData(hospede.getDataCheckIn());
    }

    public String checkIn(Hospede hospede){

        return "";
    }

    public String checkOut(Hospede hospede){
        return "";
    }



}

