package xupt.se.ttms.view.studio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.*;
import xupt.se.ttms.service.*;
import xupt.se.ttms.dao.*;
import xupt.se.ttms.view.tmpl.*;

public class StudioSelectUI extends PopUITmpl implements ActionListener{
	private JButton btnCancel;
	
	protected boolean rst = false;
	private JLabel lblname,lblrow,lblcolumn,lbldesciption;
	protected JLabel txtname,txtrow,txtcolumn,txtdescription;
	
	Object data[] = new Object[4];
	protected void Select(String s){
		System.out.println(s);
		StudioDAO studiodao = new StudioDAO();
		List<Studio> pList = null;
		pList = new LinkedList<Studio>();
		pList = studiodao.select(s);
		this.setTitle("演出厅信息");
		Iterator<Studio> itr = pList.iterator();
		while (itr.hasNext()){
			Studio stu = itr.next();
			data[0] = stu.getName();
			data[1] = stu.getRowCount();
			data[2] = stu.getColCount();
			data[3] = stu.getIntroduction();
			System.out.println(data[0].toString()+","+data[1].toString()+","+data[2].toString()+","+data[3].toString());
		}
		lblname = new JLabel("演出厅名称:");
		lblname.setBounds(60, 30,80, 30);
		contPan.add(lblname);
		txtname = new JLabel(data[0].toString());
		txtname.setBounds(150,30,200,30);
		contPan.add(txtname);
		
		lblrow = new JLabel("行:");
		lblrow.setBounds(60, 80, 80, 30);
		contPan.add(lblrow);
		txtrow = new JLabel(data[1].toString());
		txtrow.setBounds(150, 80, 100, 30);
		contPan.add(txtrow);
		
		lblcolumn = new JLabel("列:");
		lblcolumn.setBounds(60,120,80,30);
		contPan.add(lblcolumn);
		txtcolumn = new JLabel(data[2].toString());
		txtcolumn.setBounds(150,120,200,30);
		contPan.add(txtcolumn);
		
		lbldesciption = new JLabel("简介:");
		lbldesciption.setBounds(60, 170, 80, 30);
		contPan.add(lbldesciption);
		txtdescription = new JLabel(data[3].toString());
		txtdescription.setBounds(150,170,200,50);
		contPan.add(txtdescription);
		
		btnCancel = new JButton("关闭");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180,370,60,30);
		contPan.add(btnCancel);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
