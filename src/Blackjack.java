import cards.*;
import players.*;
import java.util.Scanner;


public class Blackjack {
    private static final String PLAY = "Играть";
    private static final String JOIN = "Присоедениться";
    private static final String HELP = "Помошь";
    private static final String EXIT = "Выход";
    private static final String PLAYERS = "Игроки";
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
        System.out.println("\tИграть          -для начала игры");
        System.out.println("\tПрисоедениться  -для присоеденения к игровому пулу");
        System.out.println("\tИгроки          -для перечисления игроков за столом");
        System.out.println("\tПомошь          -для получения справки о правилах игры");
        System.out.println("\tВыход           -для немедленного выхода из игры");
    }
    private static void userAskCycle() {
        scan = new Scanner(System.in);
        String humanAnswer;
        while(true) {
            System.out.print("\n:");
            humanAnswer = scan.nextLine();
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
        for(int nextPlayer = 0; nextPlayer < pool.getPoolSize(); nextPlayer++) {
            Player cycleDummy = pool.getPlayer(nextPlayer);
            if(cycleDummy instanceof Dealer) {
                System.out.println("\tКрупье ");
            }
            else if(cycleDummy instanceof AIPlayer) {
                System.out.println("\t" + cycleDummy.getName() + " " + cycleDummy.getSurname() + " ID " + cycleDummy.getUnicID() + " (Бот)");
            }
            else if(cycleDummy instanceof HumanPlayer) {
                System.out.println("\t"+ cycleDummy.getName() + " " + cycleDummy.getSurname() + " ID " + cycleDummy.getUnicID() + " (Человек)");
            }
        }
        System.out.println("\tВсего: " + pool.getPoolSize());
    }
    private static void exit() {
        System.exit(0);
    }
}