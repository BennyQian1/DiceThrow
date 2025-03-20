package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"

    val PrevRoll = "PREVROLL"

    var RollValue = 0

    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    lateinit var dieViewModeling : DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
            dieViewModeling = ViewModelProvider(this)[DieViewModel::class.java]
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PrevRoll, RollValue)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModeling.getCurrentRoll().observe(viewLifecycleOwner) {
            dieTextView.text = it.toString()
        }

        if (savedInstanceState == null) {
            throwDie()
        }
    }

    fun throwDie() {
        dieViewModeling.setCurrentRoll(Random.nextInt(dieSides) + 1)
    }
}