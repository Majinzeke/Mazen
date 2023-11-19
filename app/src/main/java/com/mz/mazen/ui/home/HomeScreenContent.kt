package com.mz.mazen.ui.home

import android.content.Intent
import android.content.LocusId
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenTopContent(
    urls: List<String>,
    articleImage: List<ArticleData>
){
    val context = LocalContext.current
    val startActivityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        items(articleImage.size){index ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(16.dp)
                    .clickable {
                        val uri = Uri.parse(urls[index])
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivityLauncher.launch(intent)

                    },
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val painter: Painter = painterResource(id = articleImage[index].imageResId)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        alignment = Alignment.BottomCenter
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

data class ArticleData(val name: String, val url: String,val imageResId: Int)


@Composable
fun HomeScreenMiddleContent(){

}
