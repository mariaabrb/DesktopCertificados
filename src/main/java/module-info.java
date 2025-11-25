module org.example.certifyproadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.security.crypto;
    requires static lombok;

    exports org.example.certifyproadmin;
    opens org.example.certifyproadmin to javafx.fxml;
    exports org.example.certifyproadmin.controller;
    opens org.example.certifyproadmin.controller to javafx.fxml;
    opens org.example.certifyproadmin.model to com.google.gson, org.hibernate.orm.core;
}