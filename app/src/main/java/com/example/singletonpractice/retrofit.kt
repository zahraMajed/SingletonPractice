package com.example.singletonpractice

import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class retrofit {
    companion object {
        val apiInterface=APIclint().getClient()?.create(APIInterface::class.java)

        fun getCurrency(onResult: (myData?) -> Unit){

            apiInterface?.getDate()?.enqueue(object : Callback<myData?> {
                override fun onResponse(call: Call<myData?>, response: Response<myData?>) {
                    onResult(response.body())
                }

                override fun onFailure(call: Call<myData?>, t: Throwable) {
                    onResult(null)
                }
            })

        }//end getResult()

        fun calc(i:Double, numTo:Double):Double{
            var convertedNum= 0.0
            if (i != null){
                convertedNum=(numTo*i)
            }
            return convertedNum
        }//end calc

    }//end companion

}//end class