import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;



public class Frog {
	public Image image ;
	private int x;
	private int y;
	int color;
	

	Frog(int n) {
		this.x=800/2;
		this.y=600/2;
		this.color=n;
		image = Toolkit.getDefaultToolkit().getImage("pic/frogball/"+this.color+".png");
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.x=x;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y=y;
	}
	public void changeImg(int color) {
		this.color=color;
		image = Toolkit.getDefaultToolkit().getImage("pic/frogball/"+this.color+".png");
		//image = Toolkit.getDefaultToolkit().getImage("pic/frog.png");
	}
	
	


}
