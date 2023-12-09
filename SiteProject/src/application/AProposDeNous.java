package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AProposDeNous extends Application {

    private static final String IMAGES_DIRECTORY = "/members/";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("THE TEAM");

        Label titleLabel = new Label("MIAGISTES DE LA L3 APPRENTISSAGE");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-family: 'Times New Roman';");

        Label subTitleLabel = new Label("Plongez dans l'univers captivant des livres avec nous ! "
                + "Heureux de partager cette aventure littéraire avec vous. Bonne lecture !");
        subTitleLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Times New Roman';");

        //Hbox qui permet de positionner les membres horizontalement
        HBox teamMembersContainer = createTeamMembersBox(
                new TeamMember("Katia DJELLALI", "katiaa.jpg"),
                new TeamMember("Nikita VALENZA", "nikita.jpg"),
                new TeamMember("Jimmy RASOLOSOA", "jimmy.jpg"),
                new TeamMember("Nassim KACI", "nassim.jpg")
        );

        teamMembersContainer.setAlignment(Pos.CENTER);
        
        // Vbox qui place le titre principale, les images et le sous titre verticalement
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lightblue;");
        root.setPadding(new Insets(20));  // Ajout d'une marge autour de la VBox
        root.setSpacing(50);
        root.getChildren().addAll(titleLabel, teamMembersContainer, subTitleLabel);

        Scene scene = new Scene(root, 900, 500);
        scene.setFill(javafx.scene.paint.Color.LIGHTBLUE);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private HBox createTeamMembersBox(TeamMember... members) {
        HBox teamMembersBox = new HBox(20);
        teamMembersBox.setAlignment(Pos.CENTER);

        for (TeamMember member : members) {
            teamMembersBox.getChildren().add(member.getMemberInfo());
        }

        return teamMembersBox;
    }


    private class TeamMember {
        private String name;
        private String imageName;

        public TeamMember(String name, String imageName) {
            this.name = name;
            this.imageName = imageName;
        }

        // méthode pour avoir un membre
        public VBox getMemberInfo() {
            VBox memberInfo = new VBox(10);
            memberInfo.setAlignment(Pos.CENTER);

            Image image = new Image(getClass().getResourceAsStream(IMAGES_DIRECTORY + imageName));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);

            Label nameLabel = new Label(name);
            nameLabel.setStyle("-fx-font-weight: bold;");

            // Ajouter une marge inférieure pour créer un espacement entre les images
            memberInfo.setMargin(imageView, new Insets(0, 0, 10, 30));  // Ajustez la valeur des marges
            memberInfo.getChildren().addAll(imageView, nameLabel);

            return memberInfo;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

