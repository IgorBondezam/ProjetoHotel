import entities.Hospede;
import entities.Quarto;


import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        List<Hospede> hospedeList = new ArrayList<>();

        while (true) {

            String nome;
            Date nascData;
            Date checkIn;
            String usarGaragem;


            System.out.println("Digite o nome do Hospede: ");
            nome = sc.nextLine();

            System.out.println("Digite a data de nascimento do hospede(dd/mm/aaaa): ");
            nascData = sdf.parse(sc.nextLine());

            System.out.println("Digite a data de checkIn(dd/mm/aaaa): ");
            checkIn = sdf.parse(sc.nextLine());

            System.out.println("Irá usar a garagem(sim/nao): ");
            usarGaragem = sc.nextLine();


            Hospede hospede = new Hospede(nome, nascData, checkIn, new Quarto(), usarGaragem);

            hospedeList.add(hospede);

            hospedeList.forEach(System.out::println);
        }

    }
}