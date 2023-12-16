package application;

import java.util.List;
import java.util.Random;

import application.BDDLivres.Livre;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LivreDescription extends Application {

	private Livre selectedBook;
	private List<BDDLivres.Livre> threeRandBooks;
	private List<Livre> livreList;

	public LivreDescription(Livre selectedBook) {
		this.selectedBook = selectedBook;
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Livre Description");

		BDDLivres bddLivres = new BDDLivres();
		livreList = bddLivres.getLivresFromMongoDB();
		System.out.println("Livre list : " + livreList);

		// Get three random books
		threeRandBooks = bddLivres.getThreeRandomBooks(selectedBook);
		System.out.println("Le selected book est : " + selectedBook);
		System.out.println("Les trois livres aléatoires sont : " + threeRandBooks);

		// Le layout final
		BorderPane layout = new BorderPane();

		// Le conteneur du Livre description
		HBox bookContainer = new HBox();

		// Le conteneur des commentaires
		VBox commentContainer = new VBox();

		// On crée le premier component Image
		// L'image du livre
		Image book = new Image(selectedBook.getCoverImage());
		ImageView bookCover = new ImageView(book);
		bookCover.setFitWidth(250);
		bookCover.setFitHeight(350);
		FlowPane bookContainerLeft = new FlowPane();
		bookContainerLeft.getChildren().add(bookCover);

		// Le livre avec ses informations
		VBox bookInfo = new VBox();

		Label title = new Label(selectedBook.getTitle());
		title.setStyle("-fx-font-family: 'Lucida Calligraphy'; -fx-font-size: 50px;");

		List<String> authors = selectedBook.getAuthor();
		String authorName = (authors != null && !authors.isEmpty()) ? authors.get(0) : "Unknown";
		Label author = new Label(authorName);
		author.setStyle("-fx-font-size: 16px");

		Text description = new Text(selectedBook.getDescription());
		description.setWrappingWidth(750);
		description.setStyle("-fx-font-family: 'Lucida Fax'; -fx-font-size: 20px;");

		GridPane metaBook = new GridPane();
		metaBook.setHgap(10);
		metaBook.setVgap(5);

		Label genre = new Label("Genre:");
		Label genreAnswer = new Label(selectedBook.getGenre().isEmpty() ? "Unknown" : selectedBook.getGenre().get(0));

		Label datePublished = new Label("Date de publication:");
		Label datePublishedAnswer = new Label(
				selectedBook.getPublicationDate() != null ? selectedBook.getPublicationDate() : "Unknown");

		Label language = new Label("Langue:");
		Label languageAnswer = new Label(selectedBook.getLanguage());

		Label isbn = new Label("Numéro ISBN:");
		Label isbnAnswer = new Label(selectedBook.getISBN());

		Label nbLike = new Label("Nombre de J'aime:");
		Label nbLikeAnswer = new Label("10 👍");

		Label nbComment = new Label("Nombre de Commentaire:");
		Label nbCommentAnswer = new Label("25 ✍️");

		// Bold metadata labels
		genre.setStyle("-fx-font-weight: bold;");
		datePublished.setStyle("-fx-font-weight: bold;");
		language.setStyle("-fx-font-weight: bold;");
		isbn.setStyle("-fx-font-weight: bold;");
		nbLike.setStyle("-fx-font-weight: bold;");
		nbComment.setStyle("-fx-font-weight: bold;");

		metaBook.add(genre, 0, 0);
		metaBook.add(datePublished, 0, 1);
		metaBook.add(language, 0, 2);

		metaBook.add(genreAnswer, 1, 0);
		metaBook.add(datePublishedAnswer, 1, 1);
		metaBook.add(languageAnswer, 1, 2);

		metaBook.add(isbn, 2, 0);
		metaBook.add(nbLike, 2, 1);
		metaBook.add(nbComment, 2, 2);

		metaBook.add(isbnAnswer, 3, 0);
		metaBook.add(nbLikeAnswer, 3, 1);
		metaBook.add(nbCommentAnswer, 3, 2);

		Insets labelInsets = new Insets(5, 10, 5, 0); // Reduced vertical space
		Insets rightLabel = new Insets(5, 0, 5, 10); // Reduced vertical space

		// Apply margins directly to each label
		GridPane.setMargin(genre, labelInsets);
		GridPane.setMargin(datePublished, labelInsets);
		GridPane.setMargin(language, labelInsets);
		GridPane.setMargin(genreAnswer, labelInsets);
		GridPane.setMargin(datePublishedAnswer, labelInsets);
		GridPane.setMargin(languageAnswer, labelInsets);
		GridPane.setMargin(isbn, rightLabel);
		GridPane.setMargin(nbLike, rightLabel);
		GridPane.setMargin(nbComment, rightLabel);
		GridPane.setMargin(isbnAnswer, rightLabel);
		GridPane.setMargin(nbLikeAnswer, rightLabel);
		GridPane.setMargin(nbCommentAnswer, rightLabel);

		bookInfo.getChildren().addAll(title, author, description, metaBook);
		VBox.setMargin(title, new Insets(10, 0, 0, 10));
		VBox.setMargin(author, new Insets(0, 0, 0, 10));
		VBox.setMargin(description, new Insets(10));
		VBox.setMargin(metaBook, new Insets(10));
		BorderPane.setMargin(bookInfo, new Insets(30, 20, 37, 75));
		VBox.setMargin(metaBook, new Insets(50, 0, 0, 10));
		VBox.setMargin(description, new Insets(50, 0, 0, 10));

		// On ajoute les livres du même genre dans un bookContainer de recommendation...
		VBox bookCoverContainer = new VBox();
		for (int i = 0; i < 3; i++) {
			Livre bookDisplayed = threeRandBooks.get(i);
			Image bookImage = new Image(bookDisplayed.getCoverImage());
			ImageView bookCoverC = new ImageView(bookImage);
			bookCoverC.setFitWidth(120);
			bookCoverC.setFitHeight(180);
			bookCoverContainer.getChildren().add(bookCoverC);
			VBox.setMargin(bookCoverC, new Insets(5));
		}

		bookCoverContainer.setPrefHeight(500);

		// Création de la partie commentaire

		Label comment = new Label("Commentaires : 💭");
		comment.setStyle("-fx-font-family: 'Lucida Calligraphy'; -fx-font-size: 25px;");
		TextArea test = new TextArea();
		test.setStyle(
				"-fx-font-family: 'Lucida Fax'; -fx-font-size: 14px; -fx-border-color: #ccc; -fx-border-width: 1px;");
		test.setText("Entrez votre commentaire ...");
		test.setPrefHeight(110);
		Button submit = new Button("Envoyer");
		submit.setStyle(
				"-fx-font-family: 'Lucida Fax'; -fx-font-size: 14px; -fx-text-fill:white; -fx-background-color:gray;");

		commentContainer.getChildren().addAll(comment, test, submit);

		// Add margin to the comment label to create space between the label and the
		// TextArea
		VBox.setMargin(comment, new Insets(0, 0, 10, 0));

		// Adjustments for better visibility of the submit button
		VBox.setMargin(submit, new Insets(10, 0, 0, 0));

		BorderPane.setMargin(commentContainer, new Insets(0, 75, 10, 80));
		BorderPane.setAlignment(commentContainer, Pos.CENTER);

		layout.setLeft(bookContainerLeft);
		layout.setCenter(bookInfo);
		layout.setRight(bookCoverContainer);
		layout.setBottom(commentContainer);

		FlowPane.setMargin(bookCover, new Insets(80));
		BorderPane.setMargin(bookCoverContainer, new Insets(10, 60, 10, 10));

		Scene scene = new Scene(layout, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setSelectedBook(Livre selectedBook) {
		System.out.println("Le livre choisi a été changé");
		BDDLivres.selectedBook = selectedBook;
	}

	private void handleTitleClick(Livre book) {
		BDDLivres bddLivres = new BDDLivres();
		bddLivres.updateSelectedBook(book);
		selectedBook = bddLivres.getSelectedBook();
	}

	private void createTitleLabels(VBox bookCoverContainer) {
		for (int i = 0; i < 3; i++) {
			Livre bookDisplayed = threeRandBooks.get(i);
			Image bookImage = new Image(bookDisplayed.getCoverImage());
			ImageView bookCoverC = new ImageView(bookImage);
			bookCoverC.setFitWidth(120);
			bookCoverC.setFitHeight(180);

			// Create a label for the title and handle the click event
			Label titleLabel = new Label(bookDisplayed.getTitle());
			titleLabel.setStyle("-fx-font-family: 'Lucida Calligraphy'; -fx-font-size: 12px; -fx-underline: true;");

			// Handle title click event
			titleLabel.setOnMouseClicked(event -> handleTitleClick(bookDisplayed));

			VBox bookInfoContainer = new VBox(bookCoverC, titleLabel);
			bookInfoContainer.setAlignment(Pos.CENTER);
			bookCoverContainer.getChildren().add(bookInfoContainer);

			VBox.setMargin(bookInfoContainer, new Insets(5));
		}
	}

	public Livre getSelectedBook() {
		return selectedBook;
	}

	public void showLivreDescription(Stage primaryStage, BDDLivres.Livre selectedLivre) {
		this.selectedBook = selectedLivre;
		start(primaryStage);
	}

}