package com.example.advancetest.ui.main.view_movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.advancetest.common.extension.initYoutube
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.databinding.FragmentMovieDetailBinding
import com.example.advancetest.ui.main.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment(){

    private var _binding: FragmentMovieDetailBinding? = null

    private val fragmentVm: MovieDetailViewModel by viewModels()

    private var currentTime = 0F

    private val vm by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                fragmentVm.time.collect(::getTime)
            }
            launch {
                vm.selectedMovieTrailer.collect(::openTrailerData)
            }
            launch {
                vm.selectedMovie.collect(::setUiData)
            }
        }
    }

    private fun setUiData(data: MovieItemDomain?){
        binding.apply {
            data?.let {
                tvTitle.text = data.title
                tvStar.text = data.voteAverage.toString()
            }
        }
    }

    private fun openTrailerData(data: MovieTrailersDomain?){
        data?.let {
            initYoutube(binding.youtubePlayerView, data.key, currentTime, ::setTime)
            //vm.clearMovieState()
            binding.tvName.text = data.name
        }
    }

    private fun setTime(data: Float){
        fragmentVm.setTime(data)
    }

    private fun getTime(data: Float ){
        currentTime = data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}