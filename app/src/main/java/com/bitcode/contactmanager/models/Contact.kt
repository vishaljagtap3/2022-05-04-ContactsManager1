package com.bitcode.contactmanager.models

data class Contact(
    var id : Int,
    var name : String,
    var phoneNumber : String?,
    var email : String?,
    var imageId : Int?
)