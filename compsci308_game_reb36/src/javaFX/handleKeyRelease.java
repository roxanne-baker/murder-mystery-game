package javaFX;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class handleKeyRelease implements EventHandler<KeyEvent> {
	
	private ArrayList<String> input;
	
	public handleKeyRelease(ArrayList<String> currentKeys) {
		input = currentKeys;
	}
	
    public void handle(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove( code );
    }
}
