package com.example.mdeshpande.multipleactivities;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mdeshpande.multipleactivities.dummy.DummyContent;

import java.util.ArrayList;

import Misc.DessertListAdapter;
import Misc.DessertListItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>

 * interface.
 */
public class DessertFragment extends Fragment implements AbsListView.OnItemClickListener {

    private AbsListView mListView;

    private DessertListAdapter mAdapter;
    private ArrayList<DessertListItem> dessertListItemList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dessertListItemList = new ArrayList();
        dessertListItemList.add(new DessertListItem("CupCake"));
        dessertListItemList.add(new DessertListItem("Donut"));
        dessertListItemList.add(new DessertListItem("GingerBread"));
        dessertListItemList.add(new DessertListItem("IceCream"));
        dessertListItemList.add(new DessertListItem("JellyBean"));
        mAdapter = new DessertListAdapter(getActivity(), dessertListItemList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dessert_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DessertListItem item = this.dessertListItemList.get(position);
        Toast.makeText(getActivity(), item.getItemTitle() + " Clicked!"
                , Toast.LENGTH_SHORT).show();
    }

}