package com.example.smartcity05.ui.bottomFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity05.MainActivity;
import com.example.smartcity05.databinding.FragmentUserBinding;
import com.example.smartcity05.ui.activity.FeedBackActivity;
import com.example.smartcity05.ui.activity.LoginActivity;
import com.example.smartcity05.ui.activity.ModifyPswActivity;
import com.example.smartcity05.ui.activity.OrderListActivity;
import com.example.smartcity05.ui.activity.UserInfoActivity;
import com.j256.ormlite.stmt.query.In;

public class UserFragment extends Fragment {
    private FragmentUserBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.llChangePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ModifyPswActivity.class));
            }
        });
        binding.llFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), FeedBackActivity.class));
            }
        });
        binding.llOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), OrderListActivity.class));
            }
        });
        binding.llUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), UserInfoActivity.class));
            }
        });
        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
