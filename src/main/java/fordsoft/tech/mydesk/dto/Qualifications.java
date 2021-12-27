package fordsoft.tech.mydesk.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

 public class Qualifications {

    static ObservableList<String> obsList;

    // Returns an ObservableList populated by Grades
    public static ObservableList<String> obsList() {
        ArrayList<String> items = new ArrayList<>();

        items.add("PHD");
        items.add("MSC");
        items.add("B.ENG");
        items.add("PGD");
        items.add("HND");
        items.add("OND");
        items.add("SSC");
        items.add("FLSC");
        
       

        return obsList = FXCollections.observableArrayList(items);

    }

}

