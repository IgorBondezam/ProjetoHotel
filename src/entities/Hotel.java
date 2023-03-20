package entities;

import entities.Util.Utils;
import entities.enums.TipoCama;

import java.text.ParseException;
import java.util.*;

public class Hotel {

    private static final Random geradorNum = new Random();


    private String nome;
    private final List<Quarto> quartos = new ArrayList<>();
    private Integer contador;


    public Hotel() {

    }

    public Hotel(String nome) {
        this.nome = nome;
        contador = 100;
    }

    public String getNome() {
        return nome;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }


    public void adicionarQuarto() {

        String animais;
        if(geradorNum.nextInt(2)==1){
            animais = "sim";
        }else {
            animais = "nao";
        }

        Quarto umQuarto = new Quarto(contador, TipoCama.valueOf(geradorNum.nextInt(4) + 1), animais);
        contador++;
        quartos.add(umQuarto);
    }

    //Relaciona o hospede com o quarto
    public String criarReserva(Scanner sc, List<Hotel> hotels, int escolhaHotel) throws ParseException {


        Date checkIn;
        Date checkOut;
        int numeroQuarto;
        String usarGaragem;
        String nome;
        Date nascData = null;

        System.out.println("Digite o nome da pessoa: ");
        nome = sc.nextLine();

        boolean condicao = false;
        do {
            condicao = false;
            try {
                System.out.println("Digite a data de nascimento da pessoa(dd/mm/aaaa): ");
                nascData = Utils.stringToData(sc.nextLine());

            } catch (InputMismatchException | ParseException e) {
                System.out.println("Digite um valor válido");
                checkIn = Utils.stringToData("01/01/0001");
                condicao = true;
                Utils.tempoEspera(2);
                System.out.println("\n\n");
            }
        } while ((condicao));
        Pessoa pessoa = new Hospede(nome, nascData);

        //NUMERO DO QUARTO
        do {
            try {
                System.out.println("Digite o número do quarto: ");
                numeroQuarto = sc.nextInt();
                sc.nextLine();

                if (numeroQuarto > hotels.get(escolhaHotel).getQuartos().size() + 99 || numeroQuarto < 100) {
                    System.out.println("O hotel não possui esse quarto cadastrado.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor válido");
                numeroQuarto = 0;
                sc.nextLine();
                Utils.tempoEspera(2);
                System.out.println("\n\n");
            }
        } while (numeroQuarto > hotels.get(escolhaHotel).getQuartos().size() + 99 || numeroQuarto < 100);


        //Quarto selecionado dentro do hotel
        Quarto quartoEscolhido = hotels.get(escolhaHotel).getQuartos().get(numeroQuarto - 100);

        do {
            //CHECKIN
            do {
                try {

                    System.out.println("Digite a data de entrada(dd/mm/aaaa): ");
                    checkIn = Utils.stringToData((sc.nextLine()));

                    if (Utils.subDate(nascData, checkIn) < 18) {
                        System.out.println("Você é muito novo para realizar essa reserva.\n");
                    }
                    if (!(Utils.locadoEntreOsDias(quartoEscolhido, checkIn, null))) {
                        System.out.println("Esse quarto já está reservado para este dia.\n");
                    }
                } catch (InputMismatchException | ParseException e) {
                    System.out.println("Digite um valor válido");
                    checkIn = Utils.stringToData("01/01/0001");

                    Utils.tempoEspera(2);
                    System.out.println("\n\n");
                }
            } while (Utils.subDate(nascData, checkIn) < 18
                    || !(Utils.locadoEntreOsDias(quartoEscolhido, checkIn, null)));

            //CHECKOUT
            do {
                try {
                    System.out.println("Digite a data de saída(dd/mm/aaaa): ");
                    checkOut = Utils.stringToData(sc.nextLine());

                    if (checkOut.before(checkIn)) {
                        System.out.println("O checkIn não pode ser depois do checkOut.\n");
                    }
                    if (!(Utils.locadoEntreOsDias(quartoEscolhido, null, checkOut))) {
                        System.out.println("Esse quarto já está reservado para este dia.\n");
                    }

                } catch (InputMismatchException | ParseException e) {
                    System.out.println("Digite um valor válido");
                    checkOut = Utils.stringToData("00/01/0001");

                    Utils.tempoEspera(2);

                    System.out.println("\n\n");
                }
            } while (checkOut.before(checkIn) || !(Utils.locadoEntreOsDias(quartoEscolhido, null, checkOut)));

            if (!(Utils.locadoEntreOsDias(quartoEscolhido, checkIn, checkOut))) {
                System.out.println("Tem uma reserva já cadastrada entre os dias registrados.\n");
            }
        } while (!(Utils.locadoEntreOsDias(quartoEscolhido, checkIn, checkOut)));

        //Usuario deseja GARAGEM
        do {
            try {


                System.out.println("Irá usar a garagem(sim/nao): ");
                usarGaragem = sc.nextLine();

                if (!(usarGaragem.equals("sim") || usarGaragem.equals("nao"))) {
                    System.out.println("Digite somente sim ou não.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor válido");
                usarGaragem = null;
                sc.nextLine();
                Utils.tempoEspera(2);
                System.out.println("\n\n");
            }
        } while (!(usarGaragem.equals("sim") || usarGaragem.equals("nao")));

        //Cria o hospede
        Hospede hospede = new Hospede(nome, nascData,
                checkIn, checkOut, quartoEscolhido,
                usarGaragem
        );
        quartoEscolhido.adicionarHospAoQuarto(hospede);


        return "Foi criado a reserva de " + hospede.getNome() + " para o dia " + Utils.formatarData(hospede.getDataCheckIn()) + ".";
    }


    public String checkIn(Integer numeroQuartoCheckIn, Date data) throws ParseException {


        int identificador = -1;

        Date dataConver = Utils.stringToData(Utils.formatarData(data));

        List<Date> datasDoQuarto = quartos.get(numeroQuartoCheckIn).getCheckInDoQuarto();

        if (datasDoQuarto.isEmpty()) {
            return "Não há nenhum checkIn para esse quarto hoje.";
        }

        for (int i = 0; i < datasDoQuarto.size(); i++) {
            if (datasDoQuarto.get(i).compareTo(dataConver) == 0 && !(quartos.get(numeroQuartoCheckIn).statusCheckIn)) {
                identificador = i;
            }
        }

        if (identificador >= 0) {
            quartos.get(numeroQuartoCheckIn).statusCheckIn = true;
            return "Check-in de " + quartos.get(numeroQuartoCheckIn).getHospedeList().get(identificador).getNome() +
                    " em " + Utils.formatarData(dataConver) + " realizado com sucesso no quarto " + (numeroQuartoCheckIn + 100) + ".";
        }

        return "Não há nenhum checkIn para esse quarto na data de hoje.";
    }

    public String checkOut(Integer numeroQuartoCheckOut, Date data) throws ParseException {

        int identificador = -1;

        Date dataConver = Utils.stringToData(Utils.formatarData(data));

        List<Date> datasDoQuarto = quartos.get(numeroQuartoCheckOut).getCheckOutDoQuarto();

        if (datasDoQuarto.isEmpty()) {
            return "Não há nenhum checkIn para esse quarto hoje.";
        }

        for (int i = 0; i < datasDoQuarto.size(); i++) {
            if (datasDoQuarto.get(i).compareTo(dataConver) == 0) {
                identificador = i;
            }
        }

        if (identificador >= 0 && quartos.get(numeroQuartoCheckOut).statusCheckIn) {
            quartos.get(numeroQuartoCheckOut).statusCheckIn = false;
            String msg = "Check-Out de " + quartos.get(numeroQuartoCheckOut).getHospedeList().get(identificador).getNome() +
                    " em " + Utils.formatarData(dataConver) + " realizado com sucesso no quarto " + (numeroQuartoCheckOut + 100) + ".";
            quartos.get(numeroQuartoCheckOut).getCheckInDoQuarto().remove(identificador);
            quartos.get(numeroQuartoCheckOut).getCheckOutDoQuarto().remove(identificador);
            quartos.get(numeroQuartoCheckOut).getHospedeList().remove(identificador);
            return msg;
        }else if(identificador >= 0 && !(quartos.get(numeroQuartoCheckOut).statusCheckIn)){
            return "É preciso realizar o checkIn primeiro.";
        }

        return "Não há nenhum checkIn para esse quarto na data de hoje.";
    }


}

