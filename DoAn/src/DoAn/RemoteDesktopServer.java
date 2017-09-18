package DoAn;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RemoteDesktopServer extends JFrame implements Serializable{
	ServerSocket serverSocket;
	Socket socket;

	public RemoteDesktopServer() {
	    try {
	        serverSocket = new ServerSocket(2009);
	        while (true) {
	            socket = serverSocket.accept();
	            new ChatHandeler(socket);
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}

public static void main(String[] args) {
	new FormServer();
    RemoteDesktopServer server=new RemoteDesktopServer();


	}
}

//========================================

class ChatHandeler extends Thread {

	FileInputStream fis;
	ObjectInputStream ois;
	ImageIcon img;
	Image image;
	File file;
	JFrame frame;
	RemoteDesktopServer server;

public ChatHandeler(Socket s) {
    file=new File("Server.png");
    frame=new JFrame();
    try {
        ois=new ObjectInputStream(s.getInputStream());
        start();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

public void run() {
    while (true) {
        try {

            img = (ImageIcon) ois.readObject();
            image= img.getImage();
            frame.add(new MyPanel(image), BorderLayout.CENTER);
            img = null;
            image = null;
            Runtime runtime= Runtime.getRuntime();
            runtime.gc();
            frame.setSize(1366, 768);
            frame.setVisible(true);
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                System.out.println(" sleep  exception");
            }
            System.out.println("Read");
        } catch (ClassNotFoundException ex) {
            System.out.println(" Class not found exception");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
}
}

class MyPanel extends JPanel {
Image image;
  public MyPanel(Image image  ) {
      this.image=image;
Toolkit kit = Toolkit.getDefaultToolkit(  );
image = kit.getImage("client.png");

  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    g2.drawImage(image, 10, 10, this);

  }
}
