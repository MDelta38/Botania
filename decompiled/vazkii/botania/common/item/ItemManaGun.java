/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.ManaGunClipRecipe;
import vazkii.botania.common.crafting.recipe.ManaGunLensRecipe;
import vazkii.botania.common.crafting.recipe.ManaGunRemoveLensRecipe;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ItemMod;

public class ItemManaGun
extends ItemMod
implements IManaUsingItem {
    private static final String TAG_LENS = "lens";
    private static final String TAG_CLIP = "clip";
    private static final String TAG_CLIP_POS = "clipPos";
    private static final int CLIP_SLOTS = 6;
    private static final int COOLDOWN = 30;
    IIcon[] icons;

    public ItemManaGun() {
        this.func_77656_e(30);
        this.func_77625_d(1);
        this.setNoRepair();
        this.func_77655_b("manaGun");
        GameRegistry.addRecipe((IRecipe)new ManaGunLensRecipe());
        GameRegistry.addRecipe((IRecipe)new ManaGunRemoveLensRecipe());
        GameRegistry.addRecipe((IRecipe)new ManaGunClipRecipe());
        RecipeSorter.register((String)"botania:manaGunLens", ManaGunLensRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        RecipeSorter.register((String)"botania:manaGunRemoveLens", ManaGunRemoveLensRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        RecipeSorter.register((String)"botania:manaGunClip", ManaGunClipRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int effCd = 30;
        PotionEffect effect = par3EntityPlayer.func_70660_b(Potion.field_76422_e);
        if (effect != null) {
            effCd -= (effect.func_76458_c() + 1) * 8;
        }
        if (par3EntityPlayer.func_70093_af() && ItemManaGun.hasClip(par1ItemStack)) {
            ItemManaGun.rotatePos(par1ItemStack);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.click", 0.6f, (1.0f + (par2World.field_73012_v.nextFloat() - par2World.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
            if (par2World.field_72995_K) {
                par3EntityPlayer.func_71038_i();
            }
            ItemStack lens = ItemManaGun.getLens(par1ItemStack);
            ItemsRemainingRenderHandler.set(lens, -2);
            par1ItemStack.func_77964_b(effCd);
        } else if (par1ItemStack.func_77960_j() == 0) {
            EntityManaBurst burst = this.getBurst(par3EntityPlayer, par1ItemStack, true);
            if (burst != null && ManaItemHandler.requestManaExact(par1ItemStack, par3EntityPlayer, burst.getMana(), true)) {
                if (!par2World.field_72995_K) {
                    par2World.func_72956_a((Entity)par3EntityPlayer, "botania:manaBlaster", 0.6f, 1.0f);
                    par3EntityPlayer.func_71064_a((StatBase)ModAchievements.manaBlasterShoot, 1);
                    if (this.isSugoiKawaiiDesuNe(par1ItemStack)) {
                        par3EntityPlayer.func_71064_a((StatBase)ModAchievements.desuGun, 1);
                    }
                    par2World.func_72838_d((Entity)burst);
                } else {
                    par3EntityPlayer.func_71038_i();
                    par3EntityPlayer.field_70159_w -= burst.field_70159_w * 0.1;
                    par3EntityPlayer.field_70181_x -= burst.field_70181_x * 0.3;
                    par3EntityPlayer.field_70179_y -= burst.field_70179_y * 0.1;
                }
                par1ItemStack.func_77964_b(effCd);
            } else if (!par2World.field_72995_K) {
                par2World.func_72956_a((Entity)par3EntityPlayer, "random.click", 0.6f, (1.0f + (par2World.field_73012_v.nextFloat() - par2World.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
            }
        }
        return par1ItemStack;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        int states = 3;
        this.icons = new IIcon[states * 2];
        for (int i = 0; i < states; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
            this.icons[states + i] = IconHelper.forName(par1IconRegister, "desuGun" + i);
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        boolean desu = this.isSugoiKawaiiDesuNe(stack);
        int index = pass;
        if (index == 0 && ItemManaGun.hasClip(stack)) {
            index = 2;
        }
        return this.icons[Math.min(2, index) + (desu ? 3 : 0)];
    }

    private boolean isSugoiKawaiiDesuNe(ItemStack stack) {
        return stack.func_82833_r().equalsIgnoreCase("desu gun");
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par2 == 0) {
            return 0xFFFFFF;
        }
        EntityManaBurst burst = this.getBurst((EntityPlayer)Minecraft.func_71410_x().field_71439_g, par1ItemStack, false);
        Color color = new Color(burst == null ? 0x20FF20 : burst.getColor());
        float mul = (float)(Math.sin((double)ClientTickHandler.ticksInGame / 5.0) * (double)0.15f);
        int c = (int)(255.0f * mul);
        return new Color(Math.max(0, Math.min(255, color.getRed() + c)), Math.max(0, Math.min(255, color.getGreen() + c)), Math.max(0, Math.min(255, color.getBlue() + c))).getRGB();
    }

    public boolean hasContainerItem(ItemStack stack) {
        return ItemManaGun.getLens(stack) != null;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return ItemManaGun.getLens(itemStack);
    }

    public boolean func_77630_h(ItemStack p_77630_1_) {
        return false;
    }

    public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack, boolean request) {
        EntityManaBurst burst = new EntityManaBurst(player);
        int maxMana = 120;
        int color = 0x20FF20;
        int ticksBeforeManaLoss = 60;
        float manaLossPerTick = 4.0f;
        float motionModifier = 5.0f;
        float gravity = 0.0f;
        BurstProperties props = new BurstProperties(maxMana, ticksBeforeManaLoss, manaLossPerTick, gravity, motionModifier, color);
        ItemStack lens = ItemManaGun.getLens(stack);
        if (lens != null) {
            ((ILens)lens.func_77973_b()).apply(lens, props);
        }
        burst.setSourceLens(lens);
        if (!request || ManaItemHandler.requestManaExact(stack, player, props.maxMana, false)) {
            burst.setColor(props.color);
            burst.setMana(props.maxMana);
            burst.setStartingMana(props.maxMana);
            burst.setMinManaLoss(props.ticksBeforeManaLoss);
            burst.setManaLossPerTick(props.manaLossPerTick);
            burst.setGravity(props.gravity);
            burst.setMotion(burst.field_70159_w * (double)props.motionModifier, burst.field_70181_x * (double)props.motionModifier, burst.field_70179_y * (double)props.motionModifier);
            return burst;
        }
        return null;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        List tooltip;
        boolean clip = ItemManaGun.hasClip(par1ItemStack);
        if (clip && !GuiScreen.func_146272_n()) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.shiftinfo"), par3List);
            return;
        }
        ItemStack lens = ItemManaGun.getLens(par1ItemStack);
        if (lens != null && (tooltip = lens.func_82840_a(par2EntityPlayer, false)).size() > 1) {
            par3List.addAll(tooltip.subList(1, tooltip.size()));
        }
        if (clip) {
            int pos = ItemManaGun.getClipPos(par1ItemStack);
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.hasClip"), par3List);
            for (int i = 0; i < 6; ++i) {
                String name = "";
                EnumChatFormatting formatting = i == pos ? EnumChatFormatting.GREEN : EnumChatFormatting.GRAY;
                ItemStack lensAt = ItemManaGun.getLensAtPos(par1ItemStack, i);
                name = lensAt == null ? StatCollector.func_74838_a((String)"botaniamisc.clipEmpty") : lensAt.func_82833_r();
                this.addStringToTooltip(formatting + " - " + name, par3List);
            }
        }
    }

    private void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        ItemStack lens = ItemManaGun.getLens(par1ItemStack);
        return super.func_77653_i(par1ItemStack) + (lens == null ? "" : " (" + EnumChatFormatting.GREEN + lens.func_82833_r() + EnumChatFormatting.RESET + ")");
    }

    public static boolean hasClip(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_CLIP, false);
    }

    public static void setClip(ItemStack stack, boolean clip) {
        ItemNBTHelper.setBoolean(stack, TAG_CLIP, clip);
    }

    public static int getClipPos(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_CLIP_POS, 0);
    }

    public static void setClipPos(ItemStack stack, int pos) {
        ItemNBTHelper.setInt(stack, TAG_CLIP_POS, pos);
    }

    public static void rotatePos(ItemStack stack) {
        int currPos = ItemManaGun.getClipPos(stack);
        boolean acceptEmpty = ItemManaGun.getLensAtPos(stack, currPos) != null;
        int[] slots = new int[5];
        int index = 0;
        int i = currPos + 1;
        while (i < 6) {
            slots[index] = i++;
            ++index;
        }
        i = 0;
        while (i < currPos) {
            slots[index] = i++;
            ++index;
        }
        for (int i2 : slots) {
            ItemStack lensAt = ItemManaGun.getLensAtPos(stack, i2);
            if (!acceptEmpty && lensAt == null) continue;
            ItemManaGun.setClipPos(stack, i2);
            return;
        }
    }

    public static ItemStack getLensAtPos(ItemStack stack, int pos) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_LENS + pos, true);
        if (cmp != null) {
            ItemStack lens = ItemStack.func_77949_a((NBTTagCompound)cmp);
            return lens;
        }
        return null;
    }

    public static void setLensAtPos(ItemStack stack, ItemStack lens, int pos) {
        NBTTagCompound cmp = new NBTTagCompound();
        if (lens != null) {
            lens.func_77955_b(cmp);
        }
        ItemNBTHelper.setCompound(stack, TAG_LENS + pos, cmp);
    }

    public static void setLens(ItemStack stack, ItemStack lens) {
        if (ItemManaGun.hasClip(stack)) {
            ItemManaGun.setLensAtPos(stack, lens, ItemManaGun.getClipPos(stack));
        }
        NBTTagCompound cmp = new NBTTagCompound();
        if (lens != null) {
            lens.func_77955_b(cmp);
        }
        ItemNBTHelper.setCompound(stack, TAG_LENS, cmp);
    }

    public static ItemStack getLens(ItemStack stack) {
        if (ItemManaGun.hasClip(stack)) {
            return ItemManaGun.getLensAtPos(stack, ItemManaGun.getClipPos(stack));
        }
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_LENS, true);
        if (cmp != null) {
            ItemStack lens = ItemStack.func_77949_a((NBTTagCompound)cmp);
            return lens;
        }
        return null;
    }

    public boolean func_77662_d() {
        return true;
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.func_77951_h()) {
            par1ItemStack.func_77964_b(par1ItemStack.func_77960_j() - 1);
        }
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

