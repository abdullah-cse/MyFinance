package bd.com.abdullah.myfinance.ui.transaction;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Transaction.class, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    private static TransactionDatabase instance;

    public abstract TransactionDao transactionDao();

    public static synchronized TransactionDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    TransactionDatabase.class,"transaction_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)  //Optional, To prepopulate Dummy Data
                    .build();
        }
        return instance;
    }

    //Pre-populate some DummyData Starts
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private TransactionDao transactionDao;
        private PopulateDbAsyncTask(TransactionDatabase db){
            transactionDao=db.transactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDao.insert(new Transaction("Manir",5000,"26-06-2022",25000,"Debt"));
            transactionDao.insert(new Transaction("Liton",1500,"23-06-2022",25000,"Donation"));
            return null;
        }
    }
    //Pre-populate some DummyData Ends
}