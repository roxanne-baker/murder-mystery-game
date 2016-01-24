package javaFX;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Arrow extends Rectangle {
	
	public final double TOP = 382;
	public final double MID = 416;
	public final double BOT = 450;
	public final double LEFT = 30;
	public final double RIGHT = 225; // most common right placement, but there are others
	private double nextTopMarg = TOP;
	private double nextLeftMarg = LEFT;
	private double currTopMarg = TOP;
	private double currLeftMarg = LEFT;
	
	public Arrow() {
		this.setWidth(22);
		this.setHeight(17);
		this.setFill(new ImagePattern(new Image("arrow.png")));
	}
	
	public void addArrow(StackPane stack) {
    	StackPane.setAlignment(this, Pos.TOP_LEFT);
    	StackPane.setMargin(this, new Insets(385, 253, 33, 30));
    	stack.getChildren().add(this);
	}
	
	public boolean atTop() {
		return getCurrTopMarg() == TOP;
	}
	
	public boolean atCenter() {
		return getCurrTopMarg() == MID;
	}
	
	public boolean atBot() {
		return getCurrTopMarg() == BOT;
	}
	
	public boolean atLeft() {
		return getCurrLeftMarg() == LEFT;
	}
	
	public boolean atRight() {
		return getCurrLeftMarg() >= 150;
	}
	
	public void moveArrow(ArrayList<String> input, TextBox battleText) {
    	setCurrTopMarg(StackPane.getMargin(this).getTop());
    	setCurrLeftMarg(StackPane.getMargin(this).getLeft());
    	
    	if (battleText.subclassIs("javaFX.Inventory")) {
    		moveInventoryArrow(input, (Inventory) battleText);
    	}
    	else if (battleText.subclassIs("javaFX.MartText")) {
    		moveMartArrow(input, (MartText) battleText);
    	}
    	else if (battleText.subclassIs("javaFX.BattleText")) {
    		moveCombatArrow(input, (BattleText) battleText);
    	}
    	else if (battleText.subclassIs("javaFX.NPCText")) {
    		moveNPCArrow(input, (NPCText) battleText);
    	}  
    	
    	if((getCurrTopMarg() != getNextTopMarg()) && !input.contains("UP") && !input.contains("DOWN")
    			|| (getCurrLeftMarg() != getNextLeftMarg()) && !input.contains("LEFT") && !input.contains("RIGHT"))
    		StackPane.setMargin(this, new Insets(getNextTopMarg(), 253, 33, getNextLeftMarg()));
	}
    
	
	public void moveNPCArrow(ArrayList<String> input, NPCText npc) {
    	if (npc.getText().equals(npc.accuseNPC)) {
    		moveLeftRight(input);
    	}
	}
	
	public void moveCombatArrow(ArrayList<String> input, BattleText combat) {
		moveUpDown(input, true);
    	if (combat.getText().equals(combat.listOfMoves)) {
    		moveLeftRight(input);
    	}
	}
	
	public void moveMartArrow(ArrayList<String> input, MartText mart) {
    	if (!(mart.getText().equals(mart.getItemSelect()) && atRight()) && !mart.getText().equals(mart.getItemConfirm())) {
	    	moveUpDown(input, true);
    	}
    	if (mart.getText().equals(mart.getItemConfirm())) {
    		moveLeftRight(input);
    	}
	}
	
    public void moveInventoryArrow(ArrayList<String> input, Inventory inventory) {
    	if (inventory.getText().equals(inventory.getMain())) {
	    	moveUpDown(input, false);
    	}
    	if (inventory.getText().equals(inventory.getConfirmItem())) {
    		moveLeftRight(input);
    	}
    }
    	
    public void moveUpDown(ArrayList<String> input, boolean canMoveToBottom) {
    	if((atTop() && input.contains("DOWN")) || (atBot() && input.contains("UP")))
    		setNextTopMarg(MID);
    	else if(atCenter() && input.contains("DOWN") && canMoveToBottom)
    		setNextTopMarg(BOT);
    	else if((atCenter() && input.contains("UP")))
    		setNextTopMarg(TOP);
    }
    	
    public void moveLeftRight(ArrayList<String> input) {
		if(getCurrLeftMarg() == LEFT && input.contains("RIGHT"))
			setNextLeftMarg(RIGHT);
		if(getCurrLeftMarg() == RIGHT && input.contains("LEFT"))
			setNextLeftMarg(LEFT);
    }

	public double getNextTopMarg() {
		return nextTopMarg;
	}

	public void setNextTopMarg(double nextTopMarg) {
		this.nextTopMarg = nextTopMarg;
	}

	public double getNextLeftMarg() {
		return nextLeftMarg;
	}

	public void setNextLeftMarg(double nextLeftMarg) {
		this.nextLeftMarg = nextLeftMarg;
	}

	public double getCurrTopMarg() {
		return currTopMarg;
	}

	public void setCurrTopMarg(double currTopMarg) {
		this.currTopMarg = currTopMarg;
	}

	public double getCurrLeftMarg() {
		return currLeftMarg;
	}

	public void setCurrLeftMarg(double currLeftMarg) {
		this.currLeftMarg = currLeftMarg;
	}
	

}
