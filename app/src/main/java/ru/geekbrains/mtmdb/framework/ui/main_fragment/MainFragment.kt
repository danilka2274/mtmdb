package ru.geekbrains.mtmdb.framework.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.mtmdb.R
import ru.geekbrains.mtmdb.databinding.FragmentMainBinding
import ru.geekbrains.mtmdb.framework.AppSettings
import ru.geekbrains.mtmdb.framework.ui.adapters.MainFragmentAdapter
import ru.geekbrains.mtmdb.framework.ui.details_fragment.DetailsFragment
import ru.geekbrains.mtmdb.model.MovieState
import ru.geekbrains.mtmdb.model.entities.Movie
import ru.geekbrains.mtmdb.model.repository.RemoteDataSource
import ru.geekbrains.mtmdb.model.repository.RepositoryImpl
import ru.geekbrains.mtmdb.utils.showSnackBar

class MainFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel {
        parametersOf(RepositoryImpl(RemoteDataSource()))
    }
    private var adapter: MainFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.adapter = adapter
        viewModel.liveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getNewDataFromServer()
    }

    private fun renderData(movieState: MovieState) = with(binding) {
        when (movieState) {
            is MovieState.Loading ->
                LoadingLayout.visibility = View.VISIBLE
            is MovieState.Success -> {
                LoadingLayout.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(movie: Movie) {
                        val fragmentManager = activity?.supportFragmentManager
                        fragmentManager?.let { manager ->
                            val bundle = Bundle().apply {
                                putInt(DetailsFragment.BUNDLE_EXTRA, movie.id)
                            }
                            manager.beginTransaction()
                                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setMovies(if (AppSettings.adultShow) {
                        movieState.moviesData
                    } else {
                        movieState.moviesData.filter { !it.adult }
                    })
                }
                RecyclerView.adapter = adapter
            }
            is MovieState.Error -> {
                LoadingLayout.visibility = View.GONE
                movieState.error.localizedMessage?.let {
                    view?.showSnackBar(
                        it,
                        getString(R.string.reload),
                        { viewModel.getNewDataFromServer() }
                    )
                }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}