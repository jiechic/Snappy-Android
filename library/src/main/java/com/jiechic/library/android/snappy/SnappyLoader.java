/*--------------------------------------------------------------------------
 *  Copyright 2011 Taro L. Saito
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *--------------------------------------------------------------------------*/
//--------------------------------------
// snappy-java Project
//
// SnappyLoader.java
// Since: 2011/03/29
//
// $URL$ 
// $Author$
//--------------------------------------
package com.jiechic.library.android.snappy;

import java.io.*;

/**
 * <b>Internal only - Do not use this class.</b> This class loads a native
 * library of snappy-java (snappyjava.dll, libsnappy.so, etc.) according to the
 * user platform (<i>os.name</i> and <i>os.arch</i>). The natively compiled
 * libraries bundled to snappy-java contain the codes of the original snappy and
 * JNI programs to access Snappy.
 * <p/>
 * In default, no configuration is required to use snappy-java, but you can load
 * your own native library created by 'make native' command.
 * <p/>
 * This SnappyLoader searches for native libraries (snappyjava.dll,
 * libsnappy.so, etc.) in the following order:
 * <ol>
 * <li>If system property <i>org.xerial.snappy.use.systemlib</i> is set to true,
 * lookup folders specified by <i>java.lib.path</i> system property (This is the
 * default path that JVM searches for native libraries)
 * <li>(System property: <i>org.xerial.snappy.lib.path</i>)/(System property:
 * <i>org.xerial.lib.name</i>)
 * <li>One of the libraries embedded in snappy-java-(version).jar extracted into
 * (System property: <i>java.io.tempdir</i>). If
 * <i>org.xerial.snappy.tempdir</i> is set, use this folder instead of
 * <i>java.io.tempdir</i>.
 * </ol>
 * <p/>
 * <p>
 * If you do not want to use folder <i>java.io.tempdir</i>, set the System
 * property <i>org.xerial.snappy.tempdir</i>. For example, to use
 * <i>/tmp/leo</i> as a temporary folder to copy native libraries, use -D option
 * of JVM:
 * <p/>
 * <pre>
 * <code>
 * java -Dorg.xerial.snappy.tempdir="/tmp/leo" ...
 * </code>
 * </pre>
 * <p/>
 * </p>
 *
 * @author leo
 */
public class SnappyLoader {
    private static boolean isLoaded = false;

    private static volatile SnappyNative snappyApi = null;

    private static File nativeLibFile = null;

    static void cleanUpExtractedNativeLib() {
        if (nativeLibFile != null && nativeLibFile.exists()) {
            boolean deleted = nativeLibFile.delete();
            if (!deleted) {
                // Deleting native lib has failed, but it's not serious so simply ignore it here
            }
            snappyApi = null;
        }
    }

    /**
     * Set the `snappyApi` instance.
     *
     * @param nativeCode
     */
    static synchronized void setSnappyApi(SnappyNative nativeCode) {
        snappyApi = nativeCode;
    }

    static synchronized SnappyNative loadSnappyApi() {
        if (snappyApi != null) {
            return snappyApi;
        }
        loadNativeLibrary();
        setSnappyApi(new SnappyNative());
        return snappyApi;
    }

    /**
     * Load a native library of snappy-java
     */
    private synchronized static void loadNativeLibrary() {
        if (!isLoaded) {
            // Load preinstalled snappyjava (in the path -Djava.library.path)
            System.loadLibrary("snappy-android");
            isLoaded = true;
        }
    }
}
