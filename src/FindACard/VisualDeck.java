package FindACard;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * implements the deck class in a visual text-based and image-based manner in a window
 * Only makes one window at a time
 * @author okuzumiken8594
 */
public class VisualDeck extends Deck implements ActionListener {
	/**
	 * The window that will be displayed
	 */
	protected JFrame table;
	
	/**
	 * The panel that will be displayed in the window, can be swapped out
	 * <br>
	 * This one contains the entire deck face up
	 */
	protected JPanel mainPanel;
	
	/**
	 * The menu bar at the top of table, that contains the buttons for actions like shuffling and resetting
	 */
	protected JMenuBar menuBar;
	
	/**
	 * The name of the window that will be displayed
	 */
	protected String tableName;
	
	/**
	 * The length of the deck
	 */
	protected int deckLength;
	
	/**
	 * The number of columns in the JFrame table
	 */
	protected int displayWidth;
	
	/**
	 * The number of rows in the JFrame table, not including the JMenuBar
	 */
	protected int displayHeight;


	// maybe a 2d array of the deck?
	
	/**
	 * Makes a visual Deck
	 */
	public VisualDeck() {
		super();
		deckLength = super.getLength();
		displayWidth = 13;
		displayHeight = 4;
		tableName = "Card Table";
	}
	
	/**
	 * Makes a visual deck with the given name
	 * Copies the implementation of the default constructor but sets the name of the window to the provided name
	 * @param name - the name of the window
	 */
	public VisualDeck(String name) {
		super();
		deckLength = super.getLength();
		displayWidth = 13;
		displayHeight = 4;
		if (name != null)
			tableName = name;
		else
			tableName = "Card Table";
	}
	
	/**
	 * Displays the window at the given coordinates <br>
	 * Creates a new window
	 * @param x - the x coordinate to display the window at
	 * @param y - the y coordinate to display the window at
	 */
	public void display(int x, int y) {
		table = new JFrame(tableName);
		table.setLayout(new CardLayout());
		// JPanel to hold main cards
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(displayHeight, displayWidth));
//		addTextPanes();
		addImagePanes();
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
	 * shows the visual deck with card names in text, placed at 0, 0 <br>
	 * Creates a new window
	 */
	public void display() {
		this.display(0,0);
	}
		
	/**
	 * redraws the cards to the same window, keeping the window's current position and size <br>
	 * Does not create a new window
	 */
	public void redraw() {
		// save the size of the table
		int x = table.getX();
		int y = table.getY();
		
		// removing everything from the inside the table, the Content Pane
		table.getContentPane().removeAll();
		
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(displayHeight, displayWidth));
		
		// table holds text of cards, not cards
		// this loop should reupdate the cards in the table
		addTextPanes();
		table.add(mainPanel);
		
		table.setJMenuBar(menuBar);
		table.revalidate();
		table.repaint();
		table.setLocation(x, y);
		table.setVisible(true);
	}
	
	/**
	 * Resets the deck, keeping table configurations from before
	 * <br>
	 * The deck is set so that it is in the order it was when it was new as defined in Deck -> Card
	 */
	public void reset() {
		// resetting the underlying deck
		super.reset();
		deckLength = super.getLength();
		
		// save old table configurations
		int x = table.getX();
		int y = table.getY();
		int width = table.getWidth();
		int height = table.getHeight();

		table.getContentPane().removeAll();
		
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(displayHeight, displayWidth));
		addTextPanes();
		table.add(mainPanel);
		
		table.setJMenuBar(menuBar);
		table.pack();
		table.setBounds(x, y, width, height);
		table.setVisible(true);
	}
	
	/**
	 * Converts the card in the deck at index i into a JTextPane with text
	 * <br>
	 * Modified slightly from: <br>
	 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
	 * @param i the card index in fullDeck
	 * @return the text pane with the name of the card in the given index in Deck
	 */
	protected JTextPane createTextPane(int i) {
		// The text pane that will be added to the grid in VisualDeck.table 
        JTextPane textPane = new JTextPane();
    
        StyledDocument doc = textPane.getStyledDocument();
        MutableAttributeSet attributeSet = textPane.getInputAttributes();
        
        StyleConstants.setForeground(attributeSet, super.getCard(i).getColor());
        doc.setCharacterAttributes(0, doc.getLength(), attributeSet, true);
        

        try {
            doc.insertString(doc.getLength(), super.getCard(i).toString(), attributeSet);
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
            ble.printStackTrace();
        }
        textPane.setEditable(false);

        return textPane;
    }
	
	/**
	 * Converts the card in the deck at index i into a JTextPane with text
	 * <br>
	 * Modified slightly from: <br>
	 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
	 * @param i the card index in fullDeck
	 * @return the text pane with the image of the card in the given index in Deck
	 */
	protected ImageIcon createImagePane(int i) {
    	ImageIcon cardImage = new ImageIcon(fullDeck[i].toImage());
    	System.out.println(cardImage.toString());

        return cardImage;
    }
	
	/**
	 * Adds the JTextPane from this.createTextPane(int) to the JPanel mainPanel
	 */
	protected void addTextPanes() {
		for (int i = 0; i < deckLength; i++) {
			JTextPane t;
			
			// make it either a text or image pane
			t = createTextPane(i);
			
			// putting the text into a panel to have a border around each
			JPanel p = new JPanel();
			p.add(t);
			p.setBackground(Color.WHITE);
			
			// getting the suit to set the color of the border
			Color border = super.getCard(i).getColor();
			
			p.setBorder(BorderFactory.createLineBorder(border));
			mainPanel.add(p);
		}
	}
	
	/**
	 * Adds the JTextPane from this.createTextPane(int) to the JPanel mainPanel
	 */
	protected void addImagePanes() {
		for (int i = 0; i < deckLength; i++) {
			ImageIcon icon;
			
			// make it either a text or image pane
			icon = createImagePane(i);
			
			// putting the text into a panel to have a border around each
			JPanel p = new JPanel();
			JLabel l = new JLabel(icon);
			p.add(l);
			p.setBackground(Color.WHITE);
			
			// getting the suit to set the color of the border
			Color border = super.getCard(i).getColor();
			
			p.setBorder(BorderFactory.createLineBorder(border));
			mainPanel.add(p);
		}
	}
	
	/**
	 * Returns a menu bar that holds buttons to <br>
	 * - shuffle <br>
	 * - faro shuffle <br>
	 * - reset <br>
	 * @return the menu bar containing reset and other functions
	 */
	protected JMenuBar makeMenuBar() {
		JMenuBar ret = new JMenuBar();
		
		ret.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JMenuItem shuffleButton = new JMenuItem("Shuffle");
		shuffleButton.setActionCommand(shuffleButton.getText());
		shuffleButton.addActionListener(this);
		ret.add(shuffleButton);
		
		JMenuItem faroShuffleButton = new JMenuItem("Faro Shuffle");
		faroShuffleButton.setActionCommand(faroShuffleButton.getText());
		faroShuffleButton.addActionListener(this);
		ret.add(faroShuffleButton);
		
		JMenuItem resetButton = new JMenuItem("Reset");
		resetButton.setActionCommand(resetButton.getText());
		resetButton.addActionListener(this);
		ret.add(resetButton);
		
		return ret;
	}
	
	/**
	 * Handles the actions as defined in makeMenuBar
	 * @param e The action from clicking the button, likely
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] actions = {"Shuffle", "Faro Shuffle", "Reset"};
		if (e.getActionCommand().equals(actions[0])) {
			super.shuffle();
			this.redraw();
		}
		else if (e.getActionCommand().equals(actions[1])) {
			super.faroShuffle();
			this.redraw();
		}
		else if (e.getActionCommand().equals(actions[2])) {
			this.reset();
		}
	}

}
