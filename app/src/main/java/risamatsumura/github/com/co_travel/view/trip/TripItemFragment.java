package risamatsumura.github.com.co_travel.view.trip;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.model.db.CotravelSQLiteHelper;
import risamatsumura.github.com.co_travel.model.db.PhotoContract;
import risamatsumura.github.com.co_travel.model.db.TripContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripItemFragment extends android.support.v4.app.Fragment {

    private String id;

    public TripItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.item_trip, container, false);
        Bundle bundle = getArguments();
        id = bundle.getString("TRIP_ID");

        Cursor cursor = getTrip(id);
        String name = cursor.getString(cursor.getColumnIndexOrThrow(TripContract.TripEntry.COLUMN_NAME));
        TextView textView = (TextView)view.findViewById(R.id.tv_trip_item_test);
        textView.setText(name);
        return view;
    }

    private Cursor getTrip(String id) {
        CotravelSQLiteHelper dbHelper = new CotravelSQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = TripContract.TripEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{id};

        Cursor cursor = db.query(
                TripContract.TripEntry.TABLE_NAME,    // The table to query
                TripContract.TripEntry.LIST_COLUMNS,  // The columns to return
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
