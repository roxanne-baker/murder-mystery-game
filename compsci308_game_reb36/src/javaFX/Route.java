package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Route {

	private ArrayList<Grass> grassPatches;
	private ArrayList<Forest> forestPatches;
	private int leftX;
	private int topY;
	private int width;
	private int height;
	
	public Route(int xPos, int yPos, int width, int height) {
		setLeftX(xPos);
		setTopY(yPos);
		this.width = width;
		this.height = height;
		
		setGrassPatches(new ArrayList<Grass>());
		setForestPatches(new ArrayList<Forest>());
	}
	
	public void setBackground(GraphicsContext gc) {
		gc.setFill(new ImagePattern(new Image("heavyGrass.png"), getLeftX(), getTopY(), 32, 32, false));
		gc.fillRect(getLeftX(), getTopY(), width, height);
	}

	public ArrayList<Grass> getGrassPatches() {
		return grassPatches;
	}

	public void setGrassPatches(ArrayList<Grass> grassPatches) {
		this.grassPatches = grassPatches;
	}

	public ArrayList<Forest> getForestPatches() {
		return forestPatches;
	}

	public void setForestPatches(ArrayList<Forest> forestPatches) {
		this.forestPatches = forestPatches;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}
	
}
