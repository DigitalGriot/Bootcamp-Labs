package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class ContractDataManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "YUm15510n";

    public static void saveContract(Contract contract) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            if (contract instanceof SalesContract) {
                saveSalesContract(connection, (SalesContract) contract);
            } else if (contract instanceof LeaseContract) {
                saveLeaseContract(connection, (LeaseContract) contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveSalesContract(Connection connection, SalesContract salesContract) throws SQLException {
        String sql = "{CALL saveSalesContract(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setString(1, salesContract.getDate());
            statement.setString(2, salesContract.getCustomerName());
            statement.setString(3, salesContract.getCustomerEmail());
            statement.setInt(4, salesContract.getVehicleSold().getVin());
            statement.setInt(5, salesContract.getVehicleSold().getYear());
            statement.setString(6, salesContract.getVehicleSold().getMake());
            statement.setString(7, salesContract.getVehicleSold().getModel());
            statement.setString(8, salesContract.getVehicleSold().getVehicleType());
            statement.setInt(9, salesContract.getVehicleSold().getOdometer());
            statement.setDouble(10, salesContract.getVehicleSold().getPrice());
            statement.setDouble(11, salesContract.getTotalPrice());
            statement.setDouble(12, salesContract.getMonthlyPayment());
            statement.setBoolean(13, salesContract.isFinanced());

            statement.execute();
        }
    }

    private static void saveLeaseContract(Connection connection, LeaseContract leaseContract) throws SQLException {
        String sql = "{CALL saveLeaseContract(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setString(1, leaseContract.getDate());
            statement.setString(2, leaseContract.getCustomerName());
            statement.setString(3, leaseContract.getCustomerEmail());
            statement.setInt(4, leaseContract.getVehicleSold().getVin());
            statement.setInt(5, leaseContract.getVehicleSold().getYear());
            statement.setString(6, leaseContract.getVehicleSold().getMake());
            statement.setString(7, leaseContract.getVehicleSold().getModel());
            statement.setString(8, leaseContract.getVehicleSold().getVehicleType());
            statement.setInt(9, leaseContract.getVehicleSold().getOdometer());
            statement.setDouble(10, leaseContract.getVehicleSold().getPrice());
            statement.setDouble(11, leaseContract.getTotalPrice());
            statement.setDouble(12, leaseContract.getMonthlyPayment());

            statement.execute();
        }
    }
}
