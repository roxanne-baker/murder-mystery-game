package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class CenterTown extends Town{	
	
	public CenterTown(GraphicsContext gc, Image image, Player player, int x, int y, int width, int height){
		super(gc, image, x, y, width, height);
		createTown(player);
		addTownNPCs();
	}
	
	public void addTownNPCs() {
        NPC hailey = new NPC("Character sprites/haileyOverworld.png", "Character sprites/haileyBattle.png", getLeftX()+1400, getTopY()+1200);
        hailey.setDialogue("HAILEY: I think that JANICE is telling the truth about the affair! "
        		+ "I saw her and some of her friends at JED's house a lot in the past couple of months!");
        addTownNPC(hailey);
        
        NPC hannah = new NPC("Character sprites/hannahOverworld.png", "Character sprites/hannahBattle.png", getLeftX()+1475, getTopY()+1200);
        hannah.setDialogue("HANNAH: That JANICE girl keeps going to the news with made-up stories. "
        		+ "You can tell she's just trying to get famous - I wouldn't believe anything she says!");	
        addTownNPC(hannah);
        
        NPCRectangle randy = new NPCRectangle("Character sprites/randyOverworld.png", "Character sprites/randyBattle.png", getLeftX()+1575, getTopY()+25);
        randy.setDialogue("RANDY:  The day before JED died, JED was with DENISE and SOYA in a restaurant "
        		+ "when SOYA just suddenly stormed out. They hadn’t even been served yet!");
        randy.setPath("RIGHT", 300, "DOWN", 300, 125);
        randy.setWaitTimes(1, 0, 0, 0);
        addTownNPC(randy);
        
        NPC_LShape alyssa = new NPC_LShape("Character sprites/alyssaOverworld.png", "Character sprites/alyssaBattle.png", getLeftX()+893, getTopY()+816);
        alyssa.setDialogue("ALYSSA:  I heard that NIGEL sold a story to the tabloids about Jed "
        		+ "and got paid $50,000 for it.  I can’t wait for it to be published!");
        alyssa.setPath("RIGHT", 150, "DOWN", 250, 100);
        alyssa.setWaitTimes(0.5, 1.25, 0.75);
        addTownNPC(alyssa);
        
        NPCLine donnie = new NPCLine("Character sprites/donnieOverworld.png", "Character sprites/donnieBattle.png", getLeftX()+425, getTopY()+600);
        donnie.setDialogue("DONNIE:  NIGEL’s been strangely unaffected by Jed’s death. "
        		+ "I’m awful suspicious of the guy…");
        donnie.setPath("LEFT", 350, 90);
        donnie.setWaitTimes(1.5, 1);
        addTownNPC(donnie);
   
	}

	public void createTown(Player player) {
		addDecorativeBuilding("building2NoDoor.png", 50, 50);
		addDeniseHouse(306, 50);
		addDecorativeBuilding("building2NoDoor.png", 564, 50);
		
		addDecorativeBuilding("building2NoDoor.png", 125, 400);
		addDecorativeBuilding("building2NoDoor.png", 381, 400);	
		addJedHouse(637, 400);
		addDecorativeBuilding("building5NoDoor.png", 893, 416);
		addMart(1141, 337, player);		
		addDecorativeBuilding("building2NoDoor.png", 1389, 400);
		addDecorativeBuilding("building2NoDoor.png", 1645, 400);
		
		addDecorativeBuilding("building5NoDoor.png", 250, 800);
		addDecorativeBuilding("building5NoDoor.png", 500, 800);
		addDecorativeBuilding("building5NoDoor.png", 1141, 800);
		addDecorativeBuilding("building5NoDoor.png", 1389, 800);
		addDecorativeBuilding("building5NoDoor.png", 1645, 800);
		
		addDecorativeBuilding("building2NoDoor.png", 125, 1350);
		addNurseBiancaHouse(385, 1350);	
		addDecorativeBuilding("building2NoDoor.png", 637, 1350);
		addDecorativeBuilding("building2NoDoor.png", 893, 1350);
		addNigelHouse(1389, 1350);
		addDecorativeBuilding("building2NoDoor.png", 1645, 1350);		
	}
	
	@Override
	public void addLeftBorder(GraphicsContext gc) {
		addBorder(gc, new Forest(gc, getLeftX()-48,getTopY()-48, 48, 48*14));
		addBorder(gc, new Forest(gc, getLeftX()-48,getTopY()+48*25, 48, 48*12));
	}
	
	@Override
	public void addBotBorder(GraphicsContext gc) {
		addBorder(gc, new Forest(gc, getLeftX()-48, getBotY(), 48*15, 48));
		addBorder(gc, new Forest(gc, getLeftX()+48*35, getBotY(), 48*7, 48));
	}
	
	@Override
	public void addTopBorder(GraphicsContext gc) {
		addBorder(gc, new Forest(gc, getLeftX()-48, getTopY()-48, 48*15, 48));
		addBorder(gc, new Forest(gc, getRightX()-48*5, getTopY()-48, 48*6, 48));
	}
	
	public void addRightBorder(GraphicsContext gc) {
		addBorder(gc, new Forest(gc, getRightX(), getTopY()-48, 48, 48*20));
		addBorder(gc, new Forest(gc, getRightX(), getTopY()+48*25, 48, 48*12));
	}
	
//	@Override
//	public void setBackground(GraphicsContext gc, String file) {
//		gc.setFill(new ImagePattern(new Image(file), leftX, topY, 24, 24, false));
//		gc.fillRect(leftX, topY, rightX-leftX, botY-topY);
//	}
	
	public void addNurseBiancaHouse(int x, int y) {   
		ArrayList<NPC> npcList = getNurseBiancaNPCs();
		ArrayList<String> interMsgs = getNurseBiancaInteractions();
		
        Interior nurseBiancaInterior = new Interior2(interMsgs);
        nurseBiancaInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("mediExterior.png", getLeftX()+x, getTopY()+y, nurseBiancaInterior)); 
	}
	
	public ArrayList<String> getNurseBiancaInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("The shelves are filled with different types of medicine.");
        interMsgs.add("It's an oil reproduction of VAN GOGH's Starry Night");
        interMsgs.add("These shelves are filled with plants and herbs--presumably alternative medicine.");
        interMsgs.add("Its today's paper with the headline:\n"
        		+ "JED's Security Guard - Missing on the Night of the Murder!");
        interMsgs.add("This silk wisteria tree almost looks real!");
        
        return interMsgs;
	}
	
	public ArrayList<NPC> getNurseBiancaNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC nurseBianca = new NPC("Character sprites/nurseOverworld.png", "Character sprites/nurseBattle.png", 243, 193);
        nurseBianca.setDialogue("NURSE BIANCA: You’re travelling all over the area? "
        		+ "Let me care for you some before you leave – those wild animals can be dangerous!");
        
        npcList.add(nurseBianca);
        return npcList;
	}
	
	public void addProfBarkerHouse(int x, int y) {
		ArrayList<NPC> npcList = getProfBarkerNPCs();
        ArrayList<String> interMsgs = getProfBarkerInteractions();
        
        Interior profBarkerInterior = new Interior2(interMsgs);
        profBarkerInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building2Door.png", getLeftX()+x, getTopY()+y, profBarkerInterior)); 
	}
	
	public ArrayList<String> getProfBarkerInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("Local Wildlife: Part 2/2\n"
        		+ "While region has the strongest influence on an animals level, "
        		+ "there are other factors as well. "
        		+ "Animals tend to be higher levelled in grass patches that travelers can avoid. "
        		+ "This is thought to be directly related to less human interference.");
        interMsgs.add("It's a picture of Prof Barker in a rainforest standing next to an okapi.");
        interMsgs.add("Local Wildlife: Part 1/2\n"
        		+ "Wild animals can be found in patches of tall grass. "
        		+ "Their strenght is categorized into 'levels' and tends to vary by region. "
        		+ "The weakest animals are in the south, while the strongest are in the west.");
        interMsgs.add("It's a journal article about the behavior of "
        		+ "some animal from another country.");
        interMsgs.add("It's a miniature pine - they're native to here!");
        
        return interMsgs;
	}
	
	public ArrayList<NPC> getProfBarkerNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC profBarker = new NPC("Character sprites/profBarkerOverworld.png", "Character sprites/profBarkerBattle.png", 243, 145);
        profBarker.setDialogue("PROF BARKER: I’m a researcher studying what factors "
        		+ "affect the strength of the local wildlife. "
        		+ "I have some notes scattered around here that will be helpful to any travelers!");
        
        npcList.add(profBarker);
        
        return npcList;
	}
	
	public void addNigelHouse(int x, int y) {
        ArrayList<NPC> npcList = getNigelNPCs();
        ArrayList<String> interMsgs = getNigelInteractions();
        
        Interior nigelInterior = new Interior1(interMsgs);
        nigelInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, nigelInterior)); 
	}
	
	public ArrayList<String> getNigelInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("The shelves are filled with albums, save for a 'DJing for Dummies' book.");
        interMsgs.add("The television is off.");
        
        return interMsgs;
	}
	
	public ArrayList<NPC> getNigelNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC nigel = new NPC("Character sprites/nigelOverworld.png", "Character sprites/nigelBattle.png", 100, 195);
        nigel.setDialogue("NIGEL: I worked as JED’s security guard. "
        		+ "Did you know he was getting sent anonymous letters saying things like "
        		+ "“If I can’t have you, nobody can” in the weeks before his murder? "
        		+ "DENISE and JANICE were both obsessed with him…");
        
        NPC martha = new NPC("Character sprites/marthaOverworld.png", "Character sprites/marthaBattle.png", 244, 240);
        martha.setDialogue("MARTHA: My hubby's trying to make it big as a DJ "
        		+ "now that he's lost his job with JED!");
        
        npcList.add(nigel);
        npcList.add(martha);
        
        return npcList;
	}
	
	public void addSoyaHouse(int x, int y) {
		ArrayList<NPC> npcList = getSoyaNPCs();
		ArrayList<String> interMsgs = getSoyaInteractions();
		
        Interior soyaInterior = new Interior1(interMsgs);
        soyaInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building2Door.png", getLeftX()+x, getTopY()+y, soyaInterior)); 
	}
	
	public ArrayList<String> getSoyaInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("The shelves are lined with SOYA’s Grammys and other awards.");
        interMsgs.add("A murder investigation show is on.");
        
        return interMsgs;
	}
	
	public ArrayList<NPC> getSoyaNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC soya = new NPC("Character sprites/soyaOverworld.png", "Character sprites/soyaBattle.png", 244, 240);
        soya.setDialogue("SOYA: Hearing about JED sure was a shocker. "
        		+ "See, I became friends with him when I transitioned from singing to acting, "
        		+ "and we were just starting to work on writing some scripts together.");
        npcList.add(soya);
        
        return npcList;
	}
	
	public void addJedHouse(int x, int y) {
        ArrayList<String> interMsgs = getJedInteractions();
        Interior jedInterior = new Interior2(interMsgs);
        
        getTownBuildings().add(new Building("building2Door.png", getLeftX()+x, getTopY()+y, jedInterior));
	}
	
	public ArrayList<String> getJedInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("There's a loose piece of paper:\n "
        		+ "Talk to lawyer about HILDA \n"
        		+ "$200,000 loan\n"
        		+ "Nothing paid back");
        interMsgs.add("An old picture of JED and HUMPHREY at an award show");
        interMsgs.add("There's a post-it note on one of the shelves:\n"
        		+ "HUMPHREY\n"
        		+ "9401 Spruce Lane\n"
        		+ "North City");
        interMsgs.add("JED,\nI gave you scripts weeks ago and you STILL haven't got me "
        		+ "a meeting with any big directors or producers. "
        		+ "I'm starting to get pissed, and if you don't follow through with "
        		+ "your promises you'll be sorry. I'm even more famous than you are "
        		+ "and I know people who can really, truly bring you down.\n- Soya");
        interMsgs.add("The tree looks like it's starting to die");
        
        return interMsgs;
	}
	
	public void addDeniseHouse(int x, int y) {
        ArrayList<NPC> npcList = getDeniseNPCs();
        ArrayList<String> interMsgs = getDeniseInteractions();
		
        Interior deniseInterior = new Interior1(interMsgs);
        deniseInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building2Door.png", getLeftX()+x, getTopY()+y, deniseInterior));    
	}
	
	public ArrayList<NPC> getDeniseNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC denise = new NPC("Character sprites/deniseOverworld.png", "Character sprites/deniseBattle.png", 100, 190);
        denise.setDialogue("DENISE: I just loved working with JED on our soap opera. "
        		+ "It's a shame he's gone - I'm simply heartbroken! "
        		+ "Even worse, there are now so many people trying to claim fame by saying he slept with them. "
        		+ "That's just nonsense, Jed only ever loved me!");
        npcList.add(denise);
        
        return npcList;
	}
	
	public ArrayList<String> getDeniseInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("You open up a leather-bound book to a page dated last Thursday. \n"
        		+ "JED's stopped returning my calls and he was cold with me on set today. "
        		+ "Just last week he couldn't keep his hands off me! "
        		+ "Does he think he's too good for me now? "
        		+ "Me, THE hottest star in the country? "
        		+ "No one but me could deserve someone as good-looking as him.");
        interMsgs.add("DENISE has the television set to one of her own movies.");
        
        return interMsgs;
	}
	
}
