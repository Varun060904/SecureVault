import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ScreenSignup extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Sign Up");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setMaxWidth(200);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setMaxWidth(200);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(200);

        Button signupButton = new Button("Sign Up");

        centerBox.getChildren().addAll(titleLabel, nameField, usernameField, passwordField, phoneField, emailField,
                signupButton);

        signupButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String phone = phoneField.getText();
            String name = nameField.getText();
            String email = emailField.getText();

            // Implement your user registration logic here
            boolean result = Signup.signup(username, password, name, email, phone);
            if (result) {
                primaryStage.close();
                showAlert(Alert.AlertType.INFORMATION, "Sign Up Successful",
                        "Registration successful for " + username + "!");
                clearFields(usernameField, passwordField, phoneField, nameField, emailField);
            }else{
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Credentials.");
            }

        });

        root.setCenter(centerBox);

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.initOwner(primaryStage);
        alert.showAndWait();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
