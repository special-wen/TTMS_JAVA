/*==============================================================*/
/* DBMS name:       SQL Server                                   */
/* Created on:      2017.6                         */
/*==============================================================*/
/*授权给用户minpan*/
grant select,insert,update,delete on play to minpan;
/*授权给用户liufan*/
grant select,insert,update,delete on play to liufan;
grant select,insert,update,delete on sale to liufan;
grant select,insert,update,delete on sale_item to liufan;
grant select,insert,update,delete on schedule to liufan;
grant select,insert,update,delete on ticket to liufan;
grant select,insert,update,delete on data_dict to liufan;
grant select,insert,update,delete on employee to liufan;
grant select,insert,update,delete on seat to liufan;
grant select,insert,update,delete on studio to liufan;

/*授权给用户wangyiru*/
grant select,insert,update,delete on ticket to wangyiru;


/*建数据库*/
drop table if exists data_dict;

drop table if exists employee;

drop table if exists play;

drop table if exists sale;

drop table if exists sale_item;

drop table if exists schedule;

drop table if exists seat;

drop table if exists studio;

drop table if exists ticket;

/*==============================================================*/
/* Table: data_dict                                             */
/*==============================================================*/
create table data_dict
(
   dict_id              int not null identity(1,1),
   dict_parent_id       int,
   dict_index           int,
   dict_name            varchar(200),
   dict_value           varchar(100) not null,
   primary key (dict_id)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   emp_id               int not null identity(1,1),
   emp_no               varchar(20) not null,
   emp_name             varchar(10) not null,
   emp_password         varchar(20),
   emp_sure             varchar(20),
   emp_addr             varchar(100),
   emp_email            varchar(100),
   primary key (emp_id)
);

/*==============================================================*/
/* Table: play                                                  */
/*==============================================================*/
create table play
(
   play_id              int not null identity(1,1),
   play_type_id         int,
   play_lang_id         int,
   play_name            varchar(200),
   play_introduction    varchar(2000),
   play_image           Image,
   play_length          int,
   play_ticket_price    numeric(10,2),
   play_status          smallint /*comment '取值含义：
            0：待安排演出
            1：已安排演出
            -1：下线'*/,
   primary key (play_id)
);

/*==============================================================*/
/* Table: sale                                                  */
/*==============================================================*/
create table sale
(
   sale_ID              bigint not null identity(1,1),
   emp_id               int,
   sale_time            datetime,/*销售时间*/
   sale_payment         decimal(10,2),/*支付金额*/
   sale_change          numeric(10,2),/*找零*/
   sale_type            smallint  /*'类别取值含义：
            1：销售单
            -1：退款单',*/,
   sale_status          smallint /*comment '销售单状态如下：
            0：代付款
            1：已付款',*/,
   primary key (sale_ID)
);

/*==============================================================*/
/* Table: sale_item  销售单明细                                           */
/*==============================================================*/
create table sale_item
(
   sale_item_id         bigint not null identity(1,1),
   ticket_id            bigint,
   sale_ID              bigint,
   sale_item_price      numeric(10,2),
   primary key (sale_item_id)
);

/*==============================================================*/
/* Table: schedule        演出计划                                      */
/*==============================================================*/
create table schedule
(
   sched_id             int not null identity(1,1),
   studio_id            int,
   play_id              int,
   sched_time           datetime not null,
   sched_ticket_price   numeric(10,2),
   primary key (sched_id)
);

/*==============================================================*/
/* Table: seat                                                  */
/*==============================================================*/
create table seat
(
   seat_id              int not null identity(1,1),
   studio_id            int,
   seat_row             int,
   seat_column          int,
   seat_state			varchar(20) default'可用',
   /*seat_status          smallint /*comment    '取值含义：
                       0：此处是空位，没有安排座椅
                       1：完好的座位，可以使用
                       -1：座位损坏，不能安排座位'*/,
*/
   primary key (seat_id)
);

/*==============================================================*/
/* Table: studio                                                */
/*==============================================================*/
create table studio
(
   studio_id            int not null identity(1,1),
   studio_name          varchar(100) not null,
   studio_row_count     int,
   studio_col_count     int,
   studio_introduction  varchar(2000),
  /* studio_status		char(20),*/
   studio_state			char(20) default '可用',
   /*studio_flag          smallint /*comment    '取值含义：
                        0：尚未生成座位，可以根据行列信息生成座位
                        1：已经根据影厅的座位信息安排了座位，不能再安排座位；
                        -1：影厅损坏或者废弃，不能使用'*/,
*/
   primary key (studio_id)
);

/*==============================================================*/
/* Table: ticket     演出票                                           */
/*==============================================================*/
create table ticket
(
   ticket_id            bigint not null identity(1,1),
   seat_id              int,
   sched_id             int,
   ticket_price         numeric(10,2),
   ticket_status        smallint /*comment '含义如下：
            0：待销售
            1：锁定
            9：卖出'*/,
   ticket_locked_time   datetime,
   primary key (ticket_id)
);

alter table data_dict add constraint FK_super_child_dict foreign key (dict_parent_id)
      references data_dict (dict_id) on delete no action on update no action;

alter table play add constraint FK_dict_lan_play foreign key (play_lang_id)
      references data_dict (dict_id) on delete no action on update no action;

alter table play add constraint FK_dict_type_play foreign key (play_type_id)
      references data_dict (dict_id) on delete no action on update no action;

alter table sale add constraint FK_employee_sale foreign key (emp_id)
      references employee (emp_id) on delete no action on update no action;

alter table sale_item add constraint FK_sale_sale_item foreign key (sale_ID)
      references sale (sale_ID) on delete no action on update no action;

alter table sale_item add constraint FK_ticket_sale_item foreign key (ticket_id)
      references ticket (ticket_id) on delete no action on update no action;

alter table schedule add constraint FK_play_sched foreign key (play_id)
      references play (play_id) on delete no action on update no action;

alter table schedule add constraint FK_studio_sched foreign key (studio_id)
      references studio (studio_id) on delete no action on update no action;

alter table seat add constraint FK_studio_seat foreign key (studio_id)
      references studio (studio_id) on delete no action on update no action;

alter table ticket add constraint FK_sched_ticket foreign key (sched_id)
      references schedule (sched_id) on delete no action on update no action;

alter table ticket add constraint FK_seat_ticket foreign key (seat_id)
      references seat (seat_id) on delete no action on update no action;
GO
select count(*) 
from ticket,schedule,play 
where ticket.sched_id=schedule.sched_id and play.play_id=schedule.play_id and ticket.ticket_status=9 and play.play_id=30;
select play_ticket_price 
from ticket,schedule,play 
where ticket.sched_id=schedule.sched_id and play.play_id=schedule.play_id and ticket.ticket_status=9 and play.play_id=30;
			
delete ticket ;
