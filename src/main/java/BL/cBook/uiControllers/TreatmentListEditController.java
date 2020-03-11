package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.model.Calf;
import BL.cBook.model.Treatment;
import BL.cBook.services.MyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TreatmentListEditController implements MyController {

    @FXML private Button cButton;
    @FXML private TableView<Treatment> table;
    @FXML private TableColumn<Treatment, LocalDate> Date;
    @FXML private TableColumn<Treatment, String> Occurrence;
    @FXML private TableColumn<Treatment, String> Medicine;
    @FXML private TableColumn<Treatment, String> Amount;
    @FXML private TableColumn<Treatment, String> WithdrawalDaysMilk;
    @FXML private TableColumn<Treatment, LocalDate> withdrawalUntilMilk;
    @FXML private TableColumn<Treatment, String> WithdrawalDaysSlaughter;
    @FXML private TableColumn<Treatment, LocalDate> withdrawalUntilSlaughter;

    private ObservableList<Treatment> list;
    private Treatment treatment;
    private MainController mainController;

    @FXML
    public void initialize(MainController msc) {

        this.mainController = msc;

        treatment = new Treatment();
        list = FXCollections.observableArrayList();

        table.setEditable(true);
        setTreatment();
        setCellsEdit();
        setCells();

        Tools.Initializer.initializeWithdrawalMilk(withdrawalUntilMilk);
        Tools.Initializer.initializeWithdrawalSlaughter(withdrawalUntilSlaughter);
    }

    private void setTreatment() {

        treatment.getDate().set(LocalDate.now());
        list.add(treatment);
        table.setItems(list);
    }

    private void setCells() {
        Tools.Initializer.initializeTreatmentFields(Date, Occurrence, Medicine, Amount, WithdrawalDaysMilk, withdrawalUntilMilk, WithdrawalDaysSlaughter, withdrawalUntilSlaughter);

    }

    private void setCellsEdit() {

        Occurrence.setCellFactory(TextFieldTableCell.forTableColumn());
        Amount.setCellFactory(TextFieldTableCell.forTableColumn());
        Medicine.setCellFactory(TextFieldTableCell.forTableColumn());
        WithdrawalDaysMilk.setCellFactory(TextFieldTableCell.forTableColumn());
        WithdrawalDaysSlaughter.setCellFactory(TextFieldTableCell.forTableColumn());
        addDate();
    }

    private void addDate() {

        Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>> cellFactory = new Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>>() {
            @Override
            public TableCell<Treatment, LocalDate> call(final TableColumn<Treatment, LocalDate> param) {

                return new TableCell<Treatment, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Treatment t = getTableView().getItems().get(getIndex());
                            t.getDate().set(btn.getValue());
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getDate().getValue().toString());
                            setGraphic(btn);

                        }
                    }
                };
            }
        };

        Date.setCellFactory(cellFactory);
    }



    @FXML
    private void handleConfirm() {

        boolean skip = treatment.getWithdrawalDaysMilk().get().equals("") || treatment.getWithdrawalDaysSlaughter().get().equals("");

        if (!skip) {

            int mDays = Integer.parseInt(treatment.getWithdrawalDaysMilk().getValue());
            int sDays = Integer.parseInt(treatment.getWithdrawalDaysSlaughter().getValue());

            setDates(mDays, sDays);
        }

        for (Calf c : mainController.getEditList()) {

            if (c.getAlive().get()) {
                Treatment treatment = new Treatment();
                treatment.getOccurrence().set(this.treatment.getOccurrence().getValue());
                treatment.getMedicine().set(this.treatment.getMedicine().getValue());
                treatment.getAmount().set(this.treatment.getAmount().getValue());
                treatment.getDate().set(this.treatment.getDate().getValue());
                treatment.getAge().set(String.valueOf(ChronoUnit.DAYS.between(c.getDateOfBirth().getValue(), this.treatment.getDate().getValue())));
                treatment.getWithdrawalDaysMilk().set(this.treatment.getWithdrawalDaysMilk().getValue());
                treatment.getWithdrawalDaysSlaughter().set(this.treatment.getWithdrawalDaysSlaughter().getValue());
                treatment.getWithdrawalUntilMilk().set(this.treatment.getWithdrawalUntilMilk().getValue());
                treatment.getWithdrawalUntilSlaughter().set(this.treatment.getWithdrawalUntilSlaughter().getValue());

                c.getTreatment().add(treatment);
                table.refresh();
            }
        }

        cButton.getScene().getWindow().hide();
    }

    private void setDates(int mDays, int sDays) {

        if (sDays <= 2) {
            treatment.getWithdrawalUntilSlaughter().set(treatment.getDate().getValue().plusDays(sDays + 2));
            treatment.getWithdrawalDaysSlaughter().set(String.valueOf(sDays));
        }
        if (sDays > 2) {
            treatment.getWithdrawalUntilSlaughter().set(treatment.getDate().getValue().plusDays(sDays * 2));
            treatment.getWithdrawalDaysSlaughter().set(String.valueOf(sDays));
        }
        if (mDays <= 2) {
            treatment.getWithdrawalUntilMilk().set(treatment.getDate().getValue().plusDays(mDays + 2));
            treatment.getWithdrawalDaysMilk().set(String.valueOf(mDays));
        }
        if (mDays > 2) {
            treatment.getWithdrawalUntilMilk().set(treatment.getDate().getValue().plusDays(mDays * 2));
            treatment.getWithdrawalDaysMilk().set(String.valueOf(mDays));
        }
    }
}