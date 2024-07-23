package com.example.domainlogicandroidtest.domain.repository;

import android.content.Context;

import com.example.domainlogicandroidtest.domain.model.User;
import com.example.domainlogicandroidtest.domain.repository.cachepolicy.CachePolicy;
import com.example.domainlogicandroidtest.domain.repository.cachepolicy.NoCachePolicy;
import com.example.domainlogicandroidtest.domain.repository.cachepolicy.TimedCachePolicy;
import com.example.domainlogicandroidtest.domain.usecase.GetUsers;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements GetUsers {
    Context context;
    CachePolicy cachePolicy;
    GetUsers apiDataSource;
    List<User> users = new ArrayList<>();

    public UserRepository(Context context, GetUsers apiDataSource) {
        this.context = context;
        this.apiDataSource = apiDataSource;
        this.cachePolicy = new NoCachePolicy();
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }

    @Override
    public List<User> get() {
        invalidateCacheIfNecessary(cachePolicy, users);

        if (users != null && users.size() > 0) {
            return users;
        }

        List<User> apiUsers = apiDataSource.get();
        cachePolicy = new TimedCachePolicy(TimedCachePolicy.ONE_MINUTE);
        users = apiUsers;
        return users;
    }

    private void invalidateCacheIfNecessary(CachePolicy cachePolicy, List<User> users) {
        if (!cachePolicy.isCacheValid()) {
            users.clear();
        }
    }

    @Override
    public void getAsync(final Listener listener) {
        invalidateCacheIfNecessary(cachePolicy, users);

        if (users != null && users.size() > 0) {
            listener.onUsersReceived(users, true);
        } else {
            apiDataSource.getAsync(new Listener() {
                @Override
                public void onUsersReceived(List<User> list, boolean isCached) {
                    users = list;
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
            });
        }
    }

}
