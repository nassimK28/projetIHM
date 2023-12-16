package application;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BDDLivres {

	static Livre selectedBook;
	private static String uri = "mongodb+srv://moshin:cQzC88jHVRKq734y@cluster0.yrxetap.mongodb.net/";
	private static List<Livre> livreList;

	public static void main(String[] args) {
		BDDLivres bddLivres = new BDDLivres();
		livreList = bddLivres.getLivresFromMongoDB();

		for (Livre book : livreList) {
			System.out.println("Le livre est : ");
			System.out.println("Title: " + book.getTitle());
			System.out.println("Author(s): " + book.getAuthor());
			System.out.println("Genre(s): " + book.getGenre());
			System.out.println("Publication Date: " + book.getPublicationDate());
			System.out.println("Language: " + book.getLanguage());
			System.out.println("ISBN: " + book.getISBN());
			System.out.println("Description: " + book.getDescription());
			System.out.println("Cover Image: " + book.getCoverImage());
			System.out.println("------------------------");
		}

		// Example of using get3RandomBooks
		bddLivres.setSelectedBook(livreList.get(0)); // Set a selected book for testing
		List<Livre> threeRandomBooks = bddLivres.getThreeRandomBooks(selectedBook);
		System.out.println("Three Random Books: ");
		for (Livre book : threeRandomBooks) {
			System.out.println(book.getTitle());
		}
	}

	public static List<Livre> getLivresFromMongoDB() {
		List<Livre> livreList = new ArrayList<>(); // Declare and initialize the list

		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("TMUL_DB");
			MongoCollection<Document> collection = database.getCollection("books");

			for (Document doc : collection.find()) {
				Livre livre = null;
				try {
					livre = convertDocumentToLivre(doc);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				livreList.add(livre);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return livreList;
	}

	private static Livre convertDocumentToLivre(Document doc) {
		String title = doc.getString("title");
		List<String> author = doc.getList("author", String.class);
		List<String> genre = doc.getList("genre", String.class);
		String publicationDate = doc.getDate("publicationDate") != null ? doc.getDate("publicationDate").toString()
				: null;
		String language = doc.getString("language");
		String ISBN = doc.getString("customId");

		// Adding description and coverImage
		String description = doc.getString("description");
		Document coverImageObject = (Document) doc.get("coverImage");
		String coverImage = coverImageObject != null ? coverImageObject.getString("big") : null;

		return new Livre(title, author, genre, publicationDate, language, ISBN, description, coverImage);
	}

	public List<Livre> getThreeRandomBooks(Livre selectedBook) {
		List<Livre> allBooks = getLivresFromMongoDB();

		// Remove the selectedBook from the list
		allBooks.remove(selectedBook);

		List<Livre> threeRandomBooks = new ArrayList<>();

		if (allBooks.size() >= 3) {
			// Shuffle the list of remaining books
			Collections.shuffle(allBooks);

			// Get the first three books from the shuffled list
			threeRandomBooks = new ArrayList<>(allBooks.subList(0, 3));
		} else {
			// If there are fewer than three remaining books, return all remaining books
			threeRandomBooks = new ArrayList<>(allBooks);
		}

		return threeRandomBooks;
	}

	public Livre setSelectedBook(Livre book) {
		System.out.println("Le livre choisi a été changé");
		return BDDLivres.selectedBook = book;
	}

	public static List<Livre> getLivreList() {
		System.out.println("Les livres sont : " + livreList);
		return livreList;
	}

	public static class Livre {
		private String title;
		private List<String> author;
		private List<String> genre;
		private String publicationDate;
		private String language;
		private String ISBN;
		private String description;
		private String coverImage;

		public Livre(String title, List<String> author, List<String> genre, String publicationDate, String language,
				String ISBN, String description, String coverImage) {
			this.title = title;
			this.author = author;
			this.genre = genre;
			this.publicationDate = publicationDate;
			this.language = language;
			this.ISBN = ISBN;
			this.description = description;
			this.coverImage = coverImage;
		}

		public String getTitle() {
			return title;
		}

		public List<String> getAuthor() {
			return author;
		}

		public List<String> getGenre() {
			return genre;
		}

		public String getPublicationDate() {
			if (publicationDate != null) {
				DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy",
						Locale.US);
				DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.FRENCH);

				try {
					LocalDate date = LocalDate.parse(publicationDate, originalFormatter);
					return date.format(targetFormatter);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
				}
			}

			return "Inconnue";
		}

		public String getLanguage() {
			return language;
		}

		public String getISBN() {
			return ISBN;
		}

		public String getDescription() {
			return description != null ? description
					: "Oh non ... Nous n'avons pas trouvé de description pour ce livre. Revenez plus tard !";
		}

		public String getCoverImage() {
			return coverImage;
		}

		@Override
		public String toString() {
			return "Livre{" + "title='" + title + '\'' + ", author=" + author + ", genre=" + genre
					+ ", publicationDate='" + publicationDate + '\'' + ", language='" + language + '\'' + ", ISBN='"
					+ ISBN + '\'' + ", description='" + description + '\'' + ", coverImage='" + coverImage + '\'' + '}';
		}
	}

	public Livre getSelectedBook() {
		return BDDLivres.selectedBook;
	}

	public List<String> extractTitles(List<Livre> livreList) {
		List<String> titles = new ArrayList<>();
		for (Livre livre : livreList) {
			titles.add(livre.getTitle());
		}
		return titles;
	}

	public void updateSelectedBook(Livre book) {
		setSelectedBook(book);
	}

	public Livre getBookByTitle(String title) {
		List<Livre> allBooks = getLivresFromMongoDB();

		for (Livre book : allBooks) {
			if (book.getTitle().equals(title)) {
				return book;
			}
		}

		return null; // Book not found
	}
}