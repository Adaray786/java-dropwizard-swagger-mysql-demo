package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.OrderDao;
import org.kainos.ea.core.OrderValidator;

import java.sql.SQLException;
import java.util.*;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    private OrderValidator orderValidator = new OrderValidator();

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }
            int id = orderDao.createOrder(order);

            if (id == -1) {
                throw new FailedToCreateOrderException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateOrderException();
        }
    }

    public void updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation !=null) {
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrderById(id);

            if (orderToUpdate == null) {
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }

    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException {
        try {
            Order orderToDelete = orderDao.getOrderById(id);

            if (orderToDelete == null) {
                throw new OrderDoesNotExistException();
            }

            orderDao.deleteOrder(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteOrderException();
        }
    }

    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            throw new FailedToGetOrdersException();
        }



        /*
        HashMap<Integer, Integer> map = new HashMap<>();

        for(Order order : orderList) {
            if (map.containsKey(order.getCustomerId())) {
                map.put(order.getCustomerId(), map.get(order.getCustomerId() + 1));
            } else {
                map.put(order.getCustomerId(), 1);
            }
        }

        Collections.max(map.)

        orderList.stream()

         */

        /*
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date oneWeekAgo = calendar.getTime();

        for (Order order : orderList) {
            if (order.getOrderDate().after(oneWeekAgo)) {
                System.out.println(order);
            }
        }
         */

        /*
        Collections.sort(orderList);

        orderList.stream().forEach(System.out::println);

         */

        /*
        for (Order order : orderList) {
            System.out.println("This Order has an ID of: " + order.getOrderId());
            System.out.println("This Order belongs to the customer with the id of: " + order.getCustomerId());
            System.out.println("This Order is dated to: " + order.getOrderDate());
        }
         */
        return orderList;
    }
    public Order getOrderbyId(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }

            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetOrdersException();
        }
    }
}
