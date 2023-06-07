package ucv.radioimagenologia.radiobook.infrastructure.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ucv.radioimagenologia.data.ProjectionLocalSource
import ucv.radioimagenologia.usecases.ProjectionListCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Singleton
    @Provides
    fun providerProjectionListCase(
        projectionLocalSource: ProjectionLocalSource
    ) = ProjectionListCase(
        projectionLocalSource = projectionLocalSource
    )

}