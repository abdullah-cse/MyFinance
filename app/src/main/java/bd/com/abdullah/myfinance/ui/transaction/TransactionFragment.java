package bd.com.abdullah.myfinance.ui.transaction;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import bd.com.abdullah.myfinance.R;
import bd.com.abdullah.myfinance.databinding.FragmentTransactionBinding;


public class TransactionFragment extends Fragment {

    private FragmentTransactionBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTransactionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final TransactionAdapter adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);

        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.getAllTransaction().observe(getViewLifecycleOwner(), new Observer<List<Transaction>>() {
                    @Override
                    public void onChanged(List<Transaction> transactions) {
                        adapter.setTransactions(transactions);
                    }
                }
        );
        FloatingActionButton buttonAddTransaction=view.findViewById(R.id.add_transaction_fab);
        buttonAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addTransactionFragment=new AddTransactionFragment();
                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,addTransactionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Swipe to Delete Function Starts
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                transactionViewModel.delete(adapter.getTransactionAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(),"Transaction Deleted",Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);
        //Swipe to Delete Function Ends

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}