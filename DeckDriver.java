package FindACard;

public class DeckDriver {

	public static void testDeckComparison() {
		System.out.println("testing deck comparison");
		Deck d = new Deck();
		System.out.println(d);
		System.out.println("Comparing the " + d.getCard(0) + " and " + d.getCard(15) + ":");
		System.out.println(d.getCard(0).compareTo(d.getCard(15)));
		System.out.println("Comparing the " + d.getCard(15) + " and " + d.getCard(0) + ":");
		System.out.println(d.getCard(15).compareTo(d.getCard(0)));
		System.out.println("Shuffling...");
		d.shuffle();
		System.out.println("Comparing the " + d.getCard(0) + " and " + d.getCard(15) + ":");
		System.out.println(d.getCard(0).compareTo(d.getCard(15)));
		System.out.println("Comparing the " + d.getCard(15) + " and " + d.getCard(0) + ":");
		System.out.println(d.getCard(15).compareTo(d.getCard(0)));
		System.out.println(d);
		
		// random comparisons
		int card1 = (int)(Math.random() * 52);
		int card2 = (int)(Math.random() * 52);
		System.out.println("Comparing the " + d.getCard(card1) + " and " + d.getCard(card2) + ":");
		System.out.println(d.getCard(card1).compareTo(d.getCard(card2)));
		
		// random comparisons, testing ignore suit
		card1 = (int)(Math.random() * 52);
		card2 = (int)(Math.random() * 52);
		System.out.println("Comparing the " + d.getCard(card1) + " and " + d.getCard(card2) + ", ignoring suit:");
		System.out.println(d.getCard(card1).compareToIgnoreSuit(d.getCard(card2)));
	}
	
	public static void testVisualDeck() {
		VisualDeck vD = new VisualDeck();
//		for(int i = 0; i < 7; i++)
//			vD.faroShuffle();
		vD.display(200, 300);
	}
	
	public static void main(String[] args) {
//		testDeckComparison();
		testVisualDeck();
	}

}
