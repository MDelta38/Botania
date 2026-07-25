/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.tool;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.kami.SoulHeartHandler;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.tool.IAdvancedTool;
import thaumic.tinkerer.common.item.kami.tool.ItemIchorSword;
import thaumic.tinkerer.common.item.kami.tool.ToolHandler;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemIchorSwordAdv
extends ItemIchorSword
implements IAdvancedTool {
    IIcon[] specialIcons = new IIcon[3];
    boolean ignoreLeftClick = false;

    public ItemIchorSwordAdv() {
        this.func_77627_a(true);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        for (int i = 0; i < this.specialIcons.length; ++i) {
            this.specialIcons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            ToolHandler.changeMode(par1ItemStack);
            ToolModeHUDHandler.setTooltip(ToolHandler.getToolModeStr(this, par1ItemStack));
            return par1ItemStack;
        }
        return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!this.ignoreLeftClick && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).field_70737_aN == 0 && !((EntityLivingBase)entity).field_70128_L) {
            switch (ToolHandler.getMode(stack)) {
                case 0: {
                    break;
                }
                case 1: {
                    int range = 3;
                    List entities = player.field_70170_p.func_72872_a(entity.getClass(), AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - (double)range), (double)(entity.field_70163_u - (double)range), (double)(entity.field_70161_v - (double)range), (double)(entity.field_70165_t + (double)range), (double)(entity.field_70163_u + (double)range), (double)(entity.field_70161_v + (double)range)));
                    this.ignoreLeftClick = true;
                    for (Entity entity_ : entities) {
                        player.func_71059_n(entity_);
                    }
                    this.ignoreLeftClick = false;
                    break;
                }
                case 2: {
                    EntityLivingBase living = (EntityLivingBase)entity;
                    PotionEffect effect = new PotionEffect(Potion.field_76429_m.field_76415_H, 1, 1);
                    living.func_70690_d(effect);
                    SoulHeartHandler.addHearts(player);
                    break;
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    public IIcon func_77617_a(int par1) {
        return par1 >= this.specialIcons.length ? super.func_77617_a(par1) : this.specialIcons[par1];
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(ToolHandler.getToolModeStr(this, par1ItemStack));
    }

    @Override
    public String getType() {
        return "sword";
    }

    @Override
    public String getItemName() {
        return "ichorSwordGem";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHOR_SWORD_GEM", new AspectList().add(Aspect.AIR, 2).add(Aspect.WEAPON, 1).add(Aspect.SOUL, 1).add(Aspect.HUNGER, 1), 16, 12, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHOR_TOOLS"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHOR_SWORD_GEM"), new ResearchPage("1")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHOR_SWORD_GEM", new ItemStack((Item)this), 15, new AspectList().add(Aspect.AIR, 50).add(Aspect.HUNGER, 64).add(Aspect.SOUL, 32).add(Aspect.WEAPON, 32).add(Aspect.ENERGY, 32).add(Aspect.ORDER, 16).add(Aspect.CRYSTAL, 16), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemIchorSword.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 2), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemSwordElemental), new ItemStack(ConfigItems.itemFocusFrost), new ItemStack(Blocks.field_150434_aF), new ItemStack(ConfigItems.itemNugget, 1, 21), new ItemStack(ConfigItems.itemNugget, 1, 16), new ItemStack(ConfigItems.itemNugget, 1, 31), new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemFocusFrost), new ItemStack(ConfigItems.itemSwordElemental), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1));
    }
}

