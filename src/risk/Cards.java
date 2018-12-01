package risk;

public class Cards {
	public static void sameTypeCards(int type){
		int i=0;
		int j;
		if(GameData.playerturn==0){
			for(j=GameData.player1Cards.size()-1;j>=0&&i<3;j--){
				if(GameData.player1Cards.elemAtRank(j)==type){
					i++;
					GameData.player1Cards.removeAtRank(j);
				}
			}
			for(j=GameData.player1Cards.size()-1;j>=0&&i<3;j--){
				if(GameData.player1Cards.elemAtRank(j)==4){
					i++;
					GameData.player1Cards.removeAtRank(j);
				}
			}
		}
		else{
			for(j=GameData.player2Cards.size()-1;j>=0&&i<3;j--){
				if(GameData.player2Cards.elemAtRank(j)==type){
					i++;
					GameData.player2Cards.removeAtRank(j);
				}
			}
			for(j=GameData.player2Cards.size()-1;j>=0&&i<3;j--){
				if(GameData.player2Cards.elemAtRank(j)==4){
					i++;
					GameData.player2Cards.removeAtRank(j);
				}
			}
		}
	}
	public static void differentTypeCards(int a, int c, int i, int w){
		int j=0;
		int k=0;
		boolean check;
		if(GameData.playerturn==0){
			if(a > 0){
				check = false;
				for(j=GameData.player1Cards.size()-1;j>=0&&!check;j--){
					if(GameData.player1Cards.elemAtRank(j)==3){
						i++;
						GameData.player1Cards.removeAtRank(j);
						check = true;
						k++;
					}
				}
				
			}
			if(c > 0){
				check = false;
				for(j=GameData.player1Cards.size()-1;j>=0&&!check;j--){
					if(GameData.player1Cards.elemAtRank(j)==2){
						i++;
						GameData.player1Cards.removeAtRank(j);
						check = true;
						k++;
					}
				}
				
			}
			if(i > 0){
				check = false;
				for(j=GameData.player1Cards.size()-1;j>=0&&!check;j--){
					if(GameData.player1Cards.elemAtRank(j)==1){
						i++;
						GameData.player1Cards.removeAtRank(j);
						check = true;
						k++;
					}
				}
			}
			for(j=GameData.player1Cards.size()-1;j>=0&&k<3;j--){
				if(GameData.player1Cards.elemAtRank(j)==4){
					i++;
					GameData.player1Cards.removeAtRank(j);
					check = true;
					k++;
				}
			}
		}
		else{
			if(a > 0){
				check = false;
				for(j=GameData.player2Cards.size()-1;j>=0&&!check;j--){
					if(GameData.player2Cards.elemAtRank(j)==3){
						i++;
						GameData.player2Cards.removeAtRank(j);
						check = true;
						k++;
					}
				}
				
			}
			if(c > 0){
				check = false;
				for(j=GameData.player2Cards.size()-1;j>=0&&!check;j--){
					if(GameData.player2Cards.elemAtRank(j)==2){
						i++;
						GameData.player2Cards.removeAtRank(j);
						check = true;
						k++;
					}
				}
				
			}
			if(i > 0){
				check = false;
				for(j=GameData.player2Cards.size()-1;j>=0&&!check;j--){
					if(GameData.player2Cards.elemAtRank(j)==1){
						i++;
						GameData.player2Cards.removeAtRank(j);
						check = true;
						k++;
					}
				}
			}
			for(j=GameData.player2Cards.size()-1;j>=0&&k<3;j--){
				if(GameData.player2Cards.elemAtRank(j)==4){
					i++;
					GameData.player2Cards.removeAtRank(j);
					check = true;
					k++;
				}
			}
		}
	}
	
	public static boolean differentCardValidity(String input){
		boolean a = false, c = false, i = false;
		int j;
		for(j=0;j<3;j++){
			if(input.charAt(j)=='a'){
				a=true;
			}
			if(input.charAt(j)=='c'){
				c=true;
			}
			if(input.charAt(j)=='i'){
				i=true;
			}
		}
		if(a&&c&&i){
			return true;
		}
		else return false;
	}
	
	public static boolean checkDifferentCards(int a, int c, int i, int w){
		boolean check = false;
		if(a>0&&c>0&&i>0) check = true;
		else if((a>0&&c>0&&w>0)||(c>0&&i>0&&w>0)||(a>0&&i>0&&w>0)||(((a+i+c)>0)&&w==2)) check = true;
		return check;
	}
}