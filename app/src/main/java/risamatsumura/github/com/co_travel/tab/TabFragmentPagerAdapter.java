package risamatsumura.github.com.co_travel.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by risam on 21/10/2015.
 * An adapter for the ViewPager which controls
 * the order of the tabs, the titles and their associated content.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Trip", "Photos", "Places" };
    private Context context;

    public TabFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    /**
     * Returns total number of pages
     * @return total number of pages
     */
    @Override
    public int getCount() { return PAGE_COUNT; }

    /**
     * Returns the fragment to display for that page
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
//        FragmentManager fFanager = getSupportFragmentManager();
//        TransactionListFragment transactionListFragment = new TransactionListFragment();
//        getFragmentManager().beginTransaction().add(android.R.id.content, transactionListFragment).commit();

        switch(position) {
            case 0:
                TripTabFragment tripTab = new TripTabFragment();
                return tripTab;
            case 1:
                PhotoTabFragment photoTab = new PhotoTabFragment();
                return photoTab;
            case 2:
                PlaceTabFragment placeTab = new PlaceTabFragment();
                return placeTab;
            default:
                return null;
        }
//        return PageFragment.newInstance(position + 1);
    }

    /**
     *  Returns the page title for the top indicator
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

//    public View getTabView(int position) {
//        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
//        View v = LayoutInflater.from(context).inflate(tabFrags[position], null);
////        TextView tv = (TextView) v.findViewById(R.id.textView);
////        tv.setText(tabTitles[position]);
////        ImageView img = (ImageView) v.findViewById(R.id.imgView);
////        img.setImageResource(imageResId[position]);
//        return v;
//    }



}
