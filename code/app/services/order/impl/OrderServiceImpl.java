package services.order.impl;

import dao.DAOFactory;
import dao.order.OrderDAO;
import entities.order.BookOrder;
import entities.order.OrderStatus;
import services.order.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    @Override
    public BookOrder add(String userId, String name, String surname, String date, String address, String phone, int zip, String books) {
        BookOrder order = new BookOrder();
        order.setAccountId(userId);
        order.setName(name);
        order.setSurname(surname);
        order.setDate(date);
        order.setAddress(address);
        order.setPhone(phone);
        order.setBooks(books);
        order.setAnswer("There's no answer for this order yet.");
        order.setStatus(OrderStatus.NEW);
        orderDAO.create(order);
        return order;
    }

    @Override
    public BookOrder get(int orderId) {
        return orderDAO.read(orderId);
    }

    @Override
    public List<BookOrder> get(String email) {
        return orderDAO.read(email);
    }

    @Override
    public BookOrder setStatus(int orderId, OrderStatus status, String answer) {
        BookOrder order = get(orderId);
        if (order == null) {
            return null;
        }
        order.setStatus(status);
        order.setAnswer(answer);
        orderDAO.update(order);
        return order;
    }
}
