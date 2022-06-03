package mattie.freelancer.jettrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import mattie.freelancer.jettrivia.screens.QuestionsViewModel
import mattie.freelancer.jettrivia.screens.TriviaHome
import mattie.freelancer.jettrivia.ui.theme.JetTriviaTheme


private const val TAG = "MainActivity"
private const val SAVED_QUESTION_INDEX = "Question Index"


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var questionsViewModel: QuestionsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: starts")
        super.onCreate(savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val index = sharedPreferences.getInt(SAVED_QUESTION_INDEX, 0)
        setContent {
            JetTriviaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val questionsViewModel = hiltViewModel<QuestionsViewModel>()
                    this.questionsViewModel = questionsViewModel
//                    val viewModel:QuestionsViewModel by viewModels()

                    // retrieve saved score amd index if any
                    questionsViewModel.setQuestionIndex(index)
                    Log.d(TAG, "onCreate: saved index was $index")

                    TriviaHome(questionsViewModel)
                }
            }
        }
        Log.d(TAG, "onCreate: ends")
    }


    override fun onStop() {
        Log.d(TAG, "onStop: called")
        super.onStop()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        Log.d(TAG, "onStop: index to save ${questionsViewModel.getQuestionIndex()}")
        sharedPreferences.edit().putInt(SAVED_QUESTION_INDEX, questionsViewModel.getQuestionIndex())

        Log.d(TAG, "onStop: ends")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTriviaTheme {
    }
}