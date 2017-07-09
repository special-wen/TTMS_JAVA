package xupt.se.ttms.view.moviechart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import xupt.se.ttms.model.Moviechart;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.MoviechartSrv;
import xupt.se.ttms.view.tmpl.*;

/**
 * author:liufan
 */
class BarChart {
	private ChartPanel barchartpanel;

	public BarChart(Moviechart m) {
		CategoryDataset dataset = getDataSet(m);
		JFreeChart chart = ChartFactory.createBarChart3D("", /* "电影排行前十名", */// 图表标题
				"电影名称", // 目录轴的显示标签
				"票房（元）", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		barchartpanel = new ChartPanel(chart, true);
		// //这里也可以用chartFrame,可以直接生成一个独立的Frame
	}

	private static CategoryDataset getDataSet(Moviechart m) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//最多只显示排行榜的前六
		if (m.getLength() >= 5) {
			dataset.addValue(m.getPlay_chart_price(1), m.getPlay_chart_name(1), m.getPlay_chart_name(1));
			dataset.addValue(m.getPlay_chart_price(2), m.getPlay_chart_name(2), m.getPlay_chart_name(2));
			dataset.addValue(m.getPlay_chart_price(3), m.getPlay_chart_name(3), m.getPlay_chart_name(3));
			dataset.addValue(m.getPlay_chart_price(4), m.getPlay_chart_name(4), m.getPlay_chart_name(4));
			dataset.addValue(m.getPlay_chart_price(5), m.getPlay_chart_name(5), m.getPlay_chart_name(5));
			} 
		else {
			for (int i = 1; i <= m.getLength(); i++) {
				dataset.addValue(m.getPlay_chart_price(i), m.getPlay_chart_name(i), m.getPlay_chart_name(i));
			}
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return barchartpanel;

	}
}

class MoviechartTable {
	private JTable jt;

	public MoviechartTable(JScrollPane jp) {
		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};// 不允许表格被编辑
			// 初始化列名
		tabModel.addColumn("排名");
		tabModel.addColumn("电影ID");
		tabModel.addColumn("电影名称");
		tabModel.addColumn("票房");
		jt = new JTable(tabModel);// 创建表格
		TableColumnModel columnModel = jt.getColumnModel();// 设置各列的宽度

		TableColumn column = columnModel.getColumn(0);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(1);
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		column = columnModel.getColumn(2);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(3);
		column.setPreferredWidth(10);

		jp.add(jt);
		jp.setViewportView(jt);

	}

	// 将m中的数据排序
	public void Sort(Moviechart m) {
		int t;
		String tn;
		for (int i = 1; i < m.getLength() - 1; i++) {
			for (int j = i + 1; j < m.getLength(); j++) {
				if (m.getPlay_chart_price(i) < m.getPlay_chart_price(j)) {
					t = m.getPlay_chart_id(i);
					m.setPlay_chart_id(i, m.getPlay_chart_id(j));
					m.setPlay_chart_id(j, t);
					t = m.getPlay_chart_price(i);
					m.setPlay_chart_price(i, m.getPlay_chart_price(j));
					m.setPlay_chart_price(j, t);
					tn = m.getPlay_chart_name(i);
					m.setPlay_chart_name(i, m.getPlay_chart_name(j));
					m.setPlay_chart_name(j, tn);
				}
			}
		}

	}

	// 创建表格
	public void showMoviechartList(Moviechart m) {
		Sort(m);
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			for (int i = 1; i <= m.getLength(); i++) {
				Object data[] = new Object[4];
				data[0] = Integer.toString(i);
				data[1] = Integer.toString(m.getPlay_chart_id(i));
				data[2] = m.getPlay_chart_name(i);
				data[3] = Integer.toString(m.getPlay_chart_price(i));
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class MoviechartMgrUI extends MainUITmpl {
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	private JScrollPane jp1;// 用来放表格的滚动控件
	MoviechartTable tms; // 显示电影排行列表
	BarChart moviebarchart;// 电影柱状图

	public MoviechartMgrUI(Moviechart m) {
		Rectangle rect1 = new Rectangle();
		rect1.setBounds(0, 81, 1024, 420);// 区域的位置和大小
		Rectangle rect2 = new Rectangle();
		rect2.setBounds(0, 400, 1024, contPan.getHeight() - 420);// 区域的位置和大小
		ca1 = new JLabel("电影票房排行榜", JLabel.CENTER);
		ca1.setBounds(0, 5, rect1.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);
		jp1 = new JScrollPane();
		jp1.setBounds(0, 40, rect1.width, rect1.height - 90);
		contPan.add(jp1);
		tms = new MoviechartTable(jp1);
		showTable(m);
		moviebarchart = new BarChart(m);
		moviebarchart.getChartPanel().setBounds(rect2);
		contPan.add(moviebarchart.getChartPanel());

	}
	public void showCurrentUser(){
		LoginedUser curUser=LoginedUser.getInstance();
		String name=curUser.getEmpName();
		if(null==name ||  name.isEmpty())
			usrName.setText("经理");
		else
			usrName.setText(name);		
	}
	private void showTable(Moviechart m) {
		tms.showMoviechartList(m);
	}

	public static void main(String[] args) {
		Moviechart m = new Moviechart();
		m = new MoviechartSrv().count(m);
		MoviechartMgrUI frmMcMgr = new MoviechartMgrUI(m);
		frmMcMgr.setVisible(true);

	}
}