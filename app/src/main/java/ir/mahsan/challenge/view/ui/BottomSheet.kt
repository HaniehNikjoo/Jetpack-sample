package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.mahsan.challenge.model.dto.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(item: Article, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color(0xFF303031),
        tonalElevation = BottomSheetDefaults.SheetPeekHeight,
        modifier = Modifier.background(Color(0x80202020))
    ) {
        NewsDetail(item = item)
    }
}