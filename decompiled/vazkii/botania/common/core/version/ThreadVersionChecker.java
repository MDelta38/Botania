/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.core.version;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import vazkii.botania.common.core.version.VersionChecker;

public class ThreadVersionChecker
extends Thread {
    public ThreadVersionChecker() {
        this.setName("Botania Version Checker Thread");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/Vazkii/Botania/master/version/1.7.10.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
            VersionChecker.onlineVersion = r.readLine();
            r.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        VersionChecker.doneChecking = true;
    }
}

