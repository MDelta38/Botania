/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item.kami;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ItemKamiBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemCatAmulet
extends ItemKamiBase
implements IBauble {
    public ItemCatAmulet() {
        this.func_77625_d(1);
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity e, int par4, boolean par5) {
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    private boolean messWithRunAwayAI(EntityAIAvoidEntity aiEntry) {
        if (aiEntry.field_75381_h == EntityOcelot.class) {
            aiEntry.field_75381_h = EntityPlayer.class;
            return true;
        }
        return false;
    }

    private void messWithGetTargetAI(EntityAINearestAttackableTarget aiEntry) {
        if (aiEntry.field_75307_b == EntityPlayer.class) {
            aiEntry.field_75307_b = EntityEnderCrystal.class;
        }
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        int range = 10;
        int rangeY = 4;
        List entities = player.field_70170_p.func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)range), (double)(player.field_70163_u - (double)rangeY), (double)(player.field_70161_v - (double)range), (double)(player.field_70165_t + (double)range), (double)(player.field_70163_u + (double)rangeY), (double)(player.field_70161_v + (double)range)));
        for (EntityLiving entity : entities) {
            ArrayList entries = new ArrayList(entity.field_70714_bg.field_75782_a);
            entries.addAll(new ArrayList(entity.field_70715_bh.field_75782_a));
            boolean avoidsOcelots = false;
            for (EntityAITasks.EntityAITaskEntry entry : entries) {
                if (entry.field_75733_a instanceof EntityAIAvoidEntity) {
                    boolean bl = avoidsOcelots = this.messWithRunAwayAI((EntityAIAvoidEntity)entry.field_75733_a) || avoidsOcelots;
                }
                if (!(entry.field_75733_a instanceof EntityAINearestAttackableTarget)) continue;
                this.messWithGetTargetAI((EntityAINearestAttackableTarget)entry.field_75733_a);
            }
            if (!(entity instanceof EntityCreeper)) continue;
            ((EntityCreeper)entity).field_70833_d = 2;
            entity.func_70624_b(null);
        }
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public String getItemName() {
        return "catAmulet";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("CAT_AMULET", new AspectList().add(Aspect.MIND, 2).add(Aspect.ORDER, 1).add(Aspect.DARKNESS, 1).add(Aspect.DEATH, 1), 13, 10, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHORIUM"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("CAT_AMULET")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("CAT_AMULET", new ItemStack((Item)this), 8, new AspectList().add(Aspect.DARKNESS, 16).add(Aspect.ORDER, 32).add(Aspect.MIND, 16), new ItemStack(Blocks.field_150371_ca), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 3), new ItemStack((Block)Blocks.field_150362_t, 1, 3), new ItemStack(Items.field_151115_aP));
    }
}

