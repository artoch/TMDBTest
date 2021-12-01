package com.example.advancetest.common.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController



fun Activity.getNavController(id:Int) =  findNavController(id)
fun Fragment.getNavController(id: Int) = requireActivity().findNavController(id)
fun NavController.popToNewDestinationBase(whereGo: Int, args: NavArgs? = null){
    this.navigate(whereGo,null,
        NavOptions.Builder().setPopUpTo(this.graph.startDestination, true).build())
}
