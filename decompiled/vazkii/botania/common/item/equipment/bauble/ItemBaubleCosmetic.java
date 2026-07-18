/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.oredict.RecipeSorter;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.crafting.recipe.CosmeticAttachRecipe;
import vazkii.botania.common.crafting.recipe.CosmeticRemoveRecipe;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemBaubleCosmetic
extends ItemBauble
implements ICosmeticBauble {
    private static final int SUBTYPES = 32;
    IIcon[] icons;

    public ItemBaubleCosmetic() {
        super("cosmetic");
        this.func_77627_a(true);
        GameRegistry.addRecipe((IRecipe)new CosmeticAttachRecipe());
        GameRegistry.addRecipe((IRecipe)new CosmeticRemoveRecipe());
        RecipeSorter.register((String)"botania:cosmeticAttach", CosmeticAttachRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        RecipeSorter.register((String)"botania:cosmeticRemove", CosmeticRemoveRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[32];
        for (int i = 0; i < 32; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 32; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public IIcon func_77617_a(int dmg) {
        return this.icons[Math.min(31, dmg)];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    @Override
    public void addHiddenTooltip(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.cosmeticBauble"), par3List);
        super.addHiddenTooltip(par1ItemStack, par2EntityPlayer, par3List, par4);
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        if (type == IBaubleRender.RenderType.HEAD) {
            IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
            switch (stack.func_77960_j()) {
                case 2: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.5f, (float)0.0f);
                    this.renderIcon(2);
                    break;
                }
                case 4: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.5f, (float)0.0f);
                    this.renderIcon(4);
                    break;
                }
                case 5: {
                    this.faceTranslate();
                    this.scale(0.35f);
                    GL11.glTranslatef((float)0.3f, (float)-0.5f, (float)0.0f);
                    this.renderIcon(5);
                    break;
                }
                case 6: {
                    this.faceTranslate();
                    this.scale(0.35f);
                    GL11.glTranslatef((float)0.9f, (float)-0.5f, (float)0.0f);
                    this.renderIcon(6);
                    break;
                }
                case 7: {
                    this.faceTranslate();
                    this.scale(0.6f);
                    GL11.glTranslatef((float)0.2f, (float)0.3f, (float)0.6f);
                    this.renderIcon(7);
                    break;
                }
                case 8: {
                    this.faceTranslate();
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.scale(0.6f);
                    GL11.glTranslatef((float)-0.9f, (float)0.0f, (float)0.2f);
                    this.renderIcon(8);
                    break;
                }
                case 9: {
                    this.faceTranslate();
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.scale(0.6f);
                    GL11.glTranslatef((float)-0.9f, (float)-0.2f, (float)0.2f);
                    this.renderIcon(9);
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)1.0f);
                    this.renderIcon(9);
                    break;
                }
                case 10: {
                    this.faceTranslate();
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.scale(0.4f);
                    GL11.glTranslatef((float)-0.5f, (float)-0.1f, (float)0.3f);
                    GL11.glRotatef((float)120.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.renderIcon(10);
                    GL11.glRotatef((float)-100.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.renderIcon(10);
                    break;
                }
                case 11: {
                    this.faceTranslate();
                    this.scale(0.6f);
                    GL11.glTranslatef((float)0.2f, (float)-0.1f, (float)0.6f);
                    this.renderIcon(11);
                    break;
                }
                case 15: {
                    this.faceTranslate();
                    GL11.glTranslatef((float)-0.1f, (float)-0.55f, (float)0.0f);
                    this.renderIcon(15);
                    break;
                }
                case 17: {
                    this.faceTranslate();
                    this.scale(0.35f);
                    GL11.glTranslatef((float)0.3f, (float)-0.6f, (float)0.0f);
                    this.renderIcon(17);
                    break;
                }
                case 18: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)-0.3f, (float)0.1f, (float)0.55f);
                    this.renderIcon(18);
                    break;
                }
                case 19: {
                    this.faceTranslate();
                    this.scale(0.6f);
                    GL11.glTranslatef((float)0.2f, (float)-0.2f, (float)0.1f);
                    this.renderIcon(19);
                    break;
                }
                case 20: {
                    this.faceTranslate();
                    this.scale(0.25f);
                    GL11.glTranslatef((float)0.4f, (float)0.5f, (float)-0.1f);
                    this.renderIcon(20);
                    GL11.glTranslatef((float)1.4f, (float)0.0f, (float)0.0f);
                    this.renderIcon(20);
                    break;
                }
                case 22: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.4f, (float)0.0f);
                    this.renderIcon(22);
                    break;
                }
                case 23: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.4f, (float)0.0f);
                    this.renderIcon(23);
                    break;
                }
                case 24: {
                    this.faceTranslate();
                    this.scale(0.6f);
                    GL11.glTranslatef((float)0.5f, (float)0.0f, (float)0.1f);
                    GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    this.renderIcon(24);
                    break;
                }
                case 25: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.5f, (float)0.0f);
                    this.renderIcon(25);
                    break;
                }
                case 26: {
                    this.faceTranslate();
                    GL11.glTranslatef((float)-0.1f, (float)-0.4f, (float)0.0f);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.7f);
                    this.renderIcon(26);
                    break;
                }
                case 27: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.65f, (float)0.0f);
                    this.renderIcon(27);
                    break;
                }
                case 28: {
                    this.faceTranslate();
                    this.scale(0.25f);
                    GL11.glTranslatef((float)1.55f, (float)-0.2f, (float)-0.1f);
                    this.renderIcon(28);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)-0.1f, (float)0.0f, (float)0.1f);
                    this.renderIcon(28);
                    break;
                }
                case 30: {
                    this.faceTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.04f, (float)-0.4f, (float)0.0f);
                    this.renderIcon(30);
                    break;
                }
                case 31: {
                    this.faceTranslate();
                    this.scale(0.5f);
                    GL11.glTranslatef((float)0.3f, (float)0.7f, (float)0.5f);
                    this.renderIcon(31);
                }
            }
        } else {
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            switch (stack.func_77960_j()) {
                case 0: {
                    this.chestTranslate();
                    this.scale(0.5f);
                    GL11.glTranslatef((float)0.5f, (float)0.7f, (float)0.0f);
                    this.renderIcon(0);
                    break;
                }
                case 1: {
                    this.chestTranslate();
                    this.scale(0.75f);
                    GL11.glTranslatef((float)0.15f, (float)-0.1f, (float)0.0f);
                    this.renderIcon(1);
                    break;
                }
                case 3: {
                    this.chestTranslate();
                    this.scale(0.6f);
                    GL11.glTranslatef((float)0.35f, (float)0.3f, (float)0.0f);
                    this.renderIcon(3);
                    break;
                }
                case 12: {
                    this.chestTranslate();
                    this.scale(0.225f);
                    GL11.glTranslatef((float)1.2f, (float)1.9f, (float)0.0f);
                    this.renderIcon(12);
                    break;
                }
                case 13: {
                    this.chestTranslate();
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.scale(0.5f);
                    GL11.glTranslatef((float)-1.3f, (float)-0.4f, (float)-1.0f);
                    this.renderIcon(13);
                    break;
                }
                case 14: {
                    this.chestTranslate();
                    this.scale(0.5f);
                    GL11.glTranslatef((float)2.3f, (float)1.0f, (float)-0.05f);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    this.renderIcon(14);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.3f, (float)1.0f);
                    GL11.glTranslatef((float)-2.6f, (float)0.0f, (float)0.05f);
                    this.renderIcon(14);
                    break;
                }
                case 16: {
                    this.chestTranslate();
                    this.scale(0.225f);
                    GL11.glTranslatef((float)2.3f, (float)1.9f, (float)0.0f);
                    this.renderIcon(16);
                    break;
                }
                case 21: {
                    this.chestTranslate();
                    this.scale(0.3f);
                    GL11.glTranslatef((float)1.2f, (float)0.5f, (float)0.0f);
                    this.renderIcon(21);
                    break;
                }
                case 29: {
                    this.chestTranslate();
                    this.scale(0.8f);
                    GL11.glTranslatef((float)0.2f, (float)-0.2f, (float)-0.35f);
                    GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    this.renderIcon(29);
                }
            }
        }
    }

    public void faceTranslate() {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)-0.25f);
    }

    public void chestTranslate() {
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.7f, (float)0.15f);
    }

    public void scale(float f) {
        GL11.glScalef((float)f, (float)f, (float)f);
    }

    public void renderIcon(int i) {
        IIcon icon = this.icons[i];
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
    }
}

