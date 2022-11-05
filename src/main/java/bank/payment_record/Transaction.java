package bank.payment_record;

import java.time.LocalDate;

public class Transaction {

    public int id;
    public double transactionAmmount;
    public LocalDate date;
    public String repDate;
    
    public Transaction(int id, double transactionAmmount, LocalDate date){
        this.id = id;
        this.date = date;
        this.transactionAmmount =  transactionAmmount;
        repDate = date.toString();
    }

    public LocalDate getDate() {
        return date;
    }
    public int getId() {
        return id;
    }   
    public double getTransactionAmmount() {
        return transactionAmmount;
    }
}
