package javaFX;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.StackPane;

public class Inventory extends TextBox {

	
	private Player player;
	private Group root;
	
	private String item = "";
	private int num = 0;
	private String main;
	private String confirmItem;
	private String itemUse;
	
	public Inventory(Player player) {
		super("");
		this.setPlayer(player);
		this.setBackground();
		
		this.resetMessage();
	}
	
	public void resetMessage() {
		
		getArrow().setNextLeftMarg(175);
		getArrow().setNextTopMarg(382);
		
		String money = ""+getPlayer().getMoney();
		while (money.length() < 9)
			money += " ";
		String hpString = ""+getPlayer().getCurrHP()+"/"+getPlayer().getMaxHP();
		if (getPlayer().getCurrHP() < 10) hpString = " "+hpString;
		setMain("Stats:    POTION      x"+getPlayer().getItemNums().get(0)
				+"\n"+hpString+"HP   HI POTION   x"+getPlayer().getItemNums().get(1)
				+"\n$"+money+"PARLYZ HEAL x"+getPlayer().getItemNums().get(2));
		
		this.setMessage(getMain());
		
	}
	
	public void useItemSelect(boolean changeText) {
		if (changeText) {
			setEnterPressed(false);
			if (getArrow().atLeft()) {
				getRoot().getChildren().remove(getStack());
				setItemUse("You use a "+getItem()+" and gain "+getNum()+"HP!");
				this.setMessage(itemUse);
			}
			else if (getArrow().atRight()) {
				resetMessage();
			}
		}
	}
	
	public void itemUsed(boolean changeText) {
		if (changeText) {
			setEnterPressed(false);
			int itemInd = getPlayer().getItemNames().indexOf(getItem());
			int currItemNum = getPlayer().getItemNums().get(itemInd);
			getPlayer().getItemNums().set(itemInd, currItemNum - 1);
			getPlayer().setCurrHP(getPlayer().getCurrHP() + getNum());
			resetMessage();
		}
	}
	
	public void selectItem(boolean changeText) {
		if (changeText) {
			setEnterPressed(false);
			if (getArrow().atTop()) {
				setItem("POTION");
				setNum(Math.min(35, getPlayer().getMaxHP() - getPlayer().getCurrHP()));
			}
			else if (getArrow().atCenter()) {
				setItem("HI POTION");
				setNum(Math.min(80, getPlayer().getMaxHP() - getPlayer().getCurrHP()));				
			}
			int itemInd = getPlayer().getItemNames().indexOf(getItem());
			if (getPlayer().getItemNums().get(itemInd) > 0) {
				getArrow().setNextTopMarg(getArrow().BOT);
				getArrow().setNextLeftMarg(getArrow().LEFT);
				setConfirmItem("Do you want to use a "+getItem()+" and gain "+getNum()+" HP?\n  YES        NO");
				this.setMessage(confirmItem);
			}
		}
	}
	
	public void changeText(ArrayList<String> input) {
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
		if (input.contains("ENTER"))
			setEnterPressed(true);
		
		if (this.getText().equals(main)) {
			if(!getRoot().getChildren().contains(getStack()))
				getRoot().getChildren().add(getStack());
			selectItem(changeText);
		}
		else if (this.getText().equals(confirmItem)) {
			useItemSelect(changeText);
		}
		else if (this.getText().equals(itemUse)) {
			itemUsed(changeText);
		}
	}
	
	public boolean showTextbox(ArrayList<String> input, Group root) {
    	Image textboxPic = new Image("textbox.png");
    	BackgroundImage textboxImage = new BackgroundImage(textboxPic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);        
        this.setBackground(new Background(textboxImage));
        
		this.setRoot(root);
		
        if (input.contains("I") && !root.getChildren().contains(this) && !isTextBoxOpen()) {
        	this.resetMessage();
        	root.getChildren().add(this);
        	root.getChildren().add(getStack());
        	setTextBoxOpen(true); }
        else if (!input.contains("I") && isTextBoxOpen() && !isEnterReleased()) {
        	setEnterReleased(true);
        } 
        else if (input.contains("I") && root.getChildren().contains(this) && isTextBoxOpen() && isEnterReleased() && isAtEnd()) {
        	setTextBoxOpen(false);
        	}
        else if ((!input.contains("I") && !isTextBoxOpen() && isEnterReleased() && isAtEnd())) { 
        	root.getChildren().remove(this); 
        	root.getChildren().remove(getStack());
        	this.resetMessage();
        	setEnterReleased(false);
        }
        if(isTextBoxOpen() || isEnterReleased()) {
        	this.changeText(input);
        	return true;
        }
        else
        	return false;
        
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Group getRoot() {
		return root;
	}

	public void setRoot(Group root) {
		this.root = root;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMain() {
		return main;
	}

	private void setMain(String inventoryMain) {
		this.main = inventoryMain;
	}

	public String getConfirmItem() {
		return confirmItem;
	}

	public void setConfirmItem(String confirmItem) {
		this.confirmItem = confirmItem;
	}

	public String getItemUse() {
		return itemUse;
	}

	public void setItemUse(String itemUse) {
		this.itemUse = itemUse;
	}
	
}
