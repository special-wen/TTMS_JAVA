package xupt.se.ttms.view.schedule;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.eltima.components.ui.DatePicker;

import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.schedule.schedAddUI;
import xupt.se.util.DBUtil;
public class schedEditUI extends schedAddUI {
		private static final long serialVersionUID=1L;
		private Schedule sched;
		private static String sName,pName,s;
		boolean flag=false;
		public schedEditUI(Schedule sched1){
			initData(sched1);
		}
		public void initData(Schedule sched1){
			if(null==sched1){
				return;
			}
			

			ScheduleSrv schedSrv=new ScheduleSrv();
			sName=schedSrv.Fetchstudname(sched1.getStudio_id());
			studioName.setSelectedItem(sName);
		    pName=schedSrv.Fetchplayname(sched1.getPlay_id());
		    playName.setSelectedItem(pName);
//		    datepick.setText(sched1.getSched_time());
		   // System.out.println(sched1.getSched_time());
		   // datepick.set
		    //datepick.getText();
		    String s=sched1.getSched_time();
		   // date = new Date(sched1.getSched_time());
//		    System.out.println(date);
		    /*try{
		    java.util.Date date = f.parse(s);
		    datepick = new DatePicker(date,DefaultFormat, font, dimension);
		    }catch (Exception ex) { 
				System.out.println(ex.getMessage()); 
				
			}*/
		    datepick.setToolTipText(s);
		   // datepick 
			txtsched_Tprice.setText(String.valueOf(sched1.getSched_TPrice()));
		    sched=sched1;
		    this.invalidate();
		}
//		private static DatePicker getDatePicker1() {
//		    final DatePicker datepick;
//		    // 格式
//		    String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
//		    // 当前时间
//		    
//		    Date date1 = new Date(100,2,1);
//		    // 字体
//		    Font font = new Font("Times New Roman", Font.BOLD, 14);
//
//		    Dimension dimension = new Dimension(177, 24);
//
//		    int[] hilightDays = { 1, 3, 5, 7 };
//
//		    int[] disabledDays = { 4, 6, 5, 9 };
//		//构造方法（初始时间，时间显示格式，字体，控件大小）
//		    datepick = new DatePicker(date1, DefaultFormat, font, dimension);
//
//		    datepick.setLocation(137, 83);//设置起始位置
//		    /*
//		    //也可用setBounds()直接设置大小与位置
//		    datepick.setBounds(137, 83, 177, 24);
//		    */
//		    // 设置一个月份中需要高亮显示的日子
//		    datepick.setHightlightdays(hilightDays, Color.red);
//		    // 设置一个月份中不需要的日子，呈灰色显示
//		    datepick.setDisableddays(disabledDays);
//		    // 设置国家
//		    datepick.setLocale(Locale.CANADA);
//		    // 设置时钟面板可见
//		    datepick.setTimePanleVisible(true);
//		    return datepick;
//		}
		protected void btnSaveClicked(){
			int len;
			int stuid,plaid;
			String studname=studioName.getSelectedItem().toString();
			ScheduleSrv schedSrv =new ScheduleSrv();
			stuid=schedSrv.FetchstudID(studname);
			ScheduleDAO sDao=new ScheduleDAO();
			List<Schedule> sList=null;
			sList=new LinkedList<Schedule>();
			sList=sDao.select(stuid);
			Iterator<Schedule> itr = sList.iterator();
			while (itr.hasNext()) {
				Schedule sched1 = itr.next();	
				System.out.println(datepick.getText());
				java.sql.Timestamp time = java.sql.Timestamp.valueOf(datepick.getText());
				java.sql.Timestamp time1 = java.sql.Timestamp.valueOf(sched1.getSched_time());
				len=schedSrv.select_Time(sched1.getPlay_id());
				long time2=time1.getTime()+(len+30)*60*1000;
				if( time.getTime() >= time1.getTime()&&time.getTime()<=time2) {
					flag=true;	
			    }
			
			}
			if(flag==false){
				String playname=playName.getSelectedItem().toString();
				plaid=schedSrv.FetchplayID(playname);
			ScheduleSrv schedSrv1 = new ScheduleSrv();
				Schedule sched2= sched;
				sched2.setStudio_id(stuid);
				sched2.setPlay_id(plaid);
				sched2.setSched_time(datepick.getText());
				sched2.setSched_TPrice(Integer.parseInt(txtsched_Tprice.getText()));
				schedSrv1.modify(sched2);
				this.setVisible(false);
				rst=true;
			}
			else  if(flag==true){
			rst=false;
			//this.setVisible(false);
			JOptionPane.showMessageDialog(null,"有冲突，无法修改！");	
		}
		
	}
	public boolean getReturnStatus(){
		   return rst;
	}
}
