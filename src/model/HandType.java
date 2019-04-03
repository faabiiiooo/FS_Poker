package model;

import java.util.ArrayList;
import java.util.Collections;

public enum HandType implements Comparable<HandType>{
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;


    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }

    public static Player handleTieBreak(ArrayList<Player> winners){ //check wihch TieBreak that has to be handled
        Player winner = null;
        switch (winners.get(0).getHandType()){

            case HighCard:
                winner = handleHighCardTieBreak(winners);
                break;
            case OnePair:
                winner = handleOnePairTieBreak(winners);
                break;
            case TwoPair:
                winner = handleTwoPairTieBreak(winners);
                break;
            case ThreeOfAKind:
                winner = handleThreeOfAKindTieBreak(winners);
                break;
            case Straight:
                winner = handleStraightTieBreak(winners);
                break;
            case Flush:
                winner = handleFlushTieBreak(winners);
                break;
            case FullHouse:
                winner = handleFullHouseTieBreak(winners);
                break;
            case FourOfAKind:
                winner = handleFourOfAKindTieBreak(winners);
                break;
            case StraightFlush:
                winner = handleStraightFlushTieBreak(winners);
                break;

        }
    return winner;
    }

    private static ArrayList<Player> sortPlayerTieBreakCards(ArrayList<Player> winners){
        for (int i = 0; i < winners.size(); i++) {
            Collections.sort(winners.get(i).getCards());  //sorting hands of players by their ordinal
        }
        return winners;
    }

    private static Player handleHighCardTieBreak(ArrayList<Player> winners) {
        Player winner = null;


       winners = sortPlayerTieBreakCards(winners);

        for (int i = 0; i < winners.size() - 1; i++) {
            for (int j = i + 1; j < winners.size(); j++) {
                if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 1).compareTo(
                        winners.get(j).getCards().get(winners.get(j).getCards().size() - 1)) < 0) {
                    winner = winners.get(j);  //returning player with higher ordinal on last place in arraylist
                    winners.get(i).setWinEval("lost"); //setting winEval to lost for every other player
                } else {
                    if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 1).compareTo(
                            winners.get(j).getCards().get(winners.get(j).getCards().size() - 1)) > 0) {
                        winner = winners.get(i);
                        winners.get(j).setWinEval("lost");
                    } else {
                        if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 1).compareTo(
                                winners.get(j).getCards().get(winners.get(j).getCards().size() - 1)) == 0) {

                            if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 2).compareTo(
                                    winners.get(j).getCards().get(winners.get(j).getCards().size() - 2)) < 0) {
                                winner = winners.get(j);  //returning player with higher ordinal on 2ndlast place in arraylist
                                winners.get(i).setWinEval("lost");
                            } else {
                                if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 2).compareTo(
                                        winners.get(j).getCards().get(winners.get(j).getCards().size()- 2)) > 0) {
                                    winner = winners.get(i);
                                    winners.get(j).setWinEval("lost");
                                } else {
                                    if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 2).compareTo(
                                            winners.get(j).getCards().get(winners.get(j).getCards().size() - 2)) == 0) {

                                        if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 3).compareTo(
                                                winners.get(j).getCards().get(winners.get(j).getCards().size() - 3)) < 0) {
                                            winner = winners.get(j);  //returning player with higher ordinal on 3dlast place in arraylist
                                            winners.get(i).setWinEval("lost");
                                        } else {
                                            if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 3).compareTo(
                                                    winners.get(j).getCards().get(winners.get(j).getCards().size() - 3)) > 0) {
                                                winner = winners.get(i);
                                                winners.get(j).setWinEval("lost");
                                            } else {
                                                if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 3).compareTo(
                                                        winners.get(j).getCards().get(winners.get(j).getCards().size() - 3)) == 0) {

                                                    if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 4).compareTo(
                                                            winners.get(j).getCards().get(winners.get(j).getCards().size() - 4)) < 0) {
                                                        winner = winners.get(j);
                                                        winners.get(i).setWinEval("lost");
                                                    } else {
                                                        if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 4).compareTo(
                                                                winners.get(j).getCards().get(winners.get(j).getCards().size() - 4)) > 0) {
                                                            winner = winners.get(i);
                                                            winners.get(j).setWinEval("lost");
                                                        } else {
                                                            if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 4).compareTo(
                                                                    winners.get(j).getCards().get(winners.get(j).getCards().size() - 4)) == 0) {
                                                                if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 5).compareTo(
                                                                        winners.get(j).getCards().get(winners.get(j).getCards().size() - 5)) < 0) {
                                                                    winner = winners.get(j);
                                                                    winners.get(i).setWinEval("lost");
                                                                } else {
                                                                    if (winners.get(i).getCards().get(winners.get(i).getCards().size() - 5).compareTo(
                                                                            winners.get(j).getCards().get(winners.get(j).getCards().size() - 5)) > 0) {
                                                                        winner = winners.get(i);
                                                                        winners.get(j).setWinEval("lost");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return winner;
    }

    private static Player handleOnePairTieBreak(ArrayList<Player> winners){
        Player winner = null;
        int winnerIndex = -1;
        boolean foundWinner = false;

        //creating a 2d arraylist. 1st dimension = player, 2nd dimension = cards of onePair
        ArrayList<ArrayList<Card>> onePairCards = getPairCards(winners, 2);


        for(int i = 0; i < onePairCards.size() -1 && !foundWinner; i++){  //iterating through players
            for(int j = i+1; j < onePairCards.size() && !foundWinner; j++){
               if(onePairCards.get(i).get(onePairCards.get(i).size()-1).compareTo(
                       onePairCards.get(j).get(onePairCards.get(j).size()-1)) < 0){  //comparing cards of both players
                        winnerIndex = j;
                        foundWinner = true;
                    } else {
                        if(onePairCards.get(i).get(onePairCards.get(i).size()-1).compareTo(
                                onePairCards.get(j).get(onePairCards.get(j).size()-1)) > 0) {
                            winnerIndex = i;
                            foundWinner = true;
                        } else {
                            if(onePairCards.get(i).get(onePairCards.get(i).size()-1).compareTo(
                                    onePairCards.get(j).get(onePairCards.get(j).size()-1)) == 0) {
                                ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();
                                //When both players have same Pair, remove pair & handleHighCard with remaining Cards.
                                clonedWinners.get(i).getCards().removeAll(onePairCards.get(i));
                                clonedWinners.get(j).getCards().removeAll(onePairCards.get(j));

                                winner = handleHighCardTieBreak(clonedWinners);

                            }
                        }

                    }
                }

        }

        if(winnerIndex != -1){
            winner = winners.get(winnerIndex);  //getting Winner based on index in outter Arraylist of onePairCards.
            for(Player p : winners){
                p.setWinEval("lost");
            }
        }

        return winner;
    }

    private static Player handleTwoPairTieBreak(ArrayList<Player>winners){
        Player winner = null;
        int winnerIndex = -1;
        boolean foundWinner = false;
        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();

        //getting TwoPair Cards

        ArrayList<ArrayList<Card>> twoPairCards = getPairCards(clonedWinners,4);


        for(int i =0; i < twoPairCards.size()-1 && !foundWinner; i++){
            for(int j = i+1; j < twoPairCards.size() &&!foundWinner; j++){
                if(twoPairCards.get(i).get(twoPairCards.get(i).size()-1).compareTo(
                        twoPairCards.get(j).get(twoPairCards.get(j).size()-1)) < 0){
                    winnerIndex = j;
                    foundWinner = true;  //checking which players twoPair Cards are higher.
                } else {
                    if(twoPairCards.get(i).get(twoPairCards.get(i).size()-1).compareTo(
                            twoPairCards.get(j).get(twoPairCards.get(j).size()-1)) > 0){
                        winnerIndex = i;
                        foundWinner = true;
                    } else {
                        if (twoPairCards.get(i).get(twoPairCards.get(i).size() - 1).compareTo(
                                twoPairCards.get(j).get(twoPairCards.get(j).size() - 1))==0){

                            if(twoPairCards.get(i).get(twoPairCards.get(i).size()-3).compareTo(
                                    twoPairCards.get(j).get(twoPairCards.get(j).size()-3)) < 0){
                                winnerIndex = j;
                                foundWinner = true;
                            } else {
                                if(twoPairCards.get(i).get(twoPairCards.get(i).size()-3).compareTo(
                                        twoPairCards.get(j).get(twoPairCards.get(j).size()-3)) > 0){
                                    winnerIndex = i;
                                    foundWinner = true;
                                } else {
                                    if (twoPairCards.get(i).get(twoPairCards.get(i).size() - 3).compareTo(
                                            twoPairCards.get(j).get(twoPairCards.get(j).size() - 3))==0){
                                        clonedWinners.get(i).getCards().removeAll(twoPairCards.get(i));
                                        clonedWinners.get(j).getCards().removeAll(twoPairCards.get(j));

                                        winner = handleHighCardTieBreak(clonedWinners);
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }

        if(winnerIndex != -1){
            winner = winners.get(winnerIndex);  //getting Winner based on index in outter Arraylist of onePairCards.
            for(Player p : winners){
                p.setWinEval("lost");
            }
        }


        return winner;
    }

    private static Player handleThreeOfAKindTieBreak(ArrayList<Player> winners){
        Player winner = null;
        int winnerIndex = -1;
        boolean foundWinner = false;

        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();

        ArrayList<ArrayList<Card>> threeOfAKindCards = getPairCards(clonedWinners, 6);

        for (int i = 0; i < threeOfAKindCards.size()-1 && !foundWinner; i++){
            for(int j = i+1; j < threeOfAKindCards.size() && !foundWinner; j++){
                if(threeOfAKindCards.get(i).get(threeOfAKindCards.get(i).size()-1).compareTo(
                        threeOfAKindCards.get(j).get(threeOfAKindCards.get(j).size()-1)) < 0){
                    winnerIndex = j;
                    foundWinner = true;
                } else {
                    if(threeOfAKindCards.get(i).get(threeOfAKindCards.get(i).size()-1).compareTo(
                            threeOfAKindCards.get(j).get(threeOfAKindCards.get(j).size()-1)) > 0){
                        winnerIndex = i;
                        foundWinner = true;
                    }
                }
            }

        }

        if(winnerIndex != -1){

            winner = clonedWinners.get(winnerIndex);
            for(Player p : clonedWinners){
                p.setWinEval("lost");
            }

        }




        return winner;
    }

    private static Player handleStraightTieBreak(ArrayList<Player> winners){
        Player winner = null;
        int winnerIndex = -1;
        boolean winnerFound = false;

        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();
        ArrayList<Integer> results = new ArrayList<>();

        for(int i = 0; i < clonedWinners.size(); i++){
            Collections.sort(clonedWinners.get(i).getCards());
        }

        for(int i = 0; i < clonedWinners.size();i++){  //putting values of two higest cards in arraylist
            int result = 0;
            for(int j = 3; j < Player.HAND_SIZE; j++){
                result += clonedWinners.get(i).getCards().get(j).getRank().ordinal();
            }
            results.add(result);

        }

        for(int i = 0; i < results.size()-1; i++){
            for(int j = i+1; j < results.size(); j++){
                if(results.get(i) < results.get(j)){
                    winnerIndex = j;
                } else {  //the higehr value must be the higher straight.
                    if(results.get(i) > results.get(j)){
                        winnerIndex = i;
                    }
                }
            }

        }

        winner = clonedWinners.get(winnerIndex);
        for(Player p : clonedWinners){
            p.setWinEval("lost");
        }

        return winner;
    }

    private static Player handleFlushTieBreak(ArrayList<Player> winners){
        Player winner = null;

        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();

        winner = handleHighCardTieBreak(clonedWinners);  //highest card wins.

        return winner;
    }

    private static Player handleFullHouseTieBreak(ArrayList<Player> winners){
        Player winner = null;

        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners;

        if(handleThreeOfAKindTieBreak(clonedWinners) != null){
            winner = handleThreeOfAKindTieBreak(clonedWinners);
        }

        return winner;
    }

    private static Player handleFourOfAKindTieBreak(ArrayList<Player> winners){
        Player winner = null;
        int winnerIndex = -1;
        boolean winnerFound = false;

        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();
        ArrayList<ArrayList<Card>> fourOfAKindCards = getPairCards(clonedWinners, 8);


        //Check which fourOfAKind is the higher card, that must be the winner

        for(int i = 0; i < fourOfAKindCards.size()-1 && !winnerFound; i++){
            for(int j = i+1; j < fourOfAKindCards.size() && !winnerFound; j++){
                if(fourOfAKindCards.get(i).get(fourOfAKindCards.get(i).size()-1).compareTo(
                        fourOfAKindCards.get(j).get(fourOfAKindCards.get(j).size()-1)) < 0){
                    winnerIndex = j;
                    winnerFound = true;
                } else {
                    if(fourOfAKindCards.get(i).get(fourOfAKindCards.get(i).size()-1).compareTo(
                            fourOfAKindCards.get(j).get(fourOfAKindCards.get(j).size()-1)) > 0){
                        winnerIndex = i;
                        winnerFound = true;
                    }
                }
            }
        }

        if(winnerIndex != -1){
            winner = clonedWinners.get(winnerIndex);
            for(Player p : clonedWinners){
                p.setWinEval("lost");
            }
        }

        return winner;
    }

    private static Player handleStraightFlushTieBreak(ArrayList<Player> winners){
        Player winner = null;

        ArrayList<Player> clonedWinners = (ArrayList<Player>) winners.clone();

        winner = handleStraightTieBreak(clonedWinners);

        return winner;
    }

    private static ArrayList<ArrayList<Card>> getPairCards(ArrayList<Player> winners, int numOfSearchedCards){
        ArrayList<ArrayList<Card>> pairCards = new ArrayList<>();

        //sorting cards of each winner by their ordinal

        for(int i = 0; i < winners.size(); i++){
            Collections.sort(winners.get(i).getCards());
        }

        /*
        adding every card with identical rank to second dimension of 2d arraylist.
        1st dimension = player, 2nd dimension = pairCards
        Only adding number of serached cards defined by numOfSearchedCards
         */


        for(int i = 0; i < winners.size(); i++){
            ArrayList<Card> playerPairCards = new ArrayList<>();
            for(int j = 0; j < winners.get(i).getCards().size()-1;j++){
                for(int n = j+1; n < winners.get(i).getCards().size(); n++){
                    if(winners.get(i).getCards().get(j).getRank() == winners.get(i).getCards().get(n).getRank()){
                        playerPairCards.add(winners.get(i).getCards().get(n));
                        playerPairCards.add(winners.get(i).getCards().get(j));
                        if(playerPairCards.size() == numOfSearchedCards){
                            pairCards.add(playerPairCards);
                        }
                    }
                }
            }
        }

        return pairCards;

    }


    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {

        boolean threeOfAKindFound = false;
        int countIdenticalCards = 0;

        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        Collections.sort(clonedCards);

        for(int i = 0; i < clonedCards.size()-2 && !threeOfAKindFound;i++){
            for(int j = i+1; j < clonedCards.size()-1 && !threeOfAKindFound; j++){
                for(int n = j + 1; n < clonedCards.size() && !threeOfAKindFound; n++){
                    if(clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(j).getRank()
                            == clonedCards.get(n).getRank()){
                        threeOfAKindFound = true;
                    }
                }
            }
        }

        return threeOfAKindFound;
    }
    
    public static boolean isStraight(ArrayList<Card> cards) {
        boolean straightFound = false;
        int countForStraight = 0;
        ArrayList<Card>clonedCards = (ArrayList<Card>) cards.clone();  //Sort Arraylist from lowest to highest Card.
        Collections.sort(clonedCards);


        //if Highest card is ace do this
        if(clonedCards.get(clonedCards.size()-1).getRank().toString().toLowerCase().contains("ace")){
            //clonedCards.remove(clonedCards.size()-1);
                //Is second highest class King? -> and so on
            if(clonedCards.get(clonedCards.size()-2).getRank().toString().toLowerCase().contains("king")){
                //clonedCards.remove(clonedCards.size()-1);
                if(clonedCards.get(clonedCards.size()-3).getRank().toString().toLowerCase().contains("queen")) {
                    //clonedCards.remove(clonedCards.size()-1);
                    if (clonedCards.get(clonedCards.size() - 4).getRank().toString().toLowerCase().contains("jack")) {
                        if(clonedCards.get(clonedCards.size() - 5).getRank().toString().toLowerCase().contains("10")){
                            straightFound = true;
                        }
                    }
                }
            } else {  //Is lowest card 2 then 3 then 4?
                if(clonedCards.get(0).getRank().toString().contains("2")){
                    if(clonedCards.get(1).getRank().toString().contains("3")){
                        if(clonedCards.get(2).getRank().toString().contains("4")){
                            if(clonedCards.get(3).getRank().toString().contains("5")){
                                straightFound = true;
                            }
                        }
                    }
                }
            }

        } else { //check for straight if no ace is included
            for(int i = 0; i < clonedCards.size()-1; i++){
                if(clonedCards.get(i).getRank().ordinal()+1 == clonedCards.get(i+1).getRank().ordinal()) {
                        countForStraight++;
                }

            }
            if(countForStraight == 4){
                straightFound = true;
            }
        }


        return straightFound;
    }


    
    public static boolean isFlush(ArrayList<Card> cards) {
        boolean flushFound = false;
        int countC = 0, countD = 0, countH = 0, countS = 0;

        for(Card c : cards){  //Check how many Cards have same suit
            if(c.getSuit().toString().toLowerCase().contains("clubs")){
                countC++;
            }
            if(c.getSuit().toString().toLowerCase().contains("diamonds")){
                countD++;
            }
            if(c.getSuit().toString().toLowerCase().contains("hearts")){
                countH++;
            }
            if(c.getSuit().toString().toLowerCase().contains("spades")){
                countS++;
            }
        }
            //If one of suits has 5 Cards than flush is true
        if(countC == 5 || countD == 5 || countH == 5 || countS == 5)
            flushFound = true;

        return flushFound;
    }
    
    public static boolean isFullHouse(ArrayList<Card> cards) {
        boolean firstThreeFound = false;
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone(); //Cloning cards to alter array
        Collections.sort(clonedCards);
        int countIdenticalCards = 0;
        ArrayList<Card> itemsToDelete = new ArrayList<>();  //Bin

        for(int i = 0; i < clonedCards.size()-2 && !firstThreeFound;i++){
            for(int j = i+1; j < clonedCards.size()-1 && !firstThreeFound; j++){
                for(int n = j + 1; n < clonedCards.size() && !firstThreeFound; n++){
                    if(clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(j).getRank()
                    == clonedCards.get(n).getRank()){
                        firstThreeFound = true;
                        clonedCards.remove(n);
                        clonedCards.remove(j);
                        clonedCards.remove(i);
                    }
                }
            }
        }
            //check if in remaining cards is a one pair, what would be neccesairy for a full house.
        return firstThreeFound && isOnePair(clonedCards);

    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
        boolean fourOfAKindFound = false;
        int countIdenticalRanks = 0;

        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        Collections.sort(clonedCards);

        if(clonedCards.get(0).getRank() == clonedCards.get(1).getRank()&&
        clonedCards.get(1).getRank() == clonedCards.get(2).getRank()&&
        clonedCards.get(2).getRank() == clonedCards.get(3).getRank()){
            fourOfAKindFound = true;
        } else {
            if(clonedCards.get(1).getRank() == clonedCards.get(2).getRank()&&
                    clonedCards.get(2).getRank() == clonedCards.get(3).getRank()&&
                    clonedCards.get(3).getRank() == clonedCards.get(4).getRank()){
                fourOfAKindFound = true;
            }
        }


        return fourOfAKindFound;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
        //StraightFlush is combination out of flush and straight, so let those both method check that
        return isFlush(cards) && isStraight(cards);
    }
}
