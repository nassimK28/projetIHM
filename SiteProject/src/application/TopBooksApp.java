package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TopBooksApp extends Application {
	
    // chemin de répértoire qui contient les images
    private static final String IMAGES_DIRECTORY = "/images/";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Top 10 des livres préférés");
         
        // Chargement de la liste des livres
        List<Book> books = loadBooks();

        // Créer une VBox pour organiser les livres
        VBox booksVBox = createBooksVBox(books);

        // Placer la VBox dans un ScrollPane pour permettre le défilement
        ScrollPane scrollPane = new ScrollPane(booksVBox);
        scrollPane.setFitToWidth(true);

        // Créer une VBox pour organiser le titre et le ScrollPane
        VBox root = new VBox();
        root.getChildren().addAll(createTitleLabel(), scrollPane);
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 400);

        // Charger la feuille de style CSS
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    // Méthode pour charger la liste des livres
    private List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        List<Image> images = loadImagesFromDirectory(IMAGES_DIRECTORY);

        // Ecrire le résumé, le titre et l'auteur de chaque livre
        String[] summaries = {
                "Santiago, un jeune berger andalou, part à la recherche d'un trésor enfoui au pied des Pyramides. Lorsqu'il rencontre l'Alchimiste dans le désert, celui-ci lui apprend à écouter son coeur,"
                + " à lire les signes du destin et, par-dessus tout, à aller au bout de ses rêves.",
                "Selon Dale Carnegie, il est plus important d'écouter que de parler lorsque vous souhaitez gagner la confiance de quelqu'un. Les humains sont toujours intéressés à parler d'eux-mêmes et"
                + " c'est pourquoi nous aimons rencontrer quelqu'un qui veut nous entendre.",
                "Le livre met en avant le pouvoir de la pensée positive, de la planification et de la persévérance pour atteindre la richesse financière. Hill souligne l'importance de fixer des objectifs clairs,"
                + " de développer une mentalité prospère et de cultiver des relations positives. En résumé, le livre encourage les lecteurs à utiliser leur esprit de manière stratégique pour créer la richesse et le succès dans leur vie.",
                "Le livre Père Riche, Père Pauvre met en scène l'histoire de deux pères, l'un bardé de diplômes, l'autre titulaire d'un Bac – 2. Croyant jouir de conditions idéales, le père sur-diplômé laissera à sa mort un héritage proche"
                + " du néant avec même quelques factures impayées par ci par là.",
                "Robert Greene suggère de se construire une réputation fondée sur de solides qualités (la générosité, l'honnêteté, l'humilité). En parallèle, il conseille aussi d'apprendre à détruire ses ennemis par leur réputation."
                + " Il suffit d'ouvrir une brèche, puis de se taire et laisser faire la meute.",
                "Cyrano de Bergerac est un mousquetaire de la compagnie des Cadets de Gascogne. Il est amoureux de sa cousine Roxane. Il ne lui dit rien car il se trouve très laid à cause de son nez difforme. Il apprend que Roxane"
                + " aime Christian, un beau jeune homme qui va bientôt rejoindre Cyrano chez les Cadets de Gascogne.",
                "Un matin, Ethan reçoit le faire-part de mariage de Céline, la femme qu'il aimait et qu'il a quittée pour se consacrer à sa fulgurante carrière. Une décision qu'il regrette amèrement aujourd'hui. Sur un coup de tête,"
                + " il se rend au mariage et se met au défi de reconquérir la jeune femme.",
                "L'intrigue est centrée sur la vie des cinq filles Bennet, alors qu'elles se lancent dans la quête de l'amour et du partenaire idéal. Les deux personnages principaux sont Elizabeth Bennet et Monsieur Darcy. Malgré"
                + " les préjugés qu'ils ont l'un envers l'autre, les deux jeunes gens finissent par tomber amoureux.",
                "Dans cette romance, nous retrouvons Perséphone Déesse du printemps qui n'a malheureusement aucun pouvoir et vit dans le monde des humains et Hadès Dieu des enfers. Après leur rencontre elle se retrouve liée a"
                + " lui par un contrat. Créer la vie aux enfers si elle ne veut pas y rester. Trouvera-t-elle une solution ?",
                " Le livre L'art de la guerre montre comment la réflexion peut mener à la victoire, comment l'analyse des faiblesses de l'ennemi peut fonder une tactique, si l'on sait les exploiter, et même les aggraver ;"
                + " il met l'accent sur la dimension psychologique du combat, sur le rôle de la ruse et de la fuite."
        };

        String[] authorNames = {
                "PAULO COELHO",
                "DALE CARNEGIE",
                "NAPOLEON HILL",
                "ROBERT T.KIYOSAKI",
                "ROBERT GREENE",
                "EDMOND ROSTAND",
                "GUILLAME MUSSO",
                "JANE AUSTEN",
                "SCARLET ST CLAIR",
                "SUN TZU",
                
        };

        String[] bookNames = {
                "L'ALCHIMISTE",
                "COMMENT SE FAIRE DES AMIS",
                "Réfléchissez et devenez riche",
                "Père riche père pauvre",
                "POWER",
                "CYANO DE BERGERAC",
                "JE REVIENS TE CHERCHER",
                "ORGEUIL ET PREJUGES",
                "HADES & PERSEPHONE",
                "L'ART DE LA GUERRE"
        };

        for (int i = 0; i < Math.min(images.size(), Math.min(summaries.length, Math.min(authorNames.length, bookNames.length))); i++) {
            books.add(new Book(
                    images.get(i),
                    bookNames[i],
                    authorNames[i],
                    summaries[i],
                    authorNames[i],
                    bookNames[i]
            ));
        }

        return books;
    }

    // Méthode pour charger les images depuis le répértoire
    private List<Image> loadImagesFromDirectory(String directoryPath) {
        List<Image> images = new ArrayList<>();

        // récupérer (get) le répértoire
        File directory = new File(getClass().getResource(directoryPath).getPath());
        File[] files = directory.listFiles();

        // chargement des images sous forme .jpg
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
                    images.add(new Image(file.toURI().toString()));
                }
            }
        }

        return images;
    }
    
    // Méthode pour créer un Vbox qui contient les livres

    private VBox createBooksVBox(List<Book> books) {
        VBox booksVBox = new VBox();
        booksVBox.setSpacing(20);

        for (Book book : books) {
            ImageView imageView = new ImageView(book.getImage());
            imageView.setFitWidth(150);
            imageView.setFitHeight(200);

            // Créer un label pour le résumé et ajout d'un espace sous le texte
            Label summaryLabel = new Label("Résumé: " + book.getSummary());
            summaryLabel.setWrapText(true);

            // Ajoutez de l'espace entre le texte et l'image
            summaryLabel.setStyle("-fx-padding: 0 0 10 0;"); 

            Label titleLabel = new Label("Titre: " + book.getTitle());
            Label authorLabel = new Label("Auteur: " + book.getAuthor());

            // Ajustez la taille de la police pour le titre, le nom de l'auteur et le résumé
            titleLabel.setStyle("-fx-font-size: 14px;"); 
            authorLabel.setStyle("-fx-font-size: 12px;"); 
            summaryLabel.setStyle("-fx-font-size: 12px;");

            // infobox permet d'rganiser le titre, l'auteur et le résumé d'une manière verticale
            VBox infoVBox = new VBox(titleLabel, authorLabel, summaryLabel);
            infoVBox.getStyleClass().add("info-box");

            // Ajoutez un espace supplémentaire entre l'image et le texte
            infoVBox.setStyle("-fx-spacing: 60px;"); 

            HBox bookBox = new HBox(imageView, infoVBox);
            bookBox.getStyleClass().add("book-box");

            // marges pour créer de l'espace autour de l'image et du texte
            VBox.setMargin(imageView, new Insets(10, 0, 10, 0));
            VBox.setMargin(infoVBox, new Insets(0, 0, 0, 20));

            booksVBox.getChildren().add(bookBox);
        }

        return booksVBox;
    }


    private Label createTitleLabel() {
        Label titleLabel = new Label("TOP 10 des livres préférés");
        titleLabel.getStyleClass().add("title-label");
        return titleLabel;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Classe pour représenter un livre avec une image, un titre, un auteur, un résumé, le nom de l'auteur et le nom du livre
    private static class Book {
        private final Image image;
        private final String title;
        private final String author;
        private final String summary;
        private final String authorName;
        private final String bookName;

        public Book(Image image, String title, String author, String summary, String authorName, String bookName) {
            this.image = image;
            this.title = title;
            this.author = author;
            this.summary = summary;
            this.authorName = authorName;
            this.bookName = bookName;
        }

        public Image getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getSummary() {
            return summary;
        }
    }
}