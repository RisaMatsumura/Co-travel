package risamatsumura.github.com.co_travel.view.trip;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import risamatsumura.github.com.co_travel.R;

/**
 * Created by risam on 26/10/2015.
 */
public class TripAdapter
        extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<String> tripTitles;

    public TripAdapter(List<String> tripTitles) {
        this.tripTitles = tripTitles;
    }

    /**
     *  Provide a direct reference to each of the views within a data item.
     *  Used to cache the views within the item layout for fast access
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cvTrip;
        public TextView tvName;
        public TextView tvDuration;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            cvTrip = (CardView) itemView.findViewById(R.id.cv_item_trip);
            tvName = (TextView) itemView.findViewById(R.id.tv_trip_name);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_trip_duration);
        }
    }

    /**
     * Inflate the item layout and create the holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View photoView = inflater.inflate(R.layout.item_trip_cv, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    /**
     * Set the view attributes based on the data
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(TripAdapter.ViewHolder holder, int position) {
        String tripTitle = tripTitles.get(position);

        // Set item views based on the data model
        TextView tvName = holder.tvName;
        tvName.setText(tripTitle);
        TextView tvDuration = holder.tvDuration;
        tvDuration.setText("24.9.2014 - 24.9.2015");

    }

    /**
     * Determine the number of items
     * @return
     */
    @Override
    public int getItemCount() {
        return tripTitles.size();
    }

}
