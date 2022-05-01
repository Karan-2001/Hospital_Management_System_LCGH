package com.example.lion_nav_barhomepage.patientdashboard.appointment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lion_nav_barhomepage.Appointment.appointment
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsProfileFragment
import com.example.lion_nav_barhomepage.doctors.data
import org.w3c.dom.Text

class AppointmentAdapter (val context: PatientAppointmentsFragment,var app_list: ArrayList<appointment>):
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.aid)
        val doc_name = itemView.findViewById<TextView>(R.id.dname)
        val date = itemView.findViewById<TextView>(R.id.date)
        val timeslot =  itemView.findViewById<TextView>(R.id.time)
        val status =  itemView.findViewById<TextView>(R.id.status)
    }
    override fun onBindViewHolder(p0: AppointmentAdapter.ViewHolder, p1: Int) {
        p0?.id?.text =  app_list[p1].appointment_id
        p0?.doc_name?.text = app_list[p1].name
        p0?.date?.text = app_list[p1].date
        p0?.timeslot?.text = app_list[p1].timeslot
        p0?.status?.text = app_list[p1].status
        Log.e("on","array:${app_list}")
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AppointmentAdapter.ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.appointment_listcard, p0, false)
        return AppointmentAdapter.ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return app_list.size
    }
}
