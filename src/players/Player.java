package players;
import cards.*;

public abstract class Player {
    private static final String[] AI_NAMES = {"Вася","Петя","Леша"};
    private static final String[] AI_SURNAMES = {"Кипятков","Пузырьков","Волосков"};
    private static int unicIDCounter = 0;

    private String name;
    private String surname;
    private int money;
    private Hand hand;
    private int unicID;


    public Player(String name, String surname, int money) {
        final String UNKNOWN_PLAYER = "Неизвестный игрок";
        if (name == null || name.isEmpty()) {
            this.name = UNKNOWN_PLAYER;
            this.surname = new String();
        } else {
            this.name = name;
        }
        if ((surname == null || surname.isEmpty()) && !name.equals(UNKNOWN_PLAYER)) {
            this.surname = new String();
        } else if (!surname.isEmpty() && !name.equals(UNKNOWN_PLAYER)) {
            this.surname = surname;
        }
        if (money <= 0) {
            System.out.println("Invalid money ammount. Random ammount will be given.");
            int randomMoneyAmmount = 0;
            while (randomMoneyAmmount <= 0) {
                randomMoneyAmmount = (int) (Math.random() * 1000);
            }
            this.money = randomMoneyAmmount;
        }
        else {
            this.money = money;
        }
        hand = null;
        unicID = unicIDCounter++;
    }

    public Player(String ... varArgs) {
        if(varArgs != null && varArgs.length > 0 && varArgs[0].equals("AI Player")) {
            name = AI_NAMES[(int)(Math.random() * AI_NAMES.length)];
            surname = AI_SURNAMES[(int)(Math.random() * AI_SURNAMES.length)];
            money = (int)(Math.random() * 900) + 100;
            hand = null;
            unicID = unicIDCounter++;
        }
        else if(varArgs != null && varArgs.length > 0 && varArgs[0].equals("Dealer")) {
            name = "Dealer";
            surname = new String();
            money = -1;
            hand = null;
            unicID = -1;

        }
        else {
            name = "NoName";
            surname = "NoSurname";
            money = -1;
            hand = null;
            unicID = -1;
        }
    }

    public int getMoney() {
        return money;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return  surname;
    }
    public final int getUnicID() {
        return unicID;
    }
    public void takeCardsOnStart(Deck deck) {
        hand = new Hand(deck.giveCardsOnStart());
    }
    public Hand getHand() {
        return hand;
    }
    public abstract String decide(Card dealersKnownCard);
}
