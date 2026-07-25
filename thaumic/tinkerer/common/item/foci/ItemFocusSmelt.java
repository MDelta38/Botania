/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.utils.BlockUtils
 */
package thaumic.tinkerer.common.item.foci;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusSmelt
extends ItemModFocus {
    private static final AspectList visUsage = new AspectList().add(Aspect.FIRE, 45).add(Aspect.ENTROPY, 12);
    public static Map<String, SmeltData> playerData = new HashMap<String, SmeltData>();
    private int soundCooldown = 3;

    @Override
    public String getItemName() {
        return "focusSmelt";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (TTResearchItem)new TTResearchItem("FOCUS_SMELT", new AspectList().add(Aspect.FIRE, 2).add(Aspect.ENERGY, 1).add(Aspect.MAGIC, 1), -2, -2, 2, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"FOCUSEXCAVATION"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("FOCUS_SMELT")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("FOCUS_SMELT", "FOCUS_SMELT", new ItemStack((Item)this), new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 6), "FNE", Character.valueOf('F'), new ItemStack(ConfigItems.itemFocusFire), Character.valueOf('E'), new ItemStack(ConfigItems.itemFocusExcavation), Character.valueOf('N'), new ItemStack(ConfigItems.itemResource, 1, 1));
    }

    @Override
    public boolean isUseItem(ItemStack stack) {
        return true;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int time) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (!wand.consumeAllVis(stack, p, visUsage, false, false)) {
            return;
        }
        MovingObjectPosition pos = BlockUtils.getTargetBlock((World)p.field_70170_p, (Entity)p, (boolean)false);
        if (pos != null) {
            Block block = p.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            int meta = p.field_70170_p.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            ItemStack blockStack = new ItemStack(block, 1, meta);
            ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(blockStack);
            if (result != null && result.func_77973_b() instanceof ItemBlock) {
                SmeltData data;
                boolean decremented = false;
                if (playerData.containsKey(p.func_146103_bH().getName()) && (data = playerData.get(p.func_146103_bH().getName())).equalPos(pos)) {
                    --data.progress;
                    decremented = true;
                    if (data.progress <= 0) {
                        if (!p.field_70170_p.field_72995_K) {
                            p.field_70170_p.func_147465_d(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d, Block.func_149634_a((Item)result.func_77973_b()), result.func_77960_j(), 3);
                            wand.consumeAllVis(stack, p, visUsage, true, false);
                            playerData.remove(p.func_146103_bH().getName());
                            decremented = false;
                        }
                        p.field_70170_p.func_72956_a((Entity)p, "fire.ignite", 0.6f, 1.0f);
                        p.field_70170_p.func_72956_a((Entity)p, "fire.fire", 1.0f, 1.0f);
                        for (int i = 0; i < 25; ++i) {
                            double x = (double)pos.field_72311_b + Math.random();
                            double y = (double)pos.field_72312_c + Math.random();
                            double z = (double)pos.field_72309_d + Math.random();
                            ThaumicTinkerer.tcProxy.wispFX2(p.field_70170_p, x, y, z, (float)Math.random() / 2.0f, 4, true, true, (float)(-Math.random()) / 10.0f);
                        }
                    }
                }
                if (!decremented) {
                    int potency = this.getUpgradeLevel(stack, FocusUpgradeType.potency);
                    playerData.put(p.func_146103_bH().getName(), new SmeltData(pos, 20 - Math.min(3, potency) * 5));
                } else {
                    if (time % this.soundCooldown == 0) {
                        p.field_70170_p.func_72956_a((Entity)p, "fire.fire", 0.2f, 1.0f);
                    }
                    for (int i = 0; i < 2; ++i) {
                        double x = (double)pos.field_72311_b + Math.random();
                        double y = (double)pos.field_72312_c + Math.random();
                        double z = (double)pos.field_72309_d + Math.random();
                        ThaumicTinkerer.tcProxy.wispFX2(p.field_70170_p, x, y, z, (float)Math.random() / 2.0f, 4, true, true, (float)(-Math.random()) / 10.0f);
                    }
                }
                if (p.field_70170_p.field_72995_K) {
                    ThaumicTinkerer.tcProxy.beamCont(p.field_70170_p, p, (double)pos.field_72311_b + 0.5, (double)pos.field_72312_c + 0.5, (double)pos.field_72309_d + 0.5, 2, 0xFF0000, true, 0.0f, null, 1);
                }
            }
        }
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "SMELT";
    }

    @Override
    protected boolean hasOrnament() {
        return true;
    }

    public int getFocusColor(ItemStack stack) {
        return 0xFF0000;
    }

    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[]{FocusUpgradeType.treasure, FocusUpgradeType.potency};
    }

    static class SmeltData {
        public MovingObjectPosition pos;
        public int progress;

        public SmeltData(MovingObjectPosition pos, int progress) {
            this.pos = pos;
            this.progress = progress;
        }

        public boolean equalPos(MovingObjectPosition pos) {
            return pos.field_72311_b == this.pos.field_72311_b && pos.field_72312_c == this.pos.field_72312_c && pos.field_72309_d == this.pos.field_72309_d;
        }
    }
}

