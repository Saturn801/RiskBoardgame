//Team Determination
//Eoghan O'Donnell - 14464082
//Jazheel Luna - 14486752
//Sam Bryan - 14701935

package risk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Risk extends JFrame{

	public Risk(){	//constructor
	setSize(GameData.FRAME_WIDTH, GameData.FRAME_HEIGHT);
	setLocationRelativeTo(null);
	setTitle("Risk");
	GameData.PLAYER_NAMES[2]="Neutral 1";
	GameData.PLAYER_NAMES[3]="Neutral 2";
	GameData.PLAYER_NAMES[4]="Neutral 3";
	GameData.PLAYER_NAMES[5]="Neutral 4";
	
	GameData.Cards.initialize(44);
	
	final Risk game = this;	// This creates a copy of the class to be able to pass as a parameter to functions.
							// This will allow functions in other classes to call methods from this class such as repaint().

	JButton button = new JButton("Draw cards");
	button.addActionListener(new ActionListener(){	// If the button is pressed.
		public void actionPerformed(ActionEvent e){
			Button.runButton(game);
		}
	});

	JPanel buttonPane = new JPanel();
	buttonPane.add(button);
	add(buttonPane, BorderLayout.WEST);
	UI.textField = new JTextField();		//input text field.
	add(UI.textField, BorderLayout.SOUTH);	
	UI.textArea = new JTextArea(25,25);	//output text area.
	UI.textArea.setEditable(false);
	add(UI.textArea, BorderLayout.EAST);
	UI.textArea.append("WELCOME TO RISK!\n");	// adds the starting text to the text area
	UI.textArea.append("Please enter the players' names:\n\n");
	UI.textArea.setLineWrap(true);		// makes it so if the string is too long it goes onto the next line
	UI.textArea.setMargin(new Insets(5, 5, 5, 5));
	add(new JScrollPane(UI.textArea), BorderLayout.LINE_END); 				//adds the text area to the scroll pane
	UI.textArea.setCaretPosition(UI.textArea.getDocument().getLength());		//auto scroll to bottom of textArea
	//URL riskIconLink = getClass().getResource("/RiskIcon.png");			//these four lines are for setting the icon image.
	//ImageIcon riskImageIcon = new ImageIcon(riskIconLink); 
	//Image riskIcon = riskImageIcon.getImage();
	//setIconImage(riskIcon);
	setVisible(true);
	setResizable(false);	// sets the JFrame to a fixed size
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// this sets it so that when the JFrame is closed, the program ends.
	UI.textField.requestFocus();
	
	UI.textField.addActionListener(new ActionListener(){	// this method is called when the user hits enter when typing in the text field.
		public void actionPerformed(ActionEvent e){
			if(!UI.textField.getText().equals("")){	
				//PHASE 0 NAME INPUTS
				if(GameData.phase==0){	//Player input phase
					NameInputPhase.runPhase0();
				}
				
				//PHASE 1 ASSIGNING TERRITORIES + DICE
				if(GameData.phase==1){	//Army deployment phase
					InitialTerritoryAssignment_Dice.runPhase1();
				}

				//PHASE 2 INITIAL REINFORCEMENTS
				if(GameData.phase==2){
					InitialReinforcements.runPhase2(game);
				}	

				//PHASE 3 DICE
				if(GameData.phase == 3){
					DicePhase.runPhase3(game);	// Whenever we need to call repaint() inside the phase we pass in game.
				}
				
				//PHASE 4 REINFORCEMENT CALCULATION
				if(GameData.phase == 4){
					ReinforcementCalculation.runPhase4();
				}

				//PHASE 5 MAIN REINFORCEMENTS
				if(GameData.phase == 5){	//goes to the next phase
					MainReinforcements.runPhase5(game);
				}
				
				//PHASE 6 COMBAT
				if(GameData.phase == 6){
					Combat.runPhase6(game);
				}
				
				
				//PHASE 7 FORTIFYING
				if(GameData.phase == 7){
					Fortifying.runPhase7(game);
				}
				
				//PHASE 8 END GAME
				if(GameData.phase==8){
					UI.textArea.append("\nCongratulations, Player " + (GameData.playerturn+1) + ", you win!!!");	
				}
			}
		}	
	});
}
	
public void paint(Graphics g){	// the graphics class is used to draw all the board for the game (circles, lines, text, ...).
	super.paint(g);
	int i=0, j, k;	// all these are just for use in loops.
	
	Image img = new ImageIcon("resources/Risk_board2.png").getImage();
	g.drawImage(img, 0, 40, null);
	
	while(i<GameData.NUM_COUNTRIES){	// this loop is here so that the lines between the countries are drawn before the nodes, so that they do not overlap.
		j=0; 
		while(j<GameData.ADJACENT[i].length){	// cycles through the array of countries.
			if((i==8 && j==2) || (i==22 && j==0)){		// this if statement is for not drawing a line between Alaska and Kamchatka, since it would go across the map and get in the way.
				j++;
			}  
			else{
				k=GameData.ADJACENT[i][j];	// draws a line between the country we are looking at and the ones adjacent to it.
				g.drawLine(GameData.COUNTRY_COORD[i][0]+GameData.circleRadius,GameData.COUNTRY_COORD[i][1]+GameData.circleRadius,GameData.COUNTRY_COORD[k][0]+GameData.circleRadius,GameData.COUNTRY_COORD[k][1]+GameData.circleRadius);
				j++;
			}
		}
		i++;
	} // this draws a horizontal line from Alaska and Kamchatka to the edge of the map to make it look cleaner.
	g.drawLine(GameData.COUNTRY_COORD[8][0]+GameData.circleRadius,GameData.COUNTRY_COORD[8][1]+GameData.circleRadius,0, GameData.COUNTRY_COORD[8][1]+GameData.circleRadius);
	g.drawLine(GameData.COUNTRY_COORD[22][0]+GameData.circleRadius,GameData.COUNTRY_COORD[22][1]+GameData.circleRadius,1009, GameData.COUNTRY_COORD[22][1]+GameData.circleRadius);
	i=0;
	while(i<GameData.NUM_COUNTRIES){	// this has to be another loop so we draw the nodes after the lines.
		g.setFont(new Font("default",Font.PLAIN,12));
		switch(GameData.CONTINENT_IDS[i]){ 
		case 0:
			g.setColor(new Color(252,243,125)); //yellow
			break;
		case 1:
			g.setColor(new Color(10,106,227)); //blue
			break;
		case 2:
			g.setColor(new Color(73,173,100)); //green
			break;
		case 3:
			g.setColor(new Color(146,163,207)); //light blue
			break;
		case 4:
			g.setColor(new Color(238,134,0)); //orange
			break;
		case 5:
			g.setColor(new Color(169,105,43)); //brown
			break;
		}
		g.fillOval(GameData.COUNTRY_COORD[i][0],GameData.COUNTRY_COORD[i][1], GameData.circleRadius*2+1, GameData.circleRadius*2+1);	// the circles are drawn and filled in.
		g.setColor(Color.black);
		g.drawOval(GameData.COUNTRY_COORD[i][0],GameData.COUNTRY_COORD[i][1],GameData.circleRadius*2,GameData.circleRadius*2);
		g.setFont(new Font("default",Font.BOLD,12));
		g.drawString(GameData.COUNTRY_NAMES[i], GameData.COUNTRY_COORD[i][0]+GameData.circleRadius-(GameData.COUNTRY_NAMES[i].length()*3),GameData.COUNTRY_COORD[i][1]+50);  // the country names are drawn on as well.
		i++;
	}
	for(i=0;i<GameData.NUM_COUNTRIES;i++){	//
		g.setFont(new Font("default",Font.BOLD,14));	//sets font to bold
		switch(GameData.troop_display[i][0]){						//switch statement to check player
		case 0:											//nobody owns territory
			break;
		case 1:											//player 1
			g.setColor(new Color(200,40,30)); //red
			break;
		case 2:											//player 2
			g.setColor(new Color(0,204,255)); //blue
			break;
		case 3:											//neutral 1
			g.setColor(new Color(0,51,0)); //green
			break;
		case 4:											//neutral 2
			g.setColor(new Color(125,123,124)); //gray
			break;
		case 5:											//neutral 3
			g.setColor(new Color(100,100,0)); //olive
			break;
		case 6:											//neutral 4
			g.setColor(new Color(51,0,102)); //violet
			break;
		}
		if(GameData.troop_display[i][0]>0){//runs if a territory has troops
			g.fillOval(GameData.COUNTRY_COORD[i][0]+8,GameData.COUNTRY_COORD[i][1]+8, GameData.circleRadius+5, GameData.circleRadius+5);

			//colour coordination for players and neutrals 
			g.setColor(Color.black);
			g.drawRect(150, 30, 25, 10);	//neutral 1 colour rectangle
			g.drawString("Neutral 1", 178, 40);
			g.setColor(new Color(0,51,0));
			g.fillRect(150, 30, 25, 10);

			g.setColor(Color.black);
			g.drawRect(250, 30, 25, 10);	//neutral 2 colour rectangle
			g.drawString("Neutral 2", 278, 40);
			g.setColor(new Color(125,123,124));
			g.fillRect(250, 30, 25, 10);

			g.setColor(Color.black);
			g.drawRect(350, 30, 25, 10);	//neutral 3 colour rectangle
			g.drawString("Neutral 3", 378, 40);
			g.setColor(new Color(100,100,0));
			g.fillRect(350, 30, 25, 10);

			g.setColor(Color.black);
			g.drawRect(450, 30, 25, 10);	//neutral 4 colour rectangle
			g.drawString("Neutral 4", 478, 40);
			g.setColor(new Color(51,0,102));
			g.fillRect(450, 30, 25, 10);

			g.setColor(Color.black);
			g.drawRect(600, 30, 25, 10);	//player 1 colour rectangle
			g.drawString(GameData.PLAYER_NAMES[0], 628, 40);
			g.setColor(new Color(200,40,30));
			g.fillRect(600, 30, 25, 10);

			g.setColor(Color.black);
			g.drawRect(800, 30, 25, 10);	//player 2 colour rectangle
			g.drawString(GameData.PLAYER_NAMES[1], 828, 40);
			g.setColor(new Color(0,204,255));
			g.fillRect(800, 30, 25, 10);
			
			g.setColor(Color.black);
			g.drawString("Player " + (GameData.playerturn+1) + ": " + GameData.PLAYER_NAMES[GameData.playerturn] + " is currently playing...", 30, 610);
								
			g.setColor(Color.white);	//white font colour for numbers
			
			if(GameData.troop_display[i][1]<10)					//position of one-digit number
				g.drawString(Integer.toString(GameData.troop_display[i][1]), GameData.COUNTRY_COORD[i][0]+17,GameData.COUNTRY_COORD[i][1]+25);   
			else 										//position of two-digit number
				g.drawString(Integer.toString(GameData.troop_display[i][1]), GameData.COUNTRY_COORD[i][0]+13,GameData.COUNTRY_COORD[i][1]+25);
		}
	}
}


public static void main(String[] a){	// the main is just used to declare a new variable of type risk for use with the entire program.
		Risk myJFrame = new Risk();
	}
}