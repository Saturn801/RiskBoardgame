package risk;

public class Troops{	//Troops class, will be used a lot for later sprints
	int i=0;
	private int Reinforcements = 6;	//this variable tracks initial reinforcements (27*2)
	public void assignTroops(int territory, int player, int troops){	//function to assign troops to a territory
		GameData.troop_display[territory][0] = player;
		GameData.troop_display[territory][1] += troops;
	}
	public void updateReinforcements(int x){	//adjusts number of reinforcements left 
		Reinforcements += x;
	}
	public int getReinforcements(){	//gets number of beginning reinforcements left to assign left
		return Reinforcements;
	}

	public int checkReinforceInput(String s){	//gets number of beginning reinforcements left to assign left
		boolean temp = isNumeric(s);
		if(temp==false){	//if the text input is not a number
			if(s.equals("cancel"))
				return 1;	//returns 1 if the user input is 'cancel'
			else if(s.equals("skip")) //returns 3 if the user input is 'skip'
				return 3;
			else
				return 0;	//else returns 0
		}
		else
			return 2;	//if the text input is a number return 2
	}

	public boolean isNumeric(String str)  //returns true if the text input is a number or false if it is not a number
	{  
		try  
		{  
			Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	public int checkValidity(String s){			//this function takes in a string and either returns the location of the country corresponding
		//to it or a value corresponding to a different error
		boolean check = true;		//used for checks
		boolean check_2 = true;
		int k = -1;					//-1 is return value if string doesn't match, i.e. invalid error

		if(check){					//first checks if entered string is in array of alternate names
			for(int i = 0;i<GameData.NUM_COUNTRIES;i++){
				for(int j = 0;j<GameData.ALTERNATE_NAMES[i].length;j++){
					if(GameData.ALTERNATE_NAMES[i][j].toLowerCase().equals(s.toLowerCase())){	//compares w/o capitalisation
						k = i;				//if a match is found, sets k to location in array
						check = false;		//and sets check to false so other loop doesn't iterate
					}
				}
			}
		}

		if(check){		//if string isn't in alternate names array
			for(int i=0;i<GameData.NUM_COUNTRIES;i++){
				if(GameData.COUNTRY_NAMES[i].toLowerCase().contains(s.toLowerCase())&&!s.equals("")&&check_2){	//checks is string is contained in territory names
					k = i;	//sets k to i if it is
					if (!check){			//if check is false (i.e. a string has already been found and this is the second territory containing it)
						check_2 = false;	//check_2 is set to false, which ends for loop
						k=-2;				//k is set to -2, which is used to report an ambiguity error
					}
					else
						check = false;		//if the string is found, check is set to false in order to check above statement
				}
			}		
		}

		return k;	//returns value of k, i.e. location in array or error value.
	}

	public boolean checkAdjacent(int x, int y){	//checks whether two countries are adjacent to each other
		
		for(int i = 0; i < GameData.ADJACENT[x].length; i++){
			if(GameData.ADJACENT[x][i] == y)
			{
				return true;
			}
		}
		return false;
		
	}
	public String attackTerritory(){
		String output;
		int p1dice[] = {0, 0, 0};
		int p2dice[] = {0, 0};
		int p1lost=0, p2lost=0;
		int temp;
		Dice dice = new Dice();
		p2dice[0] = dice.getValue();
		if(GameData.p2troops==2){
			p2dice[1]=dice.reroll();
		}
		for(int i=0;i<GameData.p1troops;i++){
			p1dice[i]=dice.reroll();
		}
		output = "Attacker rolled: ";
		for(int i = 0;i<GameData.p1troops;i++)	output += p1dice[i] + " ";
		output += "\nDefender rolled: ";
		for(int i = 0;i<GameData.p2troops;i++)	output += p2dice[i] + " ";
		output += "\n";
		if (p2dice[1]>p2dice[0]){
			temp = p2dice[1];
			p2dice[1]=p2dice[0];
			p2dice[0]=temp;
		}
		for(int i=1;i<GameData.p1troops;i++){
			for(int j=0;j<i;j++){
				if(p1dice[j]<p1dice[i]){
					temp = p1dice[j];
					p1dice[j]=p1dice[i];
					p1dice[i]=temp;
				}
			}
		}
		if(p1dice[0]>p2dice[0]){	//if attacker has higher roll, defender loses one army.
			assignTroops(GameData.location2,GameData.troop_display[GameData.location2][0],-1);
			p2lost++;
		}
		else{						//else attacker loses one army.
			assignTroops(GameData.location,GameData.troop_display[GameData.location][0],-1);
			p1lost++;
		}
		if(GameData.p2troops>1&&GameData.p1troops>1){				//if both players rolled at least two dice, we check their second highest dice
			if(p1dice[1]>p2dice[1]){	
				assignTroops(GameData.location2,GameData.troop_display[GameData.location2][0],-1);
				p2lost++;
			}
			else{					
				assignTroops(GameData.location,GameData.troop_display[GameData.location][0],-1);
				p1lost++;
			}
		}
		output+= "\nAttacker lost " + p1lost + " units.\nDefender lost " + p2lost + " units.\n";
		return output;
	}

}