package at.spengergasse.views.prices;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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

        H1 companyName = new H1 ("S U P E R C L E A N");
        companyName.getStyle()
            .set("font-family", "bold")
            .set("font-size", "6rem")
            .set("margin", "0");

        H2 subName = new H2("The Whiter Than White Laundry");
        subName.getStyle()
            .set("margin", "0")
            .set("color", "blue");

        H2 priceList = new H2("Price List");
        priceList.getStyle()
            .set("margin", "0")
            .set("color", "blue");

        H2 Trousers = new H2("Trousers");
        Paragraph priceTrousers = new Paragraph("Cleaning: 15 €");
        Paragraph priceTrousersIron = new Paragraph("Ironing: 8 €");
        Paragraph priceFastT = new Paragraph("Express 24H Service: 10 €");

        H2 Dress = new H2("Dress");
        Paragraph priceDress = new Paragraph("Cleaning: 20 €");
        Paragraph priceDressIron = new Paragraph("Ironing: 11 €");
        Paragraph priceFastD = new Paragraph("Express 24H Service: 11 €");

        H2 Skirt = new H2("Skirt");
        Paragraph priceSkirt = new Paragraph("Cleaning: 17 €");
        Paragraph priceSkirtIron = new Paragraph("Ironing: 9 €");
        Paragraph priceFastS = new Paragraph("Express 24H Service: 9 €");

        H2 Jumper = new H2("Jumper");
        Paragraph priceJumper = new Paragraph("Cleaning: 14 €");
        Paragraph priceJumperIron = new Paragraph("Ironing: 10 €");
        Paragraph priceFastJ = new Paragraph("Express 24H Service: 8 €");

        H2 Leatherjacket = new H2("Leatherjacket");
        Paragraph priceLeatherjacket = new Paragraph("Cleaning: 95 €");
        Paragraph priceLJImpreg = new Paragraph("Impregnation: 25 €");
        Paragraph priceFastLJ = new Paragraph("Express 24H Service: 20 €");
        /*companyName, subName, priceList, Trousers,*/
        add(companyName, subName, priceList, Trousers, priceTrousers, priceTrousersIron, priceFastT,
                Dress, priceDress, priceDressIron, priceFastD,
                Skirt, priceSkirt, priceSkirtIron, priceFastS,
                Jumper, priceJumper, priceJumperIron, priceFastJ,
                Leatherjacket, priceLeatherjacket, priceLJImpreg, priceFastLJ);

        /*H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
        */
        /*
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center"); */
    }

}
