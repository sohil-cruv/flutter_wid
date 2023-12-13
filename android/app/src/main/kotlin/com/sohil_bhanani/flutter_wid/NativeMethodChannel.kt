package com.sohil_bhanani.flutter_wid
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


object NativeMethodChannel {
    private lateinit var methodChannel: MethodChannel
        private const val CHANNEL_NAME = "atof"

    fun configureChannel(flutterEngine: FlutterEngine) {
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL_NAME
        )
    }

    // add the following method, it passes a string to flutter
    fun showNewIdea(idea: String) {
        methodChannel.invokeMethod("showNewIdea", idea)
    }

    fun triggerNext(){
        methodChannel.invokeMethod("triggerNext",null)
    }
    fun triggerPrev(){
        methodChannel.invokeMethod("triggerPrev",null)
    }


}