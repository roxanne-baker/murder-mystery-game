package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EastTown extends Town {
	
	public EastTown(GraphicsContext gc, Image image, Player player, int x, int y, int width, int height){
		super(gc, image, x, y, width, height);
		createTown(player);
		addTownNPCs();
	}
	
	public void addTownNPCs() {
		NPC_LShape emily = new NPC_LShape("Character sprites/emilyOverworld.png", "Character sprites/emilyBattle.png", getLeftX()+700, getTopY()+275);
		emily.setDialogue("EMILY:  ELIZABETH moved here years ago after she broke up with JED. "
				+ "But even so, they remained close friends and confidants until the end.");
		emily.setPath("UP", 200, "RIGHT", 250, 90);
		emily.setWaitTimes(0.5, 1.25, 0.75);
		addTownNPC(emily);
	}
	
	public void createTown(Player player) {  
		addElizabethHouse(100, 425);
        addZaneHouse(450, 425);       
		addNurseJaneHouse(300, 50);
		addMart(825, 225, player);	
	}
	
	@Override
	public void addLeftBorder(GraphicsContext gc) {
	}
	
	public void addNurseJaneHouse(int x, int y) {
		ArrayList<NPC> npcList = getNurseJaneNPCs();
		ArrayList<String> interMsgs = getNurseJaneInteractions();
	  
		Interior nurseJaneInterior = new Interior1(interMsgs);
		nurseJaneInterior.setNPCs(npcList);
	  
	  getTownBuildings().add(new Building("mediExterior.png", getLeftX()+x, getTopY()+y, nurseJaneInterior));  
	}
	
	public ArrayList<NPC> getNurseJaneNPCs() {
		  ArrayList<NPC> npcList = new ArrayList<NPC>();
		  NPC nurseJane = new NPC("Character sprites/nurseOverworld.png", "Character sprites/nurseBattle.png", 243, 200);
		  nurseJane.setDialogue("NURSE JANE: Ooh, a patient! I hardly get anyone coming in here. "
		  		+ "I have just the thing to get you feeling like a million.");
		  
		  npcList.add(nurseJane);
		  
		  return npcList;
	}
	
	public ArrayList<String> getNurseJaneInteractions() {
		  ArrayList<String> interMsgs = new ArrayList<String>();
		  interMsgs.add("There are several books that seem related to knitting and crocheting.");
		  interMsgs.add("An episode of one of Jed's sitcoms is on.");
		  
		  return interMsgs;
	}
	
	public void addZaneHouse(int x, int y) {
		ArrayList<NPC> npcList = getZaneNPCs();
		ArrayList<String> interMsgs = getZaneInteractions();
				  
		Interior zaneInterior = new Interior2(interMsgs);
		zaneInterior.setNPCs(npcList);
		  
		getTownBuildings().add(new Building("building4Door.png", getLeftX()+x, getTopY()+y, zaneInterior));
	}
	
	public ArrayList<String> getZaneInteractions() {
		  ArrayList<String> interMsgs = new ArrayList<String>();
		  interMsgs.add("The books seem to be centered on spiritualism.");
		  interMsgs.add("A landscape photo of Mt. Fuji");
		  interMsgs.add("The books seem to be centered on spiritualism.");
		  interMsgs.add("Notes on ZANE's various 'realizations', but no recognizable names");
		  interMsgs.add("The bamboo plant gives the room an East Asian feel"); 
		  
		  return interMsgs;
	}
	
	public ArrayList<NPC> getZaneNPCs() {
		  ArrayList<NPC> npcList = new ArrayList<NPC>();
		  NPC zane = new NPC("Character sprites/zaneOverworld.png", "Character sprites/zaneBattle.png", 180, 90);
		  zane.setDialogue("ZANE: I believe that the killer knew JED’s secret and hated him for it.");

		  NPC lee = new NPC("Character sprites/leeOverworld.png", "Character sprites/leeBattle.png", 243, 145);
		  lee.setDialogue("LEE: ZANE is the best medium in the entire country!"
		  		+ " I'm sure his insights will help lead investigators to find the killer.");
		  
		  npcList.add(zane);
		  npcList.add(lee);
		  
		  return npcList;
	}
	
	public void addElizabethHouse(int x, int y) {
		  ArrayList<NPC> npcList = getElizabethNPCs();
		  ArrayList<String> interMsgs = getElizabethInteractions();
		  
		  Interior elizabethInterior = new Interior2(interMsgs);
		  elizabethInterior.setNPCs(npcList);
		  
		  getTownBuildings().add(new Building("building4Door.png", getLeftX()+x, getTopY()+y, elizabethInterior));
	}
	
	public ArrayList<NPC> getElizabethNPCs() {
		  ArrayList<NPC> npcList = new ArrayList<NPC>();
		  NPC elizabeth = new NPC("Character sprites/elizabethOverworld.png", "Character sprites/elizabethBattle.png", 100, 145);
		  elizabeth.setDialogue("ELIZABETH: It’s so shocking that JED’s gone! "
		  		+ "I just hated when he switched to daytime operas, it was a waste of his ability. "
		  		+ "Ah well—that’s why I left the city years ago, it suppresses artistic talent.");

		  NPC mary = new NPC("Character sprites/maryOverworld.png", "Character sprites/maryBattle.png", 243, 145);
		  mary.setDialogue("MARY: My sister here was just offered a role in that director HUMPHREY’s "
		  		+ "new movie, but she insists she won’t go back to film!");
		  
		  npcList.add(elizabeth);
		  npcList.add(mary);
		  
		  return npcList;
	}
	
	public ArrayList<String> getElizabethInteractions() {
		  ArrayList<String> interMsgs = new ArrayList<String>();
		  interMsgs.add("Most of the books are old classic novels.");
		  interMsgs.add("There's a picture of ELIZABETH with JED and some other stars in the film industry.");
		  interMsgs.add("This shelf is loaded with theater scripts.");
		  interMsgs.add("Stop spreading lies about JED’s love life—"
		  		+ "you’re just mad that JED broke up with you and started sleeping with me instead.\n"
		  		+ "- DENISE");
		  interMsgs.add("The plants seem to be very carefully maintained.");
		  
		  return interMsgs;
	}
}
