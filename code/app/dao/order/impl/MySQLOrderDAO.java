package dao.order.impl;

import dao.order.OrderDAO;
import entities.order.BookOrder;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MySQLOrderDAO implements OrderDAO {
    @Override
    public BookOrder create(BookOrder order) {
        Optional<BookOrder> maxIdOrder = BookOrder.find.all().stream().max(Comparator.comparing(BookOrder::getId));
        int maxId = 0;
        if (maxIdOrder.isPresent()) {
            maxId = maxIdOrder.get().getId() + 1;
        }
        order.setId(maxId);
        order.save();
        return order;
    }

    @Override
    public BookOrder read(int id) {
        return BookOrder.find.byId(id);
    }

    @Override
    public List<BookOrder> read(String email) {
        return BookOrder.find.all().stream().filter(o -> o.getAccountId().equals(email)).collect(Collectors.toList());
    }

    @Override
    public List<BookOrder> read() {
        return BookOrder.find.all();
    }

    @Override
    public BookOrder update(BookOrder order) {
        order.update();
        return order;
    }

    @Override
    public void delete(BookOrder order) {
        order.delete();
    }
}
