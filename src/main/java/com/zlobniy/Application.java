package com.zlobniy;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Aleksandr on 04.12.2016.
 */
public class Application extends UI {

    @WebServlet(urlPatterns = "/*", name = "app", asyncSupported = true)
    @VaadinServletConfiguration(ui = Application.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private Grid grid = new Grid();

    @Override
    protected void init( VaadinRequest vaadinRequest ){

        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Table table = new Table( "test table" );

        table.setWidth( 100.0f, Unit.PERCENTAGE );
        table.setHeight( 80.0f, Unit.PERCENTAGE );
        table.addContainerProperty( "label field", Button.class, null );
        table.addContainerProperty( "check box field", CheckBox.class, null );
        table.addContainerProperty( "text field", TextField.class, null );
        table.addContainerProperty( "button", Button.class, null );

        Label sumField = new Label( String.format(
                "Sum is <b>$%04.2f</b><br/><i>(VAT incl.)</i>",
                new Object[]{new Double( Math.random() * 1000 )} ),
                ContentMode.HTML );


        CheckBox transferredField = new CheckBox( "is transferred" );

        // Multiline text field. This required modifying the
        // height of the table row.
        TextField commentsField = new TextField();

        // The Table item identifier for the row.
        Integer itemId = 1;

        // Create a button and handle its click. A Button does not
        // know the item it is contained in, so we have to store the
        // item ID as user-defined data.
        Button detailsField = new Button( "show details" );
        detailsField.setData( itemId );
        detailsField.addClickListener( new Button.ClickListener() {
            public void buttonClick( Button.ClickEvent event ){
                // Get the item identifier from the user-defined data.
                Integer iid = (Integer) event.getButton().getData();
                Notification.show( "Link " +
                        iid.intValue() + " clicked." );
            }
        } );
        detailsField.addStyleName( "link" );

        Object[] item1 = new Object[]{sumField, transferredField, commentsField, detailsField};
        table.addItem( item1, itemId );

        // add Grid to the layout
        layout.addComponents( table );
        layout.addComponent( new Button( "test" ) );

        setContent( layout );

    }
}
