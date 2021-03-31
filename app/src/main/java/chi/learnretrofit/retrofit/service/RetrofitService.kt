package chi.learnretrofit.retrofit.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("Chi/GET")
    fun testGet(): Call<ResponseBody>
}