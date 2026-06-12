package com.vladusecho.trovare.presentation.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Int.SpacerW() = Spacer(modifier = Modifier.width(this.dp))

@Composable
fun Int.SpacerH() = Spacer(modifier = Modifier.height(this.dp))