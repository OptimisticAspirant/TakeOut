/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/8 8:55:17                             */
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
   add_id               varchar(20) not null,
   cust_id              varchar(20),
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
   coup_id              varchar(20) not null,
   shop_id              varchar(20),
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
   coup_id              varchar(20) not null,
   cust_id              varchar(20) not null,
   shop_id              varchar(20) not null,
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
   cust_id              varchar(20) not null,
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
   cust_id              varchar(20) not null,
   shop_id              varchar(20) not null,
   coup_id              varchar(20) not null,
   collect_count        int not null,
   collect_require      int not null,
   primary key (cust_id, shop_id, coup_id)
);

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   manager_id           varchar(20) not null,
   manager_name         varchar(20) not null,
   manager_password     varchar(20) not null,
   primary key (manager_id)
);

/*==============================================================*/
/* Table: orderdetail                                           */
/*==============================================================*/
create table orderdetail
(
   pro_id               varchar(20) not null,
   order_id             varchar(20) not null,
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
   pre_id               varchar(20) not null,
   shop_id              varchar(20),
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
   pro_id               varchar(20) not null,
   shop_id              varchar(20),
   cate_id              varchar(20),
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
   cate_id              varchar(20) not null,
   columnname           varchar(20) not null,
   pro_count            int not null,
   primary key (cate_id)
);

/*==============================================================*/
/* Table: productevaluate                                       */
/*==============================================================*/
create table productevaluate
(
   shop_id              varchar(20) not null,
   pro_id               varchar(20) not null,
   cust_id              varchar(20) not null,
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
   order_id             varchar(20) not null,
   pre_id               varchar(20),
   add_id               varchar(20),
   cust_id              varchar(20),
   shop_id              varchar(20),
   coup_id              varchar(20),
   rider_id             varchar(20),
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
   rider_id             varchar(20) not null,
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
   rider_id             varchar(20) not null,
   order_id             varchar(20) not null,
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
   shop_id              varchar(20) not null,
   shop_name            varchar(20) not null,
   shop_star            float,
   per_consume          float,
   total_sale           int not null,
   primary key (shop_id)
);

alter table address add constraint FK_owns foreign key (cust_id)
      references customer (cust_id) on delete restrict on update restrict;

alter table coupon add constraint FK_Relationship_11 foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table couponhold add constraint FK_couponhold foreign key (coup_id)
      references coupon (coup_id) on delete restrict on update restrict;

alter table couponhold add constraint FK_couponhold2 foreign key (cust_id)
      references customer (cust_id) on delete restrict on update restrict;

alter table couponhold add constraint FK_couponhold3 foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table discount add constraint FK_discount foreign key (cust_id)
      references customer (cust_id) on delete restrict on update restrict;

alter table discount add constraint FK_discount2 foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table discount add constraint FK_discount3 foreign key (coup_id)
      references coupon (coup_id) on delete restrict on update restrict;

alter table orderdetail add constraint FK_orderdetail foreign key (pro_id)
      references product (pro_id) on delete restrict on update restrict;

alter table orderdetail add constraint FK_orderdetail2 foreign key (order_id)
      references productorder (order_id) on delete restrict on update restrict;

alter table preferential add constraint FK_Relationship_12 foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table product add constraint FK_Relationship_13 foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table product add constraint FK_sort foreign key (cate_id)
      references productcategory (cate_id) on delete restrict on update restrict;

alter table productevaluate add constraint FK_productevaluate foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table productevaluate add constraint FK_productevaluate2 foreign key (pro_id)
      references product (pro_id) on delete restrict on update restrict;

alter table productevaluate add constraint FK_productevaluate3 foreign key (cust_id)
      references customer (cust_id) on delete restrict on update restrict;

alter table productorder add constraint FK_Relationship_10 foreign key (add_id)
      references address (add_id) on delete restrict on update restrict;

alter table productorder add constraint FK_Relationship_8 foreign key (pre_id)
      references preferential (pre_id) on delete restrict on update restrict;

alter table productorder add constraint FK_Relationship_9 foreign key (coup_id)
      references coupon (coup_id) on delete restrict on update restrict;

alter table productorder add constraint FK_had foreign key (cust_id)
      references customer (cust_id) on delete restrict on update restrict;

alter table productorder add constraint FK_has foreign key (shop_id)
      references shopkeeper (shop_id) on delete restrict on update restrict;

alter table productorder add constraint FK_owned foreign key (rider_id)
      references rider (rider_id) on delete restrict on update restrict;

alter table riderbill add constraint FK_riderbill foreign key (rider_id)
      references rider (rider_id) on delete restrict on update restrict;

alter table riderbill add constraint FK_riderbill2 foreign key (order_id)
      references productorder (order_id) on delete restrict on update restrict;

