import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ucv.radioimagenologia.radiobook.ui.screens.projectionList.ProjectionListModel
import ucv.radioimagenologia.radiobook.ui.theme.RadiobookTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ucv.radioimagenologia.radiobook.ui.screens.projection.ProjectionModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectionListScreen(
    navController: NavController,
    projectionModel: ProjectionModel,
    model: ProjectionListModel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        model.loadProjections()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Proyecciones",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->

            if (model.state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(model.state.projections) { projection ->
                        ListItem(
                            headlineText = { Text(projection.name) },
                            modifier = Modifier
                                .selectable(
                                    selected = true,
                                    onClick = {
                                        projectionModel.setProjection(projection)
                                        navController.navigate("PROJECTION")
                                    }
                                )
                        )
                        Divider()
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewProjectionListScreen() {
    RadiobookTheme {
        Surface {
            ProjectionListScreen(
                navController = rememberNavController(),
                projectionModel = hiltViewModel()
            )
        }
    }
}

