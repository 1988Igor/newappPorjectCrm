package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class ProjectForm extends FormLayout {
    Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);


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
    private Contact contact;


    public ProjectForm() {
        binder.bindInstanceFields(this);
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

    public void setContact(Contact contact){
        this.contact = contact;
        binder.readBean(contact);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener((event -> fireEvent(new DeleteEvent(this, contact))));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(contact);
            fireEvent(new SaveEvent(this, contact));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ProjectForm> {
        private Contact contact;

        protected ContactFormEvent(ProjectForm source, Contact contact) {
            super(source, false);
            this.contact = contact;
        }

        public Contact getContact() {
            return contact;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ProjectForm source, Contact contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ProjectForm source, Contact contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ProjectForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
