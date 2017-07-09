package xupt.se.ttms.model;

import java.sql.Blob;

public class Play {
		private int id=0      ; 
		private String name="" ;
		private String introduction="";
		private int length=0;
		private int ticket_price=0;
		private int status=0;/*剧目状态	0：待安排演出
										1：已安排演出
        								-1：下线*/
		private Blob  image;//剧目海报
		private int play_type_id;//剧目类型ID
		private int play_lang_id;//剧目语言ID
		
		public void setID(int ID){
			this.id=ID;
		}
		
		public int getID(){
			return id;
		}

		public void setPlay_lang_id(int id){
			this.play_lang_id=id;
		}
		public int getPlay_lang_id(){
			return play_lang_id;
		}
		public void setPlay_type_id(int id){
			this.play_type_id=id;
		}
		public int getPlay_type_id(){
			return play_type_id;
		}
		public void setName(String name){
			this.name=name;
		}
		
		public String getName(){
			return name;
		}
		
		public void setIntroduction(String introduce ){
			this.introduction=introduce;
		}
		
		public String getIntroduction(){
			return introduction;
		}

		public void setImage(Blob image){
			this.image=image;
		}
		
		public Blob getImage(){
			return image;
		}
		
		public void setLength(int len){
			this.length=len;
		}
		
		public int getLength(){
			return length;
		}
		public void setTicket_price(int TPrice){
			this.ticket_price=TPrice;
		}
		
		public int getTicket_price(){
			return ticket_price;
		}	
		public void setStatus(int status){
			this.status=status;
		}
		
		public int getStatus(){
			return status;
		}	
		
	}


