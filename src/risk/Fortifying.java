package risk;

public class Fortifying {
	public static void runPhase7(Risk game){
		String text = UI.textField.getText();	
		UI.textField.setText("");
		GameData.adjacent=true;
		
		if(!text.equals("")){
			UI.textArea.append("\n>" + text + "\n");		
			if(GameData.countryselected==0){
				GameData.location = GameData.test.checkValidity(text);
				GameData.location2 = GameData.test.checkValidity(text);
			}
			if(GameData.countryselected==1)	//country you wish to fortify 
			{
				GameData.location2 = GameData.test.checkValidity(text);
				if(GameData.location2 >= 0)
					GameData.adjacent = isConnected(GameData.location, GameData.location2);	// Changed from adjacent to connected countries. Using the isConnected functions.
			}
			GameData.phasecheck = GameData.test.checkReinforceInput(text);
			int numtroops=0;
			if(GameData.phasecheck==3 && GameData.countryselected==0){
				if(GameData.playerturn==0)
					GameData.playerturn=1;
				else
					GameData.playerturn=0;
				
				UI.textArea.append("Fortifying skipped.\n\n-----------------------------------------------------------------\n\nTURN OVER\n"); 
				UI.textArea.append("\n-----------------------------------------------------------------\n");
				UI.textArea.append("\nIt is now Player " + (GameData.playerturn+1) + "'s turn.\n");
				game.repaint();
				GameData.phase=4;
				UI.textArea.append("\nPlease type in anything to start turn.\n");
				UI.textArea.append("\n-----------------------------------------------------------------\n");
			}
			else if(GameData.phasecheck==1 && GameData.countryselected>0){
				UI.textArea.append("Fortify cancelled.\nPlease enter another territory \nOr enter 'skip' if you do not wish to fortify.\n");
				GameData.countryselected=0;
			}
			else if(GameData.location==-1 && GameData.countryselected==0) UI.textArea.append("Invalid input. Please try again.\n");	
			else if(GameData.location==-2 && GameData.countryselected==0) UI.textArea.append("Ambiguous input. Please try again.\n");	
			else if(GameData.location2==-1 && GameData.countryselected==1) UI.textArea.append("Invalid input. Please try again.\n");	
			else if(GameData.location2==-2 && GameData.countryselected==1) UI.textArea.append("Ambiguous input. Please try again.\n");	
			else if(GameData.adjacent == false) UI.textArea.append("The country is not connected to " +GameData.COUNTRY_NAMES[GameData.location]+ ". Please enter another country. \n");	
			
			else{
				if(GameData.troop_display[GameData.location][0]==GameData.playerturn+1 && GameData.troop_display[GameData.location2][0]==GameData.playerturn+1){	//if the player owns the territory
					if(GameData.countryselected==0){
						UI.textArea.append("Please enter the country you wish to fortify from:\n" + GameData.COUNTRY_NAMES[GameData.location] + "\nOr enter 'cancel' to enter a different country.\n");
						GameData.countryselected=1;
					}
					else if(GameData.countryselected==1){
						UI.textArea.append("Please enter the number of troops you wish to \ntransfer to " + GameData.COUNTRY_NAMES[GameData.location2] + " from " + GameData.COUNTRY_NAMES[GameData.location] + "\nOr enter 'cancel' to enter a different country.\n");
						GameData.countryselected=2;
					}
					else if(GameData.countryselected==2){
						if(GameData.phasecheck==2){
							numtroops=Integer.parseInt(text);
							if(numtroops>0 && numtroops<GameData.troop_display[GameData.location][1]){ 
								UI.textArea.append("Player " + (GameData.playerturn+1) + " has fortified " + GameData.COUNTRY_NAMES[GameData.location2] + " with\n" + numtroops + " troops from " + GameData.COUNTRY_NAMES[GameData.location] + ".\n");
								GameData.test.assignTroops(GameData.location2, GameData.playerturn+1, numtroops);
								GameData.test.assignTroops(GameData.location, GameData.playerturn+1, -numtroops);
								game.repaint();	
								GameData.countryselected=0;
								GameData.phase=4;
								if(GameData.playerturn==0)
									GameData.playerturn=1;
								else
									GameData.playerturn=0;
								UI.textArea.append("\n-----------------------------------------------------------------\n");
								UI.textArea.append("\nTURN OVER\n"); 
								UI.textArea.append("\n-----------------------------------------------------------------\n");
								UI.textArea.append("\nIt is now Player " + (GameData.playerturn+1) + "'s turn.\n");
								game.repaint();
								UI.textArea.append("\nPlease type anything to start turn.\n");
								UI.textArea.append("\n-----------------------------------------------------------------\n");
							}
							else
								UI.textArea.append("Invalid number of troops. \nPlease enter the number of troops you wish to transfer \nOr 'cancel'.\n");
						}
						else{
							UI.textArea.append("Invalid input. Please enter the number of troops you wish to transfer or 'cancel'.\n");
						}
					}
				}
				else UI.textArea.append("You do not own " + GameData.COUNTRY_NAMES[GameData.location2] + "!\n");	//if player doesn't own territory
			}

		}
	}
	
	public static boolean isConnected (int fromCountry, int toCountry, boolean[] countriesChecked) {
		int[] neighbours;
		int currentCountry;
		boolean found = false;
		if (GameData.troop_display[fromCountry][0] == GameData.troop_display[toCountry][0])  {
			if (GameData.test.checkAdjacent(fromCountry,toCountry)) {
				found = true;
			}
			else {
				neighbours = GameData.ADJACENT[fromCountry];
				countriesChecked[fromCountry] = true;
				for (int i=0; (i<neighbours.length) && (!found); i++) {
					currentCountry = neighbours[i];
					if ( (GameData.troop_display[currentCountry][0] == GameData.troop_display[toCountry][0]) && (!countriesChecked[currentCountry]) ) {
						found = isConnected(currentCountry,toCountry,countriesChecked);
					}
				}
			}
		}		
		return found;
	}
	
	public static boolean isConnected (int fromCountry, int toCountry) {
		boolean [] countriesChecked = new boolean[GameData.NUM_COUNTRIES];
		for (int i=0; i<GameData.NUM_COUNTRIES; i++) {
			countriesChecked[i] = false;
		}
		countriesChecked[fromCountry] = true;
		return isConnected (fromCountry, toCountry, countriesChecked);
	}
}