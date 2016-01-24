package javaFX;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class Combatant extends Sprite{
	private String name = "You";
	private Rectangle battleImage;
	private int level = 1;
	private int currHP = 55;
	private int maxHP = 55;
	
	private int attack = 30;
	private int defense = 30;
	private int accuracy = 30;
	private int evasion = 30;

	private int paralyzed = 0;
	
	private int atkDen = 5;
	private int defDen = 5;
	private int accDen = 5;
	private int evaDen = 5;
	
	private Text display;
	private BattleText battle;
	
	public Combatant() {
        setDisplay(new Text());
        getDisplay().setFont(Font.font("Courier New", FontPosture.REGULAR, 30));
        getDisplay().setLayoutX(50);
        getDisplay().setLayoutY(25); 
        getDisplay().setText("Lv: "+getLevel()+"\nHP: "+getCurrHP()+" / "+getMaxHP());
	}
	
	public void updateDisplay() {
		String parMsg;
		if (getParalyzed() > 0) parMsg = " PAR";
		else parMsg = "";
        getDisplay().setText("Lv: "+getLevel()+parMsg+"\nHP: "+getCurrHP()+" / "+getMaxHP());
	}
	
	public void setStats(int atkNum, int defNum, int accNum, int evaNum) {
		setAttack(atkNum);
		setDefense(defNum);
		setAccuracy(accNum);
		setEvasion(evaNum);
	}
	
	public void createRandomEnemy(int lowLv, int highLv) {
        Random enemyRNG = new Random();
        int enemyNum = enemyRNG.nextInt(4);
        setLevel(enemyRNG.nextInt(highLv-lowLv+1)+lowLv);
		
		int lowStat = 25 + 2*getLevel();
		int midStat = 28 + 2*getLevel();
		int highStat = 31 + 2*getLevel();
		
		setMaxHP(50 + 5*getLevel());
		if (getMaxHP() == 100) setMaxHP(99);
		setCurrHP(getMaxHP());
		
		if (enemyNum == 0) {
			setName("SPIDER");
			setBattleImage(new Rectangle(140, 107, new ImagePattern(new Image("ariados.png"))));
			setStats(midStat, midStat, lowStat, highStat);
		}
		else if (enemyNum == 1) {
			setName("SNAKE");
			setBattleImage(new Rectangle(132, 137, new ImagePattern(new Image("arbok.png"))));
			setStats(highStat, lowStat, midStat, midStat);
		}
		else if (enemyNum == 2) {
			setName("OWL");
			setBattleImage(new Rectangle(107, 140, new ImagePattern(new Image("noctowl.png"))));
			setStats(midStat, highStat, midStat, lowStat);
		}
		else {
			setName("RAT");
			setBattleImage(new Rectangle(117, 110, new ImagePattern(new Image("raticate.png"))));
			setStats(lowStat, midStat, highStat, midStat);
		}
		this.updateDisplay();
	}
	
	public int calcDamage(Combatant enemy) {
		Random damageRNG = new Random();
		double dmgPercent = (100 - damageRNG.nextInt(15))/100.0;
		double atkDefRatio = (5.0*this.getAttack()/atkDen) / (5.0*enemy.getDefense()/defDen);
		return (int) (((45+(getLevel()*5))*(0.2)*atkDefRatio+2)*dmgPercent);
	}
	
	public boolean[] doesItHit(Combatant enemy, int baseHit) {
		boolean[] hitCrit = new boolean[]{false, false};
		
		int border = (int) baseHit*(5*this.getAccuracy()/accDen) / (5*enemy.getEvasion()/evaDen);
		Random hitRNG = new Random();
		int hitVal = hitRNG.nextInt(100);
		
		if (hitVal < border) {
			hitCrit[0] = true;
			int critVal = hitRNG.nextInt(20);
			if (critVal == 19) hitCrit[1] = true;
		}
		return hitCrit;
	}
	
	public void setBattleHitMsg(boolean[] hitCrit, String move, BattleText battle) {
		String extraMsg;
		String tense = " ";
		if(getName() != "You") tense = "s ";
		
		if (hitCrit[1]) extraMsg = "It's a critical hit!";
		else if (hitCrit[0]) extraMsg = "It hits!";
		else extraMsg = "It misses.";
		
		String attackMsg = getName()+" use"+tense+move+"!\n"+extraMsg;
		if(getName() == "You") battle.setMoveOrItemMsg(attackMsg);
		battle.setMessage(attackMsg);
	}
	
	public void attack(Combatant combatant, BattleText battle) {
		boolean[] hitCrit = doesItHit(combatant, 95);
		int damage = 0;
		if (hitCrit[0]) {
			damage = calcDamage(combatant);
			combatant.setCurrHP(combatant.getCurrHP() - damage);
			if (hitCrit[1]) combatant.setCurrHP(combatant.getCurrHP() - damage);
			if (combatant.getCurrHP() < 0) combatant.setCurrHP(0);
		}
		setBattleHitMsg(hitCrit, "ATTACK", battle);
	}
	
	public void intimidate(Combatant enemy, BattleText battle) {
		boolean[] hitCrit;
		if (enemy.atkDen < 9) hitCrit = doesItHit(enemy, 100);
		else hitCrit = doesItHit(enemy, 0);
		if (hitCrit[0]) {
			enemy.atkDen++;
			if (hitCrit[1] && enemy.atkDen < 9) enemy.atkDen++;
		}
		setBattleHitMsg(hitCrit, "INTIMIDATE", battle);
	}
	
	public void leer(Combatant enemy, BattleText battle) {
		boolean[] hitCrit;
		if (enemy.defDen < 9) hitCrit = doesItHit(enemy, 100);
		else hitCrit = doesItHit(enemy, 0);
		if (hitCrit[0]) {
			enemy.defDen++;
			if (hitCrit[1] && enemy.defDen < 9) enemy.defDen++;
		}
		setBattleHitMsg(hitCrit, "LEER", battle);
	}
	
	public void sweetScent(Combatant enemy, BattleText battle) {
		boolean[] hitCrit;
		if (enemy.evaDen < 9) hitCrit = doesItHit(enemy, 100);
		else hitCrit = doesItHit(enemy, 0);
		if (hitCrit[0]) {
			enemy.evaDen++;
			if (hitCrit[1] && enemy.evaDen < 9) enemy.evaDen++;
		}
		setBattleHitMsg(hitCrit, "SWEET SCENT", battle);
	}
	
	public void mace(Combatant enemy, BattleText battle, boolean isPlayer) {
		boolean[] hitCrit;
		if (enemy.accDen < 9) hitCrit = doesItHit(enemy, 100);
		else hitCrit = doesItHit(enemy, 0);
		if (hitCrit[0]) {
			enemy.accDen++;
			if (hitCrit[1] && enemy.accDen < 9) enemy.accDen++;
		}
		if (isPlayer)
			setBattleHitMsg(hitCrit, "MACE", battle);
		else
			setBattleHitMsg(hitCrit, "SAND ATTACK", battle);
	}
	
	public void tranquilize(Combatant enemy, BattleText battle, boolean isPlayer) {
		boolean[] hitCrit = doesItHit(enemy, 30);
		Random attackRNG = new Random();
		if (hitCrit[0]) {
			int paralyzeTurns = attackRNG.nextInt(4)+2;
//			int paralyzeTurns = 1;
			enemy.setParalyzed(enemy.getParalyzed() + paralyzeTurns);
			if (hitCrit[1]) enemy.setParalyzed(enemy.getParalyzed() + paralyzeTurns);
		}
		if (isPlayer)
			setBattleHitMsg(hitCrit, "TRANQUILIZE", battle);
		else
			setBattleHitMsg(hitCrit, "BITE", battle);
	}	
	
	public void resetStats() {
		setParalyzed(0);
		atkDen = 5;
		defDen = 5;
		accDen = 5;
		evaDen = 5;
	}
	
	public void randomFight(Combatant enemy) {
		Random attackRNG = new Random();
		int attackNum = attackRNG.nextInt(100);
		
		if (attackNum < 63)		// 63% chance of attack
			attack(enemy, getBattle());
		else if (attackNum < 71) // 8% chance of intimidate
			intimidate(enemy, getBattle());
		else if (attackNum < 79) // 8% chance of leer
			leer(enemy, getBattle());
		else if (attackNum < 87) // 8% chance of sweet scent
			sweetScent(enemy, getBattle());
		else if (attackNum < 95) // 8% chance of sand attack
			mace(enemy, getBattle(), false);
		else	// 5% chance of bite
			tranquilize(enemy, getBattle(), false);
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getEvasion() {
		return evasion;
	}

	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurrHP() {
		return currHP;
	}

	public void setCurrHP(int currHP) {
		this.currHP = currHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getParalyzed() {
		return paralyzed;
	}

	public void setParalyzed(int paralyzed) {
		this.paralyzed = paralyzed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rectangle getBattleImage() {
		return battleImage;
	}

	public void setBattleImage(Rectangle battleImage) {
		this.battleImage = battleImage;
	}

	public Text getDisplay() {
		return display;
	}

	public void setDisplay(Text display) {
		this.display = display;
	}

	public BattleText getBattle() {
		return battle;
	}

	public void setBattle(BattleText battle) {
		this.battle = battle;
	}	
}
