package javaFX;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Map {
	private ArrayList<Town> towns = new ArrayList<Town>(); 
	private ArrayList<Route> routes = new ArrayList<Route>();
    private ArrayList<Grass> grassBlocks = new ArrayList<Grass>();
    private ArrayList<NPC> npcs = new ArrayList<NPC>();        
    private ArrayList<Sprite> decorativeBuildings = new ArrayList<Sprite>();
    private ArrayList<Building> realBuildings = new ArrayList<Building>();
    private ArrayList<Forest> forestBlocks = new ArrayList<Forest>();
    
    public Map(GraphicsContext gc, Player player) {
        addAllTowns(gc, player);
        addAllRoutes(gc);
    }
    
    public void addAllRoutes(GraphicsContext gc) {
        addRoute(new EastRoute(gc, 3, 48*94, 48*51, 48*20, 48*15));
        addRoute(new EastRoute(gc, 7, 48*33, 48*51, 48*20, 48*15));
        addRoute(new SouthRoute(gc, 5, 48*64, 48*22+100, 48*20, 48*15));
        addRoute(new SouthRoute(gc, 1, 48*64, 48*75, 48*20, 48*15));
    }
    
    public void addAllTowns(GraphicsContext gc, Player player) {
        addTown(new SouthTown(gc, new Image("sparseGrass.png"), player, 48*64, 48*90, 1250, 900));
        addTown(new EastTown(gc, new Image("normalGrass.png"), player, 48*114, 48*52, 1150, 650));
        addTown(new CenterTown(gc, new Image("brick.png"), player, 48*53, 48*39, 1968, 1750));
        addTown(new WestTown(gc, new Image("brick.png"), player, 48*2, 48*52, 1488, 1300));
        addTown(new NorthTown(gc, new Image("sparseGrass.png"), player, 48*62+4, 48*2, 1500, 48*22));
    }	
    
    public void setMapBackgrounds(GraphicsContext gc) {
    	setRouteBackgrounds(gc);
    	setTownBackgrounds(gc);
    }
    
    public void setTownBackgrounds(GraphicsContext gc) {
    	for (Town town : towns) {
    		town.setBackground(gc);
    	}
    }
    
    public void setRouteBackgrounds(GraphicsContext gc) {
    	for (Route route : routes) {
    		route.setBackground(gc);
    	}
    }
    
    public void addTown(Town town) {
    	towns.add(town);
        getRealBuildings().addAll(town.getTownBuildings());  
        getDecorativeBuildings().addAll(town.getDecorativeBuildings());
        getForestBlocks().addAll(town.getBorders());
        getNpcs().addAll(town.getTownNPCs());
    }
    
    public void addRoute(Route route) {
    	routes.add(route);
        getGrassBlocks().addAll(route.getGrassPatches());
        getForestBlocks().addAll(route.getForestPatches());
    }

	public ArrayList<Grass> getGrassBlocks() {
		return grassBlocks;
	}

	public void setGrassBlocks(ArrayList<Grass> grassBlocks) {
		this.grassBlocks = grassBlocks;
	}

	public ArrayList<NPC> getNpcs() {
		return npcs;
	}

	public void setNpcs(ArrayList<NPC> npcs) {
		this.npcs = npcs;
	}

	public ArrayList<Sprite> getDecorativeBuildings() {
		return decorativeBuildings;
	}

	public void setDecorativeBuildings(ArrayList<Sprite> decorativeBuildings) {
		this.decorativeBuildings = decorativeBuildings;
	}

	public ArrayList<Building> getRealBuildings() {
		return realBuildings;
	}

	public void setRealBuildings(ArrayList<Building> realBuildings) {
		this.realBuildings = realBuildings;
	}

	public ArrayList<Forest> getForestBlocks() {
		return forestBlocks;
	}

	public void setForestBlocks(ArrayList<Forest> forestBlocks) {
		this.forestBlocks = forestBlocks;
	}	
}
