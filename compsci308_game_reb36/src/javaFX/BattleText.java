package javaFX;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BattleText extends TextBox{

	private Combatant enemy;
	private Player player;

	private boolean playerDead = false;
	private boolean enemyDead = false;
	private boolean expAdded = false;
	private String lvlUpMsg = "";
	private boolean changeText = false;
	private int escapeAttempts = 0;
	private boolean fightingNPC;
	
	public final String selectActionType = "  FIGHT\n  ITEM\n  RUN";
	public final String listOfMoves = "  ATTACK     INTIMIDATE\n  LEER       SWEET SCENT\n  MACE       TRANQUILIZE";
	
	public final String successfulEscape = "You have successfully escaped!";
	public final String failedEscape = "You fail to escape!";
	public final String playerParalyzed = "You are paralyzed and cannot attack!";
	
	public final String lostBattle = "You have lost! Game over!";
	
	// important for decision-making but slightly variable due to enemy name or player inventory
	private String enemyParalyzed;
	private String openingMsg;
	private String moveOrItemMsg;	
	private String itemMenu; 
	private String wonBattle;
	
	public BattleText(Player player, Combatant opponent) {
		super("");
		if (opponent == null) {
			setEnemy(new Combatant());
		}
		else {
			setEnemy(opponent);
		}
		getEnemy().updateDisplay();
		getEnemy().setBattle(this);
		this.player = player;
	}
	
	public BattleText(Player player, NPC npc, Group root) {
		this(player, npc);
        openingMsg = "You are now fighting "+getEnemy().getName()+"!";
		enemyParalyzed = getEnemy().getName()+" is paralyzed!  It cannot attack!";
		setFightingNPC(true);
		
		this.initiateStack(root);
        this.setMessage(openingMsg);
	}
	
	public BattleText(Player player, Grass grass, Group root) {
		this(player, null);
		getEnemy().createRandomEnemy(grass.getLowLv(), grass.getHighLv());
        openingMsg = "Wild "+getEnemy().getName()+" appeared!";
		enemyParalyzed = getEnemy().getName()+" is paralyzed!  It cannot attack!";
		setFightingNPC(false);
		
		this.initiateStack(root);
        this.setMessage(openingMsg);
	}
	
	public void initiateStack(Group root) {
        Rectangle playerBack = new Rectangle(168, 168, new ImagePattern(new Image("redBack.png")));
		getStack().getChildren().add(playerBack);
		StackPane.setAlignment(playerBack, Pos.TOP_LEFT);
		StackPane.setMargin(playerBack, new Insets(191, 0, 0, 25));
		getStack().getChildren().add(getEnemy().getBattleImage());
		StackPane.setAlignment(getEnemy().getBattleImage(), Pos.TOP_LEFT);
		StackPane.setMargin(getEnemy().getBattleImage(), new Insets(10, 0, 0, 350));
        
		getStack().getChildren().add(this);
    	StackPane.setMargin(this, new Insets(355, 0, 0, 0));
    	
		if (!root.getChildren().contains(getEnemy().getDisplay())) {
			root.getChildren().add(getEnemy().getDisplay());
		}	
		if (!root.getChildren().contains(player.getDisplay())) {
			root.getChildren().add(player.getDisplay());
		}        

		if (!root.getChildren().contains(getStack()))
			root.getChildren().add(getStack());
		

	}
	
	public void selectAction() {
		player.updateDisplay();
		getEnemy().updateDisplay();
		if (changeText) {
			setEnterPressed(false);
			if (getArrow().atTop())
				this.setMessage(listOfMoves);
			else if (getArrow().atCenter()) {
				getArrow().setNextLeftMarg(getArrow().LEFT);
				getArrow().setNextTopMarg(getArrow().TOP);
				setItemMenu("  POTION      x"+player.getItemNums().get(0)+
						"\n  HI POTION   x"+player.getItemNums().get(1)+
						"\n  PARLYZ HEAL x"+player.getItemNums().get(2));
				this.setMessage(getItemMenu());
			}
			else if (getArrow().atBot() && !isFightingNPC()) {
				double border = (128*((double) player.getEvasion()/getEnemy().getEvasion()))+30*escapeAttempts;
				Random escapeRNG = new Random();
				int escapeNum = escapeRNG.nextInt(256);
				if (escapeNum < border) {
					this.setMessage(successfulEscape);
				}
				else {
					this.setMessage(failedEscape);
					escapeAttempts += 1;
				}
			}
		}
	}
	
	
	
	public void selectItem() {
		setEnterPressed(false);
		if (getArrow().atTop() && player.getItemNums().get(0) > 0) {
			useItem(0, 35);
		}
		else if (getArrow().atCenter() && player.getItemNums().get(1) > 0) {
			useItem(1, 80);
		}
		else if (getArrow().atBot() && player.getItemNums().get(2) > 0 && player.getParalyzed() > 0) {
			useItem(2, 0);
		}
	}
	
	public void useItem(int itemInd, int hpGained) {
		String itemName = player.getItemNames().get(itemInd);
		
		hpGained = Math.min(hpGained, player.getMaxHP()-player.getCurrHP());
		if (hpGained > 0) {
			player.setCurrHP(player.getCurrHP()+hpGained);
			setMoveOrItemMsg("You use a "+itemName+" and gain "+hpGained+" HP!");
		}
		else {
			if (itemInd == 2) player.setParalyzed(0);
			setMoveOrItemMsg("You use a "+itemName+"!");			
		}
		player.getItemNums().set(itemInd, player.getItemNums().get(itemInd) - 1);
		this.setMessage(getMoveOrItemMsg());
	}
	
	public void makeMove() {
		setEnterPressed(false);
		if (player.getParalyzed() > 0) {
			if (player.getParalyzed() > 6) player.setParalyzed(6);
			player.setParalyzed(player.getParalyzed() - 1);
			this.setMessage(playerParalyzed);
		}
		else {	
			if (getArrow().atTop() && getArrow().atLeft()) // attack
				player.attack(getEnemy(), this);
			else if (getArrow().atTop() && getArrow().atRight()) // intimidate
				player.intimidate(getEnemy(), this);					
			else if (getArrow().atCenter() && getArrow().atLeft()) // leer
				player.leer(getEnemy(), this);					
			else if (getArrow().atCenter() && getArrow().atRight())  // sweet scent
				player.sweetScent(getEnemy(), this);				
			else if (getArrow().atBot() && getArrow().atLeft())  // mace
				player.mace(getEnemy(), this, true);					
			else if (getArrow().atBot() && getArrow().atRight())  // tranquilize
				player.tranquilize(getEnemy(), this, true);				
		}
	}
	
	public void goBack() {
		setEnterPressed(false);
		getArrow().setNextLeftMarg(getArrow().LEFT);
		getArrow().setNextTopMarg(getArrow().TOP);
		this.setMessage(selectActionType);	
	}
	
	public void enemyDead() {
		if(isFightingNPC()) {
			if (getEnemy().getName().equals("HUMPHREY")) {
				this.setWonBattle("You have captured the murderer!  Congratulations!");
			}
			else {
				this.setWonBattle("You have falsely accused "+getEnemy().getName()+" and were imprisoned for assault.");
			}
		}
		else {
			int exp = 60+20*getEnemy().getLevel();
			String expMoneyGainMsg = "+"+exp+" money";
			if (!expAdded) {
				if (player.getLevel() < 10) {
					player.setExperience(player.getExperience() + exp*2);
					expMoneyGainMsg += " and EXP";
				}
				player.setMoney(player.getMoney() + exp);
				expAdded = true;
			}
			if (player.getExperience() >= Math.pow(player.getLevel()+10, 3)) {
				player.lvlUp();
				lvlUpMsg = "+1 level";
				player.updateDisplay();
				
			}
			this.setWonBattle("You have won!\n"+expMoneyGainMsg+"\n"+lvlUpMsg);
		}
		this.setMessage(getWonBattle());
	}
	
	public void playerDead() {
		this.setMessage(lostBattle);
		player.resetStats();
	}
	
	public void endBattle() {
		if (this.getText().equals(lostBattle) && changeText)
			setPlayerDead(true);
		else if (changeText) {
			setEnemyDead(true);
		}
		player.resetStats();
	}
	
	public void combat(ArrayList<String> input) {
		if (input.contains("ENTER")) setEnterPressed(true);
		changeText = (!input.contains("ENTER") && isEnterPressed());
		
		if (this.getText().equals(openingMsg)) { // start of fight
			if (changeText) {	// reset message
				setEnterPressed(false);
				this.setMessage(selectActionType);
			}
		}
		else if (this.getText().equals(selectActionType)) {	// fight, item, run
			this.selectAction();
		}
		else if (this.getText().equals(itemMenu)) {	// select item
			if (input.contains("B")) {
				this.goBack();
			}
			else if (changeText) {
				this.selectItem();
				if (player.getParalyzed() > 0) {
					if (player.getParalyzed() > 6) player.setParalyzed(6);
					player.setParalyzed(player.getParalyzed() - 1);
				}
			}
		}
		else if (this.getText().equals(listOfMoves)) { // select move
			if (input.contains("B")) {
				this.goBack();	
			}
			else if (changeText) {
				this.makeMove();
			}
		}
		else if (this.getText().equals(moveOrItemMsg) || this.getText().equals(playerParalyzed) || this.getText().equals(failedEscape)) {  // just attacked, time for enemy attack
			getEnemy().updateDisplay();
			player.updateDisplay();
			if (changeText) {
				setEnterPressed(false);
				if (this.getEnemy().getCurrHP() <= 0) {
					this.enemyDead();	
				}
				else if (getEnemy().getParalyzed() > 0) {
					if (getEnemy().getParalyzed() > 6) getEnemy().setParalyzed(6);
					getEnemy().setParalyzed(getEnemy().getParalyzed() - 1);
					this.setMessage(enemyParalyzed);
				}
				else {
					getEnemy().randomFight(player);
				}
			}
		}
		else if (this.getText().equals(lostBattle) || this.getText().equals(wonBattle)) {
			this.endBattle();
		}
		else {
			player.updateDisplay();
			if (changeText) {
				setEnterPressed(false);
				if (this.player.getCurrHP() <= 0)
					this.playerDead();
				else
					this.goBack();
			}
		}
	}

	public Combatant getEnemy() {
		return enemy;
	}

	public void setEnemy(Combatant enemy) {
		this.enemy = enemy;
	}

	public boolean isPlayerDead() {
		return playerDead;
	}

	public void setPlayerDead(boolean playerDead) {
		this.playerDead = playerDead;
	}

	public boolean isEnemyDead() {
		return enemyDead;
	}

	public void setEnemyDead(boolean enemyDead) {
		this.enemyDead = enemyDead;
	}

	public boolean isFightingNPC() {
		return fightingNPC;
	}

	public void setFightingNPC(boolean fightingNPC) {
		this.fightingNPC = fightingNPC;
	}

	public String getItemMenu() {
		return itemMenu;
	}

	public void setItemMenu(String itemMenu) {
		this.itemMenu = itemMenu;
	}

	public String getMoveOrItemMsg() {
		return moveOrItemMsg;
	}

	public void setMoveOrItemMsg(String moveOrItemMsg) {
		this.moveOrItemMsg = moveOrItemMsg;
	}

	public String getWonBattle() {
		return wonBattle;
	}

	public void setWonBattle(String wonBattle) {
		this.wonBattle = wonBattle;
	}	
}
