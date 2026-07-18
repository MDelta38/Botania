/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool.manasteel;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemManasteelSword
extends ItemSword
implements IManaUsingItem {
    public static final int MANA_PER_DAMAGE = 60;
    public static IIcon elucidatorIcon;

    public ItemManasteelSword() {
        this(BotaniaAPI.manasteelToolMaterial, "manasteelSword");
    }

    public ItemManasteelSword(Item.ToolMaterial mat, String name) {
        super(mat);
        this.func_77637_a(BotaniaCreativeTab.INSTANCE);
        this.func_77655_b(name);
    }

    public Item func_77655_b(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.func_77655_b(par1Str);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack).replaceAll("item.", "item.botania:");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
        elucidatorIcon = IconHelper.forName(par1IconRegister, "elucidator");
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        if (this.usesMana(par1ItemStack)) {
            ToolCommons.damageItem(par1ItemStack, 1, par3EntityLivingBase, this.getManaPerDamage());
        }
        return true;
    }

    public IIcon func_77650_f(ItemStack par1ItemStack) {
        String name = par1ItemStack.func_82833_r().toLowerCase().trim();
        return name.equals("the elucidator") ? elucidatorIcon : super.func_77650_f(par1ItemStack);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        if (this.usesMana(stack) && block.func_149712_f(world, x, y, z) != 0.0f) {
            ToolCommons.damageItem(stack, 1, entity, this.getManaPerDamage());
        }
        return true;
    }

    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (!world.field_72995_K && player instanceof EntityPlayer && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer)player, this.getManaPerDamage() * 2, true)) {
            stack.func_77964_b(stack.func_77960_j() - 1);
        }
    }

    public int getManaPerDamage() {
        return 60;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 0 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

