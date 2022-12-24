package myapp.hoang.onboarding.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import myapp.hoang.core_ui.BackIcon
import myapp.hoang.core_ui.LinkBlue
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.White
import myapp.hoang.core_ui.components.AlreadyHaveAccountDialog
import myapp.hoang.core_ui.components.OnBoardingFilledButton
import myapp.hoang.core_ui.components.OnBoardingTextField

@Composable
fun FullNameScreen(
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var isDialogShown by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to Color(0xFF223234),
                    0.6f to Color(0xFF1C2E3D),
                )
            )
            .padding(
                horizontal = LocalDimension.current.mediumSmall
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.wrapContentHeight()
        ) {
            Spacer(Modifier.height(LocalDimension.current.large))
            BackIcon(
                color = White,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable(onClick = onBackClick)
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            Text(
                text = "What's your name?",
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            OnBoardingTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full name",
                keyboardType = KeyboardType.Text
            )
            Spacer(Modifier.height(LocalDimension.current.mediumLarge))
            OnBoardingFilledButton(
                text = "Next",
                onClick = { onNextClick(fullName) }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.wrapContentHeight()
        ) {
            Text(
                text = "Already have an account?",
                color = LinkBlue,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable { isDialogShown = true }
            )
            Spacer(Modifier.height(LocalDimension.current.large))
        }
        if (isDialogShown) {
            AlreadyHaveAccountDialog(
                onConfirm = {
                    isDialogShown = false
                    onBackClick()
                    onBackClick()
                },
                onDismiss = { isDialogShown = false }
            )
        }
    }
}