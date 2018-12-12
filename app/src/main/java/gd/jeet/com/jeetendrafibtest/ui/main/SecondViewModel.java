package gd.jeet.com.jeetendrafibtest.ui.main;

import android.arch.lifecycle.ViewModel;

public class SecondViewModel extends ViewModel {

    private String number;
    private String totalTime;

    public SecondViewModel(String number, String totalTime) {
        this.number = number;
        this.totalTime = totalTime;
    }

    public String getNumber() {
        return number;
    }

    public String getTotalTime() {
        return totalTime;
    }
}