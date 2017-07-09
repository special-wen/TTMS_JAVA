package xupt.se.ttms.model;
/*
 * author:liufan*/
public class Employeechart {

	private int[] emp_chart_id=new int[100];
	private String[] emp_chart_name=new String[100];
	private String[] emp_chart_no= new String[100];//工号
	private int[] emp_chart_sale=new int[100];
	private int length=0;
	public int getemp_chart_id(int i){
		return emp_chart_id[i];
	}
	public void setemp_chart_id(int i,int id){
		this.emp_chart_id[i]=id;
	}
	public String getemp_chart_name(int i){
		return emp_chart_name[i];
	}
	public void setemp_chart_name(int i,String name){
		this.emp_chart_name[i]=name;
	}
	public String getemp_chart_no(int i){
		return emp_chart_no[i];
	}
	public void setemp_chart_no(int i,String no){
		this.emp_chart_no[i]=no;
	}
	public int getemp_chart_sale(int i){
		return emp_chart_sale[i];
	}
	public void setemp_chart_sale(int i,int sale){
		this.emp_chart_sale[i]=sale;
	}
	public int getlength(){
		return length;
	}
	public void setlength(int length){
		this.length=length;
	}

}
