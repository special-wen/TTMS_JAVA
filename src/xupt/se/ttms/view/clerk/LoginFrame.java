//package xupt.se.ttms.view.clerk;
//
//import java.awt.*;
//
//import java.awt.event.*;
//
//import javax.swing.*;
//
//public class LoginFrame{
//		
//	JFrame mainFrame = new JFrame("娆㈣繋浣跨敤");
//	
//	public LoginFrame(){
//	
//		int FWIDTH = 1024;
//		int FHEIGHT = 768;
//		mainFrame.setSize(1024, 768);
//		mainFrame.setLocation(0, 0);
//		mainFrame.setResizable(false);
//		mainFrame.setLayout(null);
//		mainFrame.addWindowListener(new WindowAdapter(){
//			public void windowClosing(WindowEvent e){
//				System.exit(0);
//			}
//		});
//
//		JPanel pwelcomeimg = new JPanel();
//		JPanel plogin = new JPanel();
//		pwelcomeimg.setSize(FWIDTH, FHEIGHT*3/4);
//		plogin.setSize(FWIDTH, FHEIGHT/4);
//		pwelcomeimg.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//		plogin.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//		plogin.setLocation(0, FHEIGHT*3/4);
//		
//		ImageIcon imgwelcome = new ImageIcon("resource/image/main.jpg");
//		ImageIcon imglogin = new ImageIcon("resource/image/main1.jpg");
//		JLabel jLabelwel = new JLabel(imgwelcome);
//		JLabel jLabellogin = new JLabel(imglogin);
//		jLabellogin.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
//		
//		JButton blogin = new JButton("鐧婚檰");
//		JButton breset = new JButton("閲嶇疆");
//		JTextField usernametext = new JTextField(10);
//		JPasswordField passwordtext = new JPasswordField(10);
//		//JLabel username = new JLabel("鐢ㄦ埛鍚�);
//		
//		JLabel username = new JLabel("替换一下");
//		JLabel password = new JLabel("瀵嗙爜");	
//		
//		jLabellogin.add(username);
//		jLabellogin.add(usernametext);
//		jLabellogin.add(password);
//		jLabellogin.add(passwordtext);
//		jLabellogin.add(blogin);
//		jLabellogin.add(breset);		
//		
//		pwelcomeimg.add(jLabelwel);
//		plogin.add(jLabellogin);
//		blogin.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						ClerkMenuFrame clerkMenuFrame = new ClerkMenuFrame();
//						clerkMenuFrame.setVisible(true);
//						mainFrame.dispose();
//					}
//				});
//		
//		mainFrame.add(pwelcomeimg);
//		mainFrame.add(plogin);
//		pwelcomeimg.setVisible(true);
//		plogin.setVisible(true);
//		mainFrame.setVisible(true);
//		
//	}
//	
//	public static void main(String[] args) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new LoginFrame();
//				} catch (Exception e) {
//					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}
//
//}
