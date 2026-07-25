/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Start
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Village
 *  net.minecraftforge.common.ChestGenHooks
 */
package thaumcraft.common.lib.world;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.ChestGenHooks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;

public class ComponentWizardTower
extends StructureVillagePieces.Village {
    private int averageGroundLevel = -1;
    public static final WeightedRandomChestContent[] towerChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151114_aO, 0, 1, 3, 3), new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 5, 10), new WeightedRandomChestContent(Items.field_151074_bl, 0, 1, 3, 5), new WeightedRandomChestContent(Items.field_151059_bz, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151144_bL, 0, 1, 1, 3), new WeightedRandomChestContent(ConfigItems.itemResource, 9, 1, 3, 20), new WeightedRandomChestContent(ConfigItems.itemResource, 0, 1, 1, 5), new WeightedRandomChestContent(ConfigItems.itemResource, 1, 1, 1, 5), new WeightedRandomChestContent(ConfigItems.itemResource, 2, 1, 2, 5), new WeightedRandomChestContent(ConfigItems.itemThaumonomicon, 0, 1, 1, 20)};

    public ComponentWizardTower() {
    }

    public ComponentWizardTower(StructureVillagePieces.Start par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
        super(par1ComponentVillageStartPiece, par2);
        this.field_74885_f = par5;
        this.field_74887_e = par4StructureBoundingBox;
    }

    public static Object buildComponent(StructureVillagePieces.Start startPiece, List pieces, Random random, int par3, int par4, int par5, int par6, int par7) {
        StructureBoundingBox var8 = StructureBoundingBox.func_78889_a((int)par3, (int)par4, (int)par5, (int)0, (int)0, (int)0, (int)5, (int)12, (int)5, (int)par6);
        return !ComponentWizardTower.func_74895_a((StructureBoundingBox)var8) || StructureComponent.func_74883_a((List)pieces, (StructureBoundingBox)var8) != null ? null : new ComponentWizardTower(startPiece, par7, random, var8, par6);
    }

    public boolean func_74875_a(World world, Random par2Random, StructureBoundingBox bb) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.func_74889_b(world, bb);
            if (this.averageGroundLevel < 0) {
                return true;
            }
            this.field_74887_e.func_78886_a(0, this.averageGroundLevel - this.field_74887_e.field_78894_e + 12 - 1, 0);
        }
        this.func_151549_a(world, bb, 2, 1, 2, 4, 11, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
        this.func_151549_a(world, bb, 2, 0, 2, 4, 0, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151549_a(world, bb, 2, 5, 2, 4, 5, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151549_a(world, bb, 2, 10, 2, 4, 10, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_151549_a(world, bb, 1, 0, 2, 1, 11, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151549_a(world, bb, 2, 0, 1, 4, 11, 1, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151549_a(world, bb, 5, 0, 2, 5, 11, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151549_a(world, bb, 2, 0, 5, 4, 11, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151549_a(world, bb, 2, 0, 5, 4, 11, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 1, 0, 1, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 1, 0, 5, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 5, 0, 1, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 5, 0, 5, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 1, 5, 1, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 1, 5, 5, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 5, 5, 1, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 5, 5, 5, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 1, 10, 1, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 1, 10, 5, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 5, 10, 1, bb);
        this.func_151550_a(world, Blocks.field_150347_e, 3, 5, 10, 5, bb);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 3, 7, 1, bb);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 3, 8, 1, bb);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 3, 7, 5, bb);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 3, 8, 5, bb);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 3, 2, 5, bb);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 3, 3, 5, bb);
        int var4 = this.func_151555_a(Blocks.field_150468_ap, 4);
        for (int var5 = 1; var5 <= 9; ++var5) {
            this.func_151550_a(world, Blocks.field_150468_ap, var4, 4, var5, 3, bb);
        }
        this.func_151550_a(world, Blocks.field_150415_aT, var4, 4, 10, 3, bb);
        this.func_151550_a(world, Blocks.field_150426_aN, 0, 3, 5, 3, bb);
        ChestGenHooks cgh = new ChestGenHooks("towerChestContents", towerChestContents, 4, 9);
        this.func_74879_a(world, bb, par2Random, 2, 6, 2, cgh.getItems(par2Random), cgh.getCount(par2Random));
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, 1, 1, bb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, 2, 1, bb);
        this.func_74881_a(world, bb, par2Random, 3, 1, 1, this.func_151555_a(Blocks.field_150466_ao, 1));
        if (this.func_151548_a(world, 3, 0, 0, bb).isAir((IBlockAccess)world, 3, 0, 0) && !this.func_151548_a(world, 3, -1, 0, bb).isAir((IBlockAccess)world, 3, -1, 0)) {
            this.func_151550_a(world, Blocks.field_150446_ar, this.func_151555_a(Blocks.field_150446_ar, 3), 3, 0, 0, bb);
        }
        for (int var5 = 0; var5 < 12; ++var5) {
            for (int var6 = 0; var6 < 5; ++var6) {
                this.func_74871_b(world, var6, 12, var5, bb);
                this.func_151554_b(world, Blocks.field_150347_e, 0, var6, -1, var5, bb);
            }
        }
        this.func_74893_a(world, bb, 7, 1, 1, 1);
        return true;
    }

    protected int func_74888_b(int par1) {
        return ConfigEntities.entWizardId;
    }
}

