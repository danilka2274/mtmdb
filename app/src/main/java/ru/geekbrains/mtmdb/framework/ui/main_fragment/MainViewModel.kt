package ru.geekbrains.mtmdb.framework.ui.main_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.geekbrains.mtmdb.model.MovieState
import ru.geekbrains.mtmdb.model.entities.MoviesList
import ru.geekbrains.mtmdb.model.repository.REQUEST_ERROR
import ru.geekbrains.mtmdb.model.repository.RemoteDataSource
import ru.geekbrains.mtmdb.model.repository.RepositoryImpl
import ru.geekbrains.mtmdb.model.repository.SERVER_ERROR

class MainViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource()),
) : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {
    val liveData: MutableLiveData<MovieState> = MutableLiveData()

    fun getNewDataFromServer() {
        launch {
            liveData.value = MovieState.Loading
            repository.getNewMoviesListFromServer(callback)
        }
    }

    private val callback = object :
        Callback<MoviesList> {
        override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
            val serverResponse: MoviesList? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    MovieState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MoviesList>, t: Throwable) {
            liveData.postValue(MovieState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: MoviesList): MovieState {
            return MovieState.Success(serverResponse.results)
        }
    }
}