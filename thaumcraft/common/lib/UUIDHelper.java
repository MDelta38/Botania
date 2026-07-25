/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Strings
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package thaumcraft.common.lib;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class UUIDHelper {
    public static String uuidToName(String uuid) {
        if (!uuid.isEmpty()) {
            try {
                URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + UUIDHelper.cleanUUID(uuid));
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                JsonObject profile = (JsonObject)new JsonParser().parse((Reader)isr);
                return profile.get("name").toString().replace('\"', '\u0000').trim();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return "";
    }

    public static UUID getOnlineUUID(String name) {
        if (!Strings.isNullOrEmpty((String)name)) {
            try {
                URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                JsonObject profile = (JsonObject)new JsonParser().parse((Reader)new InputStreamReader(connection.getInputStream()));
                return UUID.fromString(UUIDHelper.fullUUID(profile.get("id").toString()));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return null;
    }

    public static UUID getOfflineUUID(String name) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
    }

    public static UUID stringToUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static String fullUUID(String uuid) {
        uuid = UUIDHelper.cleanUUID(uuid);
        uuid = uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
        return uuid;
    }

    public static String cleanUUID(String uuid) {
        return uuid.replaceAll("[^a-zA-Z0-9]", "");
    }
}

