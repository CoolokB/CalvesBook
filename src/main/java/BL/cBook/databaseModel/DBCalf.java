package BL.cBook.databaseModel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "calf")
public class DBCalf {

    @Id
    @GeneratedValue
    private int id;

    private String cid = "";
    @Column(name = "col")
    private String colostrum_by_bottle = "";
    @Column(name = "qua")
    private String quantity = "";
    @Column(name = "gdays")
    private String daysOfGestation = "0";
    @Column(name = "bdate")
    private LocalDate dateOfBirth;
    @Column(name = "bplace")
    private String placeOfBirth = "";
    @Column(name = "lacnum")
    private String lactationNumber = "";

    private String gender = "";
    private String breed = "";
    @Transient
    private String age = "";
    private boolean alive=true;
    private String day90 = "0";

    private DBDeathInfo dbDeathInfo = new DBDeathInfo();
    private DBBackUpDates backup = new DBBackUpDates();

    @ElementCollection
    @CollectionTable(name = "places")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DBPlaceOfHabitat> dbPlaceHistory = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "treatment")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DBTreatment> dbTreatmentHistory = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "feedhistory")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DBFeedHistory> dbFeedingHistory = new ArrayList<>();

}
