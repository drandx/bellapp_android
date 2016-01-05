package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.CompaniesListView;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by juangarcia on 1/4/16.
 */
public class CompanyListPresenter implements ICompanyListPresenter {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private Subscription subscription = Subscriptions.empty();

    private CompaniesListView view;
    private Context context;
    private DataManager dataManager;

    @Inject
    public CompanyListPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.context = context;
        this.dataManager = dataManager;
    }

    @Override
    public void configureView(CompaniesListView view) {
        this.view = view;
    }

    @Override
    public void loadCompanies(int categoryId) {
        Log.i("CompanyListPresenter", "Loading categories...");
    }
}
