package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hospede extends Pessoa{

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Date dataCheckIn;
    private Date dataCheckOut;
    private Quarto quarto;
    private String garagem;


    public Hospede(String nome, Date dataNasc) {
        super(nome, dataNasc);
    }


    public Hospede(Date dataCheckIn, Date dataCheckOut, Quarto quarto, String garagem) {
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.quarto = quarto;
        this.garagem = garagem;
    }

    public Hospede(String nome, Date dataNasc, Date dataCheckIn, Date dataCheckOut, Quarto quarto, String garagem) {
        super(nome, dataNasc);
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.quarto = quarto;
        this.garagem = garagem;
    }

    public Date getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(Date dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public Date getGetDataCheckOut() {
        return dataCheckOut;
    }

    public void setGetDataCheckOut(Date getDataCheckOut) {
        this.dataCheckOut = getDataCheckOut;
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
                "\n CheckIn=" + sdf.format(dataCheckIn) +
                "\n checkOut=" + sdf.format(dataCheckOut) +
                "\n quarto=" + quarto +
                "\n garagem='" + garagem + '\'' +
                "\n}";
    }
}
