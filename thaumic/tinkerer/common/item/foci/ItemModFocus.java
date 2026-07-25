/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.api.wands.ItemFocusBasic
 *  thaumcraft.api.wands.ItemFocusBasic$WandFocusAnimation
 */
package thaumic.tinkerer.common.item.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public abstract class ItemModFocus
extends ItemFocusBasic
implements ITTinkererItem {
    private IIcon ornament;
    private IIcon depth;

    public ItemModFocus() {
        this.func_77656_e(1);
        this.setNoRepair();
        this.func_77625_d(1);
    }

    protected boolean hasOrnament() {
        return false;
    }

    protected boolean hasDepth() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.icon = IconHelper.forItem(par1IconRegister, (Item)this);
        if (this.hasOrnament()) {
            this.ornament = IconHelper.forItem(par1IconRegister, (Item)this, "Orn");
        }
        if (this.hasDepth()) {
            this.depth = IconHelper.forItem(par1IconRegister, (Item)this, "Depth");
        }
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }

    public boolean func_77616_k(ItemStack par1ItemStack) {
        return true;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList cost = this.getVisCost(stack);
        if (cost != null) {
            list.add(StatCollector.func_74838_a((String)(this.isVisCostPerTick(stack) ? "item.Focus.cost2" : "item.Focus.cost1")));
            this.addVisCostTooltip(cost, stack, player, list, par4);
        }
    }

    protected void addVisCostTooltip(AspectList cost, ItemStack stack, EntityPlayer player, List list, boolean par4) {
        for (Aspect aspect : cost.getAspectsSorted()) {
            float amount = (float)cost.getAmount(aspect) / 100.0f;
            list.add(" \u00a7" + aspect.getChatcolor() + aspect.getName() + '\u00a7' + "r x " + amount);
        }
    }

    public int func_77619_b() {
        return 5;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public IIcon getOrnament(ItemStack stack) {
        return this.ornament;
    }

    public IIcon getFocusDepthLayerIcon(ItemStack stack) {
        return this.depth;
    }

    public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack stack) {
        return ItemFocusBasic.WandFocusAnimation.WAVE;
    }

    public boolean isVisCostPerTick(ItemStack stack) {
        return false;
    }

    public boolean isUseItem(ItemStack stack) {
        return this.isVisCostPerTick(stack);
    }

    public ItemStack onFocusRightClick(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, MovingObjectPosition paramMovingObjectPosition) {
        if (this.isUseItem(paramItemStack)) {
            paramEntityPlayer.func_71008_a(paramItemStack, Integer.MAX_VALUE);
        }
        return paramItemStack;
    }

    public void onUsingFocusTick(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt) {
    }

    public void onPlayerStoppedUsingFocus(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, int paramInt) {
    }

    public abstract String getSortingHelper(ItemStack var1);

    public boolean onFocusBlockStartBreak(ItemStack paramItemStack, int paramInt1, int paramInt2, int paramInt3, EntityPlayer paramEntityPlayer) {
        return false;
    }

    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[]{FocusUpgradeType.treasure};
    }
}

