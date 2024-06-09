


-- Create the new database
CREATE DATABASE dealership;

-- Use the newly created database
USE dealership;

-- Create the tables
CREATE TABLE dealerships (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL
);

CREATE TABLE vehicles (
    VIN VARCHAR(17) PRIMARY KEY,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    color VARCHAR(20) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    SOLD BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE inventory (
    dealership_id INT,
    VIN VARCHAR(17),
    PRIMARY KEY (dealership_id, VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE salesContracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    sale_date DATE NOT NULL,
    sale_price DECIMAL(10, 2) NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_address VARCHAR(50) NOT NULL,
    customer_phone VARCHAR(12) NOT NULL,
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE leaseContracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17) NOT NULL,
    leaseStartDate DATE NOT NULL,
    leaseEndDate DATE NOT NULL,
    monthlyPayment DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

-- Insert data into the leaseContracts table
INSERT INTO leaseContracts (VIN, leaseStartDate, leaseEndDate, monthlyPayment)
VALUES 
    ('37846', '2021-09-28', '2024-09-28', 541.39), 
    ('37846', '2024-05-19', '2027-05-19', 25.33);

-- Retrieve and display the data
SELECT * FROM leaseContracts;
