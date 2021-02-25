package com.example.screening_time.Anak.Fragment.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.screening_time.Anak.Features.Menu_ListAplikasi;
import com.example.screening_time.Anak.Features.Menu_ListJadwal;
import com.example.screening_time.Anak.Features.Menu_Profile;
import com.example.screening_time.Anak.Features.Menu_Statistic;
import com.example.screening_time.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardFragment extends Fragment {
    @BindView(R.id.Apk_user)
    ImageView ListApk;
    @BindView(R.id.Jadwal_user)
    ImageView Listjadwal;
    @BindView(R.id.Reminder_user)
    ImageView ListPenginat;
    @BindView(R.id.Status_user)
    ImageView Liststatus;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_menu__dashboard, container, false);
        ButterKnife.bind(this,root);
        ListApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoListApk();
            }
        });
        Listjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoListJadwal();
            }
        });
        Liststatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoStatus();
            }
        });
        ListPenginat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoReminder();
            }
        });
        return root;
    }

    private void GotoReminder() {
        Intent intent=new Intent(getContext(), Menu_Profile.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void GotoStatus() {
        Intent intent=new Intent(getContext(), Menu_Statistic.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void GotoListJadwal() {
        Intent intent=new Intent(getContext(), Menu_ListJadwal.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void GotoListApk() {
        Intent intent=new Intent(getContext(), Menu_ListAplikasi.class);
        startActivity(intent);
        getActivity().finish();
    }
}