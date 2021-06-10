package bukubank;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author Ahmad Faishal Albadri
 * @nim 20523166
 * 
 * @author Muhammad Yusuf Hidayat
 * @nim 20523128
 */

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea tfHasil;
    
    @FXML
    private TextField tfDescAdd;
    
    @FXML
    private TextField tfDebitAdd;
    
    @FXML
    private TextField tfCreditAdd;
    
    @FXML
    private TextField tfDescEdit;
    
    @FXML
    private TextField tfDebitEdit;
    
    @FXML
    private TextField tfCreditEdit;
    
    @FXML
    private ComboBox<String> cbEdit;
    
    @FXML
    private ComboBox<String> cbDelete;
    
    private DataBank dataBank;
    
    private ArrayList<DataBank> arrayDataBank = new ArrayList<DataBank>();
    
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    
    @FXML
    private void handleButtonAdd(ActionEvent event) {
        if (tfDescAdd.getText().isEmpty()) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Data Deskripsi Masih Kosong");
            alert.showAndWait();
        } else if (tfCreditAdd.getText().isEmpty()) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Data Kredit Masih Kosong");
            alert.showAndWait();
        } else if (tfDebitAdd.getText().isEmpty()) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Data Debit Masih Kosong");
            alert.showAndWait();
        } else {
            createData(tfDescAdd.getText().toString(), Integer.parseInt(tfDebitAdd.getText().toString()), Integer.parseInt(tfCreditAdd.getText().toString()) );
        }
    }
    
    @FXML
    private void onComboBoxEditClick(ActionEvent event) {
        if (cbEdit.getValue() != null) {
            String cbValue = cbEdit.getValue();
            for (int i = 0; i < arrayDataBank.size(); i++) {
                String itemValue = String.valueOf(arrayDataBank.get(i).getId()) + ". " + arrayDataBank.get(i).getDesc();
                if (itemValue.equals(cbValue)) {
                    tfDescEdit.setText(arrayDataBank.get(i).getDesc());
                    tfCreditEdit.setText(String.valueOf(arrayDataBank.get(i).getCredit()));
                    tfDebitEdit.setText(String.valueOf(arrayDataBank.get(i).getDebit()));
                }
            }
        } else {
            tfDescEdit.setText(null);
            tfCreditEdit.setText(null);
            tfDebitEdit.setText(null);
        }
    }
    
    @FXML
    private void handleButtonEdit(ActionEvent event) {
        if (tfDescEdit.getText().isEmpty()) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Data Deskripsi Masih Kosong");
            alert.showAndWait();
        } else if (tfCreditEdit.getText().isEmpty()) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Data Kredit Masih Kosong");
            alert.showAndWait();
        } else if (tfDebitEdit.getText().isEmpty()) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Data Debit Masih Kosong");
            alert.showAndWait();
        } else if(cbEdit.getValue() == null) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Pilih Data Yang Mau Di Edit");
            alert.showAndWait();
        } else {
            editData(cbEdit.getValue(), tfDescEdit.getText().toString(), Integer.parseInt(tfDebitEdit.getText().toString()), Integer.parseInt(tfCreditEdit.getText().toString()));
        }
    }
    
    @FXML
    private void handleButtonDelete(ActionEvent event) {
        if (cbDelete.getValue() == null) {
            alert.setTitle("Kosong!!!");
            alert.setContentText("Pilih Data Yang Mau Di Delete");
            alert.showAndWait();
        } else {
            deleteData(cbDelete.getValue());
        }
    }
    
    @FXML
    private void handleButtonReset(ActionEvent event) {
        resetData();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataBank = new DataBank();
        
    }    

    private void readData() {
        String hasil = "";
        ObservableList<String> itemsCombobox = FXCollections.observableArrayList();
        cbEdit.setItems(itemsCombobox);
        cbDelete.setItems(itemsCombobox);
        
        for (int i = 0; i < arrayDataBank.size(); i++) {
            int id, debit, credit;
            String desc;
            
            id = arrayDataBank.get(i).getId();
            debit = arrayDataBank.get(i).getDebit();
            credit = arrayDataBank.get(i).getCredit();
            desc = arrayDataBank.get(i).getDesc();
            
            hasil = hasil + String.valueOf(id) + ". "  + desc + " | Credit : " + String.valueOf(credit) + " | Debit : " + String.valueOf(debit) + "\n";
            String sComboBox = String.valueOf(id) + ". " + desc;
            itemsCombobox.add(sComboBox);
        }
        
        tfHasil.setText(hasil);
       
    }
    
    private void createData(String desc, int debit, int credit) {
        int id = 0;
        for (int i = 0; i < arrayDataBank.size(); i++) {
            id = arrayDataBank.get(i).getId();
        }
        id = id + 1;
        
        arrayDataBank.add(new DataBank(id, debit, credit, desc));
        tfDescAdd.setText(null);
        tfCreditAdd.setText(null);
        tfDebitAdd.setText(null);
        readData();
    }
    
    private  void editData(String id, String desc, int debit, int credit) {
        ArrayList<DataBank> listdata = new ArrayList<DataBank>();
        listdata.addAll(arrayDataBank);
        arrayDataBank.clear();
        
        for (int i = 0; i < listdata.size(); i++) {
            String idValue = String.valueOf(listdata.get(i).getId()) + ". " + listdata.get(i).getDesc();
            if (idValue.equals(id)) {
                arrayDataBank.add(new DataBank(listdata.get(i).getId(), debit, credit, desc));
            } else {
                arrayDataBank.add(new DataBank(listdata.get(i).getId(), listdata.get(i).getDebit(), listdata.get(i).getCredit(), listdata.get(i).getDesc()));
            }
        }
        listdata.clear();
        tfDescEdit.setText(null);
        tfCreditEdit.setText(null);
        tfDebitEdit.setText(null);
        readData();
    }

    private void deleteData(String id) {
        for (int i = 0; i < arrayDataBank.size(); i++) {
            String idValue = String.valueOf(arrayDataBank.get(i).getId()) + ". " + arrayDataBank.get(i).getDesc();
            if (idValue.equals(id)) {
                arrayDataBank.remove(i);
            }
        }
        readData();
    }

    private void resetData() {
        arrayDataBank.clear();
        readData();
    }
    
}
