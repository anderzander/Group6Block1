package at.ac.fhcampuswien.fhmdb.factory;

public class MyCtrl {
    private static MyCtrl instance;

    private MyCtrl() {

    }

    // Statische Methode, um die Singleton-Instanz abzurufen
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
