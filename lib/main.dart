import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'flutter_method_channel.dart';

void main() {
  runApp(const MainApp());
  FlutterMChannel.instance.configureChannel(); // configure method channel
}

class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  State<MainApp> createState() => _MainAppState();
}

class _MainAppState extends State<MainApp> {
  final titleController = TextEditingController();
  final artistController = TextEditingController();

  @override
  void dispose() {
    titleController.dispose();
    artistController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    const channel = MethodChannel("data_channel");

    return MaterialApp(
      home: Scaffold(
        body: Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: TextField(
                controller: titleController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), hintText: "Title"),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: TextField(
                controller: artistController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), hintText: "Artist"),
              ),
            ),
            ElevatedButton(
                onPressed: () async {
                  var a = await channel.invokeMethod<String>("TriggerUpdate", {
                    'title': titleController.text,
                    'artist': artistController.text
                  });

                  log(a.toString());
                },
                child: const Text("Send to Widget")),
          ],
        )),
      ),
    );
  }
}
