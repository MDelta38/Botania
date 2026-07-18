/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.UseHoeEvent
 */
package vazkii.botania.common.item.equipment.tool.manasteel;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.ISortableTool;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemManasteelShovel
extends ItemSpade
implements IManaUsingItem,
ISortableTool {
    private static final int MANA_PER_DAMAGE = 60;

    public ItemManasteelShovel() {
        this(BotaniaAPI.manasteelToolMaterial, "manasteelShovel");
    }

    public ItemManasteelShovel(Item.ToolMaterial mat, String name) {
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
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        ToolCommons.damageItem(par1ItemStack, 1, par3EntityLivingBase, 60);
        return true;
    }

    public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        if (block.func_149712_f(world, x, y, z) != 0.0f) {
            ToolCommons.damageItem(stack, 1, entity, 60);
        }
        return true;
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_)) {
            return false;
        }
        UseHoeEvent event = new UseHoeEvent(p_77648_2_, p_77648_1_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return false;
        }
        if (event.getResult() == Event.Result.ALLOW) {
            ToolCommons.damageItem(p_77648_1_, 1, (EntityLivingBase)p_77648_2_, 60);
            return true;
        }
        Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
        if (p_77648_7_ != 0 && p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_ + 1, p_77648_6_).isAir((IBlockAccess)p_77648_3_, p_77648_4_, p_77648_5_ + 1, p_77648_6_) && (block == Blocks.field_150349_c || block == Blocks.field_150346_d)) {
            Block block1 = Blocks.field_150458_ak;
            p_77648_3_.func_72908_a((double)((float)p_77648_4_ + 0.5f), (double)((float)p_77648_5_ + 0.5f), (double)((float)p_77648_6_ + 0.5f), block1.field_149762_H.func_150498_e(), (block1.field_149762_H.func_150497_c() + 1.0f) / 2.0f, block1.field_149762_H.func_150494_d() * 0.8f);
            if (p_77648_3_.field_72995_K) {
                return true;
            }
            p_77648_3_.func_147449_b(p_77648_4_, p_77648_5_, p_77648_6_, block1);
            ToolCommons.damageItem(p_77648_1_, 1, (EntityLivingBase)p_77648_2_, 60);
            return true;
        }
        return false;
    }

    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (!world.field_72995_K && player instanceof EntityPlayer && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer)player, 120, true)) {
            stack.func_77964_b(stack.func_77960_j() - 1);
        }
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 0 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public ISortableTool.ToolType getSortingType(ItemStack stack) {
        return ISortableTool.ToolType.SHOVEL;
    }

    @Override
    public int getSortingPriority(ItemStack stack) {
        return ToolCommons.getToolPriority(stack);
    }
}

