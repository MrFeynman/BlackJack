package cards;

public class Card {
    private static final String[] CARD_NAMES = {"Двойка","Тройка","Четверка","Пятерка",
            "Шестерка","Семерка","Восьмерка","Девятка","Десятка","Валет","Дама","Король","Туз"};
    private static final String[] CARD_SUITS = {"Бубей","Червей","Крестей","Пик"};
    private static int unicIDCounter= 0;

    private String name;
    private String suit;
    private int value;
    private int unicID;
    private boolean imAce;

    public Card() {
        name = "Скрытая";
        suit = "карта";
        value = -1;
        imAce = false;
        unicID = -1;
    }

    public Card(int name, int suit) {
        this.name = CARD_NAMES[name];
        this.suit = CARD_SUITS[suit];
        unicID = unicIDCounter++;
        if(name < 8) {
            value = name + 2;
            imAce = false;
        }
        else if(name >= 8 && name < 12) {
            value = 10;
            imAce = false;
        }
        else {
            value = 1;
            imAce = true;
        }
    }

    public String getCardsName() {
        String infoToSend = new String(name);
        return infoToSend;
    }
    public String getCardsSuit() {
        String infoToSend = new String(suit);
        return infoToSend;
    }
    public int getCardsValue() {
        return value;
    }
    public int getCardsUnicID() {
        return unicID;
    }
    public boolean isCardAce() {
        return imAce;
    }
    public void addTenToValue() {
        if(!imAce) {
            System.out.println("Can't incr value by 10. Im NOT an ace!");
            return;
        }
        else {
            if(value != 1) {
                System.out.println("Can't incr value by 10. Already incred!");
                return;
            }
            else {
                value +=11;
            }
        }
        return;
    }
    public void subTenFromValue() {
        if(!imAce) {
            System.out.println("Cant't decr value by 10. Im NOT an ace!");
            return;
        }
        else {
            if(value != 11) {
                System.out.println("Can't decr value by 10. Already decred!");
                return;
            }
            else {
                value -= 11;
            }
        }
        return;
    }
}

