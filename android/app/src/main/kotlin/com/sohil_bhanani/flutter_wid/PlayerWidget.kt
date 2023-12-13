package com.sohil_bhanani.flutter_wid


import WidgetState
import WidgetStateHelper
import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import io.flutter.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel


class PlayerWidget() : GlanceAppWidget() {

    companion object {
        const val UNIQUE_WORK_TAG = "PlayerWidgetWork"
    }


    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {

            val state = WidgetStateHelper.getState(currentState())



            Box(
                modifier = GlanceModifier.background(
                    ColorProvider(Color(0xffb67a8f))

                ).height(60.dp)

            ) {

                when (state.data.title) {
                    // "" -> InitialView()
                    // else -> WidgetBody(state = state)
                    "" -> WidgetBody(title = "Default",artist = "Artist")
                    else -> WidgetBody(title = state.data.title,artist = state.data.artist)
                }
            }

        }
    }


}

@Composable
fun InitialView() {
    Column(
        modifier = GlanceModifier.padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(color = ColorProvider(Color.White))
        Spacer(GlanceModifier.size(4.dp))
        Text(text = "Loading data ...")
    }
}

@Composable
// fun WidgetBody(state: WidgetState) {
fun WidgetBody(title: String, artist: String) {
    androidx.glance.layout.Row {

        androidx.glance.Image(
            provider = ImageProvider(R.drawable.lejare),
            contentDescription = "ada",
            modifier = GlanceModifier.fillMaxHeight().width(60.dp)
        )

        Column {
            androidx.glance.layout.Row {
                Spacer(modifier = GlanceModifier.width(20.dp))
                Text(
                    // text = "${state.data.title} - ${state.data.artist}",
                    text = "$title - $artist",
                    GlanceModifier.padding(vertical = 4.dp),
                    style = TextStyle(
                        color = ColorProvider(Color.White),
                        fontSize = 14.sp,
                    )
                )
            }

            androidx.glance.layout.Row(
                modifier = GlanceModifier.fillMaxWidth().padding(horizontal = 4.dp)
            ) {
                val modifier = GlanceModifier.defaultWeight()
                androidx.glance.Image(
                    provider = ImageProvider(R.drawable.baseline_thumb_down_off_alt_24),
                    modifier = modifier,
                    contentDescription = ""
                )

                androidx.glance.Image(
                    provider = ImageProvider(R.drawable.baseline_skip_previous_24),
                    modifier = modifier.clickable { doNext() },

                    contentDescription = ""
                )
                androidx.glance.Image(
                    provider = ImageProvider(R.drawable.baseline_play_arrow_24),
                    modifier = modifier,
                    contentDescription = ""
                )
                androidx.glance.Image(
                    provider = ImageProvider(R.drawable.baseline_skip_next_24),
                    modifier = modifier.clickable { doPrev() },
                    contentDescription = ""
                )
                androidx.glance.Image(
                    provider = ImageProvider(R.drawable.baseline_thumb_up_off_alt_24),
                    modifier = modifier,
                    contentDescription = ""
                )
            }
        }


    }
}

fun doNext() {
    Log.d("TAG", "CEHFEHHEHHEHEHH")
//    NativeMethodChannel.showNewIdea("Hell Lot of work")
    NativeMethodChannel.triggerNext()

//     MethodChannel( engine.dartExecutor.binaryMessenger,
//    "data_channel").invokeMethod("CHECK","adfadf")

}

fun doPrev() =
    NativeMethodChannel.triggerPrev()


//fun Context.startPlayerWorker(title: String, artist:String) {
//    val networkConstraint =
//        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
//    val request = PeriodicWorkRequest
//        .Builder(PlayerWidgetWorker::class.java, 15, TimeUnit.MINUTES)
//        .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5000L, TimeUnit.MILLISECONDS)
//        .setInputData(
//            PlayerWidgetWorker.buildData(title, artist)
//        )
//        .setConstraints(networkConstraint)
//        .build()
//    val uniqueTag = PlayerWidget.UNIQUE_WORK_TAG + "_$title" + "_$artist"
//    WorkManager.getInstance(this)
//        .enqueueUniquePeriodicWork(
//            uniqueTag,
//            ExistingPeriodicWorkPolicy.REPLACE,
//            request
//        )
//}


class PlayerWidgetReciever : GlanceAppWidgetReceiver() {


    companion object {
        const val UNIQUE_WORK_TAG = "WeatherWidgetWork"
    }
//    private lateinit var flutterEngine : FlutterEngine

//    override fun onUpdate(
//        context: Context,
//        appWidgetManager: AppWidgetManager,
//        appWidgetIds: IntArray
//    ) {
//        super.onUpdate(context, appWidgetManager, appWidgetIds)
//        flutterEngine = FlutterEngine(context)
//        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
//
//
//        Log.d("TAG","Flutter engine now initialised $flutterEngine")
//    }
//    lateinit var messenger: BinaryMessenger
//    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
//        messenger = binding.binaryMessenger
//        MethodChannel( messenger,
//            "data_channel").invokeMethod("CHECK","adfadf")
//    }
//
//    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
//        TODO("Not yet implemented")
//    }

    override val glanceAppWidget: GlanceAppWidget = PlayerWidget()
}