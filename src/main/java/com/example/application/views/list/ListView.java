package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.awt.*;
import java.util.Collections;

@PageTitle("Projects | CRM")
@Route(value = "")
public class ListView extends VerticalLayout {
    Grid <Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    ProjectForm form ;
    public ListView() {
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

    }

    private Component    getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }
    private void configureForm() {
        form = new ProjectForm();
        form.setWidth("18cm");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name.....");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add contact");

        HorizontalLayout toolbar= new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassNames("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns ("projectNumber", "projectName", "dateOfBeginn",
                        "projectManager", "priceNetto", "priceBrutto",
                        "statusOfProject", "comments");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
