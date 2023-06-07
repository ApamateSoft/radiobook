package ucv.radioimagenologia.radiobook.ui.screens.projectionList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ucv.radioimagenologia.domain.Projection
import ucv.radioimagenologia.usecases.ProjectionListCase
import javax.inject.Inject

@HiltViewModel
class ProjectionListModel @Inject constructor(
    private val projectionListCase: ProjectionListCase
) : ViewModel() {

    data class State(
        val loading: Boolean = false,
        val projections: List<Projection> = emptyList()
    )

    var state by mutableStateOf(State())
        private set

    fun loadProjections() {
        if (state.loading) return
        state = state.copy(loading = true)
        viewModelScope.launch {
            try {
                withContext(IO) {
                    projectionListCase.loadProjections()
                }.also {
                    state = state.copy(projections = it)
                }
            } catch (e: Exception) {
                TODO("Not yet implemented")
            } finally {
                state = state.copy(loading = false)
            }
        }
    }

}