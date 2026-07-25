/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.ai.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class AIConvertGrass
extends EntityAIBase {
    private EntityLiving entity;
    private World world;
    int field_48399_a = 0;

    public AIConvertGrass(EntityLiving par1EntityLiving) {
        this.entity = par1EntityLiving;
        this.world = par1EntityLiving.field_70170_p;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        int var3;
        int var2;
        if (this.entity.func_70681_au().nextInt(250) != 0) {
            return false;
        }
        int var1 = MathHelper.func_76128_c((double)this.entity.field_70165_t);
        return this.world.func_147439_a(var1, var2 = MathHelper.func_76128_c((double)this.entity.field_70163_u), var3 = MathHelper.func_76128_c((double)this.entity.field_70161_v)) == Blocks.field_150329_H && this.world.func_72805_g(var1, var2, var3) == 1 ? true : this.world.func_147439_a(var1, var2 - 1, var3) == Blocks.field_150349_c;
    }

    public void func_75249_e() {
        this.field_48399_a = 40;
        this.world.func_72960_a((Entity)this.entity, (byte)10);
        this.entity.func_70661_as().func_75499_g();
    }

    public void func_75251_c() {
        this.field_48399_a = 0;
    }

    public boolean func_75253_b() {
        return this.field_48399_a > 0;
    }

    public int func_48396_h() {
        return this.field_48399_a;
    }

    public void func_75246_d() {
        this.field_48399_a = Math.max(0, this.field_48399_a - 1);
        if (this.field_48399_a == 4) {
            int var3;
            int var2;
            int var1 = MathHelper.func_76128_c((double)this.entity.field_70165_t);
            if (this.world.func_147439_a(var1, var2 = MathHelper.func_76128_c((double)this.entity.field_70163_u), var3 = MathHelper.func_76128_c((double)this.entity.field_70161_v)) == Blocks.field_150329_H) {
                this.world.func_72926_e(2001, var1, var2, var3, Block.func_149682_b((Block)Blocks.field_150349_c) + 4096);
                this.world.func_147468_f(var1, var2, var3);
                this.world.func_147465_d(var1, var2, var3, ConfigBlocks.blockTaintFibres, 0, 3);
                Utils.setBiomeAt(this.world, var1, var3, ThaumcraftWorldGenerator.biomeTaint);
                this.entity.func_70615_aA();
            } else if (this.world.func_147439_a(var1, var2 - 1, var3) == Blocks.field_150349_c) {
                this.world.func_72926_e(2001, var1, var2 - 1, var3, Block.func_149682_b((Block)Blocks.field_150349_c));
                this.world.func_147465_d(var1, var2, var3, ConfigBlocks.blockTaintFibres, 0, 3);
                Utils.setBiomeAt(this.world, var1, var3, ThaumcraftWorldGenerator.biomeTaint);
                this.entity.func_70615_aA();
            }
        }
    }
}

