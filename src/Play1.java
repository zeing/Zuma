
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;

import java.io.*;
import java.util.*;
import java.awt.geom.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;


public class Play1 extends JApplet  implements Runnable,MouseMotionListener {	
	
	
	Thread thread;
	ImageIcon bgGame = new ImageIcon("pic/serpentss.jpg");
	int check=0;
	int mouseX,mouseY,mouseX1,mouseY1;
	int score=0; 
	int rear=10,allBall=300; // rear show ball 50 but all balls are 300
	int first,last; // check same color f and l
	int color;
	double currentAngle; 
	double angle ; 
	boolean run=true;
	boolean gameover=false;
	boolean checkShoot=false;
	boolean checkBoom=true;
	boolean checkInsert;
	//Object
	Home home = new Home();
	Random rand = new Random(); 
	Frog frog = new Frog(rand.nextInt(4)+1);
	
	Loading load = new Loading();
	GameOver gameOver = new GameOver();
	Ball[] ball = new Ball[4];
	Ball[] balls = new Ball[300];
	Ball ballShoot ;
	Timer timer = new Timer(2500, new ActionListener (){
    	public void actionPerformed(ActionEvent e){
		
	    		remove(load);
	    		load.stopS();
	    		//setSize(800, 600);
				timer.stop();
				timerBall.start();
				check=1;
    	}
    });
	Timer timerFrog = new Timer(180, new ActionListener (){
    	public void actionPerformed(ActionEvent e){
    		
    		roFrog();
    		
    		
    	}
    });
	Timer timerBall = new Timer(600, new ActionListener (){
    	public void actionPerformed(ActionEvent e){
		
    		for(int i=0;i<allBall;i++)
				moveBall(balls[i]);
    	
    			
    	}
    });
	Timer boom = new Timer(100, new ActionListener (){
    	public void actionPerformed(ActionEvent e){
		
	    		remove(load);
	    		load.stopS();
	    		//setSize(800, 600);
				timer.stop();
				check=1;
				score=0;
    	}
    });
	public void init() { 
		System.out.println("Init");
		setSize(800,600);
		setVisible(true);
		requestFocus();
		addMouseMotionListener(this);
		add(home);
		home.playS();
		home.play.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			      if(e.getSource()==home.play)
			      { 

						check=-1;
						remove(home);
						home.stopS();
						//setSize(800, 600);
						add(load);
						load.playS();
						load.revalidate();
						timer.start();
			    	 
			      }
			  }	  
		});
		
		home.exit.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			      if(e.getSource()==home.exit)
			    	 System.exit(0);
			  }	  
		});
	
		addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			     //shoot ball
				if(!checkShoot) {
					 ballShoot = new Ball(400,300,frog.color);
					 frog.changeImg(rand.nextInt(4)+1);
					 checkShoot=true;
					 mouseX1=mouseX;
					 mouseY1=mouseY;
				 }
			      
			  }
  
		});
		/*for(int i=0; i<4; i++) {
			ball[i] = new Ball(this.getWidth()/2,this.getHeight()/2,i+1);
			
		}*/
		for(int i=0; i<allBall;i++) {
			balls[i] = new Ball(i+1);
		}
		
	}
	public void checkMaxPoint() {
		
	}
	public void checkBoom() {
		if(checkShoot==true) {
			for(int i=0;i<rear;i++) {
				if(distance(balls[i].x,balls[i].y, ballShoot.x, ballShoot.y)<=15) {
					checkBoom=false;
					if(balls[i].color==ballShoot.color) {
						//System.out.println("same "+i); 
						first=i;
						last=i;
						System.out.println("check first");
						for(int j=i-1;j>=0;j--) {
							if(balls[j].color==balls[i].color) {
								//System.out.println("first -");
								first--;
								//System.out.println("first  "+first);
								if(first==-1)
									first=0;
							} else {
								break;
							}
						}
						System.out.println("check last ");
						for(int j=i+1;j<=rear;j++){
							if(balls[j].color==balls[i].color) {
								//System.out.println("last +");
								last++;
								//System.out.println("last  "+last);
								if(last==rear)
									last=rear-1;
							} else {
								break;
								
							}
							
						}
						System.out.println(first+"  "+last);
						//System.out.println(rear);
						if(first==last){
							System.out.println("insert");
							checkInsert=true;
							insertBall();
						} else {
							System.out.println("del");
							deleteBall();
						}
						
					}/*not same color */ else {
						System.out.println("not same");
						last=i;
						color=ballShoot.color;
						checkInsert=false;
						insertBall();
						//checkBoom=true;
					}
				break;
				}// distance

			} //forloop
		}
	}
	

	public void insertBall() {
			rear++;
			if(checkInsert) {
    			for(int i =rear;i>last+1;i--)	{
    				balls[i].changeImg(balls[i-1].color);
    			}
    			balls[last+1].changeImg(balls[last].color);	

			} else {
				for(int i =rear;i>last+1;i--)	{
    				balls[i].changeImg(balls[i-1].color);
    			}
    			balls[last+1].changeImg(color);	
			}		
			System.out.println(rear);
   }
	public void deleteBall() {
		for(int i=first;i<rear;i++) {
			//int j=last+1;
			balls[i].changeImg(balls[i+Math.abs(last-first)+1].color);
		}
		rear-=(last-first+1);
		score=last-first+1;
		System.out.println(rear);
	}
		
		
	

	public void shootBall() {
		if(checkShoot==true){
			if((ballShoot.x<0 || ballShoot.x>this.getWidth()) ){
				checkShoot = false;	
				checkBoom=true;
			}
			if((ballShoot.y<0 || ballShoot.y>this.getHeight()) ){
				checkShoot = false;
				checkBoom=true;
			} 
			
		} 
		//System.out.println(checkBoom);
		
		angle = Math.atan2(mouseY1- this.getHeight()/2  , mouseX1- this.getWidth()/2);
		 ballShoot.x+=(30*(Math.cos(angle)));
		 ballShoot.y+=(30*(Math.sin(angle)));
		
		//System.out.println((double)ballShoot.y/(double)ballShoot.x);	

	
	}
				
	public int distance(int x,int y,int x1,int y1) {
		 //System.out.println((int)Math.sqrt((Math.pow(Math.abs(x-x1),2))+(Math.pow(Math.abs(y-y1),2))));
		return (int)Math.sqrt((Math.pow(Math.abs(x-x1),2))+(Math.pow(Math.abs(y-y1),2)));
	}
	
		
	
	public void moveBall(Ball ball) {
		if(ball.x <= 704 && ball.y==65) {
			ball.x+=16;
			ball.y=65;
		}  else if(ball.x >= 720 && ball.y <=529) {
			ball.x=720;
			ball.y+=16;
		 } else if(ball.x>=96 && ball.y >= 545 ) { 
			 ball.x-=16;
			 ball.y=545;
		 } else if (ball.x <=96 && ball.y>=360) {
			 ball.x=96;
			 ball.y-=16;
		 }
	}
	public void start() {
		System.out.println("Start");
		thread = new Thread(this);
		thread.start();
		

	}
	public void stop() {
		System.out.println("Stop");
		thread.stop();
		

	}
	public void run() {
		
		System.out.println("run");
		timerFrog.start();
		//insertBall.start();
		while(true) {
			if(check==1) {
				checkBoom();
				repaint();
				try {
					thread.sleep(100);
				} catch(InterruptedException e) {}	
			}else 
				repaint();
			
			
		}
	}
		
	
	public void destroy() {
	
	}

	public void paint(Graphics g) {
		update(g);

	}
	public void update(Graphics g) {
		//System.out.println(check);
		//g.clearRect(0,0,800,600);
		if(check==1) {
			//g.clearRect(0,0,800,600);
			g.drawImage(bgGame.getImage(),0, 0,bgGame.getIconWidth(),bgGame.getIconHeight(),this);
			drawShootBall(g);
			drawFrog(g);
			drawBall(g);
		} else if(check==0) 
			home.repaint();
		else if(check==-1)
			load.repaint();	
		else if(check==2){
			gameOver.repaint();
		}
		
	}
	public void drawShootBall(Graphics g) {
		if(checkShoot) {
			shootBall();
			if(checkBoom) {
			 g.drawImage(ballShoot.image,ballShoot.x, ballShoot.y,ballShoot.image.getWidth(this),ballShoot.image.getHeight(this), this);
		
			} 
		}
	}
	public void drawBall(Graphics g) {
		for(int i=0;i<rear;i++) {
		//System.out.println(rear);
		g.drawImage(balls[i].image,balls[i].x,balls[i].y,balls[i].image.getWidth(this),balls[i].image.getHeight(this),this);
		//System.out.println(balls[0].x+" "+balls[0].y);
		}
		//System.out.println(balls[0].y/balls[0].x);
	}
	public void drawFrog (Graphics g) {
		// System.out.println(frog.color);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform origXform = g2d.getTransform();
		AffineTransform newXform = (AffineTransform)(origXform.clone());
		//center of rotation is center of the panel
		 int xRot = this.getWidth()/2;
		 int yRot = this.getHeight()/2;
		 //System.out.println(Math.toRadians(currentAngle));
		 newXform.rotate(Math.toRadians(currentAngle), xRot, yRot);
		 g2d.setTransform(newXform);
		 //draw image centered in panel
		// System.out.println(getWidth()+"+"+getHeight());
		 int x  = (this.getWidth() - frog.image.getWidth(this))/2;
		 int y  = (this.getHeight() - frog.image.getHeight(this))/2; 
		 //System.out.println("x: "+x+" y: "+y);
		 g2d.drawImage(frog.image,x,y,frog.image.getWidth(this),frog.image.getHeight(this),this);
		 g2d.setTransform(origXform);
		
		
	}
	public void roFrog() {
		 
		currentAngle =-1*Math.atan2(mouseX-frog.getX(),mouseY-frog.getY()) / Math.PI * 180;
		//System.out.println(currentAngle);
	}
	
	public Dimension getPreferredSize() {
		System.out.println("dfdf");
	     return new Dimension (frog.image.getWidth(this), frog.image.getHeight(this));
	 }
	
	
	
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(check==1) {
				mouseX=e.getX();
				mouseY=e.getY();	
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			//System.out.println(e.getX()+" "+e.getY());	
			if(check==1) {
			mouseX=e.getX();
			mouseY=e.getY();	
			}
		}

	
	
}


