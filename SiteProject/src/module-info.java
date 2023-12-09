module SiteProject {
	requires javafx.controls;
	requires javafx.web;
	requires javafx.graphics;
	requires javafx.base;
	requires mongo.java.driver;
	
	opens application to javafx.graphics, javafx.fxml;
}
