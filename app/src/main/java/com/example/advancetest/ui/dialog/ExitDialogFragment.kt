package com.example.advancetest.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.advancetest.databinding.DialogExitBinding

class ExitDialogFragment : DialogFragment()  {

    private var _binding: DialogExitBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogExitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        setupBindings()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        //dialog.window?.attributes?.windowAnimations = R.style.checkDialogAnimation
        return dialog
    }

    fun setupBindings() {
        binding.apply {
            cancelButton.setOnClickListener{
                dismiss()
            }
            acceptButton.setOnClickListener {
                requireActivity().finish()
            }
        }
    }
}