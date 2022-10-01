package org.skyfaced.characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Badge
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import org.skyfaced.characters.util.MIN_HEADER_ITEM
import org.skyfaced.characters.vm.CharacterViewModel
import org.skyfaced.characters.vm.CharactersUiState
import org.skyfaced.characters.vm.HeaderUiState
import org.skyfaced.data.model.Character
import org.skyfaced.data.model.LocationShort
import org.skyfaced.network.model.filter.util.Status

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CharactersRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val headerUiState: HeaderUiState by viewModel.headerUiState.collectAsStateWithLifecycle()
    val characterUiState: CharactersUiState by viewModel.characterUiState.collectAsStateWithLifecycle()

    CharactersScreen(
        headerUiState = headerUiState,
        characterUiState = characterUiState,
        modifier = modifier,
        onHeaderRefreshClick = viewModel::headerRefresh,
        onShowAdditionalInfoClick = viewModel::showAdditionalInfoDialog,
        onCloseAdditionalInfoClick = viewModel::closeAdditionalInfoDialog,
        onLocationClick = { /* TODO Open location details */ },
        onEpisodeClick = { /* TODO Open episode details */ }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharactersScreen(
    headerUiState: HeaderUiState,
    characterUiState: CharactersUiState,
    modifier: Modifier = Modifier,
    onHeaderRefreshClick: () -> Unit,
    onShowAdditionalInfoClick: (Character) -> Unit,
    onCloseAdditionalInfoClick: () -> Unit,
    onLocationClick: (LocationShort) -> Unit,
    onEpisodeClick: (Int) -> Unit,
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
            if (characterUiState is CharactersUiState.Success) {
                if (characterUiState.isAdditionalInfoVisible && characterUiState.additionalInfo != null) {
                    CharacterAdditionalInfoDialog(
                        character = characterUiState.additionalInfo,
                        onDismiss = onCloseAdditionalInfoClick,
                        onLocationClick = onLocationClick,
                        onEpisodeClick = onEpisodeClick,
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                header(
                    state = headerUiState,
                    onRefreshClick = onHeaderRefreshClick
                )

                characters(
                    state = characterUiState,
                    onShowAdditionalInfoClick = onShowAdditionalInfoClick,
                )
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
private fun LazyListScope.characters(
    state: CharactersUiState,
    onShowAdditionalInfoClick: (Character) -> Unit,
) {
    when (state) {
        is CharactersUiState.Success -> {
            item {
                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
                    text = pluralStringResource(
                        R.plurals.lbl_found_characters,
                        state.info.count,
                        state.info.count
                    )
                )
            }

            itemsIndexed(state.characters) { idx, character ->
                Column {
                    CharacterItem(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        character = character,
                        onClick = {
                            // TODO Open details
                        },
                        onShowAdditionalInfoClick = onShowAdditionalInfoClick
                    )

                    if (idx != state.characters.size) Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        is CharactersUiState.Failure -> {
            // TODO Failure block
        }
        is CharactersUiState.Loading -> {
            item {
                ElevatedCard(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        modifier = Modifier.shimmer(MaterialTheme.shapes.small),
                        text = "Total: 826"
                    )
                }
            }

            items(20) { idx ->
                CharacterItemPlaceholderSimple(modifier = Modifier.padding(horizontal = 8.dp))

                if (idx != 20) Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: (Character) -> Unit,
    onShowAdditionalInfoClick: (Character) -> Unit,
) = ElevatedCard(
    modifier = modifier.fillMaxWidth(),
    onClick = { onClick(character) },
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp),
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(CharacterContentPadding),
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge,
                )

                IconButton(onClick = { onShowAdditionalInfoClick(character) }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO Harmonize colors
                Badge(
                    modifier = Modifier.offset(y = (2).dp),
                    backgroundColor = when (character.status) {
                        Status.Alive -> Color.Green
                        Status.Dead -> Color.Red
                        Status.Unknown -> Color.Gray
                    }
                )

                Text(
                    text = String.format(
                        "%s • %s • %s",
                        character.status.name,
                        character.species,
                        character.gender.name
                    )
                )
            }

            Text(
                text = pluralStringResource(
                    R.plurals.lbl_seen_in,
                    character.episodes.size,
                    character.episodes.size
                ),
                color = contentColorFor(MaterialTheme.colorScheme.surface).copy(
                    alpha = 0.6f
                )
            )
        }
    }
}

@Composable
private fun CharacterItemPlaceholderSimple(
    modifier: Modifier
) = ElevatedCard(
    modifier = modifier.fillMaxWidth(),
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp),
        )

        Column(
            modifier = Modifier.padding(CharacterContentPadding),
        ) {
            Text(
                text = "",
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Badge()

                Text(text = "")
            }

            Text(text = "")
        }
    }
}

@Composable
private fun CharacterItemPlaceholder(
    modifier: Modifier = Modifier
) = ElevatedCard(
    modifier = modifier,
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .shimmer(RectangleShape),
        )

        Column(
            modifier = Modifier.padding(CharacterContentPadding),
        ) {
            Text(
                modifier = Modifier.shimmer(MaterialTheme.shapes.small),
                text = "Rick Sanchez",
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Badge(
                    modifier = Modifier
                        .offset(y = (2).dp)
                        .shimmer(CircleShape),
                )

                Text(
                    modifier = Modifier.shimmer(MaterialTheme.shapes.small),
                    text = "Alive • Human • Male"
                )
            }

            Text(
                modifier = Modifier.shimmer(MaterialTheme.shapes.small),
                text = "Seen in 51 episodes",
                color = contentColorFor(MaterialTheme.colorScheme.surface).copy(alpha = 0.6f)
            )
        }
    }
}

private fun LazyListScope.header(
    state: HeaderUiState,
    onRefreshClick: () -> Unit,
) = item {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.lbl_cool_characters),
                maxLines = 1
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                enabled = state != HeaderUiState.Loading,
                onClick = onRefreshClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_refresh),
                    contentDescription = stringResource(R.string.lbl_refresh_header)
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (state) {
                is HeaderUiState.Success -> {
                    items(state.characters) { character ->
                        HeaderItem(
                            character = character,
                            onClick = {
                                // TODO Open character details
                            }
                        )
                    }
                }
                is HeaderUiState.Failure -> {
                    // TODO Failure block
                }
                is HeaderUiState.Loading -> {
                    items(MIN_HEADER_ITEM) {
                        HeaderItemPlaceHolder()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeaderItem(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: (Character) -> Unit,
) = ElevatedCard(
    modifier = modifier,
    onClick = { onClick(character) }
) {
    AsyncImage(
        modifier = Modifier
            .size(HeaderImageSize)
            .clip(HeaderImageShape),
        model = character.image,
        contentDescription = character.name,
        contentScale = ContentScale.Crop,
    )

    Text(
        modifier = Modifier
            .widthIn(max = HeaderImageSize.width)
            .padding(HeaderTextPadding),
        text = character.name,
        style = MaterialTheme.typography.labelSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun HeaderItemPlaceHolder(
    modifier: Modifier = Modifier,
) = ElevatedCard(
    modifier = modifier
) {
    Box(
        modifier = Modifier
            .size(HeaderImageSize)
            .shimmer()
    )

    Text(
        modifier = Modifier
            .widthIn(max = HeaderImageSize.width)
            .padding(HeaderTextPadding)
            .shimmer(MaterialTheme.shapes.small),
        text = "Rick Sanchez",
        style = MaterialTheme.typography.labelSmall
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun CharacterAdditionalInfoDialog(
    character: Character,
    onDismiss: () -> Unit,
    onLocationClick: (LocationShort) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) = Dialog(
    onDismissRequest = onDismiss
) {
    Surface(shape = MaterialTheme.shapes.extraLarge, tonalElevation = 6.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 18.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(AdditionalInfoTitlePadding),
                text = character.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Row(
                modifier = Modifier.padding(AdditionalInfoPadding),
                verticalAlignment = Alignment.Top
            ) {
                val origin = character.originLocation

                LabeledContent(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.lbl_origin_location),
                ) {
                    Text(text = origin?.name ?: stringResource(R.string.lbl_unknown), maxLines = 2)
                }

                if (origin != null) OpenButton { onLocationClick(origin) }
            }

            Row(
                modifier = Modifier.padding(AdditionalInfoPadding),
                verticalAlignment = Alignment.Top
            ) {
                val last = character.lastLocation

                LabeledContent(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.lbl_last_known_location),
                ) {
                    Text(text = last?.name ?: stringResource(R.string.lbl_unknown), maxLines = 2)
                }


                if (last != null) OpenButton { onLocationClick(last) }
            }

            LabeledContent(
                labelModifier = Modifier.padding(AdditionalInfoPadding),
                label = pluralStringResource(
                    R.plurals.lbl_seen_in,
                    character.episodes.size,
                    character.episodes.size
                ),
            ) {
                LazyRow(
                    contentPadding = AdditionalInfoPadding,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(character.episodes) { episode ->
                        AssistChip(
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_episode),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(stringResource(R.string.placeholder_episode, episode))
                            },
                            onClick = { onEpisodeClick(episode) }
                        )
                    }
                }
            }

            TextButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(AdditionalInfoPadding),
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.lbl_ok))
            }
        }
    }
}

@Composable
private fun LabeledContent(
    label: String,
    modifier: Modifier = Modifier,
    labelModifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) = Column(
    modifier = modifier
) {
    Text(modifier = labelModifier, text = "$label:", style = MaterialTheme.typography.labelMedium)

    content()
}

@Composable
private fun OpenButton(
    onClick: () -> Unit
) = Button(
    contentPadding = PaddingValues(start = 4.dp, end = 4.dp, bottom = 2.dp),
    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
    onClick = onClick
) {
    Text(stringResource(R.string.lbl_open))
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

private val HeaderImageSize = DpSize(width = 125.dp, height = 150.dp)
private val HeaderImageShape
    @Composable
    get() = MaterialTheme.shapes.medium
private val HeaderTextPadding = PaddingValues(all = 8.dp)

private val CharacterContentPadding =
    PaddingValues(top = 2.dp, bottom = 4.dp, start = 12.dp, end = 2.dp)

private val AdditionalInfoTitlePadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 16.dp)
private val AdditionalInfoPadding = PaddingValues(horizontal = 24.dp)
