package gd.jeet.com.jeetendrafibtest.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gd.jeet.com.jeetendrafibtest.R;

public class SecondFragment extends Fragment {

    private static final String TAG = SecondFragment.class.getName();
    private List<SecondViewModel> mViewModel;
    private View rootView;
    private SummaryAdapter summaryAdapter;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.second_fragment, container, false);
        if (mViewModel == null)
        mViewModel = new ArrayList<SecondViewModel>();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.summarylist);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        summaryAdapter = new SummaryAdapter(mViewModel, R.layout.seconditem);
        recyclerView.setAdapter(summaryAdapter);

    }

    public void setSecondViewModel(List<SecondViewModel> mSecondViewModels){
        if (mViewModel == null)
            mViewModel = new ArrayList<SecondViewModel>();
        else if (mViewModel != null && mViewModel.size() > 0)
            mViewModel.clear();
        mViewModel.addAll(mSecondViewModels);
    }
}