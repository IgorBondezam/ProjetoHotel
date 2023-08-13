import entities.Hospede;
import entities.Hotel;
import entities.Quarto;
import entities.Util.Utils;
import entities.enums.TipoCama;
import jdk.jshell.execution.Util;


import java.text.ParseException;
import java.util.*;


public class Main {


    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<Hotel> hotels = new ArrayList<>();

        String nome;
        int opcao;
        int escolhaHotel;
        boolean quartoEscolhido, selecionarFuncionario = true;
        boolean sair = true;


        //Cria os hoteis e os quartos dentro de cada hotel
        start(hotels);


        do {


            do {
                System.out.println("\n\n");
                escolhaHotel = -1;
                //Seleciona o hotel
                System.out.println("Escolha um hotel para viajar.\n" +
                        "1 - Novo Horizonte   2 - Disney Resort   3 - Veios do nordeste");
                //validação se o que foi escrito foi um número
                try {
                    escolhaHotel = sc.nextInt() - 1;
                    sc.nextLine();
                    //validação se o que foi escrito foi um número foi um dos números
                    if (!(escolhaHotel == 0 || escolhaHotel == 1 || escolhaHotel == 2)) {
                        System.out.println("\n\n");
                        System.out.println("Digite um dos valores válidos");
                        Utils.tempoEspera(2);
                        Utils.limparTela();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n\n");
                    System.out.println("Digite um dos números possíveis");
                    sc.nextLine();
                    Utils.tempoEspera(2);
                    Utils.limparTela();
                }


            } while (!(escolhaHotel == 0 || escolhaHotel == 1 || escolhaHotel == 2));


            do {
                System.out.println("\n\n");
                //Funcionário que realizará o cadastro do hospede
                System.out.println("Digite o nome do funcionário: ");
                nome = sc.nextLine();


                quartoEscolhido = true;
                do {
                    Utils.limparTela();
                    //Opção do que se deseja fazer
                    System.out.println("---" + hotels.get(escolhaHotel).getNome() + "---");
                    System.out.println("- Funcionario: " + nome);
                    System.out.println("\n");
                    System.out.println("1 - Fazer reserva. \n" +
                            "2 - Ver quartos disponíveis.\n" +
                            "3 - Ver hóspedes cadastrados.\n" +
                            "4 - Fazer checkIn do hóspede.\n" +
                            "5 - Fazer checkOut do hóspede.\n" +
                            "8 - Trocar de conta.\n" +
                            "9 - Trocar hotel.\n" +
                            "-1 - Para sair do programa.");
                    try {

                        opcao = sc.nextInt();
                        sc.nextLine();

                        switch (opcao) {
                            case 1 -> {

                                //FUNCAO Cadastra HOSPEDE
                                Utils.limparTela();
                                System.out.println("--Cadastrar Hospede--");
                                String hospedeCriado = hotels.get(escolhaHotel).criarReserva(sc, hotels, escolhaHotel);

                                System.out.println("\n");
                                System.out.println(hospedeCriado);
                                Utils.tempoEspera(2);
                                System.out.println("\n");


                            }
                            case 2 -> {
                                //Select dos quartos(para ver a cama)
                                Utils.limparTela();
                                int escolhaCama = 0;

                                do {
                                    try {

                                        System.out.println("Quais tipos de quartos deseja ver:\n" +
                                                "1-CASAL 2-SOLTEIRO 3-BELICHE 4-TRES CAMAS 5-TODOS;");
                                        escolhaCama = sc.nextInt();
                                    } catch (IllegalArgumentException | InputMismatchException e) {
                                        sc.nextLine();
                                        System.out.println("Digite um número válido.");
                                        escolhaCama = 0;
                                        Utils.tempoEspera(2);
                                        Utils.limparTela();
                                    }
                                } while (escolhaCama > 5 || escolhaCama < 1);

                                if (escolhaCama == 5) {
                                    List<Quarto> selecaoQuartos = hotels.get(escolhaHotel).getQuartos();

                                    for (Quarto quarto : selecaoQuartos) {
                                        if (!(quarto.statusCheckIn)) {
                                            System.out.println("Número do quarto: " + quarto.getNumeroQuarto() +
                                                    ", tipo da cama do quarto: " + quarto.getTipoCama() +
                                                    ", é permitido animais: " + quarto.getAceitaAnimais() + "\n");
                                        }
                                    }

                                } else {
                                    int finalEscolhaCama = escolhaCama;
                                    List<Quarto> selecaoQuartos = hotels.get(escolhaHotel).getQuartos().stream()
                                            .filter(x -> x.getTipoCama() == TipoCama.valueOf(finalEscolhaCama)).toList();
                                    for (Quarto quarto : selecaoQuartos) {
                                        if (!(quarto.statusCheckIn)) {
                                            System.out.println("Número do quarto: " + quarto.getNumeroQuarto() +
                                                    ", tipo da cama do quarto: " + quarto.getTipoCama() +
                                                    ", é permitido animais: " + quarto.getAceitaAnimais() + "\n");
                                        }
                                    }
                                }

                                sc.nextLine();
                                Utils.aperteEnter();

                            }
                            case 3 -> {

                                Utils.limparTela();
                                System.out.println("Lista de Hóspedes do hotel " + hotels.get(escolhaHotel).getNome() + ":\n");

                                List<List<Hospede>> hospedePerQuarto = hotels.get(escolhaHotel)
                                        .getQuartos().stream().map(Quarto::getHospedeList).toList();

                                for (List<Hospede> hospedeIteravel : hospedePerQuarto) {
                                    for (Hospede hospedeIndividual : hospedeIteravel) {
                                        System.out.println("Nome: " + hospedeIndividual.getNome() +
                                                " - data de nascimento : " + Utils.formatarData(hospedeIndividual.getDataNasc()) +
                                                "\n- número do quarto: " + hospedeIndividual.getQuarto().getNumeroQuarto() +
                                                " - tipo da cama do quarto: " + hospedeIndividual.getQuarto().getTipoCama() +
                                                " - quarto aceita animais: " + hospedeIndividual.getQuarto().getAceitaAnimais() +
                                                "\n- entrada no quarto: " + Utils.formatarData(hospedeIndividual.getDataCheckIn()) +
                                                " - saída do quarto: " + Utils.formatarData(hospedeIndividual.getGetDataCheckOut()) +
                                                " - uso da garagem: " + hospedeIndividual.getGaragem() +
                                                "\n\n");
                                    }
                                }
                                Utils.aperteEnter();
                            }

                            case 4 -> {
                                boolean condicao;
                                do {
                                    try {
                                        condicao = false;
                                        Utils.limparTela();
                                        System.out.println("--Realização de CheckIn--");
                                        System.out.println("Qual quarto deseja fazer checkIn:");
                                        int numeroQuartoCheckIn = sc.nextInt();

                                        sc.nextLine();

                                        System.out.println(hotels.get(escolhaHotel)
                                                .checkIn(numeroQuartoCheckIn - 100, new Date()));
                                        sc.nextLine();
                                        Utils.aperteEnter();
                                    } catch (InputMismatchException | IndexOutOfBoundsException e) {
                                        System.out.println("Digite um valor válido");
                                        condicao = true;

                                        Utils.tempoEspera(1);
                                        System.out.println("\n\n");
                                    }
                                } while (condicao);
                            }

                            case 5 -> {
                                boolean condicao;
                                do {
                                    try {
                                        condicao = false;
                                        Utils.limparTela();
                                        System.out.println("--Realização de CheckOut--");
                                        System.out.println("Qual quarto deseja fazer checkOut:");
                                        int numeroQuartoCheckOut = sc.nextInt();
                                        sc.nextLine();
                                        System.out.println(hotels.get(escolhaHotel)
                                                .checkOut(numeroQuartoCheckOut - 100, new Date()));
                                        sc.nextLine();
                                        Utils.aperteEnter();
                                    } catch (InputMismatchException | IndexOutOfBoundsException e) {
                                        System.out.println("Digite um valor válido");
                                        condicao = true;

                                        Utils.tempoEspera(1);
                                        System.out.println("\n\n");
                                    }
                                } while (condicao);
                            }

                            case 8 -> {
                                quartoEscolhido = false;
                                selecionarFuncionario = true;
                                Utils.limparTela();
                            }

                            case 9 -> {
                                quartoEscolhido = false;
                                selecionarFuncionario = false;
                                Utils.limparTela();

                            }

                            case (-1) -> {
                                quartoEscolhido = false;
                                selecionarFuncionario = false;
                                sair = false;
                                Utils.limparTela();
                            }

                            default -> {
                                System.out.println("\n\n");
                                System.out.println("Essa opção não exite.\nDigite uma opção válida!");
                                Utils.tempoEspera(2);
                                Utils.limparTela();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\n");
                        sc.nextLine();
                        System.out.println("Digite o valor válido para a operação.");
                        Utils.tempoEspera(2);

                    }
                } while (quartoEscolhido);
            } while (selecionarFuncionario);
        } while (sair);

        Utils.limparTela();
        System.out.println("Obrigado por usar o programa!! Até a próxima.");
    }


    //Adicionar os hoteis na lista e os quartos em cada hotel
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

}