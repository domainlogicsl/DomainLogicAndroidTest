package com.example.domainlogicandroidtest.platform.interactor;

import com.example.domainlogicandroidtest.data.api.GetUsersApiImpl;
import com.example.domainlogicandroidtest.domain.model.User;
import com.example.domainlogicandroidtest.domain.usecase.GetUsers;

import java.util.List;
import javax.inject.Inject;

public class GetUsersInteractor implements Interactor, GetUsers, GetUsers.Listener {
    GetUsers.Listener listener = new GetUsersApiImpl.NullListener();
    GetUsers getUsers;
    Executor executor;
    MainThread mainThread;

    @Inject public GetUsersInteractor(GetUsers dataSource, Executor executor, MainThread mainThread) {
        this.getUsers = dataSource;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void onUsersReceived(List<User> list, boolean isCached) {
        listener.onUsersReceived(list, isCached);
    }

    @Override
    public void onError(Exception e) {
        listener.onError(e);
    }

    @Override
    public void onNoInternetAvailable() {
        listener.onNoInternetAvailable();
    }

    @Override
    public void run() {
        getUsers.getAsync(listener);
    }

    @Override
    public List<User> get() {
        throw new IllegalArgumentException("Please use async version of this Interactor");
    }

    @Override
    public void getAsync(GetUsers.Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        this.executor.run(this);
    }

}
