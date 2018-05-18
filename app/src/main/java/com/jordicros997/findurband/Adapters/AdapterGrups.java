package com.jordicros997.findurband.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jordicros997.findurband.Fragments.GrupFragment;
import com.jordicros997.findurband.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 16/05/2018.
 */

public class AdapterGrups extends BaseAdapter {
    protected Context context;
    protected List<LlistaGrups> grups;
    public AdapterGrups(GrupFragment context, ArrayList<LlistaGrups> grups)
    {
        this.context = context.getActivity();
        this.grups = grups;
    }
    @Override
    public int getCount() {
        return grups.size();
    }
    public void clear(){
        grups.clear();
    }
    public void addAll(ArrayList<LlistaGrups> grups)
    {
        for(int i=0;i<grups.size();i++){
            this.grups.add(grups.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return grups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view = LayoutInflater.from(context).
                    inflate(R.layout.llista_grups,viewGroup, false);
        }
        LlistaGrups ll =(LlistaGrups) getItem(i);
        TextView title = view.findViewById(R.id.textView4);
        title.setText(ll.getTitol());
        TextView estil = view.findViewById(R.id.textView5);
        estil.setText(ll.getEstil());
        return view;
    }
}
