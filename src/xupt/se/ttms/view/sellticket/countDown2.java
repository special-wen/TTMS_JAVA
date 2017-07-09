package xupt.se.ttms.view.sellticket;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xupt.se.ttms.model.Seat;

public class countDown2 extends JFrame implements ActionListener{
	
	private JPanel contPan=new JPanel();
	private int k=0,m=1;
	private JLabel q1,q2,q3,q4;

	public countDown2(int[] seat) {
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("那一年剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});
		contPan.setBounds(0, 0, 400, 300);
		contPan.setLayout(null);
		this.add(contPan);
		for(int i=0;;i++){
			if(seat[i]==0) {
				k=i;
				break;
			}
		}
		JLabel se=new JLabel("你选择的座位为：");
		se.setBounds(10, 20, 100, 20);
		contPan.add(se);
		while(m!=k){
			q1=new JLabel(seat[m]+"行"+seat[m+1]+"列");
			q1.setBounds(20, 40, 100, 20);
			m++;
			q2=new JLabel(seat[m]+"行"+seat[m+1]+"列");
			q2.setBounds(20, 60, 100, 20);
			m++;
			q3=new JLabel(seat[m]+"行"+seat[m+1]+"列");
			q1.setBounds(20, 80, 100, 20);;
			m++;
			q4=new JLabel(seat[m]+"行"+seat[m+1]+"列");
			q1.setBounds(20, 100, 100, 20);;
		}
		if(k==3){
			contPan.add(q1);
		}
		if(k==5){
			contPan.add(q1);
			contPan.add(q2);
		}
		if(k==7){
			contPan.add(q1);
			contPan.add(q2);
			contPan.add(q3);
		}
		if(k==9){
			contPan.add(q1);
			contPan.add(q2);
			contPan.add(q3);
			contPan.add(q4);
		}
		JLabel price=new JLabel("票价为:");
		price.setBounds(10, 150, 100, 20);
		contPan.add(price);
		JLabel pp=new JLabel((k/2)*seat[0]+"元");
		pp.setBounds(20, 170, 100, 20);
		contPan.add(pp);
	}
    
	class ShowTime extends TimerTask {
		public void run() {
			// 刷新
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	protected void onWindowClosing(){
		this.dispose();
	}
}
