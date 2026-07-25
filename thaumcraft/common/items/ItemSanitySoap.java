/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

public class ItemSanitySoap
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemSanitySoap() {
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(false);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:soap");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public int func_77626_a(ItemStack p_77626_1_) {
        return 200;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_) {
        return EnumAction.block;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        return p_77659_1_;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int ticks = this.func_77626_a(stack) - count;
        if (ticks > 195) {
            player.func_71034_by();
        }
        if (player.field_70170_p.field_72995_K) {
            if (player.field_70170_p.field_73012_v.nextFloat() < 0.2f) {
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:roots", 0.1f, 1.5f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            }
            for (int a = 0; a < Thaumcraft.proxy.particleCount(5); ++a) {
                Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), (float)player.field_70165_t - 0.5f + player.field_70170_p.field_73012_v.nextFloat(), (float)player.field_70121_D.field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5f + player.field_70170_p.field_73012_v.nextFloat(), 1.0f, 0.8f, 0.9f);
            }
        }
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int par4) {
        int qq = this.func_77626_a(stack) - par4;
        if (qq > 195) {
            --stack.field_77994_a;
            if (!world.field_72995_K) {
                int k;
                int j;
                int i;
                float chance = 0.33f;
                if (player.func_82165_m(Config.potionWarpWardID)) {
                    chance += 0.25f;
                }
                if (world.func_147439_a(i = MathHelper.func_76128_c((double)player.field_70165_t), j = MathHelper.func_76128_c((double)player.field_70163_u), k = MathHelper.func_76128_c((double)player.field_70161_v)) == ConfigBlocks.blockFluidPure) {
                    chance += 0.25f;
                }
                if (world.field_73012_v.nextFloat() < chance && Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) > 0) {
                    Thaumcraft.addStickyWarpToPlayer(player, -1);
                }
                if (Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()) > 0) {
                    Thaumcraft.addWarpToPlayer(player, -Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()), true);
                }
            } else {
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:craftstart", 0.25f, 1.0f, false);
                for (int a = 0; a < Thaumcraft.proxy.particleCount(20); ++a) {
                    Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), (float)player.field_70165_t - 0.5f + player.field_70170_p.field_73012_v.nextFloat() * 1.5f, (float)player.field_70121_D.field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5f + player.field_70170_p.field_73012_v.nextFloat() * 1.5f, 1.0f, 0.7f, 0.9f);
                }
            }
        }
    }
}

