package com.example.domainlogicandroidtest.ui.model

class User {
    var id = ""
    var name = ""
    var address = ""
    var username = ""
    var thumbnail: String? = ""
    var facebookId = ""
    var email = ""
    fun hasThumbnail(): Boolean {
        return thumbnail != null && !thumbnail!!.isEmpty()
    }
}