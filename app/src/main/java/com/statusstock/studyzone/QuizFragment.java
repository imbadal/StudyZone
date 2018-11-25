package com.statusstock.studyzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.statusstock.studyzone.Subjects.Sub1Activity;
import com.statusstock.studyzone.Subjects.Sub2Activity;
import com.statusstock.studyzone.Subjects.Sub3Activity;
import com.statusstock.studyzone.Subjects.Sub4Activity;
import com.statusstock.studyzone.Subjects.Sub5Activity;
import com.statusstock.studyzone.Subjects.Sub6Activity;

public class QuizFragment extends android.support.v4.app.Fragment {


    TextView sub1, sub2, sub3, sub4, sub5, sub6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        sub1 = view.findViewById(R.id.sub1);
        sub2 = view.findViewById(R.id.sub2);
        sub3 = view.findViewById(R.id.sub3);
        sub4 = view.findViewById(R.id.sub4);
        sub5 = view.findViewById(R.id.sub5);
        sub6 = view.findViewById(R.id.sub6);


        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub1Activity.class);
                startActivity(intent);
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub2Activity.class);
                startActivity(intent);
            }
        });

        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub3Activity.class);
                startActivity(intent);
            }
        });

        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub4Activity.class);
                startActivity(intent);
            }
        });

        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub5Activity.class);
                startActivity(intent);
            }
        });

        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub6Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
