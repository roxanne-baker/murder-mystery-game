package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Town {

	private ArrayList<Sprite> decorativeBuildings;
	private ArrayList<Forest> borders;
	private ArrayList<Building> townBuildings;
	private ArrayList<NPC> outsideNPCs;
	private int leftX;
	private int rightX;
	private int topY;
	private int botY;
//	boolean visited;
	private Image backgroundImage;
	
	
	public Town(GraphicsContext gc, Image img, int x, int y, int width, int height){
		backgroundImage = img;
		decorativeBuildings = new ArrayList<Sprite>();
		setTownBuildings(new ArrayList<Building>());
		borders = new ArrayList<Forest>();
		outsideNPCs = new ArrayList<NPC>();
		setLeftX(x);
		setTopY(y);
		setRightX(x+(width-width%48));
		setBotY(y+(height-height%48));

		setBorders(gc);
	}
	
	public ArrayList<Sprite> getDecorativeBuildings() {
		return decorativeBuildings;
	}
	
	public void setDecorativeBuildings(ArrayList<Sprite> decBuildings) {
		decorativeBuildings = decBuildings;
	}
	
	public ArrayList<NPC> getTownNPCs() {
		return outsideNPCs;
	}
	
	public void setTownNPCs(ArrayList<NPC> npcList) {
		outsideNPCs = npcList;
	}
	
	public ArrayList<Forest> getBorders() {
		return borders;
	}
	
	public void addBorder(GraphicsContext gc, Forest border) {
		borders.add(border);
	}
	
	public void addTownNPC(NPC npc) {
		outsideNPCs.add(npc);
	}
	
	public void setBorders(GraphicsContext gc) {
		addLeftBorder(gc);
		addRightBorder(gc);
		addTopBorder(gc);
		addBotBorder(gc);
	}
	
	public void addLeftBorder(GraphicsContext gc) {
		borders.add(new Forest(gc, getLeftX()-48,getTopY()-48, 48, getBotY()-getTopY()+48));
	}
	
	public void addRightBorder(GraphicsContext gc) {
		borders.add(new Forest(gc, getRightX(),getTopY()-48, 48, getBotY()-getTopY()+48));
	}
	
	public void addBotBorder(GraphicsContext gc) {
		borders.add(new Forest(gc, getLeftX()-48, getBotY(), (getRightX()-getLeftX())+48, 48));
	}
	
	public void addTopBorder(GraphicsContext gc) {
		borders.add(new Forest(gc, getLeftX()-48, getTopY()-48, (getRightX()-getLeftX())+48, 48));
	}
	
	public void setBackground(GraphicsContext gc) {
		gc.setFill(new ImagePattern(backgroundImage, getLeftX(), getTopY(), 24, 24, false));
		gc.fillRect(getLeftX(), getTopY(), getRightX()-getLeftX(), getBotY()-getTopY());
	}
	
	public void addMart(double x, double y, Player player) {
		getTownBuildings().add(new BuildingMart(getLeftX()+x, getTopY()+y, player));
	}
	
	public void addDecorativeBuilding(String file, double x, double y) {
		Sprite decBuilding = new Sprite();
		decBuilding.setImage(file);
		decBuilding.setPosition(getLeftX()+x, getTopY()+y);
		decorativeBuildings.add(decBuilding);
	}

	public ArrayList<Building> getTownBuildings() {
		return townBuildings;
	}

	public void setTownBuildings(ArrayList<Building> townBuildings) {
		this.townBuildings = townBuildings;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}

	public int getBotY() {
		return botY;
	}

	public void setBotY(int botY) {
		this.botY = botY;
	}
}