package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.example.application.data.service.CrmService;
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
import jakarta.annotation.security.PermitAll;

import java.awt.*;
import java.util.Collections;

@PageTitle("Projects | CRM")
@PermitAll
@Route(value = "")
public class ListView extends VerticalLayout {
    Grid <Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    ProjectForm form ;
    private CrmService service;

    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();
        closeEditor();
        

    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");

    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }

    private HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }
    private void configureForm() {
        form = new ProjectForm();
        form.setWidth("25em");

       form.addSaveListener(this::saveContact);
       form.addDeleteListener(this::deleteContact);
       form.addCloseListener(e -> closeEditor());

    }
        private void saveContact (ProjectForm.SaveEvent event){
        service.saveContact(event.getContact());
        updateList();
        closeEditor();

        }

        private void deleteContact(ProjectForm.DeleteEvent event){
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
        }
    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name.....");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(e -> addContact());

        HorizontalLayout toolbar= new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassNames("toolbar");
        return toolbar;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns ("projectNumber", "projectName", "dateOfBeginn",
                        "projectManager", "priceNetto", "priceBrutto",
                        "statusOfProject", "comments");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));
    }

    private void editContact(Contact contact) {
        if(contact == null){
            closeEditor();
        }else {
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");

        }
    }

}
