package risamatsumura.github.com.co_travel.view.place;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.model.db.CotravelSQLiteHelper;
import risamatsumura.github.com.co_travel.model.db.PlaceContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceTabFragment extends Fragment {
    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private MapView mapView;

    public static PlaceTabFragment newInstance() {
        PlaceTabFragment fragment = new PlaceTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_plce_tab, container, false);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();// needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        map = mapView.getMap();

        // Put markers
        Cursor cursor = getPlaceList();
        for (int i = 0; i < cursor.getCount(); i++) {
            createMarkerFromDB(cursor, i);
        }
//        createMarker("Test", 35.6814, 139.7661);
//        createMarker("Test", 35.6844, 139.7631);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(35.6814, 139.7661)).zoom(13).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        return view;
    }

    private void createMarker(String markerText, double latitude, double longitude) {
        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title(markerText);

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        map.addMarker(marker);
    }

    private void createMarkerFromDB(Cursor cursor, int position) {
        cursor.moveToPosition(position);
        String markerText = cursor.getString(cursor.getColumnIndexOrThrow(PlaceContract.PlaceEntry.COLUMN_MARKER));
        String latitudeStr = cursor.getString(cursor.getColumnIndexOrThrow(PlaceContract.PlaceEntry.COLUMN_LAT));
        String longitudeStr = cursor.getString(cursor.getColumnIndexOrThrow(PlaceContract.PlaceEntry.COLUMN_LONG));

        double latitude = Double.valueOf(latitudeStr);
        double longitude = Double.valueOf(longitudeStr);
        Log.d("createMarkerFromDB Pos", String.valueOf(position));
        Log.d("createMarkerFromDB Lat", latitudeStr);
        Log.d("createMarkerFromDB Long", longitudeStr);

        createMarker(markerText, latitude, longitude);
    }

    private Cursor getPlaceList() {

        CotravelSQLiteHelper dbHelper = new CotravelSQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PlaceContract.PlaceEntry.TABLE_NAME,                                   // The table to query
                PlaceContract.PlaceEntry.LIST_COLUMNS,  // The columns to return
                null,                                               // The columns for the WHERE clause
                null,                                               // The values for the WHERE clause
                null,                                            // group the rows, needed so that it will return 0 when table is empty
                null,                                               // don't filter by row groups
                null                                             // The sort order
        );

        return cursor;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}

