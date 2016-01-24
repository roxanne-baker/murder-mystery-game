package javaFX;

import java.util.ArrayList;

public class Interior2 extends Interior{
	
	public Interior2(ArrayList<String> interactions) {
		super();
		this.setInteractionSides(getInteractionSides());
	    this.setInteriorInteractions(interactions);
		this.setImage("interior2.png");
		this.setExitX(97);
		this.setExitWidth(96);
	}
	
	
	public ArrayList<boolean[]> getInteractionSides() {
		ArrayList<boolean[]> interactionSidesLRTB = new ArrayList<boolean[]>();
        interactionSidesLRTB.add(new boolean[]{false, false, false, true});	// bookshelf1
        interactionSidesLRTB.add(new boolean[]{false, false, false, true}); // picture
        interactionSidesLRTB.add(new boolean[]{false, false, false, true}); // bookshelf2
        interactionSidesLRTB.add(new boolean[]{true, false, true, false});	// book
        interactionSidesLRTB.add(new boolean[]{false, true, false, false}); // plantL
        interactionSidesLRTB.add(new boolean[]{true, false, false, false}); // plantR
        interactionSidesLRTB.add(new boolean[]{false, false, false, true}); // backWall
        interactionSidesLRTB.add(new boolean[]{true, true, true, true});	// table
        return interactionSidesLRTB;
	}

	public void setFurnishings() {
	    ArrayList<double[]> houseFurnishings = new ArrayList<double[]>();
	    
	    double[] bookshelf1 = new double[]{0, 0, 95, 94};
	    houseFurnishings.add(bookshelf1);
	    
	    double[] picture = new double[]{147, 0, 41, 46};
	    houseFurnishings.add(picture);
	    
	    double[] bookshelf2 = new double[]{336, 0, 47, 94};
	    houseFurnishings.add(bookshelf2);
	    
	    double[] book = new double[]{147, 140, 44, 31};
	    houseFurnishings.add(book);
	    
	    double[] plantL = new double[]{0, 287, 48, 93};
	    houseFurnishings.add(plantL);
	    
	    double[] plantR = new double[]{336, 287, 48, 93};
	    houseFurnishings.add(plantR);
	    
	    double[] table = new double[]{144, 143, 97, 92};
	    houseFurnishings.add(table);
	    
	    double[] backWall = new double[]{95, 0, 286, 46};
	    houseFurnishings.add(backWall);

	    this.setFurnishings(houseFurnishings);
	}
	

	public void setInteriorInteractions(ArrayList<String> statements) {
		ArrayList<TextBox> interactionsToSet = new ArrayList<TextBox>();
		for (String statement : statements)
			interactionsToSet.add(new TextBox(statement));
		interactionsToSet.add(new TextBox(statements.get(4)));
		for (int i=0; i<2; i++)
			interactionsToSet.add(new TextBox(""));
		setInteractions(interactionsToSet);
	}
	    
}
