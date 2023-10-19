import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class showScreen extends Application {
    public String username;
    public String selected_user;

    public void getUsername(String username, String selected_user) {
        this.username = username;
        this.selected_user = selected_user;
        System.out.println(username);
        System.out.println(selected_user);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a VBox to hold each line (label and buttons)
        VBox root = new VBox(10); // 10 pixel spacing
        root.setPrefSize(500, 500);
        root.setAlignment(Pos.TOP_LEFT);

        // Create labels and buttons for multiple lines
        for (int i = 1; i <= 3; i++) {
            // Create an HBox for each line
            HBox line = new HBox(10); // 10 pixel spacing
            line.setAlignment(Pos.CENTER_LEFT);

            // Create a label for the line
            Label label = new Label("Label " + i);

            // Create buttons for the line
            Button addButton = new Button("Add");
            addButton.setGraphic(new Label("+")); // Icon for "Add"
            Button deleteButton = new Button("Delete");
            deleteButton.setGraphic(new Label("-")); // Icon for "Delete"

            // Add the label and buttons to the line
            line.getChildren().addAll(label, addButton, deleteButton);

            // Add the line to the root VBox
            root.getChildren().add(line);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Label and Button Layout");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
