package Server;

import java.awt.Robot;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Keyboard implements Runnable {
	private ServerSocket server;

	public Keyboard(int keyboard) {
		try {
			server = new ServerSocket(keyboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Robot rb = new Robot();
				Socket client = server.accept();
				InputStream in = client.getInputStream();
				byte b[] = new byte[20];
				in.read(b);
				String s = new String(b).trim();
				String a[] = s.split("#");
				if (a[0].equals("keypressed")) {
					int keycode = Integer.parseInt(a[1]);
					rb.keyPress(keycode);
				} else if (a[0].equals("keyreleased")) {
					int keycode = Integer.parseInt(a[1]);
					rb.keyRelease(keycode);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
