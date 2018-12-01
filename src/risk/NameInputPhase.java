package risk;

public class NameInputPhase {
	public static void runPhase0(){
		String text = UI.textField.getText();	// stores the text into a string variable.
		UI.textField.setText("");	// removes the text from the text field.
		GameData.PLAYER_NAMES[GameData.enteredplayers]=text;	// stores the entered text into the PLAYER_NAMES array.
		if(text.length()<20){
			UI.textArea.append("Player " + (GameData.enteredplayers+1) + ": " + GameData.PLAYER_NAMES[GameData.enteredplayers] + "\n");	// outputs the player number and name to the text area.
			GameData.enteredplayers++;
		}
		else
			UI.textArea.append("Name \"" +text+ "\" too long.\nPlease enter a shorter name.\n\n");
		if(GameData.enteredplayers>=2){	// if two names have been entered, move onto the next phase.
			GameData.phase++;
			UI.textArea.append("\n-----------------------------------------------------------------\n");
			UI.textArea.append("\nPlease draw cards.\n");
		}
	}
}
