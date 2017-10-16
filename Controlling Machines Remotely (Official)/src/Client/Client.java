package Client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Client extends JFrame{
	JFrame frame;
	JTextField text;
	JButton btn;
	
	public Client() {
		frame = new JFrame();
		frame.setSize(600, 300);
		frame.setTitle("Client"); //Tên tiêu đề
		Font f = new Font("Arial",Font.BOLD,16); //Set FONT
		Font f1 = new Font("Arial",Font.BOLD,22);
		JLabel label = new JLabel("Kết nối tới :");
		label.setFont(f);
		text = new JTextField();
		text.setToolTipText("Nhập IP");
		text.setColumns(10);
		text.setFont(f1);
		btn = new JButton("Bắt đầu");
		frame.add(label, BorderLayout.PAGE_START);
		frame.add(text, BorderLayout.CENTER);
		frame.add(btn, BorderLayout.PAGE_END);
		frame.setVisible(true);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ip = text.getText();
				if (!ip.isEmpty()) {
					new Receive(ip,9999,8888,7777,6666); //Chạy Class Receive để thực hiện việc lấy ảnh từ Server sang Client
					frame.hide();
				}		
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//=======================================
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Client c = new Client();
	}

}
