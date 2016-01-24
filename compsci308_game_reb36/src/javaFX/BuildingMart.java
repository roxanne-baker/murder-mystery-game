package javaFX;

public class BuildingMart extends Building {

	public BuildingMart(double buildX, double buildY, Player player) {
        setImage("martExterior.png");
        setPosition(buildX, buildY);
        this.setDoorPos(68, 195);
        
        setInside(new InteriorMart(player));
	}	
}
