package org.skyfaced.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import org.skyfaced.data.model.CharacterShort
import org.skyfaced.data.model.Location
import org.skyfaced.locations.util.LOCATION_ITEM_COUNT
import org.skyfaced.locations.vm.LocationUiState
import org.skyfaced.locations.vm.LocationsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LocationsRoute(
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel = hiltViewModel()
) {
    val locationUiState by viewModel.locationUiState.collectAsStateWithLifecycle()

    LocationsScreen(
        locationUiState = locationUiState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationsScreen(
    locationUiState: LocationUiState,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetContent = {
            // TODO Filters content
            Box(modifier = Modifier.size(1.dp))
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                locations(
                    state = locationUiState
                )
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
private fun LazyListScope.locations(
    state: LocationUiState,
) {
    when (state) {
        is LocationUiState.Success -> {
            item {
                Text(
                    text = pluralStringResource(
                        R.plurals.lbl_found_locations,
                        state.info.count,
                        state.info.count
                    )
                )
            }

            items(state.locations) { location ->
                LocationItem(
                    location = location,
                    onCharacterClick = {}
                )
            }
        }
        is LocationUiState.Failure -> {
            // TODO Failure block
        }
        is LocationUiState.Loading -> {
            item {
                ElevatedCard {
                    Text(modifier = Modifier.shimmer(), text = "Found 126 locations")
                }
            }

            items(LOCATION_ITEM_COUNT) {
                LocationItemPlaceholder()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier,
    onCharacterClick: (CharacterShort) -> Unit,
) = ElevatedCard(modifier = modifier.fillMaxWidth()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(ContentPadding),
            text = location.name,
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            modifier = Modifier.padding(ContentPadding),
            text = String.format(
                "%s" + if (location.dimension.isNotEmpty()) " • %s" else "",
                location.type,
                location.dimension,
            )
        )

        if (location.residents.isNotEmpty()) Column {
            Text(
                modifier = Modifier.padding(ContentPadding),
                text = "${
                    pluralStringResource(
                        R.plurals.lbl_found_residents,
                        location.residents.size,
                        location.residents.size
                    )
                }:"
            )

            LazyRow(
                contentPadding = ContentPadding,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(location.residents) { character ->
                    AsyncImage(
                        modifier = modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .clickable { onCharacterClick(character) },
                        model = character.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationItemPlaceholder(
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(ContentPadding)
                    .shimmer(),
                text = "Earth earth earth",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                modifier = Modifier
                    .padding(ContentPadding)
                    .shimmer(),
                text = "Planet Dimension C-137"
            )

            Column {
                Text(
                    modifier = Modifier
                        .padding(ContentPadding)
                        .shimmer(),
                    text = "Found 100 residents:"
                )

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    contentPadding = ContentPadding,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(20) {
                        Box(
                            modifier = modifier
                                .size(72.dp)
                                .shimmer(CircleShape),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Modifier.shimmer(
    shape: Shape = MaterialTheme.shapes.medium
): Modifier = placeholder(
    visible = true,
    highlight = PlaceholderHighlight.shimmer(
        highlightColor = PlaceholderHighlightColor,
        progressForMaxAlpha = 0.4f
    ),
    color = PlaceholderBackgroundColor,
    shape = shape
)

private val PlaceholderBackgroundColor
    @Composable
    get() = MaterialTheme.colorScheme.surface.copy(alpha = 0.77f)
private val PlaceholderHighlightColor
    @Composable
    get() = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.77f)

private val ContentPadding = PaddingValues(horizontal = 8.dp)