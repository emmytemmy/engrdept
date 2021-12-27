package fordsoft.tech.mydesk.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

 public class Grades {

    static ObservableList<String> obsList;

    // Returns an ObservableList populated by Grades
    public static ObservableList<String> obsList() {
        ArrayList<String> items = new ArrayList<>();

        items.add("GL 1");
        items.add("GL 2");
        items.add("GL 3");
        items.add("GL 4");
        items.add("GL 5");
        items.add("GL 6");
        items.add("GL 7");
        items.add("GL 8");
        items.add("GL 9");
        items.add("GL 10");
        items.add("GL 12");
        items.add("GL 13");
        items.add("GL 14");
        items.add("GL 15");
        items.add("GL 16");
        items.add("GL 17");
       

        return obsList = FXCollections.observableArrayList(items);

    }

}

