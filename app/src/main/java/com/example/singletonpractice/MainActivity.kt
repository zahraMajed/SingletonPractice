package com.example.singletonpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var tvDate: TextView
    lateinit var tvResult:TextView
    lateinit var edAmount: EditText
    lateinit var btnConvet: Button
    lateinit var spinner: Spinner

    var myDataa : myData? = null
    var selectedCur=0//store index of Curnlist to help in converting


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDate=findViewById(R.id.tvDate)
        tvResult=findViewById(R.id.tvResult)
        edAmount=findViewById(R.id.edAmount)
        btnConvet=findViewById(R.id.btnConvert)
        spinner=findViewById(R.id.spinner)

        if (spinner != null) {

            //add list as item in drop down menu
            spinner.adapter=ArrayAdapter(this, android.R.layout.simple_spinner_item, constantValues.Curnlist)

            //to know which currency -index- user chose then save the index in selectedCur
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                //method 1:
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    selectedCur = position
                }
                //method 2:
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }//end if

        btnConvet.setOnClickListener(){
            var numToCon = edAmount.text.toString().toDouble()
            //from here
            retrofit.getCurrency (onResult = {
                myDataa=it
                when(selectedCur){
                    0 -> resultDisplay( retrofit.calc(myDataa?.eur?.inr?.toDouble()!!, numToCon) )
                    1 -> resultDisplay( retrofit.calc(myDataa?.eur?.usd?.toDouble()!!, numToCon))
                    2 -> resultDisplay( retrofit.calc(myDataa?.eur?.aud?.toDouble()!!, numToCon))
                    3 -> resultDisplay( retrofit.calc(myDataa?.eur?.sar?.toDouble()!!, numToCon))
                    4 -> resultDisplay( retrofit.calc(myDataa?.eur?.cny?.toDouble()!!, numToCon))
                    5 -> resultDisplay( retrofit.calc(myDataa?.eur?.jpy?.toDouble()!!, numToCon))
                }
            })//end getCurrency call

        } //end btnListener
    }

    fun resultDisplay(num: Double){
        tvResult.text= "Result: $num"
        tvResult.isVisible=true
        tvDate.text = "Date: ${myDataa?.date}"
    }//end display

}