package BL.cBook.services;

import BL.cBook.uiControllers.MainController;

public interface MyControllerWithParameter<T> {
    void initialize(MainController mc,T t);
}
