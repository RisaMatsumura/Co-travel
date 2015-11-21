package risamatsumura.github.com.co_travel.view.photo;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.control.ImageHandler;
import risamatsumura.github.com.co_travel.model.db.CotravelSQLiteHelper;
import risamatsumura.github.com.co_travel.model.db.PhotoContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoItemFragment extends Fragment {

    String id;

    public PhotoItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //container.removeAllViews();     // remove overlapping problem
        View view = inflater.inflate(R.layout.item_photo, container, false);

        Bundle bundle = getArguments();
        id = bundle.getString("PHOTO_ID");
        Log.d("ImageFragment id", id);

        Cursor cursor = getPhoto(id);
        String title = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUMN_TITLE));

        byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUMN_BINARY_DATA));
        ImageView imageView = (ImageView)view.findViewById(R.id.photo_image);
        imageView.setImageBitmap(ImageHandler.getSmallBitmap(image));

        return view;
    }

    private Cursor getPhoto(String id) {
        CotravelSQLiteHelper dbHelper = new CotravelSQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PhotoContract.PhotoEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{id};

        Cursor cursor = db.query(
                PhotoContract.PhotoEntry.TABLE_NAME,    // The table to query
                PhotoContract.PhotoEntry.LIST_COLUMNS,  // The columns to return
                selection,                              // The columns for the WHERE clause
                selectionArgs,                          // The values for the WHERE clause
                null,                                   // group the rows, needed so that it will return 0 when table is empty
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        cursor.moveToFirst();
        return cursor;
    }

}
