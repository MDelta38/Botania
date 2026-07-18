/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemRecord
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.common.item.record;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;

public class ItemModRecord
extends ItemRecord {
    private final String file;

    public ItemModRecord(String record, String name) {
        super("botania:" + record);
        this.func_77637_a(BotaniaCreativeTab.INSTANCE);
        this.func_77655_b(name);
        this.file = "botania:music." + record;
    }

    public Item func_77655_b(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.func_77655_b(par1Str);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack).replaceAll("item\\.", "item.botania:");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
    }

    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(this.file);
    }
}

