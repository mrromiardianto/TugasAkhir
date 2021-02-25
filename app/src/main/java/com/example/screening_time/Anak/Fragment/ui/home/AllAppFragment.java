package com.example.screening_time.Anak.Fragment.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Anak.BackEnd.AppInfo;
import com.example.screening_time.Anak.BackEnd.ApplicationListAdapter;
import com.example.screening_time.Anak.BackEnd.GetListOfAppsAsyncTask;
import com.example.screening_time.R;

import java.util.ArrayList;
import java.util.List;

public class AllAppFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static String requiredAppsType;
    public static AllAppFragment newInstance(String requiredApps) {
        requiredAppsType = requiredApps;
        AllAppFragment f = new AllAppFragment();
        return (f);
    }
    public AllAppFragment() {
        super();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ProgressDialog progressDialog;
    List<AppInfo> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_apps, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Apps ...");
        progressDialog.show();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ApplicationListAdapter(list , getActivity(), requiredAppsType);
        mRecyclerView.setAdapter(mAdapter);
        GetListOfAppsAsyncTask task = new GetListOfAppsAsyncTask(this);
        task.execute(requiredAppsType);
        return v;
    }
    public void showProgressBar() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        progressDialog.show();
    }
    public void hideProgressBar(){
        progressDialog.dismiss();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void updateData(List<AppInfo> list){
        this.list.clear();
        this.list.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}
