package at.spengergasse.views.prices;

import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Prices")
@Route("prices")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PricesView extends VerticalLayout {
/* test*/
    public PricesView() {
        setSpacing(false);

        setAlignItems(Alignment.CENTER);
        VerticalLayout header = HomeView.getHeader();
        setAlignItems(Alignment.CENTER);

        H2 priceList = new H2("Price List");
        priceList.getStyle()
            .set("margin", "0")
            .set("color", "blue");

        FlexLayout laundry = new FlexLayout();

        VerticalLayout tr = getCard("Trousers", 15, "Ironing", 8, 10 );
        VerticalLayout dr = getCard("Dress", 20, "Ironing", 11, 11);
        VerticalLayout sk = getCard("Skirt", 17, "Ironing", 9, 9);
        VerticalLayout ju = getCard("Jumper", 14, "Ironing", 10, 8);
        VerticalLayout lj = getCard("Leatherjacket", 95, "Impregnation", 25, 20);

        laundry.add(tr, dr, sk, ju, lj);
        laundry.setWidthFull();
        laundry.setJustifyContentMode(JustifyContentMode.CENTER);
        laundry.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(laundry);


        Paragraph info = new Paragraph("includes taxes. Delivery is also possible");

        add(header, priceList, laundry, info);
    }

    public VerticalLayout getCard(String laundryName, double price, String extraService, double priceIroningImp, double priceExpress) {
        VerticalLayout whichLaundry = new VerticalLayout();
        H2 laundryNameH2 = new H2(laundryName);
        Paragraph laundryPrice = new Paragraph("Cleaning: " + price + " €");
        Paragraph priceII = new Paragraph(extraService + ": " + priceIroningImp + " €");
        Paragraph priceExp = new Paragraph("Express 24H Service: " + priceExpress + " €");

        whichLaundry.setWidth("350px");
        whichLaundry.setPadding(true);
        whichLaundry.setSpacing(false);

        whichLaundry.getStyle()
            .set("border", "1px solid blue")
            .set("border-radius", "10px")
            .set("margin", "10px");
        whichLaundry.add(laundryNameH2, laundryPrice, priceII, priceExp);

        return whichLaundry;
    }


}
