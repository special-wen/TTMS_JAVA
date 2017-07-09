package xupt.se.ttms.view.mainLogin;
import javax.swing.*;

import java.util.Iterator;
import java.util.List;
import xupt.se.ttms.model.User;
import xupt.se.ttms.model.Users;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.login.AdministratorLogin;
import xupt.se.ttms.view.login.ManagerLogin;
import xupt.se.ttms.view.login.SalerLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login1 {
    public static void main(String args[]){
        //创建框架
        JFrame jFrame = new JFrame("欢迎来到那一年影城");
        jFrame.setSize(1024,700);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);//居中显示

        //创建面板
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        jFrame.setResizable(false);
        mycode(jPanel); 
        JLabel jlablepicture = new JLabel(new ImageIcon("resource/image/mainui.jpg"));
        jPanel.add(jlablepicture);
        jlablepicture.setBounds(0, 0, 1024, 700);
      
        jFrame.setVisible(true);
    }

    private static void mycode(JPanel jPanel){
        jPanel.setLayout(null);
        Font font = new Font("宋体",Font.BOLD,20);
        //创建登陆用户名
        JLabel user = new JLabel("用户名:");
        user.setFont(font);
        user.setBounds(400,300,100,25);
        jPanel.add(user);

        //创建登陆用户名输入框
        JTextField usertext = new JTextField(10);
        usertext.setFont(font);
        usertext.setBounds(500,300,150,25);
        jPanel.add(usertext);

        //创建登陆密码区
        JLabel password = new JLabel("密码:");
        JPasswordField passwordtext = new JPasswordField(10);
        password.setBounds(400,350,100,25);
        password.setFont(font);
        passwordtext.setBounds(500,350,150,25);
        passwordtext.setFont(font);
        jPanel.add(password);
        jPanel.add(passwordtext);

        //创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(400,400,80,25);
        jPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              String username=usertext.getText();
              String pass=passwordtext.getText();
              UserSrv usersrv=new UserSrv();
              Users user=new Users();
              List<Users> uList=usersrv.Fetch(username);
              Iterator<Users> itr=uList.iterator();
              while(itr.hasNext()){
            	  user=itr.next();
             }
              if(!user.getPassword().equals(pass)){
            	  JOptionPane.showMessageDialog(null, "用户名或密码错误！"); 
              }
              if(user.getPassword().equals(pass)){
            	  if(user.getNO().equals("售票员")){
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
            	  else if(user.getNO().equals("经理")){
            		  java.awt.EventQueue.invokeLater(new Runnable() {
              			public void run() {
              				try {
              					new AdministratorLogin().setVisible(true);;
              					
              				} catch (Exception e) {
              					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
              					e.printStackTrace();
              				}
              			}
              		});
            	  }
            	  else if(user.getNO().equals("管理员")){
            		  java.awt.EventQueue.invokeLater(new Runnable() {
              			public void run() {
              				try {
              					new ManagerLogin().setVisible(true);;
              					
              				} catch (Exception e) {
              					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
              					e.printStackTrace();
              				}
              			}
              		});
            	  }
              }
            }
        });

        //退出
        JButton exit = new JButton("退出");
        exit.setBounds(550,400,80,25);
        jPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (true){
                    JFrame jframed = new JFrame("退出");
                    JPanel jpaneld = new JPanel();
                    jframed.setSize(200,100);
                    jframed.setResizable(false);
                    jframed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JLabel d = new JLabel("你真的要离开吗？？");
                    d.setBounds(0,0,150,25);
                    /*JButton NO = new JButton("点错了");*/
                    JButton YEW = new JButton("离开");
                   /* NO.setBounds(20,40,80,25);*/
                    YEW.setBounds(110,40,80,25);
                    jframed.add(jpaneld);
                    jpaneld.add(d);
                    /*jpaneld.add(NO);*/
                    jpaneld.add(YEW);
                    jframed.setLocationRelativeTo(null);
                    jpaneld.setLayout(null);
                    jframed.setVisible(true);
                 /*   NO.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            jframed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        }
                    });*/
                    YEW.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            System.exit(0);
                        }
                    });
                }
            }
        });
    }
}
