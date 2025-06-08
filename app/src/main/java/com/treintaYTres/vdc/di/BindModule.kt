package com.treintaYTres.vdc.di

import com.treintaYTres.vdc.repository.AuthRepository
import com.treintaYTres.vdc.repository.BandRepository
import com.treintaYTres.vdc.repository.EventRepository
import com.treintaYTres.vdc.repository.InstrumentRepository
import com.treintaYTres.vdc.repository.CreateDataRepository
import com.treintaYTres.vdc.repository.ProfileRepository
import com.treintaYTres.vdc.repository.RollCallRepository
import com.treintaYTres.vdc.repository.UserRepository
import com.treintaYTres.vdc.usecase.auth.AuthRepositoryImpl
import com.treintaYTres.vdc.usecase.band.BandRepositoryImpl
import com.treintaYTres.vdc.usecase.event.EventRepositoryImpl
import com.treintaYTres.vdc.usecase.groups.CreateDataRepositoryImpl
import com.treintaYTres.vdc.usecase.instrument.InstrumentRepositoryImpl
import com.treintaYTres.vdc.usecase.profile.ProfileRepositoryImpl
import com.treintaYTres.vdc.usecase.rollcall.RollCallRepositoryImpl
import com.treintaYTres.vdc.usecase.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindBandRepository(bandRepository: BandRepositoryImpl): BandRepository

    @Binds
    abstract fun bindEventRepository(eventRepository: EventRepositoryImpl): EventRepository

    @Binds
    abstract fun bindProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindRollCallRepository(rollCallRepository: RollCallRepositoryImpl): RollCallRepository

    @Binds
    abstract fun bindInstrumentRepository(instrumentRepository: InstrumentRepositoryImpl): InstrumentRepository

    @Binds
    abstract fun bindInstrumentStringRepository(instrumentStringRepository : CreateDataRepositoryImpl): CreateDataRepository
}