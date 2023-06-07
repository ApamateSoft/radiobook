package ucv.radioimagenologia.radiobook.ui.router

import ProjectionListScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ucv.radioimagenologia.radiobook.ui.screens.home.HomeScreen
import ucv.radioimagenologia.radiobook.ui.screens.projection.ProjectionModel
import ucv.radioimagenologia.radiobook.ui.screens.projection.ProjectionScreen

@Composable
fun Router(
    navController: NavHostController,
) {

    val projectionModel: ProjectionModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "HOME"
    ) {
        composable("HOME") {
            HomeScreen(navController)
        }
        composable("PROJECTION_LIST") {
            ProjectionListScreen(
                navController = navController,
                projectionModel = projectionModel
            )
        }
        composable("PROJECTION") {
            ProjectionScreen(
                navController = navController,
                model = projectionModel
            )
        }
    }
}