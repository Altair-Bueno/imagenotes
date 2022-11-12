package es.uma.imagenotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import es.uma.imagenotes.R;
import es.uma.imagenotes.activity.RecordActivity;
import es.uma.imagenotes.entity.RecordEntity;

// From https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=es-419#java
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private final RecordEntity[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView recordTitle;
        public final TextView recordDate;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            recordTitle = view.findViewById(R.id.record_title);
            recordDate = view.findViewById(R.id.record_date);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public RecordAdapter(RecordEntity[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.record_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        RecordEntity record = localDataSet[position];

        Context context = viewHolder.itemView.getContext();
        DateFormat df = android.text.format.DateFormat.getDateFormat(context);
        viewHolder.recordDate.setText(df.format(record.date));
        viewHolder.recordTitle.setText(record.title);

        viewHolder.itemView.setOnClickListener(x -> {
            Intent intent = new Intent(context, RecordActivity.class);
            intent.putExtra(RecordActivity.RECORD_ID, record.id);
            context.startActivity(intent);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }


}
