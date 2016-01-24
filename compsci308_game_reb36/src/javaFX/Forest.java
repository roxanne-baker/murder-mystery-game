package javaFX;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Forest extends Sprite{

	private GraphicsContext gc;
	
	public Forest(GraphicsContext gc, double xPos, double yPos, double width, double height) {
		this.gc = gc;
		setLeftX(xPos);
		setTopY(yPos);	
		setWidth(width);
		setHeight(height);
	}
	
	public void drawForest() {
		gc.setFill(new ImagePattern(new Image("tree.png"), getLeftX(), getTopY(), 48, 48, false));
		gc.fillRect(getLeftX(), getTopY(), getWidth(), getHeight());
	}
}
