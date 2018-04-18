package com.projects.ekene.pusher_location_feeds

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // list to hold our messages
        var arrayList: ArrayList<String> = ArrayList()

        // Initialize our adapter
        val adapter = Adapter(this)

        // assign a layout manager to the recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)

        // assign adapter to the recycler view
        recyclerView.adapter = adapter

        // Initialize Pusher
        val mOptions = PusherOptions()
        mOptions.setCluster("eu")
        val mPusher = Pusher("8f4ae992f5fa6cfd235b", mOptions)

        // Subscribe to a Pusher channel
        val channel = mPusher.subscribe("messages")

        // this listener recieves any new message from the server
        channel.bind("new_message") { messages, new_message, responsedata ->
            val jsonObject = JSONObject(responsedata)
            arrayList.add(jsonObject.getString("message"))
            runOnUiThread { adapter.setList(arrayList) }
        }
        mPusher.connect()

        // We check for button clicks and if any text was supplied, we send the message
        sendBtn.setOnClickListener({
            if (textInput.text.isNotEmpty()) {
                sendMessage(textInput.text.toString())
            }
        })

    } // end of onCreate method

    fun sendMessage(message: String) {
        val mCall = Client().getClient().sendMessage(message)

        mCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                textInput.setText("")
                hideKeyboard(this@MainActivity)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_LONG).show()
            }
        })
    } // end of sendMessage method

    fun hideKeyboard(activity: Activity) {
        val msgWindow = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        // Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus

        // If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }

        msgWindow.hideSoftInputFromWindow(view.windowToken, 0)
    } // end of hideKeybnoard method
}