package javaFX;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Building extends Sprite{

	private double doorPosX;
	private double doorPosY;
	private double doorWidth = 50;
	private double doorHeight = 60;
	private Interior inside;
	
	public Building() {
	}
	
	public Building(String buildingfile, double buildX, double buildY, Interior interior) {
        setImage(buildingfile);
        setPosition(buildX, buildY);

        setInside(interior);
        if (buildingfile == "building1Door.png" || buildingfile == "mediExterior.png")
        	setDoorPos(63, 198);
        else if (buildingfile == "building2Door.png")
        	setDoorPos(72, 134);
        else if (buildingfile == "building3Door.png" || buildingfile == "building4Door.png")
        	setDoorPos(75, 70);
        
	}
	
	public void setDoorPos(double x, double y)
    {
        doorPosX = getLeftX()+x;
        doorPosY = getTopY()+y;
    }
	
	@Override
	public boolean bordersBot(Sprite s) {
		boolean botDoorX = (s.getLeftX() >= this.getDoorPosX()) && (s.getRightX() <= this.getDoorPosX()+this.getDoorWidth());
		boolean inXRange = (s.getRightX() >= this.getLeftX()+10) && (s.getLeftX() <= this.getRightX()-10);
		boolean inYRange = (s.getTopY() <= this.getBotY()) && (s.getBotY() >= this.getBotY());
		return (inXRange && inYRange && !botDoorX);	
	}
	
	public boolean bordersDoorL(Sprite s) {
		boolean doorX = (s.getLeftX() <= this.getDoorPosX()) && (s.getRightX() >= this.getDoorPosX());
		boolean doorY = (s.getTopY() <= this.getDoorPosY()+this.doorHeight) && (s.getTopY() >= this.getDoorPosY());
		return (doorX && doorY);
	}
	
	public boolean bordersDoorR(Sprite s) {
		boolean doorX = (s.getRightX() >= this.getDoorPosX()+this.getDoorWidth()) && (s.getLeftX() <= this.getDoorPosX()+this.getDoorWidth());
		boolean doorY = (s.getTopY() <= this.getDoorPosY()+this.doorHeight) && (s.getTopY() >= this.getDoorPosY());
		return (doorX && doorY);
	}
	
	public boolean bordersDoorT(Sprite s) {
		boolean doorX = (s.getLeftX() >= this.getDoorPosX()) && (s.getRightX() <= this.getDoorPosX()+this.getDoorWidth());
		boolean doorY = (s.getTopY() <= this.getDoorPosY()) && (s.getBotY() >= this.getDoorPosY());
		return (doorX && doorY);
	}
	
	public boolean bordersDoorB(Sprite s) {
		boolean doorX = (s.getLeftX() >= this.getDoorPosX()) && (s.getRightX() <= this.getDoorPosX()+this.getDoorWidth());
		boolean doorY = (s.getBotY() < this.getDoorPosY()+this.doorHeight) && (s.getTopY() >= this.getDoorPosY());
		return (doorX && doorY);
	}

	public double getDoorPosX() {
		return doorPosX;
	}

	public double getDoorPosY() {
		return doorPosY;
	}

	public double getDoorWidth() {
		return doorWidth;
	}
	
	public double getDoorHeight() {
		return doorHeight;
	}

	/**
	 * @param doorWidth the doorWidth to set
	 */
	public void setDoorWidth(double doorWidth) {
		this.doorWidth = doorWidth;
	}

	/**
	 * @return the inside
	 */
	public Interior getInside() {
		return inside;
	}

	/**
	 * @param inside the inside to set
	 */
	public void setInside(Interior inside) {
		this.inside = inside;
	}	
}
