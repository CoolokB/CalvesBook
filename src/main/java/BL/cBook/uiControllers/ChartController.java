package BL.cBook.uiControllers;

import BL.cBook.enums.PlaceOfBirth;
import BL.cBook.model.Calf;
import BL.cBook.model.Treatment;
import BL.cBook.services.MyControllerWithParameter;
import BL.cBook.utils.MyPrinter;
import javafx.fxml.FXML;
import javafx.print.PageOrientation;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChartController implements MyControllerWithParameter<ParametersContainer> {

    @FXML private Pane pane;
    @FXML private Button print;
    @FXML private NumberAxis x;
    @FXML private NumberAxis y;
    @FXML private ScatterChart chart;

    private static int button;

    @FXML public void initialize(MainController mc,ParametersContainer container) {

        x.setAutoRanging(false);
        x.setTickUnit(100);
        y.setAutoRanging(false);
        y.setTickUnit(100);


        print.setOnAction(event -> MyPrinter.print(pane, print.getScene(), PageOrientation.LANDSCAPE));

        button = container.getChartButton();
        LocalDate from = container.getChartFrom();
        LocalDate to = container.getChartTo();
        Set<String> set = container.getChartSet();

        switch (button) {
            case 1: {
                x.setLabel("age at first calving");
                y.setLabel("90 days");
                x.setLowerBound(600);
                x.setUpperBound(1200);
                y.setLowerBound(1000);
                y.setUpperBound(4000);
            }
            break;

            case 2: {
                x.setLowerBound(700);
                x.setUpperBound(1400);
                y.setLowerBound(200);
                y.setUpperBound(400);
                x.setLabel("age at slaughter");
                y.setLabel("slaughter weight");
            }
            break;
        }

        makeChart(mc, from, to, set, button);

    }

    private void makeChart(MainController mainController, LocalDate from, LocalDate to, Set<String> set, int option) {

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();
        XYChart.Series series5 = new XYChart.Series();
        XYChart.Series series6 = new XYChart.Series();
        XYChart.Series series7 = new XYChart.Series();
        XYChart.Series series8 = new XYChart.Series();

        List<XYChart.Series> list = new ArrayList<>();
        Collections.addAll(list, series1, series2, series3, series4, series5, series6, series7, series8);

        List<String> list1 = new ArrayList<>(set);
        List<XYChart.Series> list2 = new ArrayList<>(list.subList(0, set.size()));


        List<Calf> targetList = mainController.getCalves().stream().filter(c -> {

            boolean first = c.getDateOfBirth().getValue().isEqual(from) || c.getDateOfBirth().getValue().isAfter(from);
            boolean second = c.getDateOfBirth().getValue().isEqual(to) || c.getDateOfBirth().getValue().isBefore(to);

            return first && second;


        }).collect(Collectors.toList());

        List<Calf> tempList = new ArrayList<>();

        switch (option) {
            case 1: {

                tempList = targetList.stream().filter(c -> {
                    for (Treatment treatment : c.getTreatment()) {
                        if (treatment.getOccurrence().getValue().toLowerCase().contains("first calving")) {
                            return true;
                        }
                    }
                    return false;
                }).filter(c -> {

                    String age = "0";

                    for (Treatment treatment : c.getTreatment()) {
                        if (treatment.getOccurrence().getValue().contains("first calving"))
                            age = treatment.getAge().getValue();
                    }

                    return Integer.parseInt(age) >= 600 && Integer.parseInt(age) <= 1200;
                }).
                        filter(calf -> Integer.parseInt(calf.getDay90().getValue()) >= 1000 && Integer.parseInt(calf.getDay90().getValue()) <= 4000).collect(Collectors.toList());
                break;
            }
            case 2: {

                tempList = targetList.stream().filter(c -> !c.getAlive().get()).filter(c -> {

                    String age = c.ageProperty().getValue();
                    return Integer.parseInt(age) >= 700 && Integer.parseInt(age) <= 1400;
                }).
                        filter(calf -> Integer.parseInt(calf.getDeathInfo().getSlaughterWeight().getValue()) >= 200 && Integer.parseInt(calf.getDeathInfo().getSlaughterWeight().getValue()) <= 400).collect(Collectors.toList());
                break;
            }
        }

        List<Calf> diarrhoea = tempList.stream().filter(c -> {
            for (Treatment treatment : c.getTreatment()) {
                if (treatment.getOccurrence().getValue().toLowerCase().contains("diar")) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<Calf> pneumonia = tempList.stream().filter(c -> {
            for (Treatment treatment : c.getTreatment()) {
                if (treatment.getOccurrence().getValue().toLowerCase().contains("pneumonia")) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<Calf> DiarrhoeaAndPneumonia = tempList.stream().filter(c -> {
            for (Treatment treatment : c.getTreatment()) {
                if (treatment.getOccurrence().getValue().toLowerCase().contains("pneumonia")) {
                    return true;
                }
            }
            return false;
        }).filter(calf -> {
            for (Treatment treatment : calf.getTreatment()) {
                if (treatment.getOccurrence().getValue().toLowerCase().contains("diar")) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<Calf> none = tempList.stream().filter(c -> {
            for (Treatment treatment : c.getTreatment()) {
                if (treatment.getOccurrence().getValue().toLowerCase().contains("pneumonia")) {
                    return false;
                }
            }
            return true;
        }).filter(calf -> {
            for (Treatment treatment : calf.getTreatment()) {
                if (treatment.getOccurrence().getValue().toLowerCase().contains("diar")) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        List<Calf> unknown = tempList.stream().filter(calf -> calf.getPlaceOfBirth().getValue().equals(PlaceOfBirth.UNKNOWN.getText())).collect(Collectors.toList());
        List<Calf> calvingBox = tempList.stream().filter(calf -> calf.getPlaceOfBirth().getValue().equals(PlaceOfBirth.CALVING_BOX.getText())).collect(Collectors.toList());
        List<Calf> sickBox = tempList.stream().filter(calf -> calf.getPlaceOfBirth().getValue().equals(PlaceOfBirth.SICK_BOX.getText())).collect(Collectors.toList());
        List<Calf> COWSHED_PASSAGE = tempList.stream().filter(calf -> calf.getPlaceOfBirth().getValue().equals(PlaceOfBirth.COWSHED_PASSAGE.getText())).collect(Collectors.toList());
        List<Calf> YOUNGSTOCKSHED = tempList.stream().filter(calf -> calf.getPlaceOfBirth().getValue().equals(PlaceOfBirth.YOUNGSTOCKSHED.getText())).collect(Collectors.toList());
        List<Calf> FIELD = tempList.stream().filter(calf -> calf.getPlaceOfBirth().getValue().equals(PlaceOfBirth.FIELD.getText())).collect(Collectors.toList());

        List<Calf> gestationA = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 250 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 260).collect(Collectors.toList());
        List<Calf> gestationB = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 261 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 270).collect(Collectors.toList());
        List<Calf> gestationC = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 271 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 276).collect(Collectors.toList());
        List<Calf> gestationD = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 277 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 283).collect(Collectors.toList());
        List<Calf> gestationE = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 284 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 290).collect(Collectors.toList());
        List<Calf> gestationF = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 291 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 300).collect(Collectors.toList());
        List<Calf> gestationG = tempList.stream().filter(c -> Integer.parseInt(c.getDaysOfGestation().getValue()) >= 301 && Integer.parseInt(c.getDaysOfGestation().getValue()) <= 310).collect(Collectors.toList());

        List<Calf> SLB = tempList.stream().filter(c -> c.getBreed().getValue().equals("SLB")).collect(Collectors.toList());
        List<Calf> SRB = tempList.stream().filter(c -> c.getBreed().getValue().equals("SRB")).collect(Collectors.toList());
        List<Calf> SLBXSRB = tempList.stream().filter(c -> c.getBreed().getValue().equals("SLBXSRB")).collect(Collectors.toList());
        List<Calf> XCHAR = tempList.stream().filter(c -> c.getBreed().getValue().equals("XCHAR")).collect(Collectors.toList());
        List<Calf> XHER = tempList.stream().filter(c -> c.getBreed().getValue().equals("XHER")).collect(Collectors.toList());
        List<Calf> XAA = tempList.stream().filter(c -> c.getBreed().getValue().equals("XAA")).collect(Collectors.toList());
        List<Calf> XJER = tempList.stream().filter(c -> c.getBreed().getValue().equals("XJER")).collect(Collectors.toList());
        List<Calf> XSKB = tempList.stream().filter(c -> c.getBreed().getValue().equals("XSKB")).collect(Collectors.toList());

        List<Calf> male = tempList.stream().filter(c -> c.getGender().getValue().equals("Male")).collect(Collectors.toList());
        List<Calf> femaleSingle = tempList.stream().filter(c -> c.getGender().getValue().equals("Female")).filter(c -> c.getQuantity().getValue().equals("SINGLE")).collect(Collectors.toList());
        List<Calf> femaleTwinOrTriplet = tempList.stream().filter(c -> c.getGender().getValue().equals("Female")).
                filter(c -> c.getQuantity().getValue().equals("TWIN") || c.getQuantity().getValue().equals("TRIPLET")).collect(Collectors.toList());


        for (int i = 0; i < set.size(); i++) {

            list2.get(i).setName(list1.get(i));

            switch (list1.get(i)) {

                case "Diarrhoea":
                    setChartSeries(list2.get(i), diarrhoea);
                    break;
                case "Pneumonia":
                    setChartSeries(list2.get(i), pneumonia);
                    break;
                case "Both":
                    setChartSeries(list2.get(i), DiarrhoeaAndPneumonia);
                    break;
                case "Neither":
                    setChartSeries(list2.get(i), none);
                    break;

                case "UNKNOWN":
                    setChartSeries(list2.get(i), unknown);
                    break;
                case "CALVING_BOX":
                    setChartSeries(list2.get(i), calvingBox);
                    break;
                case "SICK_BOX":
                    setChartSeries(list2.get(i), sickBox);
                    break;
                case "COWSHED_PASSAGE":
                    setChartSeries(list2.get(i), COWSHED_PASSAGE);
                    break;
                case "YOUNGSTOCKSHED":
                    setChartSeries(list2.get(i), YOUNGSTOCKSHED);
                    break;
                case "FIELD":
                    setChartSeries(list2.get(i), FIELD);
                    break;

                case "250-260":
                    setChartSeries(list2.get(i), gestationA);
                    break;
                case "261-270":
                    setChartSeries(list2.get(i), gestationB);
                    break;
                case "271-276":
                    setChartSeries(list2.get(i), gestationC);
                    break;
                case "277-283":
                    setChartSeries(list2.get(i), gestationD);
                    break;
                case "284-290":
                    setChartSeries(list2.get(i), gestationE);
                    break;
                case "291-300":
                    setChartSeries(list2.get(i), gestationF);
                    break;
                case "301-310":
                    setChartSeries(list2.get(i), gestationG);
                    break;

                case "Male":
                    setChartSeries(list2.get(i), male);
                    break;
                case "Female single ":
                    setChartSeries(list2.get(i), femaleSingle);
                    break;
                case "Female twin or triplet ":
                    setChartSeries(list2.get(i), femaleTwinOrTriplet);
                    break;

                case "SLB":
                    setChartSeries(list2.get(i), SLB);
                    break;
                case "SRB":
                    setChartSeries(list2.get(i), SRB);
                    break;
                case "SLBXSRB":
                    setChartSeries(list2.get(i), SLBXSRB);
                    break;
                case "XCHAR":
                    setChartSeries(list2.get(i), XCHAR);
                    break;
                case "XHER":
                    setChartSeries(list2.get(i), XHER);
                    break;
                case "XAA":
                    setChartSeries(list2.get(i), XAA);
                    break;
                case "XJER":
                    setChartSeries(list2.get(i), XJER);
                    break;
                case "XSKB":
                    setChartSeries(list2.get(i), XSKB);
                    break;

            }
        }

        chart.getData().addAll(list2);

    }

    private void setChartSeries(XYChart.Series series, List<Calf> list) {

        switch (button) {
            case 1: {
                list.forEach(c -> {

                    String age = "0";

                    for (Treatment treatment : c.getTreatment()) {
                        if (treatment.getOccurrence().getValue().contains("first calving"))
                            age = treatment.getAge().getValue();
                    }
                    series.getData().add(new XYChart.Data(Integer.parseInt(age), Integer.parseInt(c.getDay90().getValue())));
                });
            }
            break;
            case 2: {
                list.forEach(c -> series.getData().add(new XYChart.Data(Integer.parseInt(c.ageProperty().getValue()), Integer.parseInt(c.getDeathInfo().getSlaughterWeight().getValue()))));
            }
            break;
        }
    }
}