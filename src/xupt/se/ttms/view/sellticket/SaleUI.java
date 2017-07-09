package xupt.se.ttms.view.sellticket;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.omg.CORBA.PUBLIC_MEMBER;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.util.DBUtil;

public class SaleUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private int frmWidth=800;
	private int frmHeight=600;
	protected final ImagePanel headPan = new ImagePanel("resource/image/header.jpg");
	protected final JPanel contPan = new JPanel();
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JLabel sumPrice=new JLabel();
	public JLabel windowName = new JLabel();
	public JLabel sa=new JLabel();
	private static  int [][]sate ;
	
	JPanel top = new JPanel(null);
    JPanel bottom = new JPanel();
    JButton pay=new JButton();
    int [] seat=new int[20];
    int m=0,n=0,w=0;
    int k1=0,l1=0,k2=0,l2=0,k3=0,l3=0,k4=0,l4=0;
    int seat_id,ticket_id,studio_id,ticket_status,ticket_price;
    int row=0,col=0,l=1;
    String seat_status;
    int id=0;
    static Date[] time=new Date[2];
    
	public SaleUI(Schedule sch){
		
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("那一年剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 80);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 80, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);
		pay=new JButton("确认支付");
		pay.setBounds(600, 30, 100, 20);
		contPan.add(bottom);
		contPan.add(top);
		contPan.add(pay);
		
		
		initHeader();
		Studio seat;
		StudioSrv studioSrv=new StudioSrv();
		id=sch.getStudio_id();
		List<Studio> seaList = studioSrv.Fetchs(id);
		Iterator<Studio> itr = seaList.iterator();
		while(itr.hasNext()){
				seat = itr.next();
			    col=seat.getColCount();
			    row=seat.getRowCount();
		}
		Seat se;
		SeatSrv seatSrv=new SeatSrv();
		List<Seat> seList = seatSrv.Fetchw(id);
		//System.out.println(seaList.size());
		Iterator<Seat> itro = seList.iterator();
		sate = new int [row+1][col+1];
		for(int i=1;i<=row;i++){
			for(int j=1;j<=col;j++){
		           while(itro.hasNext()){
				     se = itro.next();
				     //System.out.println(se.getId());
			        String a=se.getState();
			        System.out.println(a);
			        if(a.equals("座位坏掉")){
            		   sate[i][j] = -1;
            	    }
            	   if(a.equals("可用")){
            		  sate[i][j] = 1;
            	  }
            	  if(a.equals("没有座位")){
            		 sate[i][j] = 0;
            	  }
            	  break;
		       }
			 }
		}
		
		
		initContent();
	}


	public int getWidth(){
		return this.frmWidth;
		
	}
	public int getHeight(){
		return this.frmHeight;
		
	}
	
	private void initHeader() {
		try {


			windowName.setBounds(frmWidth-160, 5, 160, 50);
			windowName.setFont(new java.awt.Font("dialog", 1, 20));
			windowName.setForeground(Color.blue);	
			headPan.add(windowName);
			setWindowName("在线选座");
			
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	public void setWindowName(String name){
		windowName.setText(name);
	}
	
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		this.dispose();
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
			top.setBounds(0, 0, 700, 50);
			bottom.setBounds(0,60,750,400);
			bottom.setLayout(new GridLayout(row, col));
			
			JLabel seat1 = new JLabel("已售");
			seat1.setBounds(150,10,30,30);
			top.add(seat1);
			JLabel seat1image = new JLabel(new ImageIcon("resource/image/售出.png"));
			seat1image.setBounds(200, 10, 50, 50);
			top.add(seat1image);
			JLabel seat2 = new JLabel("未售");
			seat2.setBounds(250,10,60,30);
			top.add(seat2);
			JLabel seat2image = new JLabel(new ImageIcon("resource/image/seat.png"));
			seat2image.setBounds(300, 10, 50, 50);
			top.add(seat2image);
			
	        view_site();
	        
	        setVisible(true);

	    }
	    void view_site() {
	    	
	    	JLabel jLabel = new JLabel();
	        JLabel jLabel1;
	        
			
	        for (int i = 1; i <=row; i++) {
	            for (int j = 1; j <= col; j++) {
	            	SeatSrv seatsrv=new SeatSrv();
                    try {
        				String sql = "select seat_id from Seat ";
        				sql+= " where seat_row=" + i+"and seat_column="+j;
        				DBUtil db = new DBUtil();
        				if(!db.openConnection()){
        					System.out.print("fail to connect database");
        					
        				}
        				ResultSet rst = db.execQuery(sql);
        				if (rst!=null) {
        					while(rst.next()){
        						seat_id=rst.getInt(1);
        					}
        				}
        				db.close(rst);
        				db.close();
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
                    TicketSrv ticSrv=new TicketSrv();
                    try {
        				String sql = "select ticket_id,ticket_status,ticket_price from Ticket ";
        				sql+= " where seat_id=" +seat_id;
        				DBUtil db = new DBUtil();
        				if(!db.openConnection()){
        					System.out.print("fail to connect database");
        				}
        				ResultSet rst = db.execQuery(sql);
        				if (rst!=null) {
        					while(rst.next()){
        						ticket_id=rst.getInt(1);
        						ticket_status=rst.getInt("ticket_status");
        						ticket_price=rst.getInt("ticket_price");
        					}
        				}
        				db.close(rst);
        				db.close();
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
                    
	                if (sate[i][j] == 1) {
	                    jLabel = new JLabel(new ImageIcon("resource/image/seat.png"));
	                    jLabel.setBounds(30+72 * (i-1), 60+ 30 * (j-1),60,30);
	                    jLabel.setText((i+","+j));
	                   
	                }
	                if(sate[i][j]==1 && ((ticket_status==9)||(ticket_status==1))){
	                	jLabel = new JLabel(new ImageIcon("resource/image/售出.png"));
	                    jLabel.setBounds(30+72 * (i-1), 60+ 30 * (j-1),60,30);
	                    jLabel.setText((i+","+j));
	                }
	                	
	                if (sate[i][j] == -1) {
	                    jLabel = new JLabel(new ImageIcon("resource/image/broken.png"));
	                    jLabel.setBounds(30+72*(i-1),60+30*(j-1),60,30);
	                    jLabel.setText((i+","+j));
	                  
	                }
	                if(sate[i][j] == 0){
	                	jLabel = new JLabel(new ImageIcon("resource/image/aisle.png"));
	                	jLabel.setBounds(30+72 * (i-1), 60+ 30 * (j-1),60,30);
	                	jLabel.setText((i+","+j));
					}
	                bottom.add(jLabel);
	                JLabel finalJLabel = jLabel;
	                
	               
	                
	                jLabel.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent mouseEvent) {
	                    	JLabel ttt = (JLabel)mouseEvent.getSource();
	                        String[] sourceStrArray = finalJLabel.getText().split(",");
	                        m = Integer.parseInt(sourceStrArray[0]);//[String]待转换的字符串
	                        n = Integer.parseInt(sourceStrArray[1]);//[String]待转换的字符串
	    
	                 		    SeatSrv seatSrv=new SeatSrv();
		                        try {
		            				String sql = "select seat_id from Seat ";
		            				sql+= " where seat_row=" + m+"and seat_column="+n;
		            				DBUtil db = new DBUtil();
		            				if(!db.openConnection()){
		            					System.out.print("fail to connect database");
		            					
		            				}
		            				ResultSet rst = db.execQuery(sql);
		            				if (rst!=null) {
		            					while(rst.next()){
		            						seat_id=rst.getInt(1);
		            					}
		            				}
		            				db.close(rst);
		            				db.close();
		            			} catch (Exception e) {
		            				e.printStackTrace();
		            			}
		                        TicketSrv ticSrv=new TicketSrv();
		                        try {
		            				String sql = "select ticket_id,ticket_status,ticket_price from Ticket ";
		            				sql+= " where seat_id=" +seat_id;
		            				DBUtil db = new DBUtil();
		            				if(!db.openConnection()){
		            					System.out.print("fail to connect database");
		            				}
		            				ResultSet rst = db.execQuery(sql);
		            				if (rst!=null) {
		            					while(rst.next()){
		            						ticket_id=rst.getInt(1);
		            						ticket_status=rst.getInt("ticket_status");
		            						ticket_price=rst.getInt("ticket_price");
		            					}
		            				}
		            				db.close(rst);
		            				db.close();
		            			} catch (Exception e) {
		            				e.printStackTrace();
		            			}
		                        
		                        if (sate[m][n]==1&&ticket_status==0){
		                        	 ttt.setIcon(new ImageIcon("resource/image/售出.png"));
		                        	 seat[0]=ticket_price;
		                        	 seat[l]=m;
		                        	 seat[l+1]=n;
		                        	 l=l+2;
		                        	 TicketSrv ticketSrvSrv=new TicketSrv();
		                        	 Ticket ticket=new Ticket();
				                        try {
				            				String sql = "update ticket set ticket_status="+1;  //锁定状态
				            				sql+= " where ticket_id=" +ticket_id;
				            				DBUtil db = new DBUtil();
				            				db.openConnection();
				            				int rtn =db.execCommand(sql);
				            				db.close();
				            			} catch (Exception e) {
				            				e.printStackTrace();
				            			}
		                        
				                     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				                     time[0]=new Date();// new Date()为获取当前系统时间
	            	    }
	                     
	                   }
	                });
        		}
	        }
	        pay.addActionListener(new ActionListener() {
	             @Override
	          public void actionPerformed(ActionEvent actionEvent) {
	            	 
	              time[1]=new Date();
	              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	              String t1=df.format(time[0]);
	              String t2=df.format(time[1]);
	              long ti1=time[0].getTime();
	              long ti2=time[1].getTime();
	              System.out.println(ti1);
	              System.out.println(ti2);
	              long kong=ti2-ti1;
	              long kong1=1*60*1000;
	              if(kong<kong1){
	                   new sale(seat);
	                   TicketSrv ticketSrvSrv=new TicketSrv();
              	       Ticket ticket=new Ticket();
                      try {
          				String sql = "update ticket set ticket_status="+9;  //售出状态
          				sql+= " where ticket_id=" +ticket_id;
          				DBUtil db = new DBUtil();
          				db.openConnection();
          				int rtn =db.execCommand(sql);
          				db.close();
          			} catch (Exception e) {
          				e.printStackTrace();
          			}
	              }
	              else{
	            	  int confirm = JOptionPane.showConfirmDialog(null, "支付失败", "确认", JOptionPane.YES_NO_OPTION);
	            	  try {
	          				String sql = "update ticket set ticket_status="+0;  //售出状态
	          				sql+= " where ticket_id=" +ticket_id;
	          				DBUtil db = new DBUtil();
	          				db.openConnection();
	          				int rtn =db.execCommand(sql);
	          				db.close();
	          			} catch (Exception e) {
	          				e.printStackTrace();
	          			}
	              
	             }
	             }
	     });
	    }
	    
	//To be override by the detailed business block interface 
	protected void btnExitClicked(ActionEvent Event){
		System.exit(0);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
}
class sale extends JFrame{
	private static final long serialVersionUID = 1L;
	private int frmWidth=300;
	private int frmHeight=350;
	protected final JPanel contPan = new JPanel();
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JLabel sumPrice=new JLabel();
	public JLabel windowName = new JLabel();
	int k=0,m;
	
	public sale(int[] seat){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("那一年剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		contPan.setBounds(0, 0, frmWidth, frmHeight);
		contPan.setLayout(null);
		this.add(contPan);
		for(int i=0;;i++){
			if(seat[i]==0)  break;
			else k++;
		}
		m=k/2;
		initContent(seat,k,m);
	}
	protected void onWindowClosing(){
		this.dispose();
	}
	protected void initContent(int[] seat,int k,int m){
		JLabel s1=new JLabel("购物小票");
		s1.setBounds(120, 10, 100, 20);
		contPan.add(s1);
		JLabel s2=new JLabel("你的座位：");
		s2.setBounds(50, 50, 100, 20);
		contPan.add(s2);
		JLabel z1=new JLabel(seat[1]+"行"+seat[2]+"列");
		z1.setBounds(80, 70, 100, 20);
		JLabel z2=new JLabel(seat[3]+"行"+seat[4]+"列");
		z2.setBounds(80, 90, 100, 20);
		JLabel z3=new JLabel(seat[5]+"行"+seat[6]+"列");
		z3.setBounds(80, 110, 100, 20);
		JLabel z4=new JLabel(seat[7]+"行"+seat[8]+"列");
		z4.setBounds(80, 130, 100, 20);
		if(m==1){
			contPan.add(z1);
		}
		if(m==2){
			contPan.add(z1);
			contPan.add(z2);
		}
		if(m==3){
			contPan.add(z1);
			contPan.add(z2);
			contPan.add(z3);
		}
		if(m==4){
			contPan.add(z1);
			contPan.add(z2);
			contPan.add(z3);
			contPan.add(z4);
		}
		JLabel s3=new JLabel("支付金额：");
		s3.setBounds(50, 200, 100, 20);
		contPan.add(s3);
		JLabel price=new JLabel(m*seat[0]+"元");
		price.setBounds(80, 220, 100, 20);
		contPan.add(price);
		JLabel s4=new JLabel("请在规定时间内入场观影，谢谢！");
		s4.setBounds(60, 270, 200, 20);
		contPan.add(s4);
		setVisible(true);
	}
}