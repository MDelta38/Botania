/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.botania.api.item.IAvatarTile;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.item.ItemMod;

public class ItemMissileRod
extends ItemMod
implements IManaUsingItem,
IAvatarWieldable {
    private static final ResourceLocation avatarOverlay = new ResourceLocation("botania:textures/model/avatarMissile.png");
    private static final int COST_PER = 120;
    private static final int COST_AVATAR = 40;

    public ItemMissileRod() {
        this.func_77625_d(1);
        this.func_77655_b("missileRod");
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (count != this.func_77626_a(stack) && count % (IManaProficiencyArmor.Helper.hasProficiency(player) ? 1 : 2) == 0 && !player.field_70170_p.field_72995_K && ManaItemHandler.requestManaExactForTool(stack, player, 120, false)) {
            if (this.spawnMissile(player.field_70170_p, (EntityLivingBase)player, player.field_70165_t + (Math.random() - 0.05), player.field_70163_u + 2.4 + (Math.random() - 0.05), player.field_70161_v + (Math.random() - 0.05))) {
                ManaItemHandler.requestManaExactForTool(stack, player, 120, true);
            }
            Botania.proxy.sparkleFX(player.field_70170_p, player.field_70165_t, player.field_70163_u + 2.4, player.field_70161_v, 1.0f, 0.4f, 1.0f, 6.0f, 6);
        }
    }

    public boolean spawnMissile(World world, EntityLivingBase thrower, double x, double y, double z) {
        EntityMagicMissile missile = thrower != null ? new EntityMagicMissile(thrower, false) : new EntityMagicMissile(world);
        missile.func_70107_b(x, y, z);
        if (missile.getTarget()) {
            if (!world.field_72995_K) {
                world.func_72908_a(x, y, z, "botania:missile", 0.6f, 0.8f + (float)Math.random() * 0.2f);
                world.func_72838_d((Entity)missile);
            }
            return true;
        }
        return false;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public void onAvatarUpdate(IAvatarTile tile, ItemStack stack) {
        TileEntity te = (TileEntity)tile;
        World world = te.func_145831_w();
        if (tile.getCurrentMana() >= 40 && tile.getElapsedFunctionalTicks() % 3 == 0 && tile.isEnabled() && this.spawnMissile(world, null, (double)te.field_145851_c + 0.5 + (Math.random() - 0.05), (double)te.field_145848_d + 2.5 + (Math.random() - 0.05), (double)te.field_145849_e + (Math.random() - 0.05))) {
            if (!world.field_72995_K) {
                tile.recieveMana(-40);
            }
            Botania.proxy.sparkleFX(world, (double)te.field_145851_c + 0.5, (double)te.field_145848_d + 2.5, (double)te.field_145849_e + 0.5, 1.0f, 0.4f, 1.0f, 6.0f, 6);
        }
    }

    @Override
    public ResourceLocation getOverlayResource(IAvatarTile tile, ItemStack stack) {
        return avatarOverlay;
    }
}

