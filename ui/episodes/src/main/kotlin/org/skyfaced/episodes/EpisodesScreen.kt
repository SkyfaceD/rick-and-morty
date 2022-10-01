package org.skyfaced.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import org.skyfaced.data.model.CharacterShort
import org.skyfaced.data.model.Episode
import org.skyfaced.episodes.vm.EpisodeUiState
import org.skyfaced.episodes.vm.EpisodesViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EpisodesRoute(
    modifier: Modifier = Modifier,
    viewModel: EpisodesViewModel = hiltViewModel()
) {
    val episodeUiState by viewModel.episodeUiState.collectAsStateWithLifecycle()

    EpisodesScreen(
        episodeUiState = episodeUiState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EpisodesScreen(
    episodeUiState: EpisodeUiState,
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
                episodes(
                    state = episodeUiState
                )
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
private fun LazyListScope.episodes(
    state: EpisodeUiState
) {
    when (state) {
        is EpisodeUiState.Success -> {
            item {
                Text(
                    text = pluralStringResource(
                        R.plurals.lbl_found_episodes,
                        state.info.count,
                        state.info.count
                    )
                )
            }

            items(state.episodes) { episode ->
                EpisodeItem(
                    episode = episode,
                    onEpisodeClick = {},
                    onCharacterClick = {}
                )
            }
        }
        is EpisodeUiState.Failure -> {
            // TODO Failure block
        }
        is EpisodeUiState.Loading -> {
            // TODO Loading block
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun EpisodeItem(
    episode: Episode,
    modifier: Modifier = Modifier,
    onEpisodeClick: (Episode) -> Unit,
    onCharacterClick: (CharacterShort) -> Unit,
) = ElevatedCard(
    modifier = modifier.fillMaxWidth(),
    onClick = { onEpisodeClick(episode) }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Row(
            modifier = Modifier.padding(ContentPadding),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = episode.episode,
                style = MaterialTheme.typography.labelMedium,
                color = LocalContentColor.current.copy(alpha = 0.77f)
            )

            Text(text = episode.airDate)
        }

        Text(
            modifier = Modifier.padding(ContentPadding),
            text = episode.name,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(
                modifier = Modifier.padding(ContentPadding),
                text = "${
                    pluralStringResource(
                        R.plurals.lbl_featured_characters,
                        episode.characters.size,
                        episode.characters.size
                    )
                }:"
            )

            Spacer(modifier = Modifier.height(4.dp))

            LazyRow(
                contentPadding = ContentPadding,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(episode.characters) { character ->
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

private val ContentPadding = PaddingValues(horizontal = 8.dp)