/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/8 16:24:43                            */
/*==============================================================*/


drop table if exists address;

drop table if exists coupon;

drop table if exists couponhold;

drop table if exists customer;

drop table if exists discount;

drop table if exists manager;

drop table if exists orderdetail;

drop table if exists preferential;

drop table if exists product;

drop table if exists productcategory;

drop table if exists productevaluate;

drop table if exists productorder;

drop table if exists rider;

drop table if exists riderbill;

drop table if exists shopkeeper;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   add_id               int not null,
   cust_id              int,
   province             varchar(20) not null,
   city                 varchar(20) not null,
   area                 varchar(20) not null,
   location             varchar(20) not null,
   contacts             varchar(20) not null,
   phonenumber          varchar(20) not null,
   primary key (add_id)
);

/*==============================================================*/
/* Table: coupon                                                */
/*==============================================================*/
create table coupon
(
   coup_id              int not null,
   shop_id              int,
   coup_amount          float not null,
   coup_count           int not null,
   startdate            datetime not null,
   enddate              datetime not null,
   primary key (coup_id)
);

/*==============================================================*/
/* Table: couponhold                                            */
/*==============================================================*/
create table couponhold
(
   coup_id              int not null,
   cust_id              int not null,
   shop_id              int not null,
   hold_mount           int not null,
   subtract             float,
   hold_deadline        datetime,
   primary key (coup_id, cust_id, shop_id)
);

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
   cust_id              int not null,
   cust_name            varchar(20) not null,
   cust_gender          varchar(5) not null,
   cust_password        varchar(30) not null,
   cust_phone           varchar(20) not null,
   cust_mail            varchar(30) not null,
   cust_city            varchar(20) not null,
   rig_time             datetime not null,
   ifVIP                varchar(5) not null,
   VIPdeadline          datetime,
   primary key (cust_id)
);

/*==============================================================*/
/* Table: discount                                              */
/*==============================================================*/
create table discount
(
   cust_id              int not null,
   shop_id              int not null,
   coup_id              int not null,
   collect_count        int not null,
   collect_require      int not null,
   primary key (cust_id, shop_id, coup_id)
);

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   manager_id           int not null,
   manager_name         varchar(20) not null,
   manager_password     varchar(20) not null,
   primary key (manager_id)
);

/*==============================================================*/
/* Table: orderdetail                                           */
/*==============================================================*/
create table orderdetail
(
   pro_id               int not null,
   order_id             int not null,
   mount                int not null,
   price                float not null,
   perdiscount          float not null,
   primary key (pro_id, order_id)
);

/*==============================================================*/
/* Table: preferential                                          */
/*==============================================================*/
create table preferential
(
   pre_id               int not null,
   shop_id              int,
   pre_require          float not null,
   pre_cut              float not null,
   ifcoupon             varchar(5) not null,
   primary key (pre_id)
);

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
   pro_id               int not null,
   shop_id              int,
   cate_id              int,
   pro_name             varchar(20) not null,
   pro_price            float not null,
   pro_discount         float not null,
   primary key (pro_id)
);

/*==============================================================*/
/* Table: productcategory                                       */
/*==============================================================*/
create table productcategory
(
   cate_id              int not null,
   columnname           varchar(20) not null,
   pro_count            int not null,
   primary key (cate_id)
);

/*==============================================================*/
/* Table: productevaluate                                       */
/*==============================================================*/
create table productevaluate
(
   shop_id              int not null,
   pro_id               int not null,
   cust_id              int not null,
   content              varchar(50),
   eval_date            datetime,
   star                 float,
   photo                longblob,
   primary key (shop_id, pro_id, cust_id)
);

/*==============================================================*/
/* Table: productorder                                          */
/*==============================================================*/
create table productorder
(
   order_id             int not null,
   pre_id               int,
   add_id               int,
   cust_id              int,
   shop_id              int,
   coup_id              int,
   rider_id             int,
   originprice          float not null,
   finalprice           float not null,
   starttime            datetime not null,
   requiretime          datetime not null,
   orderstate           varchar(20) not null,
   primary key (order_id)
);

/*==============================================================*/
/* Table: rider                                                 */
/*==============================================================*/
create table rider
(
   rider_id             int not null,
   rider_name           varchar(20) not null,
   entrydate            datetime not null,
   identity             varchar(20) not null,
   primary key (rider_id)
);

/*==============================================================*/
/* Table: riderbill                                             */
/*==============================================================*/
create table riderbill
(
   rider_id             int not null,
   order_id             int not null,
   taketime             datetime not null,
   evaluate             varchar(20),
   income               float not null,
   primary key (rider_id, order_id)
);

/*==============================================================*/
/* Table: shopkeeper                                            */
/*==============================================================*/
create table shopkeeper
(
   shop_id              int not null,
   shop_name            varchar(20) not null,
   shop_star            float,
   per_consume          float,
   total_sale           int not null,
   primary key (shop_id)
);