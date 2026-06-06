package at.spengergasse.views.orders;

import at.spengergasse.domain.Order;
import at.spengergasse.domain.OrderException;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Orders")
@Route("orders")
@Menu(order = 1, icon = LineAwesomeIconUrl.FILE)
public class OrdersView extends VerticalLayout {
    private final Button buttonRemoveAllOrders = new Button("Remove All");
    private final Button buttonAddTenOrders = new Button("Add 10 orders");
    private final Button buttonAddOneEuro = new Button("Add One Euro");
    private final Button buttonRemoveAllExpressOrders = new Button("Remove All Expr-Orders");
    private final Button buttonAddInvalidData = new Button("Add Invalid Data");
    private final Grid<Order> grid = new Grid<>(Order.class, true);
    private final OrderService orderService;

    public OrdersView(@Autowired OrderService orderService) {
        this.orderService = orderService;

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();

        buttonRemoveAllOrders.addClickListener(event -> removeAllOrders());
        buttonAddTenOrders.addClickListener(event -> addTenOrders());
        buttonAddOneEuro.addClickListener(event -> addOneEuro());
        buttonRemoveAllExpressOrders.addClickListener(event -> removeAllExpressOrders());
        buttonAddInvalidData.addClickListener(event -> addInvalidData());
        add(new HorizontalLayout(buttonRemoveAllOrders, buttonAddTenOrders, buttonAddOneEuro, buttonRemoveAllExpressOrders,buttonAddInvalidData));

        add(grid);
        reload();
    }

    private void addInvalidData() {
        try {
            orderService.addInvalidData();
            reload();
        }
        catch (OrderException e) {
            // ergibt Fenster links unten
            //Notification.show(e.getMessage());
            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setHeader("Error");
            dialog.setText(e.getMessage());
            dialog.setConfirmText("OK");
            dialog.open();
            reload();
        }
    }

    private void removeAllOrders() {
        try {
            orderService.removeAllOrders();
            buttonRemoveAllOrders.setEnabled(false);
            buttonRemoveAllExpressOrders.setEnabled(false);
            buttonAddOneEuro.setEnabled(false);
            reload();
        }
        catch(OrderException e) {
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void addTenOrders() {
        try {
            orderService.addTenOrders();
            buttonRemoveAllOrders.setEnabled(true);
            buttonRemoveAllExpressOrders.setEnabled(true);
            buttonAddOneEuro.setEnabled(true);
            reload();
        }
        catch(OrderException e) {
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void addOneEuro() {
        try {
            orderService.addOneEuro();
            reload();
        }
        catch(OrderException e) {
            Notification.show(e.getMessage());
            reload();
        }
    }
    private void removeAllExpressOrders() {
        try {
            orderService.removeAllExpressOrders();
            buttonRemoveAllExpressOrders.setEnabled(false);
            reload();
        }
        catch(OrderException e) {
            Notification.show(e.getMessage());
            reload();
        }
    }


    private void reload() {
        grid.setItems(orderService.findAll());
    }
}
