package ucv.radioimagenologia.data

import ucv.radioimagenologia.domain.Projection

interface ProjectionLocalSource {

    suspend fun loadProjections(): List<Projection>

}