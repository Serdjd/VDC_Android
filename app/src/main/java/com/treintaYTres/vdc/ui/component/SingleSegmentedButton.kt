package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun SingleSegmentedButton(
    options: List<Action.Text>,
    padding: Int = 0
) {
    var selected by rememberSaveable { mutableIntStateOf(0) }

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.padding(start = padding.dp, end = padding.dp, bottom = padding.dp),
    ) {
        options.forEachIndexed { index,it ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selected = index
                    it.action()
                },
                selected = selected == index,
                label = { Text(it.text) }
            )
        }
    }
}

@Preview
@Composable
fun SingleSegmentedButtonPrev() {
    VdcTheme {
        SingleSegmentedButton(
            listOf(
                Action.Text("Next") {},
                Action.Text("Previous") {}
            )
        )
    }
}
