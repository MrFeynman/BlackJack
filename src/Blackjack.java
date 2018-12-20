import cards.*;
import players.*;
import java.util.Scanner;


public class Blackjack {
    private static final String PLAY = "играть";
    private static final String JOIN = "присоединиться";
    private static final String HELP = "помощь";
    private static final String EXIT = "выход";
    private static final String PLAYERS = "игроки";
    private static PlayerPool pool;
    private static Scanner scan;

    public static void main(String[] args) {
        pool = new PlayerPool();
        pool.addPlayer(new Dealer());
        pool.addPlayer(new AIPlayer());
        pool.addPlayer(new AIPlayer());
        showStartMessage();
        userAskCycle();
    }
    private static void showStartMessage() {
        System.out.println("---Блекджек---");
        System.out.println("Введите ");
        System.out.println("\t"+firstCharUpperCase(PLAY)+"          -для начала игры");
        System.out.println("\t"+firstCharUpperCase(JOIN)+"  -для присоединения к игровому пулу");
        System.out.println("\t"+firstCharUpperCase(PLAYERS)+"          -для перечисления игроков за столом");
        System.out.println("\t"+firstCharUpperCase(HELP)+"          -для получения справки о правилах игры");
        System.out.println("\t"+firstCharUpperCase(EXIT)+"           -для немедленного выхода из игры");
    }
    private static void userAskCycle() {
        scan = new Scanner(System.in);
        String humanAnswer;
        while(true) {
            System.out.print("\n:");
            humanAnswer = scan.nextLine();
            humanAnswer = humanAnswer.toLowerCase(); // Конвертируем строку в нижний регистр
            if (humanAnswer.equals(PLAY)) {
                humanAnswer = "";
                play();
            } else if (humanAnswer.equals(JOIN)) {
                humanAnswer = "";
                addHuman();
            } else if (humanAnswer.equals(HELP)) {
                humanAnswer = "";
                help();
            } else if (humanAnswer.equals(EXIT)) {
                humanAnswer = "";
                exit();
            } else if (humanAnswer.equals(PLAYERS)) {
                humanAnswer = "";
//                System.out.println(pool.playerList());
                showAllPlayersInPool();
            } else {
                System.out.println("Команда не распознана. Повторите ввод");
            }
        }
    }

    private static void play() {
        Deck gameDeck = new Deck();
        gameDeck.shuffleDeck();
        System.out.println("\tКруьпе сдаёт карты из колоды:");
        giveCardsToAEveryoneOnStart(pool, gameDeck);
        showPlayersCards(pool);
        Card dealersKnownCard = new Card();
        Dealer poolDealer = null;
        for(int nextPlayer = 0; nextPlayer < pool.getPoolSize(); nextPlayer++ ) {
            Player cycleDummy = pool.getPlayer(nextPlayer);
            if(cycleDummy instanceof Dealer) {
                dealersKnownCard = cycleDummy.getHand().getHandCards()[0];
                poolDealer = (Dealer)cycleDummy;
                continue;
            }
            else {
                String playerDecision = cycleDummy.decide(dealersKnownCard);
                System.out.println(playerDecision);
            }
        }



        return;

    }
    private static void showPlayersCards(PlayerPool pool){
        if(pool == null) {
            System.out.println("pool isn't exist! Null pointer");
            return;
        }
        for(int nextPlayer = 0; nextPlayer < pool.getPoolSize(); nextPlayer++){
            Player cycleDummy = pool.getPlayer(nextPlayer);
            if(cycleDummy instanceof Dealer) {
                continue;
            }
            else {
                System.out.print("\tУ " + cycleDummy.getName() + " " + cycleDummy.getSurname() + " - ");
                for(int nextCard = 0; nextCard < cycleDummy.getHand().getHandSize(); nextCard++){
                    Card dummyCard = cycleDummy.getHand().getHandCards()[nextCard];
                    System.out.print(dummyCard.getCardsName() + " " + dummyCard.getCardsSuit()+ " ");
                }
                System.out.println();
            }

        }
        for(int nextPlayer = 0; nextPlayer < pool.getPoolSize(); nextPlayer++) {
            Player cycleDummy = pool.getPlayer(nextPlayer);
            if (!(cycleDummy instanceof Dealer)) {
                continue;
            }
            else {
                System.out.print("\tУ крупье: ");
                for(int nextCard = 0; nextCard < cycleDummy.getHand().getHandSize(); nextCard++){
                    Card dummyCard = cycleDummy.getHand().getHandCards()[nextCard];
                    System.out.print(dummyCard.getCardsName() + " " + dummyCard.getCardsSuit()+ " ");
                }
                System.out.println();
            }
        }
    }
    private static void giveCardsToAEveryoneOnStart(PlayerPool pool, Deck deck) {
        for(int nextPlayer = 0; nextPlayer < pool.getPoolSize(); nextPlayer++){
            Player cycleDummy = pool.getPlayer(nextPlayer);
            cycleDummy.takeCardsOnStart(deck);
        }
    }

    private static void addHuman() {
        scan = new Scanner(System.in);
        System.out.print("\tВведите имя: ");
        String userName = scan.nextLine();
        System.out.print("\tВведите фамилию: ");
        String userSurname = scan.nextLine();
        System.out.print("\tВведите кол-во денег: ");
        int userMoney = Integer.parseInt(scan.nextLine());
        pool.addPlayer(new HumanPlayer(userName,userSurname,userMoney));
        int humansInGame = 0;
        for(int nextPlayer = 0; nextPlayer < pool.getPoolSize(); nextPlayer++) {
            if(pool.getPlayer(nextPlayer) instanceof HumanPlayer) {
                humansInGame++;
            }
        }
        System.out.println("В игре " + humansInGame + " управляемых человеком игроков.");
        return;

    }
    private static void help() {
        System.out.println("Заглушка для пункта помощи");
        return;
    }
    private static void showAllPlayersInPool() {
        System.out.println("В игре: ");
        System.out.println(pool.personList());
        System.out.println("\tВсего: " + pool.getPoolSize());
    }

    // Превращает первую строчную букву в прописную
    private static String firstCharUpperCase(String word) {
        String upperCaseWord = new String(word);

        char firstChar = word.charAt(0); // первый символ

        char upperCaseFirstChar = Character.toUpperCase(firstChar); // в верхний регистр

        // Переводим в строку для replaceFirst -\_oO_/-
        String firstCharString = Character.toString(firstChar);
        String upperCharString = Character.toString(upperCaseFirstChar);
        upperCaseWord = upperCaseWord.replaceFirst(firstCharString, upperCharString);

        return upperCaseWord;
    }
    private static void exit() {
        System.exit(0);
    }
}
