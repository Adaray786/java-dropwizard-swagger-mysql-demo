package org.kainos.ea.core;

import org.kainos.ea.cli.Customer;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.db.CustomerDao;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class OrderValidator {
    private CustomerDao customerDao = new CustomerDao();
    public String isValidOrder(OrderRequest order) throws SQLException {
        Customer customer = customerDao.getCustomerById(order.getCustomerId());
        if (customer == null) {
            return "Customer does not exist";
        }

        if (order.getOrderDate().before(Date.from(Instant.now().minus(Duration.ofDays(365))))) {
            return "Order older than 1 year";
        }

        if (order == null) {
            return "Order does not exist";
        }

        return null;
    }
}
