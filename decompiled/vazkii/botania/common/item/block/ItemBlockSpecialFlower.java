/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IRecipeKeyProvider;
import vazkii.botania.api.subtile.signature.SubTileSignature;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.block.ItemBlockMod;

public class ItemBlockSpecialFlower
extends ItemBlockMod
implements IRecipeKeyProvider {
    public ItemBlockSpecialFlower(Block block1) {
        super(block1);
    }

    public IIcon func_77650_f(ItemStack stack) {
        return BotaniaAPI.getSignatureForName(ItemBlockSpecialFlower.getType(stack)).getIconForStack(stack);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (placed) {
            String type = ItemBlockSpecialFlower.getType(stack);
            TileEntity te = world.func_147438_o(x, y, z);
            if (te instanceof TileSpecialFlower) {
                TileSpecialFlower tile = (TileSpecialFlower)te;
                tile.setSubTile(type);
                tile.onBlockAdded(world, x, y, z);
                tile.onBlockPlacedBy(world, x, y, z, (EntityLivingBase)player, stack);
                if (!world.field_72995_K) {
                    world.func_147471_g(x, y, z);
                }
            }
        }
        return placed;
    }

    public String func_77667_c(ItemStack stack) {
        return BotaniaAPI.getSignatureForName(ItemBlockSpecialFlower.getType(stack)).getUnlocalizedNameForStack(stack);
    }

    @Override
    public String func_77657_g(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameInefficiently_(par1ItemStack);
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        String mod;
        String refUnlocalized;
        String refLocalized;
        String type = ItemBlockSpecialFlower.getType(par1ItemStack);
        SubTileSignature sig = BotaniaAPI.getSignatureForName(type);
        sig.addTooltip(par1ItemStack, par2EntityPlayer, par3List);
        if (ConfigHandler.referencesEnabled && !(refLocalized = StatCollector.func_74838_a((String)(refUnlocalized = sig.getUnlocalizedLoreTextForStack(par1ItemStack)))).equals(refUnlocalized)) {
            par3List.add(EnumChatFormatting.ITALIC + refLocalized);
        }
        if (!(mod = BotaniaAPI.subTileMods.get(type)).equals("Botania")) {
            par3List.add(EnumChatFormatting.ITALIC + "[" + mod + "]");
        }
    }

    public static String getType(ItemStack stack) {
        return ItemNBTHelper.detectNBT(stack) ? ItemNBTHelper.getString(stack, "type", "") : "";
    }

    public static ItemStack ofType(String type) {
        return ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.specialFlower), type);
    }

    public static ItemStack ofType(ItemStack stack, String type) {
        ItemNBTHelper.setString(stack, "type", type);
        return stack;
    }

    @Override
    public String getKey(ItemStack stack) {
        return "flower." + ItemBlockSpecialFlower.getType(stack);
    }

    @Override
    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        String type = ItemBlockSpecialFlower.getType(stack);
        if (type.equals("daybloom")) {
            return ModAchievements.daybloomPickup;
        }
        if (type.equals("endoflame")) {
            return ModAchievements.endoflamePickup;
        }
        if (type.equals("kekimurus")) {
            return ModAchievements.kekimurusPickup;
        }
        if (type.equals("heiseiDream")) {
            return ModAchievements.heiseiDreamPickup;
        }
        if (type.equals("pollidisiac")) {
            return ModAchievements.pollidisiacPickup;
        }
        if (type.equals("bubbell")) {
            return ModAchievements.bubbellPickup;
        }
        if (type.equals("dandelifeon")) {
            return ModAchievements.dandelifeonPickup;
        }
        if (type.equals("")) {
            return ModAchievements.nullFlower;
        }
        return null;
    }
}

