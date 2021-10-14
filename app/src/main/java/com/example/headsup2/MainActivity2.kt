package com.example.headsup2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notifyAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext


class MainActivity2 : AppCompatActivity() {
    var celedata: List<CelebrityDetails.Datum>? = null
    var celebrities = ArrayList<ArrayList<String>>()
    var cele= ArrayList<String>()
    var time:Long=60000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        if (resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            getCelebrityDetails(onResult = {
                celedata = it
               val datumList = celedata
                for (datum in datumList!!) {
                    celebrities+= arrayListOf(arrayListOf("${datum.pk}","${datum.name}","${datum.taboo1}","${datum.taboo2}","${datum.taboo3}"))
                }
                celebrities.shuffle()
                CoroutineScope(Dispatchers.IO).launch {
                    val result=useradv()
                    withContext(Dispatchers.Main){
                        tvname.text=result[1]
                        tvta1.text=result[2]
                        tvta2.text=result[3]
                        tvta3.text=result[4]
                        timer(time)
                    }
                }
            })
        }
        
    }

    private fun getCelebrityDetails(onResult: (List<CelebrityDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getCelebrityDetails()?.enqueue(object : Callback<List<CelebrityDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<CelebrityDetails.Datum>>,
                    response: Response<List<CelebrityDetails.Datum>>
                ) {
                    onResult(response.body())
                }
                override fun onFailure(call: Call<List<CelebrityDetails.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }

    private suspend fun useradv(): ArrayList<String>{
            cele=celebrities[0]
        return cele
    }

    private fun timer(t:Long){
        object : CountDownTimer(t, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTime.text = "Time: ${millisUntilFinished / 1000}"
                time=millisUntilFinished
            }

            override fun onFinish() {
                tvTime.text = "Time: --"
                time=60000
            }
        }.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("time", time)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        time= savedInstanceState.getLong("time", 0)
    }
}
