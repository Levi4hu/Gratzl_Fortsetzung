package com.example.gratzl.shared.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gratzl.data.model.UrgencyTag
import com.example.gratzl.shared.theme.Orange20
import com.example.gratzl.shared.theme.Orange80

@Composable
fun UrgencyChip(urgency: UrgencyTag) {
    val label = when (urgency) {
        UrgencyTag.FLEXIBLE -> "Flexible"
        UrgencyTag.TODAY    -> "Today"
        UrgencyTag.URGENT   -> "Urgent" //Dringend FireEmotio
    }
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Orange20
    ) {
        Text(
            text     = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
            style    = MaterialTheme.typography.labelSmall,
            color    = Orange80
        )
    }
}