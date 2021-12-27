package fordsoft.tech.mydesk.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

 public class Ranks {

    static ObservableList<String> obsList;

    // Returns an ObservableList populated by Ranks
    public static ObservableList<String> obsList() {
        ArrayList<String> items = new ArrayList<>();

        items.add("AE I");
        items.add("AE II");
        items.add("SAE");
        items.add("PAE");
        items.add("HWS");
        items.add("DD");
        items.add("DIRECTOR");
        items.add("PWS");
        
        return obsList = FXCollections.observableArrayList(items);

    }

}

