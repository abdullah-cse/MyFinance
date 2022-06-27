package bd.com.abdullah.myfinance.ui.transaction;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> allTransaction;

    //TransactionViewModel Constructor
    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository= new TransactionRepository(application);
        allTransaction=repository.getAllTransaction();
    }

    public void insert(Transaction transaction){
        repository.insert(transaction);
    }

    public void update(Transaction transaction){
        repository.update(transaction);
    }

    public void delete(Transaction transaction){
        repository.delete(transaction);
    }

    public void deleteAllTransaction(){
        repository.deleteAllTransaction();
    }

    public LiveData<List<Transaction>> getAllTransaction(){
        return allTransaction;
    }

}