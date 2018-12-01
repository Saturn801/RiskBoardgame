package risk;

public class MainReinforcements {
	public static void runPhase5(Risk game){
		String text = UI.textField.getText();	
		UI.textField.setText("");				
		if(!text.equals("")){
			UI.textArea.append("\n>" + text + "\n");		
			if(GameData.countryselected==0)
				GameData.location = GameData.test.checkValidity(text);	
		GameData.phasecheck = GameData.test.checkReinforceInput(text);
			int numtroops=0;
			if(GameData.location==-1 && GameData.countryselected==0) UI.textArea.append("Invalid input. Please try again.\n");	
			else if(GameData.location==-2 && GameData.countryselected==0) UI.textArea.append("Ambiguous input. Please try again.\n");	
			else{
				if(GameData.troop_display[GameData.location][0]==GameData.playerturn+1){	//if the player owns the territory
					if(GameData.countryselected==0){
						UI.textArea.append("Please enter number of troops to add to:\n" + GameData.COUNTRY_NAMES[GameData.location] + "\nOr enter 'cancel' to enter a different country.\n");
						GameData.countryselected=1;
					}
					else if(GameData.countryselected==1){
						if(GameData.phasecheck==2){
							numtroops=Integer.parseInt(text);
							if(numtroops>0 && numtroops<=GameData.test.getReinforcements()){ 
								UI.textArea.append("Player " + (GameData.playerturn+1) + " reinforced " + GameData.COUNTRY_NAMES[GameData.location] + " with " + numtroops + " troops.\n");
								GameData.test.assignTroops(GameData.location, GameData.playerturn+1, numtroops);	
								game.repaint();	
								GameData.test.updateReinforcements(-numtroops);							
								GameData.countryselected=0;
								UI.textArea.append("You have " + GameData.test.getReinforcements() + " reinforcements remaining.\n");
								if(GameData.test.getReinforcements() == 0){
									GameData.phase++; //goes into the attack phase
									UI.textArea.append("\n-----------------------------------------------------------------\n");
									UI.textArea.append("\nCOMBAT PHASE!\n\nEnter the country you wish to attack FROM \nOr 'skip' if you do not wish to attack.\n");
									GameData.conquered=0;
								}
							}
							else UI.textArea.append("Invalid number of troops. Please enter the number of troops you wish to add or 'cancel'.\n");
						}
						else if(GameData.phasecheck==1){
							UI.textArea.append("Reinforce cancelled. Please enter another territory\n");
							GameData.countryselected=0;
						}
						else UI.textArea.append("Invalid input. Please enter the number of troops you wish to add or 'cancel'.\n");
					}
				}
				else UI.textArea.append("You do not own " + GameData.COUNTRY_NAMES[GameData.location] + "!\n");	//if player doesn't own territory
			}

		}
	}
}
