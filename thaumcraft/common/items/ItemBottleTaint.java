/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.projectile.EntityBottleTaint;

public class ItemBottleTaint
extends Item
implements IScribeTools {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemBottleTaint() {
        this.field_77777_bU = 8;
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(false);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:bottle_taint");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!player.field_71075_bZ.field_75098_d) {
            --stack.field_77994_a;
        }
        world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!world.field_72995_K) {
            world.func_72838_d((Entity)new EntityBottleTaint(world, (EntityLivingBase)player));
        }
        return stack;
    }
}

