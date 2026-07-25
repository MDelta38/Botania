/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.client.lib;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;

public class PlayerNotifications {
    public static ArrayList<Notification> notificationList = new ArrayList();
    public static ArrayList<AspectNotification> aspectList = new ArrayList();

    public static void addNotification(String text) {
        PlayerNotifications.addNotification(text, null, 0xFFFFFF);
    }

    public static void addAspectNotification(Aspect aspect) {
        long time = System.nanoTime() / 1000000L + (long)Minecraft.func_71410_x().field_71441_e.field_73012_v.nextInt(1000);
        float x = 0.4f + Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() * 0.2f;
        float y = 0.4f + Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() * 0.2f;
        aspectList.add(new AspectNotification(aspect, x, y, time, time + 1500L));
    }

    public static void addNotification(String text, Aspect aspect) {
        PlayerNotifications.addNotification(text, aspect.getImage(), aspect.getColor());
    }

    public static void addNotification(String text, ResourceLocation image) {
        PlayerNotifications.addNotification(text, image, 0xFFFFFF);
    }

    public static void addNotification(String text, ResourceLocation image, int color) {
        long time = System.nanoTime() / 1000000L;
        long timeBonus = notificationList.size() == 0 ? (long)(Config.notificationDelay / 2) : 0L;
        notificationList.add(new Notification(text, image, time + (long)Config.notificationDelay + timeBonus, time + (long)(Config.notificationDelay / 4), color));
    }

    public static ArrayList<Notification> getListAndUpdate(long time) {
        ArrayList<Notification> temp = new ArrayList<Notification>();
        boolean first = true;
        for (Notification li : notificationList) {
            if (li.expire >= time) {
                if (!first) {
                    temp.add(new Notification(li.text, li.image, time + (long)Config.notificationDelay, li.created, li.color));
                } else {
                    temp.add(li);
                }
            }
            first = false;
        }
        notificationList = temp;
        return temp;
    }

    public static ArrayList<AspectNotification> getAspectListAndUpdate(long time) {
        ArrayList<AspectNotification> temp = new ArrayList<AspectNotification>();
        for (AspectNotification li : aspectList) {
            if (li.expire < time) continue;
            temp.add(li);
        }
        aspectList = temp;
        return temp;
    }

    public static class AspectNotification {
        public Aspect aspect;
        public float startX;
        public float startY;
        public long expire;
        public long created;

        public AspectNotification(Aspect aspect, float startX, float startY, long created, long expire) {
            this.aspect = aspect;
            this.startX = startX;
            this.startY = startY;
            this.expire = expire;
            this.created = created;
        }
    }

    public static class Notification {
        public String text;
        public ResourceLocation image;
        public long expire;
        public long created;
        public int color;

        public Notification(String text, ResourceLocation image, long expire, long created, int color) {
            this.text = text;
            this.image = image;
            this.expire = expire;
            this.created = created;
            this.color = color;
        }
    }
}

