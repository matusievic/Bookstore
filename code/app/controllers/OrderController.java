package controllers;

import entities.account.AccountType;
import entities.order.Order;
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
        Order order = orderService.get(id);

        return TODO;
    }



    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null && accountType.equals(AccountType.ADMIN.toString());
    }

}
