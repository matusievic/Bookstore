package dao.order;

import entities.order.BookOrder;

import java.util.List;

public interface OrderDAO {
    BookOrder create(BookOrder order);
    BookOrder read(int id);
    List<BookOrder> read(String email);
    List<BookOrder> read();
    BookOrder update(BookOrder order);
    void delete(BookOrder order);
}
