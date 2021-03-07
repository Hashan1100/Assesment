CREATE DATABASE IF NOT EXISTS shopping_db;
USE shopping_db;

SET GLOBAL local_infile = 'ON';

CREATE TABLE IF NOT EXISTS product
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cartoon_size INT,
    cartoon_prize DECIMAL(10, 2),
    single_unit_price_multiplier DECIMAL(4, 2),
    discount_percentage DECIMAL(4, 2),
    discount_size INT,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;

INSERT INTO product(name, cartoon_size, cartoon_prize, single_unit_price_multiplier, discount_percentage, discount_size) VALUES
('Penguin-ears', 20, 175.00, 1.3, 0.1, 3),
('Horseshoe', 5, 825.00, 1.3, 0.1, 3);

