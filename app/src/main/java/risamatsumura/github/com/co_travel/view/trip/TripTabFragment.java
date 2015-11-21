package risamatsumura.github.com.co_travel.view.trip;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.model.db.CotravelSQLiteHelper;
import risamatsumura.github.com.co_travel.model.db.TripContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripTabFragment extends Fragment implements TripCursorAdapter.ItemClickListner{

    public static TripTabFragment newInstance() {
        TripTabFragment fragment = new TripTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_trip_tab, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_trip);

        Cursor cursor = getTripList();
        TripCursorAdapter tripCursorAdapter = new TripCursorAdapter(getContext(), cursor);
        tripCursorAdapter.setItemClickListener(this);

        recyclerView.setAdapter(tripCursorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private Cursor getTripList() {

        CotravelSQLiteHelper dbHelper = new CotravelSQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String orderBy =
                TripContract.TripEntry.COLUMN_DATE + " ASC";

        Cursor cursor = db.query(
                TripContract.TripEntry.TABLE_NAME,                                   // The table to query
                TripContract.TripEntry.LIST_COLUMNS,  // The columns to return
                null,                                               // The columns for the WHERE clause
                null,                                               // The values for the WHERE clause
                null,                                            // group the rows, needed so that it will return 0 when table is empty
                null,                                               // don't filter by row groups
                orderBy                                             // The sort order
        );

        return cursor;
    }


    @Override
    public void itemClicked(String id) {
        TripItemFragment newFragment = new TripItemFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("TRIP_ID", id);
        newFragment.setArguments(bundle);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.trip_ll, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
