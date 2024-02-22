package ru.geekbrains.mtmdb.framework.ui.history_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.mtmdb.model.entities.History
import ru.geekbrains.mtmdb.model.repository.RemoteDataSource
import ru.geekbrains.mtmdb.model.repository.RepositoryImpl

class HistoryViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource()),
) : ViewModel(), LifecycleObserver {
    val historyLiveData: MutableLiveData<List<History>> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.postValue(repository.getAllHistory())
    }
}