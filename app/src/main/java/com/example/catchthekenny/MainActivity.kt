package com.example.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var score :Int = 0        // variable to store score
    var imageArray = ArrayList<ImageView>() //imageview array to store all the image view of kennys

    var handler : Handler = Handler()  //handler to handle runnable
    var runnable : Runnable = Runnable{ }//runnable for animation of kenny images

    //time calculation object
    var timeout =  object : CountDownTimer(10000,1000) {
        override fun onFinish() {
            textViewTime.text = "Time is off"
            handler.removeCallbacks(runnable)
            hideImages()
        }

        override fun onTick(millisUntilFinished: Long) {
            textViewTime.text = "Time: "+millisUntilFinished/1000
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize score to zeo
        score = 0

        //Initalize image array with all nine kenny image
        imageArray = arrayListOf(kenny1,kenny2,kenny3,kenny4,kenny5,kenny6,kenny7,kenny8,kenny9)

        //initially hide all images
        hideImages()
    }

    fun hideImages(){
        for(image in imageArray){
            image.visibility = View.INVISIBLE
        }
    }


    //function will start animation of kenny's images by select them randomly and make them visible
    private fun startKennyAnimaton(){
        runnable = object : Runnable{
            override fun run() {
                hideImages()
                //generating random variable
                //val random = Random()
                val index = Random.nextInt(8-0)

                //visible any one image from grid
                imageArray[index].visibility =  View.VISIBLE
                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)
    }

    //This function will be execute after kenny's image will be clicked
    fun increaseScore(view : View){
        score++
        textViewScore.text = "Score: "+score
    }


    //function is link to start button
    fun startGame(view: View){
        hideImages()
        score = 0
        textViewTime.text = "Time: 10"
        startKennyAnimaton()
        timeout.start()
    }
}
