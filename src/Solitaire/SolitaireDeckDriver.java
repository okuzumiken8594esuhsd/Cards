package Solitaire;

public class SolitaireDeckDriver {

	private static void testSolitaire() {
		SolitaireDeck klon = new SolitaireDeck();
		SolitaireDeck nonKlon = new SolitaireDeck(false);
		klon.display();
		nonKlon.display(500, 0);
	}
	
	public static void main(String[] args) {
		testSolitaire();
	}

}
