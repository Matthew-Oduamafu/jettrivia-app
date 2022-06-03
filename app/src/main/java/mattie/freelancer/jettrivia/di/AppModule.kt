package mattie.freelancer.jettrivia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mattie.freelancer.jettrivia.network.QuestionApi
import mattie.freelancer.jettrivia.repository.QuestionRepository
import mattie.freelancer.jettrivia.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionApi) = QuestionRepository(api)


    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApi = with(Retrofit.Builder()) {
        baseUrl(Constants.BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        build()
            .create(QuestionApi::class.java)
    }

//    fun provideQuestionApi(): QuestionApi = Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(QuestionApi::class.java)
}