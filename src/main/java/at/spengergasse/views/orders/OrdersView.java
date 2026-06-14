package at.spengergasse.views.orders;

import at.spengergasse.domain.Order;
import at.spengergasse.domain.OrderException;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.LocalDate;

@PageTitle("Orders")
@Route("orders")
@Menu(order = 1, icon = LineAwesomeIconUrl.FILE)
public class OrdersView extends VerticalLayout {
    private final Button buttonRemoveAllOrders = new Button("Remove All");
    private final Button buttonAddTenOrders = new Button("Add 10 orders");
    private final Button buttonAddOneEuro = new Button("Add One Euro");
    private final Button buttonRemoveAllExpressOrders = new Button("Remove All Expr-Orders");
    private final Button buttonAddInvalidData = new Button("Add Invalid Data");
    private final Button buttonAddOneOrder = new Button("Add One Order");
    private final Grid<Order> grid = new Grid<>(Order.class, false);
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
        buttonAddOneOrder.addClickListener(event -> addEditOrder(null));
        buttonAddInvalidData.addClickListener(event -> addInvalidData());

        add(new HorizontalLayout(buttonRemoveAllOrders, buttonAddTenOrders, buttonAddOneEuro, buttonRemoveAllExpressOrders, buttonAddOneOrder, buttonAddInvalidData));

        grid.addColumn(order -> order.getOrderId())
                .setHeader("Order ID")
                .setSortable(true);
        grid.addColumn(order -> order.getOrderDate())
                .setHeader("Order Date")
                .setSortable(true);
        grid.addColumn(order -> order.getCustomerName())
                .setHeader("Customer Name")
                .setSortable(true);

        Image shirt = new Image("icons/Shirt.png", "Shirtlogo");
        shirt.setWidth("40px");
        HorizontalLayout headerType = new HorizontalLayout(shirt, new Span("Type"));

        grid.addColumn(order -> order.getLaundryType())
             .setHeader(headerType)
             .setSortable(true);
        grid.addColumn(order -> order.getNumberLaundry())
             .setHeader("Number")
             .setSortable(true);
        grid.addColumn(order -> order.getPrice())
             .setHeader("Price/Item")
             .setSortable(true);
        grid.addColumn(order -> (order.getExpressService() == true) ? "Express" : "Normal")
            .setHeader("Service")
            .setSortable(true);
        grid.addComponentColumn(order -> {
             Checkbox express = new Checkbox(order.getExpressService());
             express.setReadOnly(true);
             return express;
        })
                // er hat .setHeader("Action")  nicht Service!!!!
            .setHeader("Service")
            .setSortable(true);

        grid.addComponentColumn(order -> {
            Button delete = new Button("Delete");
            delete.addClickListener(e-> removeOneOrder(order.getOrderId()));
            return delete;
        })
            .setHeader("Action")
            .setSortable(false);

        grid.addComponentColumn(order -> {
            Button addOneItem = new Button ("Add One Item");
            addOneItem.addClickListener(e -> addOneItem(order.getOrderId()));
            return addOneItem;
        })
            .setHeader("Action")
            .setSortable(false);

        grid.addComponentColumn(order -> {
            Button editOrder = new Button("Edit");
            editOrder.addClickListener(event -> addEditOrder(order));
            return editOrder;
        })
            .setHeader("Action")
            .setSortable(false);

        add(grid);
        reload();
    }

    private void addEditOrder(Order existingOrder) {
        Dialog dialog;
        Order order;

        dialog = new Dialog();
        if(existingOrder == null) {
            dialog.setHeaderTitle("Add One Order");
            order = new Order();
        }
        else {
            dialog.setHeaderTitle("Edit the Order");
            order = existingOrder;
        }

        TextField  orderId = new TextField("Order ID");
        DatePicker orderDate = new DatePicker("Order Date");
        TextField  customerName = new TextField("Customer Name");
        ComboBox laundryType = new ComboBox("Laundry Type");
        laundryType.setItems("Trousers", "Dress", "Skirt", "Jumper", "Leatherjacket");
        NumberField price = new NumberField("Price");
        IntegerField numberLaundry = new IntegerField("Number of Laundry Items");
        Checkbox expressService = new Checkbox("Express: Yes/No");

        BeanValidationBinder<Order> binder = new BeanValidationBinder<>(Order.class);

        binder.forField(orderDate).bind("orderDate");
        binder.forField(customerName).bind("customerName");
        binder.forField(laundryType).bind("laundryType");
        binder.forField(price).bind("price");
        binder.forField(numberLaundry).bind("numberLaundry");
        binder.forField(expressService).bind("expressService");

        binder.setBean(order);

        orderId.setValue("" + order.getOrderId());
        orderId.setReadOnly(true);

        VerticalLayout formLayout = new VerticalLayout(
                orderId,
                orderDate,
                customerName,
                laundryType,
                price,
                numberLaundry,
                expressService
        );

        Button buttonOK = new Button("OK");
        Button buttonCancel = new Button("Cancel");

        buttonOK.addClickListener(event -> {
            try {
                if (binder.validate().isOk()) {
                    orderService.addOneOrder(order);
                    dialog.close();
                    reload();
                    if(existingOrder == null)
                        Notification.show("New order added!");
                    else
                        Notification.show("Order was modified!");
                } else {
                    Notification.show("Check your input!");
                }
            }
            catch (OrderException e) {
                Notification.show(e.getMessage());
            }
        });
        buttonCancel.addClickListener(event -> dialog.close());

        dialog.add(formLayout);
        dialog.getFooter().add(buttonOK, buttonCancel);

        dialog.open();
    }

    private void addOneItem(Long orderId) {
        try {
            orderService.addOneItem(orderId);
            reload();
        }
        catch (OrderException e) {
            Notification.show(e.getMessage());
        }
    }

    private void removeOneOrder(Long orderId){
        try {
            orderService.removeOneOrder(orderId);
            reload();

        }
        catch (OrderException e) {
            Notification.show(e.getMessage());
        }
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
        }
    }

    private void addOneEuro() {
        try {
            orderService.addOneEuro();
            reload();
        }
        catch(OrderException e) {
            Notification.show(e.getMessage());
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
        }
    }

    private void reload() {
        grid.setItems(orderService.findAll());
    }
}
