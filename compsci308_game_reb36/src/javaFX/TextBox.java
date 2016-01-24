package javaFX;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class TextBox extends ScrollPane {

	private StackPane stack;
	private Arrow arrow;
	private boolean textBoxOpen = false;
	private boolean enterReleased = false;
	private boolean enterPressed = false;
	private String text;
	private boolean atEnd = true;
	
	public TextBox() {
	}
	
	public TextBox(String message) {
		this.setMessage(message);

    	this.setPadding(new Insets(20));
    	this.setLayoutY(355);
    	
        this.setPrefSize(500, 145);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
        
        setStack(new StackPane());
        setArrow(new Arrow());
        getArrow().addArrow(getStack()); 
	}
	
	public void setMessage(String message) {
		Text text1 = new Text(message);
    	text1.setFill(Color.BLACK);
    	text1.setFont(Font.font("Courier New", FontPosture.REGULAR, 30));
    	
    	TextFlow textFlow = new TextFlow(text1);
    	textFlow.setMaxWidth(460);
    	this.setContent(textFlow);
    	this.setText(message);
	}
	
	public void setBackground() {
    	Image textboxPic = new Image("textbox.png");
    	BackgroundImage textboxImage = new BackgroundImage(textboxPic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);        
        this.setBackground(new Background(textboxImage));
	}
	
	public boolean subclassIs(String className) {
		return this.getClass().getName().equals(className);
	}
	
	public boolean showTextbox(ArrayList<String> input, Group root) {
		if (!getText().equals("")) {
	    	Image textboxPic = new Image("textbox.png");
	    	BackgroundImage textboxImage = new BackgroundImage(textboxPic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);        
	        this.setBackground(new Background(textboxImage));
			
	        if (input.contains("ENTER") && !root.getChildren().contains(this) && !isTextBoxOpen()) {
	        	root.getChildren().add(this);
	        	if(this.getClass().getName() != "javaFX.MartText")
	        		this.requestFocus();
	        	setTextBoxOpen(true); }
	        else if (!input.contains("ENTER") && isTextBoxOpen() && !isEnterReleased()) {
	        	setEnterReleased(true);
	        } 
	        else if (input.contains("ENTER") && root.getChildren().contains(this) && isTextBoxOpen() && isEnterReleased() && isAtEnd()) {
	        	setTextBoxOpen(false);
	        	}
	        else if ((!input.contains("ENTER") && !isTextBoxOpen() && isEnterReleased() && isAtEnd())) { 
	        	root.getChildren().remove(this); 
	        	setEnterReleased(false);
	        }
        }
		return (isTextBoxOpen() || isEnterReleased());
	}

	public StackPane getStack() {
		return stack;
	}

	public void setStack(StackPane stack) {
		this.stack = stack;
	}

	public Arrow getArrow() {
		return arrow;
	}

	public void setArrow(Arrow arrow) {
		this.arrow = arrow;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isEnterPressed() {
		return enterPressed;
	}

	public void setEnterPressed(boolean enterPressed) {
		this.enterPressed = enterPressed;
	}

	public boolean isTextBoxOpen() {
		return textBoxOpen;
	}

	public void setTextBoxOpen(boolean textBoxOpen) {
		this.textBoxOpen = textBoxOpen;
	}

	public boolean isAtEnd() {
		return atEnd;
	}

	public void setAtEnd(boolean atEnd) {
		this.atEnd = atEnd;
	}

	public boolean isEnterReleased() {
		return enterReleased;
	}

	public void setEnterReleased(boolean spaceReleased) {
		this.enterReleased = spaceReleased;
	}	
}
