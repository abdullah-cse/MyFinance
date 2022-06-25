package bd.com.abdullah.myfinance.ui.transaction;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_table")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String transactionTitle;
    private long    transactionAmount;
    private String transactionDate;
    private long    transactionAccount;
    private String transactionCategories;

    public Transaction(String transactionTitle, long transactionAmount, String transactionDate, long transactionAccount, String transactionCategories) {
        this.transactionTitle = transactionTitle;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionAccount = transactionAccount;
        this.transactionCategories = transactionCategories;
    }

    //SetterMethod
    public void setId(int id) {
        this.id = id;
    }

    //GetterMethod
    public int getId() {
        return id;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public long getTransactionAccount() {
        return transactionAccount;
    }

    public String getTransactionCategories() {
        return transactionCategories;
    }
}
