package fordsoft.tech.mydesk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import static com.jfoenix.svg.SVGGlyphLoader.clear;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.dto.Cadre;
import fordsoft.tech.mydesk.dto.Grades;
import fordsoft.tech.mydesk.dto.Lgas;
import fordsoft.tech.mydesk.dto.Qualifications;
import fordsoft.tech.mydesk.dto.Ranks;
import fordsoft.tech.mydesk.model.User;
import static fordsoft.tech.mydesk.model.User_.cadre;
import static fordsoft.tech.mydesk.model.User_.email;
import static fordsoft.tech.mydesk.model.User_.password;
import static fordsoft.tech.mydesk.model.User_.ranks;
import fordsoft.tech.mydesk.service.UserService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Odofin Timothy
 */
@Component
@FxmlView("/ui/dashboard.fxml")
public class DashboardController implements Initializable {

    @FXML
    private Label userId;

    @FXML
    private Button reset;

    @FXML
    private Button saveUser;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Long> colUserId;

    @FXML
    private TableColumn<User, String> colFullName;

    @FXML
    private TableColumn<User, LocalDateTime> colDatecreated;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, Boolean> colEdit;

    private TableColumn<User, Boolean> colDelete;

    @FXML
    private MenuItem deleteUsers;

    @Autowired
    private Router entrance;

    @Autowired
    private UserService userService;

    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<String> roles = FXCollections.observableArrayList("Admin", "User");
    @FXML
    private Button delete;
    @FXML
    private Button btnLogout1;
    @FXML
    private Button comment;
    @FXML
    private TableColumn<User, LocalDateTime> colDob;
    @FXML
    private TableColumn<User, LocalDateTime> colDofa;
    @FXML
    private TableColumn<User, LocalDateTime> colDoc;
    @FXML
    private TableColumn<User, LocalDateTime> colDopa;
    @FXML
    private TableColumn<User, String> colRank;
    @FXML
    private TableColumn<User, String> colGradeLevel;
    @FXML
    private TableColumn<User, String> colQualification;
    @FXML
    private TableColumn<User, String> colLga;
    @FXML
    private TableColumn<User, String> colPsn;
    @FXML
    private TableColumn<User, String> colGender;
    @FXML
    private TableColumn<User, String> colCadre;
    @FXML
    private TableColumn<User, String> colLocation;
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
    private JFXComboBox<String> txtGradelevel;
    @FXML
    private JFXComboBox<String> txtqualification;
    @FXML
    private JFXComboBox<String> txtLga;
    @FXML
    private JFXTextField txtPsn;
    @FXML
    private JFXRadioButton txtmale;
    @FXML
    private ToggleGroup group;
    @FXML
    private JFXRadioButton txtfemale;
    @FXML
    private JFXComboBox<String> txtranks;
    @FXML
    private JFXComboBox<String> txtCadre;
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
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    private Stage stage;

    /**
     * Logout and go to the login page
     */
    @FXML
    private void logout(ActionEvent event) throws IOException {
        entrance.navigate(LoginController.class, event);
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        deleteUsers();
    }

    @FXML
    private void saveUser(ActionEvent event) {

        /*if (validate("Full Name", txtFullName.getText(), "[a-zA-Z]+")
                && emptyValidation("Location", txtLocation.getText() == null)
                && emptyValidation("Ranks", txtranks.getSelectionModel().getSelectedItem() == null
                        && emptyValidation("Email", txtEmail.getText() == null)))*/ {

            if (userId.getText() == null || userId.getText() == "") {

                User user = new User();

                user.setFullname(getFullName());
                user.setDob(txtDob.getValue());
                user.setDoc(txtDoc.getValue());
                user.setDofa(txtDofa.getValue());
                user.setDopa(txtDopa.getValue());
                user.setRanks(txtranks.getSelectionModel().getSelectedItem());
                user.setGradelevel(txtGradelevel.getSelectionModel().getSelectedItem());
                user.setQualification(txtqualification.getSelectionModel().getSelectedItem());
                user.setLga(txtLga.getSelectionModel().getSelectedItem());
                user.setPsn(txtPsn.getText());

                String gender;
                RadioButton bt = (RadioButton) group.getSelectedToggle();
                gender = bt.getText();

                user.setGender(gender);

                user.setCadre(txtCadre.getSelectionModel().getSelectedItem());
                user.setLocation(txtLocation.getText());
                user.setEmail(txtEmail.getText());
                user.setPassword(txtPass.getText());
                user.setDatecreated(LocalDateTime.now());

                User newUser = userService.save(user);

                saveAlert(newUser);

            } else {
                Optional<User> users = userService.find(Long.parseLong(userId.getText()));
                if (users.isPresent()) {
                    User user = users.get();

                    user.setFullname(getFullName());
                    user.setDob(txtDob.getValue());
                    user.setDoc(txtDoc.getValue());
                    user.setDofa(txtDofa.getValue());
                    user.setDopa(txtDopa.getValue());
                    user.setRanks(txtranks.getSelectionModel().getSelectedItem());
                    user.setGradelevel(txtGradelevel.getSelectionModel().getSelectedItem());
                    user.setQualification(txtqualification.getSelectionModel().getSelectedItem());
                    user.setLga(txtLga.getSelectionModel().getSelectedItem());
                    user.setPsn(txtPsn.getText());

                    String gender;
                    RadioButton bt = (RadioButton) group.getSelectedToggle();
                    gender = bt.getText();

                    user.setCadre(txtCadre.getSelectionModel().getSelectedItem());
                    user.setLocation(txtLocation.getText());
                    user.setEmail(txtEmail.getText());
                    user.setPassword(txtPass.getText());
                    user.setDatecreated(LocalDateTime.now());

                    User newUser = userService.save(user);
                    updateAlert(user);
                }

            }

            clearFields();
            loadUserDetails();
        }

    }

    @FXML
    private void deleteUsers() {
        List<User> users = userTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            userService.deleteInBatch(users);
        }

        loadUserDetails();
        clearFields();
    }

    private void clearFields() {
        userId.setText(null);
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

    private void saveAlert(User user) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + user.getFullname() + " has been created ");
        alert.showAndWait();
    }

    private void updateAlert(User user) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + user.getFullname() + " has been updated.");
        alert.showAndWait();
    }

    private String getGenderTitle(String gender) {
        return (gender.equals("Male")) ? "his" : "her";
    }

    public String getFullName() {
        return txtFullName.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = new Stage();
        // country.setItems(Countries.obsList());

        userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all users into table
        loadUserDetails();

        txtCadre.setItems(Cadre.obsList());
        txtGradelevel.setItems(Grades.obsList());
        txtLga.setItems(Lgas.obsList());
        txtqualification.setItems(Qualifications.obsList());
        txtranks.setItems(Ranks.obsList());

    }


    /*
     *  Set All userTable column properties
     */
    private void setColumnProperties() {
        /* Override date format in table
		 * colDOB.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd/MM/yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));*/

        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colDofa.setCellValueFactory(new PropertyValueFactory<>("dofa"));
        colDoc.setCellValueFactory(new PropertyValueFactory<>("doc"));
        colDopa.setCellValueFactory(new PropertyValueFactory<>("dopa"));
        colLga.setCellValueFactory(new PropertyValueFactory<>("lga"));
        colPsn.setCellValueFactory(new PropertyValueFactory<>("psn"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colCadre.setCellValueFactory(new PropertyValueFactory<>("cadre"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colRank.setCellValueFactory(new PropertyValueFactory<>("ranks"));
        colGradeLevel.setCellValueFactory(new PropertyValueFactory<>("gradelevel"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colDatecreated.setCellValueFactory(new PropertyValueFactory<>("datecreated"));
        colEdit.setCellFactory(cellFactory);
        //colDelete.setCellFactory(cellFactory);

    }

    Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>> cellFactory
            = new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
        @Override
        public TableCell<User, Boolean> call(final TableColumn<User, Boolean> param) {
            final TableCell<User, Boolean> cell = new TableCell<User, Boolean>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEdit.setOnAction(e -> {
                            User user = getTableView().getItems().get(getIndex());
                            updateUser(user);
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                /*
     *  To update the table data
                 */
                private void updateUser(User user) {
                    String fulln[] = user.getFullname().split(" ");
                    userId.setText(Long.toString(user.getId()));
                    txtFullName.setText(fulln[0]);
                    txtLocation.setText(user.getLocation());
                    txtPsn.setText(user.getPsn());

                    user.setDob(txtDob.getValue());
                    user.setDoc(txtDoc.getValue());
                    user.setDofa(txtDofa.getValue());
                    user.setDopa(txtDopa.getValue());

                    txtranks.getSelectionModel().select(user.getRanks());
                    txtGradelevel.getSelectionModel().select(user.getGradelevel());
                    txtCadre.getSelectionModel().select(user.getCadre());
                    txtLga.getSelectionModel().select(user.getLga());
                    txtqualification.getSelectionModel().select(user.getQualification());
                    txtEmail.setText(user.getEmail());
                }
            };
            return cell;
        }
    };

    /*
     *  Add All users to observable list and update table
     */
    private void loadUserDetails() {
        userList.clear();
        userList.addAll(userService.findAll());
        userTable.setItems(userList);
    }

    /*
     * Validations
     */
    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) {
            alert.setContentText("Please Select " + field);
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }

    @FXML
    private void comment(ActionEvent event) {
        entrance.navigate(CommentController.class, event);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
    }

    @FXML
    private void onLogin(ActionEvent event) {
    }

}
