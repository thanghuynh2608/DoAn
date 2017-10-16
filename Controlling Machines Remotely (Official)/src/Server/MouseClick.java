package Server;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MouseClick implements Runnable {
	private ServerSocket server;
	private Robot rb;

	MouseClick(int mouseclick) {
		try {
			server = new ServerSocket(mouseclick);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void run() {
		while (true) {
			try {
				rb = new Robot();
				Socket client = server.accept();
				InputStream in = client.getInputStream();
				byte b[] = new byte[1];
				in.read(b);
				String s = new String(b);
				if (s.equals("1")) {
					rb.mousePress(InputEvent.BUTTON1_MASK);
					rb.mouseRelease(InputEvent.BUTTON1_MASK);
				} else if (s.equals("3")) {
					rb.mousePress(InputEvent.BUTTON3_MASK);
					rb.mouseRelease(InputEvent.BUTTON3_MASK);
				} else if (s.equals("4")) {
					rb.mousePress(InputEvent.BUTTON1_MASK);
					rb.mouseRelease(InputEvent.BUTTON1_MASK);
					rb.mousePress(InputEvent.BUTTON1_MASK);
					rb.mouseRelease(InputEvent.BUTTON1_MASK);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}
}
