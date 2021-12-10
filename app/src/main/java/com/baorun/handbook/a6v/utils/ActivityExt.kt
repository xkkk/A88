package com.baorun.handbook.a6v.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


inline fun <reified T:Activity> Activity.goActivity(finish:Boolean = false){
    Intent(this,T::class.java).also {
        this.startActivity(it)
        if(finish){
            this.finish()
        }
    }
}

fun Activity.goActivity(clazz: Class<out AppCompatActivity>, finish:Boolean = false){
    Intent(this,clazz).also {
        this.startActivity(it)
        if(finish){
            this.finish()
        }
    }
}
