package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NorthTown extends Town {
	
	
	public NorthTown(GraphicsContext gc, Image image, Player player, int x, int y, int width, int height){
		super(gc, image, x, y, width, height);
		createTown(player);
		addNPCs();
	}	
	
	public void addNPCs() {
        NPCRectangle dewey = new NPCRectangle("Character sprites/deweyOverworld.png", "Character sprites/deweyBattle.png", getLeftX()+975, getTopY()+40);
        dewey.setDialogue("DEWEY: I’ve seen an awful lot of packages coming to HUMPHREY’s "
        		+ "house recently — he seems pretty angry whenever he gets one.");
        dewey.setPath("RIGHT", 400, "DOWN", 200, 90);
        dewey.setWaitTimes(1, 1.5, 0.8, 2);
        addTownNPC(dewey);
        
        NPCLine megan = new NPCLine("Character sprites/meganOverworld.png", "Character sprites/meganBattle.png", getLeftX()+110, getTopY()+650);
        megan.setDialogue("MEGAN:  I love listening to AMORITA’s radio show! "
        		+ "She’s a bit over-the-top and radical, but that’s what makes for an interesting show!");
        megan.setPath("DOWN", 300, 90);
        megan.setWaitTimes(1.5, 2);
        addTownNPC(megan);
	}

	public void createTown(Player player) {
		addHumphreyHouse(0, 150);
		addDecorativeBuilding("building3NoDoor.png", 384, 150);
		addMarkHouse(1000, 300);
		
		addAmoritaHouse(175, 400);
		addNurseDawnHouse(559, 400);
		
		addDecorativeBuilding("building3NoDoor.png", 175, 850);
		addMart(823, 723, player);
		addDecorativeBuilding("building3NoDoor.png", 1071, 850);		
	}

	@Override
	public void addBotBorder(GraphicsContext gc) {
		addBorder(gc, new Forest(gc, getLeftX()-48, getBotY(), 48*3, 48));
		addBorder(gc, new Forest(gc, getRightX()-48*3, getBotY(), 48*3, 48));
	}
	
	public void addNurseDawnHouse(int x, int y) {
		ArrayList<NPC> npcList = getNurseDawnNPCs();
		ArrayList<String> interMsgs = getNurseDawnInteractions();
		
        Interior nurseDawnInterior = new Interior2(interMsgs);
        nurseDawnInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("mediExterior.png", getLeftX()+x, getTopY()+y, nurseDawnInterior)); 
	}
	
	public ArrayList<String> getNurseDawnInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("There are several travel guides for places all over the world.");
        interMsgs.add("It's a collage of pictures of NURSE DAWN at famous landmarks");
        interMsgs.add("There are many language-learning books and dictionaries translating "
        		+ "between different languages");
        interMsgs.add("");
        interMsgs.add("NURSE DAWN seems to be trying to decide what her next "
        		+ "travel destination will be");
        interMsgs.add("This butterfly palm tree nearly reaches the ceiling!");
        
        return interMsgs;
	}
	
	public ArrayList<NPC> getNurseDawnNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC nurseDawn = new NPC("Character sprites/nurseOverworld.png", "Character sprites/nurseBattle.png", 100, 240);
        nurseDawn.setDialogue("NURSE DAWN: You're from the south? I have a sister who lives there! "
        		+ "Anywho - here's some medicine if you're feeling a bit under the weather. "
        		+ "You'll be feeling great in no time!");
        npcList.add(nurseDawn);
        
        return npcList;
	}
	
	public void addMarkHouse(int x, int y) {
        ArrayList<NPC> npcList = getMarkNPCs();
        ArrayList<String> interMsgs = getMarkInteractions(); 
        
        Interior markInterior = new Interior1(interMsgs);
        markInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building3Door.png", getLeftX()+x, getTopY()+y, markInterior));
	}
	
	public ArrayList<String> getMarkInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("There seem to be lots of popular fiction and 'pop science' books.");
        interMsgs.add("There are reruns of some dating game show on.");
        
        return interMsgs;
	}

	public ArrayList<NPC> getMarkNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC tom = new NPC("Character sprites/tomOverworld.png", "Character sprites/tomBattle.png", 100, 193);
        tom.setDialogue("TOM: When MARK and I were out celebrating our anniversary earlier this month, "
        		+ "that HUMPHREY guy came up and told us to 'keep it to ourselves.' All we were doing was holding hands!");
        NPC mark = new NPC("Character sprites/markOverworld.png", "Character sprites/markBattle.png", 244, 193);
        mark.setDialogue("MARK: Since that HUMPHREY incident, TOM and I have been spending our weekends "
        		+ "going to this gay bar in the Center City where our friend JANICE works. "
        		+ "It's nice to be accepted!");
        
        npcList.add(tom);
        npcList.add(mark);
        
        return npcList;
	}
	
	public void addAmoritaHouse(int x, int y) {
        ArrayList<NPC> npcList = getAmoritaNPCs();
        ArrayList<String> interMsgs = getAmoritaInteractions();
		
        Interior amoritaInterior = new Interior2(interMsgs);
        amoritaInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building3Door.png", getLeftX()+x, getTopY()+y, amoritaInterior));
	}
	
	public ArrayList<String> getAmoritaInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("Most of the books seem to be about feminist theory");
        interMsgs.add("A portrait of GLORIA STEINEM hangs on the wall");
        interMsgs.add("You see a crumpled up letter addressed to JED:\n"
        		+ "JED—I’m tired of your teasing. I love you and I want you for myself… "
        		+ "you flirt with me, but keep choosing to date complete ditzes over me. "
        		+ "I’m sick of it.");
        interMsgs.add("AMORITA appears to be working on the crossword in the newspaper");
        interMsgs.add("The ficus tree seems to be losing its leaves");
        
        return interMsgs;
	}
	
	public ArrayList<NPC> getAmoritaNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC amorita = new NPC("Character sprites/amoritaOverworld.png", "Character sprites/amoritaBattle.png", 100, 190);
        amorita.setDialogue("AMORITA: Honestly, I can't say I feel too bad that Jed's dead. "
        		+ "He was just a chauvinist pig; my bet is one of his spurned lovers did him in.");
        npcList.add(amorita);
        
        return npcList;
	}
	
	public void addHumphreyHouse(int x, int y) {
		ArrayList<NPC> npcList = getHumphreyNPCs();
		ArrayList<String> interMsgs = getHumphreyInteractions();
		
	    Interior humphreyInterior = new Interior1(interMsgs);
	    humphreyInterior.setNPCs(npcList);
	      
	    getTownBuildings().add(new Building("building3Door.png", getLeftX()+x, getTopY()+y, humphreyInterior)); 
	}
	
	public ArrayList<String> getHumphreyInteractions() {
	      ArrayList<String> interMsgs = new ArrayList<String>();
	      interMsgs.add("There are some unopened packages, but you can't tell who they're from.");
	      interMsgs.add("There's some weird art film on you don't recognize.");
	      
	      return interMsgs;
	}
	
	public ArrayList<NPC> getHumphreyNPCs() {
	      ArrayList<NPC> npcList = new ArrayList<NPC>();
	      NPC humphrey = new NPC("Character sprites/humphreyOverworld.png", "Character sprites/humphreyBattle.png", 243, 193);
	      humphrey.setDialogue("HUMPHREY: I loved directing JED and ELIZABETH back in the old days. "
	      		+ "I just don't know what got into him when he moved to television.");
	      
	      NPC susie = new NPC("Character sprites/susieOverworld.png", "Character sprites/susieBattle.png", 100, 193);
	      susie.setDialogue("SUSIE: There's nothin' my daddy says he hates more than people "
	      		+ "who don't take 'No' for an answer, like when bad actors keep "
	      		+ "askin' him for roles in his movies.");
	      
	      npcList.add(humphrey);
	      npcList.add(susie);
	      
	      return npcList;
	}
	
}