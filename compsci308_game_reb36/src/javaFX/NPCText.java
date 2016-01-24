package javaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class NPCText extends TextBox {

	private Player player;
	private String dialogue;
	private boolean atEnd = true;
	private boolean startBattle;
	public final String accuseNPC;
	
	public NPCText(String text) {
		super("");
		dialogue = text;
		int colonLoc = dialogue.indexOf(": ");
		String name = dialogue.substring(0, colonLoc);
		getArrow().requestFocus();
		accuseNPC = ("Accuse "+name+" of being the murderer?\n"
				+ "  YES        NO");
		 
        getArrow().setNextTopMarg(getArrow().BOT);
        getArrow().setNextLeftMarg(getArrow().RIGHT);
		atEnd = false;
    	StackPane.setMargin(this, new Insets(450, 253, 33, 30)); 
	}
	
	@Override
	public boolean showTextbox(ArrayList<String> input, Group root) {
		this.setBackground();
		
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
		if (input.contains("ENTER")) setEnterPressed(true);

		if (input.contains("ENTER") && !isTextBoxOpen()) {
    		this.setMessage(dialogue);
        	setEnterPressed(false);
        	setTextBoxOpen(true);
        }
		else if (this.getText().equals(dialogue) && !root.getChildren().contains(this) && changeText) {
			setEnterPressed(false);
        	root.getChildren().add(this);
			this.requestFocus();
		}
		else if (this.getText().equals(dialogue) && changeText) {
			setEnterPressed(false);
			if (!root.getChildren().contains(getStack()))
				root.getChildren().add(getStack());
			int colonLoc = dialogue.indexOf(": ");
			String name = dialogue.substring(0, colonLoc);
			getArrow().requestFocus();
			this.setMessage(accuseNPC);
		}
		else if (this.getText().equals(accuseNPC) && changeText) {
			setEnterPressed(false);
			if (getArrow().getCurrLeftMarg() == 30) {
				setStartBattle(true);
			}
			root.getChildren().remove(getStack());
			root.getChildren().remove(this); 
			setTextBoxOpen(false);
			this.setMessage("");
		}
		return isTextBoxOpen();
	}

	public boolean isStartBattle() {
		return startBattle;
	}

	public void setStartBattle(boolean startBattle) {
		this.startBattle = startBattle;
	}
}