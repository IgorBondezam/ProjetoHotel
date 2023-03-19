import entities.Hospede;
import entities.Hotel;
import entities.Pessoa;
import entities.Quarto;
import entities.Util.Utils;
import entities.enums.TipoCama;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;


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
        boolean quartoEscolhido, selecionarPessoa = true;
        boolean sair = true;


        //Cria os hoteis e os quartos dentro de cada hotel
        start(hotels);


        //Cadastro da PESSOA
        do {
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
                    limparTela();
                    //Opção do que se deseja fazer
                    System.out.println("---" + hotels.get(escolhaHotel).getNome() + "---");
                    System.out.println("- Pessoa: " + nome);
                    System.out.println("- idade: " + subDate(nascData, new Date()) + "\n");
                    System.out.println("1 - Fazer reserva. \n" +
                            "2 - Ver quartos disponíveis.\n" +
                            "3 - Ver hóspedes cadastrados.\n" +
                            "4 - Fazer checkIn do hóspede.\n" +
                            "5 - Fazer checkOut do hóspede.\n" +
                            "8 - Trocar de conta.\n" +
                            "9 - Trocar hotel.\n" +
                            "-1 - Para sair do programa.");
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            //FUNCAO Cadastra HOSPEDE
                            limparTela();
                            Hospede hospedeCriado = cadastrarHospede(nome, nascData, sc, hotels, escolhaHotel);
                            hospedeList.add(hospedeCriado);
                            System.out.println("\n");
                            hotels.get(escolhaHotel).criarReserva(hospedeCriado);

                        }
                        case 2 -> {
                            //Select dos quartos(para ver a cama)
                            limparTela();
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
                        case 3 -> {

                            limparTela();
                            System.out.println("Lista de Hóspedes do hotel " + hotels.get(escolhaHotel).getNome() + ":\n");

                            List<List<Hospede>> hospedePerQuarto = hotels.get(escolhaHotel)
                                    .getQuartos().stream().map(Quarto::getHospedeList).toList();

                            for (List<Hospede> hospedeIteravel: hospedePerQuarto) {
                                for (Hospede hospedeIndividual: hospedeIteravel) {
                                    System.out.println("Nome: " + hospedeIndividual.getNome() +
                                            " - data de nascimento: " + Utils.formatarData(hospedeIndividual.getDataNasc()) +
                                            "\n- número do quarto:" + hospedeIndividual.getQuarto().getNumeroQuarto() +
                                            " - tipo da cama do quarto:" + hospedeIndividual.getQuarto().getTipoCama() +
                                            " - quarto aceita animais: " + hospedeIndividual.getQuarto().getAceitaAnimais() +
                                            "\n- entrada no quarto: " + Utils.formatarData(hospedeIndividual.getDataCheckIn()) +
                                            " - saída do quarto: " + Utils.formatarData(hospedeIndividual.getGetDataCheckOut()) +
                                            " - uso da garagem: " + hospedeIndividual.getGaragem() +
                                            "\n\n");
                                }
                            }
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }

//                        case 4 -> {
//
//                        }
//                        case 5 -> {
//
//                        }

                        case 8 -> {
                            quartoEscolhido = false;
                            selecionarPessoa = false;
                            limparTela();
                        }

                        case 9 -> {
                            quartoEscolhido = false;
                            limparTela();
                        }

                        case (-1) -> {
                            quartoEscolhido = false;
                            selecionarPessoa = false;
                            sair = false;
                            limparTela();
                        }
                    }
                } while (quartoEscolhido);
            } while (selecionarPessoa);
        } while (sair);

        limparTela();
        System.out.println("Obrigado por usar o programa!! Até a próxima.");
    }

    public static void limparTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println("\n");

        }
    }


    //Criar hospede
    private static Hospede cadastrarHospede(String nome, Date nascData, Scanner sc, List<Hotel> hotels, int escolhaHotel) throws ParseException {
        Date checkIn;
        Date checkOut;
        int numeroQuarto;
        String usarGaragem;

        //NUMERO DO QUARTO
        do {
            System.out.println("Digite o número do quarto: ");
            numeroQuarto = sc.nextInt();
            sc.nextLine();

            if (numeroQuarto > hotels.get(escolhaHotel).getQuartos().size() + 99 || numeroQuarto < 100) {
                System.out.println("O hotel não possui esse quarto cadastrado.\n");
            }
        } while (numeroQuarto > hotels.get(escolhaHotel).getQuartos().size() + 99 || numeroQuarto < 100);

        //Quarto selecionado dentro do hotel
        Quarto quartoEscolhido = hotels.get(escolhaHotel).getQuartos().get(numeroQuarto - 100);

        do {
            //CHECKIN
            do {
                System.out.println("Digite a data de entrada(dd/mm/aaaa): ");
                checkIn = sdf.parse(sc.nextLine());

                if (subDate(nascData, checkIn) < 18) {
                    System.out.println("Você é muito novo para realizar essa reserva.\n");
                }
                if (!(locadoEntreOsDias(quartoEscolhido, checkIn, null))) {
                    System.out.println("Esse quarto já está reservado para este dia.\n");
                }
            } while (subDate(nascData, checkIn) < 18 || !(locadoEntreOsDias(quartoEscolhido, checkIn, null)));

            //CHECKOUT
            do {
                System.out.println("Digite a data de saída(dd/mm/aaaa): ");
                checkOut = sdf.parse(sc.nextLine());

                if (checkOut.before(checkIn)) {
                    System.out.println("O checkIn não pode ser depois do checkOut.\n");
                }
                if (!(locadoEntreOsDias(quartoEscolhido, null, checkOut))) {
                    System.out.println("Esse quarto já está reservado para este dia.\n");
                }
            } while (checkOut.before(checkIn) || !(locadoEntreOsDias(quartoEscolhido, null, checkOut)));

            if (!(locadoEntreOsDias(quartoEscolhido, checkIn, checkOut))) {
                System.out.println("Tem uma reserva já cadastrada entre os dias registrados.\n");
            }
        } while (!(locadoEntreOsDias(quartoEscolhido, checkIn, checkOut)));

        //Usuario deseja GARAGEM
        do {
            System.out.println("Irá usar a garagem(sim/nao): ");
            usarGaragem = sc.nextLine();

            if (!(usarGaragem.equals("sim") || usarGaragem.equals("nao"))) {
                System.out.println("Digite somente sim ou não.\n");
            }
        } while (!(usarGaragem.equals("sim") || usarGaragem.equals("nao")));

        //Cria o hospede
        Hospede hospede = new Hospede(nome, nascData,
                checkIn, checkOut, quartoEscolhido,
                usarGaragem
        );
        quartoEscolhido.adicionarHospAoQuarto(hospede);


        return hospede;

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

    //Usado para subtrair os anos e devolver o ano exato(contados os dias e os meses)
    public static Integer subDate(Date firstLocalDate, Date secondLocalDate) {
        //transforma os DATES em LocalDate
        LocalDate localDateFirst = firstLocalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSecond = secondLocalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //Retorna o ano baseado na subtração da data inteira
        return (int) ChronoUnit.YEARS.between(localDateFirst, localDateSecond);
    }

    //Ve se os dias estão ou não entre outros dois dias
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


}