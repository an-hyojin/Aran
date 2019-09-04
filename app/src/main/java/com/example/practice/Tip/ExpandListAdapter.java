package com.example.practice.Tip;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.practice.R;

import java.util.ArrayList;

public class ExpandListAdapter implements ExpandableListAdapter {
    ArrayList<String> titles;
    ArrayList<ArrayList<SolutionFrag.StringHolder>> texts;
    Context context;
    LayoutInflater inflater = null;
    public ExpandListAdapter(Context context, ArrayList<String> title, ArrayList<ArrayList<SolutionFrag.StringHolder>> text){
        this.context= context;
        this.titles = title;
        this.texts = text;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return texts.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return texts.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView =  inflater.inflate(R.layout.solution_title, parent, false);
        }
        TextView title = (TextView)convertView.findViewById(R.id.titleSolution);
        title.setText(titles.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.solution_text, parent, false);
        }
        ArrayList<SolutionFrag.StringHolder> temp = texts.get(groupPosition);
        TextView tempT = (TextView)convertView.findViewById(R.id.solutionText0);
        tempT.setVisibility(View.GONE);
        tempT =(TextView)convertView.findViewById(R.id.solutionText1);
        tempT.setVisibility(View.GONE);
        tempT =(TextView)convertView.findViewById(R.id.solutionText2);
        tempT.setVisibility(View.GONE);

        int level =temp.get(childPosition).level;
        TextView textSolution;
        if(level==0){
            textSolution = (TextView)convertView.findViewById(R.id.solutionText0);
        }else if(level==1){
            textSolution = (TextView)convertView.findViewById(R.id.solutionText1);
        }else{
            textSolution = (TextView)convertView.findViewById(R.id.solutionText2);
        }
        textSolution.setVisibility(View.VISIBLE);

        textSolution.setText(temp.get(childPosition).string);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
