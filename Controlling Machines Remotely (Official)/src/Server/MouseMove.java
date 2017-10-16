package Server;

import java.awt.Robot;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MouseMove implements Runnable {
	private ServerSocket server;

	public MouseMove(int mousemove) {
		try {
			server = new ServerSocket(mousemove);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void run() {
		while (true) {
			try {
				Socket client = server.accept();
				InputStream in = client.getInputStream();
				byte b[] = new byte[10];
				in.read(b);
				String s = new String(b).trim();
				String a[] = s.split("#");
				Robot rb = new Robot();
				int x = Integer.parseInt(a[0]);
				int y = Integer.parseInt(a[1]);
				rb.mouseMove(x, y);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
