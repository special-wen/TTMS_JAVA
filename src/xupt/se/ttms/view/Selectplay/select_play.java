package xupt.se.ttms.view.Selectplay;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.util.DBUtil;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class select_play extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected int frmWidth=800;
	protected int frmHeight=600;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	
	private JButton select;
	private JTable table;
	private Object body[][] = new Object[50][6];
	private String fields[]={"电影名称", "上映时间", "类型", "地区", "状态","票数"};
	
	private JLabel hint;
	private JTextField input;
	public static final ImagePanel headPan = new ImagePanel("resource/image/header_pop.jpg");
	public static final JPanel contPan = new JPanel();
	public static JLabel windowName = new JLabel();
	
	public select_play(){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		headPan.setBounds(0, 0, frmWidth, 60);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 60, frmWidth, this.frmHeight-80);
		contPan.setLayout(null);
		this.add(contPan);	
		
		initHeader();
		initContent();
		this.connection();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new select_play().setVisible(true);;
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void initHeader() {
		try {
			windowName.setBounds(frmWidth-160, 5, 160, 50);
			windowName.setFont(new java.awt.Font("dialog", 1, 20));
			windowName.setForeground(Color.blue);	
			headPan.add(windowName);
			setWindowName("查询演出");	
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	

	//Set the name of the popup window 
	public void setWindowName(String name){
		windowName.setText(name);
	}
	
	//To be override by the detailed business block interface 
//	protected void onWindowClosing(){
//		System.exit(0);
//	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
		JPanel ps = new JPanel();
		Rectangle rect = contPan.getBounds();
		select = new JButton("查询");
		select.setBounds(680,rect.height-50,60,20);
		contPan.add(select);
		select.addActionListener(this);
		
		table = new JTable(body, fields);
	    JTabbedPane tp = new JTabbedPane();
        tp.add("查询表", new JScrollPane(table));
        
        
        hint = new JLabel("请输入电影名称:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 50, 150, 20);
		contPan.add(hint);
		input = new JTextField();
		input.setBounds(220, rect.height - 50, 200, 20);
		contPan.add(input);
		JButton btnQuery = new JButton("查找");
		btnQuery.setBounds(440, rect.height - 50, 60, 20);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}

			private void btnQueryClicked() {
				// TODO Auto-generated method stub
				if (!input.getText().equals("")) {
					String playname = input.getText();
					btnQuery(playname);

				} else {
					JOptionPane.showMessageDialog(null, "请输入检索条件");
				}
			}
			private void btnQuery(String playname) {
				// TODO Auto-generated method stub
					String sql = "select play_name,sched_time,play_type_id,play_image,ticket_status from  play LEFT OUTER JOIN schedule on play.play_id=schedule.play_id LEFT OUTER JOIN ticket on schedule.sched_id=ticket.sched_id ";
					//String sql2 = "select count(*) from play LEFT OUTER JOIN schedule on play.play_id=schedule.play_id LEFT OUTER JOIN ticket on schedule.sched_id=ticket.sched_id ";
					if(!playname.isEmpty())
						sql+= " where play_name="+"'"+playname+"'";
					   // sql2+=" where play_name="+"'"+playname+"'";
					DBUtil db = new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
					}
					judge(sql);
					input.setText("");
				filltable(sql);
			}
//这个函数代码不太懂！！
			private void judge(String sql) {
				// TODO Auto-generated method stub
				 try {
					rs = st.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			     try {
					if(rs.next()==false)
					    	JOptionPane.showMessageDialog(null, "无此剧目，查询失败！");
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contPan.add(btnQuery);
		
        tp.setBounds(0,0,800,460);
        ps.setBounds(0,460,800,50);
        contPan.add(tp);
        contPan.add(ps);
	}
	public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == select) {
            select();
        }
    }
	
	private void select() {
		// TODO Auto-generated method stub
		String str = "select play_name,sched_time,play_type_id,play_image,ticket_status from  play LEFT OUTER JOIN schedule on play.play_id=schedule.play_id LEFT OUTER JOIN ticket on schedule.sched_id=ticket.sched_id;";
        filltable(str);
	}

	private void filltable(String str) {
		// TODO Auto-generated method stub
		try {
            for(int x=0;x<body.length;x++){
                body[x][0]=null;
                body[x][1]=null;
                body[x][2]=null;
                body[x][3]=null;
                body[x][4]=null;
                body[x][5]=null;
            }
            int i = 0;
            rs = st.executeQuery(str);	
            while(rs.next()){
            	int ticket_number=0;
                if(rs.getInt(5)==1)
                	ticket_number++;
                body[i][0]=rs.getString("play_name");
//                System.out.println("演出名：" + rs.getString(1));
                body[i][1]=rs.getString("sched_time");
                body[i][2]=rs.getString("play_type_id");
                body[i][3]=rs.getString("play_image");
                body[i][4]=rs.getString("ticket_status");
                body[i][5]=ticket_number;
                i=i+1;
            }
            this.repaint(i);
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
	}

	private void connection() {
		// TODO Auto-generated method stub
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName = rg_ttms","wj","123456");
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
            System.out.println("连接错误：" + ex.getMessage());
        }
	}

	//To be override by the detailed business block interface 
	protected void btnExitClicked(ActionEvent Event){
		System.exit(0);
	}
}