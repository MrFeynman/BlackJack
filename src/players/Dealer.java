package players;
import cards.*;

public class Dealer extends Player {

    private static final String OOPS = "Im a Dealer!";
    private static final int ENDLESS = 9999;

    public Dealer() {
        super("Dealer");
    }

    @Override public int getMoney() {
        return ENDLESS;
    }
    @Override public String getName() {
        return OOPS;
    }
    @Override public String getSurname() {
        return OOPS;
    }
    @Override public Hand getHand() {
        Card[] swapCards = super.getHand().getHandCards();
        swapCards[1] = new Card();
        return new Hand(swapCards);
    }
    public String decide(Card dealersKnownCard) {
        return OOPS;
    }

}
