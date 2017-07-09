package xupt.se.ttms.view.schedule;
import java.security.Timestamp;
import java.util.Date;
import java.util.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date.*;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.util.DBUtil;
import xupt.se.ttms.view.play.*;
import xupt.se.ttms.view.schedule.*;
 import xupt.se.ttms.dao.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.eltima.components.ui.DatePicker;

public class schedAddUI  extends PopUITmpl implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton btnCancel, btnSave; 	//取消，保存按鈕
	protected boolean rst=false,flag=false; 				//操作结果
	private JLabel lblstudio_id, lblplay_id, lblsched_Time,lblsched_Tprice;
	protected JTextField  txtsched_Tprice;
	protected JComboBox studioName,playName;
	//JButton txtsched_time;
	  DatePicker datepick;
	private int m, stuid,plaid;
	
	protected void initContent(){
		this.setTitle("添加演出计划");
		lblstudio_id = new JLabel("演出厅名称:");
		lblstudio_id.setBounds(60, 30, 80, 30);
		contPan.add(lblstudio_id);
	     studioName=new JComboBox();
	     StudioSrv stuSrv = new StudioSrv();
			List<Studio> sList = null;
			sList=new LinkedList<Studio>();
			sList=stuSrv.Fetchname("可用");
			String[] stu = new String[100];
			int j=0;
			Iterator<Studio> itr1=sList.iterator();
			 while(itr1.hasNext()){
				 Studio stud=itr1.next();
				 stu[j]=stud.getName();
				 studioName.addItem(stu[j]);
			 }		
		studioName.setEditable(true);
		studioName.setBounds(150, 30, 200, 30);
		contPan.add(studioName);
		
		lblplay_id = new JLabel("剧目名称:");
		lblplay_id.setBounds(60, 80, 80, 30);
		contPan.add(lblplay_id);
		
		
		 playName=new JComboBox();
		PlaySrv playSrv = new PlaySrv();
		List<Play> pList = null;
		pList=new LinkedList<Play>();
		pList=playSrv.Fetchname(1);
		String[] play = new String[100];
		int i=0;
		Iterator<Play> itr=pList.iterator();
		 while(itr.hasNext()){
			 Play pla=itr.next();
			 play[i]=pla.getName();
			 playName.addItem(play[i]);
		 }
		
		playName.setEditable(true);
		playName.setBounds(150, 80, 200, 30);
		contPan.add(playName);

		
		lblsched_Time = new JLabel("演出时间:");
		lblsched_Time.setBounds(60, 130, 80, 30);
		contPan.add(lblsched_Time);
        datepick = getDatePicker();
        datepick.setBounds(150, 130, 200, 30);
        contPan.add(datepick);

		lblsched_Tprice = new JLabel("票价:");
		lblsched_Tprice.setBounds(60, 180, 80, 30);
		contPan.add(lblsched_Tprice);
		txtsched_Tprice = new JTextField();
		txtsched_Tprice.setBounds(150, 180, 200, 30);
		contPan.add(txtsched_Tprice);

		
		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(60, 220, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 220, 60, 30);
		contPan.add(btnCancel);    
}


	
public static DatePicker getDatePicker() {
    final DatePicker datepick;
    // 格式
    String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
    // 当前时间
    Date date = new Date();
    // 字体
    Font font = new Font("Times New Roman", Font.BOLD, 14);

    Dimension dimension = new Dimension(177, 24);

    int[] hilightDays = { 1, 3, 5, 7 };

    int[] disabledDays = { 4, 6, 5, 9 };
//构造方法（初始时间，时间显示格式，字体，控件大小）
    datepick = new DatePicker(date, DefaultFormat, font, dimension);

    datepick.setLocation(137, 83);//设置起始位置
    /*
    //也可用setBounds()直接设置大小与位置
    datepick.setBounds(137, 83, 177, 24);
    */
    // 设置一个月份中需要高亮显示的日子
    datepick.setHightlightdays(hilightDays, Color.red);
    // 设置一个月份中不需要的日子，呈灰色显示
    datepick.setDisableddays(disabledDays);
    // 设置国家
    datepick.setLocale(Locale.CANADA);
    // 设置时钟面板可见
    datepick.setTimePanleVisible(true);
    return datepick;
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	
	protected void btnSaveClicked(){
		long len;
		String studname=studioName.getSelectedItem().toString();
		ScheduleSrv schedSrv=new ScheduleSrv();
		stuid=schedSrv.FetchstudID(studname);
		String playname=playName.getSelectedItem().toString();
		plaid=schedSrv.FetchplayID(playname);
		ScheduleDAO sDao=new ScheduleDAO();
		List<Schedule> sList=null;
		sList=new LinkedList<Schedule>();
		sList=sDao.select(stuid);
		Iterator<Schedule> itr = sList.iterator();
		while (itr.hasNext()) {
			Schedule sched1 = itr.next();	
			java.sql.Timestamp time = java.sql.Timestamp.valueOf(datepick.getText());
			System.out.println(datepick.getText());
			len=schedSrv.select_Time(sched1.getPlay_id());
			java.sql.Timestamp time1 = java.sql.Timestamp.valueOf(sched1.getSched_time());
			long time2=time1.getTime()+(len+30)*60*1000;
			if( time.getTime() >= time1.getTime()&&time.getTime()<=time2) {
				flag=true;	
		    }
		}
       if(flag==false){
    	    ScheduleSrv schedsrv=new ScheduleSrv();
   		    Schedule sched=new Schedule();
    	    sched.setStudio_id(stuid);
    	    sched.setPlay_id(plaid);
			sched.setSched_time(datepick.getText());
			sched.setSched_TPrice(Integer.parseInt(txtsched_Tprice.getText()));
			schedsrv.add(sched);
			createTic(sched);
			this.setVisible(false);
			rst=true;
		}
		else if(flag==true){
			rst=false;
			JOptionPane.showMessageDialog(null,"有冲突，无法添加！");	
		}
		
	}
	public boolean getReturnStatus(){
		   return rst;
	}
	private void createTic(Schedule sch){
		TicketSrv ticSrv = new TicketSrv();
		SeatSrv seaSrv = new SeatSrv();
		Ticket tic = new Ticket();
		tic.setSched_id(sch.getSched_id());
		tic.setTicket_price(sch.getSched_TPrice());
		
		Seat sea;
		System.out.println(sch.getStudio_id());
		List<Seat> seaList = seaSrv.Fetchw(sch.getStudio_id());
		//System.out.println(seaList.size());
		Iterator<Seat> itr = seaList.iterator();
		while(itr.hasNext()){
			sea = itr.next();
			//System.out.println(sea.getSeat_id());
			tic.setSeat_id(sea.getId());
			tic.setTicket_status(0);
			ticSrv.add(tic);
		}
	
	}
		
			
			

}
