package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // 1. Tạo biến trạng thái để lưu kết quả đổ xúc xắc (mặc định là 1) [cite: 222]
    var result by remember { mutableStateOf(1) }

    // 2. Xác định tài nguyên hình ảnh dựa trên kết quả
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các thành phần theo chiều ngang [cite: 106]
    ) {
        // 3. Hiển thị hình ảnh xúc xắc [cite: 235]
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )

        // 4. Thêm khoảng cách giữa hình ảnh và nút bấm [cite: 183]
        Spacer(modifier = Modifier.height(16.dp))

        // 5. Nút bấm để đổ xúc xắc [cite: 208]
        Button(onClick = {
            // Khi nhấn nút, lấy một số ngẫu nhiên từ 1 đến 6
            result = (1..6).random()
        }) {
            Text(stringResource(R.string.roll))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize() // Lấp đầy toàn bộ màn hình [cite: 83]
            .wrapContentSize(Alignment.Center) // Căn chỉnh nội dung vào giữa không gian trống [cite: 92]
    )
}