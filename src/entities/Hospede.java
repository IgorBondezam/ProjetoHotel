package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hospede extends Pessoa{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Date dataReserva;
    private Quarto quarto;
    private String garagem;


    public Hospede(String nome, Date dataNasc) {
        super(nome, dataNasc);
    }


    public Hospede(String nome, Date dataNasc, Date dataReserva, Quarto quarto, String garagem) {
        super(nome, dataNasc);
        this.dataReserva = dataReserva;
        this.quarto = quarto;
        this.garagem = garagem;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getGaragem() {
        return garagem;
    }

    public void setGaragem(String garagem) {
        this.garagem = garagem;
    }

    @Override
    public String toString() {
        return "Hospede{\n"+ " Nome=" + getNome() +
                "\n dataNascimento=" + sdf.format(getDataNasc()) +
                "\n dataReserva=" + sdf.format(dataReserva) +
                "\n quarto=" + quarto +
                "\n garagem='" + garagem + '\'' +
                "\n}";
    }
}
