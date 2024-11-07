package com.example.nmbapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.nmbapp.DriversModel;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private DriversModel[] localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView driverName;
        private final TextView route;
        private final TextView busNo;

        public ViewHolder(View view) {
            super(view);
            driverName = (TextView) view.findViewById(R.id.textView6);
            route = (TextView) view.findViewById(R.id.textView7);
            busNo = (TextView) view.findViewById(R.id.textView8);
        }

        public TextView getDriverName() {
            return driverName;
        }
        public TextView getRoute() { return route;}
        public TextView getBusNo() { return busNo;}
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public Adapter(DriversModel[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.driverlistrow, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getDriverName().setText(localDataSet[pos].getName());
        viewHolder.getRoute().setText(localDataSet[pos].getRoute());
        viewHolder.getBusNo().setText(localDataSet[pos].getBus());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}