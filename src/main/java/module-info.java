module org.openjfx {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}
