package javaFX;

import java.util.ArrayList;

public class InteriorMart extends Interior{

	public InteriorMart(Player player) {
		super();
		this.setInteractionSides(getInteractionSides());
		setInteriorInteractions(player);
		this.setImage("martInterior.png");
		this.setExitX(144);
		this.setExitWidth(96);
	}	
	
	public ArrayList<boolean[]> getInteractionSides() {
		ArrayList<boolean[]> interactionSidesLRTB = new ArrayList<boolean[]>();
        interactionSidesLRTB.add(new boolean[]{false, false, false, true});	// backShelves
        interactionSidesLRTB.add(new boolean[]{false, false, true, true}); // rightShelf
        interactionSidesLRTB.add(new boolean[]{false, false, true, false}); // leftShelf
        interactionSidesLRTB.add(new boolean[]{false, true, false, false});	// counter	
        return interactionSidesLRTB;
	}

	public void setFurnishings() {
	    ArrayList<double[]> houseFurnishings = new ArrayList<double[]>();
	    
	    double[] backShelves = new double[]{0, 0, 384, 95};
	    houseFurnishings.add(backShelves);

	    double[] rightShelf = new double[]{193, 145, 191, 95};
	    houseFurnishings.add(rightShelf);
	    
	    double[] leftShelf = new double[]{0, 145, 97, 69};
	    houseFurnishings.add(leftShelf);
	    
	    double[] counter = new double[]{0, 213, 97, 123};
	    houseFurnishings.add(counter);

	    this.setFurnishings(houseFurnishings);
	}
	
	public void setInteriorInteractions(Player player) {
		ArrayList<String> houseInteractions = new ArrayList<String>();
		for (int i=0; i<getInteractionSides().size()-1; i++) {
			houseInteractions.add("The shelves are filled with various items.");	
		}
		ArrayList<TextBox> interactionsToSet = new ArrayList<TextBox>();
		for (String statement : houseInteractions) {
			interactionsToSet.add(new TextBox(statement));
		}
		interactionsToSet.add(new MartText(player));
		this.setInteractions(interactionsToSet);
	}
	    
}
