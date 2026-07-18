/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item.lens;

import cpw.mods.fml.common.registry.GameRegistry;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ICompositableLens;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.ILensControl;
import vazkii.botania.api.mana.IManaSpreader;
import vazkii.botania.api.mana.ITinyPlanetExcempt;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.CompositeLensRecipe;
import vazkii.botania.common.crafting.recipe.LensDyeingRecipe;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.lens.Lens;
import vazkii.botania.common.item.lens.LensBounce;
import vazkii.botania.common.item.lens.LensDamage;
import vazkii.botania.common.item.lens.LensEfficiency;
import vazkii.botania.common.item.lens.LensExplosive;
import vazkii.botania.common.item.lens.LensFire;
import vazkii.botania.common.item.lens.LensFirework;
import vazkii.botania.common.item.lens.LensFlare;
import vazkii.botania.common.item.lens.LensGravity;
import vazkii.botania.common.item.lens.LensInfluence;
import vazkii.botania.common.item.lens.LensLight;
import vazkii.botania.common.item.lens.LensMagnet;
import vazkii.botania.common.item.lens.LensMine;
import vazkii.botania.common.item.lens.LensPaint;
import vazkii.botania.common.item.lens.LensPhantom;
import vazkii.botania.common.item.lens.LensPiston;
import vazkii.botania.common.item.lens.LensPower;
import vazkii.botania.common.item.lens.LensRedirect;
import vazkii.botania.common.item.lens.LensSpeed;
import vazkii.botania.common.item.lens.LensStorm;
import vazkii.botania.common.item.lens.LensTime;
import vazkii.botania.common.item.lens.LensWarp;
import vazkii.botania.common.item.lens.LensWeight;
import vazkii.botania.common.lib.LibItemNames;

public class ItemLens
extends ItemMod
implements ILensControl,
ICompositableLens,
ITinyPlanetExcempt {
    public static final int SUBTYPES = 22;
    public static final int NORMAL = 0;
    public static final int SPEED = 1;
    public static final int POWER = 2;
    public static final int TIME = 3;
    public static final int EFFICIENCY = 4;
    public static final int BOUNCE = 5;
    public static final int GRAVITY = 6;
    public static final int MINE = 7;
    public static final int DAMAGE = 8;
    public static final int PHANTOM = 9;
    public static final int MAGNET = 10;
    public static final int EXPLOSIVE = 11;
    public static final int INFLUENCE = 12;
    public static final int WEIGHT = 13;
    public static final int PAINT = 14;
    public static final int FIRE = 15;
    public static final int PISTON = 16;
    public static final int LIGHT = 17;
    public static final int WARP = 18;
    public static final int REDIRECT = 19;
    public static final int FIREWORK = 20;
    public static final int FLARE = 21;
    public static final int STORM = 5000;
    private static final int PROP_NONE = 0;
    private static final int PROP_POWER = 1;
    private static final int PROP_ORIENTATION = 2;
    private static final int PROP_TOUCH = 4;
    private static final int PROP_INTERACTION = 8;
    private static final int PROP_DAMAGE = 16;
    private static final int PROP_CONTROL = 32;
    private static final int[] props = new int[22];
    private static final Lens[] lenses = new Lens[22];
    private static Lens fallbackLens = new Lens();
    private static Lens stormLens = new LensStorm();
    private static final String TAG_COLOR = "color";
    private static final String TAG_COMPOSITE_LENS = "compositeLens";
    public static IIcon iconGlass;
    IIcon[] ringIcons;

    public ItemLens() {
        this.func_77655_b("lens");
        this.func_77625_d(1);
        this.func_77627_a(true);
        GameRegistry.addRecipe((IRecipe)new CompositeLensRecipe());
        GameRegistry.addRecipe((IRecipe)new LensDyeingRecipe());
        RecipeSorter.register((String)"botania:compositeLens", CompositeLensRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        RecipeSorter.register((String)"botania:lensDying", LensDyeingRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        iconGlass = IconHelper.forName(par1IconRegister, "lensInside");
        this.ringIcons = new IIcon[22];
        for (int i = 0; i < this.ringIcons.length; ++i) {
            this.ringIcons[i] = IconHelper.forName(par1IconRegister, LibItemNames.LENS_NAMES[i]);
        }
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 22; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon func_77618_c(int par1, int par2) {
        return par2 == 1 ? this.ringIcons[Math.min(21, par1)] : iconGlass;
    }

    public IIcon func_77617_a(int par1) {
        return this.func_77618_c(par1, 0);
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return par2 == 0 ? this.getLensColor(par1ItemStack) : 0xFFFFFF;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item." + LibItemNames.LENS_NAMES[Math.min(21, par1ItemStack.func_77960_j())];
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        int storedColor = ItemLens.getStoredColor(par1ItemStack);
        if (storedColor != -1) {
            par3List.add(String.format(StatCollector.func_74838_a((String)"botaniamisc.color"), StatCollector.func_74838_a((String)("botania.color" + storedColor))));
        }
    }

    public String getItemShortTermName(ItemStack stack) {
        return StatCollector.func_74838_a((String)(stack.func_77977_a().replaceAll("item.", "item.botania:") + ".short"));
    }

    public String func_77653_i(ItemStack stack) {
        ItemStack compositeLens = this.getCompositeLens(stack);
        if (compositeLens == null) {
            return super.func_77653_i(stack);
        }
        return String.format(StatCollector.func_74838_a((String)"item.botania:compositeLens.name"), this.getItemShortTermName(stack), this.getItemShortTermName(compositeLens));
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor != -1) {
            props.color = this.getLensColor(stack);
        }
        ItemLens.getLens(stack.func_77960_j()).apply(stack, props);
        ItemStack compositeLens = this.getCompositeLens(stack);
        if (compositeLens != null && compositeLens.func_77973_b() instanceof ILens) {
            ((ILens)compositeLens.func_77973_b()).apply(compositeLens, props);
        }
    }

    @Override
    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable)burst;
        dead = ItemLens.getLens(stack.func_77960_j()).collideBurst(burst, entity, pos, isManaBlock, dead, stack);
        ItemStack compositeLens = this.getCompositeLens(stack);
        if (compositeLens != null && compositeLens.func_77973_b() instanceof ILens) {
            dead = ((ILens)compositeLens.func_77973_b()).collideBurst(burst, pos, isManaBlock, dead, compositeLens);
        }
        return dead;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable)burst;
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor == 16 && entity.field_70170_p.field_72995_K) {
            burst.setColor(this.getLensColor(stack));
        }
        ItemLens.getLens(stack.func_77960_j()).updateBurst(burst, entity, stack);
        ItemStack compositeLens = this.getCompositeLens(stack);
        if (compositeLens != null && compositeLens.func_77973_b() instanceof ILens) {
            ((ILens)compositeLens.func_77973_b()).updateBurst(burst, compositeLens);
        }
    }

    @Override
    public int getLensColor(ItemStack stack) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor == -1) {
            return 0xFFFFFF;
        }
        if (storedColor == 16) {
            return Color.HSBtoRGB((float)(Botania.proxy.getWorldElapsedTicks() * 2L % 360L) / 360.0f, 1.0f, 1.0f);
        }
        float[] color = EntitySheep.field_70898_d[storedColor];
        return new Color(color[0], color[1], color[2]).getRGB();
    }

    public static int getStoredColor(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_COLOR, -1);
    }

    public static ItemStack setLensColor(ItemStack stack, int color) {
        ItemNBTHelper.setInt(stack, TAG_COLOR, color);
        return stack;
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
    }

    public static void setProps(int lens, int props_) {
        ItemLens.props[lens] = props_;
    }

    public static void setLens(int index, Lens lens) {
        ItemLens.lenses[index] = lens;
    }

    public static boolean isBlacklisted(ItemStack lens1, ItemStack lens2) {
        ICompositableLens item1 = (ICompositableLens)lens1.func_77973_b();
        ICompositableLens item2 = (ICompositableLens)lens2.func_77973_b();
        return (item1.getProps(lens1) & item2.getProps(lens2)) != 0;
    }

    public static Lens getLens(int index) {
        if (index == 5000) {
            return stormLens;
        }
        Lens lens = lenses[index];
        return lens == null ? fallbackLens : lens;
    }

    @Override
    public boolean canCombineLenses(ItemStack sourceLens, ItemStack compositeLens) {
        ICompositableLens compositeItem;
        ICompositableLens sourceItem = (ICompositableLens)sourceLens.func_77973_b();
        if (sourceItem == (compositeItem = (ICompositableLens)compositeLens.func_77973_b()) && sourceLens.func_77960_j() == compositeLens.func_77960_j()) {
            return false;
        }
        if (!sourceItem.isCombinable(sourceLens) || !compositeItem.isCombinable(compositeLens)) {
            return false;
        }
        return !ItemLens.isBlacklisted(sourceLens, compositeLens);
    }

    @Override
    public ItemStack getCompositeLens(ItemStack stack) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_COMPOSITE_LENS, false);
        ItemStack lens = ItemStack.func_77949_a((NBTTagCompound)cmp);
        return lens;
    }

    @Override
    public ItemStack setCompositeLens(ItemStack sourceLens, ItemStack compositeLens) {
        NBTTagCompound cmp = new NBTTagCompound();
        compositeLens.func_77955_b(cmp);
        ItemNBTHelper.setCompound(sourceLens, TAG_COMPOSITE_LENS, cmp);
        return sourceLens;
    }

    @Override
    public boolean shouldPull(ItemStack stack) {
        return stack.func_77960_j() != 5000;
    }

    @Override
    public boolean isControlLens(ItemStack stack) {
        return (this.getProps(stack) & 0x20) != 0;
    }

    @Override
    public boolean allowBurstShooting(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        return lenses[stack.func_77960_j()].allowBurstShooting(stack, spreader, redstone);
    }

    @Override
    public void onControlledSpreaderTick(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        lenses[stack.func_77960_j()].onControlledSpreaderTick(stack, spreader, redstone);
    }

    @Override
    public void onControlledSpreaderPulse(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        lenses[stack.func_77960_j()].onControlledSpreaderPulse(stack, spreader, redstone);
    }

    @Override
    public int getProps(ItemStack stack) {
        return props[stack.func_77960_j()];
    }

    @Override
    public boolean isCombinable(ItemStack stack) {
        return stack.func_77960_j() != 0;
    }

    static {
        ItemLens.setProps(0, 0);
        ItemLens.setProps(1, 0);
        ItemLens.setProps(2, 1);
        ItemLens.setProps(3, 0);
        ItemLens.setProps(4, 0);
        ItemLens.setProps(5, 4);
        ItemLens.setProps(6, 2);
        ItemLens.setProps(7, 12);
        ItemLens.setProps(8, 16);
        ItemLens.setProps(9, 4);
        ItemLens.setProps(10, 2);
        ItemLens.setProps(11, 28);
        ItemLens.setProps(12, 0);
        ItemLens.setProps(13, 12);
        ItemLens.setProps(14, 12);
        ItemLens.setProps(15, 28);
        ItemLens.setProps(16, 12);
        ItemLens.setProps(17, 12);
        ItemLens.setProps(18, 0);
        ItemLens.setProps(19, 12);
        ItemLens.setProps(20, 4);
        ItemLens.setProps(21, 32);
        ItemLens.setLens(0, fallbackLens);
        ItemLens.setLens(1, new LensSpeed());
        ItemLens.setLens(2, new LensPower());
        ItemLens.setLens(3, new LensTime());
        ItemLens.setLens(4, new LensEfficiency());
        ItemLens.setLens(5, new LensBounce());
        ItemLens.setLens(6, new LensGravity());
        ItemLens.setLens(7, new LensMine());
        ItemLens.setLens(8, new LensDamage());
        ItemLens.setLens(9, new LensPhantom());
        ItemLens.setLens(10, new LensMagnet());
        ItemLens.setLens(11, new LensExplosive());
        ItemLens.setLens(12, new LensInfluence());
        ItemLens.setLens(13, new LensWeight());
        ItemLens.setLens(14, new LensPaint());
        ItemLens.setLens(15, new LensFire());
        ItemLens.setLens(16, new LensPiston());
        ItemLens.setLens(17, new LensLight());
        ItemLens.setLens(18, new LensWarp());
        ItemLens.setLens(19, new LensRedirect());
        ItemLens.setLens(20, new LensFirework());
        ItemLens.setLens(21, new LensFlare());
    }
}

