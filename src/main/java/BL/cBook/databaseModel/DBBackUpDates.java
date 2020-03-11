package BL.cBook.databaseModel;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
class DBBackUpDates {

    @Column(name = "pend")
    private LocalDate PlaceEnd;
    @Column(name = "tmend")
    private LocalDate treatmentMilkEnd;
    @Column(name = "tsend")
    private LocalDate treatmentSlaughterEnd;
    @Column(name = "fend")
    private LocalDate feedingEnd;
    @Column(name = "fmend")
    private LocalDate feedingMilkEnd;
    @Column(name = "fsend")
    private LocalDate feedingSlaughterEnd;

}
