package com.example.advancetest.ui.main.view_movie

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advancetest.R
import com.example.advancetest.common.error.ErrorDomain
import com.example.advancetest.common.error.ErrorEntity
import com.example.advancetest.common.extension.getNavController
import com.example.advancetest.common.extension.popToNewDestinationBase
import com.example.advancetest.common.extension.shortToast
import com.example.advancetest.common.extension.showDialog
import com.example.advancetest.common.state.movie_state.MovieState
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.databinding.FragmentMovieListBinding
import com.example.advancetest.ui.dialog.trailer.TrailerListDialog
import com.example.advancetest.ui.main.MainViewModel
import com.example.advancetest.ui.main.view_movie.adapter.LoadItemAdapter
import com.example.advancetest.ui.main.view_movie.adapter.PagingMovieAdapter
import com.example.advancetest.ui.main.view_movie.adapter.withLoadStateAdapters
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val adapter by lazy { PagingMovieAdapter(::getItem) }

    private val vm by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private val navController by lazy {
        getNavController(R.id.nav_host_fragment_content_main)
    }

    private val trailerDialog: TrailerListDialog by lazy {
        TrailerListDialog().also {
            it.setAction(::getTrailerKey)
        }
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setObserver()
    }

    private fun setupView() = with(binding){
        rvPoster.layoutManager = GridLayoutManager(requireContext(),3, LinearLayoutManager.VERTICAL,false)
        rvPoster.adapter = adapter.withLoadStateAdapters(
            LoadItemAdapter(retry = adapter::retry),
            LoadItemAdapter(retry = adapter::retry)
        )
    }

    private fun getItem(data: MovieItemDomain){
        vm.setMovie(data)
        vm.getMovieTrailer(data.id)
    }

    private fun setObserver(){
        vm.getMovies()
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                vm.movies.collect(::loadDataForRV)
            }
            launch {
                vm.state.collect(::stateHandler)
            }
        }
    }

    private suspend fun loadDataForRV(data: PagingData<MovieItemDomain>){
        binding.apply {
            adapter.submitData(data)
        }
    }

    private fun openTrailerData(data: List<MovieTrailersDomain>?){
        if (data == null)
            return
        if (data.isNotEmpty()){
            trailerDialog
                .setTrailers(data)
            showDialog(trailerDialog)
        }else{
            shortToast(getString(R.string.no_trailer))
        }
    }

    private fun getTrailerKey(data: MovieTrailersDomain){
        vm.selectedMovieTrailer(data)
        navController.navigate(R.id.go_to_detail)
    }

    private fun stateHandler(state: MovieState){
        binding.prBar.visibility = View.GONE
        when (state){
            is MovieState.Loading -> {
                binding.prBar.visibility = View.VISIBLE
            }
            is MovieState.SuccessMovie -> {
                openTrailerData(state.data)
            }
            is MovieState.Error -> {
                errorStateHandler(state.error)
            }
            else -> { }
        }
    }

    private fun errorStateHandler(error: ErrorDomain) =
        when (error){
            is ErrorEntity -> shortToast(error.message)
            else -> shortToast(getString(R.string.error_connection))
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}