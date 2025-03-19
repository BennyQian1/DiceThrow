package edu.temple.dicethrow

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    private val PrevRoll = "PREVROLL"
    private var RollValue = 0
    lateinit var dieTextView: TextView
    private var dieSides: Int = 6

    companion object {
        private val DIESIDE = "sidenumber"
        fun newInstance(numSides: Int): DieFragment {
            val fragment = DieFragment()
            val args = Bundle()
            args.putInt("numSides", numSides)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        promptForDieSides()
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
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
        throwDie()

        if (savedInstanceState == null) {
            throwDie()
        }
        else {
            RollValue = savedInstanceState.getInt(PrevRoll)
            dieTextView.text = RollValue.toString()
        }

    }

    fun throwDie() {
        RollValue = Random.nextInt(1, dieSides + 1)
        dieTextView.text = RollValue.toString()
    }
    private fun promptForDieSides() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter Number of Sides for the Die")
        // Set up the input
        val input = EditText(requireContext())
        builder.setView(input)
        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, which ->
            val inputValue = input.text.toString()
            dieSides = inputValue.toIntOrNull() ?: 6 // Default to 6 if input is invalid
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()
    }
}

