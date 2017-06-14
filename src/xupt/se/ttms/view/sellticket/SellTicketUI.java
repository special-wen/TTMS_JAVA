package xupt.se.ttms.view.sellticket;


import java.awt.BorderLayout;

import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.model.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.sellticket.*;

/**
 * 
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.system.SysUserModUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
/**
 * @author Saler
 *
 */


public class SellTicketUI extends MainUITmpl{
	private static final long serialVersionUID = 1L;
	private static final LayoutManager BoxLayout = null;
	private int frmWidth=1024;
	private int frmHeight=700;
	private JButton sa,sb;
	private ScrollPane jsc;
	private JLabel first;
	private JPanel top,bottom;
	public void showCurrentUser(){
		LoginedUser curUser=LoginedUser.getInstance();
		String name=curUser.getEmpName();
		if(null==name ||  name.isEmpty())
			usrName.setText("售票员");
		else
			usrName.setText(name);		
	}
    protected void initContent() {
		Rectangle rect = contPan.getBounds();
	
		top=new JPanel();
		top.setLayout(null);
		top.setBounds(0, 5, 1024,20);
		top.setBackground(Color.PINK);
		
		
		first=new JLabel("正在热映");
		first.setBounds(10,0,100,15);
		sb=new JButton("购票");
		sb.setBounds(900, 0, 100, 15);
		sb.setBorderPainted(false);
		sb.setContentAreaFilled(false);
//		sa=new JButton("即将上映");
//		sa.setBounds(700, 0, 100, 15);
//		sa.setBorderPainted(false);
//		sa.setContentAreaFilled(false);
		top.add(first);
//		top.add(sa);
		top.add(sb);
		contPan.add(top);
		
    	
        ImageIcon p1=new ImageIcon("resource/image/加勒比海盗5.jpg");
        JLabel picturefirst=new JLabel(p1);
        picturefirst.setBounds(9, 29, 230, 260);
        contPan.add(picturefirst);
        
        ImageIcon p2=new ImageIcon("resource/image/摔跤吧爸爸.jpg");
        JLabel picturesecond=new JLabel(p2);
        picturesecond.setBounds(250, 29, 230, 260);
        contPan.add(picturesecond);
        
        ImageIcon p7=new ImageIcon("resource/image/美好的意外.jpg");
        JLabel pictureseven=new JLabel(p7);
        pictureseven.setBounds(510, 29, 230, 260);
        contPan.add(pictureseven);
        
        ImageIcon p8=new ImageIcon("resource/image/神奇女侠.jpg");
        JLabel pictureseight=new JLabel(p8);
        pictureseight.setBounds(775, 29, 230, 260);
        contPan.add(pictureseight);
        
        ImageIcon p3=new ImageIcon("resource/image/三只小猪.jpg");
        JLabel picturethird=new JLabel(p3);
        picturethird.setBounds(9, 310, 230, 260);
        contPan.add(picturethird);
        
        ImageIcon p4=new ImageIcon("resource/image/临时演员.jpg");
        JLabel pictureforth=new JLabel(p4);
        pictureforth.setBounds(250, 310, 235, 260);
        contPan.add(pictureforth);
        
        ImageIcon p5=new ImageIcon("resource/image/我的爸爸是国王.jpg");
        JLabel picturefifth=new JLabel(p5);
        picturefifth.setBounds(510, 310, 235, 260);
        contPan.add(picturefifth);
        
        ImageIcon p6=new ImageIcon("resource/image/荡寇风云.jpg");
        JLabel picturesixth=new JLabel(p6);
        picturesixth.setBounds(775, 310, 230, 260);
        contPan.add(picturesixth);
        
        
//        sa.addActionListener(new ActionListener() {
//                    @Override
//                 public void actionPerformed(ActionEvent actionEvent) {
//                        new PlayONschUI();
//                 }
//          });
         sb.addActionListener(new ActionListener() {
             @Override
          public void actionPerformed(ActionEvent actionEvent) {
                 new choseTime();
          }
     });

         contPan.setVisible(true);

    }
	
     

    public static void main(String args[]){
    	SellTicketUI frmStuMgr = new SellTicketUI();
		frmStuMgr.setVisible(true);
    }

}