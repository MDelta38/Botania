/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.Optional$Interface
 *  mods.railcraft.api.core.items.IMinecartItem
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRailBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityMinecart
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.Optional;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityPoolMinecart;
import vazkii.botania.common.item.ItemMod;

@Optional.Interface(modid="Railcraft", iface="mods.railcraft.api.core.items.IMinecartItem", striprefs=true)
public class ItemPoolMinecart
extends ItemMod
implements ICraftAchievement,
IMinecartItem {
    public ItemPoolMinecart() {
        this.func_77625_d(1);
        this.func_77655_b("poolMinecart");
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (BlockRailBase.func_150051_a((Block)p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_))) {
            if (!p_77648_3_.field_72995_K) {
                EntityPoolMinecart entityminecart = new EntityPoolMinecart(p_77648_3_, (double)p_77648_4_ + 0.5, (double)p_77648_5_ + 0.5, (double)p_77648_6_ + 0.5);
                if (p_77648_1_.func_82837_s()) {
                    entityminecart.func_96094_a(p_77648_1_.func_82833_r());
                }
                p_77648_3_.func_72838_d((Entity)entityminecart);
            }
            --p_77648_1_.field_77994_a;
            return true;
        }
        return false;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.manaCartCraft;
    }

    public boolean canBePlacedByNonPlayer(ItemStack cart) {
        return true;
    }

    public EntityMinecart placeCart(GameProfile owner, ItemStack cart, World world, int i, int j, int k) {
        if (BlockRailBase.func_150051_a((Block)world.func_147439_a(i, j, k)) && !world.field_72995_K) {
            EntityPoolMinecart entityminecart = new EntityPoolMinecart(world, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5);
            if (cart.func_82837_s()) {
                entityminecart.func_96094_a(cart.func_82833_r());
            }
            if (world.func_72838_d((Entity)entityminecart)) {
                return entityminecart;
            }
        }
        return null;
    }
}

