package com.example.ours.dialogfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ours.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class consumersSignUpFragment extends Fragment {


    public consumersSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consumer_sign_up, container, false);
    }

}
