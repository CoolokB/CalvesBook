package BL.cBook.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.function.Predicate;

public class Filter {

    public static <T> ObservableList<T> filter(ObservableList<T> list, Predicate<T> predicate) {

        ObservableList<T> temp = FXCollections.observableArrayList();

        for (T t : list) {

            if (predicate.test(t)) {
                temp.add(t);
            }
        }
        return temp;
    }

    public static <T> ObservableList<T> filter(ObservableList<T> list, List<Predicate<T>> predicates) {

        ObservableList<T> temp = FXCollections.observableArrayList();

        for (T t : list) {

            int count = 0;

            for (Predicate<T> predicate : predicates) {

                if (!predicate.test(t)) {
                    count++;
                }
            }
            if (count < 1) temp.add(t);
        }
        return temp;
    }
}