package xupt.se.ttms.model;
/*
 * author:liufan*/
public class Moviechart {

	private int[] play_chart_id =new int[50];
	private String[] play_chart_name =new String[50];
	private int[] play_chart_price =new int[50];
	private int length=0;
//	private int[] ticket_num=new int[50];//票数
//	public void setticket_num(int i,int num){
//		this.ticket_num[i]=num;
//	}
//	public int getticket_num(int i){
//		return ticket_num[i];
//	}
	public void setPlay_chart_id(int i,int id){
		this.play_chart_id[i]=id;
	}
	public int getPlay_chart_id(int i){
		return play_chart_id[i];
	}
	public void setPlay_chart_name(int i,String name){
		this.play_chart_name[i]=name;
	}

	public String getPlay_chart_name(int i){
		return play_chart_name[i];
	}
	public void setPlay_chart_price(int i,int price){
		this.play_chart_price[i]=price;
	}
	public int getPlay_chart_price(int i){
		return play_chart_price[i];
	}
	public void setLength(int length){
		this.length=length;
	}
	public int getLength(){
		return length;
	}
	
}
