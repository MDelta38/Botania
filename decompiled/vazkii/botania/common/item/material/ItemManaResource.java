/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package vazkii.botania.common.item.material;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.IPickupAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityEnderAirBottle;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.lib.LibItemNames;

public class ItemManaResource
extends ItemMod
implements IFlowerComponent,
IElvenItem,
IPickupAchievement {
    final int types = 24;
    IIcon[] icons;
    public IIcon tailIcon = null;
    public IIcon phiFlowerIcon = null;
    public IIcon goldfishIcon = null;
    public IIcon nerfBatIcon = null;

    public ItemManaResource() {
        this.func_77655_b("manaResource");
        this.func_77627_a(true);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        MovingObjectPosition pos;
        boolean ender;
        boolean rightEvent = event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR;
        ItemStack stack = event.entityPlayer.func_71045_bC();
        boolean correctStack = stack != null && stack.func_77973_b() == Items.field_151069_bo;
        boolean bl = ender = event.world.field_73011_w.field_76574_g == 1;
        if (rightEvent && correctStack && ender && (pos = ToolCommons.raytraceFromEntity(event.world, (Entity)event.entityPlayer, false, 5.0)) == null) {
            ItemStack stack1 = new ItemStack((Item)this, 1, 15);
            event.entityPlayer.func_71064_a((StatBase)ModAchievements.enderAirMake, 1);
            if (!event.entityPlayer.field_71071_by.func_70441_a(stack1)) {
                event.entityPlayer.func_71019_a(stack1, true);
            }
            --stack.field_77994_a;
            if (stack.field_77994_a == 0) {
                event.entityPlayer.field_71071_by.func_70299_a(event.entityPlayer.field_71071_by.field_70461_c, null);
            }
            if (event.world.field_72995_K) {
                event.entityPlayer.func_71038_i();
            } else {
                event.world.func_72956_a((Entity)event.entityPlayer, "random.pop", 0.5f, 1.0f);
            }
        }
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par1ItemStack.func_77960_j() == 4 || par1ItemStack.func_77960_j() == 14) {
            return EntityDoppleganger.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, par1ItemStack.func_77960_j() == 14);
        }
        if (par1ItemStack.func_77960_j() == 20 && ItemDye.applyBonemeal((ItemStack)par1ItemStack, (World)par3World, (int)par4, (int)par5, (int)par6, (EntityPlayer)par2EntityPlayer)) {
            if (!par3World.field_72995_K) {
                par3World.func_72926_e(2005, par4, par5, par6, 0);
            }
            return true;
        }
        return super.func_77648_a(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par3World, EntityPlayer par2EntityPlayer) {
        if (par1ItemStack.func_77960_j() == 15) {
            if (!par2EntityPlayer.field_71075_bZ.field_75098_d) {
                --par1ItemStack.field_77994_a;
            }
            par3World.func_72956_a((Entity)par2EntityPlayer, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
            if (!par3World.field_72995_K) {
                par3World.func_72838_d((Entity)new EntityEnderAirBottle(par3World, (EntityLivingBase)par2EntityPlayer));
            } else {
                par2EntityPlayer.func_71038_i();
            }
        }
        return par1ItemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 24; ++i) {
            if (!Botania.gardenOfGlassLoaded && (i == 20 || i == 21)) continue;
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[24];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forName(par1IconRegister, LibItemNames.MANA_RESOURCE_NAMES[i]);
        }
        this.tailIcon = IconHelper.forName(par1IconRegister, "tail");
        this.phiFlowerIcon = IconHelper.forName(par1IconRegister, "phiFlower");
        this.goldfishIcon = IconHelper.forName(par1IconRegister, "goldfish");
        this.nerfBatIcon = IconHelper.forName(par1IconRegister, "nerfBat");
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.func_77960_j() == 5 || par1ItemStack.func_77960_j() == 14) {
            return Color.HSBtoRGB((float)(Botania.proxy.getWorldElapsedTicks() * 2L % 360L) / 360.0f, 0.25f, 1.0f);
        }
        return 0xFFFFFF;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item." + LibItemNames.MANA_RESOURCE_NAMES[Math.min(23, par1ItemStack.func_77960_j())];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    @Override
    public boolean canFit(ItemStack stack, IInventory apothecary) {
        int meta = stack.func_77960_j();
        return meta == 6 || meta == 8 || meta == 5 || meta == 23;
    }

    @Override
    public int getParticleColor(ItemStack stack) {
        return 0x9B0000;
    }

    @Override
    public boolean isElvenItem(ItemStack stack) {
        int meta = stack.func_77960_j();
        return meta == 7 || meta == 8 || meta == 9;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return itemStack.func_77960_j() == 11 ? itemStack.func_77946_l() : null;
    }

    @Override
    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return stack.func_77960_j() == 4 ? ModAchievements.terrasteelPickup : null;
    }
}

