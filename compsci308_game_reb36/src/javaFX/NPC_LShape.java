package javaFX;

public class NPC_LShape extends NPC {

	private double startWait;
	private double middleWait;
	private double endWait;

	private double firstLegLength;
	private double secondLegLength;

	private boolean forward = true;
	
	
	public NPC_LShape(String overworldImg, String battleImg, double x, double y) {
		super(overworldImg, battleImg, x, y);
	}
	
	public void setWaitTimes(double start, double middle, double end) {
		startWait = start;
		middleWait = middle;
		endWait = end;
	}
	
	public void setPath(String fstDirn, double leg1, String sndDirn, double leg2, double speed) {
		this.setFirstDirection(fstDirn, speed);
		this.setSecondDirection(sndDirn, speed);
		firstLegLength = leg1;
		secondLegLength = leg2;
	}
	
	public boolean startCond() {
		return getCond(getSpeedX1(), getSpeedY1(), 0);
	} 
	
	public boolean endCond() {
		return getCond(-getSpeedX2(), -getSpeedY2(), secondLegLength);
	}
		
	public boolean midCond() {
		return atEndOfLeg(-getSpeedX1(), -getSpeedY1(), firstLegLength, getSpeedX2(), getSpeedY2(), 0);
	}
	
	public boolean mid1Cond() {
		return (forward && this.midCond());
	}
	
	public boolean mid2Cond() {
		return (!forward && this.midCond());
	}
	
	public void move(double time) {
		if (this.startCond()) {
			stopAndGo(time, startWait, getSpeedX1(), getSpeedY1());
			forward = true;
		}
		else if (this.mid1Cond()) {
			stopAndGo(time, middleWait, getSpeedX2(), getSpeedY2());
		}
		else if (this.mid2Cond()) {
			stopAndGo(time, middleWait, -getSpeedX1(), -getSpeedY1());
		}
		else if (this.endCond()) {
			stopAndGo(time, endWait, -getSpeedX2(), -getSpeedY2());
			forward = false;
		}
	}
}
