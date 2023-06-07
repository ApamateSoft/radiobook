package ucv.radioimagenologia.radiobook.infrastructure.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ucv.radioimagenologia.data.ProjectionLocalSource
import ucv.radioimagenologia.radiobook.infrastructure.dataSource.projection.ProjectionLocalSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindProjectionLocalSource(imp: ProjectionLocalSourceImp): ProjectionLocalSource

}