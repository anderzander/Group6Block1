package at.ac.fhcampuswien.fhmdb.factory;

import at.ac.fhcampuswien.fhmdb.HomeController;
import javafx.fxml.FXMLLoader;

public class MyCtrl {
    private static MyCtrl instance;

    private FXMLLoader loader;

    private MyCtrl() {

    }

    public static MyCtrl getInstance() {
        if (instance == null) {
            synchronized (MyCtrl.class) {
                if (instance == null) {
                    instance = new MyCtrl();
                }
            }
        }
        return instance;
    }

}
