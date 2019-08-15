package com.example.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class SolutionFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.solution, container, false);
        ExpandableListView expandableListView = view.findViewById(R.id.expandableList);
        ArrayList<String> titleStrings = new ArrayList<>();
        ArrayList<String> textStrings = new ArrayList<>();

        titleStrings.add("샘이많은아이");
        textStrings.add("이렇게하세요");
        titleStrings.add("어쩌구이");
        textStrings.add("요렇게하세요");
        titleStrings.add("저쩌구은아이");
        textStrings.add("저렇게하세요");
        titleStrings.add("샘이많은아이");
        textStrings.add("이렇게하세요");
        titleStrings.add("어쩌구이");
        textStrings.add("요렇게하세요");
        titleStrings.add("저쩌구은아이");
        textStrings.add("저렇게하세요");
        titleStrings.add("샘이많은아이");
        textStrings.add("이렇게하세요");
        titleStrings.add("어쩌구이");
        textStrings.add("요렇게하세요");
        titleStrings.add("저쩌구은아이");
        textStrings.add("저렇게하세요");
        titleStrings.add("샘이많은아이");
        textStrings.add("이렇게하세요");
        titleStrings.add("어쩌구이");
        textStrings.add("요렇게하세요");
        titleStrings.add("저쩌구은아이");
        textStrings.add("저렇게하세요");


        ExpandListAdapter expandListAdapter = new ExpandListAdapter(getContext(),titleStrings, textStrings);
        expandableListView.setAdapter(expandListAdapter);
        return view;
    }
}
