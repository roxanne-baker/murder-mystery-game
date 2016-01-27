package javaFX;

public class NPCRectangle extends NPC {
	// This entire file is part of my masterpiece
	// ROXANNE BAKER
	/**
	 * I feel this code is well written because
	 * it contains short functions, each with a single
	 * purpose that is obvious from its name
	 * There is no duplicated code here
	 * Calls to "atEndOfLeg" are also given their
	 * own method which significantly increases
	 * the readability of move (relative to just
	 * having the method calls within move)
	 * 
	 * Creates an NPC that moves
	 * in a rectangular shape
	 */
	
	private double startWait;
	private double middleWait;
	private double middle2Wait;
	private double endWait;

	private double firstLegLength;
	private double secondLegLength;

	public NPCRectangle(String overworldImg, String battleImg, double x, double y) {
		super(overworldImg, battleImg, x, y);
	}
	
	/**
	 * Sets the amount of time that the NPC
	 * stops at the end of each leg of rectangle
	 * @param start
	 * @param mid1
	 * @param mid2
	 * @param end
	 */
	public void setWaitTimes(double start, double mid1, double mid2, double end) {
		startWait = start;
		middleWait = mid1;
		middle2Wait = mid2;
		endWait = end;
	}	
	
	/**
	 * Sets the "path" that the NPC
	 * moves in given its directions
	 * of movement, the length of those
	 * legs of movement, and the speed
	 * @param fstDirn
	 * @param leg1
	 * @param sndDirn
	 * @param leg2
	 * @param speed
	 */
	public void setPath(String fstDirn, double leg1, String sndDirn, double leg2, double speed) {
		this.setDirection(true, fstDirn, speed);
		this.setDirection(false, sndDirn, speed);
		firstLegLength = leg1;
		secondLegLength = leg2;
	}
	
	/**
	 * @return whether or not NPC
	 * is at "starting point" of rectangle
	 */
	public boolean startCond() {
		return atEndOfLeg(getSpeedX1(), getSpeedY1(), 0, getSpeedX2(), getSpeedY2(), 0);
	} 
	
	/**
	 * @return whether or not NPC
	 * is at end of first leg of rectangle
	 */
	public boolean mid1Cond() {
		return atEndOfLeg(-getSpeedX1(), -getSpeedY1(), firstLegLength, getSpeedX2(), getSpeedY2(), 0);
	}	
	
	/**
	 * @return whether or not NPC
	 * is at end of second leg of rectangle
	 */
	public boolean mid2Cond() {	
		return atEndOfLeg(-getSpeedX1(), -getSpeedY1(), firstLegLength, -getSpeedX2(), -getSpeedY2(), secondLegLength);
	}
	
	/**
	 * @return whether or not NPC
	 * is at end of first leg of rectangle
	 */
	public boolean mid3Cond() {
		return atEndOfLeg(getSpeedX1(), getSpeedY1(), 0, -getSpeedX2(), -getSpeedY2(), secondLegLength);		
	}
	
	/**
	 * moves/stops the NPC given the amount
	 * of time that has passed since
	 * its last movement
	 */
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
