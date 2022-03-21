package com.ubaya.a160419062_advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419062_advweek4.model.Student

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val studentLiveData = MutableLiveData<Student>()
    val TAG = "detailTag"
    private var queue: RequestQueue? = null

    fun fetch(studentID: String?){
        //studentLiveData.value =  Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
        //       ".jpg/cc0000/ffffff")
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php?id=$studentID"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sTyple = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it,sTyple)
                studentLiveData.value = result
                Log.d("showstudent",it)
            },
            {
                Log.d("errorstudent", it.toString())
            }
        ).apply {
            tag = TAG
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
