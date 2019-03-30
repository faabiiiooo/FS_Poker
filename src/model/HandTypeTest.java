package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HandTypeTest {
	// We define the hands using abbreviations. The code at the bottom
	// of this class can translate one of these strings into a card.
	//
	// Another method takes a set of five cards, and translates the whole hand
	//
	// Yet another method does this for a whole set of hands
	private static String[][] highCards = {
			{ "2S", "9C", "3H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "2S", "3S", "4S", "5S", "7S" },
			{ "AS", "KC", "QH", "JD", "TH" }
			};
	
	private static String[][] pairs = {
			{ "2S", "2C", "3H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "3S", "2C", "3H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7H", "5D", "7H" },
			{ "2S", "AC", "5H", "5D", "AH" }/*,
			{ "3S", "2C", "3H", "3D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }*/
			};

	private static String[][] threeOfAKind = {
			{ "QC", "7C", "QH", "7S", "QD" },

	};

	private static String[][] straight = {
			{"AS","2H","3C","4S","5C"}
	};

	private static String[][] flush = {
			{"8D","4D","KD","QD","JD"}
	};

	private static String[][] fullHouse = {
			{"QC","7C","QH","7S","QD"}
	};

	private static String[][] fourOfAKind = {
			{"KH","KS","KC","9C","KH"}
	};

	private static String[][] straightFlush = {
			{"9H", "JH", "KH", "QH", "AH"}
	};

	// This is where we store the translated hands
	ArrayList<ArrayList<Card>> highCardHands;
	ArrayList<ArrayList<Card>> pairHands;
	ArrayList<ArrayList<Card>> twoPairHands;
	ArrayList<ArrayList<Card>> threeOfAKindHands;
	ArrayList<ArrayList<Card>> straightHands;
	ArrayList<ArrayList<Card>> flushHands;
	ArrayList<ArrayList<Card>> fullHouseHands;
	ArrayList<ArrayList<Card>> fourOfAKindHands;
	ArrayList<ArrayList<Card>> straightFlushHands;
	
	/**
	 * The makeHands method is called before each test method,
	 * and prepares the translated hands. We recreate these for
	 * each test method, in case the test method damages the data.
	 */
	@Before
	public void makeHands() {
		highCardHands = makeHands(highCards);
		pairHands = makeHands(pairs);
		twoPairHands = makeHands(twoPairs);
		threeOfAKindHands = makeHands(threeOfAKind);
		straightHands = makeHands(straight);
		flushHands = makeHands(flush);
		fullHouseHands = makeHands(fullHouse);
		fourOfAKindHands = makeHands(fourOfAKind);
		straightFlushHands = makeHands(straightFlush);
	}

	/**
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.
	 */
	@Test
	public void testIsOnePair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isOnePair(hand)); // Two-pair contains a pair
		}
	}

	/**
	 * This is the test method for the isTwoPair in HandType.
	 */
	@Test
	public void testIsTwoPair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
	}

	@Test
	public void testIsThreeOfAKind(){
		for(ArrayList<Card> hand : threeOfAKindHands){
			assertTrue(HandType.isThreeOfAKind(hand));
		}
	}

	@Test
	public void testIsStraight(){
		for(ArrayList<Card> hand : straightHands){
			assertTrue(HandType.isStraight(hand));
		}
	}

	@Test
	public void testIsFlush(){
		for(ArrayList<Card> hand : flushHands){
			assertTrue(HandType.isFlush(hand));
		}
	}

	@Test
	public void testIsFullHouse(){
		for(ArrayList<Card> hand : fullHouseHands){
			assertTrue(HandType.isFullHouse(hand));
		}
	}

	@Test
	public void testIsFourOfAKind(){
		for(ArrayList<Card> hand : fourOfAKindHands){
			assertTrue(HandType.isFourOfAKind(hand));
		}
	}

	@Test
	public void testIsStraightFlush(){
		for(ArrayList<Card> hand : straightFlushHands){
			assertTrue(HandType.isStraightFlush(hand));
		}
	}

	@Test
	public void testHandleHighCardTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("2H"));
		p1.addCard(makeCard("AH"));
		p1.addCard(makeCard("3H"));
		p1.addCard(makeCard("JS"));
		p1.addCard(makeCard("5S"));

		p2.addCard(makeCard("5C"));
		p2.addCard(makeCard("AC"));
		p2.addCard(makeCard("KD"));
		p2.addCard(makeCard("8S"));
		p2.addCard(makeCard("6H"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);


		Player winner = HandType.handleTieBreak(players);


		assertEquals(p2,winner);
	}

	@Test
	public void testHandleOnePairTieBreak(){

		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("2H"));
		p1.addCard(makeCard("AH"));
		p1.addCard(makeCard("5H"));
		p1.addCard(makeCard("JS"));
		p1.addCard(makeCard("5S"));

		p2.addCard(makeCard("5C"));
		p2.addCard(makeCard("AC"));
		p2.addCard(makeCard("5D"));
		p2.addCard(makeCard("8S"));
		p2.addCard(makeCard("6H"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);



		Player winner = HandType.handleTieBreak(players);





		assertEquals(p1,winner);


	}

	@Test
	public void testHandleTwoPairTieBreak(){

		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");


		p1.addCard(makeCard("KH"));
		p1.addCard(makeCard("2D"));
		p1.addCard(makeCard("5C"));
		p1.addCard(makeCard("KD"));
		p1.addCard(makeCard("2S"));

		p2.addCard(makeCard("KC"));
		p2.addCard(makeCard("7S"));
		p2.addCard(makeCard("2H"));
		p2.addCard(makeCard("KS"));
		p2.addCard(makeCard("2C"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);


		Player winner = HandType.handleTieBreak(players);



		assertEquals(p2,winner);


	}

	@Test
	public void testHandleThreeOfAKindTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("AH"));
		p1.addCard(makeCard("2D"));
		p1.addCard(makeCard("AC"));
		p1.addCard(makeCard("KD"));
		p1.addCard(makeCard("AS"));

		p2.addCard(makeCard("3C"));
		p2.addCard(makeCard("7S"));
		p2.addCard(makeCard("7H"));
		p2.addCard(makeCard("KS"));
		p2.addCard(makeCard("7C"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);


		Player winner = HandType.handleTieBreak(players);



		assertEquals(p1,winner);


	}
	@Test
	public void testStraightTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("AH"));
		p1.addCard(makeCard("2D"));
		p1.addCard(makeCard("3C"));
		p1.addCard(makeCard("4D"));
		p1.addCard(makeCard("5S"));

		p2.addCard(makeCard("KC"));
		p2.addCard(makeCard("8S"));
		p2.addCard(makeCard("7H"));
		p2.addCard(makeCard("QS"));
		p2.addCard(makeCard("JC"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);


		Player winner = HandType.handleTieBreak(players);



		assertEquals(p2,winner);
	}

	@Test
	public void testHandleFlushTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("2H"));
		p1.addCard(makeCard("9H"));
		p1.addCard(makeCard("3H"));
		p1.addCard(makeCard("7H"));
		p1.addCard(makeCard("5H"));

		p2.addCard(makeCard("KC"));
		p2.addCard(makeCard("2C"));
		p2.addCard(makeCard("3C"));
		p2.addCard(makeCard("QC"));
		p2.addCard(makeCard("JC"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);


		Player winner = HandType.handleTieBreak(players);



		assertEquals(p2,winner);
	}
	@Test
	public void testHandleFullHouseTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("9H"));
		p1.addCard(makeCard("KS"));
		p1.addCard(makeCard("9C"));
		p1.addCard(makeCard("KC"));
		p1.addCard(makeCard("KH"));

		p2.addCard(makeCard("KD"));
		p2.addCard(makeCard("KS"));
		p2.addCard(makeCard("3H"));
		p2.addCard(makeCard("3S"));
		p2.addCard(makeCard("KC"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);



		Player winner = HandType.handleTieBreak(players);



		assertEquals(p1,winner);
	}
	@Test
	public void testHandleFourOfAKindTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("KH"));
		p1.addCard(makeCard("KS"));
		p1.addCard(makeCard("KC"));
		p1.addCard(makeCard("9C"));
		p1.addCard(makeCard("KH"));

		p2.addCard(makeCard("JD"));
		p2.addCard(makeCard("JS"));
		p2.addCard(makeCard("JH"));
		p2.addCard(makeCard("JS"));
		p2.addCard(makeCard("KC"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);



		Player winner = HandType.handleTieBreak(players);



		assertEquals(p1,winner);
	}
	@Test
	public void testHandleStraightFlushTieBreak(){
		Player p1 = new Player("testPlayer1");
		Player p2 = new Player("testPlayer2");

		p1.addCard(makeCard("AH"));
		p1.addCard(makeCard("5H"));
		p1.addCard(makeCard("3H"));
		p1.addCard(makeCard("2H"));
		p1.addCard(makeCard("4H"));

		p2.addCard(makeCard("KC"));
		p2.addCard(makeCard("9C"));
		p2.addCard(makeCard("JC"));
		p2.addCard(makeCard("TC"));
		p2.addCard(makeCard("QC"));

		p1.evaluateHand();
		p2.evaluateHand();
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);



		Player winner = HandType.handleTieBreak(players);



		assertEquals(p2,winner);
	}

	/**
	 * Make an ArrayList of hands from an array of string-arrays
	 */
	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
		for (String[] hand : handsIn) {
			handsOut.add(makeHand(hand));
		}
		return handsOut;
	}
	
	/**
	 * Make a hand (ArrayList<Card>) from an array of 5 strings
	 */
	private ArrayList<Card> makeHand(String[] inStrings) {
		ArrayList<Card> hand = new ArrayList<>();
		for (String in : inStrings) {
			hand.add(makeCard(in));
		}
		return hand;
	}
	
	/**
	 * Create a card from a 2-character String.
	 * First character is the rank (2-9, T, J, Q, K, A) 
	 * Second character is the suit (C, D, H, S)
	 * 
	 * No validation or error handling!
	 */
	private Card makeCard(String in) {
		char r = in.charAt(0);
		Card.Rank rank = null;
		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
		else if (r == 'T') rank = Card.Rank.Ten;
		else if (r == 'J') rank = Card.Rank.Jack;
		else if (r == 'Q') rank = Card.Rank.Queen;
		else if (r == 'K') rank = Card.Rank.King;
		else if (r == 'A') rank = Card.Rank.Ace;
		
		char s = in.charAt(1);
		Card.Suit suit = null;
		if (s == 'C') suit = Card.Suit.Clubs;
		if (s == 'D') suit = Card.Suit.Diamonds;
		if (s == 'H') suit = Card.Suit.Hearts;
		if (s == 'S') suit = Card.Suit.Spades;

		return new Card(suit, rank);
	}
}
