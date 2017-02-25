package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import Fragment.FirstScreenSlideBemvindoFragment;
import Fragment.SecondScreenSlideBemvindoFragment;
import Fragment.ThirdScreenSlideBemvindoFragment;

/**
 * Created by Jhonathan on 25/02/17.
 */

public class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {

    private static final int PAGENUMBER = 3;

    public ScreenSlidePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FirstScreenSlideBemvindoFragment();
        }
        if (position == 1){
            return new SecondScreenSlideBemvindoFragment();
        }
        else {
            return new ThirdScreenSlideBemvindoFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGENUMBER;
    }
}
