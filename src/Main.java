import entities.Hospede;
import entities.Hotel;
import entities.Pessoa;
import entities.Quarto;
import entities.enums.TipoCama;


import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<Hospede> hospedeList = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        start(hotels);


        String nome;
        Date nascData;

        int opcao;







            System.out.println("Digite o nome da pessoa: ");
            nome = sc.nextLine();

            System.out.println("Digite a data de nascimento da pessoa(dd/mm/aaaa): ");
            nascData = sdf.parse(sc.nextLine());

            Pessoa pessoa = new Hospede(nome, nascData);
        while (true) {

            System.out.println("1 - Fazer reserva. \n" +
                               "2 - Ver quartos disponíveis:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    hospedeList.add(cadastrarHospede(nome, nascData, sc, hotels));

                    break;

                case 2:
                    System.out.println("Quais tipos de quartos deseja ver:\n" +
                            "1-CASAL 2-SOLTEIRO 3-BELICHE 4-TRES CAMAS 5-TODOS;");
                    int escolhaCama = sc.nextInt();

                    if(escolhaCama==5){
                        List<Quarto> selecaoQuartos = hotels.get(0).getQuartos();

                        for (Quarto quarto: selecaoQuartos) {
                            System.out.println("Número do quarto: " + quarto.getNumeroQuarto() +
                                    ", tipo da cama do quarto: " + quarto.getTipoCama() +
                                    ", é permitido animais: " + quarto.getAceitaAnimais() + "\n");
                        }

                    }else {
                        List<Quarto> selecaoQuartos = hotels.get(0).getQuartos().stream().filter(
                                x -> x.getTipoCama() == TipoCama.valueOf(escolhaCama)).collect(Collectors.toList());
                        for (Quarto quarto: selecaoQuartos) {
                            System.out.println("Número do quarto: " + quarto.getNumeroQuarto() +
                                    ", tipo da cama do quarto: " + quarto.getTipoCama() +
                                    ", é permitido animais: " + quarto.getAceitaAnimais() + "\n");
                        }
                    }
                    break;

            }






//            hospedeList.forEach(System.out::println);
//
//
//            System.out.println(hotels.get(0).criarReserva(hospedeList.get(0)));
        }

    }

    //Criar hospede
    private static Hospede cadastrarHospede(String nome, Date nascData, Scanner sc, List<Hotel> hotels) throws ParseException {
        Date checkIn;
        Date checkOut;
        int numeroQuarto;
        String usarGaragem;

        System.out.println("Digite a data de entrada(dd/mm/aaaa): ");
        checkIn = sdf.parse(sc.nextLine());

        System.out.println("Digite a data de saída(dd/mm/aaaa): ");
        checkOut = sdf.parse(sc.nextLine());

        System.out.println("Digite o número do quarto: ");
        numeroQuarto = sc.nextInt();
        sc.nextLine();

        Quarto quartoEscolhido = hotels.get(0).getQuartos().get(numeroQuarto-100);

        System.out.println("Irá usar a garagem(sim/nao): ");
        usarGaragem = sc.nextLine();

        Hospede hospede = new Hospede(nome, nascData,
                checkIn, checkOut, quartoEscolhido,
                usarGaragem
        );
        quartoEscolhido.adicionarHospAoQuarto(hospede);


        return hospede;

    }



    public static void start(List<Hotel> hotels){

        Hotel hotel = new Hotel("Novo Horizonte");

        hotels.add(hotel);

        for (int i = 0; i < 20; i++) {

        hotel.adicionarQuarto();
        }

    }
}