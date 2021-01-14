package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric = false;
    var lastdot = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            clr.setOnClickListener {view->
                clearText(view)
            }
             dot.setOnClickListener { view->
            checkDots(view)
             }

        div.setOnClickListener { view->
            onOperator(view)
        }
        plus.setOnClickListener { view->
            onOperator(view)
        }
        mult.setOnClickListener { view->
            onOperator(view)
        }
        min.setOnClickListener { view->
            onOperator(view)
        }
        eql.setOnClickListener { view ->
            clickEqual(view)
        }
    }

    private fun onOperator(view: View?) {
        if (lastNumeric && !isOperator(screen.text.toString())) {
            screen.append((view as Button).text)
            lastNumeric = false
            lastdot = false
        }
    }
    fun OnClickdigits(view : View){
        if(screen.text.toString().equals("INFINITE"))
            clearText(view);
        screen.append((view as Button).text)
        lastNumeric = true
    }
    private fun clearText(view : View) {
       screen.setText("")
        lastNumeric = false
        lastdot=false
    }
    fun checkDots(view : View){
        if(lastNumeric && !lastdot){
            screen.append(".")
            lastNumeric = false
            lastdot=true
        }
     }

   private fun removeUnnecessaryZero(result : String) : String{
        var value = result
       if(result.contains(".0"))
       value = result.substring(0, value.length-2)
       return value
    }

    fun isOperator(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun clickEqual(view : View){
            if(lastNumeric){
                var screenValue = screen.text.toString()
                    var prefix = ""
                try{
                    if(screenValue.startsWith("-"))
                    {
                        prefix = "-"
                        screenValue = screenValue.substring(1)
                       }

                    if(screenValue.contains("-")){
                        val splitValue = screenValue.split("-")
                        var a = splitValue[0]
                        var b = splitValue[1]
                        Log.d("tag3", "a = $a and b = $b")

                        if(!prefix.isEmpty())
                            a = "-" + a
                        screen.text = removeUnnecessaryZero((a.toDouble() - b.toDouble()).toString())
                    }
                    else if(screenValue.contains("+")) {
                        val splitValue = screenValue.split("+")
                        var a = splitValue[0]
                        var b = splitValue[1]
                        if(!prefix.isEmpty())
                            a = "-" + a
                        screen.text = removeUnnecessaryZero((a.toDouble() + b.toDouble()).toString())
                    }
                    else if(screenValue.contains("*")) {
                        val splitValue = screenValue.split("*")
                        var a = splitValue[0]
                        var b = splitValue[1]
                        if(!prefix.isEmpty())
                            a = "-" + a
                        screen.text = removeUnnecessaryZero((a.toDouble() * b.toDouble()).toString())
                    }
                    else if(screenValue.contains("/")){
                        val splitValue = screenValue.split("/")
                        var a = splitValue[0]
                        var b = splitValue[1]

                        if(!prefix.isEmpty())
                            a = "-" + a

                        if(b.equals("0"))
                            screen.text = "INFINITE"
                        else
                            screen.text = removeUnnecessaryZero((a.toDouble() / b.toDouble()).toString())
                    }
                }
                catch (e : ArithmeticException)
                {e.printStackTrace()}
            }
    }

}