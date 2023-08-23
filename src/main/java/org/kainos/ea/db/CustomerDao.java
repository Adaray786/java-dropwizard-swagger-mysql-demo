package org.kainos.ea.db;

import org.kainos.ea.cli.Customer;
import org.kainos.ea.cli.Product;

import java.sql.*;

public class CustomerDao {

    DatabaseConnector databaseConnector = new DatabaseConnector();
    public Customer getCustomerById (int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String st = "SELECT CustomerID, Name, Address, Phone FROM Customer WHERE OrderID=?";

        PreparedStatement preparedStatement = c.prepareStatement(st);

        preparedStatement.setInt(1,id);

        ResultSet rs = preparedStatement.executeQuery();
        //use prepared statement

        while (rs.next()) {
            return new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            );

        }
        return null;
    }
}
