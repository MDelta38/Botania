/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.botania.api.item.IAvatarTile;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.brew.ModPotions;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemTornadoRod
extends ItemMod
implements IManaUsingItem,
IAvatarWieldable {
    private static final ResourceLocation avatarOverlay = new ResourceLocation("botania:textures/model/avatarTornado.png");
    private static final int FLY_TIME = 20;
    private static final int FALL_MULTIPLIER = 3;
    private static final int MAX_DAMAGE = 60;
    private static final int COST = 350;
    private static final String TAG_FLYING = "flying";
    IIcon iconIdle;
    IIcon iconFlying;

    public ItemTornadoRod() {
        this.func_77656_e(60);
        this.func_77655_b("tornadoRod");
        this.func_77625_d(1);
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean holding) {
        if (par3Entity instanceof EntityPlayer) {
            boolean damaged;
            EntityPlayer player = (EntityPlayer)par3Entity;
            player.func_71045_bC();
            boolean bl = damaged = par1ItemStack.func_77960_j() > 0;
            if (damaged && !this.isFlying(par1ItemStack)) {
                par1ItemStack.func_77964_b(par1ItemStack.func_77960_j() - 1);
            }
            int max = 60;
            if (par1ItemStack.func_77960_j() >= max) {
                this.setFlying(par1ItemStack, false);
                player.func_71034_by();
            } else if (this.isFlying(par1ItemStack)) {
                if (holding) {
                    player.field_70143_R = 0.0f;
                    player.field_70181_x = IManaProficiencyArmor.Helper.hasProficiency(player) ? 1.6 : 1.25;
                    player.field_70170_p.func_72956_a((Entity)player, "botania:airRod", 0.1f, 0.25f);
                    for (int i = 0; i < 5; ++i) {
                        Botania.proxy.wispFX(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.25f, 0.25f, 0.25f, 0.35f + (float)Math.random() * 0.1f, 0.2f * (float)(Math.random() - 0.5), -0.01f * (float)Math.random(), 0.2f * (float)(Math.random() - 0.5));
                    }
                }
                par1ItemStack.func_77964_b(Math.min(max, par1ItemStack.func_77960_j() + 3));
                if (par1ItemStack.func_77960_j() == 60) {
                    this.setFlying(par1ItemStack, false);
                }
            }
            if (damaged) {
                player.field_70143_R = 0.0f;
            }
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int meta = par1ItemStack.func_77960_j();
        if (meta != 0 || ManaItemHandler.requestManaExactForTool(par1ItemStack, par3EntityPlayer, 350, false)) {
            par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
            if (meta == 0) {
                this.setFlying(par1ItemStack, true);
                ManaItemHandler.requestManaExactForTool(par1ItemStack, par3EntityPlayer, 350, true);
            }
        }
        return par1ItemStack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
    }

    public IIcon func_77650_f(ItemStack par1ItemStack) {
        return this.isFlying(par1ItemStack) ? this.iconFlying : this.iconIdle;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 720000;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.iconIdle = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconFlying = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public boolean isFlying(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_FLYING, false);
    }

    public void setFlying(ItemStack stack, boolean flying) {
        ItemNBTHelper.setBoolean(stack, TAG_FLYING, flying);
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
        if (tile.getCurrentMana() >= 350 && tile.isEnabled()) {
            int range = 5;
            int rangeY = 3;
            List players = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)((double)te.field_145851_c + 0.5 - (double)range), (double)((double)te.field_145848_d + 0.5 - (double)rangeY), (double)((double)te.field_145849_e + 0.5 - (double)range), (double)((double)te.field_145851_c + 0.5 + (double)range), (double)((double)te.field_145848_d + 0.5 + (double)rangeY), (double)((double)te.field_145849_e + 0.5 + (double)range)));
            for (EntityPlayer p : players) {
                if (!(p.field_70181_x > 0.3) || !(p.field_70181_x < 2.0) || p.func_70093_af()) continue;
                p.field_70181_x = 2.8;
                for (int i = 0; i < 20; ++i) {
                    for (int j = 0; j < 5; ++j) {
                        Botania.proxy.wispFX(p.field_70170_p, p.field_70165_t, p.field_70163_u + (double)i, p.field_70161_v, 0.25f, 0.25f, 0.25f, 0.35f + (float)Math.random() * 0.1f, 0.2f * (float)(Math.random() - 0.5), -0.01f * (float)Math.random(), 0.2f * (float)(Math.random() - 0.5));
                    }
                }
                if (world.field_72995_K) continue;
                p.field_70170_p.func_72956_a((Entity)p, "botania:dash", 1.0f, 1.0f);
                p.func_70690_d(new PotionEffect(ModPotions.featherfeet.field_76415_H, 100, 0));
                tile.recieveMana(-350);
            }
        }
    }

    @Override
    public ResourceLocation getOverlayResource(IAvatarTile tile, ItemStack stack) {
        return avatarOverlay;
    }
}

