package services.order;

import entities.order.BookOrder;
import entities.order.OrderStatus;

import java.util.List;

public interface OrderService {
    BookOrder add(String userId, String name, String surname, String date, String address, String phone, int zip, String books);
    BookOrder get(int orderId);
    List<BookOrder> get(String email);
    BookOrder setStatus(int orderId, OrderStatus status, String answer);
}
