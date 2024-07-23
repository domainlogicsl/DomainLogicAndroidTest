package com.example.domainlogicandroidtest.utils

import android.content.Context
import com.example.domainlogicandroidtest.domain.usecase.ShowUserGreeting
import com.example.domainlogicandroidtest.ui.model.User

class ShowToastImpl(var context: Context) : ShowUserGreeting {
    override fun show(user: User?) {
    }
}