package com.bitcode.contactmanager.models

import java.io.Serializable

data class Contact(
    var id : Int,
    var name : String,
    var phoneNumber : String?,
    var email : String?,
    var imageId : Int?
) : Serializable