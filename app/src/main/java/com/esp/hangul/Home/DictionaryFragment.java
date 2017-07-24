package com.esp.hangul.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.esp.hangul.R;

import java.util.ArrayList;

public class DictionaryFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dictionary, container, false);
        listView = (ListView) rootView.findViewById(R.id.dictionary_list_view);
        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);
        return rootView;
    }



}
