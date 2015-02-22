package com.example.mdeshpande.multipleactivities;


import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.app.ListFragment;
import android.widget.Button;

/*
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class DessertListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private int itemid = 0;
    private Button goback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d("COME","I AM HERE");

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<CharSequence> myListAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.dessert_list_values,android.R.layout.simple_list_item_1);
        setListAdapter(myListAdapter);
        getListView().setOnItemClickListener(this);

   }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_LONG).show();

        itemid = position;
    }





}
