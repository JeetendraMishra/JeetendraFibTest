package gd.jeet.com.jeetendrafibtest.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gd.jeet.com.jeetendrafibtest.R;

public class FibAdapter extends RecyclerView.Adapter<FibAdapter.ViewHolder> {

    private List<MainViewModel> items;
    private int itemLayout;

    public FibAdapter(List<MainViewModel> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainViewModel item = items.get(position);
        holder.sequence.setText(item.getSequence());
        holder.value.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sequence;
        public TextView value;

        public ViewHolder(View itemView) {
            super(itemView);
            sequence = (TextView) itemView.findViewById(R.id.sequence);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }
}