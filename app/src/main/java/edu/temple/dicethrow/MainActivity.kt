package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Another way to do it "android:id="@+id/fragmentContainerView" from activity_main layout if that does not exist (WARNING: memory leak)
        //supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, DieFragment()).commit()

        findViewById<Button>(R.id.rollDiceButton).setOnClickListener{
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as DieFragment).throwDie()
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as DieFragment).throwDie()
        }
    }
}