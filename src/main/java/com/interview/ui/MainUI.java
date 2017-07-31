package com.interview.ui;

import com.interview.dao.MessageRepository;
import com.interview.views.ErrorView;
import com.interview.views.MainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * This class is our main UI class for vaadin
 * it will show the output for the user.
 * demo graph key 
 */
@SpringUI
@Theme("valo")
@SpringViewDisplay
@Push
public class MainUI extends UI implements ViewDisplay {
    //create the panel to show the views
    private Panel springViewDisplay;

    @Autowired
    SpringViewProvider viewProvider;

    @Autowired
    SpringNavigator navigator;

    @Autowired
    MessageRepository messageRepository;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();//cover the entire page
        //create root layout for the page.

        final VerticalLayout root = new VerticalLayout();
        setContent(root); //add content

        //root.setSizeFull();

        //panel that will show all our views (spring view navigation)
        springViewDisplay = new Panel();
        springViewDisplay.setResponsive(true);
        //springViewDisplay.setSizeFull(); //we make it full size to cover the whole page before we add any components
        springViewDisplay.setStyleName(ValoTheme.PANEL_BORDERLESS);

        //add components to the root layout
        root.addComponents( springViewDisplay);
        root.setExpandRatio(springViewDisplay, 20.0f);

        viewProvider.setAccessDeniedViewClass(ErrorView.class);
        navigator.setErrorView(ErrorView.class);

    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}
