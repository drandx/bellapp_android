package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import android.app.Service;
import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.CompanyStaffView;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by juanpablogarcia on 1/12/16.
 */
public class CompanyStaffPresenter implements ICompanyStaffPresenter {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final Context context;
    private final DataManager dataManager;
    private Subscription subscription = Subscriptions.empty();

    private Company loadedCompany;
    private BusinessService loadedService;
    private List<StaffEntity> loadedStaff;

    private CompanyStaffView companyStaffView;

    public CompanyStaffPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager) {
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
    public void setUpLoadedService(BusinessService service) {
        this.loadedService = service;
    }

    @Override
    public Company getLoadedCompany() {
        return loadedCompany;
    }

    @Override
    public BusinessService getloadedService() {
        return loadedService;
    }

    @Override
    public void loadStaff() {
        companyStaffView.showLoadingView();
        subscription = dataManager.getStaffsByService(loadedService.id)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(new Subscriber<List<StaffEntity>>() {
                    @Override
                    public void onCompleted() {
                        companyStaffView.hideLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("CompanyStaffPresenter",e.getMessage());
                    }

                    @Override
                    public void onNext(List<StaffEntity> staff) {
                        loadedStaff = staff;
                        companyStaffView.loadStaff(staff);
                    }
                });
    }

    @Override
    public void configureView(CompanyStaffView view) {
        this.companyStaffView = view;
    }

    @Override
    public List<StaffEntity> getLoadedStaff() {
        return loadedStaff;
    }
}
