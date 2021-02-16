package com.example.minitwitter

object Constants {
    const val DIALOG_TAG = "dialogo"
    const val WITHUOTSESSION = 0
    const val ERROR_REQUEST = 1
    const val WITHOUT_INTERNET = 2
    const val SUCCESS = 3
    const val SHAREDPREFERENCES = "MinitwitterSharePreferences"

    object preferences{
        const val PREF_TOKEN = "PREF_TOKEN"
    }
    object retrofit{
        const val URL = "https://www.minitwitter.com:3001/apiv1/"
        //const val URL = "https://api.themoviedb.org/"
        const val TOKEN = "UDEMIMITWITTER"
        const val HEAD_AUTH = "Authorization"
    }
}
