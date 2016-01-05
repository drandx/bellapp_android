package com.lap.bellapp.bellapp_android.ui.presenters.Category;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.CategoriesListView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by juangarcia on 1/3/16.
 */
public class CategoryListPresenter implements ICategoryPresenter {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final DataManager dataManager;

    private Subscription subscription = Subscriptions.empty();
    private CategoriesListView view;
    private List<BusinessCategory> categories;

    @Inject
    public CategoryListPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.dataManager = dataManager;
    }

    @Override
    public BusinessCategory getCategorybyPosition(int position) {
        return null;
    }

    @Override
    public List<BusinessCategory> getCategories() {
        return categories;
    }

    @Override
    public void configureView(CategoriesListView view) {
        this.view = view;
    }

    @Override
    public void loadCategories() {
        this.view.showLoadingView();
        this.subscription = dataManager.getBusinessCategories()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(new Subscriber<List<BusinessCategory>>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BusinessCategory> businessCategory) {
                        categories = businessCategory;
                        view.loadCategories(businessCategory);
                    }
                });
    }
}
