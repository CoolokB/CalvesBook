package BL.cBook.databaseModel;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
class DBTreatment {

    private String AmountOfMedicine="";
    private String Occurrence="";
    private String NameOfMedicine="";
    private String WithdrawalDaysMilk="";
    private String WithdrawalDaysSlaughter="";
    private String AgeAtTheTimeOfOccurrence="";

    private LocalDate DateOfOccurrence;
    private LocalDate WithdrawalUntilMilk;
    private LocalDate WithdrawalUntilSlaughter;

}
