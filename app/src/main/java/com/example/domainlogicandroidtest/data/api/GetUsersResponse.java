package com.example.domainlogicandroidtest.data.api;


import com.example.domainlogicandroidtest.domain.model.User;

import java.util.List;


public class GetUsersResponse {
    private List<User> results;

    public List<User> getResults() {
        return results;
    }
}
