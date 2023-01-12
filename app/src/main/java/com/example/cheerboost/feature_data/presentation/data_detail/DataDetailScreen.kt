package com.example.cheerboost.feature_data.presentation.data_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.cheerboost.core.util.Routes
import com.example.cheerboost.core.util.UiEvent
import com.example.cheerboost.core.util.spacing
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DataDetailScreen(
    navController: NavHostController,
    viewModel: DataDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navController.navigate(Routes.DataListScreen.route)
                }
            }
        }
    }
    val pickerState = rememberPickerState(
        initialNumberOfOptions = 301,
        initiallySelectedOption = state.count
    )
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.workoutType,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Picker(
            state = pickerState,
            modifier = Modifier.size(50.dp, 50.dp),
        ) { num ->
            Text(
                text = num.toString(),
                fontWeight = if (num == pickerState.selectedOption) {
                    FontWeight.Bold
                } else FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(DataDetailEvent.DeleteSession)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete"
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(DataDetailEvent.SaveSession(pickerState.selectedOption))
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save"
                )
            }
        }
    }
}
