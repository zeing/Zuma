import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Ball {
	int x;
	int y;
	int color ;
	public Image image;
	Random rand = new Random(); 
	Ball(int u) {
		this.x=0-(32*u);
		this.y=65;
		this.color=(int)rand.nextInt(4)+1;
		//this.color=(int)1;
		image = Toolkit.getDefaultToolkit().getImage("pic/ball/"+this.color+".png");
		//image = Toolkit.getDefaultToolkit().getImage("pic/ball.png");
		
	}
	Ball(int x,int y,int color) {
		this.x=x;
		this.y=y;
		this.color=color;
		image = Toolkit.getDefaultToolkit().getImage("pic/ball/"+this.color+".png");
		//image = Toolkit.getDefaultToolkit().getImage("pic/ball.png");
		
	}
	public void changeImg(int color) {
		this.color=color;
		image = Toolkit.getDefaultToolkit().getImage("pic/ball/"+this.color+".png");
		//image = Toolkit.getDefaultToolkit().getImage("pic/frog.png");
	}
	
	
	
}
