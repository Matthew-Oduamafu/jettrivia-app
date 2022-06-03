package mattie.freelancer.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mattie.freelancer.jettrivia.data.DataOrExceptions
import mattie.freelancer.jettrivia.model.QuestionItem
import mattie.freelancer.jettrivia.repository.QuestionRepository
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {
    val data: MutableState<DataOrExceptions<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrExceptions(null, true, Exception("")))

    private var questionIndex: Int = 0
//    private var score: Int = 0

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.isNullOrEmpty()) {
                data.value.loading = false
            }
        }
    }

    fun getQuestionIndex(): Int = questionIndex
    fun setQuestionIndex(value: Int) {
        questionIndex = value
    }

//    fun getScore(): Int = score
//    fun setScore(value: Int){ score = value}

}