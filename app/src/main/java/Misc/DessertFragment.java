package Misc;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.Color;
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

public class DessertFragment extends Fragment implements  AbsListView.OnItemClickListener
{

    private ListView dessertList;
    int selectedItemPosition;
    Activity activity;


    public static DessertFragment newInstance() {
        DessertFragment fragment = new DessertFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewList = (View)inflater.inflate(R.layout.fragment_dessert,container,false);

        dessertList = (ListView)viewList.findViewById(R.id.dessertList);
        String[] dessertListArray = getActivity().getResources().getStringArray(R.array.dessert_list_values);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, dessertListArray);
        dessertList.setAdapter(adapter);
        dessertList.setOnItemClickListener(this);

        return viewList;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance)
    {
        super.onActivityCreated(savedInstance);
        System.out.println(getSelectedPosition());
        dessertList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        dessertList.setItemChecked(selectedItemPosition,true);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        try
        {
            ((onDessertClickHandler)activity).onDessertSelectedHandler(position);
        }
        catch(ClassCastException cce)
        {
            System.out.println("onDessertSelectedHandler must be implemented");
        }
    }

    public interface onDessertClickHandler
    {
        public void onDessertSelectedHandler(int position);
    }

    public void setSelectedPosition(int position)
    {
        this.selectedItemPosition = position;
    }

    public int getSelectedPosition()
    {
        return this.selectedItemPosition;
    }

}
