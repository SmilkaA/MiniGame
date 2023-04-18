package com.test.minigame.gameplay.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.myapplication.R

class ScoresFragment : Fragment() {

    companion object {
        fun newInstance() = ScoresFragment()
    }

    private lateinit var viewModel: ScoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scores, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScoresViewModel::class.java)
        // TODO: Use the ViewModel
    }

}