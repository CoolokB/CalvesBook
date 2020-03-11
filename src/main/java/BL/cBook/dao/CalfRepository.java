package BL.cBook.dao;

import BL.cBook.databaseModel.DBCalf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalfRepository extends JpaRepository<DBCalf, Integer> {
}

