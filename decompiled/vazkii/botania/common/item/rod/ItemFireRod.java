/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.botania.api.item.IAvatarTile;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.EntityFlameRing;
import vazkii.botania.common.item.ItemMod;

public class ItemFireRod
extends ItemMod
implements IManaUsingItem,
IAvatarWieldable {
    private static final ResourceLocation avatarOverlay = new ResourceLocation("botania:textures/model/avatarFire.png");
    private static final int COST = 900;
    private static final int COOLDOWN = 1200;

    public ItemFireRod() {
        this.func_77655_b("fireRod");
        this.func_77625_d(1);
        this.func_77656_e(1200);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer player, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (!par3World.field_72995_K && par1ItemStack.func_77960_j() == 0 && ManaItemHandler.requestManaExactForTool(par1ItemStack, player, 900, false)) {
            EntityFlameRing entity = new EntityFlameRing(player.field_70170_p);
            entity.func_70107_b((double)x + 0.5, y + 1, (double)z + 0.5);
            player.field_70170_p.func_72838_d((Entity)entity);
            par1ItemStack.func_77964_b(1200);
            ManaItemHandler.requestManaExactForTool(par1ItemStack, player, 900, true);
            par3World.func_72956_a((Entity)player, "mob.blaze.breathe", 1.0f, 1.0f);
        }
        return true;
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.func_77951_h() && par3Entity instanceof EntityPlayer) {
            par1ItemStack.func_77964_b(par1ItemStack.func_77960_j() - (IManaProficiencyArmor.Helper.hasProficiency((EntityPlayer)par3Entity) ? 2 : 1));
        }
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
        if (!world.field_72995_K && tile.getCurrentMana() >= 900 && tile.getElapsedFunctionalTicks() % 300 == 0 && tile.isEnabled()) {
            EntityFlameRing entity = new EntityFlameRing(world);
            entity.func_70107_b((double)te.field_145851_c + 0.5, te.field_145848_d, (double)te.field_145849_e + 0.5);
            world.func_72838_d((Entity)entity);
            tile.recieveMana(-900);
        }
    }

    @Override
    public ResourceLocation getOverlayResource(IAvatarTile tile, ItemStack stack) {
        return avatarOverlay;
    }
}

