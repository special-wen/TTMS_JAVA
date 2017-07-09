package xupt.se.ttms.view.employeechart;

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

import xupt.se.ttms.model.Employeechart;
import xupt.se.ttms.service.EmployeechartSrv;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.tmpl.MainUITmpl;

/**
 * author:liufan
 */
class BarChart {
	private ChartPanel barchartpanel;

	public BarChart(Employeechart e) {
		CategoryDataset dataset = getDataSet(e);
		JFreeChart chart = ChartFactory.createBarChart3D("", // 图表标题
				"员工ID", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
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

	private static CategoryDataset getDataSet(Employeechart e) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//最多只显示排行榜的前六
		if (e.getlength() >= 5) {
			dataset.addValue(e.getemp_chart_sale(1), String.valueOf(e.getemp_chart_id(1)), String.valueOf(e.getemp_chart_id(1)));
			dataset.addValue(e.getemp_chart_sale(2), String.valueOf(e.getemp_chart_id(2)), String.valueOf(e.getemp_chart_id(2)));
			dataset.addValue(e.getemp_chart_sale(3), String.valueOf(e.getemp_chart_id(3)), String.valueOf(e.getemp_chart_id(3)));
			dataset.addValue(e.getemp_chart_sale(4), String.valueOf(e.getemp_chart_id(4)), String.valueOf(e.getemp_chart_id(4)));
			dataset.addValue(e.getemp_chart_sale(5), String.valueOf(e.getemp_chart_id(5)), String.valueOf(e.getemp_chart_id(5)));
			
		} 
		else {
			for (int i = 1; i <= e.getlength(); i++) {
				dataset.addValue(e.getemp_chart_sale(i), String.valueOf(e.getemp_chart_id(i)), String.valueOf(e.getemp_chart_id(i)));
			}
			}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return barchartpanel;

	}
}
class EmployeechartTable {
	private JTable jt;

	public EmployeechartTable(JScrollPane jp) {
		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};// 不允许表格被编辑
			// 初始化列名
		tabModel.addColumn("排名");
		tabModel.addColumn("员工ID");
		tabModel.addColumn("员工工号");
		tabModel.addColumn("员工姓名");
		tabModel.addColumn("销售额");
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
	public void Sort(Employeechart e) {
		int t;
		String tn;
		for (int i = 1; i < e.getlength() - 1; i++) {
			for (int j = i + 1; j < e.getlength(); j++) {
				if (e.getemp_chart_sale(i) < e.getemp_chart_sale(j)) {
					t = e.getemp_chart_id(i);
					e.setemp_chart_id(i, e.getemp_chart_id(j));
					e.setemp_chart_id(j, t);
					t = e.getemp_chart_sale(i);
					e.setemp_chart_sale(i, e.getemp_chart_sale(j));
					e.setemp_chart_sale(j, t);
					tn = e.getemp_chart_name(i);
					e.setemp_chart_name(i, e.getemp_chart_name(j));
					e.setemp_chart_name(j, tn);
					tn = e.getemp_chart_no(i);
					e.setemp_chart_no(i, e.getemp_chart_no(j));
					e.setemp_chart_no(j, tn);
				}
			}
		}

	}

	// 创建表格
	public void showMoviechartList(Employeechart e) {
		Sort(e);
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			for (int i = 1; i <= e.getlength(); i++) {
				Object data[] = new Object[5];
				data[0] = Integer.toString(i);
				data[1] = e.getemp_chart_id(i);
				data[2] = e.getemp_chart_no(i);
				data[3] = e.getemp_chart_name(i);
				data[4] = Integer.toString(e.getemp_chart_sale(i));
				tabModel.addRow(data);
				// System.out.println(data[0]+","+data[1]);
			}
			jt.invalidate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}


public class EmployeechartMgrUI extends MainUITmpl{

	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	private JScrollPane jp1;// 用来放表格的滚动控件
	EmployeechartTable tes; // 显示售票员销售额排行列表
	BarChart employeebarchart;// 柱状图
	public void showCurrentUser(){
		LoginedUser curUser=LoginedUser.getInstance();
		String name=curUser.getEmpName();
		if(null==name ||  name.isEmpty())
			usrName.setText("售票员");
		else
			usrName.setText(name);		
	}
	public EmployeechartMgrUI(Employeechart e) {
		Rectangle rect1 = new Rectangle();
		rect1.setBounds(0, 81, 1024, 420);// 区域的位置和大小
		Rectangle rect2 = new Rectangle();
		rect2.setBounds(0, 400, 1024, contPan.getHeight() - 420);// 区域的位置和大小
		ca1 = new JLabel("员工销售额排行榜", JLabel.CENTER);
		ca1.setBounds(0, 5, rect1.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);
		jp1 = new JScrollPane();
		jp1.setBounds(0, 40, rect1.width, rect1.height - 90);
		contPan.add(jp1);
		tes = new EmployeechartTable(jp1);
		showTable(e);
		employeebarchart = new BarChart(e);
		employeebarchart.getChartPanel().setBounds(rect2);
		contPan.add(employeebarchart.getChartPanel());

	}
	private void showTable(Employeechart e) {
		tes.showMoviechartList(e);
	}

	public static void main(String[] args) {
		Employeechart e = new Employeechart();
		e = new EmployeechartSrv().count(e);
		EmployeechartMgrUI frmEcMgr = new EmployeechartMgrUI(e);
		frmEcMgr.setVisible(true);

	}

}
