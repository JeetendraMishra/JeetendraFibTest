package gd.jeet.com.jeetendrafibtest.ui.main;

import android.app.Activity;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gd.jeet.com.jeetendrafibtest.R;

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getName();
    private List<MainViewModel> mViewModel;
    private EditText inputNumber;
    private View rootView;
    private FibAdapter fibAdapter;
    private TextView totalTime;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    SendSecondViewModel mCallback;

    public interface SendSecondViewModel{
        public void sendSecondViewModel(int number, String totalTime);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.main_fragment, container, false);
        mViewModel = new ArrayList<MainViewModel>();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        inputNumber = (EditText) rootView.findViewById(R.id.input);

        inputNumber.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_NEXT
                        || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event.getAction() == KeyEvent.KEYCODE_ENTER)) {
                    performClick(Integer.parseInt(inputNumber.getText().toString()));
                    return true;
                }
                return false;
            }
        });

        totalTime = (TextView) rootView.findViewById(R.id.totalTime);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.fiblist);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        fibAdapter = new FibAdapter(mViewModel, R.layout.item);
        recyclerView.setAdapter(fibAdapter);

    }

    private void performClick(int number){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputNumber.getWindowToken(), 0);
        Log.d(TAG, "input number: "+number);
        if (mViewModel != null && mViewModel.size() > 0){
            mViewModel.clear();
        }
        long startTime = System.currentTimeMillis();
        int t1 = 0, t2 = 1;

        for (int i = 1; i <= number; ++i)
        {
            if (i<=2) {
                mViewModel.add(new MainViewModel(String.valueOf(i), String.valueOf(i-1)));
                Log.d(TAG, i+" "+(i-1));
            } else {
                int sum = t1 + t2;
                t1 = t2;
                t2 = sum;
                mViewModel.add(new MainViewModel(String.valueOf(i), String.valueOf(sum)));
                Log.d(TAG, i+" "+sum);
            }

        }
        long endTime = System.currentTimeMillis();
        long totalTimeInMS = endTime-startTime;
        totalTime.setText(totalTimeInMS+"ms");
        mCallback.sendSecondViewModel(number, totalTimeInMS+"ms");
        fibAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (SendSecondViewModel) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SendSecondViewModel");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }
}
