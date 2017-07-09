package xupt.se.ttms.dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import java.util.Vector;
//import java.awt.*;
import javax.swing.*;

import xupt.se.ttms.view.tmpl.ImagePanel;

public class datasale extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton select,update;
    JTable table;
    Object[][] body = new Object[50][5];
    String fields[] = { "票号", "座位号", "剧院号", "价格", "状态" };
    Connection conn;
    Statement st;
    ResultSet rs;
    JTabbedPane tp;
    protected final ImagePanel headPan = new ImagePanel("resource/image/header.jpg");
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JButton btnModPwd = new JButton("修改密码");
	protected JButton btnExit = new JButton("返回");	


    public datasale() {
        this.setTitle("数据库操作");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel ps = new JPanel();

        select = new JButton("查询");
        update = new JButton("更新");

        select.addActionListener(this);
        update.addActionListener(this);

        ps.add(select);
        ps.add(update);
        
        this.connection();
        table = new JTable(body, fields);
        headPan.setSize(800, 80);
        tp = new JTabbedPane();
        tp.add("ticket", new JScrollPane(table));
        tp.setSize(800, 200);
        ps.setBounds(0,480,800,80);
        this.getContentPane().add(headPan, "North");
        this.getContentPane().add(tp, "Center");
        this.getContentPane().add(ps, "South");
        
        this.setVisible(true);
        
        
    }

    public void connection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName = TTMS","liufan","123456");
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
            System.out.println("连接错误：" + ex.getMessage());
            //System.out.println("连接错误");
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        datasale dt = new datasale();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == select) {
            select();
        }
        if (e.getSource() == update) {
            update();
        }
    }

    private void update() {
        // TODO Auto-generated method stub
        try {
            int row = table.getSelectedRow();
            JTextField t[] = new JTextField[8];
            t[0] = new JTextField("输入座位号：");
            t[0].setEditable(false);
            t[1] = new JTextField();
            t[1].setText(Integer.toString((int)body[row][1]));
            t[2] = new JTextField("输入演出厅号：");
            t[2].setEditable(false);
            t[3] = new JTextField();
            t[3].setText(Integer.toString((int)body[row][2]));
            t[4] = new JTextField("输入票价: ");
            t[4].setEditable(false);
            t[5] = new JTextField();
            t[5].setText(Integer.toString((int)body[row][3]));
            t[6] = new JTextField("输入状态：");
            t[6].setEditable(false);
            t[7] = new JTextField();
            t[7].setText(Integer.toString((int)body[row][4]));
            String but[] = {"确定", "取消" };
            int go = JOptionPane.showOptionDialog(null, t,  "修改信息", JOptionPane.YES_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, but, but[0]);
            if (go == 0) {
            	int nseatid= Integer.parseInt(t[1].getText());
                int nschedid =Integer.parseInt(t[3].getText());
                String nticketprice =(String)t[5].getText();
                int nticketstatus = Integer.parseInt(t[7].getText());
                String str = "update ticket set seat_id = '" + nseatid + "',sched_id='" + nschedid + "',ticket_price='" + nticketprice + "',ticket_status='" + nticketstatus
                        + "'where ticket_id='" + body[row][0] + "' ";
                st.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "修改数据成功！");
                this.select();
                st.executeUpdate(str);
                //JOptionPane.showMessageDialog(null, "数据已经成功插入！");
            }

        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "更新数据失败！");
        }
    }

    private void select() {
        // TODO Auto-generated method stub
        String str = "select * from ticket";
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
            }
            int i = 0;
            rs = st.executeQuery(str);
            while(rs.next()){
                body[i][0]=rs.getInt("ticket_id");
                body[i][1]=rs.getInt("seat_id");
                body[i][2]=rs.getInt("sched_id");
                body[i][3]=rs.getInt("ticket_price");
                body[i][4]=rs.getInt("ticket_status");
                i=i+1;
            }
            this.repaint(i);
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
    }
}
