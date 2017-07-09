package xupt.se.ttms.view.play;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.DataDict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.dao.*;
import xupt.se.ttms.view.tmpl.*;

public class playSelectUI extends PopUITmpl implements ActionListener {
	private JButton btnCancel; 	//关闭按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName,lblType,lblLang, lblIntro, lblImage, lblLen,lblTprice,lblStatus;
	//						类型		语言类型				海报		
	protected JLabel txtName,txtType,txtLang, txtIntro,txtImage,txtLen, txtTprice,txtStatus;
	Object data[] = new Object[9];
	protected void Select(String s){
		PlayDAO playdao=new PlayDAO();
		List<Play> pList=null;
		pList=new LinkedList<Play>();
		pList=playdao.select("play_name='"+s+"'");
		this.setTitle("剧目信息");
		Iterator<Play> itr = pList.iterator();
		while (itr.hasNext()) {
			Play pla = itr.next();
			
			data[0] = Integer.toString(pla.getID());
			data[1] = pla.getName();
			
			DataDictSrv ddsrv=new DataDictSrv();
			DataDict dd=new DataDict();
			List<DataDict> dList=new LinkedList<DataDict>();
			dList=ddsrv.Fetch("dict_id="+pla.getPlay_type_id());
			Iterator<DataDict> itr1=dList.iterator();
			while(itr1.hasNext()){
				dd=itr1.next();
			}
			data[2] =dd.getValue();
			
			ddsrv=new DataDictSrv();
			dd=new DataDict();
			dList=new LinkedList<DataDict>();
			dList=ddsrv.Fetch("dict_id="+pla.getPlay_lang_id());
			itr1=dList.iterator();
			while(itr1.hasNext()){
				dd=itr1.next();
			}
			data[3] =dd.getValue();
			
			data[4] = pla.getIntroduction();
			data[5] = Integer.toString(pla.getLength());
			data[6]=Integer.toString(pla.getTicket_price());
			data[7]=Integer.toString(pla.getStatus());
			data[8] = pla.getImage();
		}
		Font font1 =new Font("宋体",Font.BOLD,16);
		
		lblName = new JLabel("剧目名称:");
		lblName.setBounds(60, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JLabel(data[1].toString());
		txtName.setBounds(150, 30, 200, 30);
//		txtName.setEditable(true);
		lblName.setFont(font1);
		txtName.setFont(font1);
		txtName.setBorder(BorderFactory.createEtchedBorder());
		contPan.add(txtName);
		
		lblImage = new JLabel("剧目海报:");
		lblImage.setBounds(400, 30, 80, 30);
		contPan.add(lblImage);
		Icon icon1 = new ImageIcon("Cache/Play_Image/"+data[0]+"_"+data[1]+".jpg");
		txtImage = new JLabel(icon1);
		txtImage.setBounds(500, 30, 200,300);
		lblImage.setFont(font1);
		contPan.add(txtImage);
		
		lblType = new JLabel("剧目类型:");
		lblType.setBounds(60,80,80,30);
		contPan.add(lblType);
		txtType = new JLabel(data[2].toString());
		txtType.setBounds(150,80,200,30);
		lblType.setFont(font1);
		txtType.setFont(font1);
		txtType.setBorder(BorderFactory.createEtchedBorder());
		contPan.add(txtType);

		lblLang = new JLabel("语言类型:");
		lblLang.setBounds(60,130,80,30);
		contPan.add(lblLang);
		txtLang = new JLabel(data[3].toString());
		txtLang.setBounds(150,130,200,30);
		lblLang.setFont(font1);
		txtLang.setFont(font1);
		contPan.add(txtLang);
		txtLang.setBorder(BorderFactory.createEtchedBorder());
		
		lblLen = new JLabel("演出时长:");
		lblLen.setBounds(60, 180, 80, 30);
		contPan.add(lblLen);
		txtLen = new JLabel(data[5].toString());
		txtLen.setBounds(150, 180, 200, 30);
		contPan.add(txtLen);
		lblLen.setFont(font1);
		txtLen.setFont(font1);
		txtLen.setBorder(BorderFactory.createEtchedBorder());
		
		lblTprice = new JLabel("票价:");
		lblTprice.setBounds(60, 230, 80, 30);
		contPan.add(lblTprice);
		txtTprice = new JLabel(data[6].toString());
		txtTprice.setBounds(150, 230, 200, 30);
		contPan.add(txtTprice);
		lblTprice.setFont(font1);
		txtTprice.setFont(font1);
		txtTprice.setBorder(BorderFactory.createEtchedBorder());
		
		lblStatus = new JLabel("剧目状态:");
		lblStatus.setBounds(60, 280, 80, 30);
		contPan.add(lblStatus);
		txtStatus = new JLabel(data[7].toString());
		txtStatus.setBounds(150, 280, 200, 30);
		contPan.add(txtStatus);
		lblStatus.setFont(font1);
		txtStatus.setFont(font1);
		txtStatus.setBorder(BorderFactory.createEtchedBorder());
		
		lblIntro = new JLabel("剧目简介:");
		lblIntro.setBounds(60, 330, 80, 30);
		contPan.add(lblIntro);
		txtIntro=new JLabel(data[4].toString());
		txtIntro.setBounds(150, 330, 580, 150);
//		contPan.add(jscrollPane);
		lblIntro.setFont(font1);
		txtIntro.setFont(font1);
		contPan.add(txtIntro);
		txtIntro.setBorder(BorderFactory.createEtchedBorder());
		
//		btnCancel = new JButton("关闭");
//		btnCancel.addActionListener(this);
//		btnCancel.setBounds(550, 450, 60, 30);
//		contPan.add(btnCancel);
	}
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} 
	}
	

	


}
