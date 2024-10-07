CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);


CREATE TABLE customers (
    customer_Id INT NOT NULL AUTO_INCREMENT,
    customer_first_name VARCHAR(255) NOT NULL,
    customer_last_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_password VARCHAR(255) NOT NULL,
    customer_address TEXT NOT NULL,
    customer_email_verification boolean,
    customer_created_time timestamp,
    customer_updated_time timestamp,
    PRIMARY KEY (customer_Id)
);

CREATE TABLE units (
    unit_Id INT NOT NULL AUTO_INCREMENT,
    unit_name VARCHAR(255) NOT NULL,
    unit_abbreviation VARCHAR(255) NOT NULL,
    unit_created_time timestamp,
    unit_updated_time timestamp,
    PRIMARY KEY (unit_Id)
);

CREATE TABLE categories (
    category_Id INT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    category_created_time timestamp,
    category_updated_time timestamp,
    PRIMARY KEY (category_Id)
);

CREATE TABLE products (
    product_Id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT NOT NULL,
    product_price decimal NOT NULL,
    product_stock_quantity INT NOT NULL,
	category_Id INT NOT NULL,
    unit_Id INT NOT NULL,
    product_image_url varchar(255) NOT NULL,
    product_created_time timestamp,
    product_updated_time timestamp,
    PRIMARY KEY (product_Id),
    FOREIGN KEY (category_Id) REFERENCES categories(category_Id),
    FOREIGN KEY (unit_Id) REFERENCES units(unit_Id)
);

CREATE TABLE Add_To_Cart (
    cart_Id INT NOT NULL AUTO_INCREMENT,
    customer_Id INT NOT NULL,
    product_Id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL NOT NULL,
    cart_created_time timestamp,
    cart_updated_time timestamp,
	PRIMARY KEY (cart_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (product_Id) REFERENCES products(product_Id)
);

ALTER TABLE customers ADD COLUMN customer_phone_number VARCHAR(15);

select current_timestamp;