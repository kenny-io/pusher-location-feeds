package com.projects.ekene.pusher_location_feeds

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by EKENE on 4/12/2018.
 */
class Adapter(private val mContext: AppCompatActivity)
    : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var arrayList: ArrayList<Model> = ArrayList()

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {
        val latLng = LatLng(arrayList[position].latitude,arrayList[position].longitude)
        Log.e("TAG",arrayList[position].longitude.toString())
        holder.mapFragment.getMapAsync(OnMapReadyCallback { map ->
            map.addMarker(MarkerOptions()
                    .title(arrayList[position].username)
                    .position(latLng))

            val cameraPosition = CameraPosition.Builder().target(latLng).zoom(17f).build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.custom_view, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mapFragment = mContext.supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

    }

    fun addItem(model: Model) {
        this.arrayList.add(model)
        notifyDataSetChanged()
    }

}