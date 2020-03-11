package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.model.Calf;
import BL.cBook.model.FeedHistory;
import BL.cBook.services.MyController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;

public class FeedHistoryController implements MyController {

    @FXML private AnchorPane pane;
    @FXML private TableView<FeedHistory> FeedTable;

    @FXML private TableColumn<FeedHistory, String> fPlaceColumn;
    @FXML private TableColumn<FeedHistory, String> fFieldColumn;
    @FXML private TableColumn<FeedHistory, String> fFeedColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fStartDateColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fEndDateColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fWithdrawalUntilMilkColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fWithdrawalUntilSlaughterColumn;

    private Calf calf;
    private ObservableList<FeedHistory> list;

    @FXML
    void handleAdd() {

        if (calf.getAlive().getValue()) {
            FeedHistory feedHistory = new FeedHistory();
            feedHistory.getStart().set(LocalDate.now());
            feedHistory.getEnd().set(LocalDate.now());
            feedHistory.getWithdrawalMilk().set(LocalDate.now());
            feedHistory.getWithdrawalSlaughter().set(LocalDate.now());
            calf.getFeedingHistory().add(feedHistory);
            FeedTable.refresh();

        } else Tools.deadCowAlert(calf, (Stage) pane.getScene().getWindow());

    }

    @FXML
    void handleDelete() {

        int selectedIndex = FeedTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            FeedTable.getItems().removeAll(FeedTable.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    public void initialize(MainController mc) {

        calf = mc.getEditList().get(0);
        list = calf.getFeedingHistory();
        FeedTable.setItems(list);
        FeedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        FeedTable.setEditable(true);
        Tools.Initializer.initializeFeedFields(fPlaceColumn, fFieldColumn, fFeedColumn, fStartDateColumn,
                fEndDateColumn, fWithdrawalUntilMilkColumn, fWithdrawalUntilSlaughterColumn);

        setCellsEdit();

    }

    private void setCellsEdit() {

        fPlaceColumn.setCellFactory(ComboBoxTableCell.forTableColumn(BL.cBook.enums.Place.getAllValues()));
        fFeedColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        addStartDate();
        addEndDate();
        addMilkDate();
        addSlaughterDate();
    }

    private void addStartDate() {

        Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>> cellFactory = new Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>>() {
            @Override
            public TableCell<FeedHistory, LocalDate> call(final TableColumn<FeedHistory, LocalDate> param) {

                return new TableCell<FeedHistory, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FeedHistory fh = getTableView().getItems().get(getIndex());
                            fh.getStart().set(btn.getValue());
                            if (list.size() > 1) {
                                FeedTable.refresh();
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
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getStart().getValue().toString());
                        }
                    }
                };
            }
        };

        fStartDateColumn.setCellFactory(cellFactory);
    }

    private void addEndDate() {
        Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>> cellFactory = new Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>>() {
            @Override
            public TableCell<FeedHistory, LocalDate> call(final TableColumn<FeedHistory, LocalDate> param) {
                return new TableCell<FeedHistory, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FeedHistory fh = getTableView().getItems().get(getIndex());
                            fh.getEnd().set((btn.getValue()));
                            fh.getWithdrawalSlaughter().set(btn.getValue().plusYears(1));
                            fh.getWithdrawalMilk().set(btn.getValue().plusMonths(6));
                            FeedTable.refresh();
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getEnd().getValue().toString());
                        }
                    }
                };
            }
        };

        fEndDateColumn.setCellFactory(cellFactory);
    }

    private void addMilkDate() {
        Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>> cellFactory = new Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>>() {
            @Override
            public TableCell<FeedHistory, LocalDate> call(final TableColumn<FeedHistory, LocalDate> param) {
                return new TableCell<FeedHistory, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FeedHistory fh = getTableView().getItems().get(getIndex());
                            fh.getWithdrawalMilk().set((btn.getValue()));
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getWithdrawalMilk().getValue().toString());
                        }
                    }
                };
            }
        };

        fWithdrawalUntilMilkColumn.setCellFactory(cellFactory);
    }

    private void addSlaughterDate() {
        Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>> cellFactory = new Callback<TableColumn<FeedHistory, LocalDate>, TableCell<FeedHistory, LocalDate>>() {
            @Override
            public TableCell<FeedHistory, LocalDate> call(final TableColumn<FeedHistory, LocalDate> param) {
                return new TableCell<FeedHistory, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FeedHistory fh = getTableView().getItems().get(getIndex());
                            fh.getWithdrawalSlaughter().set((btn.getValue()));
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getWithdrawalSlaughter().getValue().toString());
                        }
                    }
                };
            }
        };

        fWithdrawalUntilSlaughterColumn.setCellFactory(cellFactory);
    }
}
