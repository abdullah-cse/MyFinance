package bd.com.abdullah.myfinance.ui.transaction;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TransactionRepository {
    private TransactionDao transactionDao;
    private LiveData<List<Transaction>> allTransaction;

    public TransactionRepository(Application application){
        TransactionDatabase database=TransactionDatabase.getInstance(application);
        transactionDao=database.transactionDao();
        allTransaction=transactionDao.getAllTransaction();
    }

    public void insert(Transaction transaction){
        new InsertTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void update(Transaction transaction){
        new UpdateTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void delete(Transaction transaction){
        new DeleteTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void deleteAllTransaction(){
        new DeleteAllTransactionAsyncTask(transactionDao).execute();
    }

    public LiveData<List<Transaction>>getAllTransaction(){
        return allTransaction;
    }

    //Insert Method
    private static class InsertTransactionAsyncTask extends AsyncTask<Transaction,Void,Void>{
        private TransactionDao transactionDao;
        private InsertTransactionAsyncTask(TransactionDao transactionDao){
            this.transactionDao=transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.insert(transactions[0]);
            return null;
        }
    }
    //UpdateMethod
    private static class UpdateTransactionAsyncTask extends AsyncTask<Transaction,Void,Void>{
        private TransactionDao transactionDao;
        private UpdateTransactionAsyncTask(TransactionDao transactionDao){
            this.transactionDao=transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.update(transactions[0]);
            return null;
        }
    }

    //Delete Method
    private static class DeleteTransactionAsyncTask extends AsyncTask<Transaction,Void,Void>{
        private TransactionDao transactionDao;
        private DeleteTransactionAsyncTask(TransactionDao transactionDao){
            this.transactionDao=transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.delete(transactions[0]);
            return null;
        }
    }
    //Delete All Transaction
    private static class DeleteAllTransactionAsyncTask extends AsyncTask<Void,Void,Void>{
        private TransactionDao transactionDao;
        private DeleteAllTransactionAsyncTask(TransactionDao transactionDao){
            this.transactionDao=transactionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDao.deleteAllTransaction();
            return null;
        }
    }


}
