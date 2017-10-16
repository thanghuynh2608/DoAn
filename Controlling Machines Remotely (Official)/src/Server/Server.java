package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

public class Server  extends JFrame{
	ServerSocket serverSocket;
	Socket socket;
	public Server(int screen, int mouseclick, int mousemove, int keyboard) {
		Send s = new Send(screen);
		new Thread(s).start();
		MouseClick mc = new MouseClick(mouseclick);
		new Thread(mc).start();
		MouseMove mm = new MouseMove(mousemove);
		new Thread(mm).start();
		Keyboard kb = new Keyboard(keyboard);
		new Thread(kb).start();
	}
	
	//==================================
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FormServer();
		Server sv = new Server(9999,8888,7777,6666); 
		// port screen: 9999, port mouseclick: 8888, port mousemove : 7777, port key: 6666
	}

}
