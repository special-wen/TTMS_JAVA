package xupt.se.ttms.view.seat;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
	
	
	public SeatMgrUI(Studio studio){
		//获取到演出厅的信息
		int id = studio.getID();
		String name = studio.getName();
		int r = studio.getRowCount();
		int c = studio.getColCount();
		
		//生成演出厅信息
		lblname = new JLabel("演出厅名称");
		lblname.setBounds(400, 30, 100, 30);
		contPan.add(lblname);
		txtname = new JLabel(name);
		txtname.setBounds(500,30,50,30);
		contPan.add(txtname);
		
		//生成座位
		
		//初始化座位
		int [][]array = new int [r][c];
			for(int i = 0;i<r;i++){
	        for(int j = 0;j<c;j++){
		            array[i][j] = 0;
		        }
		    }
				
		//创建座位
		for(int i = 1;i<= r;i++){
			for(int j = 1;j<= c;j++){
					if(array[i][j] == 0){
						lbael1 = new JLabel(new ImageIcon("/home/zxw/座位.png"));
			            lbael1.setBounds(40+60 * (i-1), 60+ 30 * (j-1),60,30);
			            lbael1.setText((i+","+j));
					}
					contPan.add(lbael1);
				}
		}
	}
}

		


