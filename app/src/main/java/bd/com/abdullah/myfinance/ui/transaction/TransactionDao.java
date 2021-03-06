package bd.com.abdullah.myfinance.ui.transaction;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("DELETE FROM transaction_table")
    void deleteAllTransaction();

    @Query("SELECT * FROM transaction_table ORDER BY transactionAmount DESC")
    LiveData<List<Transaction>>getAllTransaction();
}
