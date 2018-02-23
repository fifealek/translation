package my.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import my.form.binders.entities.ForeignWordTable;

public class GridTranslateWords extends VerticalLayout implements View {
  private MyUI myUI;
  private Grid<ForeignWordTable> grid;
  private ForeignWordForm foreignWordForm;

  public GridTranslateWords(MyUI myUI,Grid grid) {
    this.myUI=myUI;
    this.grid=grid;
    foreignWordForm = new ForeignWordForm(this.myUI);
    VerticalLayout verticalLayout = new VerticalLayout();
    grid.setColumns("ID", "foreign","translation");
    grid.setSizeUndefined();
    HorizontalLayout main = new HorizontalLayout();
    main.addComponents(grid, foreignWordForm);
    main.setSizeFull();
    grid.setSizeFull();
    main.setExpandRatio(grid, 1);
    if (myUI.repository != null) {
      myUI.updateGrid(null);
    }
    //String m = getMessage();
//        setContent(new Button("Click me", e -> Notification.show(m + " Hello Spring+Vaadin user!")));
    verticalLayout.addComponent(main);
    //setContent(verticalLayout);
    addComponent(verticalLayout);
    addEvent();
  }

  private void addEvent(){
    grid.setSelectionMode(Grid.SelectionMode.SINGLE);

    grid.addSelectionListener(event->{
      //Notification.show(event.getFirstSelectedItem().isPresent()? event.getFirstSelectedItem().get().getForeign() : String.valueOf(event));
      if(event.getFirstSelectedItem().isPresent()) {
        myUI.editView(event.getFirstSelectedItem().get());
      }

    });

  }

}
