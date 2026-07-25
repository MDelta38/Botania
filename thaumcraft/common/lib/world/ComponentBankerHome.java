/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Start
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Village
 */
package thaumcraft.common.lib.world;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import thaumcraft.common.config.ConfigEntities;

public class ComponentBankerHome
extends StructureVillagePieces.Village {
    private boolean isTallHouse;
    private int tablePosition;

    public ComponentBankerHome() {
    }

    public ComponentBankerHome(StructureVillagePieces.Start p_i2101_1_, int p_i2101_2_, Random p_i2101_3_, StructureBoundingBox p_i2101_4_, int p_i2101_5_) {
        super(p_i2101_1_, p_i2101_2_);
        this.field_74885_f = p_i2101_5_;
        this.field_74887_e = p_i2101_4_;
        this.isTallHouse = p_i2101_3_.nextBoolean();
        this.tablePosition = p_i2101_3_.nextInt(3);
    }

    protected void func_143012_a(NBTTagCompound p_143012_1_) {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.func_74768_a("T", this.tablePosition);
        p_143012_1_.func_74757_a("C", this.isTallHouse);
    }

    protected void func_143011_b(NBTTagCompound p_143011_1_) {
        super.func_143011_b(p_143011_1_);
        this.tablePosition = p_143011_1_.func_74762_e("T");
        this.isTallHouse = p_143011_1_.func_74767_n("C");
    }

    public static Object buildComponent(StructureVillagePieces.Start p_74908_0_, List p_74908_1_, Random p_74908_2_, int p_74908_3_, int p_74908_4_, int p_74908_5_, int p_74908_6_, int p_74908_7_) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a((int)p_74908_3_, (int)p_74908_4_, (int)p_74908_5_, (int)0, (int)0, (int)0, (int)4, (int)6, (int)5, (int)p_74908_6_);
        return ComponentBankerHome.func_74895_a((StructureBoundingBox)structureboundingbox) && StructureComponent.func_74883_a((List)p_74908_1_, (StructureBoundingBox)structureboundingbox) == null ? new ComponentBankerHome(p_74908_0_, p_74908_7_, p_74908_2_, structureboundingbox, p_74908_6_) : null;
    }

    public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
        if (this.field_143015_k < 0) {
            this.field_143015_k = this.func_74889_b(p_74875_1_, p_74875_3_);
            if (this.field_143015_k < 0) {
                return true;
            }
            this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 6 - 1, 0);
        }
        this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 5, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 3, 0, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 1, 0, 1, 2, 0, 3, Blocks.field_150346_d, Blocks.field_150346_d, false);
        if (this.isTallHouse) {
            this.func_151549_a(p_74875_1_, p_74875_3_, 1, 4, 1, 2, 4, 3, Blocks.field_150364_r, Blocks.field_150364_r, false);
        } else {
            this.func_151549_a(p_74875_1_, p_74875_3_, 1, 5, 1, 2, 5, 3, Blocks.field_150364_r, Blocks.field_150364_r, false);
        }
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 1, 4, 0, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 2, 4, 0, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 1, 4, 4, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 2, 4, 4, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 0, 4, 1, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 0, 4, 2, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 0, 4, 3, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 3, 4, 1, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 3, 4, 2, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 3, 4, 3, p_74875_3_);
        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 0, 3, 3, 0, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 4, 0, 3, 4, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 4, 3, 3, 4, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 1, 3, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 4, 2, 3, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151550_a(p_74875_1_, Blocks.field_150411_aY, 0, 0, 2, 2, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150411_aY, 0, 3, 2, 2, p_74875_3_);
        if (this.tablePosition > 0) {
            this.func_151550_a(p_74875_1_, Blocks.field_150463_bK, 0, this.tablePosition, 1, 3, p_74875_3_);
            this.func_151550_a(p_74875_1_, Blocks.field_150456_au, 0, this.tablePosition, 2, 3, p_74875_3_);
        }
        this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 1, 0, p_74875_3_);
        this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 2, 0, p_74875_3_);
        this.func_74881_a(p_74875_1_, p_74875_3_, p_74875_2_, 1, 1, 0, this.func_151555_a(Blocks.field_150466_ao, 1));
        if (this.func_151548_a(p_74875_1_, 1, 0, -1, p_74875_3_).func_149688_o() == Material.field_151579_a && this.func_151548_a(p_74875_1_, 1, -1, -1, p_74875_3_).func_149688_o() != Material.field_151579_a) {
            this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, this.func_151555_a(Blocks.field_150446_ar, 3), 1, 0, -1, p_74875_3_);
        }
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.func_74871_b(p_74875_1_, j, 6, i, p_74875_3_);
                this.func_151554_b(p_74875_1_, Blocks.field_150347_e, 0, j, -1, i, p_74875_3_);
            }
        }
        this.func_74893_a(p_74875_1_, p_74875_3_, 1, 1, 2, 1);
        return true;
    }

    protected int func_74888_b(int par1) {
        return ConfigEntities.entBankerId;
    }
}

