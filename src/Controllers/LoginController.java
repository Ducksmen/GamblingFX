package Controllers;

import Dependencies.Systems.CSVReader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LoginController {
    @FXML private Pane pane;
    @FXML private JFXTextField usernameField;
    @FXML private JFXPasswordField passwordField;
    @FXML private JFXButton createAccountButton;
    private CSVReader csvReader;

    public void initialize() {
        File protectedData = new File("src/Dependencies/Systems/Data/LoginData.csv");
        csvReader = new CSVReader(protectedData);
    }

    @FXML
    private void checkLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (csvReader.getDataString(3).contains(username)) {
            String correctUserPassword = csvReader.getDataString(4).get(csvReader.getDataString(3).indexOf(username));
            if (password.equals(correctUserPassword)) {
                System.out.println("Working");
            } else {
                System.out.println("Wrong Password");
            }
        } else {
            System.out.println("Username does not exist");
        }
    }

    @FXML
    private void allowAccountCreation() throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/Resources/FXML/AccountCreation.fxml"));
        Stage currentStage = (Stage) pane.getScene().getWindow();
        currentStage.setScene(new Scene(newRoot, 600, 400));
    }
}
