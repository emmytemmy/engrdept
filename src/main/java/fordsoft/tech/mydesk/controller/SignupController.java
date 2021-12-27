package fordsoft.tech.mydesk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.dto.Cadre;
import fordsoft.tech.mydesk.dto.Grades;
import fordsoft.tech.mydesk.dto.Lgas;
import fordsoft.tech.mydesk.dto.Qualifications;
import fordsoft.tech.mydesk.dto.Ranks;
import fordsoft.tech.mydesk.model.User;
import fordsoft.tech.mydesk.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

@Component
@FxmlView("/ui/signup.fxml")
public class SignupController implements Initializable {

    @Autowired
    UserService userService;
    @Autowired
    Router router;

    // the name of the nodes are coming from fxml fx:id -> it is identity associated to component in fxml to build a controller
    @FXML
    private Label lblError;

    @FXML
    private Label txtBirth;

    @FXML
    private Label lblNameError;

    @FXML
    private Label lblMailError;

    @FXML
    private Label lblPassError;

    @FXML
    private Label lblCountryError;

    @FXML
    private Label lblCityError;

    @FXML
    private JFXTextField txtFullName;

    @FXML
    private JFXDatePicker txtDofa;

    @FXML
    private JFXDatePicker txtDoc;

    @FXML
    private JFXDatePicker txtDopa;

    @FXML
    private JFXComboBox txtGradelevel;

    @FXML
    private JFXComboBox txtqualification;

    @FXML
    private JFXComboBox txtLga;

    @FXML
    private JFXTextField txtPsn;

    @FXML
    private JFXRadioButton txtmale;

    @FXML
    private ToggleGroup group;

    @FXML
    private JFXRadioButton txtfemale;

    @FXML
    private JFXComboBox txtranks;

    @FXML
    private JFXComboBox txtCadre;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPass;

    @FXML
    private Button button;

    @FXML
    private JFXDatePicker txtDob;

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

        String fullName;
        Date dob;
        Date dofa;
        Date doc;
        Date dopa;
        String ranking;
        String grade;
        String qualification;
        String lga;
        String psn;
        String gender;
        String cadre;
        String location;
        String email;
        String password;

        // Check if any of the text field is empty
        ArrayList<JFXTextField> txtList = new ArrayList<>();
        txtList.add(txtFullName);
        txtList.add(txtPsn);
        txtList.add(txtLocation);
        txtList.add(txtEmail);
        txtList.add(txtPass);

        // iterate the textField nodes
        for (JFXTextField nodes : txtList) {
            if (nodes.getText().isEmpty()) {
                lblError.setText("Please complete  all the field");
            }

        }
        if (txtCadre.getSelectionModel().isEmpty()) {     // check if a country is selected
            lblCountryError.setText("Select a country from the list");
        }
        if (txtGradelevel.getSelectionModel().isEmpty()) {     // check if a country is selected
            lblCountryError.setText("Select a country from the list");
        }
        if (txtqualification.getSelectionModel().isEmpty()) {     // check if a country is selected
            lblCountryError.setText("Select a country from the list");
        }
        if (txtLga.getSelectionModel().isEmpty()) {     // check if a country is selected
            lblCountryError.setText("Select a country from the list");
        }
        if (txtranks.getSelectionModel().isEmpty()) {     // check if a country is selected
            lblCountryError.setText("Select a country from the list");
        } else if (isValidEmailAddress(txtEmail.getText()) == false) {     // check if the mail address is a valid address
            lblMailError.setText("Enter a valid mail");
            lblError.setText("");
        }

        if (lblError.getText().isBlank()) {

            // store the user's inputs
            fullName = txtFullName.getText();
            ranking = txtranks.getSelectionModel().getSelectedItem().toString();
            grade = txtGradelevel.getSelectionModel().getSelectedItem().toString();
            qualification = txtqualification.getSelectionModel().getSelectedItem().toString();
            lga = txtLga.getSelectionModel().getSelectedItem().toString();
            psn = txtPsn.getText();
            //gender = group.getSelectedToggle().getToggleGroup().toString();
            cadre = txtCadre.getSelectionModel().getSelectedItem().toString();
            location = txtLocation.getText();
            email = txtEmail.getText();
            password = txtPass.getText();
            RadioButton bt = (RadioButton) group.getSelectedToggle();
            gender = bt.getText();

            User eng = new User();
            eng.setFullname(fullName);
            eng.setDob(txtDob.getValue());
            eng.setDoc(txtDoc.getValue());
            eng.setDofa(txtDofa.getValue());
            eng.setDopa(txtDopa.getValue());
            eng.setRanks(ranking);
            eng.setGradelevel(grade);
            eng.setQualification(qualification);
            eng.setLga(lga);
            eng.setPsn(psn);
            eng.setGender(gender);
            eng.setCadre(cadre);
            eng.setLocation(location);
            eng.setEmail(email);
            eng.setPassword(password);
            eng.setDatecreated(LocalDateTime.now());
            userService.save(eng);
            lblError.setText("Registaration is succesful");
            clearFields();
        }

    }

    private void clearFields() {
        txtFullName.clear();
        txtLga.getSelectionModel().clearSelection();
        txtCadre.getSelectionModel().clearSelection();
        txtGradelevel.getSelectionModel().clearSelection();
        txtqualification.getSelectionModel().clearSelection();
        txtranks.getSelectionModel().clearSelection();
        txtEmail.clear();
        txtLocation.clear();
        txtPass.clear();
        txtPsn.clear();
        txtDob.setValue(null);
        txtDofa.setValue(null);
        txtDoc.setValue(null);
        txtDopa.setValue(null);

    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            //  InternetAddress emailAddr = new InternetAddress(email);
            //emailAddr.validate();
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    @FXML
    void onLogin(ActionEvent event) {
        router.navigate(LoginController.class, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // display the list of countries
        txtCadre.setItems(Cadre.obsList());
        txtGradelevel.setItems(Grades.obsList());
        txtLga.setItems(Lgas.obsList());
        txtqualification.setItems(Qualifications.obsList());
        txtranks.setItems(Ranks.obsList());

    }
}
