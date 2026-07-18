/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.relic;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemInfiniteFruit
extends ItemRelic
implements IManaUsingItem {
    public static IIcon dasBootIcon;

    public ItemInfiniteFruit() {
        super("infiniteFruit");
    }

    public int func_77626_a(ItemStack p_77626_1_) {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_) {
        return this.isBoot(p_77661_1_) ? EnumAction.drink : EnumAction.eat;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (p_77659_3_.func_71043_e(false) && ItemInfiniteFruit.isRightPlayer(p_77659_3_, p_77659_1_)) {
            p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        }
        return p_77659_1_;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        super.onUsingTick(stack, player, count);
        if (ManaItemHandler.requestManaExact(stack, player, 500, true)) {
            if (count % 5 == 0) {
                player.func_71024_bL().func_75122_a(1, 1.0f);
            }
            if (count == 5 && player.func_71043_e(false)) {
                ReflectionHelper.setPrivateValue(EntityPlayer.class, (Object)player, (Object)20, (String[])LibObfuscation.ITEM_IN_USE_COUNT);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, this);
        dasBootIcon = IconHelper.forName(par1IconRegister, "dasBoot");
    }

    public IIcon func_77650_f(ItemStack par1ItemStack) {
        return this.isBoot(par1ItemStack) ? dasBootIcon : super.func_77650_f(par1ItemStack);
    }

    private boolean isBoot(ItemStack par1ItemStack) {
        String name = par1ItemStack.func_82833_r().toLowerCase().trim();
        return name.equals("das boot");
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

