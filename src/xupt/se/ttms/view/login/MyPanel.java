package xupt.se.ttms.view.login;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * 重写jpanel，设置它的属性，添加图片;
 * 
 * @author 努尔比亚
 * 
 */
public class MyPanel extends JPanel {
	private Image im;

	// 构造函数制定Jpanel的大小
	public MyPanel(Image im,int w,int h) {
		this.im = im;
		this.setSize(w, h);
	}

	// 画出背景
	@Override
	protected void paintComponent(Graphics g) {
		// 清屏
		super.paintComponent(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}