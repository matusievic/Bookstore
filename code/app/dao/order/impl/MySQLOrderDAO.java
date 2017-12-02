package dao.order.impl;

import dao.order.OrderDAO;
import entities.order.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MySQLOrderDAO implements OrderDAO {
    @Override
    public Order create(Order order) {
        int maxId = 0;
        Optional<Order> maxIdOrder = Order.find.all().stream().max(Comparator.comparing(Order::getId));
        if (maxIdOrder.isPresent()) {
            maxId = maxIdOrder.get().getId();
        }
        order.setId(maxId);
        order.save();

        return order;
    }

    @Override
    public Order read(int id) {
        return Order.find.byId(id);
    }

    @Override
    public List<Order> read(String email) {
        return Order.find.all().stream().filter(o -> o.getAccountId().equals(email)).collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        order.update();
        return order;
    }

    @Override
    public void delete(Order order) {
        order.delete();
    }
}
