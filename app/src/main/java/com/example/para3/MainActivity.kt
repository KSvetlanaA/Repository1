package com.example.para3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.PersistableBundle
import android.widget.Button
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

     private var math : MathEval = MathEval()
    fun Factorial(num : Int) : Double {

        if (num > 1000) return Double.POSITIVE_INFINITY
        if (num < 1) return Double.NaN

        var factorial = 1.0

        for (i in 1..num) {
            factorial *= i
        }

        return factorial
    }

    fun onNumberClick(view: View) {
        view as Button

        if (text2.text != "")
        {
            if (view.text.isDigitsOnly())
                text2.text = text2.text.toString() + view.text.toString()
            else
            {
                if (text2.text[text2.length() - 1].isDigit())
                    text2.text = text2.text.toString() + view.text.toString()
                else
                {
                    if(text2.text[text2.length() - 1] == '(' || text2.text[text2.length() - 1] == ')'){
                        if(view.text.isDigitsOnly()==false)
                        text2.text = text2.text.toString() + view.text.toString()
                    }
                    else
                        text2.text = text2.text.substring(0, text2.text.length - 1) + view.text
                }
            }
        }
        else
        {
            text2.text = text2.text.toString() + view.text.toString()
        }

        try {
            val result : Double = math.evaluate(text2.text.toString())
        }
        catch (e : Exception){
        }
    }

    fun onZClick(view: View) {
        view as Button

        if (text2.text != "")
        {
            var s = text2.text.length-1
            var p = 0
            while (text2.text[s].isDigit() || text2.text[s] == '.')
            {
                if (text2.text[s] == '.') p++
                if (s > 0) s--
                else
                    break
            }
            if (p == 0)
                text2.text = text2.text.toString() + '.'
        }
        else
        {
            if (text2.text == "" )
                text2.text = "0.0"
        }
    }

    fun onFClick(view: View) {
        view as Button

        try {
            val result : Double = math.evaluate(text2.text.toString())
            text2.text = Factorial(result.toInt()).toString()
        }
        catch (e: Exception){

        }
    }

    fun onCClick(view: View) {
        text2.text = ""
    }

    fun onCEClick(view: View) {

        if (text2.text.isEmpty()) return
        text2.text = text2.text.toString().substring(0, text2.text.toString().length - 1)

        try {
            val result : Double = math.evaluate(text2.text.toString())
        }
        catch (e : Exception){
        }
    }

    fun onEqClick(view: View) {

        try {
            val result : Double = math.evaluate(text2.text.toString())
            text2.text = result.toString()
        }
        catch (e : Exception){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        if (outState != null)
        {
            outState.run{
                putString("Mem", text2.text.toString())
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        text2.text = savedInstanceState.getString("Mem")
    }

    fun onScClick(view: View) {
        view as Button

        try {
            val result : Double = math.evaluate(text2.text.toString())
            text2.text = sqrt(result.toDouble()).toString()
        }
        catch (e: Exception){

        }
    }

    fun onDroClick(view: View) {

        if (text2.text != "" && text2.text != "0")
        {
            var result : Double = math.evaluate(text2.text.toString())
            text2.text = "1/(${text2.text})"
            result = math.evaluate(text2.text.toString())
        }
        else
            text2.text = "Error"
    }

    fun onScobClick(view: View) {
        view as Button
        text2.text = text2.text.toString() + view.text.toString()
        try {
            val result : Double = math.evaluate(text2.text.toString())
        }
        catch (e : Exception){
        }
    }
}

