package javaFX;

import javafx.scene.canvas.GraphicsContext;

public class SouthRoute extends Route {
	
	public SouthRoute(GraphicsContext gc, int startingLvl, int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		addGrassPatches(gc, startingLvl);
		addForestPatches(gc);
	}
	
	//positioning is done in units of 24 because this is the width/height of a single grass square
	public void addGrassPatches(GraphicsContext gc, int startLv) {
		getGrassPatches().add(new Grass(gc, startLv+1, startLv+3, getLeftX()+48*6, getTopY()+48*2, 32*6, 32*4));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+32*30, getTopY()+48*2, 32*5, 32*4));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*15, getTopY()+48*5, 48*5, 48*6));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*6, getTopY()+48*10, 48*3, 48*5));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*13, getTopY()+48*11, 48*3, 48*2));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*11, getTopY()+48*6, 48*2, 48*2));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*6, getTopY()+48*6, 48*5, 48*1));
	}
	
	//positioning is done in units of 48 because this is the width/height of a single tree
	public void addForestPatches(GraphicsContext gc) {
		getForestPatches().add(new Forest(gc, getLeftX()+0, getTopY()+0, 48*10, 48*2));		
		getForestPatches().add(new Forest(gc, getLeftX()+0, getTopY()+48*2, 48*6, 48*13));		
		getForestPatches().add(new Forest(gc, getLeftX()+48*6, getTopY()+48*13, 48*11, 48*2));	
		getForestPatches().add(new Forest(gc, getLeftX()+48*6, getTopY()+48*4, 48*9, 48*2));	
		getForestPatches().add(new Forest(gc, getLeftX()+48*13, getTopY()+48*6, 48*2, 48*2));  
		getForestPatches().add(new Forest(gc, getLeftX()+48*9, getTopY()+48*8, 48*1, 48*2));
		getForestPatches().add(new Forest(gc, getLeftX()+48*9, getTopY()+48*10, 48*11, 48*1));
		getForestPatches().add(new Forest(gc, getLeftX()+48*15, getTopY()+48*0, 48*5, 48*2));
		getForestPatches().add(new Forest(gc, getLeftX()+48*20, getTopY()+48*0, 48*6, 48*15));
		
	}	
}
