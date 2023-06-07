package ucv.radioimagenologia.usecases

import ucv.radioimagenologia.data.ProjectionLocalSource
import ucv.radioimagenologia.domain.Projection

class ProjectionListCase(
    private val projectionLocalSource: ProjectionLocalSource
) {

    suspend fun loadProjections(): List<Projection> {
        return projectionLocalSource.loadProjections()
    }

}