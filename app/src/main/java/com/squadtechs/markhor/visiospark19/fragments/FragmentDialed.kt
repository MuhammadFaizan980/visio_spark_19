package com.squadtechs.markhor.visiospark19.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.visiospark19.R
import com.squadtechs.markhor.visiospark19.adapters.ListAdapter
import com.squadtechs.markhor.visiospark19.models.ModelMain
import com.squadtechs.markhor.visiospark19.utils.Utils
import java.util.*
import kotlin.collections.ArrayList

class FragmentDialed() : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var cursor: Cursor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_fragment_main, container, false)
        initViews()
        getCallDetails(activity!!.applicationContext)
        populateRecyclerView()
        return mView
    }

    @SuppressLint("MissingPermission")
    private fun getCallDetails(context: Context): Cursor {

        askPermissions()
        cursor = context.getContentResolver()
            .query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                CallLog.Calls.DATE + " DESC"
            ) as Cursor
        return cursor
    }

    private fun askPermissions() {
        val arr = arrayOfNulls<String>(2)
        arr[0] = android.Manifest.permission.READ_CALL_LOG
        arr[1] = android.Manifest.permission.WRITE_CALL_LOG
        if (ContextCompat.checkSelfPermission(
                activity!!.application,
                android.Manifest.permission.READ_CALL_LOG
            )
            == PackageManager.PERMISSION_DENIED
            || ContextCompat.checkSelfPermission(
                activity!!.application,
                android.Manifest.permission.WRITE_CALL_LOG
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(activity!!, arr, Utils.PERMISSION_REQUEST_CODE)
        }
    }

    private fun populateRecyclerView() {
        val list = ArrayList<ModelMain>()
        val adapter = ListAdapter(activity!!.applicationContext, list, 0)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        val number = cursor.getColumnIndex(CallLog.Calls.NUMBER)
        val type = cursor.getColumnIndex(CallLog.Calls.TYPE)
        val date = cursor.getColumnIndex(CallLog.Calls.DATE)
        val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)
        while (cursor.moveToNext()) {
            val phNumber = cursor.getString(number)
            val callType = cursor.getString(type)
            val callDate = cursor.getString(date)
            val callDayTime = Date(callDate.toLong())
            val callDuration = cursor.getString(duration)
            var dir: String? = null
            val dircode = Integer.parseInt(callType)
            when (dircode) {
                CallLog.Calls.OUTGOING_TYPE -> dir = "OUTGOING"
                CallLog.Calls.INCOMING_TYPE -> dir = "INCOMING"

                CallLog.Calls.MISSED_TYPE -> dir = "MISSED"
            }
            val obj = ModelMain()
            obj.number = phNumber
            obj.type = dir
            obj.date = callDayTime.toString()
            obj.duration = callDuration
            list.add(obj)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recyclerview)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Utils.PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()) {
            for (i in grantResults) {
                if (i == PackageManager.PERMISSION_DENIED) {
                    Utils.showErrorDialog(
                        activity!!.applicationContext,
                        "Permissions are required for this app to work properly."
                    )
                    return
                }
            }
        }
    }

}
