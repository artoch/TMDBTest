package com.example.advancetest.ui.dialog.trailer

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advancetest.common.const.EMPTY_STRING
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.databinding.DialogTrailerListBinding

class TrailerListDialog : DialogFragment()  {

    private var _binding: DialogTrailerListBinding? = null

    private val binding get() = _binding!!

    private val trailerList = ArrayList<MovieTrailersDomain>()

    private var title = EMPTY_STRING

    private var action: (MovieTrailersDomain) -> Unit = {}

    private val adapter by lazy {
        TrailerAdapter(trailerList, ::sendTrailerKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogTrailerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        //dialog.window?.attributes?.windowAnimations = R.style.checkDialogAnimation
        return dialog
    }

    private fun sendTrailerKey(data: MovieTrailersDomain){
        action.invoke(data)
        dismiss()
    }

    fun setTrailers(data: List<MovieTrailersDomain>){
        trailerList.clear()
        trailerList.addAll(data)
    }

    fun setAction(action: (MovieTrailersDomain) -> Unit){
        this.action = action
    }

    fun setTitle(data:String){
        title = data
    }

    private fun setupBindings() {
        binding.apply {
            tvTitle.text = title 
            rvTrailer.layoutManager = LinearLayoutManager(requireContext())
            rvTrailer.adapter = adapter
        }
    }
}