package ru.geekbrains.mtmdb.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.mtmdb.framework.ui.details_fragment.DetailsViewModel
import ru.geekbrains.mtmdb.framework.ui.history_fragment.HistoryViewModel
import ru.geekbrains.mtmdb.framework.ui.main_fragment.MainViewModel
import ru.geekbrains.mtmdb.framework.ui.person_fragment.PersonViewModel
import ru.geekbrains.mtmdb.framework.ui.settings_fragment.SettingsViewModel
import ru.geekbrains.mtmdb.model.repository.Repository
import ru.geekbrains.mtmdb.model.repository.RepositoryImpl

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { HistoryViewModel(get()) }
    viewModel { PersonViewModel(get()) }
}