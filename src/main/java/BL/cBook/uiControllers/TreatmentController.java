package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.model.Calf;
import BL.cBook.model.Treatment;
import BL.cBook.services.MyController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TreatmentController implements MyController {

    @FXML private AnchorPane pane;
    @FXML private TableView<Treatment> table;
    @FXML private TableColumn<Treatment, String> ageColumn;
    @FXML private TableColumn<Treatment, LocalDate> dateColumn;
    @FXML private TableColumn<Treatment, String> occurrenceColumn;
    @FXML private TableColumn<Treatment, String> medicineColumn;
    @FXML private TableColumn<Treatment, String> amountColumn;
    @FXML private TableColumn<Treatment, String> daysMilkColumn;
    @FXML private TableColumn<Treatment, LocalDate> untilMilkColumn;
    @FXML private TableColumn<Treatment, String> daysSlaughterColumn;
    @FXML private TableColumn<Treatment, LocalDate> untilSlaughterColumn;

    private Calf calf;

    @FXML
    public void initialize(MainController mc) {

        table.setEditable(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        calf = mc.getEditList().get(0);
        table.setItems(calf.getTreatment());
        setCellsEdit();
        Tools.Initializer.initializeTreatmentFields(dateColumn, occurrenceColumn, medicineColumn,
                amountColumn, daysMilkColumn, untilMilkColumn, daysSlaughterColumn, untilSlaughterColumn);
        Tools.Initializer.initializeWithdrawalMilk(untilMilkColumn);
        Tools.Initializer.initializeWithdrawalSlaughter(untilSlaughterColumn);

    }

    private void setCellsEdit() {

        occurrenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setCellValueFactory(a -> a.getValue().getAge());
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        medicineColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        daysMilkColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        daysSlaughterColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addDate();
        addWithdrawalDaysMilk();
        addWithdrawalDaysSlaughter();

    }

    private void addDate() {

        Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>> cellFactory = new Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>>() {
            @Override
            public TableCell<Treatment, LocalDate> call(final TableColumn<Treatment, LocalDate> param) {

                return new TableCell<Treatment, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            LocalDate date;
                            Treatment t = getTableView().getItems().get(getIndex());
                            date = btn.getValue();
                            t.getDate().set(date);
                            t.getAge().set(String.valueOf(ChronoUnit.DAYS.between(calf.getDateOfBirth().getValue(), date)));
                            table.refresh();
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {

                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getDate().getValue().toString());
                        }
                    }
                };
            }
        };

       dateColumn.setCellFactory(cellFactory);
    }

    private void addWithdrawalDaysMilk() {

        daysMilkColumn.setOnEditCommit(t -> {
            Treatment tr = t.getTableView().getItems().get(t.getTablePosition().getRow());
            if (Integer.parseInt(t.getNewValue()) <= 2) {
                tr.getWithdrawalUntilMilk().set(tr.getDate().getValue().plusDays(Integer.parseInt(t.getNewValue()) + 2));
                tr.getWithdrawalDaysMilk().set(t.getNewValue());
            }
            if (Integer.parseInt(t.getNewValue()) > 2) {
                tr.getWithdrawalUntilMilk().set(tr.getDate().getValue().plusDays(Integer.parseInt(t.getNewValue()) * 2));
                tr.getWithdrawalDaysMilk().set(t.getNewValue());
            }
            table.refresh();
        });
    }

    private void addWithdrawalDaysSlaughter() {

        daysSlaughterColumn.setOnEditCommit(t -> {

            Treatment tr = t.getTableView().getItems().get(t.getTablePosition().getRow());

            if (Integer.parseInt(t.getNewValue()) <= 2) {
                tr.getWithdrawalUntilSlaughter().set(tr.getDate().getValue().plusDays(Integer.parseInt(t.getNewValue()) + 2));
                tr.getWithdrawalDaysSlaughter().set(t.getNewValue());
            }
            if (Integer.parseInt(t.getNewValue()) > 2) {
                tr.getWithdrawalUntilSlaughter().set(tr.getDate().getValue().plusDays(Integer.parseInt(t.getNewValue()) * 2));
                tr.getWithdrawalDaysSlaughter().set(t.getNewValue());
            }
            table.refresh();
        });
    }

    @FXML private void handleAdd() {

        if (calf.getAlive().getValue()) {
            Treatment treatment = new Treatment();
            treatment.getDate().set(LocalDate.now());
            treatment.getAge().set(String.valueOf(ChronoUnit.DAYS.between(calf.getDateOfBirth().getValue(), LocalDate.now())));

            calf.getTreatment().add(treatment);
            table.refresh();
        } else Tools.deadCowAlert(this.calf, (Stage) pane.getScene().getWindow());
    }

    @FXML private void handleDelete() {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
        }
    }

}
