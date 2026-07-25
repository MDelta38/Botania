/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package thaumic.tinkerer.common.core.helper;

import net.minecraft.entity.player.EntityPlayer;

public class ExperienceHelper {
    public static int getPlayerXP(EntityPlayer player) {
        return (int)((float)ExperienceHelper.getExperienceForLevel(player.field_71068_ca) + player.field_71106_cc * (float)player.func_71050_bK());
    }

    public static void drainPlayerXP(EntityPlayer player, int amount) {
        ExperienceHelper.addPlayerXP(player, -amount);
    }

    public static void addPlayerXP(EntityPlayer player, int amount) {
        int experience;
        player.field_71067_cb = experience = ExperienceHelper.getPlayerXP(player) + amount;
        player.field_71068_ca = ExperienceHelper.getLevelForExperience(experience);
        int expForLevel = ExperienceHelper.getExperienceForLevel(player.field_71068_ca);
        player.field_71106_cc = (float)(experience - expForLevel) / (float)player.func_71050_bK();
    }

    public static int getExperienceForLevel(int level) {
        if (level == 0) {
            return 0;
        }
        if (level > 0 && level < 16) {
            return level * 17;
        }
        if (level > 15 && level < 31) {
            return (int)(1.5 * Math.pow(level, 2.0) - 29.5 * (double)level + 360.0);
        }
        return (int)(3.5 * Math.pow(level, 2.0) - 151.5 * (double)level + 2220.0);
    }

    public static int getLevelForExperience(int experience) {
        int i = 0;
        while (ExperienceHelper.getExperienceForLevel(i) <= experience) {
            ++i;
        }
        return i - 1;
    }
}

