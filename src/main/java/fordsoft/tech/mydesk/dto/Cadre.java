package fordsoft.tech.mydesk.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Cadre {

    static ObservableList<String> obsList;

    // Returns an ObservableList populated by Grades
    public static ObservableList<String> obsList() {
        ArrayList<String> items = new ArrayList<>();

        items.add("Officer");
        items.add("Suprintentend");
        items.add("Others");

        return obsList = FXCollections.observableArrayList(items);
        
        

    }

    
    
}
