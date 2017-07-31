package com.interview.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * This class simply shows an error view for
 * an unexpected error
 */

@UIScope
@SpringView(name = ErrorView.VIEW_NAME)
public class ErrorView extends VerticalLayout implements View{
    /*
    * Variables*/
    public static final String VIEW_NAME = "error";

    /*
    * Default constructors*/
    public ErrorView(){}

    /*
    * This methods setups all the UI components after
    * the constructor*/

    @PostConstruct
    private void init(){
        setSizeFull();
        Label errorMessage = new Label("Ooops, there seems to be a spanner in the bolts !!");
        errorMessage.setStyleName(ValoTheme.LABEL_H3);
        errorMessage.addStyleName(ValoTheme.LABEL_LIGHT);
        addComponentsAndExpand(errorMessage);
        setComponentAlignment(errorMessage, Alignment.MIDDLE_CENTER);
    }
}
