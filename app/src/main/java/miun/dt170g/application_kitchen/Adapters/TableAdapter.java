package miun.dt170g.application_kitchen.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import miun.dt170g.application_kitchen.R;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {

    private List<String> tableNumbers;

    public TableAdapter(List<String> tableNumbers) {
        this.tableNumbers = tableNumbers;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        String tableNumber = tableNumbers.get(position);
        holder.tvTableNumber.setText(tableNumber);
    }

    @Override
    public int getItemCount() {
        return tableNumbers.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder {
        TextView tvTableNumber;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTableNumber = itemView.findViewById(R.id.tvTableNumber);
        }
    }
}
