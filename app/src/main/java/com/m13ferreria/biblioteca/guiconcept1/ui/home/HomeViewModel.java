package com.m13ferreria.biblioteca.guiconcept1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<String>> mList;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este texto esta escrito en el HomeViewModel.java");

        mList = new MutableLiveData<>();
        List<String> initialList = new ArrayList<>();
        initialList.add("Item 1");
        initialList.add("Item 2");
        initialList.add("Item 3");
        mList.setValue(initialList);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getList() {
        return mList;
    }
}