package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.model.FeedControl;
import BL.cBook.services.CalfAndFeedControlService;
import BL.cBook.services.MyController;
import BL.cBook.utils.Filter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import java.time.LocalDate;
import java.util.function.Predicate;

public class FeedControlController implements MyController {

    @FXML private TableView<FeedControl> table;

    @FXML private TableColumn<FeedControl, String> fPlaceColumn;
    @FXML private TableColumn<FeedControl, String> fFieldColumn;
    @FXML private TableColumn<FeedControl, String> fFeedColumn;
    @FXML private TableColumn<FeedControl, LocalDate> fStartDateColumn;
    @FXML private TableColumn<FeedControl, LocalDate> fEndDateColumn;

    @FXML private DatePicker DateFrom;
    @FXML private DatePicker DateTo;
    @FXML private TextField fieldText;

    @FXML private ChoiceBox<String> controlPlace;

    private CalfAndFeedControlService service;
    private MainController mainController;
    private ObservableList<FeedControl> feedList;
    private boolean isFiltered = false;

    @FXML public void initialize(MainController mc) {

        this.mainController = mc;
        service = mc.getService();
        feedList = mc.getFeedControllersList();
        table.setEditable(true);
        table.setItems(feedList);
        setCells();
        setCellsEdit();

    }

    @FXML public void handleConfirm() {
        Tools.updateFeed(mainController.getCalves(),mainController.getFeedControllersList());
    }

    @FXML void handleAdd() {

        FeedControl fc = new FeedControl();
        fc.setStart(LocalDate.now());
        fc.setEnd(LocalDate.now());
        feedList.add(fc);
        table.refresh();
    }

    @FXML void handleDelete() {

        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            service.deleteAllFeed(table.getSelectionModel().getSelectedItems());
            table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
        }
    }

    private void setCells() {
        fStartDateColumn.setCellValueFactory(s -> s.getValue().startProperty());
        fEndDateColumn.setCellValueFactory(s -> s.getValue().endProperty());
        fPlaceColumn.setCellValueFactory(p -> p.getValue().placeProperty());
        fFeedColumn.setCellValueFactory(g -> g.getValue().foodTypeProperty());
        fFieldColumn.setCellValueFactory(f -> f.getValue().fieldProperty());
        controlPlace.setItems(BL.cBook.enums.Place.getAllValues());
    }

    private void addEndDate() {
        Callback<TableColumn<FeedControl, LocalDate>, TableCell<FeedControl, LocalDate>> cellFactory = new Callback<TableColumn<FeedControl, LocalDate>, TableCell<FeedControl, LocalDate>>() {
            @Override
            public TableCell<FeedControl, LocalDate> call(final TableColumn<FeedControl, LocalDate> param) {
                return new TableCell<FeedControl, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FeedControl place = getTableView().getItems().get(getIndex());
                            place.setEnd((btn.getValue()));
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getEnd().toString());
                        }
                    }
                };
            }
        };

        fEndDateColumn.setCellFactory(cellFactory);
    }

    private void setCellsEdit() {

        fPlaceColumn.setCellFactory(ComboBoxTableCell.forTableColumn(BL.cBook.enums.Place.getAllValues()));
        fFieldColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addStartDate();
        addEndDate();
    }

    private void addStartDate() {

        Callback<TableColumn<FeedControl, LocalDate>, TableCell<FeedControl, LocalDate>> cellFactory = new Callback<TableColumn<FeedControl, LocalDate>, TableCell<FeedControl, LocalDate>>() {
            @Override
            public TableCell<FeedControl, LocalDate> call(final TableColumn<FeedControl, LocalDate> param) {

                return new TableCell<FeedControl, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FeedControl place = getTableView().getItems().get(getIndex());
                            place.setStart(btn.getValue());
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {

                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getStart().toString());
                        }
                    }
                };
            }
        };

        fStartDateColumn.setCellFactory(cellFactory);
    }

    @FXML private void filter() {

        Predicate<FeedControl> feedControlPredicate = feedControl -> {

            boolean condition1;
            if (DateFrom.getValue() != null) {
                condition1 = DateFrom.getValue().isBefore(feedControl.getEnd()) || DateFrom.getValue().isEqual(feedControl.getEnd());
            } else {
                condition1 = true;
            }
            boolean condition2;
            if (DateTo.getValue() != null) {
                condition2 = DateTo.getValue().isEqual(feedControl.getStart()) || DateTo.getValue().isAfter(feedControl.getStart());
            } else {
                condition2 = true;
            }
            boolean condition3;
            if (controlPlace.getValue() != null) {
                condition3 = feedControl.getPlace().equals(controlPlace.getValue());
            } else {
                condition3 = true;
            }
            boolean condition4;
            if (!fieldText.getText().equals("")) {
                condition4 = feedControl.getField().equals(fieldText.getText());
            } else {
                condition4 = true;
            }

            return condition1 & condition2 & condition3 & condition4;

        };

        feedList = (Filter.filter(mainController.getFeedControllersList(), feedControlPredicate));
        table.setItems(feedList);
        isFiltered = true;

    }

    @FXML private void reset() {

        DateTo.setValue(null);
        DateFrom.setValue(null);
        controlPlace.setItems(BL.cBook.enums.Place.getAllValues());
        fieldText.clear();

        if (isFiltered) {
            feedList.clear();
        }
    }
}



