/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileCacophonium;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemCacophonium
extends ItemMod
implements ICraftAchievement {
    private static final String TAG_SOUND = "sound";
    private static final String TAG_SOUND_NAME = "soundName";
    private static final String TAG_HAS_SOUND = "hasSound";

    public ItemCacophonium() {
        this.func_77625_d(1);
        this.func_77655_b("cacophonium");
    }

    public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
        if (entity instanceof EntityLiving) {
            EntityLiving living = (EntityLiving)entity;
            String sound = null;
            try {
                sound = living instanceof EntityCreeper ? "creeper.primed" : (living instanceof EntitySlime ? "mob.slime." + (((EntitySlime)living).func_70809_q() > 1 ? "big" : "small") : (String)ReflectionHelper.findMethod(EntityLiving.class, (Object)living, (String[])LibObfuscation.GET_LIVING_SOUND, (Class[])new Class[0]).invoke(living, new Object[0]));
                if (sound != null) {
                    String s = EntityList.func_75621_b((Entity)entity);
                    if (s == null) {
                        s = "generic";
                    }
                    ItemNBTHelper.setString(stack, TAG_SOUND, sound);
                    ItemNBTHelper.setString(stack, TAG_SOUND_NAME, "entity." + s + ".name");
                    ItemNBTHelper.setBoolean(stack, TAG_HAS_SOUND, true);
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, stack.func_77946_l());
                    if (player.field_70170_p.field_72995_K) {
                        player.func_71038_i();
                    }
                    return true;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float xs, float ys, float zs) {
        Block block;
        boolean can = ItemCacophonium.isDOIT(stack);
        if (!can) {
            String sound = ItemNBTHelper.getString(stack, TAG_SOUND, "");
            ItemCacophonium.isDOIT(stack);
            if (sound != null && !sound.isEmpty()) {
                can = true;
            }
        }
        if (can && (block = world.func_147439_a(x, y, z)) == Blocks.field_150323_B) {
            world.func_147449_b(x, y, z, ModBlocks.cacophonium);
            ((TileCacophonium)world.func_147438_o((int)x, (int)y, (int)z)).stack = stack.func_77946_l();
            --stack.field_77994_a;
            return true;
        }
        return false;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        if (ItemCacophonium.isDOIT(stack)) {
            list.add(StatCollector.func_74838_a((String)"botaniamisc.justDoIt"));
        } else if (ItemNBTHelper.getBoolean(stack, TAG_HAS_SOUND, false)) {
            list.add(StatCollector.func_74838_a((String)ItemNBTHelper.getString(stack, TAG_SOUND_NAME, "")));
        }
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (ItemNBTHelper.getBoolean(par1ItemStack, TAG_HAS_SOUND, false) || ItemCacophonium.isDOIT(par1ItemStack)) {
            par3EntityPlayer.func_71008_a(par1ItemStack, 72000);
        }
        return par1ItemStack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (count % (ItemCacophonium.isDOIT(stack) ? 20 : 6) == 0) {
            ItemCacophonium.playSound(player.field_70170_p, stack, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.9f);
        }
    }

    public static void playSound(World world, ItemStack stack, double x, double y, double z, float volume) {
        if (stack == null) {
            return;
        }
        String sound = ItemNBTHelper.getString(stack, TAG_SOUND, "");
        boolean doit = ItemCacophonium.isDOIT(stack);
        if (doit) {
            sound = "botania:doit";
        }
        if (sound != null && !sound.isEmpty()) {
            world.func_72908_a(x, y, z, sound, volume, doit ? 1.0f : (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2f + 1.0f);
        }
    }

    private static boolean isDOIT(ItemStack stack) {
        return stack != null && stack.func_82833_r().equalsIgnoreCase("shia labeouf");
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.cacophoniumCraft;
    }
}

