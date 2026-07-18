/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.relic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.wand.ICoordBoundItem;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemFlugelEye
extends ItemRelic
implements ICoordBoundItem,
IManaUsingItem {
    private static final String TAG_X = "x";
    private static final String TAG_Y = "y";
    private static final String TAG_Z = "z";
    private static final String TAG_DIMENSION = "dim";

    public ItemFlugelEye() {
        super("flugelEye");
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (player.func_70093_af()) {
            if (world.field_72995_K) {
                player.func_71038_i();
                for (int i = 0; i < 10; ++i) {
                    float x1 = (float)((double)x + Math.random());
                    float y1 = y + 1;
                    float z1 = (float)((double)z + Math.random());
                    Botania.proxy.wispFX(player.field_70170_p, x1, y1, z1, (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random() * 0.5f, -0.05f + (float)Math.random() * 0.05f);
                }
            } else {
                ItemNBTHelper.setInt(stack, TAG_X, x);
                ItemNBTHelper.setInt(stack, TAG_Y, y);
                ItemNBTHelper.setInt(stack, TAG_Z, z);
                ItemNBTHelper.setInt(stack, TAG_DIMENSION, world.field_73011_w.field_76574_g);
                world.func_72956_a((Entity)player, "mob.endermen.portal", 1.0f, 5.0f);
            }
        }
        return true;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        float x = (float)(player.field_70165_t - Math.random() * (double)player.field_70130_N);
        float y = (float)(player.field_70163_u - 1.6 + Math.random());
        float z = (float)(player.field_70161_v - Math.random() * (double)player.field_70130_N);
        Botania.proxy.wispFX(player.field_70170_p, x, y, z, (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random() * 0.7f, -0.05f - (float)Math.random() * 0.05f);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
        int x = ItemNBTHelper.getInt(stack, TAG_X, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_Y, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_Z, 0);
        int dim = ItemNBTHelper.getInt(stack, TAG_DIMENSION, 0);
        int cost = (int)(MathHelper.pointDistanceSpace((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, player.field_70165_t, player.field_70163_u, player.field_70161_v) * 10.0f);
        if (y > -1 && dim == world.field_73011_w.field_76574_g && ManaItemHandler.requestManaExact(stack, player, cost, true)) {
            ItemFlugelEye.moveParticlesAndSound((Entity)player);
            if (player instanceof EntityPlayerMP && !world.field_72995_K) {
                ((EntityPlayerMP)player).field_71135_a.func_147364_a((double)x + 0.5, (double)y + 1.6, (double)z + 0.5, player.field_70177_z, player.field_70125_A);
            }
            ItemFlugelEye.moveParticlesAndSound((Entity)player);
        }
        return stack;
    }

    private static void moveParticlesAndSound(Entity entity) {
        for (int i = 0; i < 15; ++i) {
            float x = (float)(entity.field_70165_t + Math.random());
            float y = (float)(entity.field_70163_u - 1.6 + Math.random());
            float z = (float)(entity.field_70161_v + Math.random());
            Botania.proxy.wispFX(entity.field_70170_p, x, y, z, (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), -0.3f + (float)Math.random() * 0.2f);
        }
        if (!entity.field_70170_p.field_72995_K) {
            entity.field_70170_p.func_72956_a(entity, "mob.endermen.portal", 1.0f, 1.0f);
        }
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 40;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    @Override
    public ChunkCoordinates getBinding(ItemStack stack) {
        int x = ItemNBTHelper.getInt(stack, TAG_X, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_Y, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_Z, 0);
        return y == -1 ? null : new ChunkCoordinates(x, y, z);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

