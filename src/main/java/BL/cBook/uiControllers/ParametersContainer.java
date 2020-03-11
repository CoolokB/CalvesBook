package BL.cBook.uiControllers;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ParametersContainer {

    private List<List<String>> rowsList;
    private List<String> labelList;

    private String feedPlace;
    private String feedGrazingField;
    private LocalDate feedStartDate;
    private LocalDate feedEndDate;

    private LocalDate treatmentDateFrom;
    private LocalDate treatmentDateTo;
    private String treatmentAgeFrom;
    private String treatmentAgeTo;
    private LocalDate treatmentMilkFrom;
    private LocalDate treatmentMilkTo;
    private LocalDate treatmentSlaughterFrom;
    private LocalDate treatmentSlaughterTo;
    private String occurrence;

    private String placeOfHabitat;
    private String placeGrazingField;
    private LocalDate placeDateFrom;
    private LocalDate placeDateTo;

    private int chartButton;
    private LocalDate chartFrom;
    private LocalDate chartTo;
    private Set<String> chartSet;

    private static ParametersContainer container = new ParametersContainer();

    private ParametersContainer() { }

    public static ParametersContainer getContainer() { return container; }

}
