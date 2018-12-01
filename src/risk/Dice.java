package risk;

public class Dice {
	private int dice;
	
	public Dice(){
		dice = 1 + (int)(Math.random() *6.0);
	}
		
	public int reroll(){
		dice = 1 + (int)(Math.random() *6.0);
		return dice;
	}
	
	public int getValue(){
		return dice;
	}
}