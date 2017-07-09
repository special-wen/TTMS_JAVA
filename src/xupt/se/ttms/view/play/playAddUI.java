package xupt.se.ttms.view.play;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import xupt.se.ttms.model.DataDict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.tmpl.*;

public class playAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName,lblType,lblLang,lblIntro, lblImage, lblLen,lblTprice,lblStatus;
	//				名称		类型           语言		简介			图片		时长		票价		状态
	protected JTextField txtName, txtLen, txtTprice,txtStatus;
	//						名称		时长		票价		状态	        
	protected JTextArea txtIntro;//简介
	protected JComboBox txtType,txtLang;//类型		语言
	protected JButton button;
	protected Image image;
	protected String pic=null;//剧目海报的路径
	protected void initContent(){
		this.setTitle("添加剧目");
		Font font1 =new Font("宋体",Font.BOLD,16);
		
		lblName = new JLabel("剧目名称:");
		lblName.setBounds(60, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(150, 30, 200, 30);
		contPan.add(txtName);
		lblName.setFont(font1);
		txtName.setFont(font1);
		
		lblType = new JLabel("剧目类型:");
		lblType.setBounds(60, 80, 80, 30);
		contPan.add(lblType);
		txtType=new JComboBox();
		DataDictSrv ddsrv=new DataDictSrv();
		List<DataDict> dListplaytype=null;//类型的孩子
		dListplaytype=new LinkedList<DataDict>();
		dListplaytype=ddsrv.Fetch("dict_parent_id=2");
		int i=0;
		Iterator<DataDict> itr=dListplaytype.iterator();
		while(itr.hasNext()){
			DataDict dd=itr.next();
			txtType.addItem(dd.getValue());
		}
		txtType.setVisible(true);
		txtType.setBounds(150, 80, 200, 30);
		contPan.add(txtType);
		lblType.setFont(font1);
		txtType.setFont(font1);
		
		lblImage = new JLabel("剧目海报:");
		lblImage.setBounds(450, 30, 80, 30);
		lblImage.setFont(font1);
		contPan.add(lblImage);
		button = new JButton("浏览");
		button.setBounds(570,30,70,30);
		button.setVisible(true);
		contPan.add(button);
		button.addActionListener(this);
		
		lblLen = new JLabel("演出时长:");
		lblLen.setBounds(60, 130, 80, 30);
		contPan.add(lblLen);
		txtLen = new JTextField();
		txtLen.setBounds(150, 130, 80, 30);
		contPan.add(txtLen);
		lblLen.setFont(font1);
		txtLen.setFont(font1);
  
		lblTprice = new JLabel("票价:");
		lblTprice.setBounds(250, 130, 50, 30);
		contPan.add(lblTprice);
		txtTprice = new JTextField();
		txtTprice.setBounds(310, 130, 80, 30);
		contPan.add(txtTprice);
		lblTprice.setFont(font1);
		txtTprice.setFont(font1);

		lblStatus = new JLabel("剧目状态:");
		lblStatus.setBounds(60, 180, 80, 30);
		contPan.add(lblStatus);
		txtStatus = new JTextField();
		txtStatus.setBounds(150, 180, 200, 30);
		contPan.add(txtStatus);
		lblStatus.setFont(font1);
		txtStatus.setFont(font1);
		
		lblLang = new JLabel("剧目语言:");
		lblLang.setBounds(60, 230, 80, 30);
		contPan.add(lblLang);
		txtLang=new JComboBox();
		List<DataDict> dListplaylang=null;
		dListplaylang=new LinkedList<DataDict>();
		dListplaylang=ddsrv.Fetch("dict_parent_id=3");
		i=0;
		Iterator<DataDict> itr1=dListplaylang.iterator();
		while(itr1.hasNext()){
			DataDict dd=itr1.next();
			txtLang.addItem(dd.getValue());
		}
		txtLang.setVisible(true);
		txtLang.setBounds(150, 230, 200, 30);
		contPan.add(txtLang);
		lblLang.setFont(font1);
		txtLang.setFont(font1);
		
		lblIntro = new JLabel("剧目简介:");
		lblIntro.setBounds(60, 280, 80, 30);
		contPan.add(lblIntro);
		txtIntro = new JTextArea();
		txtIntro.setLineWrap(true);
		txtIntro.setWrapStyleWord(true);
		txtIntro.setBounds(150, 280, 580, 150);
		contPan.add(txtIntro);
		lblIntro.setFont(font1);
		txtIntro.setFont(font1);
		
		
		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(60, 460, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 460, 60, 30);
		contPan.add(btnCancel);
	}
	public boolean getReturnStatus(){
		   return rst;
	}

	public void paint(Graphics g) {
        Image buff = createImage(150, 200);//设定图片大小
        Graphics gg = buff.getGraphics();
        gg.drawImage(image, 0,0,null);
        gg.dispose();
        super.paint(g);
        g.drawImage(buff,500,150,null);
    }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button){
			//返回此抽象路径名的绝对形式
			File defaultPath = new File("").getAbsoluteFile();
			//创建选择文件对象
            JFileChooser chooser = new JFileChooser(defaultPath);
            
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          
            //设置可选择文件类型
            chooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "gif", "bmp", "ico"));
          //打开选择文件对话框
            int showOpenDialog = chooser.showOpenDialog(this);
            if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            	//file为用户选择的图片文件
            	File file = chooser.getSelectedFile();
                pic = file.getPath();
                System.out.println(pic);
                try {
                    image = ImageIO.read(file);
                    repaint();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        }
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	protected void txttypeShowClicked(){
		
	}
	protected void btnSaveClicked(){
	
		if (txtName.getText() != null && txtIntro.getText() != null
				&& txtLen.getText() != null && txtTprice.getText()!=null
				&& txtStatus.getText()!=null && txtType.getSelectedItem().toString()!=null
				&& txtType.getSelectedItem().toString()!=null 
				&& txtLang.getSelectedItem().toString()!=null 
				&& pic!=null) {/////
			
			String playtype=txtType.getSelectedItem().toString();//获取下拉列表所选值
			DataDictSrv ddsrv = new DataDictSrv();
			List<DataDict> dList=ddsrv.Fetch("dict_value='"+playtype+"'");
			Iterator<DataDict> itr=dList.iterator();
			DataDict dd=new DataDict();
			while(itr.hasNext()){
				dd=itr.next();
			}//获取到下拉列表所选值在数字字典中的id
			PlaySrv plasrv=new PlaySrv();
			Play pla=new Play();
			pla.setPlay_type_id(dd.getId());
			
			String playlang=txtLang.getSelectedItem().toString();//获取下拉列表所选值
			ddsrv = new DataDictSrv();
			dList=ddsrv.Fetch("dict_value='"+playlang+"'");
			itr=dList.iterator();
			dd=new DataDict();
			while(itr.hasNext()){
				dd=itr.next();
			}//获取到下拉列表所选值在数字字典中的id
			pla.setPlay_lang_id(dd.getId());
			
			pla.setName(txtName.getText());
			pla.setIntroduction(txtIntro.getText());
			
			pla.setLength(Integer.parseInt(txtLen.getText()));
			pla.setTicket_price(Integer.parseInt(txtTprice.getText()));
			pla.setStatus(Integer.parseInt(txtStatus.getText()));
			plasrv.add(pla,pic);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
