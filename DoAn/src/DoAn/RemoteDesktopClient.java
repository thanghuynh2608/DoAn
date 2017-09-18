package DoAn;



import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.omg.SendingContext.RunTime;
public class RemoteDesktopClient  extends JFrame implements Serializable{
public JButton btn;
//====================client
Socket mySocket;
Scanner scn;
PrintStream prntStream;
//=====================robot
Robot robot;
BufferedImage screenShot;
ImageIcon imageIcon;
String str;
FileOutputStream fos; 
ObjectOutputStream oos;
File file;

public RemoteDesktopClient() {
imageIcon=new ImageIcon();
file=new File("client.png");
btn = new JButton("Send");
try {
mySocket = new Socket("192.168.1.4", 2009);
new Send(mySocket);
robot.mouseMove(1000, 800);
setSize(300, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);

setVisible(true);
//==========================client
}catch (UnknownHostException ex) {
System.out.println("UnKnown Host Exception");
} catch (IOException ex) {
System.out.println("IO Exception after unknown");
}
//==========================client
}

public static void main(String[] args) {
RemoteDesktopClient client = new RemoteDesktopClient();
}
//===========================================
class Send extends Thread {
public Send(Socket s) {
try {
    oos = new ObjectOutputStream(mySocket.getOutputStream());
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