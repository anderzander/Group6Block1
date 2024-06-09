package at.ac.fhcampuswien.fhmdb.factory;

import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {
    // TODO: create MyCtrl as singleton instance


    @Override
    public Object call(Class<?> aClass) {
// TODO: check if MyCtrl is already instantiated
        try{
            return (MyCtrl) aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
