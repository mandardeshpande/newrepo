package Misc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mdeshpande.multipleactivities.R;

public class DessertFragment extends Fragment implements  AbsListView.OnItemClickListener   {

    private ListView dessertList;
    int selectedItemPosition;


    public static DessertFragment newInstance() {
        DessertFragment fragment = new DessertFragment();

        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewList = (View)inflater.inflate(R.layout.fragment_dessert,container,false);

        dessertList = (ListView)viewList.findViewById(R.id.dessertList);
        String[] dessertListArray = getActivity().getResources().getStringArray(R.array.dessert_list_values);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, dessertListArray);
        dessertList.setAdapter(adapter);
        dessertList.setOnItemClickListener(this);

        dessertList.requestFocus();
        dessertList.setSelection(3);

        //System.out.println("   OBJ"+dessertList.);

        return viewList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        selectedItemPosition = position;
        Log.d("POSITION", String.valueOf(position));
    }



}
