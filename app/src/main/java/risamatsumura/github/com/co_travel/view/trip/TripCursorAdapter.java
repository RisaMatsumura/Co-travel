package risamatsumura.github.com.co_travel.view.trip;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Calendar;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.model.db.TripContract;

/**
 * The adapter provides access to the items in your data set,
 * creates views for items, and replaces the content of some of the views
 * with new data items when the original item is no longer visible.
 */
public class TripCursorAdapter
        extends RecyclerView.Adapter<TripCursorAdapter.ViewHolder> {

    private Context context;
    private CursorAdapter cursorAdapter;
    private ViewHolder holder;
    private ItemClickListner itemClickListener;

    public interface ItemClickListner {
        void itemClicked(String id);
    }

    public void setItemClickListener(TripCursorAdapter.ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TripCursorAdapter(Context context, Cursor cursor) {

        this.context = context;
        cursorAdapter = new CursorAdapter(context, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                // Inflate view
                View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_trip_cv, parent, false);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                // Binding operations
                final String id = cursor.getString(cursor.getColumnIndexOrThrow(TripContract.TripEntry._ID));

                String startDateInMillis = cursor.getString(cursor.getColumnIndexOrThrow(TripContract.TripEntry.COLUMN_DATE));
                String endDateInMillis = cursor.getString(cursor.getColumnIndexOrThrow(TripContract.TripEntry.COLUMN_END_DATE));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(TripContract.TripEntry.COLUMN_NAME));

                String startDate = convertDate(startDateInMillis);
                String endDate = convertDate(endDateInMillis);

                holder.tvName.setText(name);
                holder.tvDuration.setText(startDate + " - " + endDate);
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null)
                            itemClickListener.itemClicked(id);
                    }
                });
            }
        };
    }

    private String convertDate(String storedDate) {
        long dateInMillis = Long.valueOf(storedDate);
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(dateInMillis);

        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        String dateStr = month + 1 + "-" + day;
        return dateStr;
    }

    /**
     *  Provide a direct reference to each of the views within a data item.
     *  Used to cache the views within the item layout for fast access
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView tvName;
        public TextView tvDuration;

        public ViewHolder(View view) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_trip_name);
            tvDuration = (TextView) view.findViewById(R.id.tv_trip_duration);
            cardView = (CardView) view.findViewById(R.id.cv_item_trip);
        }
    }

    /**
     * Called when the custom ViewHolder needs to be initialized
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public TripCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Pass the inflater job to the cursor-adapter
        View view = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Specify the contents of each item of the RecyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(TripCursorAdapter.ViewHolder holder, int position) {
        // Pass the binding operation to cursor loader
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    /**
     * Determine the number of items
     * @return
     */
    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

}
