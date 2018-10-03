package in.vivekchoudhary.com.deliberr_app.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.vivekchoudhary.com.deliberr_app.R;
import in.vivekchoudhary.com.deliberr_app.model.pojo.launches.Launch;
import in.vivekchoudhary.com.deliberr_app.util.ItemClickSupport;
import in.vivekchoudhary.com.deliberr_app.view.activities.MainActivity;
import in.vivekchoudhary.com.deliberr_app.view.adapters.RecViewAdapter;

/**
 * Created by cvivek on 12-09-2018.
 */

public class LaunchFragment extends Fragment {
    public static final String DETAIL_DATA = "DETAIL_DATA";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Launch> launchList;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments()!=null) launchList = getArguments().getParcelableArrayList(MainActivity.JSON_DATA);
        View view = inflater.inflate(R.layout.launch_fragment, container, false);
        recyclerView = view.findViewById(R.id.launchesrecview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecViewAdapter(getContext(),launchList);
        recyclerView.setAdapter(mAdapter);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ((MainActivity)getActivity()).getSupportActionBar().setTitle("Launch Details");
                Bundle bundle = new Bundle();
                bundle.putParcelable(DETAIL_DATA, launchList.get(position));
                Fragment frag = new DetailFragment();
                frag.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainlayout,frag,"Detail Fragment");
                transaction.commit();

            }
        });
    }
}
