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
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool.manasteel;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.ISortableTool;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemManasteelAxe
extends ItemAxe
implements IManaUsingItem,
ISortableTool {
    private static final Pattern SAPLING_PATTERN = Pattern.compile("(?:(?:(?:[A-Z-_.:]|^)sapling)|(?:(?:[a-z-_.:]|^)Sapling))(?:[A-Z-_.:]|$)");
    private static final int MANA_PER_DAMAGE = 60;

    public ItemManasteelAxe() {
        this(BotaniaAPI.manasteelToolMaterial, "manasteelAxe");
    }

    public ItemManasteelAxe(Item.ToolMaterial mat, String name) {
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
        ToolCommons.damageItem(par1ItemStack, 1, par3EntityLivingBase, this.getManaPerDamage());
        return true;
    }

    public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        if (block.func_149712_f(world, x, y, z) != 0.0f) {
            ToolCommons.damageItem(stack, 1, entity, this.getManaPerDamage());
        }
        return true;
    }

    public int getManaPerDamage() {
        return 60;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float sx, float sy, float sz) {
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stackAt = player.field_71071_by.func_70301_a(i);
            if (stackAt == null || !SAPLING_PATTERN.matcher(stackAt.func_77973_b().func_77658_a()).find()) continue;
            boolean did = stackAt.func_77973_b().func_77648_a(stackAt, player, world, x, y, z, s, sx, sy, sz);
            if (stackAt.field_77994_a == 0) {
                player.field_71071_by.func_70299_a(i, null);
            }
            ItemsRemainingRenderHandler.set(player, new ItemStack(Blocks.field_150345_g), SAPLING_PATTERN);
            return did;
        }
        return false;
    }

    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (!world.field_72995_K && player instanceof EntityPlayer && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer)player, this.getManaPerDamage() * 2, true)) {
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
        return ISortableTool.ToolType.AXE;
    }

    @Override
    public int getSortingPriority(ItemStack stack) {
        return ToolCommons.getToolPriority(stack);
    }
}

