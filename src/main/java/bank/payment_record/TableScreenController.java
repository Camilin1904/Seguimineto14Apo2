package bank.payment_record;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableScreenController implements Initializable{

    private ArrayList<Transaction> all;

    private ObservableList<Transaction> transactions;

    @FXML 
    private DatePicker dtFrom;

    @FXML
    private DatePicker dtTo;

    @FXML
    private DatePicker dt;

    @FXML
    private TextField ammount;

    @FXML
    private Label gainedMoney;

    @FXML
    private Label lostMoney;

    @FXML
    private Label balance;

    @FXML
    private TableColumn<Transaction,Integer> idCol;

    @FXML
    private TableColumn<Transaction,Double> moneyCol;

    @FXML
    private TableColumn<Transaction,LocalDate> dateCol;

    @FXML
    private TableView<Transaction> table;


    @FXML
    private void toFilter(){
        transactions.clear();
        LocalDate from = dtFrom.getValue();
        LocalDate to = dtTo.getValue();
        for(Transaction t : all){
            if(from.compareTo(t.getDate())<=0&&to.compareTo(t.getDate())>=0) transactions.add(t);
        }
        table.setItems(transactions);
        reCalculate();
    }

    @FXML
    private void toAdd(){
        transactions.clear();
        try{
            double money = Double.parseDouble(ammount.getText());
            LocalDate date = dt.getValue();
            Transaction t = new Transaction(all.size(), money, date);
            all.add(t);
            refresh();
            reCalculate();
        }
        catch (InputMismatchException n){ 
            JOptionPane.showMessageDialog(null, "Invalid ammount");
        }
    }

    @FXML
    private void refresh(){
        transactions.clear();
        dtFrom.getEditor().clear();
        dtTo.getEditor().clear();
        for (Transaction t : all){
            transactions.add(t);
        }
        table.setItems(transactions);
        reCalculate();
    }

    private void reCalculate(){
        double lost = 0;
        double gained = 0;
        double balance = 0;
        for (Transaction t : transactions){
            double i = t.getTransactionAmmount();
            if (i>0) gained += i;
            else lost += i;
            balance += i;
        }
        lostMoney.setText(lost + "");
        gainedMoney.setText(gained + "");
        this.balance.setText(balance + "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transactions = FXCollections.observableArrayList();
        all = new ArrayList<>();
        idCol.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("id"));
        moneyCol.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("transactionAmmount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,LocalDate>("date"));
    }
}
