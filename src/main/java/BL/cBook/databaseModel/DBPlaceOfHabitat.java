package BL.cBook.databaseModel;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
class DBPlaceOfHabitat {

    private LocalDate habitatStartDate;
    private LocalDate habitatEndDate;
    private String habitatPlace="";
    private String grazing_field_number="";

}
