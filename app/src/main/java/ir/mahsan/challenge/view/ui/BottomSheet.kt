package ir.mahsan.challenge.view.ui

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.view.ui.theme.MahsanTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(item: Article, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MahsanTheme.colors.secondBackground,
        tonalElevation = BottomSheetDefaults.SheetPeekHeight,
    ) {
        NewsDetail(item = item)
    }
}