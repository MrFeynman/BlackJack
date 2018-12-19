package players;
import cards.Card;

public class Hand {
    private Card[] hand;
    private boolean softHand;

    public Hand() {
        hand = new Card[2];
        softHand = false;
    }

    public Hand(Card[] cardsOnStart) {
        hand = cardsOnStart;
        softHand = (cardsOnStart[0].isCardAce() || cardsOnStart[1].isCardAce());
    }


    public Card[] getHandCards() {
        return hand;
    }

    public void addCardToHand(Card cardToAdd) {
        if (hand[0] == null) {
            hand[0] = cardToAdd;
        } else if (hand[1] == null) {
            hand[1] = cardToAdd;
        } else {
            int moveCount;
            Card[] biggerSizeHand = new Card[hand.length + 1];
            for (moveCount = 0; moveCount < hand.length; moveCount++) {
                biggerSizeHand[moveCount] = hand[moveCount];
            }
            biggerSizeHand[moveCount] = cardToAdd;
            hand = biggerSizeHand;
        }
    }

    public int getHandSize() {
        return hand.length;
    }

    public Card[] checkForSplits() {
        Card[] bigEnoughToHoldAllCards = new Card[10];
        int bigEnoughCounter = 0;
        for (int outerNext = 0; outerNext < hand.length; outerNext++) {
            for (int innerNext = 0; innerNext < hand.length; innerNext++) {
                if (hand[outerNext].getCardsUnicID() == hand[innerNext].getCardsUnicID()) {
                    continue;
                } else if (hand[outerNext].getCardsName().equals(hand[innerNext].getCardsName())) {
                    boolean allreadyExists = false;
                    for (int nextBig = 0; nextBig < bigEnoughToHoldAllCards.length; nextBig++) {
                        if(bigEnoughToHoldAllCards[nextBig] != null) {
                            if (bigEnoughToHoldAllCards[nextBig].getCardsUnicID() == hand[outerNext].getCardsUnicID()) {
                                allreadyExists = true;
                            }
                        }
                    }
                    if (!allreadyExists) {
                        bigEnoughToHoldAllCards[bigEnoughCounter++] = hand[outerNext];
                    }
                }
            }
        }
        int notNulls = 0;
        for(int nextNotNull = 0; nextNotNull < bigEnoughToHoldAllCards.length; nextNotNull++) {
            if(bigEnoughToHoldAllCards[nextNotNull] == null) {
                continue;
            }
            else {
                notNulls++;
            }
        }
        if(notNulls < 1) {
            return null;
        }
        Card[] arryToReturn = new Card[notNulls];
        int nextToReturn = 0;
        for(Card cycleDummy : bigEnoughToHoldAllCards) {
            if(cycleDummy != null) {
                arryToReturn[nextToReturn++] = cycleDummy;
            }
        }
        return arryToReturn;
    }
}
