/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.item.ItemMod;

public class ItemSpark
extends ItemMod
implements ICraftAchievement,
IManaGivingItem {
    public static IIcon invIcon;
    public static IIcon worldIcon;

    public ItemSpark() {
        this.func_77655_b("spark");
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xv, float yv, float zv) {
        ISparkAttachable attach;
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile instanceof ISparkAttachable && (attach = (ISparkAttachable)tile).canAttachSpark(stack) && attach.getAttachedSpark() == null) {
            --stack.field_77994_a;
            if (!world.field_72995_K) {
                EntitySpark spark = new EntitySpark(world);
                spark.func_70107_b((double)x + 0.5, (double)y + 1.5, (double)z + 0.5);
                world.func_72838_d((Entity)spark);
                attach.attachSpark(spark);
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
            }
            return true;
        }
        return false;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        invIcon = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        worldIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public IIcon func_77617_a(int p_77617_1_) {
        return invIcon;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.sparkCraft;
    }
}

