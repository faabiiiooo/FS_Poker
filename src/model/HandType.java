package model;

import java.util.ArrayList;
import java.util.Collections;

public enum HandType {
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

        for(int i = 0; i < cards.size() - 1; i++){
            for(int j = i+1; j < cards.size();j++ ){
                if(cards.get(i).getRank()== cards.get(j).getRank() && countIdenticalCards < 3){
                    countIdenticalCards++;
                }
            }
        }
        if(countIdenticalCards == 3) {
            threeOfAKindFound = true;
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
        int countIdenticalCards = 0;
        ArrayList<Card> itemsToDelete = new ArrayList<Card>();  //Bin

        for(int i = 0; i < clonedCards.size() - 1 && countIdenticalCards < 3; i++){
            for(int j = i+1; j < clonedCards.size() && countIdenticalCards < 3;j++ ){
                if(clonedCards.get(i).getRank()== clonedCards.get(j).getRank() && countIdenticalCards < 3){
                    countIdenticalCards++;
                    itemsToDelete.add(clonedCards.get(i));  //adding cards wich are part of the ThreePair to bin
                    itemsToDelete.add(clonedCards.get(j));
                }
            }
        }
        if(countIdenticalCards == 3) {
            for(Card c : itemsToDelete){
                clonedCards.remove(c);  //removing Cards from  cloned arraylist
            }
            firstThreeFound = true;
        }

            //check if in remaining cards is a one pair, what would be neccesairy for a full house.
            return firstThreeFound && isOnePair(clonedCards);

    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
}
