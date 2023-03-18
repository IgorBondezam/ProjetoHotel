import entities.Hospede;
import entities.Hotel;
import entities.Pessoa;
import entities.Quarto;
import entities.enums.TipoCama;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<Hospede> hospedeList = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();

        String nome;
        Date nascData;
        int opcao;
        int escolhaHotel;
        boolean quartoEscolhido;
        boolean sair = true;


        //Cria os hoteis e os quartos dentro de cada hotel
        start(hotels);

        //Cadastro da PESSOA
        System.out.println("Digite o nome da pessoa: ");
        nome = sc.nextLine();

        System.out.println("Digite a data de nascimento da pessoa(dd/mm/aaaa): ");
        nascData = sdf.parse(sc.nextLine());


        Pessoa pessoa = new Hospede(nome, nascData);
        do {
            //Seleciona o hotel
            System.out.println("Escolha um hotel para viajar.\n" +
                    "1 - Novo Horizonte   2 - Disney Resort   3 - Veios do nordeste");
            escolhaHotel = sc.nextInt() - 1;
            sc.nextLine();
            quartoEscolhido = true;
            do {

                //Opção do que se deseja fazer
                System.out.println("1 - Fazer reserva. \n" +
                        "2 - Ver quartos disponíveis.\n" +
                        "9 - Trocar hotel.\n" +
                        "-1 - Para sair do programa.");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> {
                        //FUNCAO Cadastra HOSPEDE
                        hospedeList.add(cadastrarHospede(nome, nascData, sc, hotels, escolhaHotel));
//                        System.out.println(hotels.get(escolhaHotel).criarReserva(hospedeList.get(hospedeList.size() - 1)));
                        hospedeList.forEach(System.out::println);
                    }
                    case 2 -> {
                        //Select dos quartos(para ver a cama)
                        System.out.println("Quais tipos de quartos deseja ver:\n" +
                                "1-CASAL 2-SOLTEIRO 3-BELICHE 4-TRES CAMAS 5-TODOS;");
                        int escolhaCama = sc.nextInt();
                        if (escolhaCama == 5) {
                            List<Quarto> selecaoQuartos = hotels.get(escolhaHotel).getQuartos();

                            for (Quarto quarto : selecaoQuartos) {
                                System.out.println("Número do quarto: " + quarto.getNumeroQuarto() +
                                        ", tipo da cama do quarto: " + quarto.getTipoCama() +
                                        ", é permitido animais: " + quarto.getAceitaAnimais() + "\n");
                            }

                        } else {
                            List<Quarto> selecaoQuartos = hotels.get(escolhaHotel).getQuartos().stream().filter(
                                    x -> x.getTipoCama() == TipoCama.valueOf(escolhaCama)).toList();
                            for (Quarto quarto : selecaoQuartos) {
                                System.out.println("Número do quarto: " + quarto.getNumeroQuarto() +
                                        ", tipo da cama do quarto: " + quarto.getTipoCama() +
                                        ", é permitido animais: " + quarto.getAceitaAnimais() + "\n");
                            }
                        }
                    }
                    case 9 -> {
                        quartoEscolhido = false;
                    }
                    case (-1) -> {
                        quartoEscolhido = false;
                        sair = false;
                    }
                }
            } while (quartoEscolhido);


        } while (sair);
        System.out.println("Obrigado por usar o programa!! Até a próxima.");
    }

    //Criar hospede
    private static Hospede cadastrarHospede(String nome, Date nascData, Scanner sc, List<Hotel> hotels, int escolhaHotel) throws ParseException {
        Date checkIn;
        Date checkOut;
        int numeroQuarto;
        String usarGaragem;

        do {
            System.out.println("Digite a data de entrada(dd/mm/aaaa): ");
            checkIn = sdf.parse(sc.nextLine());

            if (subDate(nascData, checkIn) < 18) {
                System.out.println("Você é muito novo para realizar essa reserva.\n");
            }
        } while (subDate(nascData, checkIn) < 18);


        do {
            System.out.println("Digite a data de saída(dd/mm/aaaa): ");
            checkOut = sdf.parse(sc.nextLine());

            if (checkOut.before(checkIn)) {
                System.out.println("O checkIn não pode ser depois do checkOut.\n");
            }
        } while (checkOut.before(checkIn));

        do {
            System.out.println("Digite o número do quarto: ");
            numeroQuarto = sc.nextInt();
            sc.nextLine();

            if (numeroQuarto > hotels.get(escolhaHotel).getQuartos().size() + 99 || numeroQuarto < 100) {
                System.out.println("O hotel não possui esse quarto cadastrado.\n");
            }
        } while (numeroQuarto > hotels.get(escolhaHotel).getQuartos().size() + 99 || numeroQuarto < 100);

        Quarto quartoEscolhido = hotels.get(escolhaHotel).getQuartos().get(numeroQuarto - 100);

        do {
            System.out.println("Irá usar a garagem(sim/nao): ");
            usarGaragem = sc.nextLine();

            if (!(usarGaragem.equals("sim") || usarGaragem.equals("nao"))) {
                System.out.println("Digite somente sim ou não.\n");
            }
        } while (!(usarGaragem.equals("sim") || usarGaragem.equals("nao")));

        Hospede hospede = new Hospede(nome, nascData,
                checkIn, checkOut, quartoEscolhido,
                usarGaragem
        );
        quartoEscolhido.adicionarHospAoQuarto(hospede);


        return hospede;

    }


    public static void start(List<Hotel> hotels) {

        Hotel hotel = new Hotel("Novo Horizonte");
        Hotel hotel2 = new Hotel("Disney Resort");
        Hotel hotel3 = new Hotel("Veios do nordeste");

        hotels.add(hotel);
        hotels.add(hotel2);
        hotels.add(hotel3);

        for (Hotel hotelSep : hotels) {
            for (int i = 0; i < 20; i++) {

                hotelSep.adicionarQuarto();
            }
        }


    }

    public static Integer subDate(Date firstLocalDate, Date secondLocalDate) {
        LocalDate localDateFirst = firstLocalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSecond = secondLocalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return (int) ChronoUnit.YEARS.between(localDateFirst, localDateSecond);
    }


}