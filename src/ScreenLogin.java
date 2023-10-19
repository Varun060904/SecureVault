import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ScreenLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

       
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(200);
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(200);
        passwordField.setPromptText("Password");

        CheckBox rememberCheckBox = new CheckBox("Remember My Info");


        // Create a login button
        Button loginButton = new Button("Login");

        centerBox.getChildren().addAll( titleLabel,usernameField,passwordField, rememberCheckBox,loginButton);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            Signin signin = new Signin();
            // Implement your authentication logic here
            if ((boolean) signin.signin(username, password)) {
                usernameField.setText("");
                passwordField.setText("");
                ScreenHome screenhome = new ScreenHome();
                screenhome.getUsername(username);
                screenhome.start(new Stage());
                primaryStage.close();
                // Successful login
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            } else {
                // Failed login
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        });
        Label noAccountLabel = new Label("No existing account?");
        Button signUpButton = new Button("Sign Up");

        HBox signUpBox = new HBox(10);
        signUpBox.setAlignment(javafx.geometry.Pos.CENTER);
        signUpBox.getChildren().addAll(noAccountLabel, signUpButton);

        // Handle Sign Up button click
        signUpButton.setOnAction(e -> {
            // Implement code to navigate to the sign-up screen or handle sign-up logic
            ScreenSignup screensignup = new ScreenSignup();
            screensignup.start(new Stage());
            System.out.println("Sign Up button clicked!");
        });

        // Add the sign-up components to the center VBox
        centerBox.getChildren().addAll(noAccountLabel, signUpButton);

        

        // Set the center VBox as the center of the BorderPane
        root.setCenter(centerBox);

        // Create the scene and set it in the stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Secure Vault");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);    
        alert.showAndWait();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}