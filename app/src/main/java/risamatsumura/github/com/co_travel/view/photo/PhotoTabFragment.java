package risamatsumura.github.com.co_travel.view.photo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.model.db.CotravelSQLiteHelper;
import risamatsumura.github.com.co_travel.model.db.PhotoContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoTabFragment extends Fragment implements PhotoCursorAdapter.ItemClickListener {

    private static int SPAN = 3; // column span of the gallary

    public static PhotoTabFragment newInstance() {
        PhotoTabFragment fragment = new PhotoTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_photo_tab, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.photo_recycler_view);

        Cursor cursor = getTripList();
        PhotoCursorAdapter photoAdapter = new PhotoCursorAdapter(getContext(), cursor);
        photoAdapter.setItemClickListener(this);

        recyclerView.setAdapter(photoAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN));

        return view;
    }

    private Cursor getTripList() {

        CotravelSQLiteHelper dbHelper = new CotravelSQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String orderBy =
                PhotoContract.PhotoEntry.COLUMN_DATE + " ASC";

        Cursor cursor = db.query(
                PhotoContract.PhotoEntry.TABLE_NAME,    // The table to query
                PhotoContract.PhotoEntry.LIST_COLUMNS,  // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // group the rows, needed so that it will return 0 when table is empty
                null,                                   // don't filter by row groups
                orderBy                                 // The sort order
        );

        return cursor;
    }

    @Override
    public void itemClicked(String id) {
        // Create new fragment and transaction
        PhotoItemFragment photoItemFragment = new PhotoItemFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("PHOTO_ID", id);
        photoItemFragment.setArguments(bundle);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.photo_fl, photoItemFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}

