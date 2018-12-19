package players;
import cards.Card;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, String surname, int money) {
        super(name, surname, money);
    }
    public String decide(Card dealersKnownCard) {
        return "Заглукаша!";
    }
}
