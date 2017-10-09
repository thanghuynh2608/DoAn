package test2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

public class Server  extends JFrame{
	ServerSocket serverSocket;
	Socket socket;
	public Server(int screen) {
		Send s = new Send(screen);
		new Thread(s).start();
	}
	
	//==================================
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FormServer();
		Server sv = new Server(9999); // port = 9999
	}

}
