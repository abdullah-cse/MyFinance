package bd.com.abdullah.myfinance.ui.transaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import bd.com.abdullah.myfinance.R;


public class AddTransactionFragment extends Fragment {

    private TransactionViewModel transactionViewModel;
    private TransactionDao transactionDao;


    private EditText editTextTransactionTitle;
    private EditText editTextTransactionAmount;
    private EditText editTextTransactionDate;
    private EditText editTextTransactionAccount;
    private EditText editTextTransactionCategory;

    public AddTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_transaction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextTransactionTitle = view.findViewById(R.id.editTextTransactionTitle);
        editTextTransactionAmount = view.findViewById(R.id.editTextTransactionAmount);
        editTextTransactionDate = view.findViewById(R.id.editTextTransactionDate);
        editTextTransactionAccount = view.findViewById(R.id.editTextTransactionAccount);
        editTextTransactionCategory = view.findViewById(R.id.editTextTransactionCategory);

        Button buttonSaveTransaction = view.findViewById(R.id.save_transaction);
        buttonSaveTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTransaction();
            }
        });
    }

    //Save Transaction Method
    private void saveTransaction() {

        String transactionTitle = editTextTransactionTitle.getText().toString().trim();
        long transactionAmount = Long.parseLong(editTextTransactionAmount.getText().toString().trim());
        String transactionDate = editTextTransactionDate.getText().toString().trim();
        long transactionAccount = Long.parseLong(editTextTransactionAccount.getText().toString().trim());
        String transactionCategories = editTextTransactionCategory.getText().toString().trim();

            TransactionDatabase transactionDatabase = TransactionDatabase.getInstance(getContext());
            Transaction transaction = new Transaction(transactionTitle, transactionAmount, transactionDate, transactionAccount, transactionCategories);
            transactionDatabase.transactionDao().insert(transaction);

        Toast.makeText(getActivity(), "Transaction added Successfully", Toast.LENGTH_LONG).show();

        //সঠিকভাবে সেভ হওয়ার পর এই ফ্রাগমেন্ট বন্ধ হয়ে আগেরটাতে ফিরে যাবে।
        Fragment TransactionFragment=new TransactionFragment();
        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main,TransactionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}