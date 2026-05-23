package at.spengergasse.views.home;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.TSHIRT_SOLID)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(false);

        setAlignItems(Alignment.CENTER);

        H1 companyName = new H1 ("S U P E R C L E A N");
        companyName.getStyle()
            .set("font-family", "bold")
            .set("font-size", "6rem")
            .set("margin", "0");

        H2 subName = new H2("The Whiter Than White Laundry");
        subName.getStyle()
            .set("margin", "0")
            .set("color", "blue");

        Image logo = new Image("images/logo.png", "Logo");
        logo.setWidth("400px");

        Paragraph line1 = new Paragraph("At our laundry shop, excellence is not just a promise — it is the standard we deliver every single day. From everyday garments to premium fabrics, every piece is treated with precision, care, and professional attention. Customers especially love our renowned Quick White Shirt Service, designed for busy professionals who expect perfectly cleaned, crisply pressed white shirts in record time without compromising quality. Every shirt leaves our shop looking immaculate, fresh, and ready to impress.");
        Paragraph line2 = new Paragraph ("Our Leather Jacket Service has become one of the most trusted specialty treatments in the area. Leather requires expert handling, and our experienced team uses premium cleaning and conditioning techniques to restore softness, shine, and elegance while protecting the material’s durability. Whether it is a luxury fashion piece or a beloved vintage jacket, customers consistently praise the remarkable transformation and attention to detail that make their garments look almost brand new again.");
        Paragraph line3 = new Paragraph ("What truly sets our laundry shop apart is our dedication to customer satisfaction, speed, and premium garment care. We combine modern cleaning technology with personalized service to create an experience that feels both luxurious and reliable. From spotless white shirts delivered with exceptional speed to expertly restored leather jackets, our shop has earned a reputation for quality, professionalism, and outstanding results that customers proudly recommend again and again.");
        line1.setWidth("800px");
        line1.getStyle()
            .set("font-size", "18px")
            .set("line-height", "1.4")
            .set("text-align", "left");
        line2.setWidth("800px");
        line2.getStyle()
            .set("font-size", "18px")
            .set("line-height", "1.4")
            .set("text-align", "left");
        line3.setWidth("800px");
        line3.getStyle()
            .set("font-size", "18px")
            .set("line-height", "1.4")
            .set("text-align", "left");

        H3 name = new H3("Superclean GmbH");
        H3 street = new H3("An der Alten Donau 1");
        H3 city = new H3("1020 Wien");

        add(companyName, subName, logo, line1, line2, line3, name, street, city);
    }

}
