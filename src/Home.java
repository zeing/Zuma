import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;

import sun.audio.*;

import java.applet.*;
import java.net.*;


public class Home extends JPanel {
	Image bg = Toolkit.getDefaultToolkit().getImage("pic/home2.gif");
	//ImageIcon bg = new ImageIcon("pic/serpents.jpg");
	JLabel play = new JLabel(new ImageIcon("pic/playbutton.png"));
	JLabel exit = new JLabel(new ImageIcon("pic/exitbutton.png"));

	boolean change=false;
	private AudioClip sound;
	
	Home () { 
		setLayout(null);
		play.setBounds(375,225,250,89);
		exit.setBounds(375,325,250,89);
		add(play);
		add(exit);
		//playS();
		System.out.println("home");
	}
	public void playS()
	   {
	      try 
	      {
	    	  sound = Applet.newAudioClip(new URL("file:sound/main.wav"));
	    	  sound.loop();      
	      } 
	      catch ( MalformedURLException e ) 
	      {
	         e.printStackTrace();
	      }
	      sound.loop();
	   }
	   
	   public void stopS()
	   {
		   sound.stop();
	   }
	public void paintComponent(Graphics g){
		//super.paintComponents(g);	
		//System.out.println("asgg181561h");
		g.clearRect(0,0,800,600);	
		g.drawImage(bg,0, 0,800, 600, this);
	}
	
	
}

