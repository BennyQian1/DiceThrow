package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Another way to do it "android:id="@+id/fragmentContainerView" from activity_main layout if that does not exist (WARNING: memory leak)
        //supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, DieFragment()).commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, DieFragment()) // Ensure R.id.fragment_container exists in your layout
            .commit()

        findViewById<Button>(R.id.rollDiceButton).setOnClickListener{
            dieViewModel.rollDie()
        }
    }
}