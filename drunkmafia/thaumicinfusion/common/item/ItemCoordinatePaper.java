/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.input.Keyboard
 */
package drunkmafia.thaumicinfusion.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

public class ItemCoordinatePaper
extends Item {
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean val) {
        NBTTagCompound nbt = stack.func_77978_p();
        if (nbt == null) {
            return;
        }
        if (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54)) {
            list.add("X: " + nbt.func_74762_e("CoordinateX"));
            list.add("Y: " + nbt.func_74762_e("CoordinateY"));
            list.add("Z: " + nbt.func_74762_e("CoordinateZ"));
            list.add("Dimension: " + nbt.func_74762_e("CoordinateDim"));
        } else {
            list.add("Hold shift for more info");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.field_77791_bV = ir.func_94245_a("thaumcraft:researchnotes");
    }
}

