package com.treintaYTres.vdc.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.mapper.toBandInfo
import com.treintaYTres.vdc.ui.model.band.BandInfo
import com.treintaYTres.vdc.ui.model.event.Member
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.usecase.auth.DeleteMemberUseCase
import com.treintaYTres.vdc.usecase.band.GetBandInfoUseCase
import com.treintaYTres.vdc.usecase.user.UpdateInstrumentsUseCase
import com.treintaYTres.vdc.usecase.user.UpdatePermissionsUseCase
import com.treintaYTres.vdc.util.getUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BandViewModel @Inject constructor(
    private val getBandInfoUseCase: GetBandInfoUseCase,
    private val updateInstrumentsUseCase: UpdateInstrumentsUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val updatePermissionsUseCase: UpdatePermissionsUseCase,
    private val prefs: SharedPreferences
) : ViewModel() {
    private val _info =
        MutableStateFlow<Result<BandInfo>>(Result.Loading())
    val info: StateFlow<Result<BandInfo>> get() = _info

    init {
        getInfo()
    }

    private fun getInfo() {
        viewModelScope.launch {
            val response = getBandInfoUseCase.execute()
            _info.emit(
                when (response) {
                    null -> Result.Error("Error al obtener los datos")
                    else -> Result.Success(response.toBandInfo())
                }
            )
        }
    }

    fun updateInstruments(
        id: Int,
        instruments: List<Instrument>
    ) {
        viewModelScope.launch {
            updateInstrumentsUseCase.execute(id,instruments.map { it.id })
        }
    }

    fun getUserId() = prefs.getUserId()

    suspend fun deleteMember(id: Int): Boolean {
        return withContext(viewModelScope.coroutineContext) {
            return@withContext deleteMemberUseCase.execute(id)
        }
    }

    fun removeAtIndex(index: Int) {
        (_info.value as Result.Success).data.members.removeAt(index)
    }

    fun updatePermissions(member: Member) {
        viewModelScope.launch {
            if(updatePermissionsUseCase.execute(member.id) != null) {
                member.isAdmin.value = false
            }
        }
    }
}