package risk;

public class ReinforcementCalculation {
	public static void runPhase4(){
		int infantry, cavalry, artillery, wildcard;
		if(GameData.countryselected==0){
			//Territory count and extra reinforcements
			int i, numOfTerritories = 0, x;
			for(i = 0; i < 42; i++){
				if(GameData.troop_display[i][0] == GameData.playerturn + 1){
					numOfTerritories++;
				}
			}
			GameData.test.updateReinforcements(numOfTerritories/3);
			UI.textArea.append("\nBased on the number of territories you own,\nyou get " + numOfTerritories/3 + " extra reinforcements!\n");
	
			//Checks if player has the whole continent and gives the extra reinforcements
			//North America
			boolean check = true;
			for(i = 0; i < 9; i++){
				if(GameData.troop_display[i][0] != GameData.playerturn + 1){
					check = false;
				}
			}
			if(check == true){
				GameData.test.updateReinforcements(5);
				UI.textArea.append("\nYou have conquered all of North America! \nYou get 5 extra reinforcements!\n");
			}
	
	
			//Europe
			check = true;
			for(i = 9; i < 16; i++){
				if(GameData.troop_display[i][0] != GameData.playerturn + 1){
					check = false;
				}
			}
			if(check == true){
				GameData.test.updateReinforcements(5);
				UI.textArea.append("\nYou have conquered all of Europe! \nYou get 5 extra reinforcements!\n");
			}
	
	
			//Asia
			check = true;
			for(i = 16; i < 28; i++){
				if(GameData.troop_display[i][0] != GameData.playerturn + 1){
					check = false;
				}
			}
			if(check == true){
				GameData.test.updateReinforcements(7);
				UI.textArea.append("\nYou have conquered all of Asia! \nYou get 7 extra reinforcements!\n");
			}
	
			//Australia
			check = true;
			for(i = 28; i < 32; i++){
				if(GameData.troop_display[i][0] != GameData.playerturn + 1){
					check = false;
				}
			}
			if(check == true){
				GameData.test.updateReinforcements(2);
				UI.textArea.append("\nYou have conquered all of Australia! \nYou get 2 extra reinforcements!\n");
			}
	
			//South America
			check = true;
			for(i = 32; i < 36; i++){
				if(GameData.troop_display[i][0] != GameData.playerturn + 1){
					check = false;
				}
			}
			if(check == true){
				GameData.test.updateReinforcements(2);
				UI.textArea.append("\nYou have conquered all of South America! \nYou get 2 extra reinforcements!\n");
			}
	
			//Africa
			check = true;
			for(i = 36; i < 42; i++){
				if(GameData.troop_display[i][0] != GameData.playerturn + 1){
					check = false;
				}
			}
			if(check == true){
				GameData.test.updateReinforcements(3);
				UI.textArea.append("\nYou have conquered all of Africa! \nYou get 3 extra reinforcements!\n");
			}
	
			if(GameData.test.getReinforcements()<3){
				x = 3-GameData.test.getReinforcements();
				GameData.test.updateReinforcements(x);
			}
			
			UI.textArea.append("\nPlayer " + (GameData.playerturn+1) + "'s reinforcement count: " + GameData.test.getReinforcements() + "\n");
			GameData.countryselected++;
		}
		
		if(GameData.countryselected==1&&((GameData.playerturn==0&&GameData.player1Cards.size()>=3)||(GameData.playerturn==1&&GameData.player2Cards.size()>=3))){
			if(GameData.playerturn == 0){ //player 1s turn
				infantry = GameData.player1Cards.countOccurences(1);
				cavalry = GameData.player1Cards.countOccurences(2);
				artillery = GameData.player1Cards.countOccurences(3);
				wildcard = GameData.player1Cards.countOccurences(4);
			}
			else { //player 2s turn
				infantry = GameData.player2Cards.countOccurences(1);
				cavalry = GameData.player2Cards.countOccurences(2);
				artillery = GameData.player2Cards.countOccurences(3);
				wildcard = GameData.player2Cards.countOccurences(4);
			}
			
			String text = UI.textField.getText();	//assigns input to text variable
			UI.textField.setText("");				//clears text field
			if((GameData.playerturn==0&&GameData.player1Cards.size()<5)||(GameData.playerturn==1&&GameData.player2Cards.size()<5)) UI.textArea.append("\nDo you want to trade cards? Type in 'view' to view your cards, 'skip' if you do not wish to trade or input the first letter of the insignias of the three cards you wish to trade.\n\n");
			else if(!text.equals("skip")) UI.textArea.append("You have five cards, so you must trade in your\ncards. Type in 'view' to view your cards or\nenter the first letter of the insignias of the cards\nyou wish to exchange.\n\n");
			if (wildcard>0) UI.textArea.append("If you wish to use a wild card, please type in the\ninsignia of the card you wish to use it as.\n\n");
			if(!text.equals("")){
				UI.textArea.append("\n>" + text + "\n\n");	
				if(text.equalsIgnoreCase("skip")){
					if((GameData.playerturn==0&&GameData.player1Cards.size()<5)||(GameData.playerturn==1&&GameData.player2Cards.size()<5)){
						UI.textArea.append("\nNo cards traded!\n");
						UI.textArea.append("\nPlayer " + (GameData.playerturn+1) + "'s total reinforcement count: " + GameData.test.getReinforcements() + "\n");
						UI.textArea.append("\n\nREINFORCEMENT PHASE!\n Pick a country and reinforce!\n");
						GameData.countryselected=0;
						GameData.phase++;
					}
					else UI.textArea.append("You have five cards! You must trade in a card!\n\n");
				}
				else if(text.equalsIgnoreCase("view")){
					UI.textArea.append("You have:\n" + infantry + " infantry card(s).\n" + cavalry + " cavalry card(s).\n" + artillery + " artillery card(s).\n" + wildcard + " wildcard(s).\n");
					if((GameData.playerturn==0&&GameData.player1Cards.size()<5)||(GameData.playerturn==1&&GameData.player2Cards.size()<5)) UI.textArea.append("\nDo you want to trade cards? Type in 'skip' if you do not wish to trade or input the first letter of the insignias of the three cards you wish to trade.\n");
					else UI.textArea.append("\nPlease enter the first letters of the insignias\nof the cards you wish to exchange.\n");
				}
				else{
					text = text.replaceAll("\\s","");
					text = text.toLowerCase();
					if (text.length() == 3){
						//if all three characters are the same
						if(text.charAt(0)==text.charAt(1)&&text.charAt(1)==text.charAt(2)){
							switch(text.charAt(0)){
								case 'a':
									if((artillery+wildcard)>=3){
										Cards.sameTypeCards(3);
										GameData.countryselected=2;
									}
									else UI.textArea.append("You do not have enough cards to trade in.\n");
									break;
								case 'c':
									if((cavalry+wildcard)>=3){
										Cards.sameTypeCards(2);
										GameData.countryselected=2;
									}
									else UI.textArea.append("You do not have enough cards to trade in.\n");
									break;
								case 'i':
									if((infantry+wildcard)>=3){
										Cards.sameTypeCards(1);
										GameData.countryselected=2;
									}
									else UI.textArea.append("You do not have enough cards to trade in.\n");
									break;
								default:
									UI.textArea.append("Invalid input! Accepted types are \"I\", \"C\", \"A\".\n");
							}
						}
						else if(text.charAt(0)!=text.charAt(1)&&text.charAt(0)!=text.charAt(2)&&text.charAt(1)!=text.charAt(2)){
							if (Cards.differentCardValidity(text)){
								if(Cards.checkDifferentCards(artillery, cavalry, infantry, wildcard)){
									Cards.differentTypeCards(artillery, cavalry, infantry, wildcard);
									GameData.countryselected=2;
								}
								else UI.textArea.append("You do not have enough cards to trade in.\n");
							}
							else UI.textArea.append("Invalid input! Accepted types are \"I\", \"C\", \"A\".\n");
						}
						else UI.textArea.append("Invalid input! Accepted types are \"I\", \"C\", \"A\".\nYou may either exhange three of the same card of the same type or one of each.\n");
					}
					else UI.textArea.append("Invalid input! Please enter three characters.\n");
				}
			
			}
		}
		else if(GameData.countryselected==1){
			GameData.countryselected = 0;
			UI.textField.setText("");
			UI.textArea.append("\n-----------------------------------------------------------------\n");
			UI.textArea.append("\nREINFORCEMENT PHASE!\n \nPick a country and reinforce!\n");
			GameData.phase++;
		}
		if(GameData.countryselected==2){

			UI.textArea.append("Traded in cards for " + GameData.CARD_REWARD_TROOPS[GameData.card_counter] + " troops.\n");
			if(GameData.playerturn == 0){ //player 1's turn
				infantry = GameData.player1Cards.countOccurences(1);
				cavalry = GameData.player1Cards.countOccurences(2);
				artillery = GameData.player1Cards.countOccurences(3);
				wildcard = GameData.player1Cards.countOccurences(4);
			}
			else { //player 2's turn
				infantry = GameData.player2Cards.countOccurences(1);
				cavalry = GameData.player2Cards.countOccurences(2);
				artillery = GameData.player2Cards.countOccurences(3);
				wildcard = GameData.player2Cards.countOccurences(4);
			}
			UI.textArea.append("\nYou have:\n" + infantry + " infantry card(s).\n" + cavalry + " cavalry card(s).\n" + artillery + " artillery card(s).\n" + wildcard + " wildcard(s).\n");
			GameData.test.updateReinforcements(GameData.CARD_REWARD_TROOPS[GameData.card_counter]);
			UI.textArea.append("\nPlayer " + (GameData.playerturn+1) + "'s new reinforcement count: " + GameData.test.getReinforcements() + "\n");
			UI.textArea.append("\n-----------------------------------------------------------------\n");
			UI.textArea.append("\nREINFORCEMENT PHASE!\n Pick a country and reinforce!\n");
			GameData.card_counter++;
			GameData.countryselected=0;
			GameData.phase++;
		}
	}
}