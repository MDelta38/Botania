/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 */
package witchinggadgets.common.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class EntityItemReforming
extends EntityItem {
    public int renderDelay = 120;

    public EntityItemReforming(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
        this.field_145804_b = 125;
    }

    public EntityItemReforming(World world) {
        super(world);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.renderDelay > 0) {
            --this.renderDelay;
            if (this.renderDelay <= 20) {
                for (int i = 0; i < 6; ++i) {
                    Thaumcraft.proxy.drawInfusionParticles1(this.field_70170_p, this.field_70165_t + (double)this.field_70146_Z.nextInt(3) - 1.5, this.field_70163_u + (double)this.field_70146_Z.nextFloat(), this.field_70161_v + (double)this.field_70146_Z.nextInt(3) - 1.5, (int)Math.floor(this.field_70165_t), (int)Math.floor(this.field_70163_u), (int)Math.floor(this.field_70161_v), this.func_92059_d().func_77973_b(), this.func_92059_d().func_77960_j());
                }
            }
        }
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("renderDelay", this.renderDelay);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.renderDelay = par1NBTTagCompound.func_74762_e("renderDelay");
    }
}

