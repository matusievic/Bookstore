package services.order;

import entities.order.Order;
import entities.order.OrderStatus;

import java.util.Date;

public interface OrderService {
    Order add(String userId, Date date, String address, String phone, int zip);
    Order get(int orderId);
    Order setStatus(int orderId, OrderStatus status, String answer);
}
