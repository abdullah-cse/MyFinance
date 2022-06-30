package bd.com.abdullah.myfinance.ui.transaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import bd.com.abdullah.myfinance.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {
    //শুরুতে যখন কোন আইটেম না থাকবে, তখনকার জন্য এই ArrayList
    private List<Transaction> transactions=new ArrayList<>();

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item,parent,false);
        return new TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction currentTransaction=transactions.get(position);
        holder.textViewTitle.setText(currentTransaction.getTransactionTitle());
        holder.textViewAmount.setText(String.valueOf(currentTransaction.getTransactionAmount()));
        holder.textViewDate.setText(currentTransaction.getTransactionDate());
        holder.textViewAccount.setText(String.valueOf(currentTransaction.getTransactionAccount()));
        holder.textViewCategory.setText(currentTransaction.getTransactionCategories());
    }

    //লিস্টের সকল আইটেম দেখাতে চাই। তাই transactions.size
    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions=transactions;
        notifyDataSetChanged();
    }

    class TransactionHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewAmount;
        private TextView textViewDate;
        private ImageView imageViewDate;
        private TextView textViewAccount;
        private TextView textViewCategory;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
           // textViewTitle=itemView.findViewById(R.id.)
            textViewTitle=itemView.findViewById(R.id.textViewTransactionTitle);
            textViewAmount=itemView.findViewById(R.id.textViewTransactionAmount);
            textViewDate=itemView.findViewById(R.id.textViewTransactionDate);
            imageViewDate=itemView.findViewById(R.id.imageViewDate);
            textViewAccount=itemView.findViewById(R.id.textViewTransactionAccount);
            textViewCategory=itemView.findViewById(R.id.textViewTransactionCategory);
        }
    }
}
