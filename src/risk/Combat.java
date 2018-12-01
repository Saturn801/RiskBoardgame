package risk;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Combat {
	public static void runPhase6(Risk game){
		String text = UI.textField.getText();	//assigns input to text variable
		UI.textField.setText("");				//clears text field
		if(!text.equals("")){
			UI.textArea.append("\n>" + text + "\n");		
			if(GameData.countryselected==0) GameData.location = GameData.test.checkValidity(text);	
			if(GameData.countryselected==1) GameData.location2 = GameData.test.checkValidity(text);	
			GameData.phasecheck = GameData.test.checkReinforceInput(text);
			
			if(GameData.countryselected>0&&GameData.phasecheck==1 && GameData.countryselected!=4){
				GameData.countryselected = 0;
				UI.textArea.append("Attack cancelled. Please start over and enter \nanother territory to attack from.\nOr enter 'skip' to advance.\n");
				GameData.location=-3;
				GameData.location2=-3;
			}
			if(GameData.phasecheck==3 && GameData.countryselected!=4){
				UI.textArea.append("Attacking skipped.\n");
				GameData.phase=7; //goes into fortifying phase
				GameData.countryselected = 0;
				if(GameData.conquered==1){	// If a country has been conquered.
					int card;
					int randomNum = GameData.rand.nextInt((GameData.range - 0) + 1) + 0;	// We pick a random number in the range of cards left in the deck.
					int infantry, cavalry, artillery, wildcard;
					card = GameData.Cards.removeAtRank(randomNum);	// We remove that card from the vector.
					GameData.range--;				
					ImageIcon icon1 = new ImageIcon("resources/" + GameData.IMAGES[card]);
					JOptionPane.showMessageDialog(null, "", GameData.PLAYER_NAMES[GameData.playerturn] + " drew:", JOptionPane.INFORMATION_MESSAGE, icon1);	// This shows a popup box with the image of the card and who drew it.
					if(GameData.playerturn==0){		// If it is player 1's turn, insert the card into their vector and display the number of cards they own.
						GameData.player1Cards.insertAtRank(0, GameData.COUNTRY_CARDS[card]);
						infantry = GameData.player1Cards.countOccurences(1);
						cavalry = GameData.player1Cards.countOccurences(2);
						artillery = GameData.player1Cards.countOccurences(3);
						wildcard = GameData.player1Cards.countOccurences(4);
						UI.textArea.append("\nPlayer " + (GameData.playerturn+1) + " now has:\n" + infantry + " infantry cards.\n" + cavalry + " cavalry cards.\n" + artillery + " artillery cards.\n" + wildcard + " wildcards.\n");
					}
					else{	// Same for player 2.
						GameData.player2Cards.insertAtRank(0, GameData.COUNTRY_CARDS[card]);
						infantry = GameData.player2Cards.countOccurences(1);
						cavalry = GameData.player2Cards.countOccurences(2);
						artillery = GameData.player2Cards.countOccurences(3);
						wildcard = GameData.player2Cards.countOccurences(4);
						UI.textArea.append("\nPlayer " + (GameData.playerturn+1) + " now has:\n" + infantry + " infantry cards.\n" + cavalry + " cavalry cards.\n" + artillery + " artillery cards.\n" + wildcard + " wildcards.\n");
					}
				}
				UI.textArea.append("\n-----------------------------------------------------------------\n");
				UI.textArea.append("\nFORTIFYING PHASE! \nPlease enter the country that is going to fortify \nOr 'skip' to end your turn.\n");
			}
			else if((GameData.location==-1 && GameData.countryselected==0)||(GameData.location2==-1 && GameData.countryselected==1)) UI.textArea.append("Invalid input. Please try again.\n");	
			else if((GameData.location==-2 && GameData.countryselected==0)||(GameData.location2==-2 && GameData.countryselected==1)) UI.textArea.append("Ambiguous input. Please try again.\n");	
			else{
				if(GameData.countryselected==0){
					if(GameData.troop_display[GameData.location][0]==GameData.playerturn+1){	//if the player owns the territory
						UI.textArea.append("Please enter the territory you wish to INVADE\nusing " + GameData.COUNTRY_NAMES[GameData.location] + "\nOr enter 'cancel' to start over.\n");
						GameData.countryselected=1;
					}
					else UI.textArea.append("You do not own " + GameData.COUNTRY_NAMES[GameData.location] + "!\n");	//if player doesn't own territory
				}	
				else if(GameData.countryselected==1){
					if((GameData.test.checkAdjacent(GameData.location, GameData.location2))&&(GameData.troop_display[GameData.location2][0]!=GameData.playerturn+1)){
						UI.textArea.append("Please enter number of troops you want to use to attack: " + GameData.COUNTRY_NAMES[GameData.location2] + ".\nOr enter 'cancel' to enter a different country.\n");
						GameData.countryselected=2;
					}
					else if(GameData.troop_display[GameData.location2][0]==GameData.playerturn+1) UI.textArea.append("You own " + GameData.COUNTRY_NAMES[GameData.location2] + "!\n");
					else UI.textArea.append("You cannot attack " + GameData.COUNTRY_NAMES[GameData.location2] + "!\n");
				}
				else if(GameData.countryselected==2){
					if(GameData.phasecheck==2){
						GameData.p1troops=Integer.parseInt(text);
						if(GameData.p1troops>0 && GameData.p1troops<=3 && GameData.p1troops < GameData.troop_display[GameData.location][1]){ 
							UI.textArea.append("Player " + (((GameData.playerturn+1)%2)+1) + ", please enter how many troops you \nwant to defend with.\n");
							GameData.countryselected=3;
						}
						else
							if(GameData.troop_display[GameData.location][1]>3) UI.textArea.append("Invalid number of troops. Please enter a number from 1 to 3.\n");
							else UI.textArea.append("Invalid number of troops. You do not have that many.\n");
					}
					else UI.textArea.append("Invalid input. Please enter the number of troops you wish to attack with or 'cancel'.\n");
				}
				else if(GameData.countryselected==3){
					if(GameData.phasecheck==2){
						GameData.p2troops=Integer.parseInt(text);
						if(GameData.p2troops>0 && GameData.p2troops<=2 && GameData.p2troops<=(GameData.troop_display[GameData.location2][1])){ 
							UI.textArea.append(GameData.test.attackTerritory());
							game.repaint();
							if(GameData.troop_display[GameData.location2][1]==0){
								boolean check = false;
								if(GameData.troop_display[GameData.location2][0]==(((GameData.playerturn+1)%2)+1)){
									GameData.troop_display[GameData.location2][0]=GameData.playerturn+1;
									for(int i = 0;i<GameData.NUM_COUNTRIES;i++){
										if(GameData.troop_display[i][0]==((GameData.playerturn+1)%2)+1) check = true;
									}
									if (!check) GameData.phase=8;
								}
								if(GameData.phase!=8){
									UI.textArea.append("You have conquered " + GameData.COUNTRY_NAMES[GameData.location2] + "!\nPlease enter the number of troops to transfer.\n");
									GameData.countryselected=4;
									GameData.conquered=1;	// When a country is conquered. Set this to 1.
								}
							}
							else UI.textArea.append("\n\nEnter a territory if you want to attack again. \nOr enter 'skip' to advance.\n\n");
							if(GameData.countryselected!=4) GameData.countryselected=0;
						}
						else {
							if(GameData.troop_display[GameData.location2][1]>2) UI.textArea.append("Invalid number of troops. Please enter a number between 1 and 2.\n");
							else UI.textArea.append("Invalid number of troops. You do not have that many.\n");
						}
					}
					else UI.textArea.append("Invalid input. Please enter the number of troops you wish to defend with or 'cancel'.\n");
				}
				else if(GameData.countryselected==4){
					if(GameData.phasecheck==2){
						GameData.p2troops=Integer.parseInt(text);
						if(GameData.troop_display[GameData.location][1]>GameData.p2troops&&GameData.p2troops>0){
							GameData.test.assignTroops(GameData.location,GameData.playerturn+1,-GameData.p2troops);
							GameData.test.assignTroops(GameData.location2,GameData.playerturn+1,GameData.p2troops);
							game.repaint();
							UI.textArea.append("You have moved " + GameData.p2troops + " units to " + GameData.COUNTRY_NAMES[GameData.location2] + "\nfrom " + GameData.COUNTRY_NAMES[GameData.location] + ".\n\nEnter a territory if you want to attack again.\nOr enter 'skip' to advance.\n");
							GameData.countryselected=0;
						}
						else UI.textArea.append("The number entered is not within the valid \nrange...\n");
					}
					else UI.textArea.append("Invalid input. Please enter the number of troops\nyou wish to move.\n");
				}
			}
		}
	}
}