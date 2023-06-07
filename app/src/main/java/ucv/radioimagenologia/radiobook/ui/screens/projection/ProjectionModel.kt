package ucv.radioimagenologia.radiobook.ui.screens.projection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ucv.radioimagenologia.domain.Projection
import javax.inject.Inject

@HiltViewModel
class ProjectionModel @Inject constructor(
) : ViewModel() {

    data class State(
        val projection: Projection? = null
    )

    var state by mutableStateOf(State())
        private set

    fun setProjection(projection: Projection) {
        state = state.copy(projection = projection)
    }

}