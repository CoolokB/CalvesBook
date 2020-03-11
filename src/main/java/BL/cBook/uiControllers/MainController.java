package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.model.Calf;
import BL.cBook.model.FeedControl;
import BL.cBook.services.CalfAndFeedControlService;
import BL.cBook.utils.Loader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;

@Setter
@Getter
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @Autowired
    private CalfAndFeedControlService service;

    @FXML private Button chartButton;
    @FXML private Button addButton;
    @FXML private Button feedControlButton;
    @FXML private Button filterButton;

    @FXML public TableView<Calf> Table;
    @FXML private TableColumn<Calf, String> age;
    @FXML private TableColumn<Calf, String> idColumn;
    @FXML private TableColumn<Calf, String> BreedColumn;
    @FXML private TableColumn<Calf, String> GenderColumn;
    @FXML private TableColumn<Calf, String> isAliveColumn;
    @FXML private TableColumn<Calf, String> QuantityColumn;
    @FXML private TableColumn<Calf, String> PlaceOfBirthColumn;
    @FXML private TableColumn<Calf, LocalDate> dateOfBirthColumn;
    @FXML private TableColumn<Calf, String> DaysOfGestationColumn;
    @FXML private TableColumn<Calf, String> ColostrumByBottleColumn;

    private ObservableList<Calf> calves;
    private ObservableList<FeedControl> feedControllersList;
    private ObservableList<Calf> editList;
    private ObservableList<Calf> calvesFilteredList;


    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

        Table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        calves = Tools.Transformer.transform(service.findAll());
        feedControllersList = FXCollections.observableArrayList(service.findAllFeedControls());
        Table.setItems(calves);
        initializeButtons();
        Tools.Initializer.initializeMainFields(isAliveColumn,dateOfBirthColumn, idColumn, GenderColumn, DaysOfGestationColumn,
                BreedColumn, PlaceOfBirthColumn, ColostrumByBottleColumn, QuantityColumn, age);
        Table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Table.refresh());

    }

    private void initializeButtons() {
        addButton.setOnAction(e -> Loader.load(this, "/cards/AddingPage.fxml", "New Calf"));
        chartButton.setOnAction(e -> Loader.load(this, "/cards/ChartConstructor.fxml", "Chart Constructor"));
        filterButton.setOnAction(e -> Loader.load(this, "/cards/EditingPage.fxml", "Filter", true));
        feedControlButton.setOnAction(e -> Loader.load(this, "/cards/FeedControl.fxml", "Feed Control"));
    }

    @PreDestroy
    public void onExit() {
       service.saveAll(Tools.Transformer.transform(calves));
       service.saveAllFeedControllers(feedControllersList);
    }

    @FXML public void summaryHandle() {

        editList = Table.getSelectionModel().getSelectedItems();

        if (editList.size() == 1) {

            Loader.load(this, "/cards/Summary.fxml", editList.get(0).getMyId().getValue());

        } else if (editList.size() == 0) {
            Tools.alert("No Selection", "No Calf Selected", "Please select a calf in the table.");

        } else {
            Tools.alert("!", "More than one  Calf Selected", "Please select only one calf in the table.");
        }
    }

    @FXML private void editHandle() {
        editList = Table.getSelectionModel().getSelectedItems();

        if (editList.size() == 0) {
            Tools.alert("No Selection", "No Calf Selected", "Please select a calf in the table.");
        } else {
            Loader.load(this, "/cards/EditingPage.fxml", "Editing", false);
        }
    }

    @FXML private void feedHandle() {

        editList = Table.getSelectionModel().getSelectedItems();

        if (editList.size() == 0) {
            Tools.alert("No Selection", "No Calf Selected", "Please select a calf in the table.");
        } else {

                Loader.load(this, "/cards/FeedHistory.fxml", editList.get(0).getMyId().getValue());

        }
    }

    @FXML private void handleDelete() {
        editList = Table.getSelectionModel().getSelectedItems();

        if (editList.size() == 0) {
            Tools.alert("No Selection", "No Calf Selected", "Please select a calf in the table.");
        }else {
            service.deleteAll(Tools.Transformer.transform(editList));
            Table.getItems().removeAll(editList);
        }
    }

    @FXML private void TreatmentHandle() {
        editList = Table.getSelectionModel().getSelectedItems();
        if (editList.size()==0) {
            Tools.alert("No Selection", "No Calf Selected", "Please select a calf in the table.");
        } else if (editList.size() == 1) {
            Loader.load(this, "/cards/Treatment.fxml", editList.get(0).getMyId().getValue());
        } else {
            Loader.load(this, "/cards/TreatmentListEdit.fxml", "Treatment Edit" );
        }
    }

    @FXML private void placeOfHabitatHandle() {
        editList = Table.getSelectionModel().getSelectedItems();
        if (editList.size()==0) {
            Tools.alert("No Selection", "No Calf Selected", "Please select a calf in the table.");
        } else if (editList.size() == 1) {
            Loader.load(this, "/cards/PlaceOfHabitat.fxml", editList.get(0).getMyId().getValue());
        } else {
            Loader.load(this, "/cards/PlaceListEdit.fxml", "Place Edit");
        }
    }

}