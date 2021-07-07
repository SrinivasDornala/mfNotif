CREATE TABLE IF NOT EXISTS user (
  id INTEGER(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  admin BOOLEAN not null default false
);
CREATE TABLE IF NOT EXISTS orders (
  id INTEGER(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  userid INTEGER(10) ,
  name VARCHAR(255) NOT NULL,
  notify VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS notifications (
  id INTEGER(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  order_id INTEGER(10) not null,
  to VARCHAR(32) not null,
  type VARCHAR(32) not null,
  text VARCHAR(500),
  subject VARCHAR(200),
  method VARCHAR(10),
  notify VARCHAR(10) not null,
  order_status VARCHAR(32) not null
);

insert into user
values(1l,'Abcd','abc@h.com','9177294641', 'false');
insert into user
values(2l,'Abcd3','def@h.com','9177294631', 'true');

insert into orders
values(1l,1l,'Order 1','N');
insert into orders
values(2l,2l,'Order 2','N');
insert into orders
values(3l,2l,'Order 3','N');
insert into orders
values(4l,1l,'Order 4','N');

insert into notifications
values(1l,1l,'abc@mail.com','Order Placed','Order Number O123 has been Placed','Your Order has been Placed -your order number is O123','email', 'Y','ORDER_PLACED');

insert into notifications
values(2l,2l,'+919177294644','Order Placed','Order Number O128 has been Placed','Your Order has been Placed -your order number is O128','sms', 'Y','ORDER_PLACED');

insert into notifications
values(3l,3l,'abc@mail.com','Order canceled','Order Number O123 has been canceled','Your Order has been canceled -your order number is O123','email', 'Y','ORDER_CANCELED');
insert into notifications
values(4l,4l,'abc@mail.com','Order canceled','Order Number O123 has been canceled','Your Order has been canceled -your order number is O123','email', 'Y','ORDER_CANCELED');
