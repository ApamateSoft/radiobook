package ucv.radioimagenologia.radiobook.infrastructure.dataSource.projection

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import ucv.radioimagenologia.data.ProjectionLocalSource
import ucv.radioimagenologia.domain.Projection
import javax.inject.Inject

class ProjectionLocalSourceImp  @Inject constructor(
    @ApplicationContext private val context: Context
): ProjectionLocalSource {

    override suspend fun loadProjections(): List<Projection> {
        return try {
            val json = context.assets.open("projections.json")
                .bufferedReader()
                .use { it.readText() }
            val typeToken = object : TypeToken<List<Projection>>() {}.type
            Gson().fromJson(json, typeToken)
        } catch (e: Exception) {
            throw e
        }
    }

}