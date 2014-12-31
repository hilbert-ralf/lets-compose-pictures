package de.hilbert.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import de.hilbert.services.CommonBootstrapService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.spring.VaadinUI;

/**
 * @author hilbert
 * @since 14.11.14
 */
@VaadinUI
public class DashboardVaadinUI extends UI {

    public static Logger log = Logger.getLogger(DashboardVaadinUI.class);

    @Autowired
    private CommonBootstrapService commonBootstrapService;

    private Button initializationButton = new Button("Bootstrap Data",
            clickEvent -> {
                commonBootstrapService.bootstrapStocks();
                Notification.show("bootstrapped");
            });

    final UploadField uploadField = new UploadField();

    Button uploadButton = new Button("Show value",
            clickEvent -> {
                Object value = uploadField.getValue();
                Notification.show("Value:" + value);
            });

    Panel panel = new Panel();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //putting stuff together
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(createMainMenu());
        verticalLayout.addComponent(panel);
        changePage("");
        setContent(verticalLayout);
    }

    private MenuBar createMainMenu() {
        MenuBar barmenu = new MenuBar();

        // Define a common menu command for all the menu items.
        MenuBar.Command mycommand = selectedItem -> changePage(selectedItem.getText());

        // Put some items in the menu hierarchically
        barmenu.addItem(MENU_CAPTION_DASHBOARD, null, mycommand);
        barmenu.addItem(MENU_CAPTION_COLORS, null, mycommand);
        barmenu.addItem(MENU_CAPTION_ADMINISTRATION, null, mycommand);

        return barmenu;
    }

    public final static String MENU_CAPTION_DASHBOARD = "Dashboard";
    public final static String MENU_CAPTION_COLORS = "Colors";
    public final static String MENU_CAPTION_ADMINISTRATION = "Administration";

    private void changePage(String menuText) {
        switch (menuText) {
            case MENU_CAPTION_DASHBOARD:
                panel.setCaption(MENU_CAPTION_DASHBOARD);
                panel.setContent(createDashboardView());
                break;
            case MENU_CAPTION_COLORS:
                panel.setCaption(MENU_CAPTION_COLORS);
                break;
            case MENU_CAPTION_ADMINISTRATION:
                panel.setCaption(MENU_CAPTION_ADMINISTRATION);
                panel.setContent(createAdministrationView());
                break;
            default:
                panel.setCaption(MENU_CAPTION_DASHBOARD);
                panel.setContent(createDashboardView());
                break;
        }
    }

    private Component createAdministrationView() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(initializationButton);
        return horizontalLayout;
    }

    private Component createDashboardView() {
        uploadField.setFieldType(UploadField.FieldType.FILE);
        uploadField.setButtonCaption("Choose file...");
        
        return uploadField;
        //return new Label("Dashboard");
    }

}
