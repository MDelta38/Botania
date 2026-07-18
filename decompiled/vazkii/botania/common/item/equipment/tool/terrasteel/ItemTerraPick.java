/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item.equipment.tool.terrasteel;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.TerraPickTippingRecipe;
import vazkii.botania.common.item.ItemTemperanceStone;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;
import vazkii.botania.common.item.relic.ItemLokiRing;
import vazkii.botania.common.item.relic.ItemThorRing;

public class ItemTerraPick
extends ItemManasteelPick
implements IManaItem,
ISequentialBreaker {
    private static final String TAG_ENABLED = "enabled";
    private static final String TAG_MANA = "mana";
    private static final String TAG_TIPPED = "tipped";
    private static final int MAX_MANA = Integer.MAX_VALUE;
    private static final int MANA_PER_DAMAGE = 100;
    private static final Material[] MATERIALS = new Material[]{Material.field_151576_e, Material.field_151573_f, Material.field_151588_w, Material.field_151592_s, Material.field_76233_E, Material.field_151574_g, Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z, Material.field_151571_B};
    public static final int[] LEVELS = new int[]{0, 10000, 1000000, 10000000, 100000000, 1000000000};
    private static final int[] CREATIVE_MANA = new int[]{9999, 999999, 9999999, 99999999, 999999999, 0x7FFFFFFE};
    IIcon iconTool;
    IIcon iconOverlay;
    IIcon iconTipped;

    public ItemTerraPick() {
        super(BotaniaAPI.terrasteelToolMaterial, "terraPick");
        GameRegistry.addRecipe((IRecipe)new TerraPickTippingRecipe());
        RecipeSorter.register((String)"botania:terraPickTipping", TerraPickTippingRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int mana : CREATIVE_MANA) {
            ItemStack stack = new ItemStack(item);
            ItemTerraPick.setMana(stack, mana);
            list.add(stack);
        }
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        String rankFormat = StatCollector.func_74838_a((String)"botaniamisc.toolRank");
        String rank = StatCollector.func_74838_a((String)("botania.rank" + ItemTerraPick.getLevel(par1ItemStack)));
        par3List.add(String.format(rankFormat, rank).replaceAll("&", "\u00a7"));
        if (this.getMana(par1ItemStack) == Integer.MAX_VALUE) {
            par3List.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"botaniamisc.getALife"));
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        this.getMana(par1ItemStack);
        int level = ItemTerraPick.getLevel(par1ItemStack);
        if (level != 0) {
            this.setEnabled(par1ItemStack, !this.isEnabled(par1ItemStack));
            if (!par2World.field_72995_K) {
                par2World.func_72956_a((Entity)par3EntityPlayer, "botania:terraPickMode", 0.5f, 0.4f);
            }
        }
        return par1ItemStack;
    }

    @Override
    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float sx, float sy, float sz) {
        return player.func_70093_af() && super.func_77648_a(stack, player, world, x, y, z, s, sx, sy, sz);
    }

    @Override
    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        super.func_77663_a(par1ItemStack, par2World, par3Entity, par4, par5);
        if (this.isEnabled(par1ItemStack)) {
            int level = ItemTerraPick.getLevel(par1ItemStack);
            if (level == 0) {
                this.setEnabled(par1ItemStack, false);
            } else if (par3Entity instanceof EntityPlayer && !((EntityPlayer)par3Entity).field_82175_bq) {
                this.addMana(par1ItemStack, -level);
            }
        }
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        MovingObjectPosition raycast = ToolCommons.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0);
        if (raycast != null) {
            this.breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.field_72310_e);
            ItemLokiRing.breakOnAllCursors(player, (Item)this, stack, x, y, z, raycast.field_72310_e);
        }
        return false;
    }

    @Override
    public int getManaPerDmg() {
        return 100;
    }

    @Override
    public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {
        if (!this.isEnabled(stack)) {
            return;
        }
        World world = player.field_70170_p;
        Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (!ToolCommons.isRightMaterial(mat, MATERIALS)) {
            return;
        }
        if (world.func_147437_c(x, y, z)) {
            return;
        }
        ForgeDirection direction = ForgeDirection.getOrientation((int)side);
        int fortune = EnchantmentHelper.func_77517_e((EntityLivingBase)player);
        boolean silk = EnchantmentHelper.func_77502_d((EntityLivingBase)player);
        boolean thor = ItemThorRing.getThorRing(player) != null;
        boolean doX = thor || direction.offsetX == 0;
        boolean doY = thor || direction.offsetY == 0;
        boolean doZ = thor || direction.offsetZ == 0;
        int origLevel = ItemTerraPick.getLevel(stack);
        int level = origLevel + (thor ? 1 : 0);
        if (ItemTemperanceStone.hasTemperanceActive(player) && level > 2) {
            level = 2;
        }
        int range = Math.max(0, level - 1);
        int rangeY = Math.max(1, range);
        if (range == 0 && level != 1) {
            return;
        }
        ToolCommons.removeBlocksInIteration(player, stack, world, x, y, z, doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0, doX ? range + 1 : 1, doY ? rangeY * 2 : 1, doZ ? range + 1 : 1, null, MATERIALS, silk, fortune, ItemTerraPick.isTipped(stack));
        if (origLevel == 5) {
            player.func_71064_a((StatBase)ModAchievements.rankSSPick, 1);
        }
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.iconTool = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconOverlay = IconHelper.forItem(par1IconRegister, (Item)this, 1);
        this.iconTipped = IconHelper.forItem(par1IconRegister, (Item)this, 2);
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return pass == 1 && this.isEnabled(stack) ? this.iconOverlay : (ItemTerraPick.isTipped(stack) ? this.iconTipped : this.iconTool);
    }

    public static boolean isTipped(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_TIPPED, false);
    }

    public static void setTipped(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, TAG_TIPPED, true);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par2 == 0 || !this.isEnabled(par1ItemStack)) {
            return 0xFFFFFF;
        }
        return Color.HSBtoRGB(0.375f, (float)Math.min(1.0, Math.sin((double)System.currentTimeMillis() / 200.0) * 0.5 + 1.0), 1.0f);
    }

    boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_ENABLED, false);
    }

    void setEnabled(ItemStack stack, boolean enabled) {
        ItemNBTHelper.setBoolean(stack, TAG_ENABLED, enabled);
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    @Override
    public int getMana(ItemStack stack) {
        return ItemTerraPick.getMana_(stack);
    }

    public static int getMana_(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    public static int getLevel(ItemStack stack) {
        int mana = ItemTerraPick.getMana_(stack);
        for (int i = LEVELS.length - 1; i > 0; --i) {
            if (mana < LEVELS[i]) continue;
            return i;
        }
        return 0;
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        ItemTerraPick.setMana(stack, Math.min(this.getMana(stack) + mana, Integer.MAX_VALUE));
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return true;
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return !(otherStack.func_77973_b() instanceof IManaGivingItem);
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return false;
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return true;
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 4 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean disposeOfTrashBlocks(ItemStack stack) {
        return ItemTerraPick.isTipped(stack);
    }
}

