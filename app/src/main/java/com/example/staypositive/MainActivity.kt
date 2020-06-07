package com.example.staypositive

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var count : Int = 0 //used to keep a count of how many times the NEXT QUOTE button is pressed

    lateinit var editText : EditText //used to input name of the user
    lateinit var greetings : TextView //Displays "Greetings <name>!"
    lateinit var quoteForText : TextView //displays: "Today's Quote"
    lateinit var quoteText : TextView //TextView for showing the quote
    lateinit var nextQuoteButton : Button
    lateinit var quotes : Array<String> //stores the array of strings having the quotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.done_button).setOnClickListener(){
            modify(it)
        }

        nextQuoteButton = findViewById<Button>(R.id.nextquote_button)

        nextQuoteButton.setOnClickListener(){
            next(it)
        }
    }


    private fun modify(view : View){
        editText = findViewById<EditText>(R.id.name_edit)
        greetings = findViewById<TextView>(R.id.greeting_text)
        quoteForText = findViewById<TextView>(R.id.quoteForYou_text)
        quoteText = findViewById<TextView>(R.id.quote_text)
        quotes = resources.getStringArray(R.array.quotes)

        greetings.append(editText.text)
        greetings.append("!")
        //Appends user input to Greetings followed by an exclamation mark

        quoteText.text = quotes[count]
        //First quote is displayed before setting visibility of quoteText TextView to VISIBLE

        editText.visibility = View.GONE
        view.visibility = View.GONE

        greetings.visibility = View.VISIBLE
        quoteForText.visibility = View.VISIBLE
        quoteText.visibility = View.VISIBLE
        nextQuoteButton.visibility = View.VISIBLE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        //To hide the keyboard after DONE is pressed
    }

    private fun next(view : View){
        if(count==24)
            count=0
        else
            count++

        quoteText.text = quotes[count]

        // There are 25 quotes in the strings.xml file. Each time NEXT QUOTE button is pressed,
        // this function sets the TextView's text to the next quote and when the end of string array
        // is reached the first quote is displayed again.
    }

}
