package br.com.jrmantovani.rideexpress.di

import br.com.jrmantovani.rideexpress.data.remote.api.DirectionsApi
import br.com.jrmantovani.rideexpress.data.remote.api.RideAPI
import br.com.jrmantovani.rideexpress.data.repository.DirectionsRepositoryImpl
import br.com.jrmantovani.rideexpress.data.repository.RideRepositoryImpl
import br.com.jrmantovani.rideexpress.domain.repository.IDirectionsRepository
import br.com.jrmantovani.rideexpress.domain.repository.IRideRepository

import br.com.jrmantovani.rideexpress.helper.Constants.BASE_URL
import br.com.jrmantovani.rideexpress.helper.Constants.BASE_URL_MAPS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

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
fun provideRideRepository(rideApi: RideAPI): IRideRepository {
    return RideRepositoryImpl(rideApi)
}

    @Provides
    @Singleton
    fun provideDirectionsApi(): DirectionsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MAPS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApi::class.java)
    }

    @Provides
    fun provideDirectionsRepository(directionsApi: DirectionsApi): IDirectionsRepository {
        return DirectionsRepositoryImpl(directionsApi)
    }




}