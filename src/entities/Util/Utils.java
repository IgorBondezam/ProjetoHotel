package entities.Util;

import entities.Quarto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;

public abstract class Utils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Scanner sc = new Scanner(System.in);

    public static String formatarData(Date data){
        return sdf.format(data);
    }

    public static Date stringToData(String data) throws ParseException {
        return sdf.parse(data);
    }

    public static Integer subDate(Date firstLocalDate, Date secondLocalDate) {
        //transforma os DATES em LocalDate
        LocalDate localDateFirst = firstLocalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSecond = secondLocalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //Retorna o ano baseado na subtração da data inteira
        return (int) ChronoUnit.YEARS.between(localDateFirst, localDateSecond);
    }


    public static Boolean locadoEntreOsDias(Quarto quarto, Date dataEscolhidaCheckIn, Date dataEscolhidaCheckOut) {
        //Ve se o primeiro está dentro dos dois
        if (dataEscolhidaCheckOut == null) {
            for (int i = 0; i <= quarto.getCheckInDoQuarto().size() - 1; i++) {
                if (dataEscolhidaCheckIn.after(quarto.getCheckInDoQuarto().get(i)) &&
                        dataEscolhidaCheckIn.before(quarto.getCheckOutDoQuarto().get(i))) {
                    return false;
                }
            }
            //vê se o segundo está dentro dos dois
        } else if (dataEscolhidaCheckIn == null) {
            for (int i = 0; i <= quarto.getCheckInDoQuarto().size() - 1; i++) {
                if (dataEscolhidaCheckOut.after(quarto.getCheckInDoQuarto().get(i)) &&
                        dataEscolhidaCheckOut.before(quarto.getCheckOutDoQuarto().get(i))) {
                    return false;
                }
            }
            //ve se o intervalo de dias q foi cadastrado primeiro está dentro do outro intervalo
        } else {
            for (int i = 0; i <= quarto.getCheckInDoQuarto().size() - 1; i++) {
                if (quarto.getCheckInDoQuarto().get(i).after(dataEscolhidaCheckIn) &&
                        quarto.getCheckOutDoQuarto().get(i).before(dataEscolhidaCheckOut)) {
                    return false;
                }
            }
        }
        return true;

    }

    public static void limparTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println("\n");

        }
    }

    public static void tempoEspera(int seg){
        seg = seg*1000;
        try {
            Thread.sleep(seg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void aperteEnter(){
        System.out.println("\n\nPrecione enter para voltar...\n");
        sc.nextLine();

        limparTela();
    }
}
