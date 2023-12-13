package com.sohil_bhanani.flutter_wid


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

object PlayerWidgetBig : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Box(
                modifier = GlanceModifier.background(
                    ColorProvider(Color(0xff4f302f))

                ).height(210.dp).fillMaxWidth()

            ) {
                Column {

                    Box(modifier = GlanceModifier.height(12.dp)) {}
                    Row {
                        Box(modifier = GlanceModifier.width(12.dp)) {}

                        Image(
                            provider = ImageProvider(R.drawable.bb),
                            contentDescription = "ada",
                            modifier = GlanceModifier.height(60.dp).width(60.dp)

                        )

                        Column {
                            Row {
                                Spacer(modifier = GlanceModifier.width(20.dp))
                                Column {
                                    Text(
                                        text = "Khuda Jaane",
                                        style = TextStyle(
                                            color = ColorProvider(Color.White),
                                            fontSize = 14.sp,
                                        )
                                    )
                                    Text(
                                        text = "Vishal Shekhar",
                                        style = TextStyle(
                                            color = ColorProvider(Color.White),
                                            fontSize = 14.sp,
                                        )
                                    )

                                }
                            }
                            Spacer(modifier = GlanceModifier.height(8.dp))
                            Row(
//                                modifier = GlanceModifier.fillMaxWidth().padding(horizontal = 4.dp)
                            ) {
//                                val modifier = GlanceModifier.defaultWeight()

                                Spacer(modifier = GlanceModifier.width(20.dp))
                                Image(
                                    provider = ImageProvider(R.drawable.baseline_skip_previous_24),
//                                    modifier = modifier,
                                    contentDescription = ""
                                )
                                Spacer(modifier = GlanceModifier.width(25.dp))
                                Image(
                                    provider = ImageProvider(R.drawable.baseline_play_circle_24),
//                                    modifier = modifier,
                                    contentDescription = ""
                                )
                                Spacer(modifier = GlanceModifier.width(25.dp))
                                Image(
                                    provider = ImageProvider(R.drawable.baseline_skip_next_24),
//                                    modifier = modifier,
                                    contentDescription = ""
                                )

                            }

                        }


                    }
                    Spacer(modifier = GlanceModifier.height(35.dp))
                    Box(
                        modifier = GlanceModifier.fillMaxSize()
                            .background(color = Color(0xff412525)).padding(vertical = 12.dp)
                    ) {
                        Row(
                            modifier = GlanceModifier.fillMaxSize()
                                .height(65.dp)
                                .padding(horizontal = 12.dp),
                            verticalAlignment = androidx.glance.layout.Alignment.CenterVertically
                        ) {
                            val modifier = GlanceModifier.defaultWeight()
                            Image(
                                provider = ImageProvider(R.drawable.a1),
                                contentDescription = "ada",
                                modifier = modifier

                            )

                            Image(
                                provider = ImageProvider(R.drawable.a2),
                                contentDescription = "ada",
                                modifier = modifier

                            )

                            Image(
                                provider = ImageProvider(R.drawable.a3),
                                contentDescription = "ada",
                                modifier = modifier

                            )

                            Image(
                                provider = ImageProvider(R.drawable.a4),
                                contentDescription = "ada",
                                modifier = modifier

                            )
                        }
                    }
                }
            }

        }
    }
}

class PlayerWidgetBigReciever : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = PlayerWidgetBig
}