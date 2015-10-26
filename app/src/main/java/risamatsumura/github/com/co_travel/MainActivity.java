package risamatsumura.github.com.co_travel;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import risamatsumura.github.com.co_travel.tab.TabFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String POSITION = "POSITION";
    public static FragmentManager fragmentManager;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabFragmentPagerAdapter pagerAdapter =
                new TabFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new TabFragmentPagerAdapter(fragmentManager,
                MainActivity.this));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

//        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            tab.setCustomView(pagerAdapter.getTabView(i));
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
