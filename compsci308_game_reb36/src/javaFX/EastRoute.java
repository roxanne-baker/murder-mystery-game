package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EastRoute extends Route {
	
	public EastRoute(GraphicsContext gc, int startLevel, int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		addGrassPatches(gc, startLevel);
		addForestPatches(gc);
	}
	
	//positioning is done in units of 24 because this is the width/height of a single grass square
	public void addGrassPatches(GraphicsContext gc, int startLv) {
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*1, getTopY()+48*2, 48*4, 48*2));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*1, getTopY()+48*4, 48*3, 48*4));
		getGrassPatches().add(new Grass(gc, startLv+1, startLv+3, getLeftX()+48*12, getTopY()+48*6, 48*1, 48*2));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*8, getTopY()+48*5, 48*4, 48*2));
		getGrassPatches().add(new Grass(gc, startLv+1, startLv+3, getLeftX()+48*8, getTopY()+48*3, 24*3, 48*2));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*15, getTopY()+48*9, 48*4, 48*4));
		getGrassPatches().add(new Grass(gc, startLv, startLv+2, getLeftX()+48*6, getTopY()+48*10, 48*5, 48*3));
		getGrassPatches().add(new Grass(gc, startLv+1, startLv+3, getLeftX()+48*11, getTopY()+48*1, 48*5, 48*3));
		getGrassPatches().add(new Grass(gc, startLv+1, startLv+3, getLeftX()+48*1, getTopY()+24*23, 48*4, 24*3));
	}
	
	//positioning is done in units of 48 because this is the width/height of a single tree
	public void addForestPatches(GraphicsContext gc) {
		getForestPatches().add(new Forest(gc, getLeftX()+48*1, getTopY()+48*0, 48*18, 48*1));
		getForestPatches().add(new Forest(gc, getLeftX()+48*1, getTopY()+48*13, 48*18, 48*1));
		getForestPatches().add(new Forest(gc, getLeftX()+48*0, getTopY()+48*0, 48*1, 48*9));
		getForestPatches().add(new Forest(gc, getLeftX()+48*0, getTopY()+48*11, 48*1, 48*3));
		
		getForestPatches().add(new Forest(gc, getLeftX()+48*19, getTopY()+48*0, 48*1, 48*6));
		getForestPatches().add(new Forest(gc, getLeftX()+48*19, getTopY()+48*8, 48*1, 48*6));
		
		getForestPatches().add(new Forest(gc, getLeftX()+48*4, getTopY()+96*2, 48*1, 48*4));
		getForestPatches().add(new Forest(gc, getLeftX()+48*5, getTopY()+48*7, 48*4, 48*1));
		getForestPatches().add(new Forest(gc, getLeftX()+48*6, getTopY()+48*0, 48*2, 48*5));
		getForestPatches().add(new Forest(gc, getLeftX()+48*8, getTopY()+48*0, 48*2, 48*3));
		getForestPatches().add(new Forest(gc, getLeftX()+48*11, getTopY()+48*4, 48*1, 48*4));	
		getForestPatches().add(new Forest(gc, getLeftX()+48*13, getTopY()+48*4, 48*6, 48*2));
		getForestPatches().add(new Forest(gc, getLeftX()+48*5, getTopY()+48*8, 48*1, 48*5));
		getForestPatches().add(new Forest(gc, getLeftX()+48*8, getTopY()+48*10, 48*6, 48*1));
		getForestPatches().add(new Forest(gc, getLeftX()+48*14, getTopY()+48*6, 48*1, 48*5));
	}
}
