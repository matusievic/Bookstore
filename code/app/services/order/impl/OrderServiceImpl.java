package services.order.impl;

import dao.DAOFactory;
import dao.order.OrderDAO;
import entities.order.Order;
import entities.order.OrderStatus;
import services.order.OrderService;

import java.util.Date;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    @Override
    public Order add(String userId, Date date, String address, String phone, int zip) {
        Order order = new Order();
        order.setAccountId(userId);
        order.setDate(date);
        order.setAddress(address);
        order.setPhone(phone);
        orderDAO.create(order);
        return order;
    }

    @Override
    public Order get(int orderId) {
        return orderDAO.read(orderId);
    }

    @Override
    public Order setStatus(int orderId, OrderStatus status, String answer) {
        Order order = get(orderId);
        if (order == null) {
            return null;
        }
        order.setStatus(status);
        order.setAnswer(answer);
        orderDAO.update(order);
        return order;
    }
}
