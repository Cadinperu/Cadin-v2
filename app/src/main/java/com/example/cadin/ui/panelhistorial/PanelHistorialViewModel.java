package com.example.cadin.ui.panelhistorial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PanelHistorialViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText;

    public PanelHistorialViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is historial fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
