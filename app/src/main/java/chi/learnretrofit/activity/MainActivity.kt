package chi.learnretrofit.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnretrofit.R
import chi.learnretrofit.retrofit.service.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.114:8080/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
            .testGet()
            .enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if (response.isSuccessful) {
                        Log.i(TAG, "Response: ${response.body()?.string()}")
                    } else {
                        Log.i(TAG, "Response code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.i(TAG, "Failure: ${t.message}")
                }
            })
    }
}