package risk;

import java.util.Random;

public final class GameData {
	
	public static final int NUM_PLAYERS = 2;
	public static final int NUM_NEUTRALS = 4;
	public static final int NUM_PLAYERS_PLUS_NEUTRALS = NUM_PLAYERS + NUM_NEUTRALS;
	public static final int NUM_COUNTRIES = 42;
	public static final int INIT_COUNTRIES_PLAYER = 9;
	public static final int INIT_COUNTRIES_NEUTRAL = 6;
	public static final int INIT_UNITS_PLAYER = 36;
	public static final int INIT_UNITS_NEUTRAL = 24;
	public static final String[] COUNTRY_NAMES = {
		"Ontario","Quebec","NW Territory","Alberta","Greenland","E United States","W United States","Central America","Alaska",
		"Great Britain","W Europe","S Europe","Ukraine","N Europe","Iceland","Scandinavia",
		"Afghanistan","India","Middle East","Japan","Ural","Yakutsk","Kamchatka","Siam","Irkutsk","Siberia","Mongolia","China",
		"E Australia","New Guinea","W Australia","Indonesia",
		"Venezuela","Peru","Brazil","Argentina",
		"Congo","N Africa","S Africa","Egypt","E Africa","Madagascar"};  // for reference
	public static final String[][] ALTERNATE_NAMES = {{},{},{"NWT", "North West Territory", "Northwest Territory"},{},{},
			{"EUS", "E US", "East US", "Eastern US", "East United States", "Eastern United States"},
			{"WUS", "W US", "West US", "Western US", "West United States", "Western United States"},{"CA"},{},
			{"GB", "UK", "England"},{"WEU", "West EU", "Western EU", "West Europe", "Western Europe", "France", "Spain", "Iberia"},
			{"SEU", "South EU", "Southern EU", "South Europe", "Southern Europe", "Italy", "Greece"},{},
			{"NEU", "North EU", "Northern EU", "North Europe", "Northern Europe", "Germany", "Prussia"},{},
			{"Sweden", "Norway", "Finland"},{},{},{"ME"},{"JP","JPN"},{},{},{"KCK", "KCHK"},{},{},{"SBR"},{},{"CN", "CHN"},
			{"EAU", "East AU", "East Aus", "Eastern AU", "Eastern Aus", "East Australia", "Eastern Australia"},{"NG"},
			{"WAU", "West AU", "West Aus", "Western AU", "Western Aus", "West Australia", "Western Australia"},{},{},{},{},{},{},
			{"NAF", "NAFR", "North AF", "Northern AF", "North AFR", "Northern AFR", "North Africa", "Northern Africa"},
			{"SAF", "SAFR", "South AF", "Southern AF", "South AFR", "Southern AFR", "South Africa", "Southern Africa"},{},
			{"EAF", "EAFR", "East AF", "Eastern AF", "East AFR", "Eastern AFR", "East Africa", "Eastern Africa"},{}};
	public static final String[] IMAGES = {
			"1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png", "10.png", "11.png", "12.png", 
			"13.png", "14.png", "15.png", "16.png", "17.png", "18.png", "19.png", "20.png", "21.png", "22.png", "23.png",
			"24.png", "25.png", "26.png", "27.png", "28.png", "29.png", "30.png", "31.png", "32.png", "33.png", "34.png", 
			"35.png", "36.png", "37.png", "38.png", "39.png", "40.png", "41.png", "42.png", "43.png", "44.png"};
	public static final int[][] ADJACENT = { 
		{4,1,5,6,3,2},    // 0
		{4,5,0},
		{4,0,3,8},
		{2,0,6,8},
		{14,1,0,2},
		{0,1,7,6}, 
		{3,0,5,7},
		{6,5,32},
		{2,3,22},
		{14,15,13,10},    
		{9,13,11,37},     // 10
		{13,12,18,39,10,37}, // 11 ---- ADDED 37 HERE
		{20,16,18,11,13,15},
		{15,12,11,10,9},
		{15,9,4},
		{12,13,14,9},	// 15 ---- ADDED 9 HERE
		{20,27,17,18,12}, 
		{16,27,23,18},
		{12,16,17,40,39,11},
		{26,22},
		{25,27,16,12},    // 20
		{22,24,25},        
		{8,19,26,24,21},
		{27,31,17},
		{21,22,26,25},
		{21,24,26,27,20},
		{24,22,19,27,25},
		{26,23,17,16,20,25},
		{29,30},          
		{28,30,31},
		{29,28,31},      // 30
		{23,29,30},
		{7,34,33},       
		{32,34,35},
		{32,37,35,33},
		{33,34},
		{37,40,38},      
		{10,11,39,40,36,34},
		{36,40,41},
		{11,18,40,37},
		{39,18,41,38,36,37},  //40
		{38,40}
	};
	public static final int NUM_CONTINENTS = 6;
	public static final String[] CONTINENT_NAMES = {"N America","Europe","Asia","Australia","S America","Africa"};  // for reference 
	public static final int[] CONTINENT_IDS = {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5};
	public static final int[] CONTINENT_VALUES = {5,5,7,2,2,3};
	public static final int FRAME_WIDTH = 1300;    
	public static final int FRAME_HEIGHT = 650;
	public static final int[][] COUNTRY_COORD = {
		{191,150},     // 0
		{255,161},
		{146,86},
		{123,144},
		{314,61},
		{205,235},
		{135,219},
		{140,299},
		{45,89},
		{370,199},
		{398,280},      // 10
		{465,270},
		{547,180},
		{460,200},
		{393,127},
		{463,122},
		{628,227},
		{679,332},
		{572,338},
		{861,213},
		{645,152},      // 20
		{763,70},
		{827,94},
		{751,360},
		{750,140},
		{695,108},
		{760,216},
		{735,277},
		{889,537},
		{850,429},
		{813,526},       // 30
		{771,454},
		{213,352},
		{221,426},
		{289,415},
		{233,523},
		{496,462},
		{440,393},
		{510,532},
		{499,354},
		{547,432},        // 40
		{586,545}
	};	

	public static final int circleXcenter = 50;
	public static final int circleYcenter = 50;
	public static final int circleRadius = 20;	//constants for circles
	
    public static int phase=0, enteredplayers=0, drawncards=0, playerturn=0, loops=0, neutralturn=0, countryselected=0, location=0, numentered=0, location2=0, phasecheck=0, p1troops=0, p2troops=0, conquered;
    public static boolean adjacent;
    public static final int[] COUNTRY_CARDS = {2,3,3,1,2,3,1,2,1,2,1,2,3,2,1,3,1,1,3,1,2,2,2,3,1,3,3,2,1,2,3,2,3,2,3,1,2,1,3,1,3,1,4,4};
    public static SinglyLinkedVector player1Cards = new SinglyLinkedVector();	// These vectors store each players hands.
    public static SinglyLinkedVector player2Cards = new SinglyLinkedVector();
    public static SinglyLinkedVector Cards = new SinglyLinkedVector();	// This is used to store the actual cards.
    public static int range = 43;	// This is for checking how many cards are left in the vector to pick a random one.
    public static Troops test = new Troops();
    public static Random rand = new Random();
    //phase is a control variable for sequence of events. enteredplayers is used for player name inputs.
    //drawncards is used when drawing cards for territory assignment. playerturn is used in several places to check which players turn it is.
    //loops is used for checking how many cards the neutrals have drawn at the start, so that they can be skipped. neutralturn is used when assigning starting troops to neutrals.
	public static String[] PLAYER_NAMES = new String[6];	//stores player names.
	public static int[] ASSIGNED_TERRITORIES = new int[GameData.NUM_COUNTRIES];
	 public static int[][] troop_display = new int[GameData.NUM_COUNTRIES][2];	//holds who owns every country and the number of troops there.
	 public static int[] CARD_REWARD_TROOPS = {4,6,8,10,15,20,25,30,35,40,45,50,55,60};
	 public static int card_counter = 0;
	
	private GameData() {
	    //this prevents even the native class from calling this constructor
	    throw new AssertionError();
    }
}