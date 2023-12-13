package com.sohil_bhanani.flutter_wid

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import androidx.lifecycle.lifecycleScope
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import kotlinx.coroutines.launch

class MainActivity : FlutterActivity() {
    private val CHANNEL = "data_channel"
//    val imageLoader = context.imageLoader
//    val request = ImageRequest.Builder(context)
//        .data("https://example.com/image.jpg")
//        .build()

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
//    startPlayerWorker(title = d.title, artist = d.artist)

        super.configureFlutterEngine(flutterEngine)

        NativeMethodChannel.configureChannel(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "TriggerUpdate") {

                val context = this
                val titleFromFlutter = call.argument<String>("title")
                val artistFromFlutter = call.argument<String>("artist")
                lifecycleScope.launch {
                    val glanceId =
                        GlanceAppWidgetManager(context).getGlanceIds(
                            PlayerWidget::class.java
                        ).last()
//                    var drawable = imageLoader.execute(request).drawable

                    Log.d("TAG", "------------> $glanceId")

                    PlayerWidget().apply {
                        updateAppWidgetState(context, glanceId) {
                            WidgetStateHelper.saveTitleArtist(
                                it,
                                title = titleFromFlutter.orEmpty(),
                                artist = artistFromFlutter.orEmpty(),
                            )
                        }
                        update(context, glanceId)
                    }

//                   setResult(RESULT_OK, intent)
//                   finish()
                }

                result.success(getHelloData());
            }

            if(call.method == "triggerNextResponse" || call.method == "triggerPrevResponse"){
                val titleFromFlutter = call.argument<String>("title")
                val artistFromFlutter = call.argument<String>("artist")
                lifecycleScope.launch {
                    val glanceId =
                        GlanceAppWidgetManager(context).getGlanceIds(
                            PlayerWidget::class.java
                        ).last()
//                    var drawable = imageLoader.execute(request).drawable

                    Log.d("TAG", "------------> $glanceId")

                    PlayerWidget().apply {
                        updateAppWidgetState(context, glanceId) {
                            WidgetStateHelper.saveTitleArtist(
                                it,
                                title = titleFromFlutter.orEmpty(),
                                artist = artistFromFlutter.orEmpty(),
                            )
                        }
                        update(context, glanceId)
                    }

//                   setResult(RESULT_OK, intent)
//                   finish()
                }

            }


            else {
                result.notImplemented()
            }
        }

    }

    fun getHelloData(): String {
        return "Hello from android"
    }


}
