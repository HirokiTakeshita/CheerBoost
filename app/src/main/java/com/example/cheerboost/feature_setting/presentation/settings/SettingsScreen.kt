package com.example.cheerboost.feature_setting.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import com.example.cheerboost.core.util.VoiceAvatarType
import com.example.cheerboost.core.util.spacing
import com.example.cheerboost.feature_setting.presentation.settings.components.RadioChip

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        }
    ) {
        val state = viewModel.state.value
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = MaterialTheme.spacing.medium),
            state = listState
        ) {
            item {
                Text(
                    text = "Settings",
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            }
            item {
                Text(
                    text = "Voice Avatar",
                    fontWeight = FontWeight.SemiBold,
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }
            item {
                RadioChip(
                    checked = state.avatarType == VoiceAvatarType.YOUNG_MAN,
                    onCheckedChange = {
                        viewModel.onEvent(
                            SettingsEvent.SelectAvatar(VoiceAvatarType.YOUNG_MAN)
                        )
                    },
                    text = "お兄さん"
                )
            }
            item {
                RadioChip(
                    checked = state.avatarType == VoiceAvatarType.LADY,
                    onCheckedChange = {
                        viewModel.onEvent(
                            SettingsEvent.SelectAvatar(VoiceAvatarType.LADY)
                        )
                    },
                    text = "お姉さん"
                )
            }
            item {
                RadioChip(
                    checked = state.avatarType == VoiceAvatarType.GIRL,
                    onCheckedChange = {
                        viewModel.onEvent(
                            SettingsEvent.SelectAvatar(VoiceAvatarType.GIRL)
                        )
                    },
                    text = "少女"
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
            item {
                ToggleChip(
                    modifier = Modifier.fillMaxWidth(),
                    checked = state.isCountOnlyMode,
                    onCheckedChange = {
                        viewModel.onEvent(
                            SettingsEvent.ToggleCountOnlyMode
                        )
                    },
                    label = {
                        Text(text = "Count Only Mode")
                    },
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = state.isCountOnlyMode
                            ),
                            contentDescription =
                            if (state.isCountOnlyMode) "Checked" else "Not Checked"
                        )
                    }
                )
            }
        }
    }
}
