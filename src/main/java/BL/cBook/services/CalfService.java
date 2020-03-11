package BL.cBook.services;

import BL.cBook.databaseModel.DBCalf;
import BL.cBook.model.Calf;

import java.util.List;
import java.util.Set;

public interface CalfService {

    void save(DBCalf calf);
    List<DBCalf> findAll();
    void saveAll(List<DBCalf> list);
    void deleteAll();
    void deleteAll(List<DBCalf> list);

}
