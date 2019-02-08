package com.example.ours.dialogfragments;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ours.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class signUpFragment extends DialogFragment {


    public signUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signupview= inflater.inflate(R.layout.fragment_sign_up, container, false);
        final Switch switchusertype=signupview.findViewById(R.id.userchooseswitch);
        TextView consumersTextView=signupview.findViewById(R.id.consumersTextView);
        TextView sellersTextView=signupview.findViewById(R.id.sellersTextView);
        final ConstraintLayout usertypeViewLayout=signupview.findViewById(R.id.usertypeViewLayout);

        switchusertype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchusertype.isChecked()){
                    usertypeViewLayout.removeAllViews();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                    android.support.v4.app.Fragment fragment=new sellersSignUpFragment();
                    fragmentTransaction.replace(R.id.usertypeViewLayout,fragment,null).commit();

                }else{
                    usertypeViewLayout.removeAllViews();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                    android.support.v4.app.Fragment fragment=new consumersSignUpFragment();
                    fragmentTransaction.replace(R.id.usertypeViewLayout,fragment,null).commit();
                }

            }
        });

        android.support.v4.app.FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
        android.support.v4.app.Fragment fragment=new consumersSignUpFragment();
        fragmentTransaction.add(R.id.usertypeViewLayout,fragment,null).commit();

        return signupview;
    }

}
