package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import android.content.Context;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by juanpablogarcia on 1/9/16.
 */
public class CompanyDetailPresenter implements ICompanyDetailPresenter {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final Context context;
    private final DataManager dataManager;
    private Subscription subscription = Subscriptions.empty();

    private Company loadedCompany;


    public CompanyDetailPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.context = context;
        this.dataManager = dataManager;
    }

    @Override
    public void setUpLoadedCompany(Company company) {
        this.loadedCompany = company;
    }

    @Override
    public Company getLoadedCompany() {
        return loadedCompany;
    }
}
