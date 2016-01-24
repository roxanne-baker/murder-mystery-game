package javaFX;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

public class MartText extends TextBox {
	private Player player;
	private int potNum = 1;
	private int potPrice = 100;
	private int sPotNum = 1;
	private int sPotPrice = 250;
	private int parHealNum = 1;
	private int parHealPrice = 150;

	private boolean buying = false;
	private boolean selling = false;
	
	private boolean open = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean backPressed = false;
	private boolean backChange = false;
	
	public final String martIntro = "How may I help you?";
	public final String buyOrSellMenu = "  BUY\n  SELL\n  EXIT";
	public final String thankedForPurchase = "Thank you for your business.  Please come again.";
	
	private String item = "";
	private String itemSelect = "";
	private String itemConfirm = "";
	
	public MartText(Player player) {
		super("How may I help you?");  // can
		
		setAtEnd(false);
		this.setPlayer(player);
    	StackPane.setMargin(this, new Insets(450, 253, 33, 30)); 
	}  
	
	public void updateMessage() {
		double multiplier = 1;
		if (selling) {
			multiplier = 0.5;
		}
		
		String potMsg = "  POTION       x"+getPotNum()+" $"+((int) (potPrice*multiplier*getPotNum()));
		String sPotMsg = "  HI POTION    x"+getsPotNum()+" $"+((int) (sPotPrice*multiplier*getsPotNum()));
		String parHealMsg = "  PARLYZ HEAL  x"+getParHealNum()+" $"+((int) (parHealPrice*multiplier*getParHealNum()));
		setItemSelect(potMsg+"\n"+sPotMsg+"\n"+parHealMsg);
		this.setMessage(getItemSelect());
	}
	
	public void buySellItem(ArrayList<String> input, String item, int itemNum, int itemPrice) {
		backChange = false;
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
		String buyOrSell = "";
		
		int itemInd = getPlayer().getItemNames().indexOf(item);
		int itemMax = 99;
		if (buying) {
			buyOrSell = "buy ";
			itemMax = Math.min(99-getPlayer().getItemNums().get(itemInd), getPlayer().getMoney()/itemPrice);
		}
		if (selling) {
			buyOrSell = "sell ";
			itemMax = Math.min(99, getPlayer().getItemNums().get(itemInd));
		}
		
		boolean updated = updateItem(input, item, itemNum, itemMax);
		if (!updated && changeText) {
			this.item = item;
			setEnterPressed(false);
			getArrow().setNextTopMarg(450);
			getArrow().setNextLeftMarg(30);
			setItemConfirm("You want to "+buyOrSell+itemNum+" "+item+" for "+(itemNum*itemPrice)+" money?"
					+ "\n  YES        NO");
			this.setMessage(getItemConfirm());
		}
	}
	
	public boolean updateItem(ArrayList<String> input, String item, int itemNum, int itemMax) {
		boolean upChange = (!input.contains("UP") && upPressed);
		boolean downChange = (!input.contains("DOWN") && downPressed);
		upPressed = upPressed && (itemNum < itemMax);
		downPressed = downPressed && (itemNum > 1);
		if (upChange && itemNum < itemMax) {
			upPressed = false;
			if (item == "POTION") setPotNum(getPotNum() + 1);
			if (item == "HI POTION") setsPotNum(getsPotNum() + 1);
			if (item == "PARLYZ HEAL") setParHealNum(getParHealNum() + 1);
			return true;
		}
		else if (downChange && itemNum > 1) {
			downPressed = false;
			if (item == "POTION") setPotNum(getPotNum() - 1);
			if (item == "HI POTION") setsPotNum(getsPotNum() - 1);
			if (item == "PARLYZ HEAL") setParHealNum(getParHealNum() - 1);
			return true;
		}
		return false;
	}
	
	public void resetMart() {
		buying = false;
		selling = false;
		setEnterPressed(false);
		backChange = false;
		backPressed = false;
		
		getArrow().setNextTopMarg(getArrow().TOP);
		getArrow().setNextLeftMarg(getArrow().LEFT);
		this.setMessage(buyOrSellMenu);
		setPotNum(1);
		setsPotNum(1);
		setParHealNum(1);
	}
	
	public void goBack() {
		getArrow().setNextLeftMarg(getArrow().LEFT);
		setPotNum(1);
		setsPotNum(1);
		setParHealNum(1);
		backPressed = true;
	}
		
	public void martIntro(ArrayList<String> input, Group root) {
		if (!input.contains("ENTER") && !isOpen()) setOpen(true);
		
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
		if (this.getText().startsWith("How may") && changeText) {
			setEnterPressed(false);
			this.setMessage(buyOrSellMenu);
		}
		else if (this.getText().equals(buyOrSellMenu)) {
			if (!root.getChildren().contains(getStack()))
				root.getChildren().add(getStack());
			if (changeText) {
				setEnterPressed(false);
				if (getArrow().atTop()) {
					buying = true;
					this.updateMessage();
				}
				if (getArrow().atCenter()) {
					selling = true;
					this.updateMessage();
				}	
			}
		}
	}
	
	
	
	public void buySellItem(ArrayList<String> input) {
		if (input.contains("B"))
			goBack();
		else {
			if (input.contains("DOWN")) downPressed = true;
			else if (input.contains("UP")) upPressed = true;
			
			if (getArrow().atTop())
				buySellItem(input, "POTION", getPotNum(), potPrice);
			else if (getArrow().atCenter())
				buySellItem(input, "HI POTION", getsPotNum(), sPotPrice);
			else if (getArrow().atBot())
				buySellItem(input, "PARLYZ HEAL", getParHealNum(), parHealPrice);
		}
	}
	
	public int[] getItemInfo(String item) {
		int[] itemInfo = new int[]{0, 0, 0, 0};
		double multiplier = 1;
		if (selling) multiplier = 0.5;
		
		if (item == "POTION") {
			itemInfo[0] = getPotNum();
			itemInfo[1] = (int) (getPotPrice()*multiplier);
		}
		if (item == "HI POTION") {
			itemInfo[0] = getsPotNum();
			itemInfo[1] = (int) (getsPotPrice()*multiplier);
		}
		if (item == "PARLYZ HEAL") {
			itemInfo[0] = getParHealNum();
			itemInfo[1] = (int) (getParHealPrice()*multiplier);
		}
		itemInfo[2] = getPlayer().getItemNames().indexOf(item);
		itemInfo[3] = getPlayer().getItemNums().get(itemInfo[2]);
		return itemInfo;
	}
	
	public void martEnd(ArrayList<String> input, Group root) {
		int[] itemInfo = getItemInfo(item);
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
	
		if (getArrow().atLeft() && changeText) {
			setEnterPressed(false);
			this.setMessage(thankedForPurchase);
			if (root.getChildren().contains(getStack()))
				root.getChildren().remove(getStack());
			updatePlayerInventory(buying, itemInfo);
		}
		else if (getArrow().atRight() && changeText) {
			resetMart();
		}
	}
	
	public void updatePlayerInventory(boolean buying, int[] itemInfo) {
		if (buying) {
			getPlayer().setMoney(getPlayer().getMoney() - (itemInfo[0]*itemInfo[1]));
			getPlayer().getItemNums().set(itemInfo[2], itemInfo[3] += itemInfo[0]);
		}
		else {
			getPlayer().setMoney(getPlayer().getMoney() + (itemInfo[0]*itemInfo[1]));
			getPlayer().getItemNums().set(itemInfo[2], itemInfo[3] -= itemInfo[0]);
		}
	}
	
	public boolean canBuyOrSell() {
		if(buying) {
			if (getArrow().atTop() && getPlayer().getMoney() >= 100 && getPlayer().getItemNums().get(0) < 99) return true;
			else if (getArrow().atCenter() && getPlayer().getMoney() >= 250 && getPlayer().getItemNums().get(1) < 99) return true;
			else if (getArrow().atBot() && getPlayer().getMoney() >= 150 && getPlayer().getItemNums().get(2) < 99) return true;
		}
		if (selling) {
			if (getArrow().atTop() && getPlayer().getItemNums().get(0) >= 1) return true;
			else if (getArrow().atCenter() && getPlayer().getItemNums().get(1) >= 1) return true;
			else if (getArrow().atBot() && getPlayer().getItemNums().get(2) >= 1) return true;			
		}
		return false;
	}
	
	public void changeText(ArrayList<String> input, Group root) {
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
		
		if (input.contains("ENTER") && isOpen())
			setEnterPressed(true);	
		if (this.getText().equals(martIntro) || this.getText().equals(buyOrSellMenu)) {
			martIntro(input, root);
		}
		else if (this.getText().equals(getItemSelect())) {
			this.updateMessage();
			if (!input.contains("B") && backPressed) backChange = true;
			
			if (getArrow().atRight())
				buySellItem(input);
			else if (getArrow().atLeft()){	
				if (input.contains("B") && (backChange || !backPressed))
					resetMart();
				else if (changeText) {
					setEnterPressed(false);
					if (getArrow().atLeft() && canBuyOrSell())
						this.getArrow().setNextLeftMarg(265);	// different left margin - not the normal 225
				}
			}
		}
		else if (this.getText().equals(getItemConfirm()))
			martEnd(input, root);		
	}
	
	@Override
	public boolean showTextbox(ArrayList<String> input, Group root) {
		this.setBackground();
		boolean changeText = (!input.contains("ENTER") && isEnterPressed());
		if (input.contains("ENTER") && !root.getChildren().contains(this) && !isTextBoxOpen()) {
			resetMart();
			this.setMessage(martIntro);
        	root.getChildren().add(this);
        	setTextBoxOpen(true); }
		if (this.getText().equals(buyOrSellMenu) && getArrow().atBot() && changeText) {
			root.getChildren().remove(this); 
			root.getChildren().remove(getStack());
			setTextBoxOpen(false);
			setOpen(false);
			getArrow().setNextTopMarg(getArrow().TOP);
		}
		if (this.getText().equals(thankedForPurchase) && changeText) {
			root.getChildren().remove(this); 
			setTextBoxOpen(false);
			setOpen(false);
		}
		return isTextBoxOpen();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getPotNum() {
		return potNum;
	}

	public void setPotNum(int potNum) {
		this.potNum = potNum;
	}

	public int getPotPrice() {
		return potPrice;
	}

	public int getsPotNum() {
		return sPotNum;
	}

	public void setsPotNum(int sPotNum) {
		this.sPotNum = sPotNum;
	}

	public int getsPotPrice() {
		return sPotPrice;
	}

	public int getParHealNum() {
		return parHealNum;
	}

	public void setParHealNum(int parHealNum) {
		this.parHealNum = parHealNum;
	}

	public int getParHealPrice() {
		return parHealPrice;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getItemSelect() {
		return itemSelect;
	}

	public void setItemSelect(String itemSelect) {
		this.itemSelect = itemSelect;
	}

	public String getItemConfirm() {
		return itemConfirm;
	}

	public void setItemConfirm(String itemConfirm) {
		this.itemConfirm = itemConfirm;
	}
}
