/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.parts.IBoxProvider;
import appeng.api.parts.ISimplifiedBundle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.EnumSet;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPartRenderHelper {
    public void setBounds(float var1, float var2, float var3, float var4, float var5, float var6);

    @SideOnly(value=Side.CLIENT)
    public void renderFace(int var1, int var2, int var3, IIcon var4, ForgeDirection var5, RenderBlocks var6);

    @SideOnly(value=Side.CLIENT)
    public void renderFaceCutout(int var1, int var2, int var3, IIcon var4, ForgeDirection var5, float var6, RenderBlocks var7);

    @SideOnly(value=Side.CLIENT)
    public void renderBlock(int var1, int var2, int var3, RenderBlocks var4);

    @SideOnly(value=Side.CLIENT)
    public void renderInventoryFace(IIcon var1, ForgeDirection var2, RenderBlocks var3);

    @SideOnly(value=Side.CLIENT)
    public void renderInventoryBox(RenderBlocks var1);

    public void setTexture(IIcon var1, IIcon var2, IIcon var3, IIcon var4, IIcon var5, IIcon var6);

    public void setTexture(IIcon var1);

    public void setInvColor(int var1);

    public Block getBlock();

    public ForgeDirection getWorldX();

    public ForgeDirection getWorldY();

    public ForgeDirection getWorldZ();

    public ISimplifiedBundle useSimplifiedRendering(int var1, int var2, int var3, IBoxProvider var4, ISimplifiedBundle var5);

    public void normalRendering();

    public void renderBlockCurrentBounds(int var1, int var2, int var3, RenderBlocks var4);

    public void renderForPass(int var1);

    public void setFacesToRender(EnumSet<ForgeDirection> var1);
}

