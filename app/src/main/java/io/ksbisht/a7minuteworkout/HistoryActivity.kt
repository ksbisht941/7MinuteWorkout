package io.ksbisht.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_b_m_i.*
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history_activity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "HISTORY"
        }

        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompleteDates()
    }

    private fun getAllCompleteDates() {
        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList()

       if (allCompletedDatesList.size > 0) {
           tvHistory.visibility = View.VISIBLE
           rvHistory.visibility = View.VISIBLE
           tvNoDataAvailable.visibility = View.GONE

           Log.i("FUCKYOU:", "" + allCompletedDatesList.size)

           rvHistory.layoutManager = LinearLayoutManager(this)
           val historyAdapter = HistoryAdapter(this, allCompletedDatesList)
           rvHistory.adapter = historyAdapter
       } else {
           tvHistory.visibility = View.GONE
           rvHistory.visibility = View.GONE
           tvNoDataAvailable.visibility = View.VISIBLE
       }
    }
}