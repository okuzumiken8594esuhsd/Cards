package FindACard;

/**
 * Models a single deck of cards. Does not work with multiple decks 
 * @author okuzumiken8594
 */
public class Deck {
	private Card[] fullDeck;
	
	/**
	 * Makes one full deck of cards
	 * <br>
	 * This will be in the order of the specification of the Card constructor
	 */
	public Deck() {
		fullDeck = new Card[52];
		for (int i = 0; i < fullDeck.length; i++) {
			fullDeck[i] = new Card();
		}
	}
	
	/**
	 * Gives the card at the index
	 * @param index - the index of the deck, 0-51, at which to get the card
	 * @return the card at index
	 */
	public Card getCard(int index) {
		if (index < 0) {
			System.err.println("No such card, too low: " + index);
		}
		if (index > 51) {
			System.err.println("No such card, too high" + index);
		}
		// this will runtime error with the above messages
		return fullDeck[index];
	}
	
	/**
	 * Returns the number of cards in the deck
	 * @return - the number of cards in the deck
	 */
	public int getLength() {
		return fullDeck.length;
	}
	
	/**
	 * Shuffles the deck randomly n times
	 * <br>
	 * defaults to 1000 if n <= 0
	 * @param n the number of times to swap cards
	 */
	public void shuffle(int times) {
		if (times <= 0) {
			times = 1000;
		}
		for (int i = 0; i < times; i++) {
			int one = (int)(Math.random()*52);
			int two = (int)(Math.random()*52);
			swap(one, two);
		}
	}
	
	/**
	 * Shuffles the deck randomly by swapping 2 random cards 1000 times
	 */
	public void shuffle() {
		shuffle(1000);
	}
	
	/**
	 * Faro shuffles the deck
	 * <hr>
	 * should look like https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Faro_shuffles.svg/500px-Faro_shuffles.svg.png
	 */
	public void faroShuffle() {
		Card[] newDeck = new Card[fullDeck.length];
		
		int fullDeckIndex = 0;
		/*
		 * how to fill the new array with the deck faro shuffled
		 * fill the even indices of newDeck with indices 0-25 from fullDeck
		 */
		for (int i = 0; i < fullDeck.length; i+=2) {
			newDeck[i] = fullDeck[fullDeckIndex];
			fullDeckIndex++;
		}
		/*
		 * Then fill the odd indices of newDeck with index 26-51 from fullDeck
		 */
		for (int i = 1; i < fullDeck.length; i+=2) {
			newDeck[i] = fullDeck[fullDeckIndex];
			fullDeckIndex++;
		}
		
		fullDeck = newDeck;
	}
	
	/**
	 * Swaps the two cards
	 * @param one - the first card's index
	 * @param two - the second card's index
	 */
	public void swap(int one, int two) {
		Card temp = fullDeck[one];
		fullDeck[one] = fullDeck[two];
		fullDeck[two] = temp;
	}
	
	/**
	 * Removes the card at the specified index and sets it to null
	 * @return the card that was removed;
	 */
	public Card remove(int index) {
		Card ret = fullDeck[index];
		fullDeck[index] = null;
		return ret;
	}
	
	/**
	 * Flips the card at the index
	 * @param index - the index of the card to flip, from 0-51
	 */
	public void flip(int index) {
		fullDeck[index].flip();
	}
	
	/**
	 * Flips all instances of the target card in the deck
	 * <br>
	 * The target card is the same as a card in the deck if it makes the equals method in Card true
	 * @param c - the card to flip
	 */
	public void flip (Card c) {
		for (int i = 0; i < fullDeck.length; i++) {
			if (fullDeck[i].equals(c)) {
				fullDeck[i].flip();
			}
		}
	}
	
	/**
	 * Resets the deck to brand new as described in the constructor
	 */
	public void reset() {
		fullDeck = new Card[52];
		for (int i = 0; i < fullDeck.length; i++) {
			fullDeck[i] = new Card();
		}
	}
	
	/**
	 * Returns the deck as a list
	 * @return the deck in string form
	 */
	public String toString() {
		String ret = "[";
		for (int i = 0; i < fullDeck.length; i++) {
			ret += fullDeck[i].toString() + ", ";
			if ((i+1) % 13 == 0) {
				ret = ret.substring(0, ret.length()-1) + "\n";
			}
		}
		ret = ret.substring(0, ret.length() - 2) + "]";
		return ret;
	}
}
