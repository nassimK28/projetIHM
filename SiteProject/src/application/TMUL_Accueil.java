package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//pour importer les images
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//pour faire le dégradé
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;

public class TMUL_Accueil extends Application {

    private static final String TRANSPARENT = "transparent";
    
    // Boolean mode sombre/mode normal
    private boolean darkMode = false;
    
    private BorderPane mainContent;  // Declare mainContent as a class variable
    private HBox navigationBar;     // Declare navigationBar as a class variable
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Main layout with BorderPane
            BorderPane root = new BorderPane();

            // Navigation Bar
            navigationBar = createNavigationBar();  // Use class variable
            root.setTop(navigationBar);

            // Main content
            mainContent = createMainContent();  // Use class variable
            root.setCenter(mainContent);

            // Create scene
            Scene scene = new Scene(root, javafx.stage.Screen.getPrimary().getVisualBounds().getWidth(), javafx.stage.Screen.getPrimary().getVisualBounds().getHeight());
           
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methode pour avoir le mode sombre
    private void toggleDarkMode() {
        darkMode = !darkMode;

        if (darkMode) {
            // appliquer le mode sombre
            mainContent.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            navigationBar.setStyle("-fx-background-color: darkblue;");
 
        } else {
            // Revenir au mode normal
            mainContent.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #87CEEB, #4682B4); -fx-text-fill: black;");
            navigationBar.setStyle("-fx-background-color: lightblue;");
        }
    }

    // Boutons de la barre de navigation
    private HBox createNavigationBar() {
        HBox navigationBar = new HBox();
        Button livres = createButton("Top 10 des livres");
        Button deNous = createButton("A propos de nous");
        Button boutique = createButton("Boutique");
        Button blog = createButton("Blog");
        Button seConnecter = createButton("Se connecter");

        // Évènements associés aux boutons de la barre de navigation
        livres.addEventHandler(MouseEvent.MOUSE_ENTERED, createMouseEnteredHandler(livres));
        deNous.addEventHandler(MouseEvent.MOUSE_ENTERED, createMouseEnteredHandler(deNous));
        boutique.addEventHandler(MouseEvent.MOUSE_ENTERED, createMouseEnteredHandler(boutique));
        blog.addEventHandler(MouseEvent.MOUSE_ENTERED, createMouseEnteredHandler(blog));
        seConnecter.addEventHandler(MouseEvent.MOUSE_ENTERED, createMouseEnteredHandler(seConnecter));

        livres.addEventHandler(MouseEvent.MOUSE_EXITED, createMouseExitedHandler(livres));
        deNous.addEventHandler(MouseEvent.MOUSE_EXITED, createMouseExitedHandler(deNous));
        boutique.addEventHandler(MouseEvent.MOUSE_EXITED, createMouseExitedHandler(boutique));
        blog.addEventHandler(MouseEvent.MOUSE_EXITED, createMouseExitedHandler(blog));
        seConnecter.addEventHandler(MouseEvent.MOUSE_EXITED, createMouseExitedHandler(seConnecter));

        
        // Ajouter l'événement de clic pour le bouton "Top 10 des livres"
        livres.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Ouvrir la classe TopBookApp
                TopBooksApp topBookApp = new TopBooksApp();;
                topBookApp.start(new Stage());
            }
        });
        
        deNous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Ouvrir la classe AProposDeNous
                AProposDeNous aproposdenous = new AProposDeNous();
                aproposdenous.start(new Stage());
            }
        });

        
        
        boutique.addEventHandler(MouseEvent.MOUSE_CLICKED, createMouseClickedHandler("https://www.amazon.fr/livre-achat-occasion-litterature-roman/b?ie=UTF8&node=301061"));
        blog.addEventHandler(MouseEvent.MOUSE_CLICKED, createMouseClickedHandler("https://blog.trouvemoiunlivre.com/"));
        seConnecter.addEventHandler(MouseEvent.MOUSE_CLICKED, createMouseClickedHandler("https://www.trouvemoiunlivre.com/AuthForm/Auth"));

        
        //Créer le mode sombre
        Button darkModeButton = createButton("Mode Sombre");
        darkModeButton.addEventHandler(MouseEvent.MOUSE_ENTERED, createMouseEnteredHandler(darkModeButton));
        darkModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toggleDarkMode(); 
            }
        });  
        
        navigationBar.getChildren().addAll(livres, deNous, boutique, blog, seConnecter, darkModeButton);
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setStyle("-fx-background-color: lightblue;");
        navigationBar.setPrefHeight(50); // Ajustez la hauteur selon les besoins
        return navigationBar;
    }

	private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18px; -fx-min-width: 120px; -fx-min-height: 40px;");
        return button;
    }

    private EventHandler<MouseEvent> createMouseEnteredHandler(Button button) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setCursor(Cursor.HAND);
                button.setStyle("-fx-background-color: skyblue;");
            }
        };
    }

    private EventHandler<MouseEvent> createMouseExitedHandler(Button button) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: lightblue;");
            }
        };
    }

    private EventHandler<MouseEvent> createMouseClickedHandler(String url) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getHostServices().showDocument(url);
            }
        };
    }

    // Contenu principal
    private BorderPane  createMainContent() {
    	BorderPane  mainContent = new BorderPane ();
   
        // images du fond :
        // VBox circuit innovation
        VBox leftBox = new VBox(10);
        URL circuitImageUrl = getClass().getResource("/demo/circuit.png");
        ImageView circuitImage = new ImageView(new Image(circuitImageUrl.toString()));
        leftBox.getChildren().add(circuitImage);
        leftBox.setStyle("-fx-background-color: " + TRANSPARENT + ";");

        

        // VBox page déchirée
        VBox rightBox = new VBox(10);
        URL tornPageImageUrl = getClass().getResource("/demo/tornpage.png");
        ImageView tornPageImage = new ImageView(new Image(tornPageImageUrl.toString()));
        rightBox.getChildren().add(tornPageImage);
        rightBox.setStyle("-fx-background-color: " + TRANSPARENT + ";");
        rightBox.setAlignment(Pos.CENTER_RIGHT);


        // Titre et barre de recherche centrale
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Text textLabel = new Text("Quand l'innovation rencontre");
        textLabel.setStyle("-fx-font-size: 30px; -fx-fill: white; -fx-font-family: 'Arial Black';");

        Text secondLine = new Text("la lecture...");
        secondLine.setStyle("-fx-font-size: 30px; -fx-fill: white; -fx-font-family: 'Arial Black';");

        // Barre de recherche 
        VBox vbn = new VBox();
        
        BDDLivres bddLivres = new BDDLivres();
        
        // Récupérer la liste des livres depuis la base de données
     	ObservableList<BDDLivres.Livre> livreObservableList = FXCollections
     				.observableArrayList(bddLivres.getLivresFromMongoDB());

     	// Créer une liste déroulante des titres de livres avec le rendu personnalisé
     	ComboBox<BDDLivres.Livre> livreComboBox = new ComboBox<>(livreObservableList);
     	
     	livreComboBox.setPromptText("Sélectionnez un livre");

		livreComboBox.setCellFactory(new Callback<>() {
			@Override
			public ListCell<BDDLivres.Livre> call(ListView<BDDLivres.Livre> param) {
				return new ListCell<>() {
					@Override
					protected void updateItem(BDDLivres.Livre item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setText(null);
						} else {
							setText(item.getTitle() + " - " + item.getAuthor());
						}
					}
				};
			}
		});
		
		
		EventHandler<MouseEvent> clickLivre = new EventHandler<>() {
			public void handle(MouseEvent event) {
				BDDLivres.Livre selectedLivre = livreComboBox.getSelectionModel().getSelectedItem();
				if (selectedLivre != null) {
					// Get the title of the selected book
					String selectedTitle = selectedLivre.getTitle();

					// Call the getBookByTitle method (assuming it's added in BDDLivres)
					BDDLivres.Livre selectedBookByTitle = bddLivres.getBookByTitle(selectedTitle);

					if (selectedBookByTitle != null) {
						// Launch the LivreDescription stage with the corresponding book data
						System.out.println("Le selected livre est : " + selectedBookByTitle);

						LivreDescription livreDescription = new LivreDescription(selectedBookByTitle);
						livreDescription.start(new Stage());

						// Clear the selection in livreComboBox
						livreComboBox.getSelectionModel().clearSelection();
					} else {
						System.out.println("Le livre avec le titre " + selectedTitle + " n'a pas été trouvé.");
					}
				}
			}
		};


		livreComboBox.addEventHandler(MouseEvent.MOUSE_CLICKED, clickLivre);
		vbn.setAlignment(Pos.CENTER_RIGHT);
		vbn.getChildren().addAll(livreComboBox);	

        // Text label en dessous de la barre
        Text additionalTextLabel = new Text("Tous les livres du monde... À portée de clic !");
        additionalTextLabel.setStyle("-fx-font-size: 20px; -fx-fill: white;");

        centerBox.getChildren().addAll(textLabel, secondLine,  vbn, additionalTextLabel);
        
        //mettre les positions de chaque child du borderPane 
        mainContent.setLeft(leftBox);
        mainContent.setRight(rightBox);
        mainContent.setCenter(centerBox);
        

        // Fond en dégradé avec les couleurs du site
        mainContent.setBackground(new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                        new Stop(0, javafx.scene.paint.Color.web("#87CEEB")),
                        new Stop(1, javafx.scene.paint.Color.web("#4682B4"))), null, null), null));

        //mainContent.setAlignment(Pos.CENTER);
        
        BorderPane.setMargin(mainContent, new Insets(10)); // Adjust the insets as needed

        return mainContent;
    }
    
  

    public static void main(String[] args) {
        launch(args);
    }
}