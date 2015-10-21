package com.lap.bellapp.bellapp_android.domain.interactor;

import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.repository.StaffRepository;

import rx.Observable;

/**
 * Created by juangarcia on 10/21/15.
 */
public class StaffAppontmentsListUseCase extends UseCase {

    private final int userId;
    private final StaffRepository staffRepository;


    public StaffAppontmentsListUseCase(int userId, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, StaffRepository staffRepository) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;
        this.staffRepository = staffRepository;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return this.staffRepository.getUser(this.userId);
    }
}
