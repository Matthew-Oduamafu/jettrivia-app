package mattie.freelancer.jettrivia.repository

import android.util.Log
import mattie.freelancer.jettrivia.data.DataOrExceptions
import mattie.freelancer.jettrivia.model.QuestionItem
import mattie.freelancer.jettrivia.network.QuestionApi
import javax.inject.Inject

private const val TAG = "QuestionRepository"

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
    private val dataOrExceptions = DataOrExceptions<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrExceptions<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrExceptions.loading = true
            dataOrExceptions.data = api.getAllQuestions()
            if (dataOrExceptions.data.toString().isNotEmpty()) dataOrExceptions.loading = false
        } catch (e: Exception) {
            dataOrExceptions.e = e
            Log.d(TAG, "getAllQuestions: failed to download json file")
            Log.d(TAG, "getAllQuestions: exception :: ${e.localizedMessage}")
        }
        return dataOrExceptions
    }
}