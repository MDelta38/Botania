/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.research.ResearchManager
 *  thaumcraft.common.lib.utils.BlockUtils
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.BlockUtils;

public class ItemFocusCompound
extends ItemFocusBasic {
    public static FocusUpgradeType fission = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/fission.png"), "focus.upgrade.fission.name", "focus.upgrade.fission.text", new AspectList().add(Aspect.EXCHANGE, 8));
    private static final AspectList cost = new AspectList().add(Aspect.FIRE, 0).add(Aspect.WATER, 0).add(Aspect.AIR, 0).add(Aspect.EARTH, 0).add(Aspect.ORDER, 0).add(Aspect.ENTROPY, 0);

    public ItemFocusCompound() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public String func_77653_i(ItemStack stack) {
        return StatCollector.func_74838_a((String)"item.focusCompound.name");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:focus_containment");
    }

    @Override
    public int getFocusColor(ItemStack focusstack) {
        return 15054592;
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "CR" + super.getSortingHelper(itemstack);
    }

    @Override
    public AspectList getVisCost(ItemStack focusstack) {
        return cost.copy();
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
        return new FocusUpgradeType[]{fission};
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        p.func_71008_a(itemstack, Integer.MAX_VALUE);
        return itemstack;
    }

    public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve, AspectList nodeAspects, World world) {
        int min = preserve ? 1 : 0;
        ArrayList<Aspect> validaspects = new ArrayList<Aspect>();
        for (Aspect prim : nodeAspects.getAspects()) {
            if (filter.getAmount(prim) <= 0 || nodeAspects.getAmount(prim) <= min) continue;
            validaspects.add(prim);
        }
        if (validaspects.size() == 0) {
            return null;
        }
        Aspect asp = (Aspect)validaspects.get(world.field_73012_v.nextInt(validaspects.size()));
        if (asp != null && nodeAspects.getAmount(asp) > min) {
            return asp;
        }
        return null;
    }

    @Override
    public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) {
        boolean mfu = false;
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        MovingObjectPosition movingobjectposition = BlockUtils.getTargetBlock((World)player.field_70170_p, (Entity)player, (boolean)true);
        int i = 0;
        int j = 0;
        int k = 0;
        AspectList nodeAsp = new AspectList();
        INode node = null;
        int color = 0;
        if (movingobjectposition == null || movingobjectposition.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            player.func_71034_by();
        } else {
            i = movingobjectposition.field_72311_b;
            j = movingobjectposition.field_72312_c;
            k = movingobjectposition.field_72309_d;
            if (!(player.field_70170_p.func_147438_o(i, j, k) instanceof INode)) {
                player.func_71034_by();
                return;
            }
            node = (INode)player.field_70170_p.func_147438_o(i, j, k);
            nodeAsp = node.getAspects();
        }
        if (count % 5 == 0) {
            int tap = 1;
            if (ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"NODETAPPER1")) {
                ++tap;
            }
            if (ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"NODETAPPER2")) {
                ++tap;
            }
            boolean preserve = !player.func_70093_af() && ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"NODEPRESERVE") && !wand.getRod(wandstack).getTag().equals("wood") && !wand.getCap(wandstack).getTag().equals("iron");
            boolean success = false;
            Aspect aspect = null;
            aspect = this.chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve, nodeAsp, player.field_70170_p);
            if (aspect != null) {
                int rem;
                int amt = nodeAsp.getAmount(aspect);
                if (tap > amt) {
                    tap = amt;
                }
                if (preserve && tap == amt) {
                    --tap;
                }
                if (tap > 0 && (rem = wand.addVis(wandstack, aspect, tap, !player.field_70170_p.field_72995_K)) < tap) {
                    color = aspect.getColor();
                    if (!player.field_70170_p.field_72995_K) {
                        node.takeFromContainer(aspect, tap - rem);
                        mfu = true;
                    }
                    success = true;
                }
            }
            if (success) {
                Color col = new Color(color);
                Thaumcraft.proxy.beamPower(player.field_70170_p, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5, player.field_70165_t, player.field_70163_u + (double)player.eyeHeight, player.field_70161_v, (float)col.getRed() / 255.0f, (float)col.getGreen() / 255.0f, (float)col.getBlue() / 255.0f, true, (Object)node);
            }
            if (mfu) {
                player.field_70170_p.func_147471_g(i, j, k);
                ((TileEntity)node).func_70296_d();
            }
        }
    }
}

