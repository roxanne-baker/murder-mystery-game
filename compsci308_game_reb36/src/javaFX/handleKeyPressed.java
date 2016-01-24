package javaFX;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class handleKeyPressed implements EventHandler<KeyEvent> {
	
	private ArrayList<String> input;
	
	public handleKeyPressed(ArrayList<String> keysPressed) {
		input = keysPressed;
	}
	
    public void handle(KeyEvent e)
    {
        String code = e.getCode().toString();
        if ( !input.contains(code) )
            input.add( code );
    }
}
