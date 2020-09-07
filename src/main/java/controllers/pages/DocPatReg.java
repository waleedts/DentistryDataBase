package main.java.controllers.pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import main.java.helpers.LimitedTextField;
import main.java.connections.ClinicDataAccessor;
import main.java.connections.CurrentUser;
import main.java.connections.DoctorDataAccessor;
import main.java.connections.PatientDataAccessor;
import main.java.helpers.Helper;
import main.java.entities.Clinic;
import main.java.entities.Doctor;
import main.java.entities.Patient;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DocPatReg implements Initializable {
    @FXML
    Pane doctorShadow;
    @FXML
    Pane patientShadow;
    @FXML
    JFXButton patientBtn;
    @FXML
    JFXButton doctorBtn;
    @FXML
    LimitedTextField salaryField;
    @FXML
    JFXComboBox<String> bloodChooser;
    @FXML
    LimitedTextField allergiesField;
    @FXML
    JFXComboBox<Clinic> clinicChooser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] st ={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodChooser.setItems(FXCollections
                .observableArrayList(st));
        if(CurrentUser.isDoctor()){
            doctorShadow.setVisible(false);
        }else{
            patientShadow.setVisible(false);
        }
        ClinicDataAccessor dataAccessor=new ClinicDataAccessor();
        try {
            clinicChooser.setItems(FXCollections
                    .observableArrayList(dataAccessor.getUnusedClinics()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void registerDoc(Event event) throws SQLException {
        if(event.getSource()==doctorBtn) {
            int i;
            if (salaryField.getText() == null || clinicChooser.getValue() == null)
                return;
            try {
                i = Integer.parseInt(salaryField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Salary Field Must be a number!");
                alert.show();
                return;
            }
            Doctor doctor = new Doctor(CurrentUser.getCurrentUser());
            doctor.setSalary(i);
            doctor.setClinicId(clinicChooser.getValue().getId());
            CurrentUser.setCurrentUser(doctor, true);
            DoctorDataAccessor dataAccessor = new DoctorDataAccessor();
            dataAccessor.setDoctor(doctor);
            Helper.changeScene("Bills_GUI.fxml",patientBtn);
        }
    }
    public void registerPat(Event event){
        if(event.getSource()==patientBtn) {

            Patient patient = new Patient(CurrentUser.getCurrentUser());
            patient.setAllergies(allergiesField.getText());
            patient.setBloodType(bloodChooser.getValue());

            CurrentUser.setCurrentUser(patient, false);
            PatientDataAccessor dataAccessor = new PatientDataAccessor();
            dataAccessor.setPatient(patient);
            Helper.changeScene("ClinicsList.fxml", patientBtn);
        }
    }
    public void goToAddClinic(){
        Helper.changeScene("clinicReg_GUI.fxml",patientBtn);
    }
}
