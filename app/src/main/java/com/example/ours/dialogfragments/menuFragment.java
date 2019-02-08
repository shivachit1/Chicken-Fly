package com.example.ours.dialogfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ours.R;
import com.example.ours.activities.Level1;
import com.example.ours.activities.LogInActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class menuFragment extends DialogFragment {


    public menuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menuView= inflater.inflate(R.layout.fragment_menu, container, false);

        Button resume=menuView.findViewById(R.id.resumebutton);

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();

            }
        });

        Button restart=menuView.findViewById(R.id.resumebutton);



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Level1.class);
                intent.putExtra("Level 1", "Level 1");
                startActivity(intent);
            }
        });

        Button quit=menuView.findViewById(R.id.Quit);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LogInActivity.class);
                intent.putExtra("LogIn Activity", "LogIn Activity");
                startActivity(intent);
            }
        });

        return menuView;
    }

}

