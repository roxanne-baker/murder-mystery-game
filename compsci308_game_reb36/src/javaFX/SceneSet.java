package javaFX;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SceneSet extends Scene {
	//Scene interior = new SceneSet(input, root2, 512, 512, Color.BLACK);	
	private ArrayList<String> input;
	
	public SceneSet(ArrayList<String> keys, Group group, double width, double height) {
		super(group, width, height);
		input = keys;
        this.setOnKeyPressed(new handleKeyPressed(input));
        this.setOnKeyReleased(new handleKeyRelease(input));
	}
	
	public SceneSet(ArrayList<String> keys, Group group, double width, double height, Color fill) {
		super(group, width, height, fill);
		input = keys;
        this.setOnKeyPressed(new handleKeyPressed(input));
        this.setOnKeyReleased(new handleKeyRelease(input));
	}
	
	
	

}

	

