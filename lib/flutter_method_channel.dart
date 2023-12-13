import 'package:flutter/services.dart';
import 'package:flutter_wid/song_model.dart';

class FlutterMChannel {
  static int curIndex = 0;
  static const channelName =
      'atof'; // this channel name needs to match the one in Native method channel
  late MethodChannel methodChannel;

  static final FlutterMChannel instance = FlutterMChannel._init();
  FlutterMChannel._init();

  void configureChannel() {
    methodChannel = const MethodChannel(channelName);
    methodChannel.setMethodCallHandler(methodHandler); // set method handler
  }

  Future<void> methodHandler(MethodCall call) async {
    // final String idea = call.arguments;
    const channel = MethodChannel("data_channel");

    switch (call.method) {
      case "triggerNext":
        if (curIndex < songData.length - 1) {
          curIndex++;
          await channel.invokeMethod<String>("triggerNextResponse", {
            'title': songData[curIndex].name,
            'artist': songData[curIndex].artist
          });
        }
        break;
      case "triggerPrev":
        if (curIndex > 0) {
          curIndex--;
          await channel.invokeMethod<String>("triggerPrevResponse", {
            'title': songData[curIndex].name,
            'artist': songData[curIndex].artist
          });
        }
        break;
      default:
        print('no method handler for method ${call.method}');
    }
  }
}
