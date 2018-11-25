package com.statusstock.studyzone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.statusstock.studyzone.R;
import com.statusstock.studyzone.Subjects.Sub1DetailsActivity;
import com.statusstock.studyzone.Subjects.Sub2DetailsActivity;
import com.statusstock.studyzone.Subjects.Sub3DetailsActivity;
import com.statusstock.studyzone.Subjects.Sub4DetailsActivity;
import com.statusstock.studyzone.Subjects.Sub5DetailsActivity;
import com.statusstock.studyzone.Subjects.Sub6DetailsActivity;

public class ProfileFragment extends android.support.v4.app.Fragment {

    TextView sub1, sub2, sub3, sub4, sub5, sub6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        sub1 = view.findViewById(R.id.sub_details1);
        sub2 = view.findViewById(R.id.sub_details2);
        sub3 = view.findViewById(R.id.sub_details3);
        sub4 = view.findViewById(R.id.sub_details4);
        sub5 = view.findViewById(R.id.sub_details5);
        sub6 = view.findViewById(R.id.sub_details6);


        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub1DetailsActivity.class);
                startActivity(intent);
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub2DetailsActivity.class);
                startActivity(intent);
            }
        });

        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub3DetailsActivity.class);
                startActivity(intent);
            }
        });

        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub4DetailsActivity.class);
                startActivity(intent);
            }
        });

        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub5DetailsActivity.class);
                startActivity(intent);
            }
        });

        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub6DetailsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
