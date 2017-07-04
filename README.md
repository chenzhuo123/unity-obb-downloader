Unity OBB Downloader
==============================
This package is an adaption of the Google Play market_downloader library, for use with Unity Android (as a plugin).

This plugin does NOT solve splitting up a >100MB .apk into .obb (through asset bundles or similar techiniques). It merely handles the downloading of .obb files attached to a published .apk, on devices that don't support automatic downloading.

This software is free and published as is, with no warranty and responsibilities - use it at your own risk.

Downloads
----------
Please check out our
[releases](//github.com/snoozinsquatch/unity-obb-downloader/releases)
for the latest official version of the plugin.

Documentation
--------------
This plugin is published as an Android Library project, and is tested with Unity 5.

1. Open Unity, create a new project.
2. Add this package
3. Attach the DownloadObbExample.cs to the Main Camera
4. Open GooglePlayDownloader.cs and replace the PUBLIC_KEY with your value
5. Change the Bundle Identifier / Version Code so it matches an application already available on Google Play (that has .obb files attached).
6. Build and Run on your android device.

Build
------
Should you need to re-build the Unity Package, make your changes and run `./gradlew exportPackage`. The unitypackage will be in your directory when done. You must have Unity installed to do this.

If you just want to re-build the `.aar` you can `cd source/android-library` and run `./gradlew build` and the new `.aar` will be in the Gradle outputs folder.

Suggesting improvements
------------------------
To file bugs, make feature requests, or to suggest other improvements,
please use [github's issue tracker](//github.com/snoozinsquatch/unity-obb-downloader/issues).

License
-------
[Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html)
