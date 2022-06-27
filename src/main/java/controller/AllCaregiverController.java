package controller;

import datastorage.CaregiverDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;
import datastorage.DAOFactory;
import java.sql.SQLException;
import java.util.List;


/**
 * The <code>AllCaregiverController</code> contains the entire logic of the Caregiver view. It determines which data is displayed and how to react to events.
 */
public class AllCaregiverController {
    @FXML
    private TableView<Caregiver> tableView;
    @FXML
    private TableColumn<Caregiver, Integer> colID;
    @FXML
    private TableColumn<Caregiver, String> colFirstName;
    @FXML
    private TableColumn<Caregiver, String> colSurname;
    @FXML
    private TableColumn<Caregiver, String> colDateOfBirth;
    @FXML
    private TableColumn<Caregiver, String> colTelephone;


    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txfSurname;
    @FXML
    TextField txfFirstname;
    @FXML
    TextField txfBirthday;
    @FXML
    TextField txfTelephone;


    private ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private CaregiverDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("dateOfBirth"));
        this.colDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("phoneNumber"));
        this.colDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());



        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new birthdate value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditDateOfBirth(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setDateOfBirth(event.getNewValue());
        doUpdate(event);
    }
    /**
     * handles new telephone value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditTelephone(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setTelephone(event.getNewValue());
        doUpdate(event);
    }

    /**
     * updates a Caregiver by calling the update-Method in the {@link CaregiverDAO}
     * @param t row to be updated by the user (includes the Caregiver)
     */
    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link CaregiverDAO} and shows Caregivers in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregivers;
        try {
            allCaregivers = dao.readAll();
            for (Caregiver p : allCaregivers) {
                this.tableviewContent.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link CaregiverDAO}
     */
    @FXML
    public void handleDeleteRow() {
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            dao.deleteById(selectedItem.getCid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * handles a block-click-event. Calls the block methods in the {@link CaregiverDAO}
     */
    @FXML
    public void handleBlockRow() {
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            dao.blockById(selectedItem.getCid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a Caregiver and calls the create method in the {@link CaregiverDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txfSurname.getText();
        String firstname = this.txfFirstname.getText();
        String birthday = this.txfBirthday.getText();
        String telephone = this.txfTelephone.getText();
        try {
            Caregiver p = new Caregiver(firstname, surname, birthday, telephone);
            dao.create(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txfFirstname.clear();
        this.txfSurname.clear();
        this.txfBirthday.clear();
        this.txfTelephone.clear();
    }
}
