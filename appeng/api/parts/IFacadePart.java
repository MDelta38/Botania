/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.parts.IFacadeContainer;
import appeng.api.parts.IPartCollisionHelper;
import appeng.api.parts.IPartRenderHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public interface IFacadePart {
    public ItemStack getItemStack();

    public void getBoxes(IPartCollisionHelper var1, Entity var2);

    @SideOnly(value=Side.CLIENT)
    public void renderStatic(int var1, int var2, int var3, IPartRenderHelper var4, RenderBlocks var5, IFacadeContainer var6, AxisAlignedBB var7, boolean var8);

    @SideOnly(value=Side.CLIENT)
    public void renderInventory(IPartRenderHelper var1, RenderBlocks var2);

    public ForgeDirection getSide();

    public AxisAlignedBB getPrimaryBox();

    public Item getItem();

    public int getItemDamage();

    public boolean isBC();

    public void setThinFacades(boolean var1);

    public boolean isTransparent();
}

