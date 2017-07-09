//package xupt.se.ttms.view.clerk;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.SwingConstants;
//
//import xupt.se.ttms.view.tmpl.MainUITmpl;
//
//public class ClerkMenuFrame extends MainUITmpl{
//
//	private static final long serialVersionUID = 1025028999012028956L;
//
//	public ClerkMenuFrame(){
//		initContent();
//	}
//
//	@Override
//	protected void initContent() {
//		JPanel workPanel = new JPanel();
//		workPanel.setLayout(null);
//		workPanel.setBounds(0, 0, 1000, 500);
//		
//		JButton sale = new JButton();
//		sale.setVerticalTextPosition(SwingConstants.BOTTOM);
//		sale.setHorizontalTextPosition(SwingConstants.CENTER);
//		sale.setIcon(new ImageIcon("resource/image/ticket.jpg"));
//		sale.setBackground(Color.WHITE);
//		sale.setText(" 售 票 ");
//		sale.setBounds(150, 100, 160, 160);
//		
//		sale.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent Event) {
//				SaleFrame saleFrame = new SaleFrame();
//				saleFrame.setVisible(true);
//				ClerkMenuFrame.this.dispose();
//			}
//		});
//		
//		JButton refund = new JButton();
//		refund.setVerticalTextPosition(SwingConstants.BOTTOM);
//		refund.setHorizontalTextPosition(SwingConstants.CENTER);
//		refund.setIcon(new ImageIcon("resource/image/ticket.jpg"));
//		refund.setBackground(Color.WHITE);
//		refund.setText(" 退 票 ");
//		refund.setBounds(450, 100, 160, 160);
//		
//		JButton checkList = new JButton();
//		checkList.setVerticalTextPosition(SwingConstants.BOTTOM);
//		checkList.setHorizontalTextPosition(SwingConstants.CENTER);
//		checkList.setIcon(new ImageIcon("resource/image/ticket.jpg"));
//		checkList.setBackground(Color.WHITE);
//		checkList.setText("查看销售记录");
//		checkList.setBounds(750, 100, 160, 160);
//		
//		workPanel.add(sale);
//		workPanel.add(refund);
//		workPanel.add(checkList);
//		
//		contPan.add(workPanel);
//		contPan.validate();
//		
//	}
//
//}
