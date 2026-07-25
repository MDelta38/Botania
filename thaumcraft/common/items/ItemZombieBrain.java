/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class ItemZombieBrain
extends ItemFood {
    public IIcon icon;

    public ItemZombieBrain() {
        super(4, 0.2f, true);
        this.func_77844_a(Potion.field_76438_s.field_76415_H, 30, 0, 0.8f);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:brain");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.icon;
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K && player instanceof EntityPlayerMP) {
            if (world.field_73012_v.nextFloat() < 0.1f) {
                Thaumcraft.addStickyWarpToPlayer(player, 1);
            } else {
                Thaumcraft.addWarpToPlayer(player, 1 + world.field_73012_v.nextInt(3), true);
            }
        }
        return super.func_77654_b(stack, world, player);
    }
}

