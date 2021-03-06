package org.vaadin.extension.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.annotation.WebServlet;

import org.vaadin.extension.gridscroll.GridScrollExtension;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

@Push
@Theme("demo")
@Title("GridScrollExtension Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	Grid grid1 = null;
	Grid grid2 = null;

	public class GridTab1 extends VerticalLayout {
		GridTab1() {
			Random random = new Random(4837291937l);
			BeanItemContainer<SimplePojo> container = new BeanItemContainer<SimplePojo>(
					SimplePojo.class);
			for (int i = 0; i < 1000; i++) {
				container.addBean(new SimplePojo(i, "Bean", true, new Date(),
						BigDecimal.valueOf(random.nextDouble() * 100), Double
								.valueOf(random.nextInt(5))));
			}
			grid1 = new Grid(container);
			grid1.setColumns("description", "stars", "truth", "date", "number");
			grid1.setSizeFull();
			grid1.setEditorEnabled(false);
			setSizeFull();
			addComponent(grid1);
			setComponentAlignment(grid1, Alignment.MIDDLE_CENTER);
			setMargin(true);
		}
	}

	public class GridTab2 extends VerticalLayout {
		GridTab2() {
			Random random = new Random(4837291937l);
			BeanItemContainer<SimplePojo> container = new BeanItemContainer<SimplePojo>(
					SimplePojo.class);
			for (int i = 0; i < 1000; i++) {
				container.addBean(new SimplePojo(i, "Bean", true, new Date(),
						BigDecimal.valueOf(random.nextDouble() * 100), Double
								.valueOf(random.nextInt(5))));
			}
			grid2 = new Grid(container);
			grid2.setColumns("description", "stars", "truth", "date", "number");
			grid2.setSizeFull();
			grid2.setEditorEnabled(false);
			setSizeFull();
			addComponent(grid2);
			setComponentAlignment(grid2, Alignment.MIDDLE_CENTER);
			setMargin(true);
		}
	}

    
    @Override
    protected void init(VaadinRequest request) {

        // Show it in the middle of the screen
        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(new GridTab1(), "Grid 1");
        tabSheet.addTab(new GridTab2(), "Grid 2");
        tabSheet.setSizeFull();
        setContent(tabSheet);
        
        GridScrollExtension ext1 = new GridScrollExtension(grid1);
        GridScrollExtension ext2 = new GridScrollExtension(grid2);        
    }
    
  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
  public static class Servlet extends VaadinServlet {
  }
}
