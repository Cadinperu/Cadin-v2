package com.example.cadin.ui.panelnotify;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PanelNotifyViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText;

    public PanelNotifyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Notify fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
