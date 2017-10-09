package test2;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Receive extends JFrame {
	private Socket screensocket;
	private String ip;
	private JLabel l;
	private int screen = 9999;
	public Receive(String ip, int screen) {
		this.screen = screen;
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.ip = ip;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoadImage loader = new LoadImage();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int sw = (int) d.getWidth();
		int sh = (int) d.getHeight();
		setSize(sw, sh);
		setVisible(true);
		ScreenThread st = new ScreenThread();
		ImageIcon loadScreen = new ImageIcon();
		l = new JLabel();
		l.setIcon(loadScreen);
		l.setHorizontalAlignment(JLabel.CENTER);
		add(l);
		st.start();
	}
	//=============================================
	class ScreenThread extends Thread {
		public void run() {
			while (true) {
				try {
					screensocket = new Socket(ip, screen);
					InputStream in = screensocket.getInputStream();
					BufferedImage img = ImageIO.read(in);
					l.setText("");
					ImageIcon icon = new ImageIcon(img);
					l.setIcon(icon);
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}
	//====================================================
	class LoadImage{
		public BufferedImage loadImage(String fileName) {
		fileName = "img/" + fileName;
		BufferedImage im = null;
		try {
			im = ImageIO.read(getClass().getClassLoader().getResource(fileName));
		}catch (IOException e) {
		System.out.println("Error loading " + fileName);
		}
			return im;
		}
}
}
