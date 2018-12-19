package players;

import cards.Card;

import java.nio.charset.StandardCharsets;

public class AIPlayer extends Player {
    public AIPlayer() {
        super("AI Player");
    }
    public String decide(Card dealersKnownCard) {
        final String HIT = "Беру";
        final String STAND = "Пасую";
        final String DOUBLE = "Удваиваю";
        final String SPLIT = "Делю";
        final String ERROR = "Dont know!";
        Card[] myPossibleSplits = this.getHand().checkForSplits();
        Hand myHand = this.getHand();
        Card[] myCards = myHand.getHandCards();
        Card dealersCard = dealersKnownCard;
        boolean gotAceOnMyHand = false;
        int countHardSum = 0;
        int countSoftSum = 0;
        int noAceSum = 0;
        int dealersValue = dealersCard.getCardsValue();
        for(int nextCard = 0; nextCard < myCards.length; nextCard++) {
            if(myCards[nextCard].isCardAce()) {
                countHardSum += 1;
                countSoftSum += 11;
                gotAceOnMyHand = true;

            }
            else {
                countHardSum += myCards[nextCard].getCardsValue();
                countSoftSum += myCards[nextCard].getCardsValue();
                noAceSum += myCards[nextCard].getCardsValue();
            }
        }
        System.out.println("\t\t" + this.getName() + " " + this.getSurname() + " думает: ");
        System.out.print("\t\t\tУ меня на руках: " );
        for(Card cycleDummy : myCards) {
            System.out.print(cycleDummy.getCardsName() + "; ");
        }
        System.out.println();
        if(gotAceOnMyHand) {
            System.out.println("\t\t\tСумма моих карт - " + countHardSum + " или " + countSoftSum);

        }
        else {
            System.out.println("\t\t\tСумма моих карт - " + countHardSum);
        }
        System.out.println("\t\t\tА у крупье," + dealersCard.getCardsName());
        String playerDecision = ERROR;
        if(!gotAceOnMyHand && myPossibleSplits == null) {
            switch (dealersValue) {
                case 2:
                    if(countHardSum >= 5 && countHardSum <= 8) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 9) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 10 || countHardSum == 11) {
                        playerDecision = DOUBLE;
                    }
                    else if(countHardSum == 12) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum > 12) {
                        playerDecision = STAND;
                    }
                    break;
                case 3:
                    if(countHardSum >= 5 && countHardSum <= 8) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 9 || countHardSum == 10 || countHardSum == 11 ) {
                        playerDecision = DOUBLE;
                    }
                    else if(countHardSum == 12) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum > 12) {
                        playerDecision = STAND;
                    }
                    break;
                case 4:
                case 5:
                case 6:
                    if(countHardSum >= 5 && countHardSum <= 8) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 9 || countHardSum == 10 || countHardSum == 11 ) {
                        playerDecision = DOUBLE;
                    }
                    else if(countHardSum >= 12) {
                        playerDecision = STAND;
                    }
                    break;
                case 7:
                case 8:
                case 9:
                    if(countHardSum >= 5 && countHardSum <= 8) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 9) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 10 || countHardSum == 11) {
                        playerDecision = DOUBLE;
                    }
                    else if(countHardSum >= 12 && countHardSum <= 16) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum > 16) {
                        playerDecision = STAND;
                    }
                    break;
                case 10:
                case 1:
                    if(countHardSum >= 5 && countHardSum <= 8) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 9 || countHardSum == 10) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum == 11) {
                        playerDecision = DOUBLE;
                    }
                    else if(countHardSum >= 12 && countHardSum <= 16) {
                        playerDecision = HIT;
                    }
                    else if(countHardSum > 16) {
                        playerDecision = STAND;
                    }
                    break;
            }
        }
        else if(myPossibleSplits != null) {
            Card mySplit = myPossibleSplits[0];
            switch (dealersValue) {
                case 2:
                case 3:
                case 4:
                    if(mySplit.getCardsValue() == 2 || mySplit.getCardsValue() == 3) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 4) {
                        playerDecision = HIT;
                    }
                    else if(mySplit.getCardsValue() == 5) {
                        playerDecision = DOUBLE;
                    }
                    else if((mySplit.getCardsValue() >= 6 && mySplit.getCardsValue() <= 9) || mySplit.isCardAce() == true) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 10) {
                        playerDecision = STAND;
                    }
                    break;
                case 5:
                case 6:
                    if((mySplit.getCardsValue() >= 2 && mySplit.getCardsValue() <= 4) || (mySplit.getCardsValue() >= 6 &&
                    mySplit.getCardsValue() <= 9) || mySplit.isCardAce() == true) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 5) {
                        playerDecision = DOUBLE;
                    }
                    else if(mySplit.getCardsValue() == 10) {
                        playerDecision = STAND;
                    }
                    break;
                case 7:
                    if(mySplit.getCardsValue() == 2 || mySplit.getCardsValue() == 3) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 4 || mySplit.getCardsValue() == 6) {
                        playerDecision = HIT;
                    }
                    else if(mySplit.getCardsValue() == 5) {
                        playerDecision = DOUBLE;
                    }
                    else if(mySplit.getCardsValue() == 7 || mySplit.getCardsValue() == 8 || mySplit.isCardAce() == true) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 9 || mySplit.getCardsValue() == 10) {
                        playerDecision = STAND;
                    }
                    break;
                case 8:
                case 9:
                    if((mySplit.getCardsValue() >= 2 && mySplit.getCardsValue() <= 4) || mySplit.getCardsValue() == 6 ||
                    mySplit.getCardsValue() == 7) {
                        playerDecision = HIT;
                    }
                    else if(mySplit.getCardsValue() == 5) {
                        playerDecision = DOUBLE;
                    }
                    else if(mySplit.getCardsValue() == 8 || mySplit.getCardsValue() == 9 || mySplit.isCardAce() == true) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 10) {
                        playerDecision = STAND;
                    }
                    break;
                case 10:
                case 1:
                    if(mySplit.getCardsValue() >= 2 && mySplit.getCardsValue() <= 7) {
                        playerDecision = HIT;
                    }
                    else if(mySplit.getCardsValue() == 8 || (mySplit.isCardAce() == true)) {
                        playerDecision = SPLIT;
                    }
                    else if(mySplit.getCardsValue() == 9 || mySplit.getCardsValue() == 10) {
                        playerDecision = STAND;
                    }
                    break;
            }
        }
        else {
            switch (dealersValue) {
                case 2:
                    if(noAceSum >=2 && noAceSum <= 6) {
                        playerDecision = HIT;
                    }
                    else if(noAceSum == 7) {
                        playerDecision = DOUBLE;
                    }
                    else if(noAceSum > 7) {
                        playerDecision = STAND;
                    }
                    break;
                case 3:
                    if(noAceSum >=2 && noAceSum <= 5) {
                        playerDecision = HIT;
                    }
                    else if(noAceSum == 6 || noAceSum == 7) {
                        playerDecision = DOUBLE;
                    }
                    else if(noAceSum > 7) {
                        playerDecision = STAND;
                    }
                    break;
                case 4:
                    if(noAceSum == 2 || noAceSum == 3) {
                        playerDecision = HIT;
                    }
                    else if(noAceSum >= 4 || noAceSum <= 7) {
                        playerDecision = DOUBLE;
                    }
                    else if(noAceSum > 7) {
                        playerDecision = STAND;
                    }
                    break;
                case 5:
                    if(noAceSum >= 2 || noAceSum <= 7) {
                        playerDecision = DOUBLE;
                    }
                    else if(noAceSum > 7) {
                        playerDecision = STAND;
                    }
                    break;
                case 6:
                    if(noAceSum >= 2 || noAceSum <= 7) {
                        playerDecision = DOUBLE;
                    }
                    else if(noAceSum > 7) {
                        playerDecision = STAND;
                    }
                    break;
                case 7:
                case 8:
                    if(noAceSum >=2 && noAceSum <= 6) {
                        playerDecision = HIT;
                    }
                    else if(noAceSum > 6) {
                        playerDecision = STAND;
                    }
                    break;
                case 9:
                case 10:
                case 1:
                    if(noAceSum >=2 && noAceSum <= 7) {
                        playerDecision = HIT;
                    }
                    else if(noAceSum > 7) {
                        playerDecision = STAND;
                    }
                    break;
            }
        }
        return playerDecision;
    }
}

