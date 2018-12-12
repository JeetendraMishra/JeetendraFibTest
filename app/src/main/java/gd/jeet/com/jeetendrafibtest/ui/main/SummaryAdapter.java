package gd.jeet.com.jeetendrafibtest.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gd.jeet.com.jeetendrafibtest.R;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    private List<SecondViewModel> items;
    private int itemLayout;

    public SummaryAdapter(List<SecondViewModel> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public SummaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new SummaryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SummaryAdapter.ViewHolder holder, int position) {
        SecondViewModel item = items.get(position);
        holder.number.setText(item.getNumber());
        holder.totalTime.setText(item.getTotalTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView totalTime;

        public ViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            totalTime = (TextView) itemView.findViewById(R.id.totaltime);
        }
    }
}