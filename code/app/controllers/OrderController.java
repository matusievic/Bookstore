package controllers;

import entities.order.BookOrder;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.order.OrderService;
import views.html.index;

import javax.inject.Inject;

public class OrderController extends Controller {
    @Inject
    private FormFactory formFactory;

    public Result display(int id) {
        if (!isAccountHasAccess()) { return ok(index.render()); }

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        BookOrder order = orderService.get(id);

        return ok(views.html.cart.shipping.render(order));
    }

    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null;
    }

}
