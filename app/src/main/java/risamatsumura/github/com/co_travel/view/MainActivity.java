package risamatsumura.github.com.co_travel.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import risamatsumura.github.com.co_travel.R;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String POSITION = "POSITION";
    public static FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] testList = {
            "List item 1", "List item 2", "List item3"
    };

    private ActionBarDrawerToggle drawerToggle;


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

        // Setting up a Tool Bar and replacing it with the Action Bar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter pagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new ViewPagerAdapter(fragmentManager,
                MainActivity.this));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

//        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
        // Automatic way to link with ViewPagerAdapter
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            tab.setCustomView(pagerAdapter.getTabView(i));
//        }

        // Navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, testList));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
