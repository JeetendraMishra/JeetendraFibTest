package gd.jeet.com.jeetendrafibtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import gd.jeet.com.jeetendrafibtest.ui.main.MainFragment;
import gd.jeet.com.jeetendrafibtest.ui.main.SecondFragment;
import gd.jeet.com.jeetendrafibtest.ui.main.SecondViewModel;

public class MainActivity extends AppCompatActivity implements MainFragment.SendSecondViewModel {

    private MenuItem actionNext;
    private List<SecondViewModel> secondList;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        secondList = new ArrayList<SecondViewModel>();
        count = 0;

        if (savedInstanceState == null) {
            MainFragment mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .addToBackStack(null)
                    .commit();
        }

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                getSupportActionBar().setHomeButtonEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                actionNext.setVisible(true);
                return true;

            case R.id.action_next:
                actionNext = item;
                SecondFragment secondFragment = SecondFragment.newInstance();
                secondFragment.setSecondViewModel(secondList);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, secondFragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                item.setVisible(false);
                return true;

                default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void sendSecondViewModel(int number, String totalTime) {
        count++;
        secondList.add(new SecondViewModel(count + "--> "+number, totalTime));
    }
}
