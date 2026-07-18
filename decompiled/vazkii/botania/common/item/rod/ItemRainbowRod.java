/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.item.IAvatarTile;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileBifrost;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemMod;

public class ItemRainbowRod
extends ItemMod
implements IManaUsingItem,
IAvatarWieldable {
    private static final ResourceLocation avatarOverlay = new ResourceLocation("botania:textures/model/avatarRainbow.png");
    private static final int MANA_COST = 750;
    private static final int MANA_COST_AVATAR = 10;
    private static final int TIME = 600;

    public ItemRainbowRod() {
        this.func_77656_e(600);
        this.func_77655_b("rainbowRod");
        this.func_77625_d(1);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.field_72995_K && par1ItemStack.func_77960_j() == 0 && ManaItemHandler.requestManaExactForTool(par1ItemStack, par3EntityPlayer, 750, false)) {
            int count;
            int time;
            Block place = ModBlocks.bifrost;
            Vector3 vector = new Vector3(par3EntityPlayer.func_70040_Z()).normalize();
            double x = par3EntityPlayer.field_70165_t;
            double y = par3EntityPlayer.field_70163_u;
            double z = par3EntityPlayer.field_70161_v;
            double lx = 0.0;
            double ly = -1.0;
            double lz = 0.0;
            boolean prof = IManaProficiencyArmor.Helper.hasProficiency(par3EntityPlayer);
            int maxlen = prof ? 160 : 100;
            int n = time = prof ? 960 : 600;
            for (count = 0; (count < maxlen && (int)lx == (int)x && (int)ly == (int)y && (int)lz == (int)z || count < 4 || par2World.func_147439_a((int)x, (int)y, (int)z).isAir((IBlockAccess)par2World, (int)x, (int)y, (int)z) || par2World.func_147439_a((int)x, (int)y, (int)z) == place) && !(y >= 256.0) && !(y <= 0.0); ++count) {
                for (int i = -2; i < 1; ++i) {
                    for (int j = -2; j < 1; ++j) {
                        if (!par2World.func_147439_a((int)x + i, (int)y, (int)z + j).isAir((IBlockAccess)par2World, (int)x + i, (int)y, (int)z + j) && par2World.func_147439_a((int)x + i, (int)y, (int)z + j) != place) continue;
                        par2World.func_147449_b((int)x + i, (int)y, (int)z + j, place);
                        TileBifrost tile = (TileBifrost)par2World.func_147438_o((int)x + i, (int)y, (int)z + j);
                        if (tile == null) continue;
                        for (int k = 0; k < 4; ++k) {
                            Botania.proxy.sparkleFX(par2World, (double)tile.field_145851_c + Math.random(), (double)tile.field_145848_d + Math.random(), (double)tile.field_145849_e + Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 0.45f + 0.2f * (float)Math.random(), 6);
                        }
                        tile.ticks = time;
                    }
                }
                lx = x;
                ly = y;
                lz = z;
                x += vector.x;
                y += vector.y;
                z += vector.z;
            }
            if (count > 0) {
                par2World.func_72956_a((Entity)par3EntityPlayer, "botania:bifrostRod", 0.5f, 0.25f);
                ManaItemHandler.requestManaExactForTool(par1ItemStack, par3EntityPlayer, 750, false);
                par1ItemStack.func_77964_b(600);
            }
        }
        return par1ItemStack;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return itemStack.func_77946_l();
    }

    public boolean hasContainerItem(ItemStack stack) {
        return this.getContainerItem(stack) != null;
    }

    public boolean func_77630_h(ItemStack par1ItemStack) {
        return false;
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.func_77951_h()) {
            par1ItemStack.func_77964_b(par1ItemStack.func_77960_j() - 1);
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
        if (world.field_72995_K || tile.getCurrentMana() < 250 || !tile.isEnabled()) {
            return;
        }
        int x = te.field_145851_c;
        int y = te.field_145848_d;
        int z = te.field_145849_e;
        int w = 1;
        int h = 1;
        int l = 20;
        AxisAlignedBB axis = null;
        switch (te.func_145832_p() - 2) {
            case 0: {
                axis = AxisAlignedBB.func_72330_a((double)(x - w), (double)(y - h), (double)(z - l), (double)(x + w + 1), (double)(y + h), (double)z);
                break;
            }
            case 1: {
                axis = AxisAlignedBB.func_72330_a((double)(x - w), (double)(y - h), (double)(z + 1), (double)(x + w + 1), (double)(y + h), (double)(z + l + 1));
                break;
            }
            case 2: {
                axis = AxisAlignedBB.func_72330_a((double)(x - l), (double)(y - h), (double)(z - w), (double)x, (double)(y + h), (double)(z + w + 1));
                break;
            }
            case 3: {
                axis = AxisAlignedBB.func_72330_a((double)(x + 1), (double)(y - h), (double)(z - w), (double)(x + l + 1), (double)(y + h), (double)(z + w + 1));
            }
        }
        List players = world.func_72872_a(EntityPlayer.class, axis);
        for (EntityPlayer p : players) {
            int px = MathHelper.func_76128_c((double)p.field_70165_t);
            int py = MathHelper.func_76128_c((double)p.field_70163_u) - 1;
            int pz = MathHelper.func_76128_c((double)p.field_70161_v);
            int dist = 5;
            int diff = dist / 2;
            for (int i = 0; i < dist; ++i) {
                for (int j = 0; j < dist; ++j) {
                    TileBifrost tileBifrost;
                    int ex = px + i - diff;
                    int ez = pz + j - diff;
                    if (!axis.func_72318_a(Vec3.func_72443_a((double)((double)ex + 0.5), (double)(py + 1), (double)((double)ez + 0.5)))) continue;
                    Block block = world.func_147439_a(ex, py, ez);
                    if (block.isAir((IBlockAccess)world, ex, py, ez)) {
                        world.func_147449_b(ex, py, ez, ModBlocks.bifrost);
                        tileBifrost = (TileBifrost)world.func_147438_o(ex, py, ez);
                        tileBifrost.ticks = 10;
                        tile.recieveMana(-10);
                        continue;
                    }
                    if (block != ModBlocks.bifrost) continue;
                    tileBifrost = (TileBifrost)world.func_147438_o(ex, py, ez);
                    if (tileBifrost.ticks >= 2) continue;
                    tileBifrost.ticks = 10;
                    tile.recieveMana(-10);
                }
            }
        }
    }

    @Override
    public ResourceLocation getOverlayResource(IAvatarTile tile, ItemStack stack) {
        return avatarOverlay;
    }
}

