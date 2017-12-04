package dao.order;

import entities.order.BookOrder;

import java.util.List;

public interface OrderDAO {
    BookOrder create(BookOrder order);
    BookOrder read(int id);
    List<BookOrder> read(String email);
    BookOrder update(BookOrder order);
    void delete(BookOrder order);
}
