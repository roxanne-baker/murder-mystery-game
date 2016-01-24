package javaFX;

import java.util.ArrayList;
import java.util.Arrays;

public class Interior extends Sprite {

	private ArrayList<Sprite> furniture;
	private ArrayList<TextBox> interactions;
	private ArrayList<boolean[]> interactionSidesLRTB;
	private ArrayList<NPC> npcs = new ArrayList<NPC>();
	private double exitX;
	private double exitWidth;
	
	public Interior() {
		this.setLeftX(0);
		this.setTopY(0);
		this.setFurnishings();	    
	}
	
	public Interior(String fileName, double[] doorOut) {
		this.setImage(fileName);		
		setExitX(doorOut[0]);
		setExitWidth(doorOut[1]);
	}
	
	
	
	public ArrayList<Sprite> getFurniture() {
		return furniture;
	}

	public void setFurniture(ArrayList<Sprite> furniture) {
		this.furniture = furniture;
	}

	public void setNPCs(ArrayList<NPC> list) {
		npcs = list;
	}
	
	public ArrayList<NPC> getNPCs() {
		return npcs;
	}
	
	
	public void setInteractions(ArrayList<TextBox> statements) {
		interactions = statements;
	}
	
	public ArrayList<TextBox> getInteractions() {
		return interactions;
	}
	
	public void setInteractionSides(ArrayList<boolean[]> interSides) {
		interactionSidesLRTB = 	interSides;
	}
	
	public ArrayList<boolean[]> getInteractionSides() {
		return interactionSidesLRTB;
	}
	
	public boolean bordersExit(Sprite s) {
		boolean inXRange = (s.getLeftX() >= this.getLeftX()+getExitX() && s.getRightX() <= this.getLeftX()+getExitX()+getExitWidth());
		boolean inYRange = (s.getBotY() >= this.getBotY());
		return (inXRange && inYRange);
	}
	
	public void setFurnishings() {
		ArrayList<double[]> features = new ArrayList<double[]>();
		setFurnishings(features);
	}
	
	public void setFurnishings(ArrayList<double[]> features) {
		setFurniture(new ArrayList<Sprite>());
		
		for(double[] feature: features) {
			Sprite furnishing = new Sprite();
			furnishing.setLeftX(feature[0]+this.getLeftX());
			furnishing.setTopY(feature[1]+this.getTopY());
			furnishing.setWidth(feature[2]);
			furnishing.setHeight(feature[3]);
//			furnishing.setRightX(furnishing.getLeftX()+furnishing.getWidth());
//			furnishing.setBotY() = furnishing.topY+furnishing.height;
			getFurniture().add(furnishing);
		}
	}

	public double getExitX() {
		return exitX;
	}

	public void setExitX(double exitX) {
		this.exitX = exitX;
	}

	public double getExitWidth() {
		return exitWidth;
	}

	public void setExitWidth(double exitWidth) {
		this.exitWidth = exitWidth;
	}
}