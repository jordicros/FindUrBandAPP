package com.jordicros997.findurband.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jordicros997.findurband.Adapters.AdapterGrups;
import com.jordicros997.findurband.Adapters.LlistaGrups;
import com.jordicros997.findurband.Models.Grup;
import com.jordicros997.findurband.R;
import com.jordicros997.findurband.Data.UserData;

import java.util.ArrayList;
import java.util.List;


public class GrupFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_grup, container, false);
        ListView lv = (ListView) RootView.findViewById(R.id.grups_llista);
        ArrayList<LlistaGrups> grups = adapterGrups(UserData.getInstance().getGrups());
        AdapterGrups adapter = new AdapterGrups(this, grups);

// get the ListView and attach the adapter
        ListView itemsListView  = (ListView) RootView.findViewById(R.id.grups_llista);
        itemsListView.setAdapter(adapter);
        return RootView;
    }
    private ArrayList<LlistaGrups> adapterGrups(List<Grup> grups)
    {
      ArrayList<LlistaGrups> temp = new ArrayList<LlistaGrups>();
      for(int i=0;i<grups.size();i++)
      {
          LlistaGrups g = new LlistaGrups(grups.get(i).nom,grups.get(i).estilMusical);
          temp.add(g);
      }
      return temp;
    }

}
