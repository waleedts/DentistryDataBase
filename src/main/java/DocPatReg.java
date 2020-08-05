package main.java;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import main.java.connections.CurrentUser;
import main.java.connections.DoctorDataAccessor;
import main.java.connections.PatientDataAccessor;
import main.java.helper.Helper;
import main.java.requirements.Doctor;
import main.java.requirements.Patient;

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
    JFXTextField salaryField;
    @FXML
    JFXComboBox<String> bloodChooser;
    @FXML
    JFXTextField allergiesField;
    @FXML
    JFXComboBox<String> clinicChooser;
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
//        Todo:implement
//        doctor.setClinicId(clinicChooser.getId());
            CurrentUser.setCurrentUser(doctor, true);
            DoctorDataAccessor dataAccessor = new DoctorDataAccessor();
            dataAccessor.setDoctor(doctor);
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
            Helper.changeScene("First_Page_GUI.fxml", patientBtn);
        }
    }
    public void goToAddClinic(){
        Helper.changeScene("",patientBtn);
    }
}
