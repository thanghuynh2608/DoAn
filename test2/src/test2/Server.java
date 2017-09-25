package test2;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import org.omg.SendingContext.RunTime;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Server extends JFrame implements Serializable {
	ServerSocket serverSocket;
	Socket socket;
	//=====================robot
		Robot robot;
		BufferedImage screenShot;
		ImageIcon imageIcon;
		String str;
		FileOutputStream fos; 
		ObjectOutputStream oos;
		File file;


	public Server() {
	    imageIcon = new ImageIcon();
	    file = new File ("Server.png");
	    try {
	    	serverSocket = new ServerSocket(9999);
	    	while (true) {
	    		socket = serverSocket.accept();
	    		new Send(socket);
	    		robot.mouseMove(1000, 800);
	    		setSize(300, 300);
	    		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    		setVisible(false);
	    		}
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FormServer();
		Server server = new Server();
	}



//===============================================
class Send extends Thread{
	public Send(Socket s) {
		try {
		    oos = new ObjectOutputStream(socket.getOutputStream());
		    robot = new Robot();
		    start();
		} catch (IOException ex) {
		    ex.printStackTrace();
		} catch (AWTException ex) {
		    ex.printStackTrace();
		}
	}

	public void run() {
		while (true) {
		    try {
		    screenShot = robot.createScreenCapture(new Rectangle((Toolkit.getDefaultToolkit().getScreenSize())));
		    ImageIO.write(screenShot, "png", file);
		    imageIcon=new ImageIcon(""+file);
		    oos.writeObject(imageIcon);
		    oos.flush();
		    screenShot = null;
		    Runtime runtime= Runtime.getRuntime();
		    runtime.gc();
		        } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		    }
		}
	}
}