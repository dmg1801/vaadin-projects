package com.example.application.views.list;

import java.util.Collections;

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

@PageTitle("Contact | Vaadin CRM")
@Route(value = "")
public class ListView extends VerticalLayout {
	Grid<Contact> grid = new Grid<>(Contact.class);
	TextField filterText = new TextField();
	ContactForm form;

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

	private Component getContent() {
		HorizontalLayout content = new HorizontalLayout(grid, form); 
		content.setFlexGrow(2, grid);
		content.setFlexGrow(1, form);
		content.addClassName("content");
		content.setSizeFull();
		
		return content;
	}

	private void configureForm() {
		form = new ContactForm(Collections.emptyList(), Collections.emptyList());
		form.setWidth("25em");
		
	}

	private Component getToolbar() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		
		Button addContactButton = new Button("Add Contact");
		
		HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void configureGrid() {
		grid.addClassName("contact-grid");
		grid.setSizeFull();
		grid.setColumns("firstName", "lastName", "email");
		grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Status");
		grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Company");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
				
	}

}
