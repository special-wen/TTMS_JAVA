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
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.studio.StudioAddUI;
import xupt.se.ttms.view.studio.StudioMgrUI;

public class SeatMgrUI extends MainUITmpl{
	private JLabel lblname,lbael1;
	private JLabel txtname;
	private int r,c;
	private JPanel message,seat;
	private static  int [][]array = new int [100][100];
	/*private JPanel jPanel;*/
	public SeatMgrUI(Studio studio){
		//获取到演出厅的信息
		int id = studio.getID();
		String name = studio.getName();
		r = studio.getRowCount();
		c = studio.getColCount();
		
		//生成演出厅信息，面板
		message = new JPanel();
		lblname = new JLabel("演出厅名称");
		lblname.setBounds(400, 30, 100, 30);
		message.add(lblname);
		txtname = new JLabel(name);
		txtname.setBounds(500,30,50,30);
		message.add(txtname);
		contPan.add(message);
		message.setBounds(0, 0, 1026, 30);
		
		//创建座位面板
		seat = new JPanel();
		contPan.add(seat);
		seat.setBounds(0, 30, 1026, 520);
		seat.setLayout(new GridLayout(r, c));
		//初始化座位
		int [][]array = new int [r+1][c+1];
			for(int i = 0;i<r;i++){
				for(int j = 0;j<c;j++){
		            array[i][j] = 0;
		        }
		    }
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
					seat.add(lbael1);
					//seat.setLayout(new GridLayout(r, c));
					//添加座位监听
					JLabel finalJLabel = lbael1;
					lbael1.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent mouseEvent) {
//	                    	JLabel ttt = (JLabel)mouseEvent.getSource();
//	                    	ttt.setIcon(new ImageIcon("resource/image/broken.png"));
//	                        String[] sourceStrArray = finalJLabel.getText().split(",");
//	                        int m = Integer.parseInt(sourceStrArray[0]);//[String]待转换的字符串
//	                        int n = Integer.parseInt(sourceStrArray[1]);//[String]待转换的字符串
//	                        if (array[m][n] == 0){
//	                            int i = JOptionPane.showConfirmDialog(null, "购票", "是否选择该座位", JOptionPane.YES_NO_OPTION);
//	                            if(i == 0){
//	                                array[m][n] = 1;
//	                            }
//	                            seat.removeAll();
//	                            view_site();
//	                            seat.repaint();
//	                            seat.setLayout(new GridLayout(r, c));
//	                        }
//	                        else{
//	                            int i = JOptionPane.showConfirmDialog(null, "退票", "是否退票", JOptionPane.YES_NO_OPTION);
//	                            if(i == 0){
//	                                array[m][n] = 0;
//	                            }
//	                            seat.removeAll();
//	                            view_site();
//	                            seat.repaint();
//	                            seat.setLayout(new GridLayout(r, c));
//	                        }
	                    }
	                });
				}
		}
		
	}
}

		


