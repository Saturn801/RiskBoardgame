package risk;

import risk.Risk;

public class InitialReinforcements{
	public static void runPhase2(Risk game){
		game.repaint();
		String text = UI.textField.getText();	//assigns input to text variable
		UI.textField.setText("");				//clears text field
		if(!text.equals("")){
			UI.textArea.append("\n>" + text + "\n");		//if input isn't null, prints input
			if(GameData.countryselected==0)			//only assigns text to location if it's referring to a country
				GameData.location = GameData.test.checkValidity(text);	//checks if input is unambiguously one country
			GameData.phasecheck = GameData.test.checkReinforceInput(text);	//checks if troop text input is valid
			int numtroops=0;
			if(GameData.location==-1 && GameData.countryselected==0) UI.textArea.append("Invalid input. Please try again.\n");	//-1 is return value in input is invalid
			else if(GameData.location==-2 && GameData.countryselected==0) UI.textArea.append("Ambiguous input. Please try again.\n");	//-2 is return value if input is ambiguous
			else{
				if(GameData.neutralturn==0){	//if it's a player's turn
					if(GameData.troop_display[GameData.location][0]==GameData.playerturn+1){	//if player owns territory
						if(GameData.countryselected==0){	//if a country had not been previously selected the option below is given
							UI.textArea.append("Please enter number of troops to add to:\n" + GameData.COUNTRY_NAMES[GameData.location] + "\nOr enter 'cancel' to enter a different country.\n");
							GameData.countryselected=1;	
						}
						else if(GameData.countryselected==1){	//runs if a country has been selected previously
							if(GameData.phasecheck==2){	
								numtroops=Integer.parseInt(text);
								if(numtroops>0 && numtroops<=3-GameData.numentered){	//if the number entered is in a valid range
									UI.textArea.append("Player " + (GameData.playerturn+1) + " reinforced " + GameData.COUNTRY_NAMES[GameData.location] + " with " + numtroops + " troops.\n");
									GameData.test.assignTroops(GameData.location, GameData.playerturn+1, numtroops);	//assigns however many troops to territory
									game.repaint();
									GameData.test.updateReinforcements(-numtroops);	//subtracts the number of troops entered from initial reinforcements
									if (GameData.test.getReinforcements()%3==0){	//if player has placed three armies
										GameData.neutralturn++;	//goes to first neutral phase
										UI.textArea.append("\nAssign an army to Neutral 1:\n");
										GameData.numentered=0;	//resets these variables for the next player
										numtroops=0;
									}
									//two different if statements to print plural or singular of reinforcement(s) if player has troops left to place
									else if (GameData.test.getReinforcements()%3==2) UI.textArea.append("\nYou have 2 reinforcements left to place.\nPick a territory to reinforce!");
									else UI.textArea.append("\nYou have 1 reinforcement left to place.\nPick a territory to reinforce!");
									GameData.countryselected=0;	//resets to no country selected
									GameData.numentered+=numtroops;	
								}
								else
									UI.textArea.append("Invalid number of troops. Please enter the number of troops you wish to add or 'cancel'.\n");
							}
							else if(GameData.phasecheck==1){
								UI.textArea.append("Reinforce cancelled.\nPlease enter another territory\n");
								GameData.countryselected=0;
							}
							else{
								UI.textArea.append("Invalid input. Please enter the number of troops you wish to add or 'cancel'.\n");
							}
						}
					}
					else UI.textArea.append("You do not own " + GameData.COUNTRY_NAMES[GameData.location] + "!\n");	//if player doesn't own territory
				}
				else if(GameData.neutralturn>0){	//else if it is neutral phase
					if(GameData.troop_display[GameData.location][0]==(GameData.neutralturn+2)){	//if territory entered is owned by neutral
						UI.textArea.append(GameData.COUNTRY_NAMES[GameData.location] + " reinforced for Neutral " + (GameData.neutralturn) + ".\n\n");
						GameData.test.assignTroops(GameData.location, GameData.neutralturn+2, 1);	//same operations as in player assignment
						game.repaint();
						GameData.neutralturn=(GameData.neutralturn+1)%5;	//advances to next neutral phase. if neutral 4s phase, goes back to "player phase"
						if(GameData.neutralturn!=0) UI.textArea.append("Assign an army to Neutral " + GameData.neutralturn + ":\n");	//prints if it is still a neutral phase
						else{	//otherwise it is player phase again
							if(GameData.test.getReinforcements()>0){
								GameData.playerturn = (GameData.playerturn+1)%2;	//advances to next player
								UI.textArea.append("\nPlayer " + (GameData.playerturn%2-(-1)) + ", it is your turn to reinforce:\n");
							}
							else{
								UI.textArea.append("ALL REINFORCEMENTS HAVE BEEN PLACED!\n");
								UI.textArea.append("\n-----------------------------------------------------------------\n");
								GameData.phase++;
							}
						}
					}
					else UI.textArea.append("Neutral " +GameData.neutralturn + " does not own " + GameData.COUNTRY_NAMES[GameData.location] + "!\n");
				}
			}
		}
		else
			UI.textArea.append("\nYou have 3 reinforcements left to place.\n");

	}
}