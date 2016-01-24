package javaFX;

public class NPCRectangle extends NPC {
	
	private double startWait;
	private double middleWait;
	private double middle2Wait;
	private double endWait;
	
	private double firstLegLength;
	private double secondLegLength;

	public NPCRectangle(String overworldImg, String battleImg, double x, double y) {
		super(overworldImg, battleImg, x, y);
	}
	
	public void setWaitTimes(double start, double mid1, double mid2, double end) {
		startWait = start;
		middleWait = mid1;
		middle2Wait = mid2;
		endWait = end;
	}	
	
	public void setPath(String fstDirn, double leg1, String sndDirn, double leg2, double speed) { // same as NPC L_Shape
		this.setFirstDirection(fstDirn, speed);
		this.setSecondDirection(sndDirn, speed);
		firstLegLength = leg1;
		secondLegLength = leg2;
	}
	
	public boolean startCond() {
		return atEndOfLeg(getSpeedX1(), getSpeedY1(), 0, getSpeedX2(), getSpeedY2(), 0);
	} 
	
	public boolean mid1Cond() {
		return atEndOfLeg(-getSpeedX1(), -getSpeedY1(), firstLegLength, getSpeedX2(), getSpeedY2(), 0);
	}	
	
	public boolean mid2Cond() {	
		return atEndOfLeg(-getSpeedX1(), -getSpeedY1(), firstLegLength, -getSpeedX2(), -getSpeedY2(), secondLegLength);
	}
	
	public boolean mid3Cond() {
		return atEndOfLeg(getSpeedX1(), getSpeedY1(), 0, -getSpeedX2(), -getSpeedY2(), secondLegLength);		
	}
	
	
	public void move(double time) {
		if (this.startCond()) {
			stopAndGo(time, startWait, getSpeedX1(), getSpeedY1());
		}
		else if (this.mid1Cond()) {
			stopAndGo(time, middleWait, getSpeedX2(), getSpeedY2());
		}
		else if (this.mid2Cond()) {
			stopAndGo(time, middle2Wait, -getSpeedX1(), -getSpeedY1());
		}
		else if (this.mid3Cond()) {
			stopAndGo(time, endWait, -getSpeedX2(), -getSpeedY2());
		}
	}
}
