package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FormServer extends JFrame implements ActionListener{
		  JTextField toName; // Khai báo ô nhập tên 
		  public FormServer() {
			 	//Lấy địa chỉ IP của máy.
		 	  InetAddress adr = null;
				try {
					adr = Inet4Address.getLocalHost();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      setSize(600,300); // Định nghĩa kích thước giao diện
		      setTitle("Server"+ adr); // Tiêu đề giao diện
		      Font f = new Font("Arial",Font.BOLD,30);  // Định nghĩa Font chữ nội dung   
		      toName = new JTextField("\nReady for action.\n");  
		      toName.setFont(f);
		      toName.setBackground(Color.gray);
		      add(toName, BorderLayout.CENTER); // Chia bố cục cho toName nằm ở vị trí đầu tiên
		      toName = new JTextField(""+ adr);  
		      toName.setFont(f);
		      add(toName, BorderLayout.PAGE_START); 
		      toName.setBackground(Color.pink);
		
		      setVisible(true); // Cập nhật lại giao diện
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát giao diện khi đóng  	
		  	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

}
