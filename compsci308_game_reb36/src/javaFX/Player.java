package javaFX;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class Player extends Combatant {
	
	private boolean cantMove = false;
	private double npcVX = 0; // X speed of NPC being interacted with
	private double npcVY = 0; // Y speed of NPC being interacted with
	private double movesSinceLastBattle = 10;
	
	private int money = 1000;
	private int experience = 1000;
	private ArrayList<String> itemNames = new ArrayList<String>();
	private ArrayList<Integer> itemNums = new ArrayList<Integer>();
	
	public void initItems() {
		getItemNames().add("POTION");
		getItemNames().add("HI POTION");
		getItemNames().add("PARLYZ HEAL");
		for(int i=0;i<getItemNames().size();i++)
			getItemNums().add(0);
	}
	
	public void addItem(String itemName) {
		int itemInd = getItemNames().indexOf(itemName);
		if (itemInd >= 0)
			getItemNums().set(itemInd, getItemNums().get(itemInd)+1);
	}
	
	public void initDisplay() {
        setDisplay(new Text());
        getDisplay().setFont(Font.font("Courier New", FontPosture.REGULAR, 30));
        getDisplay().setLayoutX(250);
        getDisplay().setLayoutY(315); 
        getDisplay().setText("Lv: "+getLevel()+"\nHP: "+getCurrHP()+" / "+getMaxHP());
	}
	
	public void updateDisplay() {
		String parMsg;
		if (getParalyzed() > 0) parMsg = " PAR";
		else parMsg = "";
        getDisplay().setText("Lv: "+getLevel()+parMsg+"\nHP: "+getCurrHP()+" / "+getMaxHP());
	}
	
	public void move(ArrayList<String> input) {
		if (!isCantMove()) {
	        if (input.contains("LEFT")) 
	        	this.addVelocity(-100,0);
	        
	        else if (input.contains("RIGHT")) 
	        	this.addVelocity(100,0);
	        
	        else if (input.contains("DOWN")) 
	        	this.addVelocity(0,100);
	        
	        else if (input.contains("UP")) 
	        	this.addVelocity(0,-100);
		}
	}
	
	public boolean moveOutsideObject(Sprite object) {
		boolean moved = false;
		double x = this.getLeftX();
		double y = this.getTopY();	
		
		if (!isCantMove()) {
	        if (object.bordersTop(this)) {
	        	y = object.getTopY()-this.getHeight()-1;
	        	moved = true;
	        }
	        
	        else if (object.bordersBot(this)) {
	        	y = object.getBotY()+1;
	        	moved = true;
	        }
	        else if (object.bordersLeft(this)) {
	        	x = object.getLeftX()-this.getWidth()-1;
	        	moved = true;
	        }
	        
	        else if (object.bordersRight(this)) {
	        	x = object.getRightX()+1;
	        	moved = true;
	        }   
		}
        this.setPosition(x, y);
        return moved;
	}
	
	public void moveOutsideBuilding(Building building) {
		boolean moved = this.moveOutsideObject(building);
    	if (!moved && !isCantMove()) {
	        if (building.bordersDoorL(this)) {
	        	this.setPosition(building.getDoorPosX(), this.getTopY()); }
	        
	        else if (building.bordersDoorR(this)) {
	        	this.setPosition(building.getDoorPosX()+building.getDoorWidth()-this.getWidth(), this.getTopY()); }
	        
	        else if (building.bordersDoorT(this)) {
	        	this.setPosition(this.getLeftX(), building.getDoorPosY()); } 
    	}
	}
	
	public boolean moveInsideBuilding(Interior interior, ArrayList<String> input, double outsideX, double outsideY) {
    	if (!isCantMove()) {
			if (interior.bordersExit(this) && input.contains("DOWN")) {
	    		this.setPosition(outsideX, outsideY);
	    		return false;
	    	}
	    	
	    	if (interior.bordersLeft(this)) {
	        	this.setPosition(interior.getLeftX(), this.getTopY()); }
	    	
	        else if (interior.bordersBot(this)) {
	    		this.setPosition(this.getLeftX(), interior.getBotY()-this.getHeight());}
	       
	        else if (interior.bordersRight(this)) {
	        	this.setPosition(interior.getRightX()-this.getWidth(), this.getTopY()); }
	        
	        else if (interior.bordersTop(this)) {
	        	this.setPosition(this.getLeftX(), interior.getTopY()); }
    	}
    	
    	return true;
	}
	
	public boolean inInteractionRange(Sprite object, boolean[] sidesLRTB) {
		boolean inXRange = (this.getRightX() >= object.getLeftX()) && (this.getLeftX() <= object.getRightX());
		boolean inYRange = (this.getTopY() <= object.getBotY()) && (this.getBotY() >= object.getTopY());
		if (sidesLRTB == null)
			sidesLRTB = new boolean[]{true, true, true, true};
		if (sidesLRTB[0] && inYRange && (this.getRightX() > object.getLeftX()-8) && (this.getRightX() <= object.getLeftX()))
			return true;
		else if (sidesLRTB[1] && inYRange && (this.getLeftX() > object.getRightX()) && (this.getLeftX() <= object.getRightX()+8))
			return true;
		else if (sidesLRTB[2] && inXRange && (this.getBotY() < object.getTopY()) && (this.getBotY() > object.getTopY()-8))
			return true;
		else if (sidesLRTB[3] && inXRange && (this.getTopY() > object.getBotY()) && (this.getTopY() < object.getBotY()+8))
			return true;
		return false;
	}
	
	public boolean inGrass(Grass grassBlock) {
		boolean inYRange = (this.getBotY() < grassBlock.getBotY()) && (this.getBotY() > grassBlock.getTopY());
		boolean inXRange = (this.getLeftX() > grassBlock.getLeftX()) && (this.getRightX() < grassBlock.getRightX());
		return (inXRange && inYRange);
	}
	
	public boolean beginBattle(Grass grassBlock) {
		if (this.inGrass(grassBlock)) {
			if (this.getMovesSinceLastBattle() < 8 && (this.getVelocityX() != 0 || this.getVelocityY() != 0))
				this.setMovesSinceLastBattle(this.getMovesSinceLastBattle() + 1);
			else if (this.getMovesSinceLastBattle() >= 8 && (this.getVelocityX() != 0 || this.getVelocityY() != 0)){
				this.setMovesSinceLastBattle(this.getMovesSinceLastBattle() + 1);
				if (getMovesSinceLastBattle()%5 == 0) {
					Random rng = new Random();
					int num = rng.nextInt(100);
					if (num < 15) {
						this.setMovesSinceLastBattle(0);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void resetGame() {
		setLevel(1);
		setMaxHP(55);
		setCurrHP(55);
		setStats(30, 30, 30, 30);
		resetStats();
		setMoney(1000);
		setExperience(1000);	
		getItemNums().set(0, 0);
		getItemNums().set(1, 0);
		getItemNums().set(2, 0);
		this.setPosition(250, 250);
		updateDisplay();
	}
	
	public void lvlUp() {
		setLevel(getLevel() + 1);
		setMaxHP(getMaxHP() + 5);
		if (getMaxHP() == 100) setMaxHP(99);
		setCurrHP(getMaxHP());
		setAttack(getAttack() + 2);
		setDefense(getDefense() + 2);
		setAccuracy(getAccuracy() + 2);
		setEvasion(getEvasion() + 2);	
	}
	

	

	public boolean isCantMove() {
		return cantMove;
	}

	public void setCantMove(boolean cantMove) {
		this.cantMove = cantMove;
	}

	public double getNpcVX() {
		return npcVX;
	}

	public void setNpcVX(double npcVX) {
		this.npcVX = npcVX;
	}

	public double getNpcVY() {
		return npcVY;
	}

	public void setNpcVY(double npcVY) {
		this.npcVY = npcVY;
	}

	public double getMovesSinceLastBattle() {
		return movesSinceLastBattle;
	}

	public void setMovesSinceLastBattle(double movesSinceLastBattle) {
		this.movesSinceLastBattle = movesSinceLastBattle;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public ArrayList<String> getItemNames() {
		return itemNames;
	}

	public void setItemNames(ArrayList<String> itemNames) {
		this.itemNames = itemNames;
	}

	public ArrayList<Integer> getItemNums() {
		return itemNums;
	}

	public void setItemNums(ArrayList<Integer> itemNums) {
		this.itemNums = itemNums;
	}	
}