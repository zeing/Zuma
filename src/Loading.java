import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;




import java.applet.*;
import java.net.*;

import sun.audio.*;

public class Loading extends JPanel {	
	
	//Image bgloading = Toolkit.getDefaultToolkit().getImage("picture/loadingscreen.jpg");
	ImageIcon bgloading = new ImageIcon("pic/loadingscreen2.gif");
	private AudioClip sound;
	Loading () { 
		setLayout(null);
		//playS();
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
	public void paintComponent(Graphics g) {	
		g.clearRect(0,0,800,600);
		g.drawImage(bgloading.getImage(),0, 0,800, 600,this);

	}
	
}

