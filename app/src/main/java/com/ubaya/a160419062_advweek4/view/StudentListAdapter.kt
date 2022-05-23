package com.ubaya.a160419062_advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419062_advweek4.R
import com.ubaya.a160419062_advweek4.databinding.StudentListItemBinding
import com.ubaya.a160419062_advweek4.model.Student
import com.ubaya.a160419062_advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),ButtonDetailClickListener{
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        ///val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view);
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        //val student = studentList[position]
        holder.view.student = studentList[position]
        holder.view.listener = this
//        with (holder.view){
//            textID.text = student.id
//            textName.text = student.name
//            buttonDetail.setOnClickListener {
//                val action = student.id?.let { id ->
//                    StudentListFragmentDirections.actionStudentDetail(id)
//                }
//                if (action != null)
//                {
//                    Navigation.findNavController(it).navigate(action)
//                }
//            }
//            imageStudentPhoto.loadImage(student.photoUrl, progressLoadingStudentPhoto)
//        }
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}