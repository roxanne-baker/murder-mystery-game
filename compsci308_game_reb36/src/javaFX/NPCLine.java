package javaFX;

public class NPCLine extends NPC {

	private double startWait;
	private double endWait;
	
	private double firstLegLength;

	public NPCLine(String overworldImg, String battleImg, double x, double y) {
		super(overworldImg, battleImg, x, y);
	}
	
	public void setWaitTimes(double start, double end) {
		startWait = start;
		endWait = end;
	}
	
	public boolean startCond() {
		return getCond(getSpeedX1(), getSpeedY1(), 0);
	}
	
	public boolean endCond() {
		return getCond(-getSpeedX1(), -getSpeedY1(), firstLegLength);
	}
	
	public void setPath(String fstDirn, double pathlength, double speed) {
		this.setFirstDirection(fstDirn, speed);
		firstLegLength = pathlength;
	}
	
	public void move(double time) {
		if (this.startCond()) {
			stopAndGo(time, startWait, getSpeedX1(), getSpeedY1());
		}
		if (this.endCond()) {
			stopAndGo(time, endWait, -getSpeedX1(), -getSpeedY1());
		}
	}
	
}
