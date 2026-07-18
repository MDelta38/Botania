/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.relic;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemDice
extends ItemRelic {
    private static final int[] SIDES_FOR_MOON_PHASES = new int[]{-1, 0, 1, 2, -1, 2, 3, 4};
    public static ItemStack[] relicStacks;

    public ItemDice() {
        super("dice");
        relicStacks = new ItemStack[]{new ItemStack(ModItems.infiniteFruit), new ItemStack(ModItems.kingKey), new ItemStack(ModItems.flugelEye), new ItemStack(ModItems.thorRing), new ItemStack(ModItems.odinRing), new ItemStack(ModItems.lokiRing)};
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (ItemDice.isRightPlayer(player, stack) && !player.field_70170_p.field_72995_K) {
            int moonPhase = world.field_73011_w.func_76559_b(world.func_72820_D());
            int side = SIDES_FOR_MOON_PHASES[moonPhase];
            int relic = side;
            if (this.hasRelicAlready(player, relic)) {
                ArrayList<Integer> possible = new ArrayList<Integer>();
                ArrayList<Integer> alreadyHas = new ArrayList<Integer>();
                for (int i = 0; i < 6; ++i) {
                    if (this.hasRelicAlready(player, i)) {
                        alreadyHas.add(i);
                        continue;
                    }
                    possible.add(i);
                }
                if (alreadyHas.size() > 0) {
                    possible.add((Integer)alreadyHas.get(world.field_73012_v.nextInt(alreadyHas.size())));
                }
                relic = (Integer)possible.get(world.field_73012_v.nextInt(possible.size()));
            }
            world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (world.field_73012_v.nextFloat() * 0.4f + 0.8f));
            if (this.hasRelicAlready(player, relic)) {
                player.func_145747_a(new ChatComponentTranslation("botaniamisc.dudDiceRoll", new Object[]{relic + 1}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.DARK_GREEN)));
                --stack.field_77994_a;
                return stack;
            }
            player.func_145747_a(new ChatComponentTranslation("botaniamisc.diceRoll", new Object[]{relic + 1}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.DARK_GREEN)));
            return relicStacks[relic].func_77946_l();
        }
        return stack;
    }

    @Override
    public boolean shouldDamageWrongPlayer() {
        return false;
    }

    boolean hasRelicAlready(EntityPlayer player, int relic) {
        if (relic < 0 || relic > 5 || !(player instanceof EntityPlayerMP)) {
            return true;
        }
        EntityPlayerMP mpPlayer = (EntityPlayerMP)player;
        Item item = relicStacks[relic].func_77973_b();
        IRelic irelic = (IRelic)item;
        Achievement achievement = irelic.getBindAchievement();
        return mpPlayer.func_147099_x().func_77443_a(achievement);
    }
}

