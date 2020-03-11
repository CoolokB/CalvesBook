package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.enums.Place;
import BL.cBook.model.Calf;
import BL.cBook.model.PlaceOfHabitat;
import BL.cBook.services.MyController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;

public class PlaceOfHabitatController implements MyController {

    @FXML private AnchorPane pane;
    @FXML private TableView<PlaceOfHabitat> table;
    @FXML private TableColumn<PlaceOfHabitat, LocalDate> pStartDateColumn;
    @FXML private TableColumn<PlaceOfHabitat, LocalDate> pEndDateColumn;
    @FXML private TableColumn<PlaceOfHabitat, String> placeOfHabitatColumn;
    @FXML private TableColumn<PlaceOfHabitat, String> grazingFieldColumn;

    private ObservableList<PlaceOfHabitat> list;
    private MainController mainController;
    private Calf calf;

    @FXML public void initialize(MainController mc) {

        this.mainController = mc;
        calf = mainController.getEditList().get(0);
        table.setEditable(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list = calf.getPlaces();
        table.setItems(list);
        setCellsEdit();
        Tools.Initializer.initializePlaceOfHabitatFields(pStartDateColumn, pEndDateColumn, placeOfHabitatColumn, grazingFieldColumn);

    }

    private void setCellsEdit() {
        placeOfHabitatColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Place.getAllValues()));
        grazingFieldColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addStartDate();
    }

    private void addStartDate() {

        Callback<TableColumn<PlaceOfHabitat, LocalDate>, TableCell<PlaceOfHabitat, LocalDate>> cellFactory = new Callback<TableColumn<PlaceOfHabitat, LocalDate>, TableCell<PlaceOfHabitat, LocalDate>>() {
            @Override
            public TableCell<PlaceOfHabitat, LocalDate> call(final TableColumn<PlaceOfHabitat, LocalDate> param) {

                return new TableCell<PlaceOfHabitat, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            PlaceOfHabitat place = list.get(getIndex());
                            place.getStartDate().set(btn.getValue());

                            if (list.size() > 1) {
                                list.get(list.size() - 2).getEndDate().set(btn.getValue());
                                setDates();
                                table.refresh();
                            }
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {

                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getStartDate().getValue().toString());
                        }
                    }
                };
            }
        };

        pStartDateColumn.setCellFactory(cellFactory);
    }

    @FXML private void handleDelete() {
        if (table.getSelectionModel().getSelectedIndex() >= 0) {
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        }

    }

    @FXML private void handleAdd() {

        if (calf.getAlive().getValue()) {
            PlaceOfHabitat place = new PlaceOfHabitat();
            LocalDate date;
            try {
                date = list.get(list.size() - 1).getEndDate().getValue();
                place.getStartDate().set(date);
            } catch (Exception e) {
                place.getStartDate().set(LocalDate.now());
            }
            place.getEndDate().set(LocalDate.now());
            list.add(place);
            table.refresh();
        } else Tools.deadCowAlert(this.calf, (Stage) pane.getScene().getWindow());
    }

    @FXML private void accept() {
        Tools.updateFeed(mainController.getCalves(), mainController.getFeedControllersList());
    }

    private void setDates() {
        for (int i = calf.getPlaces().size() - 2; i >= 0; i--) {
            calf.getPlaces().get(i).getEndDate().set(calf.getPlaces().get(i + 1).getStartDate().getValue());
        }
    }
}
