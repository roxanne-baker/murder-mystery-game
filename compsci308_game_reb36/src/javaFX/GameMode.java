package javaFX;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMode extends Group {

	private Scene setting;
	private Canvas canvas;
	private GraphicsContext gc;
	
	GameMode(int x, int y) {
	    canvas = new Canvas(x, y);
	    this.getChildren().add(canvas);
	    gc = canvas.getGraphicsContext2D();
	    gc.fill(); 
	}
	
	GameMode(ArrayList<String> input, int x, int y) {
		this(x, y);
	    setting = new SceneSet(input, this, 500, 500);  
	}
	
	GameMode(ArrayList<String> input, Color fillColor, int x, int y) {
		this(x, y);
	    setting = new SceneSet(input, this, 500, 500, fillColor);  
	    
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public GraphicsContext getGC() {
		return gc;
	}
	
	
	
}
