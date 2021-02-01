package com.example.android.networkconnect.datafragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.networkconnect.datafragment.datainj.DaggerApiComponent
import com.example.android.networkconnect.datafragment.model.Character
import com.example.android.networkconnect.datafragment.model.DataService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {
    @Inject
    lateinit var dataService: DataService
    init {
        DaggerApiComponent.create().inject(this)
    }
//    private val disposable = CompositeDisposable()

    @Inject
    lateinit var disposable: CompositeDisposable

    val characters by lazy { MutableLiveData<List<Character>>() }
    val characterListLD: LiveData<List<Character>>
        get() = characters
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadingLD: LiveData<Boolean>
        get() = loading
    val characterLoadError by lazy { MutableLiveData<Boolean>() }
    val characterLoadErrorLD: LiveData<Boolean>
        get() = characterLoadError

    // val characters = MutableLiveData<List<Character>>()
    // val characterLoadError = MutableLiveData<Boolean>()
    // val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        loading.value = true
        disposable.add(
            dataService.getCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {it.results }
                .subscribeWith(object: DisposableSingleObserver<List<Character>>() {
                    override fun onSuccess(value: List<Character>?) {
                        characters.value = value
                        characterLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        characterLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}