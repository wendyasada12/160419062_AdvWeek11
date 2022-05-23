package com.ubaya.a160419062_advweek4.view

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419062_advweek4.R
import com.ubaya.a160419062_advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.a160419062_advweek4.model.Student
import com.ubaya.a160419062_advweek4.util.loadImage
import com.ubaya.a160419062_advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener, ButtonNotifClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = FragmentStudentDetailBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentID)

            observeViewModel()
            dataBinding.listenerupd = this
            dataBinding.listenernotif = this
        }
    }
    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner){
            dataBinding.studentdetail = it
//            textID.setText(it.id)
//            textName.setText(it.name)
//            editDob.setText(it.dob)
//            editPhone.setText(it.phone)
//            imageStudentDetail.loadImage(it.photoUrl,progressLoadingStudentDetail)
//            buttonCreateNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe{
//                        Log.d("mynotif", "Notification Delay after 5 second")
//                        student.name?.let{ studentName ->
//                            MainActivity.showNotification(studentName, "Notification created",
//                                R.drawable.ic_baseline_person_24)
//
//                        }
//                    }
//                }
//            }
        }
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(v.context, "Updated", Toast.LENGTH_SHORT).show()
    }

    override fun onButtonNotifClickListener(v: View) {
        val student = viewModel.studentLiveData.value
        Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        Log.d("mynotif", "Notification Delay after 5 second")
                        student?.name?.let { studentName ->
                            MainActivity.showNotification(
                                studentName, "Notification created",
                                R.drawable.ic_baseline_person_24
                            )
                        }
                    }
    }
}