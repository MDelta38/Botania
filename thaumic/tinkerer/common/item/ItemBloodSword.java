/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  thaumcraft.api.IRepairable
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.core.helper.EnumMobAspect;
import thaumic.tinkerer.common.item.ItemMobAspect;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemBloodSword
extends ItemSword
implements IRepairable,
ITTinkererItem {
    private static final int DAMAGE = 10;
    static int handleNext = 0;
    private IIcon activeIcon;

    public ItemBloodSword() {
        super(EnumHelper.addToolMaterial((String)"TT_BLOOD", (int)0, (int)950, (float)0.0f, (float)0.0f, (int)ThaumcraftApi.toolMatThaumium.func_77995_e()));
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.func_77637_a(ModCreativeTab.INSTANCE);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return super.func_77661_b(par1ItemStack);
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return stack.field_77990_d != null && stack.field_77990_d.func_74762_e("Activated") == 1 ? this.activeIcon : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.activeIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public Multimap func_111205_h() {
        HashMultimap multimap = HashMultimap.create();
        multimap.put((Object)SharedMonsterAttributes.field_111264_e.func_111108_a(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", 10.0, 0));
        multimap.put((Object)SharedMonsterAttributes.field_111263_d.func_111108_a(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", 0.25, 1));
        return multimap;
    }

    public void addDrops(LivingDropsEvent event, ItemStack dropStack) {
        EntityItem entityitem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, dropStack);
        entityitem.field_145804_b = 10;
        event.drops.add(entityitem);
    }

    @SubscribeEvent
    public void onDrops(LivingDropsEvent event) {
        Aspect[] aspects;
        EntityPlayer player;
        ItemStack stack;
        if (event.source.field_76373_n.equals("player") && (stack = (player = (EntityPlayer)event.source.func_76346_g()).func_71045_bC()) != null && stack.func_77973_b() == this && stack.field_77990_d != null && stack.field_77990_d.func_74762_e("Activated") == 1 && (aspects = EnumMobAspect.getAspectsForEntity(event.entity)) != null) {
            event.drops.removeAll(event.drops);
            for (Aspect a : aspects) {
                this.addDrops(event, ItemMobAspect.getStackFromAspect(a));
            }
        }
    }

    @SubscribeEvent
    public void onDamageTaken(LivingAttackEvent event) {
        EntityLivingBase attacker;
        ItemStack itemInUse;
        Entity source;
        boolean handle;
        if (event.entity.field_70170_p.field_72995_K) {
            return;
        }
        boolean bl = handle = handleNext == 0;
        if (!handle) {
            --handleNext;
        }
        if (event.entityLiving instanceof EntityPlayer && handle) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack itemInUse2 = player.field_71074_e;
            if (itemInUse2 != null && itemInUse2.func_77973_b() == this) {
                event.setCanceled(true);
                handleNext = 3;
                player.func_70097_a(DamageSource.field_76376_m, 3.0f);
            }
        }
        if (handle && (source = event.source.func_76364_f()) != null && source instanceof EntityLivingBase && (itemInUse = (attacker = (EntityLivingBase)source).func_70694_bm()) != null && itemInUse.func_77973_b() == this) {
            attacker.func_70097_a(DamageSource.field_76376_m, 2.0f);
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer) {
        ItemStack cache = super.func_77659_a(stack, par2World, par3EntityPlayer);
        if (par3EntityPlayer.func_70093_af() && !par2World.field_72995_K) {
            if (stack.field_77990_d == null) {
                stack.field_77990_d = new NBTTagCompound();
            }
            if (stack.field_77990_d.func_74762_e("Activated") == 0) {
                par3EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.bloodSword.activateEssentiaHarvest", new Object[0]));
                stack.field_77990_d.func_74768_a("Activated", 1);
            } else {
                par3EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.bloodSword.deactivateEssentiaHarvest", new Object[0]));
                stack.field_77990_d.func_74768_a("Activated", 0);
            }
        }
        return cache;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "bloodSword";
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
        return (TTResearchItem)new TTResearchItem("BLOOD_SWORD", new AspectList().add(Aspect.HUNGER, 2).add(Aspect.WEAPON, 1).add(Aspect.FLESH, 1).add(Aspect.SOUL, 1), -4, 6, 3, new ItemStack((Item)this), new ResearchPage[0]).setWarp(1).setParents(new String[]{"CLEANSING_TALISMAN"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("BLOOD_SWORD"), new ResearchPage("1")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("BLOOD_SWORD", new ItemStack((Item)this), 6, new AspectList().add(Aspect.HUNGER, 20).add(Aspect.DARKNESS, 5).add(Aspect.SOUL, 10).add(Aspect.MAN, 6), new ItemStack(ConfigItems.itemSwordThaumium), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151073_bk));
    }
}

