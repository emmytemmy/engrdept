/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fordsoft.tech.mydesk.controller;

import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.model.Comment;
import fordsoft.tech.mydesk.service.CommentService;
import fordsoft.tech.mydesk.service.UserService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author DELL
 */
@Component
@FxmlView("/ui/Comment.fxml")

public class CommentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private Router router;
    @FXML
    private Button Back;

    private Stage stage;
    @FXML
    private Button send;

    @FXML
    private TextField desc;
    @FXML
    private TextField comm;
    @FXML
    private Label ss;
    @FXML
    private Button refresh;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stage = new Stage();

    }

    @FXML
    private void Back(ActionEvent event) {
        router.navigate(DashboardController.class, event);

    }

    @FXML
    private void refresh(ActionEvent event) {
        router.navigate(CommentController.class, event);

    }

    @FXML
    private void send(ActionEvent event) {
        String description;
        String comment;

        ArrayList<TextField> txtList = new ArrayList<>();
        txtList.add(desc);
        txtList.add(comm);

        description = desc.getText();
        comment = comm.getText();

        Comment user = new Comment();
        user.setDescription(description);
        user.setComment(comment);
        commentService.save(user);
        ss.setText("Comment Sent");
        clear();
    }

    void clear() {
        desc.clear();
        comm.clear();
    }

}
