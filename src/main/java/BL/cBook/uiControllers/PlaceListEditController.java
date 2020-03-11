package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.enums.Place;
import BL.cBook.model.Calf;
import BL.cBook.model.PlaceOfHabitat;
import BL.cBook.services.MyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import java.time.LocalDate;

public class PlaceListEditController implements MyController {

    @FXML private Button confirm;
    @FXML private TableView<PlaceOfHabitat> table;
    @FXML private TableColumn<PlaceOfHabitat, LocalDate> pStartDateColumn;
    @FXML private TableColumn<PlaceOfHabitat, LocalDate> pEndDateColumn;
    @FXML private TableColumn<PlaceOfHabitat, String> placeOfHabitatColumn;
    @FXML private TableColumn<PlaceOfHabitat, String> grazingFieldColumn;

    private PlaceOfHabitat placeOfHabitat;
    private ObservableList<PlaceOfHabitat> list;
    private MainController mainController;

    @FXML public void initialize(MainController msc) {

        this.mainController = msc;
        placeOfHabitat = new PlaceOfHabitat();
        list = FXCollections.observableArrayList();
        table.setEditable(true);
        setPlace();
        setCellsEdit();
        Tools.Initializer.initializePlaceOfHabitatFields(pStartDateColumn, pEndDateColumn,
                placeOfHabitatColumn, grazingFieldColumn);

    }

    private void setPlace() {

        placeOfHabitat.getStartDate().set(LocalDate.now());
        placeOfHabitat.getEndDate().set(LocalDate.now());
        list.add(placeOfHabitat);
        table.setItems(list);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setCellsEdit() {
        placeOfHabitatColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Place.getAllValues()));
        grazingFieldColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addStartDate();
        addEndDate();
    }

    private void addStartDate() {

        Callback<TableColumn<PlaceOfHabitat, LocalDate>, TableCell<PlaceOfHabitat, LocalDate>> cellFactory = new Callback<TableColumn<PlaceOfHabitat, LocalDate>, TableCell<PlaceOfHabitat, LocalDate>>() {
            @Override
            public TableCell<PlaceOfHabitat, LocalDate> call(final TableColumn<PlaceOfHabitat, LocalDate> param) {

                return new TableCell<PlaceOfHabitat, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> placeOfHabitat.getStartDate().set(btn.getValue()));
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        pStartDateColumn.setCellFactory(cellFactory);
    }

    private void addEndDate() {
        Callback<TableColumn<PlaceOfHabitat, LocalDate>, TableCell<PlaceOfHabitat, LocalDate>> cellFactory = new Callback<TableColumn<PlaceOfHabitat, LocalDate>, TableCell<PlaceOfHabitat, LocalDate>>() {
            @Override
            public TableCell<PlaceOfHabitat, LocalDate> call(final TableColumn<PlaceOfHabitat, LocalDate> param) {
                return new TableCell<PlaceOfHabitat, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> placeOfHabitat.getEndDate().set(btn.getValue()));
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        pEndDateColumn.setCellFactory(cellFactory);
    }

    @FXML private void handleConfirm() {

        for (Calf c : mainController.getEditList()) {

            if (c.getAlive().get()) {
                int length = c.getPlaces().size();
                PlaceOfHabitat place = new PlaceOfHabitat();

                place.getPlace().set(placeOfHabitat.getPlace().getValue());
                place.getGrazing_field_number().set(placeOfHabitat.getGrazing_field_number().getValue());
                place.getStartDate().set(placeOfHabitat.getStartDate().getValue());
                place.getEndDate().set(placeOfHabitat.getEndDate().getValue());

                if (length > 0) {
                    c.getPlaces().get(length - 1).getEndDate().set(place.getStartDate().getValue());
                    c.getPlaces().add(place);
                } else {
                    c.getPlaces().add(place);
                }
                confirm.getScene().getWindow().hide();
            }
        }
        Tools.updateFeed(mainController.getCalves(),mainController.getFeedControllersList());
    }
}
