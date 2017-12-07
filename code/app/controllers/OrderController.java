package controllers;

import entities.account.AccountType;
import entities.order.BookOrder;
import entities.order.OrderStatus;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.order.OrderService;

import javax.inject.Inject;

public class OrderController extends Controller {
    @Inject
    private FormFactory formFactory;
    OrderService orderService = ServiceFactory.getInstance().getOrderService();
    @Inject
    private Application applicationController;

    public Result display(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        BookOrder order = orderService.get(id);
        return ok(views.html.cart.shipping.render(order));
    }

    public Result edit(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess() || !session("accountType").equals(AccountType.ADMIN.toString())) {
            return redirect(previousPage);
        }
        Form<BookOrder> orderForm = formFactory.form(BookOrder.class);
        BookOrder order = orderService.get(id);
        return ok(views.html.cart.acceptance.render(order, orderForm));
    }

    public Result update(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess() || !session("accountType").equals(AccountType.ADMIN.toString())) {
            return redirect(previousPage);
        }
        Form<BookOrder> orderForm = formFactory.form(BookOrder.class).bindFromRequest();
        OrderStatus status = orderForm.get().getStatus();
        String answer = orderForm.get().getAnswer();
        orderService.setStatus(id, status, answer);
        return applicationController.orders();
    }

    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null;
    }

}
