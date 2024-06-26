module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires java.sql;

    requires com.jfoenix;
    requires com.google.gson;

    opens at.ac.fhcampuswien.fhmdb.database to ormlite.jdbc;
    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.models;
    opens at.ac.fhcampuswien.fhmdb.models to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.factory;
    opens at.ac.fhcampuswien.fhmdb.factory to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.builder;
    opens at.ac.fhcampuswien.fhmdb.builder to com.google.gson, javafx.fxml;
}