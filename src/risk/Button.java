package risk;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Button {
	public static void runButton(Risk game){
		if(GameData.phase<1){
			JOptionPane.showMessageDialog(null, "Error! Cannot draw cards yet.");
		}
		else if(GameData.drawncards>41){	// Shows appropriate error messages depending on the state of the deck.
			JOptionPane.showMessageDialog(null, "Error! All cards have already been drawn.");
		}
		else{
			SinglyLinkedVector initialdeck = new SinglyLinkedVector();	// Creates a new vector for use with the deck of cards.
			initialdeck.initialize(42);	// Initialize it with 42 integers from 0 to 41.
			int range = 41;	// Range is the range which the random card can be drawn from.
			while(GameData.drawncards<42){	// Runs 42 times for each card.
				int card;
				int randomNum = GameData.rand.nextInt((range - 0) + 1) + 0;
				card = initialdeck.removeAtRank(randomNum);	// We remove that card from the vector.
				range--;
				GameData.test.assignTroops(card, GameData.playerturn+1, 1);	// The troops then get assigned depending on who's turn it is and the card drawn.
				game.repaint();	
				GameData.drawncards++;								
				ImageIcon icon1 = new ImageIcon("resources/" + GameData.IMAGES[card]);
				JOptionPane.showMessageDialog(null, "", GameData.PLAYER_NAMES[GameData.playerturn] + " drew:", JOptionPane.INFORMATION_MESSAGE, icon1);	// This shows a popup box with the image of the card and who drew it.
				GameData.playerturn++;
				if(GameData.loops>5 && GameData.playerturn>1){	// If the neutrals have already drawn there cards, only player 1 and 2 can draw.
					GameData.playerturn=0;
				}
				else if(GameData.playerturn>5){			// If not, everyone can draw.
					GameData.playerturn=0;
					GameData.loops++;
				}
			}	// Once all the cards have been drawn, show appropriate messages.
			UI.textArea.append("All cards have now been drawn.\n");
			UI.textArea.append("\n-----------------------------------------------------------------\n");
			UI.textArea.append("\nPlease type in anything to roll dice.\n");
			UI.textField.requestFocus();	// This makes the textField selected again so there's no need to click on it.
		}
	}
}
