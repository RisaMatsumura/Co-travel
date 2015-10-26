package risamatsumura.github.com.co_travel.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import risamatsumura.github.com.co_travel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoTabFragment extends Fragment {

    public static PhotoTabFragment newInstance() {
        PhotoTabFragment fragment = new PhotoTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_photo_tab, container, false);
        TextView textView = (TextView) view.findViewById(R.id.photoTextView);
        textView.setText("Trip Photo Text");
        return view;
    }

}
