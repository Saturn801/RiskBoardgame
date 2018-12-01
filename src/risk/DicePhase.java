package risk;

public class DicePhase {
	public static void runPhase3(Risk game){

		//Dice for taking turns
		int result1;
		int result2;
		Dice dice = new Dice();
		result1 = dice.getValue();
		result2 = dice.reroll();

		while(result1==result2){
			result1 = dice.reroll();
			result2 = dice.reroll();
			UI.textArea.append("\nEach player rolled the same. Reroll!");
		}
		
		//if Player 1 gets a higher dice number
		if(result1>result2){
			GameData.playerturn = 0;
			UI.textArea.append("\nDICE ROLL:\nPlayer 1 rolled " + result1 + "\nPlayer 2 rolled " + result2 + "\n\nPlayer 1 wins! Play your turn!\n");
		}

		//if Player 2 gets a higher dice number
		else{
			GameData.playerturn = 1;
			UI.textArea.append("\nDICE ROLL:\nPlayer 2 rolled " + result1 + "\nPlayer 1 rolled " + result2 + "\n\nPlayer 2 wins! Play your turn!\n");
		}
		
		UI.textArea.append("\n-----------------------------------------------------------------\n");
		GameData.phase++;
		game.repaint();
	}
}