DROP TABLE IF EXISTS order_accessories;
DROP TABLE IF EXISTS accessories;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS lamps;
DROP TABLE IF EXISTS lenses;
DROP TABLE IF EXISTS fixtures;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  customer_pk int UNSIGNED NOT NULL AUTO_INCREMENT,
  customer_id varchar(50) NOT NULL,
  first_name varchar(25) NOT NULL, 
  last_name varchar(25) NOT NULL,
  phone varchar(20),
  PRIMARY KEY (customer_pk)
);

CREATE TABLE fixtures (
  fixture_pk int UNSIGNED NOT NULL AUTO_INCREMENT,
  fixture_id varchar(75) NOT NULL,
  color varchar(20) NOT NULL,
  plug_type varchar(50) NOT NULL,
  description varchar(150) NOT NULL,
  price decimal(6, 2) NOT NULL,
  PRIMARY KEY (fixture_pk)
);

CREATE TABLE lenses (
  lens_pk int UNSIGNED NOT NULL AUTO_INCREMENT,
  lens_id varchar(50) NOT NULL,
  lens_size int NOT NULL,
  lens_color varchar(20) NOT NULL, 
  description varchar(100) NOT NULL,
  price decimal(5, 2) NOT NULL,
  PRIMARY KEY (lens_pk)
);

CREATE TABLE lamps (
  lamp_pk int UNSIGNED NOT NULL AUTO_INCREMENT,
  lamp_id varchar(50) NOT NULL,
  wattage int NOT NULL,
  description varchar(100) NOT NULL,
  price decimal(4, 2) NOT NULL,
  PRIMARY KEY (lamp_pk)
);

CREATE TABLE orders (
  order_pk int UNSIGNED NOT NULL AUTO_INCREMENT,
  customer_fk int UNSIGNED NOT NULL,
  fixture_fk int UNSIGNED NOT NULL,
  lens_fk int UNSIGNED NOT NULL,
  lamp_fk int UNSIGNED NOT NULL,
  total_price decimal(7, 2) NOT NULL,
  PRIMARY KEY (order_pk),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (fixture_fk) REFERENCES fixtures (fixture_pk) ON DELETE CASCADE,
  FOREIGN KEY (lens_fk) REFERENCES lenses (lens_pk) ON DELETE CASCADE,
  FOREIGN KEY (lamp_fk) REFERENCES lamps (lamp_pk) ON DELETE CASCADE
  );
 
 CREATE TABLE accessories (
  accessory_pk int unsigned NOT NULL AUTO_INCREMENT,
  accessory_id varchar(50) NOT NULL,
  name varchar(60) NOT NULL,
  manufacturer varchar(50) NOT NULL,
  description varchar(250) NOT NULL,
  price decimal(6, 2) NOT NULL,
  PRIMARY KEY (accessory_pk)
  );
 
 CREATE TABLE order_accessories (
  accessory_fk int unsigned NOT NULL,
  order_fk int unsigned NOT NULL,
  FOREIGN KEY (accessory_fk) REFERENCES accessories (accessory_pk) ON DELETE CASCADE,
  FOREIGN KEY (order_fk) REFERENCES orders (order_pk) ON DELETE CASCADE
);