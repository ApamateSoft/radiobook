package ucv.radioimagenologia.radiobook.ui.screens.projection

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import ucv.radioimagenologia.radiobook.R
import ucv.radioimagenologia.radiobook.ui.theme.RadiobookTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectionScreen(
    navController: NavController,
    model: ProjectionModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = model.state.projection?.name ?: "",
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
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
            Column(Modifier.fillMaxSize()) {

                Row {
                    AsyncImage(
                        model = model.state.projection!!.images[0],
                        contentDescription = null,
                        modifier = Modifier.weight(1f)
                    )
                    AsyncImage(
                        model = model.state.projection!!.images[1],
                        contentDescription = null,
                        modifier = Modifier.weight(1f)
                    )
                }

                Section(
                    title = "Indicaciones",
                    body = model.state.projection!!.indications
                )

                Divider()

                Section(
                    title = "Posicionamiento",
                    list = model.state.projection!!.positioning
                )

                Divider()

                Section(
                    title = "Factores tecnicos",
                    body = model.state.projection!!.technicalFactors
                )

                Divider()

                Section(
                    title = "Criterios de calidad",
                    list = model.state.projection!!.qualityCriteria
                )

            }
        }
    }
}

@Composable
fun Section(
    title: String,
    body: String? = null,
    list: List<String>? = null
) {
    var show by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(onClick = { show = !show })  {
                Icon(
                    painter = painterResource(id = if (show) R.drawable.arrow_drop_up_ic else R.drawable.arrow_drop_down_ic ),
                    contentDescription = null
                )
            }
        }
        Surface(Modifier.animateContentSize()) {
            if (show) {
                when {
                    body != null -> {
                        Text(
                            body,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    list != null -> {
                        Column(
                            Modifier.padding(bottom = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            list.map {
                                Row(verticalAlignment = CenterVertically) {
                                    Icon(
                                        painterResource(id = R.drawable.dot_ic),
                                        contentDescription = null
                                    )
                                    Text(
                                        it,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewProjectionScreen() {
    RadiobookTheme {
        Surface {
//            ProjectionScreen(
//                navController = rememberNavController()
//            )
            Section(
                title = "Lorem Ipsum",
                list = listOf(
                    "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum  Lorem Ipsum.",
                    "Ipsum Lorem",
                    "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum  Lorem Ipsum.",
                    "Ipsum Lorem"
                )
            )
        }
    }
}

