package xupt.se.ttms.view.login;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import xupt.se.ttms.model.Employeechart;
import xupt.se.ttms.model.Moviechart;
import xupt.se.ttms.service.EmployeechartSrv;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.MoviechartSrv;
import xupt.se.ttms.view.Selectplay.SelectplayMgrUI;
import xupt.se.ttms.view.employeechart.EmployeechartMgrUI;
import xupt.se.ttms.view.moviechart.MoviechartMgrUI;
import xupt.se.ttms.view.sellticket.SellTicketUI;
import xupt.se.ttms.view.system.*;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.ttms.view.user.UserMgrUI;
/**
 * @author Administrator
 *
 */

public class SalerLogin extends JFrame  {

	private static final long serialVersionUID = 1L;
	private int frmWidth=1024;
	private int frmHeight=700;
	protected ImagePanel headPan = new ImagePanel("resource/image/header.jpg");
	protected JPanel contPan = new JPanel();
	protected JPanel  barPan=new JPanel();
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();

	public SalerLogin(){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("那一年剧院票务管理系统");
		this.setLayout(null);
//		this.addWindowListener(new WindowAdapter(){
//			public void windowClosing(WindowEvent e){
//				onWindowClosing();
//			}
//		});		
		
		headPan.setBounds(0, 0, frmWidth, 80);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 80, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);	
		
		
		
		initHeader();
		initContent();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SalerLogin().setVisible(true);;
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
		
	}
	public int getWidth(){
		return this.frmWidth;
		
	}
	public int getHeight(){
		return this.frmHeight;
		
	}
	
	private void initHeader() {
		try {

			usrLabel.setBounds(frmWidth-220, 25, 150, 30);
			usrLabel.setText("当前用户：");
			usrLabel.setFont(new java.awt.Font("宋体", 1, 20));
			headPan.add(usrLabel);
			
			usrName.setBounds(frmWidth-110, 25, 80, 30);
			usrName.setText("售票员");
			usrName.setFont(new java.awt.Font("宋体", 1, 20));
			usrName.setForeground(Color.blue);				
			headPan.add(usrName);
			
			//Show the information of current user
			showCurrentUser();
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	
	private void btnModUserClicked(){
		SysUserModUI dlgUserMod=new SysUserModUI();
		dlgUserMod.setModal(true);
		dlgUserMod.setVisible(true);
	}	
	
	private void showCurrentUser(){
		LoginedUser curUser=LoginedUser.getInstance();
		String name=curUser.getEmpName();
		if(null==name ||  name.isEmpty())
			usrName.setText("售票员");
		else
			usrName.setText(name);		
	}
	
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		System.exit(0);
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
		//放置图片
		JLabel j3=new JLabel(new ImageIcon("resource/image/出售查询.png"));
		JLabel j1=new JLabel(new ImageIcon("resource/image/淘票票.png"));
		JLabel j2=new JLabel(new ImageIcon("resource/image/销售额查询.png"));
		JLabel j4=new JLabel(new ImageIcon("resource/image/帮助.png"));
		
		j1.setBounds(100, 60, 128, 128);
		contPan.add(j1);
		j2.setBounds(500, 60, 128, 128);
		contPan.add(j2);
		j3.setBounds(100, 350, 128, 128);
		contPan.add(j3);
		j4.setBounds(500, 350, 128, 128);
		contPan.add(j4);
		
		
		JButton button1=new JButton("查看演出");
		button1.setBounds(100, 220, 130,40);
		button1.setContentAreaFilled(false);
		contPan.add(button1);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SelectplayMgrUI sm=new SelectplayMgrUI(); 
				sm.setVisible(true);
			}
		});
		
		JButton button2=new JButton("售票");
		button2.setBounds(500, 220, 130,40);
		button2.setContentAreaFilled(false);
		contPan.add(button2);
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SellTicketUI sm=new SellTicketUI(); 
				sm.setVisible(true);
			}
		});
		
		JButton button3=new JButton("电影票房排行");
		button3.setBounds(100, 500, 130,40);
		button3.setContentAreaFilled(false);
		contPan.add(button3);
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				Moviechart m = new Moviechart();
				m = new MoviechartSrv().count(m);
				MoviechartMgrUI frmMcMgr = new MoviechartMgrUI(m);
				frmMcMgr.setVisible(true);

			}
		});
		
		JButton button4=new JButton("帮助");
		button4.setBounds(500, 500, 130,40);
		button4.setContentAreaFilled(false);
		contPan.add(button4);
	
		//每个功能的简述
		JLabel jj1=new JLabel("查看演出");
		jj1.setBounds(260,80,100,40);
		JLabel area1=new JLabel("功能简述：查看剧目，时长，类型，地区，状态，票数");
		area1.setBounds(260, 120, 250, 50);
		JLabel  j11=new JLabel("操作人：售票员");
		j11.setBounds(260, 170, 100, 40);
		contPan.add(j11);
		contPan.add(jj1);
		contPan.add(area1);
		
		JLabel jj2=new JLabel("售票");
		jj2.setBounds(660,80,100,40);
		JLabel area2=new JLabel("功能简述：进行售票操作");
		area2.setBounds(660, 120, 250, 50);
		JLabel  j22=new JLabel("操作人：售票员");
		j22.setBounds(660, 170, 100, 40);
		contPan.add(j22);
		contPan.add(jj2);
		contPan.add(area2);
		
		JLabel jj3=new JLabel("电影票房排行");
		jj3.setBounds(260,370,100,40);
		JLabel area3=new JLabel("功能简述：查看电影票房排行榜");
		area3.setBounds(260, 410, 250, 50);
		JLabel  j33=new JLabel("操作人：售票员");
		j33.setBounds(260, 460, 100, 40);
		contPan.add(j33);
		contPan.add(jj3);
		contPan.add(area3);
		
		JLabel jj4=new JLabel("帮助");
		jj4.setBounds(660,370,100,40);
		JLabel area4=new JLabel("功能简述：为操作者提供一些帮助");
		area4.setBounds(660, 410, 250, 50);
		JLabel  j44=new JLabel("操作人：售票员");
		j44.setBounds(660, 460, 100, 40);
		contPan.add(j44);
		contPan.add(jj4);
		contPan.add(area4);
		contPan.setVisible(true);
	}
	
	//To be override by the detailed business block interface 
	protected void btnExitClicked(ActionEvent Event){
		System.exit(0);
	}	
}