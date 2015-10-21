package com.lap.bellapp.bellapp_android.domain.interactor;

import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.repository.StaffRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by juangarcia on 10/17/15.
 */
public class StaffLoginUseCase extends UseCase {

    private final StaffRepository staffRepository;
    private int userId;
    private String email;
    private String password;

    @Inject
    public StaffLoginUseCase(int userId, String email, String password,
                             StaffRepository staffRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.staffRepository = staffRepository;
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return staffRepository.getUserLogin(email,password);
    }

    @Override
    public void execute(Subscriber UseCaseSubscriber){
        super.execute(UseCaseSubscriber);
    }

}
