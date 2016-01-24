package javaFX;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    private double leftX;
    private double topY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double rightX = leftX+width;
    private double botY = topY+height;

    public Sprite() {
        leftX = 0;
        topY = 0;    
        velocityX = 0;
        velocityY = 0;
    }
    
    public double getLeftX() {
    	return leftX;
    }
    
    public void setLeftX(double xLoc) {
    	leftX = xLoc;
    }
    
    public double getTopY() {
    	return topY;
    }
    
    public void setTopY(double yLoc) {
    	topY = yLoc;
    }
    
    public double getWidth() {
    	return width;
    }
    
    public double getHeight() {
    	return height;
    }
    
    public void setWidth(double w) {
    	width = w;
    }
    
    public void setHeight(double h) {
    	height = h;
    }
    
    public double getVelocityX() {
    	return velocityX;
    }
    
    public void setVelocityX(double velX) {
    	velocityX = velX;
    }
    
    public double getVelocityY() {
    	return velocityY;
    }
    
    public double getRightX() {
    	return leftX+width;
    }
    
    public double getBotY() {
    	return topY+height;
    }
    
    public void setVelocityY(double velY) {
    	velocityY = velY;
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y) {
        leftX = x;
        topY = y;
        rightX = leftX+width;
        botY = topY+height;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
    	if (velocityX*time < 7 && velocityY*time < 7) {
    		leftX += velocityX * time;
    		topY += velocityY * time;
    	}
    }
    
    public double[] updateAmount(double time) {
    	return new double[]{leftX += velocityX*time, topY += velocityY*time};
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, leftX, topY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(leftX,topY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + leftX + "," + topY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
    
	public boolean bordersRight(Sprite s) {
        rightX = leftX+width;
        botY = topY+height;
		boolean inXRange = (s.leftX < this.rightX) && (s.rightX > this.rightX);
		boolean inYRange = (s.topY <= this.botY-10) && (s.botY >= this.topY+10);
		return (inXRange && inYRange);
	}
	
	public boolean bordersLeft(Sprite s) {
        rightX = leftX+width;
        botY = topY+height;
		boolean inXRange = (s.rightX > this.leftX) && (s.leftX < this.leftX);
		boolean inYRange = (s.topY <= this.botY-10) && (s.botY >= this.topY+10);
		return (inXRange && inYRange);
	}		
	
	public boolean bordersBot(Sprite s) {
        rightX = leftX+width;
        botY = topY+height;
		boolean inXRange = (s.rightX >= this.leftX+10) && (s.leftX <= this.rightX-10);
		boolean inYRange = (s.topY < this.botY) && (s.botY > this.botY);
		return (inXRange && inYRange);	
	}
	
	public boolean bordersTop(Sprite s) {
        rightX = leftX+width;
        botY = topY+height;
		boolean inXRange = (s.rightX >= this.leftX+10) && (s.leftX <= this.rightX-10);
		boolean inYRange = (s.botY > this.topY) && (s.topY < this.topY);
		return (inXRange && inYRange);		
	}
    
    
    
}