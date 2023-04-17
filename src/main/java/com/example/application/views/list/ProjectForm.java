package com.example.application.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ProjectForm extends FormLayout {
    TextField projectNumber = new TextField("Project Number");
    TextField projectName = new TextField("Project Name");
    DatePicker dateOfBeginn = new DatePicker("Date Of Beginn");
    TextField projectManager = new TextField("Project Manager");
    TextField priceNetto = new TextField("Price Netto");
    TextField priceBrutto = new TextField("Price Brutto");
    TextField statusOfProject = new TextField("Status Of Project");
    TextField comments = new TextField("Comments");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");


    public ProjectForm() {
        add(
                projectNumber,
                projectName,
                dateOfBeginn,
                projectManager,
                priceNetto,
                priceBrutto,
                statusOfProject,
                comments,
                save,
                delete,
                cancel,
                createButtonLayout()
        );
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, cancel);
    }
}
