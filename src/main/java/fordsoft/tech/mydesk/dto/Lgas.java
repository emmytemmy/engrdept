package fordsoft.tech.mydesk.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

 public class Lgas {

    static ObservableList<String> obsList;

    // Returns an ObservableList populated by Grades
    public static ObservableList<String> obsList() {
        ArrayList<String> items = new ArrayList<>();

        items.add("Irepodun");
        items.add("Ifelodun");
        items.add("Isin");
        items.add("Oke-Ero");
        items.add("Ekiti");
        items.add("Baruteen");
        items.add("Kaiama");
        items.add("Edu");
        items.add("Ilorin East");
        items.add("Ilorin West");
        items.add("Ilorin South");
        items.add("");
        items.add("");
        items.add("");
        items.add("");
       
       

        return obsList = FXCollections.observableArrayList(items);

    }

}

