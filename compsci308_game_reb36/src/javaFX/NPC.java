package javaFX;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class NPC extends Combatant {

	private NPCText dialogue;
	private double startX;
	private double startY;
	
	private double speedX1;
	private double speedY1;
	private double speedX2;
	private double speedY2;
	
	private double timeStopped = 0;
	
	public NPC() {
	}
	
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
	
	public NPC(Image filename, double x, double y) {
		this.setImage(filename);
		this.setPosition(x, y);	
		setStartX(x);
		setStartY(y);
	}
	
	public void move(double time) {};
	
	public void setDialogue(String message) {
		int colonLoc = message.indexOf(": ");
		setName(message.substring(0, colonLoc));
		dialogue = new NPCText(message);
	}
	
	public void setFirstDirection(String fstDirn, double speed) {		
		if (fstDirn == "LEFT") {
			setSpeedX1(-speed);
			setSpeedY1(0);
		}
		else if (fstDirn == "RIGHT") {
			setSpeedX1(speed);
			setSpeedY1(0);
		}
		else if (fstDirn == "UP") {
			setSpeedX1(0);
			setSpeedY1(-speed);
		}
		else if (fstDirn == "DOWN") {
			setSpeedX1(0);
			setSpeedY1(speed);
		}
	}
	
	public void setSecondDirection(String sndDirn, double speed) {		
		if (sndDirn == "LEFT") {
			setSpeedX2(-speed);
			setSpeedY2(0);
		}
		else if (sndDirn == "RIGHT") {
			setSpeedX2(speed);
			setSpeedY2(0);
		}
		else if (sndDirn == "UP") {
			setSpeedX2(0);
			setSpeedY2(-speed);
		}
		else if (sndDirn == "DOWN") {
			setSpeedX2(0);
			setSpeedY2(speed);
		}
	}
	
	public void stopAndGo(double passedTime, double waitTime, double nextXSpeed, double nextYSpeed) {
		if (getTimeStopped() <= waitTime) {
			this.setVelocity(0, 0);
			setTimeStopped(getTimeStopped() + passedTime);
		}
		else {
			this.setVelocity(nextXSpeed, nextYSpeed);
			setTimeStopped(0);
		}
	}
	
	public boolean getCond(double speedX, double speedY, double legLength) {
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
	
	public boolean atEndOfLeg(double speedX1, double speedY1, double legLength1, double speedX2, double speedY2, double legLength2) {
		boolean firstCond = false;
		boolean endCond = false;
		firstCond = getCond(speedX1, speedY1, legLength1);
		endCond = getCond(speedX2, speedY2, legLength2);
		return firstCond && endCond;
	}

	public NPCText getDialogue() {
		return dialogue;
	}

	public void setDialogue(NPCText dialogue) {
		this.dialogue = dialogue;
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
