package dao.order;

import entities.order.Order;

import java.util.List;

public interface OrderDAO {
    Order create(Order order);
    Order read(int id);
    List<Order> read(String email);
    Order update(Order order);
    void delete(Order order);
}
