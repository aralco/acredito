package com.bo.acredito.ui.util;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.tepi.filtertable.paged.PagedFilterControlConfig;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * Created by alvaro on 3/4/15.
 */
public class ListTableUtil {
    public static PagedFilterTable buildPagedTable(String[] tablePropertyIds,
                                             String[] tableHeaders,
                                             PagedFilterTable<IndexedContainer> filterTable,
                                             JPAContainer container){
        filterTable= new PagedFilterTable<IndexedContainer>();
        filterTable.setWidth("100%");

        filterTable.setFilterDecorator(new GenericFilterDecorator());
        filterTable.setFilterGenerator(new GenericFilterGenerator());

        filterTable.setFilterBarVisible(true);

        filterTable.setSelectable(true);
        filterTable.setImmediate(true);
        filterTable.setMultiSelect(true);

        filterTable.setRowHeaderMode(CustomTable.RowHeaderMode.INDEX);

        filterTable.setColumnCollapsingAllowed(true);

        filterTable.setColumnReorderingAllowed(true);

        filterTable.setContainerDataSource(container);

        filterTable.setVisibleColumns(tablePropertyIds);
        filterTable.setColumnHeaders(tableHeaders);

        return filterTable;
    }

    public static AbstractOrderedLayout createListLayout(
                                        String title,
                                        Button addButton,
                                        PagedFilterTable<IndexedContainer> filterTable){
        Panel productListPanel=new Panel(title);
        productListPanel.setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        addButton.setStyleName(Reindeer.BUTTON_SMALL);
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(addButton);
        verticalLayout.addComponent(filterTable);
        verticalLayout.addComponent(filterTable.createControls(new PagedFilterControlConfig()));
        productListPanel.setContent(verticalLayout);
        mainLayout.addComponent(productListPanel);
        return mainLayout;
    }
}
