package iyannah.ivy.roomdb.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import iyannah.ivy.roomdb.data.PersonRepositoryImpl
import iyannah.ivy.roomdb.data.local.dao.PersonDao
import iyannah.ivy.roomdb.data.local.database.PersonDatabase
import iyannah.ivy.roomdb.domain.PersonRepository
import iyannah.ivy.roomdb.domain.usecase.AddPersonUseCase
import iyannah.ivy.roomdb.domain.usecase.FetchPersonUseCase
import iyannah.ivy.roomdb.domain.usecase.RemovePersonUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDbSampleModule {

    @Provides
    @Singleton
    fun providePersonDatabase(@ApplicationContext applicationContext: Context): PersonDatabase {
        return Room.databaseBuilder(
            applicationContext,
            PersonDatabase::class.java,
            "person_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonDao(personDatabase: PersonDatabase): PersonDao {
        return personDatabase.getDao()
    }

    @Provides
    @Singleton
    fun providePersonRepository(personDao: PersonDao): PersonRepository {
        return PersonRepositoryImpl(personDao)
    }

    @Provides
    @Singleton
    fun provideAddPersonUseCase(personRepository: PersonRepository): AddPersonUseCase {
        return AddPersonUseCase(personRepository)
    }

    @Provides
    @Singleton
    fun provideFetchPersonUseCase(personRepository: PersonRepository): FetchPersonUseCase {
        return FetchPersonUseCase(personRepository)
    }

    @Provides
    @Singleton
    fun provideRemovePersonUseCase(personRepository: PersonRepository): RemovePersonUseCase {
        return RemovePersonUseCase(personRepository)
    }

}