package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class SouthTown extends Town{	
	
	public SouthTown(GraphicsContext gc, Image image, Player player, int x, int y, int width, int height){
		super(gc, image, x, y, width, height);
		createTown(player);
		addNPCs();
		
		addLeftBorder(gc);
		addRightBorder(gc);
		addBotBorder(gc);
	}
	
	public void addNPCs() {        
        NPC_LShape rick = new NPC_LShape("Character sprites/rickOverworld.png", "Character sprites/rickBattle.png", getLeftX()+925, getTopY()+275);
        rick.setDialogue("RICK:  I used to be a good friend of ZENA and her family. "
        		+ "Even though she’s claiming that JANIS disowned the family, the truth is that she disowned him! "
        		+ "Thought he was a disgrace to his Greek heritage and his family because he changed his name and went into acting.");
        rick.setPath("RIGHT", 200, "DOWN", 115, 100);
        rick.setWaitTimes(1, 1.25, 1);
        addTownNPC(rick);
        
        NPCLine ben = new NPCLine("Character sprites/benOverworld.png", "Character sprites/benBattle.png", getLeftX()+60, getTopY()+400);
        ben.setDialogue("BEN: Did you know that Jed actually grew up here? His mom still lives in town. I wonder how she’s doing...");
        ben.setPath("DOWN", 250, 90);
        ben.setWaitTimes(0.5, 0.5);
        addTownNPC(ben);
	}
	
	public void createTown(Player player) {
		addPlayerHouse(475, 450);
        addZenaHouse(0, 0);       
		addBrunoHouse(125, 450);
		addNurseAnneHouse(250, 0);
		addMart(950, 450, player);
		addDecorativeBuilding("building1NoDoor.png", 500, 0);
		addDecorativeBuilding("building1NoDoor.png", 950, 0);
	}
	
	public void addTopBorder(GraphicsContext gc) {
	}
	
	public void setBackground(GraphicsContext gc, String file) {
		gc.setFill(new ImagePattern(new Image(file), getLeftX(), getTopY(), 24, 24, false));
		gc.fillRect(getLeftX(), getTopY(), getRightX()-getLeftX(), getBotY()-getTopY());
	}
	
	public void addPlayerHouse(double x, double y) {
        ArrayList<String> interMsgs = getPlayerInteractions();
        
        Interior houseInterior = new Interior1(interMsgs);
        getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, houseInterior));
	}
	
	public ArrayList<String> getPlayerInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("The shelves are filled with your favorite books");
        interMsgs.add("Beloved actor Jed Jones murdered, leaves entire fortune to his mother. "
        		+ "Investigators looking for the one responsible!");		
        
        return interMsgs;
	}
	
	public void addZenaHouse(double x, double y) {
        ArrayList<NPC> npcList = getZenaNPCs();
        ArrayList<String> interMsgs = getZenaInteractions();
        
        Interior zenaInterior = new Interior2(interMsgs);
        zenaInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, zenaInterior));
	}
	
	public ArrayList<NPC> getZenaNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC zena = new NPC("Character sprites/zenaOverworld.png", "Character sprites/zenaBattle.png", 100, 190);
        zena.setDialogue("ZENA:  I can’t believe it!  My darling Janis!  Not only did he disown his family, "
        		+ "but he went on to lead a life of shame, and now it’s killed him!");
        NPC panos = new NPC("Character sprites/panosOverworld.png", "Character sprites/panosBattle.png", 245, 190);
        panos.setDialogue("PANOS: As much as I hate to say it, the money from Janis’ death is going to be a huge relief. "
        		+ "We had no way to keep the restaurant running and fund any sort of wedding for my sister—and you know how Greek weddings are!");
        npcList.add(panos);
        npcList.add(zena);
        
        return npcList;
	}
	
	public ArrayList<String> getZenaInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
	    interMsgs.add("There are several cookbooks");
	    interMsgs.add("There's what looks like a family picture with at least 30 people!");
	    interMsgs.add("There are more wedding planning books than any one person could need!");
	    interMsgs.add("There are some notes that seem to be related to wedding planning.");
	    interMsgs.add("These fake plants seem a bit on the tacky side.");
	    
	    return interMsgs;
	}
	
	public void addBrunoHouse(double x, double y) {
        ArrayList<NPC> npcList = getBrunoNPCs();
		ArrayList<String> interMsgs = getBrunoInteractions();
        
        Interior brunoInterior = new Interior2(interMsgs);
        brunoInterior.setNPCs(npcList);
        
        getTownBuildings().add(new Building("building1Door.png", getLeftX()+x, getTopY()+y, brunoInterior));
	}
	
	public ArrayList<NPC> getBrunoNPCs() {
        ArrayList<NPC> npc = new ArrayList<NPC>();

        NPC bruno = new NPC("Character sprites/brunoOverworld.png", "Character sprites/brunoBattle.png", 243, 145);
        
        bruno.setDialogue("BRUNO:  If you’re going to go out there, you should know how t' fight! "
        		+ "Look at this here book, it’s got everything ya need t' know! "
        		+ "I also have some other notes around here somewhere that’d be useful to ya, if only I knew where they were…");
        npc.add(bruno);
        
        return npc;
	}
	
	public ArrayList<String> getBrunoInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("There are six main moves that can be used "
        		+ "when trying to fight against a wild animal, explained below. "
        		+ "You can also try to run from a battle if you do not wish to fight.\n"
        		+ "Attack – The only way to inflict physical damage on an animal\n"
        		+ "Intimidate – Scares the animal, lowers its attack\n"
        		+ "Leer – Causes animal to lower its guard, and consequentially its defense\n"
        		+ "Sweet scent – Draws the animal in, lowers their evasion\n"
        		+ "Mace – An easy way to lower the accuracy of the animal\n"
        		+ "Tranquilize – Has a low chance of temporarily paralyzing the animal");
        interMsgs.add("There's a picture of Koga at a martial arts competition.");
        interMsgs.add("Many of the books seem related to some sort of combat.");
        interMsgs.add("There are four main stats that are relevant to fighting - "
        		+ "attack, defense, accuracy, and evasion. "
        		+ "These stats differ between different types of animals.  See below for specifics…\n"
        		+ "Snake - high attack, low defense\n"
        		+ "Owl - high defense, low evasion\n"
        		+ "Rat - high accuracy, low attack\n"
        		+ "Spider - high evasion, low accuracy");
        interMsgs.add("The plants are healthy and thriving.");
        return interMsgs;
	}
	
	public void addNurseAnneHouse(double x, double y) {
		ArrayList<NPC> npcList = getNurseAnneNPCs();
        ArrayList<String> interMsgs = getNurseAnneInteractions();
		
        Interior nurseAnneInterior = new Interior1(interMsgs);
        nurseAnneInterior.setNPCs(npcList);

        getTownBuildings().add(new Building("mediExterior.png", getLeftX()+x, getTopY()+y, nurseAnneInterior));
	}
	
	public ArrayList<NPC> getNurseAnneNPCs() {
        ArrayList<NPC> npcList = new ArrayList<NPC>();
        NPC nurseAnne = new NPC("Character sprites/nurseOverworld.png", "Character sprites/nurseBattle.png", 100, 240);
        nurseAnne.setDialogue("NURSE ANNE: Are you feeling unwell? "
        		+ "Here, let me make sure you’re at your best! "
        		+ "I have relatives in all of the nearby towns who are nurses too, "
        		+ "so be sure to visit them if you need it!");
        npcList.add(nurseAnne);
        
        return npcList;
	}
	
	public ArrayList<String> getNurseAnneInteractions() {
        ArrayList<String> interMsgs = new ArrayList<String>();
        interMsgs.add("There's a mixture of popular novels and medical books.");
        interMsgs.add("The television is off.");
        
        return interMsgs;
	}
}
