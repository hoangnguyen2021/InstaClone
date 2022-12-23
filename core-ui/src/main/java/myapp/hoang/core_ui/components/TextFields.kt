package myapp.hoang.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = White,
            selectionColors = TextSelectionColors(Turquoise, Verdigris),
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sevenExtraLarge)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = if (isFocused) AliceBlue else GrayBlue,
                shape = RoundedCornerShape(LocalDimension.current.extraSmall)
            )
            .padding(vertical = LocalDimension.current.extraSmall)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                color = if (isFocused) AliceBlue else MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = if (value.isNotEmpty()) {
            {
                CancelIcon(
                    color = AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.medium)
                        .clickable { onValueChange("") }
                )
            }
        } else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }
    var isPasswordHidden by remember { mutableStateOf(true) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = White,
            selectionColors = TextSelectionColors(Turquoise, Verdigris),
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sevenExtraLarge)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = if (isFocused) AliceBlue else GrayBlue,
                shape = RoundedCornerShape(LocalDimension.current.extraSmall)
            )
            .padding(vertical = LocalDimension.current.extraSmall)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                color = if (isFocused) AliceBlue else MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (!isFocused) null
            else if (isPasswordHidden) {
                PasswordHiddenIcon(
                    color = AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.mediumLarge)
                        .clickable { isPasswordHidden = false }
                )
            } else {
                PasswordShownIcon(
                    color = AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.mediumLarge)
                        .clickable { isPasswordHidden = true }
                )
            }
        },
        visualTransformation = if (isPasswordHidden)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        singleLine = true,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = White,
            selectionColors = TextSelectionColors(Turquoise, Verdigris),
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sevenExtraLarge)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = if (isFocused) AliceBlue else GrayBlue,
                shape = RoundedCornerShape(LocalDimension.current.extraSmall)
            )
            .padding(vertical = LocalDimension.current.extraSmall)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                color = if (isFocused) AliceBlue else MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = if (value.isNotEmpty()) {
            {
                CancelIcon(
                    color = AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.medium)
                        .clickable { onValueChange("") }
                )
            }
        } else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        ),
        singleLine = true,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = White,
            selectionColors = TextSelectionColors(Turquoise, Verdigris),
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sevenExtraLarge)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = if (isFocused) AliceBlue else GrayBlue,
                shape = RoundedCornerShape(LocalDimension.current.extraSmall)
            )
            .padding(vertical = LocalDimension.current.extraSmall)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                color = if (isFocused) AliceBlue else MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = if (value.isNotEmpty()) {
            {
                CancelIcon(
                    color = AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.medium)
                        .clickable { onValueChange("") }
                )
            }
        } else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall)
    )
}