/*
 * Decompiled with CFR 0.152.
 */
package appeng.api;

import appeng.api.IAppEngApi;

public class AEApi {
    private static IAppEngApi api = null;

    public static IAppEngApi instance() {
        if (api == null) {
            try {
                Class<?> c = Class.forName("appeng.core.Api");
                api = (IAppEngApi)c.getField("instance").get(c);
            }
            catch (Throwable e) {
                return null;
            }
        }
        return api;
    }
}

