package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WestTown extends Town{	
	
	public WestTown(GraphicsContext gc, Image image, Player player, int x, int y, int width, int height){
		super(gc, image, x, y, width, height);
		createTown(player);
		addTownNPCs();
	}
	
	public void addTownNPCs() {
        NPC allen = new NPC("Character sprites/allenOverworld.png", "Character sprites/allenBattle.png", getLeftX()+600, getTopY()+25);
        allen.setDialogue("ALLEN:  JANICE got really drunk last Saturday and started complaining about she thought JED was "
        		+ "using her for her friends. What nonsense is that, he’s the real famous one!");
        addTownNPC(allen);
        
        NPCRectangle jacob = new NPCRectangle("Character sprites/jacobOverworld.png", "Character sprites/jacobBattle.png", getLeftX()+50, getTopY()+480);
        jacob.setDialogue("JACOB:  HILDA goes to the casino an awful lot for someone so poor! "
        		+ "She got in a big argument with JED outside the casino just a week before he was killed.");
        jacob.setPath("RIGHT", 110, "DOWN", 200, 90);
        jacob.setWaitTimes(0.25, 1.75, 0.75, 1.5);
        addTownNPC(jacob);
        
        NPCLine chloe = new NPCLine("Character sprites/chloeOverworld.png", "Character sprites/chloeBattle.png", getLeftX()+1350, getTopY()+110);
        chloe.setDialogue("CHLOE: JED had been spending a lot of time at IVY’s recently, even when she wasn’t home!");
        chloe.setPath("DOWN", 175, 100);
        chloe.setWaitTimes(1, 0.5);
        addTownNPC(chloe);
        
        NPCLine michael = new NPCLine("Character sprites/michaelOverworld.png", "Character sprites/michaelBattle.png", getLeftX()+1125, getTopY()+775);
        michael.setDialogue("MICHAEL:  I hate that DENISE—she acts like she’s just the best thing in the world. "
        		+ "So entitled! Too bad she could never have the one thing she wanted — JED!");
        michael.setPath("LEFT", 250, 80);
        michael.setWaitTimes(1.5, 2);
        addTownNPC(michael);
   
	}
	
	public void createTown(Player player) {
		addDecorativeBuilding("building1NoDoor.png", 100, 100);
		addMart(348, 100, player);
		addDecorativeBuilding("building1NoDoor.png", 596, 100);
		addDecorativeBuilding("building1NoDoor.png", 844, 100);
		addNurseMayHouse(1092, 100);
		
		addDecorativeBuilding("building1NoDoor.png", 225, 500);
		addHildaHouse(473, 500);
		addDecorativeBuilding("building1NoDoor.png", 869, 500);
		addDecorativeBuilding("building1NoDoor.png", 1117, 500);
		
		addDecorativeBuilding("building1NoDoor.png", 100, 900);
		addTimHouse(348, 900);
		addIvyHouse(596, 900);
		addDecorativeBuilding("building1NoDoor.png", 844, 900);
		addJaniceHouse(1092, 900);
	}
	
	@Override
	public void addRightBorder(GraphicsContext gc) {
		addBorder(gc, new Forest(gc, getRightX(),getBotY()-48*14, 48, 48*15));
	}
	
	public void addNurseMayHouse(int x, int y) {
		ArrayList<NPC> npcList = getNurseMayNPCs();
		ArrayList<String> interMsgs = getNurseMayInteractions();

        Interior nurseMayInterior = new Interior2(interMsgs);
        nurseMayInterior.setNPCs(npcList);
      
        getTownBuildings().add(new Building("mediExterior.png", getLeftX()+x, getTopY()+y, nurseMayInterior));
	}
	
	public ArrayList<String> getNurseMayInteractions() {
	      ArrayList<String> interMsgs = new ArrayList<String>();
	      interMsgs.add("There seem to be lots of poetry collections and anthologies");
	      interMsgs.add("NURSE MAY's framed nursing degree");
	      interMsgs.add("There are some books NURSE MAY has written her own poetry in!");
	      interMsgs.add("It seems to be an inventory of medicines.");
	      interMsgs.add("The tree has beautiful pink and white flowers.");
	      
	      return interMsgs;
	}
	
	public ArrayList<NPC> getNurseMayNPCs() {
	      ArrayList<NPC> npcList = new ArrayList<NPC>();
	      NPC nurseMay = new NPC("Character sprites/nurseOverworld.png", "Character sprites/nurseBattle.png", 100, 240);
	      nurseMay.setDialogue("NURSE MAY: Here, honey, I can patch you up if you need it. "
	      		+ "No need to walk around feeling awful all day!");
	      npcList.add(nurseMay);
	      
	      return npcList;
	}
	
	public void addHildaHouse(int x, int y) {	      
	    ArrayList<NPC> npcList = getHildaNPCs();
	    ArrayList<String> interMsgs = getHildaInteractions();

	    Interior hildaInterior = new Interior1(interMsgs);
	    hildaInterior.setNPCs(npcList);
	      
	    getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, hildaInterior));
	}
	
	public ArrayList<String> getHildaInteractions() {
	      ArrayList<String> interMsgs = new ArrayList<String>();
	      interMsgs.add("The shelves are mostly empty, but there are some children's books.");
	      interMsgs.add("There's an old black-and-white comedy movie on.");
	      
	      return interMsgs;
	}
	
	public ArrayList<NPC> getHildaNPCs() {
	      ArrayList<NPC> npcList = new ArrayList<NPC>();
	      NPC hilda = new NPC("Character sprites/hildaOverworld.png", "Character sprites/hildaBattle.png", 100, 193);
	      hilda.setDialogue("HILDA: I was JED'S maid for decades!  I've seen an awful lot of things, "
	      		+ "but I never reported a one of 'em to those tabloids, that's how good I was to 'im!");
	      NPC jimmy = new NPC("Character sprites/jimmyOverworld.png", "Character sprites/jimmyBattle.png", 243, 145);
	      jimmy.setDialogue("JIMMY: My mommy said she was counting on Mr. JED leaving her something "
	      		+ "in the will, but she got nothing and was mad!");
	      
	      npcList.add(hilda);
	      npcList.add(jimmy);
	      
	      return npcList;
	}
	
	public void addIvyHouse(int x, int y) {
		ArrayList<NPC> npcList = getIvyNPCs();
		ArrayList<String> interMsgs = getIvyInteractions();
		
	    Interior ivyInterior = new Interior2(interMsgs);
	    ivyInterior.setNPCs(npcList);
	      
	    getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, ivyInterior)); 
	}
	
	public ArrayList<String> getIvyInteractions() {
	      ArrayList<String> interMsgs = new ArrayList<String>();
	      interMsgs.add("There seems to be a disproportionate number of history books.");
	      interMsgs.add("It's a picture of IVY and PATRICK on his 25th birthday.");
	      interMsgs.add("There's an empty envelope addressed for PATRICK on the top shelf.");
	      interMsgs.add("Ivy seems to be writing down ideas for material for her next show.");
	      interMsgs.add("The tree seems like it could use some watering.");
	      
	      return interMsgs;
	}
	
	public ArrayList<NPC> getIvyNPCs() {
	      ArrayList<NPC> npcList = new ArrayList<NPC>();
	      NPC ivy = new NPC("Character sprites/ivyOverworld.png", "Character sprites/ivyBattle.png", 100, 145);
	      ivy.setDialogue("IVY: I'm just so sad that JED is gone! I may have bad talked "
	      		+ "him a lot on my show, but in reality we were becoming great friends! "
	      		+ "Not like that AMORITA girl... that's some genuine hate!");
	      
	      NPC patrick = new NPC("Character sprites/patrickOverworld.png", "Character sprites/patrickBattle.png", 243, 193);
	      patrick.setDialogue("PATRICK: JED had started coming around a lot before... well, you know. "
	      		+ "My mom thinks he had a thing for her, but I can tell you for a fact that isn't true. "
	      		+ "She just doesn't know it.");
	      
	      npcList.add(ivy);
	      npcList.add(patrick);
	      
	      return npcList;
	}
	
	public void addJaniceHouse(int x, int y) {
		ArrayList<NPC> npcList = getJaniceNPCs();
		ArrayList<String> interMsgs = getJaniceInteractions();
	     	      
	    Interior janiceInterior = new Interior1(interMsgs);
	    janiceInterior.setNPCs(npcList);
	      
	    getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, janiceInterior)); 
	}
	
	public ArrayList<String> getJaniceInteractions() {
	      ArrayList<String> interMsgs = new ArrayList<String>();
	      interMsgs.add("There aren't any books - just tabloids and fashion magazines.");
	      interMsgs.add("There's an episode of a popular drama series on.");
	      
	      return interMsgs;
	}
	
	public ArrayList<NPC> getJaniceNPCs() {
	      ArrayList<NPC> npcList = new ArrayList<NPC>();
	      NPC janice = new NPC("Character sprites/janiceOverworld.png", "Character sprites/janiceBattle.png", 100, 193);
	      janice.setDialogue("JANICE: I'm JANICE from last season of Big Brother. "
	      		+ "You know, JED and I actually became super close recently, if you know what I mean. "
	      		+ "Oh, what could have been!");
	      
	      NPC sophie = new NPC("Character sprites/sophieOverworld.png", "Character sprites/sophieBattle.png", 243, 193);
	      sophie.setDialogue("SOPHIE: Ever since my friend JANICE started her new job, "
	      		+ "she’s started making connections with some big-time celebrities! "
	      		+ "Though she does exaggerate her ‘friendship’ with JED…");
	      
	      npcList.add(janice);
	      npcList.add(sophie);
	      
	      return npcList;
	}
	
	public void addTimHouse(int x, int y) {
	      ArrayList<NPC> npcList = getTimNPCs();
	      ArrayList<String> interMsgs = getTimInteractions();
	      
	      Interior timInterior = new Interior1(interMsgs);
	      timInterior.setNPCs(npcList);
	      
	      getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, timInterior)); 
	}
	
	public ArrayList<NPC> getTimNPCs() {
	      ArrayList<NPC> npcList = new ArrayList<NPC>();
	      NPC tim = new NPC("Character sprites/timOverworld.png", "Character sprites/timBattle.png", 100, 193);
	      tim.setDialogue("TIM: I used to date SOYA, and I can tell you first-hand that "
	      		+ "she can be a really violent person at times. "
	      		+ "I moved to this city just to get away from her!"); 
	      
	      npcList.add(tim);
	      
	      return npcList;
	}
	
	public ArrayList<String> getTimInteractions() {
	      ArrayList<String> interMsgs = new ArrayList<String>();
	      interMsgs.add("There are lots of health and fitness books.");
	      interMsgs.add("World-famous medium ZANE says that the secret "
	      		+ "to JED’s murder lies in a secret he never let out to the public!");
	      
	      return interMsgs;
	}
}