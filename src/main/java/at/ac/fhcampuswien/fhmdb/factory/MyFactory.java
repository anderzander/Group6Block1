package at.ac.fhcampuswien.fhmdb.factory;

import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {
    // TODO: create MyCtrl as singleton instance

    public MyFactory(){
        MyCtrl.getInstance();
    }


    @Override
    public Object call(Class<?> aClass) {
// TODO: check if MyCtrl is already instantiated
        try{
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
