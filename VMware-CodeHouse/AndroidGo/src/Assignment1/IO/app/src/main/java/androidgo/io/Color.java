package androidgo.io;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Author: Ketki Haridas
 * Date: 08/16/2016
 * Color: Fragment that displays the color
 */
public class Color extends Fragment {

    
    public Color() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color2, container, false);
    }

}
