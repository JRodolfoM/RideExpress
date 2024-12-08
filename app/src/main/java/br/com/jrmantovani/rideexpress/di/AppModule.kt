package br.com.jrmantovani.rideexpress.di

import br.com.jrmantovani.rideexpress.data.remote.api.RideAPI
import br.com.jrmantovani.rideexpress.data.repository.RideRepositoryImpl
import br.com.jrmantovani.rideexpress.domain.repository.RideRepository
import br.com.jrmantovani.rideexpress.helper.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRideApi(): RideAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RideAPI::class.java)
    }

@Provides
fun provideRideRepository(rideApi: RideAPI): RideRepository {
    return RideRepositoryImpl(rideApi)
}



}