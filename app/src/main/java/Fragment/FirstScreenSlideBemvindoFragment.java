package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.celusoftwares.onibustb.R;

/**
 * Created by Jhonathan on 25/02/17.
 */

public class FirstScreenSlideBemvindoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.slide_bemvindo_1, container, false);


        return rootView;
    }
}
