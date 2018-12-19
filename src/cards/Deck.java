package cards;

public class Deck {
    public static final int DECK_SIZE = 52;
    public static final int CARDS_PER_SUITE = 13;
    public static final int SUITS = 4;

    private Card[] deck = new Card[DECK_SIZE];
    private static int cardsLeft = DECK_SIZE;

    public Deck() {
        for(int nextSuit = 0, nextCard = 0; nextSuit < SUITS; nextSuit++) {
            for(int nextCardPerSuit = 0; nextCardPerSuit < CARDS_PER_SUITE; nextCardPerSuit++) {
                deck[nextCard++] = new Card(nextCardPerSuit,nextSuit);
            }
        }
    }

    public void debuggShowAllDeck() {
        System.out.println("      В колоде: ");
        for(int nextSuit = 0, nextCard = 0; nextSuit < SUITS; nextSuit++){
            System.out.println("");
            for(int nextCardPerSuit = 0; nextCardPerSuit < CARDS_PER_SUITE; nextCardPerSuit++) {
                Card dummyCard = deck[nextCard++];
                System.out.print(dummyCard.getCardsName() + " " + dummyCard.getCardsSuit()
                        + " ID" + dummyCard.getCardsUnicID() + " ");
            }
        }
        System.out.println();
    }
    public void shuffleDeck() {
        //System.out.println("      Крупье тщательно перемешивает колоду, и теперь там: ");
        for(int nextCard = 0; nextCard < DECK_SIZE; nextCard++) {
            int randomCardNum1 = (int)(Math.random() * DECK_SIZE);
            int randomCardNum2 = (int)(Math.random() * DECK_SIZE);
            Card swapCard = deck[randomCardNum1];
            deck[randomCardNum1] = deck[randomCardNum2];
            deck[randomCardNum2] = swapCard;
        }
    }
    public Card[] giveCardsOnStart() {
        Card[] deckToGive = new Card[2];
        deckToGive[0] = deck[--cardsLeft];
        deckToGive[1] = deck[--cardsLeft];
        return  deckToGive;
    }
}
