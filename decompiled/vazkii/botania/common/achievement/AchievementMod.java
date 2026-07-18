/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 */
package vazkii.botania.common.achievement;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import vazkii.botania.api.item.IRelic;

public class AchievementMod
extends Achievement {
    public static List<Achievement> achievements = new ArrayList<Achievement>();

    public AchievementMod(String name, int x, int y, ItemStack icon, Achievement parent) {
        super("achievement.botania:" + name, "botania:" + name, x, y, icon, parent);
        achievements.add(this);
        this.func_75971_g();
        if (icon.func_77973_b() instanceof IRelic) {
            ((IRelic)icon.func_77973_b()).setBindAchievement(this);
        }
    }

    public AchievementMod(String name, int x, int y, Item icon, Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }

    public AchievementMod(String name, int x, int y, Block icon, Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }
}

