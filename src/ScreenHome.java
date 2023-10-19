import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ScreenHome extends Application {
    public String username;
    public String member;
    public String selected_user;
    

    public static void main(String[] args) {
        launch(args);
    }

    public void getUsername(String username) {
        this.username = username;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        primaryStage.setTitle("Family Member App");

        // Create a ComboBox for family members
        ComboBox<String> familyMembers = new ComboBox<>();
        familyMembers.setMaxWidth(200);
        familyMembers.setPromptText("Select Family Member");

        List<String> myList = new ArrayList<>();

        members_name_Fetch member_list = new members_name_Fetch();
        myList = member_list.getListOfStrings(username);

        for (String item : myList) {
            familyMembers.getItems().add(item);
        }
        // Create an "Add" button
        Button addButton = new Button("Add Family Member");
        addButton.setMaxWidth(200);
        addButton.setOnAction(e -> {
            // Show a dialog to enter the family member's name
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Family Member");
            dialog.setHeaderText("Enter Family Member's Name:");
            dialog.setContentText("Name:");
            String userName = username;
            // Get the result of the dialog
            dialog.showAndWait().ifPresent(result -> {
                String name = result.trim();
                if (!name.isEmpty()) {
                    member = name;
                    familyMembers.getItems().add(name);
                    members_add members = new members_add();
                    boolean resu = members.add_member(userName, member);
                    System.out.println(resu);
                }
            });
        });

        Button nextbutton = new Button("Next");
        nextbutton.setMaxWidth(200);

        nextbutton.setOnAction(e -> {
            selected_user = familyMembers.getValue();
            System.out.println("Next Button Clicked");
            showScreen userscreen = new showScreen();
            userscreen.getUsername(username, selected_user);
            userscreen.start(new Stage());
            primaryStage.close();
        });

        // Create a VBox to hold the ComboBox and "Add" button
        VBox vbox = new VBox(10, familyMembers, addButton, nextbutton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        // Create the scene and set it in the stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
