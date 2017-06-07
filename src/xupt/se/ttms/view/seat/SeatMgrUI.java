package xupt.se.ttms.view.seat;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.lang.model.type.TypeKind;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.fabric.xmlrpc.base.Array;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.studio.StudioAddUI;
import xupt.se.ttms.view.studio.StudioMgrUI;

public class SeatMgrUI extends MainUITmpl{
	protected static final String String = null;
	private JLabel lblname,lbael1,lblsate,lblbroken,lblaisle;
	private JLabel txtname,imgsate,imgbroken,imgaisle;
	private int r,c,id;
	private JPanel message,seat;
	private static  int [][]array ;
	//array[][] = -1  座位坏掉
	//array[][] = 0   座位正常
	//array[][] = 1   座位去掉改为过道
	/*private JPanel jPanel;*/
	
	public SeatMgrUI(Studio studio){
		//获取到演出厅的信息
		id = studio.getID();
		String name = studio.getName();
		r = studio.getRowCount();
		c = studio.getColCount();
		
		//生成演出厅信息，面板
		message = new JPanel();
		message.setLayout(null);
		//演出厅信息
		lblname = new JLabel("演出厅名称");
		lblname.setBounds(400, 10, 100, 30);
		message.add(lblname);
		txtname = new JLabel(name);
		txtname.setBounds(500,10,50,30);
		message.add(txtname);
		lblsate = new JLabel("座位正常:");
		lblsate.setBounds(695,10,50,30);
		message.add(lblsate);
		imgsate = new JLabel(new ImageIcon("resource/image/seat.png"));
		imgsate.setBounds(745, 0, 50, 50);
		message.add(imgsate);
		lblbroken = new JLabel("座位坏掉:");
		lblbroken.setBounds(795,10,50,30);
		message.add(lblbroken);
		imgbroken = new JLabel(new ImageIcon("resource/image/broken.png"));
		imgbroken.setBounds(845, 0, 50, 50);
		message.add(imgbroken);
		lblaisle = new JLabel("过道:");
		lblaisle.setBounds(900, 10, 30, 30);
		message.add(lblaisle);
		imgaisle = new JLabel(new ImageIcon("resource/image/aisle.png"));
		imgaisle.setBounds(930, 0, 50, 50);
		message.add(imgaisle);
		
		contPan.add(message);
		message.setSize(1026,50);
		message.setBounds(0, 0, 1026, 50);
		
		//创建座位面板
		seat = new JPanel();
		contPan.add(seat);
		seat.setBounds(0, 50, 1026, 520);
		seat.setLayout(new GridLayout(r, c));
		
		//调用数据表seat中的座位信息生成座位表
		
		array = new int [r+1][c+1];
		for(int i =1;i<=r;i++){
			for(int j = 1;j<=c;j++){
				Seat seat = new Seat();
				seat.setStudio_id(id);
				seat.setRow(i);
				seat.setColumn(j);
				SeatDAO seatdao = new SeatDAO();
            	List<Seat> pList = null;
        		pList = new LinkedList<Seat>();
        		//System.out.println(seat.getColumn());
        		pList = seatdao.select(seat);
            	Iterator<Seat> itr = pList.iterator();
            	while (itr.hasNext()){
        			Seat stu = itr.next();
        			String a = stu.getState();
        			System.out.println(i+","+j);
        			if(a.equals("座位坏掉")){
                		array[i][j] = 1;
                		System.out.println(array[i][j]);
                	}
                	if(a.equals("座位正常")){
                		array[i][j] = 0;
                		System.out.println(array[i][j]);
                	}
                	if(a.equals("没有座位")){
                		array[i][j] = -1;
                		System.out.println(array[i][j]);
                	}
        		}
            	
			}
		}
//		int [][]array = new int [r+1][c+1];
//			for(int i = 0;i<r;i++){
//				for(int j = 0;j<c;j++){
//		            array[i][j] = 0;
//		        }
//		    }
		view_site();
	    setVisible(true);
	}
	//创建座位
		void view_site(){
		for(int i = 1;i<= r;i++){
			for(int j = 1;j<= c;j++){
					if(array[i][j] == 0){
						lbael1 = new JLabel(new ImageIcon("resource/image/seat.png"));
			            lbael1.setBounds(40+60 * (i-1), 60+ 30 * (j-1),60,30);
			            lbael1.setText((i+","+j));
					}
					if(array[i][j] == 1){
						lbael1 = new JLabel(new ImageIcon("resource/image/broken.png"));
			            lbael1.setBounds(40+60 * (i-1), 60+ 30 * (j-1),60,30);
			            lbael1.setText((i+","+j));
					}
					if(array[i][j] == -1){
						lbael1 = new JLabel(new ImageIcon("resource/image/aisle.png"));
						lbael1.setBounds(40+60 * (i-1), 60+ 30 * (j-1),60,30);
						lbael1.setText((i+","+j));
					}
					seat.add(lbael1);
					//seat.setLayout(new GridLayout(r, c));
					//添加座位监听
					JLabel finalJLabel = lbael1;
					lbael1.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent mouseEvent) {
	                    	JLabel ttt = (JLabel)mouseEvent.getSource();
	                    	/*System.out.println(ttt);*/
	                    	String[] sourceStrArray = finalJLabel.getText().split(",");
	                        int m = Integer.parseInt(sourceStrArray[0]);//[String]待转换的字符串
	                        int n = Integer.parseInt(sourceStrArray[1]);//[String]待转换的字符串
	                        //更改座位状态
                        	Object[] options = { "座位坏掉", "过道","座位正常" }; 
	                        int a = JOptionPane.showOptionDialog(null, "选择要更改的状态", "Warning", 
	                        			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
	                        			null, options, options[0]); 
	                        //System.out.println(a);
	                        if(a == 0){
	                        	ttt.setIcon(new ImageIcon("resource/image/broken.png"));
	                        	//
	                        	//studio_id r m,state
	                        	Seat seat = new Seat();
	                        	
	                        	seat.setStudio_id(id);
	                        	seat.setRow(m);
	                        	seat.setColumn(n);
	                        	seat.setState("座位坏掉");
	                        	SeatDAO seatdao = new SeatDAO();
	                        	seatdao.update(seat);
	                        	array[m][n] = -1;
	                        }
	                        if(a == 1){
	                        	ttt.setIcon(new ImageIcon("resource/image/aisle.png"));
	                        	Seat seat = new Seat();
	                        	seat.setStudio_id(id);
	                        	seat.setRow(m);
	                        	seat.setColumn(n);
	                        	seat.setState("没有座位");
	                        	SeatDAO seatdao = new SeatDAO();
	                        	seatdao.update(seat);
	                        	array[m][n] = 1;
	                        }
	                        if(a == 2){
	                        	ttt.setIcon(new ImageIcon("resource/image/seat.png"));
	                        	Seat seat = new Seat();
	                        	seat.setStudio_id(id);
	                        	seat.setRow(m);
	                        	seat.setColumn(n);
	                        	seat.setState("座位正常");
	                        	SeatDAO seatdao = new SeatDAO();
	                        	seatdao.update(seat);
	                        	array[m][n] = 0;
	                        }

	                    }
	                });
				}
		}
		
	}
}

		


