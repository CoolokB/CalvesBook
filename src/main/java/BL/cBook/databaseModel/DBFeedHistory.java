package BL.cBook.databaseModel;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
class DBFeedHistory {

    private LocalDate startOfFeeding;
    private LocalDate endOfFeeding;
    private LocalDate withdrawalMilk;
    private LocalDate withdrawalSlaughter;
    private String placeOfFeeding="";
    private String foodType = "Conventional";
    private String field="";

}

