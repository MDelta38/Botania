/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemXPTalisman
extends ItemBase
implements IBauble {
    private static final String TAG_XP = "xp";
    IIcon enabledIcon;

    public ItemXPTalisman() {
        this.func_77625_d(1);
    }

    public static boolean hasCmp(ItemStack stack) {
        return ItemNBTHelper.detectNBT(stack);
    }

    public static int getXP(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_XP, 0);
    }

    public static void setXP(ItemStack stack, int xp) {
        ItemNBTHelper.setInt(stack, TAG_XP, xp);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        boolean has;
        if (par3EntityPlayer.func_70093_af()) {
            if (ItemXPTalisman.getXP(par1ItemStack) < 1500) {
                int dmg = par1ItemStack.func_77960_j();
                par1ItemStack.func_77964_b(~dmg & 1);
                par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
            }
        } else if (ItemXPTalisman.getXP(par1ItemStack) >= 10 && (has = par3EntityPlayer.field_71071_by.func_146026_a(Items.field_151069_bo))) {
            if (!par3EntityPlayer.field_71071_by.func_70441_a(new ItemStack(Items.field_151062_by, 1)) && !par2World.field_72995_K) {
                par3EntityPlayer.func_145779_a(Items.field_151062_by, 1);
            }
            int xp = ItemXPTalisman.getXP(par1ItemStack);
            ItemXPTalisman.setXP(par1ItemStack, xp - 10);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.1f, (float)((double)0.1f + Math.random() / 2.0));
            for (int i = 0; par2World.field_72995_K && i < 6; ++i) {
                ThaumicTinkerer.tcProxy.sparkle((float)(par3EntityPlayer.field_70165_t + (Math.random() - 0.5)), (float)(par3EntityPlayer.field_70163_u + Math.random() - 0.5), (float)(par3EntityPlayer.field_70161_v + (Math.random() - 0.5)), 3);
            }
        }
        return par1ItemStack;
    }

    private void consumeXPOrb(EntityXPOrb orb) {
        orb.func_70106_y();
        orb.field_70170_p.func_72956_a((Entity)orb, "thaumcraft:zap", (float)orb.func_70526_d() / 10.0f, 1.0f);
        ThaumicTinkerer.tcProxy.wispFX(orb.field_70170_p, orb.field_70165_t, orb.field_70163_u, orb.field_70161_v, (float)orb.func_70526_d() / 5.0f, 0.1f, 0.9f, 0.1f);
    }

    public boolean func_77651_p() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("XP: " + ItemXPTalisman.getXP(par1ItemStack));
        if (ItemXPTalisman.getXP(par1ItemStack) >= 1500) {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.full"));
        } else if (par1ItemStack.func_77960_j() == 0) {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.notAbsorbing"));
        } else {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.absorbing"));
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.enabledIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (TTResearchItem)new TTResearchItem("XP_TALISMAN", new AspectList().add(Aspect.GREED, 1).add(Aspect.MAGIC, 1).add(Aspect.MAN, 1), 4, -1, 2, new ItemStack((Item)this, 1, 1), new ResearchPage[0]).setParents(new String[]{"JARBRAIN", "SPELL_CLOTH"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("XP_TALISMAN")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("XP_TALISMAN", new ItemStack((Item)this), 6, new AspectList().add(Aspect.GREED, 20).add(Aspect.EXCHANGE, 10).add(Aspect.BEAST, 10).add(Aspect.MECHANISM, 5), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.field_151045_i));
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 == 1 ? this.enabledIcon : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack par1ItemStack, EntityLivingBase player) {
        World par2World = player.field_70170_p;
        if (par1ItemStack.func_77960_j() == 1 && !par2World.field_72995_K) {
            int r = 3;
            int currentXP = ItemXPTalisman.getXP(par1ItemStack);
            int xpToAdd = 0;
            int maxXP = 1500 - currentXP;
            if (maxXP <= 0) {
                par1ItemStack.func_77964_b(0);
                return;
            }
            AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)r), (double)(player.field_70163_u - (double)r), (double)(player.field_70161_v - (double)r), (double)(player.field_70165_t + (double)r), (double)(player.field_70163_u + (double)r), (double)(player.field_70161_v + (double)r));
            List orbs = par2World.func_72872_a(EntityXPOrb.class, boundingBox);
            for (EntityXPOrb orb : orbs) {
                if (orb.field_70128_L) continue;
                int xp = orb.func_70526_d();
                if (xpToAdd + xp <= maxXP) {
                    xpToAdd += xp;
                    this.consumeXPOrb(orb);
                }
                if ((maxXP -= xpToAdd) > 0) continue;
                break;
            }
            ItemXPTalisman.setXP(par1ItemStack, Math.min(1500, currentXP + xpToAdd));
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
        return "xpTalisman";
    }
}

