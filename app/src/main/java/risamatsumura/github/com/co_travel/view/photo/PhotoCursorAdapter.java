package risamatsumura.github.com.co_travel.view.photo;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import risamatsumura.github.com.co_travel.utils.DateFormatter;
import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.control.ImageHandler;
import risamatsumura.github.com.co_travel.model.db.PhotoContract;

/**
 * The adapter provides access to the items in your data set,
 * creates views for items, and replaces the content of some of the views
 * with new data items when the original item is no longer visible.
 */
public class PhotoCursorAdapter
        extends RecyclerView.Adapter<PhotoCursorAdapter.ViewHolder> {

    private Context context;
    private CursorAdapter cursorAdapter;
    private ViewHolder holder;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void itemClicked(String id);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public PhotoCursorAdapter(Context context, Cursor cursor) {

        this.context = context;
        cursorAdapter = new CursorAdapter(context, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                // Inflate view
                View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_photo, parent, false);
//                holder = new ViewHolder(view);
                return view;
            }

            @Override
            public void bindView(View view, final Context context, Cursor cursor) {
                // Binding operations
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUMN_TITLE));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUMN_BINARY_DATA));
                String dateInMillis = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUMN_DATE));
                String date = DateFormatter.getMDYString(dateInMillis);
                final String id = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry._ID));
//                holder = (ViewHolder) view.getTag();
                holder.ivPhoto.setImageBitmap(ImageHandler.getSmallBitmap(image));
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null)
                            itemClickListener.itemClicked(id);
                    }
                });

//                holder.tvName.setText(name);

            }
        };
    }

    /**
     * Provide a direct reference to each of the views within a data item.
     * Used to cache the views within the item layout for fast access
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivPhoto;
        public TextView tvName;

        public ViewHolder(View view) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(view);
            ivPhoto = (ImageView) view.findViewById(R.id.photo_image);
            tvName = (TextView) view.findViewById(R.id.photo_name);
        }
    }

    /**
     * Called when the custom ViewHolder needs to be initialized
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public PhotoCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Pass the inflater job to the cursor-adapter
        View view = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        holder = new ViewHolder(view);
//        view.setTag(holder);
        return holder;
    }

    /**
     * Specify the contents of each item of the RecyclerView
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(PhotoCursorAdapter.ViewHolder holder, int position) {
        // Pass the binding operation to cursor loader
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    /**
     * Determine the number of items
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
