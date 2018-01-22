# Snappy-Android

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Jcenter](https://img.shields.io/badge/%20Jcenter%20-1.1.7-5bc0de.svg)](https://bintray.com/jiechic/android/rxservice/_latestVersion)

[^_^]:[![Methods](https://img.shields.io/badge/%20Methods%20%7C%20Size%20-%20239%20%7C%2040%20KB-d9534f.svg)](http://www.methodscount.com/?lib=com.jiechic.android.library%3Arxservice%3A1.0.0)
[^_^]:[![Maven](https://img.shields.io/badge/%20Maven%20-1.0.0-5bc0de.svg)](https://mvnrepository.com/artifact/com.akaita.java/rxservice/1.2.0)
[^_^]:[![Arsenal](https://img.shields.io/badge/%20Arsenal%20-%20RxService%20-4cae4c.svg?style=flat)](https://android-arsenal.com/details/1/6027)

snappy-android is a android port of the snappy
<http://code.google.com/p/snappy/>, a fast C++ compresser/decompresser developed by Google.

# Installation

Using *JCenter*:
```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.jiechic.android.library:snappy-android:1.1.7'
}
```

## Usage
First, import `com.jiechic.library.android.snappy.Snappy` in your Java code:

```java
import com.jiechic.library.android.snappy.Snappy;
```

Then use `Snappy.compress(byte[])` and `Snappy.uncompress(byte[])`:

```java
String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
     + "Snappy, a fast compresser/decompresser.";
byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
byte[] uncompressed = Snappy.uncompress(compressed);

String result = new String(uncompressed, "UTF-8");
System.out.println(result);
```

In addition, high-level methods (`Snappy.compress(String)`, `Snappy.compress(float[] ..)` etc. ) and low-level ones (e.g. `Snappy.rawCompress(.. )`,  `Snappy.rawUncompress(..)`, etc.), which minimize memory copies, can be used.


Snappy-Android is developed by [Jiechic](http://www.jiechic.com/). Twitter  [@jiechic](http://twitter.com/#!/jiechic)

# Thanks

https://github.com/google/snappy

https://github.com/xerial/snappy-java
