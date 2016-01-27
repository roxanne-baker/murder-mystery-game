package javaFX;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class NPC extends Combatant {
	// This entire file is part of my masterpiece
	// ROXANNE BAKER
	/**
	 * I feel this code is well written because
	 * it contains short functions, each with a single
	 * purpose that is obvious from its name
	 * There is no duplicated code here
	 * 
	 * This class can be used to create an NPC
	 * Subclasses allow the NPC to move
	 * 
	 * Dialogue represents the text that shows
	 * when player interacts with NPC
	 * 
	 * startX and startY represent the initial
	 * x and y coordinates of the NPC
	 * 
	 * the "speed" variables dictate the speeds
	 * for the NPC when moving in certain directions
	 * 
	 * timeStopped represents the amount of time that
	 * the NPC is stopped for in between movements
	 * 
	 * Since player can fight NPC, it extends the
	 * Combatant superclass.  Stats for the NPC
	 * are set in the constructor.
	 */
	private NPCText dialogue;
	private double startX;
	private double startY;
	private double speedX1;
	private double speedY1;
	private double speedX2;
	private double speedY2;
	private double timeStopped = 0;
	
	public NPC() {}
	
	public NPC(String overworldImage, String battleImage, double x, double y) {
		this.setImage(overworldImage);
		Image battleImg = new Image(battleImage);
		this.setBattleImage(new Rectangle(battleImg.getWidth(), battleImg.getHeight(), new ImagePattern(battleImg)));
		
		this.setPosition(x, y);	
		setStartX(x);
		setStartY(y);
		
		setLevel(10);
		setStats(48, 48, 48, 48);
		setMaxHP(99);
		setCurrHP(getMaxHP());
	}
	
	/**
	 * Empty function because in the superclass
	 * NPCs do not move.
	 * 
	 * Overwritten in subclasses.
	 * @param time
	 */
	public void move(double time) {};
	
	/**
	 * Sets the dialogue of the NPC
	 * and the name of the NPC
	 * 
	 * Assumes text is of format "NAME: text"
	 * @param message
	 */
	public void setDialogueAndName(String message) {
		int colonLoc = message.indexOf(": ");
		setName(message.substring(0, colonLoc));
		dialogue = new NPCText(message);
	}
	
	/**
	 * Sets direction that the NPC is moving
	 * @param setSpeed1
	 * @param direction
	 * @param speed
	 */
	protected void setDirection(boolean setSpeed1, String direction, double speed) {
		if (direction == "LEFT") {
			setSpeedLeg1OrLeg2(setSpeed1, true, -speed);
			setSpeedLeg1OrLeg2(setSpeed1, false, 0);
		}
		else if (direction == "RIGHT") {
			setSpeedLeg1OrLeg2(setSpeed1, true, speed);
			setSpeedLeg1OrLeg2(setSpeed1, false, 0);
		}
		else if (direction == "UP") {
			setSpeedLeg1OrLeg2(setSpeed1, true, 0);
			setSpeedLeg1OrLeg2(setSpeed1, false, -speed);
		}
		else if (direction == "DOWN") {
			setSpeedLeg1OrLeg2(setSpeed1, true, 0);
			setSpeedLeg1OrLeg2(setSpeed1, false, speed);
		}		
	}
	
	/**
	 * Sets the speed of the corresponding leg
	 * when given which leg is given and whether
	 * the "x" or "y" speed is being set
	 * @param setSpeed1
	 * @param setXSpeed
	 * @param speedValue
	 */
	private void setSpeedLeg1OrLeg2(boolean setSpeed1, boolean setXSpeed, double speedValue) {
		if (setSpeed1 && setXSpeed) {
			setSpeedX1(speedValue);
		}
		else if (setSpeed1 && !setXSpeed) {
			setSpeedY1(speedValue);
		}
		else if (!setSpeed1 && setXSpeed) {
			setSpeedX2(speedValue);
		}
		else if (!setSpeed1 && !setXSpeed) {
			setSpeedY2(speedValue);
		}
	}
	
	/**
	 * Causes the NPC to either stop or go
	 * based on the wait time and timeStopped
	 * @param passedTime
	 * @param waitTime
	 * @param nextXSpeed
	 * @param nextYSpeed
	 */
	protected void stopAndGo(double passedTime, double waitTime, double nextXSpeed, double nextYSpeed) {
		if (getTimeStopped() <= waitTime) {
			this.setVelocity(0, 0);
			setTimeStopped(getTimeStopped() + passedTime);
		}
		else {
			this.setVelocity(nextXSpeed, nextYSpeed);
			setTimeStopped(0);
		}
	}
	
	/**
	 * Determines one of the conditions
	 * for "atEndOfLeg" method
	 * @param speedX
	 * @param speedY
	 * @param legLength
	 * @return whether or not condition
	 * for "atEndOfLeg" method is true/false
	 */
	protected boolean getCond(double speedX, double speedY, double legLength) {
		if (speedX < 0) {
			return this.getLeftX() >= getStartX()+legLength;
		}
		else if (speedX > 0) {
			return this.getLeftX() <= getStartX()-legLength;
		}
		else if (speedY < 0) {
			return this.getTopY() >= getStartY()+legLength;
		}
		else if (speedY > 0) {
			return this.getTopY() <= getStartY()-legLength;
		}
		return false;
	}
	
	/**
	 * 
	 * @param speedX1
	 * @param speedY1
	 * @param legLength1
	 * @param speedX2
	 * @param speedY2
	 * @param legLength2
	 * @return whether or not the NPC
	 * is at the end of its current
	 * leg of movement
	 */
	protected boolean atEndOfLeg(double speedX1, double speedY1, double legLength1, double speedX2, double speedY2, double legLength2) {
		boolean firstCond = false;
		boolean endCond = false;
		firstCond = getCond(speedX1, speedY1, legLength1);
		endCond = getCond(speedX2, speedY2, legLength2);
		return firstCond && endCond;
	}

	public NPCText getDialogue() {
		return dialogue;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getSpeedX1() {
		return speedX1;
	}

	public void setSpeedX1(double speedX1) {
		this.speedX1 = speedX1;
	}

	public double getSpeedY1() {
		return speedY1;
	}

	public void setSpeedY1(double speedY1) {
		this.speedY1 = speedY1;
	}

	public double getSpeedX2() {
		return speedX2;
	}

	public void setSpeedX2(double speedX2) {
		this.speedX2 = speedX2;
	}

	public double getSpeedY2() {
		return speedY2;
	}

	public void setSpeedY2(double speedY2) {
		this.speedY2 = speedY2;
	}

	public double getTimeStopped() {
		return timeStopped;
	}

	public void setTimeStopped(double timeStopped) {
		this.timeStopped = timeStopped;
	}
}
