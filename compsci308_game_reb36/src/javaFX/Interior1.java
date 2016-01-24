package javaFX;

import java.util.ArrayList;

public class Interior1 extends Interior{
	
	public Interior1(ArrayList<String> interactions) {
		super();
		this.setInteractionSides(getInteractionSides());
	    this.setInteriorInteractions(interactions);
		this.setImage("interior1-F1.png");
		this.setExitX(97);
		this.setExitWidth(96);
	}
	
	public ArrayList<boolean[]> getInteractionSides() {
		ArrayList<boolean[]> interactionSidesLRTB = new ArrayList<boolean[]>();
        interactionSidesLRTB.add(new boolean[]{false, false, false, true}); //bookshelf
        interactionSidesLRTB.add(new boolean[]{false, false, false, true}); //wall
        interactionSidesLRTB.add(new boolean[]{false, false, false, true}); //TV
        interactionSidesLRTB.add(new boolean[]{true, true, true, true});	//table	
        return interactionSidesLRTB;
	}
	
	public void setFurnishings() {
	    ArrayList<double[]> houseFurnishings = new ArrayList<double[]>();
	    
	    double[] bookshelf = new double[]{0, 0, 97, 97};
	    houseFurnishings.add(bookshelf);
	    
	    double[] television = new double[]{145, 49, 48, 48};
	    houseFurnishings.add(television);    
	    
	    double[] table = new double[]{144, 193, 97, 93};
	    houseFurnishings.add(table);
	    
	    double[] backWall = new double[]{97, 0, 288, 49};
	    houseFurnishings.add(backWall);

	    this.setFurnishings(houseFurnishings);
	}
	
	public void setInteriorInteractions(ArrayList<String> statements) {
		ArrayList<TextBox> interactionsToSet = new ArrayList<TextBox>();
		for (String statement : statements)
			interactionsToSet.add(new TextBox(statement));
		for (int i=0; i<2; i++)
			interactionsToSet.add(new TextBox(""));
		setInteractions(interactionsToSet);
	}
	    
}
