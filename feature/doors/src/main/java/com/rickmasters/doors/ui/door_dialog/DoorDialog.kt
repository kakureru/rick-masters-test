package com.rickmasters.doors.ui.door_dialog

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.rickmasters.doors.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DoorDialog(
    onDismissRequest: () -> Unit,
    viewModel: DoorDialogViewModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.navState) {
        when (state.navState) {
            DoorDialogNavState.Dismiss -> onDismissRequest()
            DoorDialogNavState.Idle -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is DoorDialogEffect.Error -> {
                    Toast.makeText(context, effect.msg.asString(context), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        dragHandle = null,
        shape = MaterialTheme.shapes.large.copy(
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp)
        )
    ) {
        DoorDialogUi(
            state = state,
            onConfirmClick = {
                viewModel.onConfirmClick()
            },
            onNameChange = { text -> viewModel.onNameChanged(text) },
            modifier = modifier,
        )
    }
}

@Composable
private fun DoorDialogUi(
    state: DoorDialogState,
    onNameChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Row(
        modifier = modifier.padding(top = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            textStyle = MaterialTheme.typography.titleLarge,
            value = state.name,
            onValueChange = { value -> onNameChange(value) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done,
            ),
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.room_name),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
        IconButton(modifier = modifier, onClick = onConfirmClick) {
            Icon(
                imageVector = Icons.Rounded.Done,
                contentDescription = "done",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

}