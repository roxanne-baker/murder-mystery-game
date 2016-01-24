package javaFX;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Grass extends Sprite{
	
	private GraphicsContext gc;
	
	private int lowLv = 1;
	private int highLv = 10;
	
	public Grass(GraphicsContext gc, int startLv, int endLv, double xPos, double yPos, double width, double height) {
		this.gc = gc;
		setLeftX(xPos);
		setTopY(yPos);	
		setWidth(width);
		setHeight(height);
		setLevelRange(startLv, endLv);
	}
	
	public void setLevelRange(int minLevel, int maxLevel) {
		if (minLevel <= 0) minLevel = 1;
		if (maxLevel > 10) maxLevel = 10;
		if (minLevel > maxLevel) minLevel = maxLevel;
		if (maxLevel < minLevel) maxLevel = minLevel;
		setLowLv(minLevel);
		setHighLv(maxLevel);
	}
	
	public void drawGrass() {
		gc.setFill(new ImagePattern(new Image("grass.png"), getLeftX(), getTopY(), 24, 24, false));
		gc.fillRect(getLeftX(), getTopY(), getRightX()-getLeftX(), getBotY()-getTopY());
	}


	public int getLowLv() {
		return lowLv;
	}

	public void setLowLv(int lowLv) {
		this.lowLv = lowLv;
	}

	public int getHighLv() {
		return highLv;
	}

	public void setHighLv(int highLv) {
		this.highLv = highLv;
	}
}
