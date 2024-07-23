package com.example.domainlogicandroidtest.domain.usecase;

import com.example.domainlogicandroidtest.domain.model.User;

import java.util.List;

public interface GetUsers {
    List<User> get();

    void getAsync(Listener listener);

    interface Listener {
        void onUsersReceived(List<User> users, boolean isCached);

        void onError(Exception e);

        void onNoInternetAvailable();
    }
}
