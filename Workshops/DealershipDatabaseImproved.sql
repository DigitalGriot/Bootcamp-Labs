# Create the database
CREATE DATABASE dealership;

# Select the newly created database
use dealership;

# Creating the 'dealerships' table
CREATE TABLE dealerships (
    dealershipID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL
);
INSERT INTO dealerships (name, address, phone) 
VALUES ('D & B Used Cars', '111 Old Benbrook Rd', '817-555-5555');

# Creating the 'vehicles' table
CREATE TABLE vehicles (
    VIN VARCHAR(20) PRIMARY KEY,
    make VARCHAR(20) NOT NULL,
    model VARCHAR(20) NOT NULL,
    year INT NOT NULL,
    vehicleType VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    odometer INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    sold Bit
);
INSERT INTO vehicles (VIN, make, model, year, vehicleType, color, odometer, price, sold) VALUES
('10112', 'Ford', 'Explorer', 1993, 'SUV', 'Red', 525123, 995.00, 1),
('37846', 'Ford', 'Ranger', 2001, 'Truck', 'Yellow', 172544, 1995.00, 0),
('44902', 'Honda', 'Civic', 2012, 'SUV', 'red', 103221, 10000.50, 0),
('37845', 'Ford', 'Ranger', 2001, 'Truck', 'Black', 172555, 2350.36, 1),
('10113', 'Ford', 'Explorer', 1993, 'SUV', 'White', 250000, 1500.00, 0),
('12589', 'Cadillac', 'SRX', 2012, 'SUV', 'White', 155555, 8566.00, 1);

# Creating the 'inventory' table
CREATE TABLE inventory (
    dealershipID INT,
    VIN VARCHAR(20),
    PRIMARY KEY (dealershipID, VIN),
    FOREIGN KEY (dealershipID) REFERENCES dealerships(dealershipID),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);
INSERT INTO inventory (dealershipID, VIN) VALUES
(1, '10112'),
(1, '37846'),
(1, '44902'),
(1, '37845'),
(1, '10113'),
(1, '12589');

# Creating the 'sales_contracts' table
CREATE TABLE salesContracts (
    SaleID INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(20) NOT NULL,
    contractDate DATE NOT NULL,
    customerName VARCHAR(50) NOT NULL,
    customerEmail VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);
INSERT INTO salesContracts (VIN, contractDate, customerName, customerEmail, amount) VALUES
('10112', '2021-09-28', 'Dana Wyatt', 'dana@texas.com', 1439.75),
('10113', '2024-05-19', 'David virgen', 'Davidmtw3@gmail.com', 1439.75);
