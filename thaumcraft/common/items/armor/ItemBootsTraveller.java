/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;

public class ItemBootsTraveller
extends ItemArmor
implements IRepairable,
IRunicArmor {
    public IIcon icon;

    public ItemBootsTraveller(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77656_e(350);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:bootstraveler");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumcraft:textures/models/bootstraveler.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!player.field_71075_bZ.field_75100_b && player.field_70701_bs > 0.0f) {
            if (player.field_70170_p.field_72995_K && !player.func_70093_af()) {
                if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.func_145782_y())) {
                    Thaumcraft.instance.entityEventHandler.prevStep.put(player.func_145782_y(), Float.valueOf(player.field_70138_W));
                }
                player.field_70138_W = 1.0f;
            }
            if (player.field_70122_E) {
                float bonus = 0.055f;
                if (player.func_70090_H()) {
                    bonus /= 4.0f;
                }
                player.func_70060_a(0.0f, 1.0f, bonus);
            } else {
                player.field_70747_aH = Hover.getHover(player.func_145782_y()) ? 0.03f : 0.05f;
            }
        }
        if (player.field_70143_R > 0.0f) {
            player.field_70143_R -= 0.25f;
        }
    }
}

