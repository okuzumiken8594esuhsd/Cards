package FindACard;

import java.awt.Color;

/**
 * This class models a card in a deck, in a text based form
 * <br>
 * Does not support multiple threads
 * @author okuzumiken8594
 */
public class Card {
	protected String suit;
	protected int number;
	protected boolean frontSide;
	protected static int nextCardNumber = 1;
	protected static String nextCardSuit = "Spades";
	
	/**
	 * Call this 52 times successively to make the deck
	 * <br>
	 * Makes cards in the order of:
	 * <br>
	 * Ace -> Two -> ... -> Ten -> Jack -> Queen -> King
	 * <br>
	 * and suits in the order of Spades -> Diamonds -> Clubs -> Hearts
	 * <hr>
	 * So, the deck will be in the order of
	 * <br>
	 * Ace of Spades, ..., King of Spades, Ace of Diamonds, ..., King of Diamonds, Ace of Clubs, ...
	 */
	public Card() {
		suit = nextCardSuit;
		number = nextCardNumber;
		frontSide = true;
		updateNextCard();
	}
	
	/**
	 * Changes the next card variables to the next card
	 * Helper method for the 
	 */
	private void updateNextCard() {
		// 1 == Ace, 13 == King
		nextCardNumber++;
		if (nextCardNumber > 13) {
			nextCardNumber = 1;
			if (nextCardSuit.equals("Spades")) {
				nextCardSuit = "Diamonds";
			} else if (nextCardSuit.equals("Diamonds")) {
				nextCardSuit = "Clubs";
			} else if (nextCardSuit.equals("Clubs")) {
				nextCardSuit = "Hearts";
			} else if (nextCardSuit.equals("Hearts")) {
				nextCardSuit = "Spades";
			}
		}
	}
	
	/**
	 * Compares the cards in the order of <br>
	 * Smallest: Ace, 2, 3, ..., Jack, Queen, King :Largest <br> 
	 * Smallest: Hearts, Clubs, Diamonds, Spades :Largest
	 * @param other
	 * @return the difference in the cards, -1, 0, or 1
	 * TODO decide whether to add the actual difference between cards
	 */
	public int compareTo(Card other) {
		// check number Ace is smallest -> King largest, King > Q > J > 10 > ... > Ace
		// check suit: spades > diamonds > clubs > hearts
		int greaterBy = 0;
		if (this == other) {
			return 0;
		}
		if (this == null || other == null) {
			return 0;
		}
		// greaterBy now has the difference in suit
		greaterBy = compareSuit(other);
		// now check for difference in number
		greaterBy = greaterBy + (other.number - this.number);
		// I somehow messed this up, so the number is opposite what it should be
		return -1 * greaterBy;
	}
	
	/**
	 * helper method for compareTo()
	 * Spades are bigger than Diamonds by 13, Diamonds are larger than Clubs by 13,
	 * Clubs are larger than Hearts by 13
	 * the specific card number will change this in an interval less than 13 
	 * @param other - the other card
	 * @return the difference in suit in intervals of 13
	 */
	private int compareSuit(Card other) {
		// converting a suit to a number to compare
		// start as hearts
		int thisSuit = 1;
		int otherSuit = 1;
		if (this.suit.equals("Spades")) {
			thisSuit = 4;
		} else if (this.suit.equals("Diamonds")) {
			thisSuit = 3;
		} else if (this.suit.equals("Clubs")) {
			thisSuit = 2;
		}
		if (other.suit.equals("Spades")) {
			otherSuit = 4;
		} else if (other.suit.equals("Diamonds")) {
			otherSuit = 3;
		} else if (other.suit.equals("Clubs")) {
			otherSuit = 2;
		}
		// return by how much greater * 13
		return (thisSuit - otherSuit) * 13;
		
	}
	
	/**
	 * 
	 * @return - the difference in number
	 */
	public int compareToIgnoreSuit(Card other) {
		// check number Ace is smallest -> King largest, King > Q > J > 10 > ... > Ace
		if (this == other) {
			return 0;
		}
		if (this == null || other == null) {
			return 0;
		}
		// now check for difference in number
		int greaterBy = this.number - other.number;
		return greaterBy;
	}
	
	/**
	 * equals method for another card
	 * @param other - the other card
	 * @return whether the card is equal
	 */
	public boolean equals(Card other) {
		if (this == other) {
			return true;
		}
		if (this == null || other == null) {
			return false;
		}
		boolean match = true;
		if (this.suit.equals(other.suit)) {
			// keep match as is
		} else {
			match = false;
		}
		if (this.number == other.number) {
			// keep match as is
		} else {
			match = false;
		}
		return match;
	}
	
	/**
	 * equals for cards, ignoring suit
	 * <br>
	 * Examples:
	 * <br>
	 * Ace of Diamonds == Ace of Hearts
	 * <br>
	 * Three of Spades == Three of Hearts
	 * @param other - the other card
	 * @return The equality of the cards, ignoring the suit
	 */
	public boolean equalsIgnoreSuit(Card other) {
		return this.number == other.number;
	}
	
	/**
	 * Flips the card to the front or back
	 */
	public void flip() {
		frontSide = !frontSide;
	}
	
	/**
	 * Returns the suit of the card 
	 * @return suit - the card
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * Returns the corresponding colors of the card suit
	 * @return Color.BLACK or Color.RED
	 */
	public Color getColor() {
		if (suit.equals("Spades") || suit.equals("Clubs")) {
			return Color.BLACK;
		} else {
			return Color.RED;
		}
	}
	
	/**
	 * Returns whether the front of the card is up or not
	 * @return whether or not the front side of the card is up
	 */
	public boolean isVisible() {
		return frontSide;
	}
	
	/**
	 * Returns the card as a String
	 * 1 = Ace
	 * Number in between = two, three, etc. 
	 * 11 = Jack
	 * 12 = Queen
	 * 13 = King
	 * @return the String representation of the card
	 */
	public String toString() {
		// I don't know if this will ever be true
		// this == null ?
		if (this.equals(null)) {
			return "null card";
		}
		
		if (!frontSide)
			return "Back Side";
		
		String cardNumber = "";
		if (number == 1) {
			cardNumber = "Ace";
		} else if (number == 2) {
			cardNumber = "Two";
		} else if (number == 3) {
			cardNumber = "Three";
		} else if (number == 4) {
			cardNumber = "Four";
		} else if (number == 5) {
			cardNumber = "Five";
		} else if (number == 6) {
			cardNumber = "Six";
		} else if (number == 7) {
			cardNumber = "Seven";
		} else if (number == 8) {
			cardNumber = "Eight";
		} else if (number == 9) {
			cardNumber = "Nine";
		} else if (number == 10) {
			cardNumber = "Ten";
		} else if (number == 11) {
			cardNumber = "Jack";
		} else if (number == 12) {
			cardNumber = "Queen";
		} else if (number == 13) {
			cardNumber = "King";
		} else {
			cardNumber = "" + number;
		}
		return cardNumber + " of " + suit;
	}
	
}
