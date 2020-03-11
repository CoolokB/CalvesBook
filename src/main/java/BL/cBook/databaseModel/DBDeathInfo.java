package BL.cBook.databaseModel;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
class DBDeathInfo {

    @Column(name = "ffield1")
    private String fatClassField1="";
    @Column(name = "ffield2")
    private String fatClassField2="";
    @Column(name = "sfield1")
    private String slaughterClassField1="";
    @Column(name = "sfield2")
    private String slaughterClassField2="";
    @Column(name = "cause")
    private String causeOfDeath="";
    @Column(name = "ddate")
    private LocalDate dateOfDeath;
    @Column(name = "weight")
    private String slaughterWeight = "0";
    @Column(name = "reason")
    private String reasonOfDeath="";

}
