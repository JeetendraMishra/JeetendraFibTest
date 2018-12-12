package gd.jeet.com.jeetendrafibtest.ui.main;

import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private String sequence;
    private String value;

    public MainViewModel(String sequence, String value) {
        this.sequence = sequence;
        this.value = value;
    }

    public String getSequence() {
        return sequence;
    }

    public String getValue() {
        return value;
    }
}
