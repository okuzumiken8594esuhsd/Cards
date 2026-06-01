package Solitaire;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import FindACard.*;

/**
 * Makes a visual deck to play Solitaire, based off of Visual Deck
 * @author okuzumiken8594
 * @version May 2026
 */
public class SolitaireDeck extends VisualDeck implements ActionListener {

	/**
	 * Whether this game is klondike solitaire where you draw 3.
	 * Otherwise it is draw 1.
	 */
	protected boolean klondike;
	/**
	 * The index of the card at the top of the deck
	 */
	protected int currentCard;
	
	/**
	 * Makes a solitaire deck in the klondike style
	 */
	public SolitaireDeck() {
		super();
		klondike = true;
		currentCard = 0;
		super.displayWidth = 8;
		super.displayHeight = 14;
	}
	
	/**
	 * Makes a solitaire deck in the klondike style with the given name
	 * @param name - the name of the window
	 */
	public SolitaireDeck(String name) {
		super(name);
		klondike = true;
		super.displayWidth = 8;
		super.displayHeight = 14;
	}
	
	/**
	 * Makes a solitaire deck that might be klondike
	 * @param klondike - whether this game of solitaire is klondike or not
	 */
	public SolitaireDeck(boolean klondike) {
		super();
		this.klondike = klondike;
		currentCard = 0;
		super.displayWidth = 8;
		super.displayHeight = 14;
	}
	
	/**
	 * Makes a solitaire deck that might be klondike with the given name
	 * @param klondike - whether this game of solitaire is klondike or not
	 * @param name - the name of the window
	 */
	public SolitaireDeck(boolean klondike, String name) {
		super(name);
		this.klondike = klondike;
		super.displayWidth = 8;
		super.displayHeight = 14;
	}
	
	/**
	 * Deals the three cards of of the top of the deck
	 * <br>
	 * Or one if it is not klondike
	 */
	public void deal() {
		if (klondike) {
			// deal 3 cards if the deck can deal them (currentCard +3 -1; taking 3 cards off puts you exactly at fullDeck.length)
			if (!(currentCard + 3 - 1 >= fullDeck.length)) {
				// adds 3 cards to the rightmost column
				addCard(fullDeck[currentCard], super.displayWidth - 1);
				currentCard++;
				addCard(fullDeck[currentCard], super.displayWidth - 1);
				currentCard++;
				addCard(fullDeck[currentCard], super.displayWidth - 1);
				currentCard++;
			} else if (currentCard >= fullDeck.length){
				System.err.println("Tried to draw cards out of bounds of fullDeck");
			}
		} else {
			if (!(currentCard >= fullDeck.length)) {
				// adds one card to the rightmost column
				addCard(fullDeck[currentCard], super.displayWidth - 1);
				currentCard++;
			} else {
				System.err.println("Tried to draw cards out of bounds of fullDeck");
			}
		}
	}

	/**
	 * Displays the window at 0, 0 on the monitor <br>
	 * does nothing to the overridden method
	 */
	@Override
	public void display() {
		this.display();
	}
	
	/** Displays the window at the given coordinates <br>
	 * Creates a new window
	 * @param x the x coordinate to display the window at
	 * @param y the y coordinate to display the window at
	 */
	@Override
	public void display(int x, int y) {
		table = new JFrame(tableName);
		table.setLayout(new CardLayout());
		// JPanel to hold main cards
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(displayHeight, displayWidth));
		addTextPanes();
		table.add(mainPanel);
				
		menuBar = makeMenuBar();
		table.setJMenuBar(menuBar);
		table.pack();
		table.setLocation(new Point(x, y));
		table.setMinimumSize(new Dimension(table.getWidth(), table.getHeight()));
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setVisible(true);
	}
	
	/**
	 * Adds the text panes in the style of solitaire
	 */
	@Override
	protected void addTextPanes() {
		// make the initial layout
		// for every column in the panel, except the last
		for (int column = 0; column < super.displayWidth - 1; column++) {
			// for every row in these columns
			for (int row = 1; row < column + 1; row++) {
				addCard(fullDeck[currentCard], column);
			}
		}
		
		
	}
	
	/**
	 * Returns a menu bar that holds buttons to <br>
	 * - shuffle <br>
	 * - faro shuffle <br>
	 * - reset <br>
	 * @return the menu bar containing reset and other functions
	 */
	@Override
	protected JMenuBar makeMenuBar() {
		JMenuBar ret = new JMenuBar();
		
		ret.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JMenuItem drawButton = new JMenuItem("Draw");
		drawButton.setActionCommand(drawButton.getText());
		drawButton.addActionListener(this);
		ret.add(drawButton);
		
		JMenuItem resetButton = new JMenuItem("Reset");
		resetButton.setActionCommand(resetButton.getText());
		resetButton.addActionListener(this);
		ret.add(resetButton);
		
		return ret;
	}
	
	/**
	 * Adds the card to the column at the location, if the card can go there
	 * @param c the card from the deck
	 * @param row the column to add the card to
	 */
	protected void addCard(Card c, int column) {
		// error checking
		if (c == null) {
			System.err.println("null card");
			throw new NullPointerException("null card");
		}
		if (column > displayWidth) {
			System.err.println("Out of bounds, column = " + column);
			throw new ArrayIndexOutOfBoundsException(column);
		}
		
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] actions = {"Draw", "Reset"};
		if (e.getActionCommand().equals(actions[0])) {
			this.deal();
		} else if (e.getActionCommand().equals(actions[1])) {
			this.reset();
		}
	}

}
