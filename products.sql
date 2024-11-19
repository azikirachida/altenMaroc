CREATE TABLE `product` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50),
  `name` VARCHAR(50),
  `description` VARCHAR(50),
  `image` VARCHAR(50),
  `category` VARCHAR(50),
  `price` DECIMAL(12,2),
  `quantity` INT,
  `internal_reference` VARCHAR(50),
  `inventory_status` VARCHAR(50),
  `shell_id` INT,
  `rating` INT,
  `created_at` DATE,
  `updated_at` DATE,
  PRIMARY KEY (`id`)
);
INSERT INTO `alten`.`product` (`code`,`name`,`description`,`image`,`category`,`price`,`quantity`,`internal_reference`,`inventory_status`,`shell_id`,`rating`,`created_at`,`updated_at`)
VALUES("123456","TV LG","tv 60","tv_tg.jpg","tv",200.00,100,"ABC123456","INSTOCK",123456,6,NOW(),null);

INSERT INTO `alten`.`product` (`code`,`name`,`description`,`image`,`category`,`price`,`quantity`,`internal_reference`,`inventory_status`,`shell_id`,`rating`,`created_at`,`updated_at`)
VALUES("225588","TV SONY","tv 55","tv_sony.jpg","tv",300.00,200,"FDRY123","OUTOFSTOCK",485697,6,NOW(),null);
