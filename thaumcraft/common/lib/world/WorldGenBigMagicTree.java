/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBigMagicTree
extends WorldGenAbstractTree {
    static final byte[] otherCoordPairs = new byte[]{2, 0, 0, 1, 2, 1};
    Random rand = new Random();
    World worldObj;
    int[] basePos = new int[]{0, 0, 0};
    int heightLimit;
    int height;
    double heightAttenuation = 0.6618;
    double branchDensity = 1.0;
    double branchSlope = 0.381;
    double scaleWidth = 1.0;
    double leafDensity = 1.0;
    int trunkSize = 1;
    int heightLimitLimit = 12;
    int leafDistanceLimit = 3;
    int[][] leafNodes;

    public WorldGenBigMagicTree(boolean par1) {
        super(par1);
    }

    void generateLeafNodeList() {
        int i;
        this.height = (int)((double)this.heightLimit * this.heightAttenuation);
        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }
        if ((i = (int)(1.382 + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0, 2.0))) < 1) {
            i = 1;
        }
        int[][] aint = new int[i * this.heightLimit][4];
        int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
        int k = 1;
        int l = this.basePos[1] + this.height;
        int i1 = j - this.basePos[1];
        aint[0][0] = this.basePos[0];
        aint[0][1] = j--;
        aint[0][2] = this.basePos[2];
        aint[0][3] = l;
        while (i1 >= 0) {
            float f = this.layerSize(i1);
            if (f < 0.0f) {
                --j;
                --i1;
                continue;
            }
            double d0 = 0.5;
            for (int j1 = 0; j1 < i; ++j1) {
                int[] aint2;
                int l1;
                double d2;
                double d1 = this.scaleWidth * (double)f * ((double)this.rand.nextFloat() + 0.328);
                int k1 = MathHelper.func_76128_c((double)(d1 * Math.sin(d2 = (double)this.rand.nextFloat() * 2.0 * Math.PI) + (double)this.basePos[0] + d0));
                int[] aint1 = new int[]{k1, j, l1 = MathHelper.func_76128_c((double)(d1 * Math.cos(d2) + (double)this.basePos[2] + d0))};
                if (this.checkBlockLine(aint1, aint2 = new int[]{k1, j + this.leafDistanceLimit, l1}) != -1) continue;
                int[] aint3 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
                double d3 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - aint1[0]), 2.0) + Math.pow(Math.abs(this.basePos[2] - aint1[2]), 2.0));
                double d4 = d3 * this.branchSlope;
                aint3[1] = (double)aint1[1] - d4 > (double)l ? l : (int)((double)aint1[1] - d4);
                if (this.checkBlockLine(aint3, aint1) != -1) continue;
                aint[k][0] = k1;
                aint[k][1] = j;
                aint[k][2] = l1;
                aint[k][3] = aint3[1];
                ++k;
            }
            --j;
            --i1;
        }
        this.leafNodes = new int[k][4];
        System.arraycopy(aint, 0, this.leafNodes, 0, k);
    }

    void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6) {
        int i1 = (int)((double)par4 + 0.618);
        byte b1 = otherCoordPairs[par5];
        byte b2 = otherCoordPairs[par5 + 3];
        int[] aint = new int[]{par1, par2, par3};
        int[] aint1 = new int[]{0, 0, 0};
        int k1 = -i1;
        aint1[par5] = aint[par5];
        for (int j1 = -i1; j1 <= i1; ++j1) {
            aint1[b1] = aint[b1] + j1;
            k1 = -i1;
            while (k1 <= i1) {
                double d0 = Math.pow((double)Math.abs(j1) + 0.5, 2.0) + Math.pow((double)Math.abs(k1) + 0.5, 2.0);
                if (d0 > (double)(par4 * par4)) {
                    ++k1;
                    continue;
                }
                aint1[b2] = aint[b2] + k1;
                try {
                    Block l1 = this.worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);
                    if (l1 != Blocks.field_150350_a && l1 != Blocks.field_150362_t) {
                        ++k1;
                        continue;
                    }
                    this.func_150516_a(this.worldObj, aint1[0], aint1[1], aint1[2], par6, 0);
                    ++k1;
                }
                catch (Exception e) {}
            }
        }
    }

    float layerSize(int par1) {
        if ((double)par1 < (double)this.heightLimit * 0.3) {
            return -1.618f;
        }
        float f = (float)this.heightLimit / 2.0f;
        float f1 = (float)this.heightLimit / 2.0f - (float)par1;
        float f2 = f1 == 0.0f ? f : (Math.abs(f1) >= f ? 0.0f : (float)Math.sqrt(Math.pow(Math.abs(f), 2.0) - Math.pow(Math.abs(f1), 2.0)));
        return f2 *= 0.5f;
    }

    float leafSize(int par1) {
        return par1 >= 0 && par1 < this.leafDistanceLimit ? (par1 != 0 && par1 != this.leafDistanceLimit - 1 ? 3.0f : 2.0f) : -1.0f;
    }

    void generateLeafNode(int par1, int par2, int par3) {
        int i1 = par2 + this.leafDistanceLimit;
        for (int l = par2; l < i1; ++l) {
            float f = this.leafSize(l - par2);
            this.genTreeLayer(par1, l, par3, f, (byte)1, (Block)Blocks.field_150362_t);
        }
    }

    void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3) {
        int[] aint2 = new int[]{0, 0, 0};
        int b1 = 0;
        for (int b0 = 0; b0 < 3; b0 = (int)((byte)(b0 + 1))) {
            aint2[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if (Math.abs(aint2[b0]) <= Math.abs(aint2[b1])) continue;
            b1 = b0;
        }
        if (aint2[b1] != 0) {
            byte b2 = otherCoordPairs[b1];
            byte b3 = otherCoordPairs[b1 + 3];
            int b4 = aint2[b1] > 0 ? 1 : -1;
            double d0 = (double)aint2[b2] / (double)aint2[b1];
            double d1 = (double)aint2[b3] / (double)aint2[b1];
            int[] aint3 = new int[]{0, 0, 0};
            int k = aint2[b1] + b4;
            for (int j = 0; j != k; j += b4) {
                int i1;
                aint3[b1] = MathHelper.func_76128_c((double)((double)(par1ArrayOfInteger[b1] + j) + 0.5));
                aint3[b2] = MathHelper.func_76128_c((double)((double)par1ArrayOfInteger[b2] + (double)j * d0 + 0.5));
                aint3[b3] = MathHelper.func_76128_c((double)((double)par1ArrayOfInteger[b3] + (double)j * d1 + 0.5));
                int b5 = 0;
                int l = Math.abs(aint3[0] - par1ArrayOfInteger[0]);
                int j1 = Math.max(l, i1 = Math.abs(aint3[2] - par1ArrayOfInteger[2]));
                if (j1 > 0) {
                    if (l == j1) {
                        b5 = 4;
                    } else if (i1 == j1) {
                        b5 = 8;
                    }
                }
                this.func_150516_a(this.worldObj, aint3[0], aint3[1], aint3[2], par3, b5);
            }
        }
    }

    void generateLeaves() {
        try {
            int j = this.leafNodes.length;
            for (int i = 0; i < j; ++i) {
                int k = this.leafNodes[i][0];
                int l = this.leafNodes[i][1];
                int i1 = this.leafNodes[i][2];
                this.generateLeafNode(k, l, i1);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    boolean leafNodeNeedsBase(int par1) {
        return (double)par1 >= (double)this.heightLimit * 0.2;
    }

    void generateTrunk() {
        int i = this.basePos[0];
        int j = this.basePos[1];
        int k = this.basePos[1] + this.height;
        int l = this.basePos[2];
        int[] aint = new int[]{i, j, l};
        int[] aint1 = new int[]{i, k, l};
        this.placeBlockLine(aint, aint1, Blocks.field_150364_r);
        if (this.trunkSize == 2) {
            aint[0] = aint[0] + 1;
            aint1[0] = aint1[0] + 1;
            this.placeBlockLine(aint, aint1, Blocks.field_150364_r);
            aint[2] = aint[2] + 1;
            aint1[2] = aint1[2] + 1;
            this.placeBlockLine(aint, aint1, Blocks.field_150364_r);
            aint[0] = aint[0] + -1;
            aint1[0] = aint1[0] + -1;
            this.placeBlockLine(aint, aint1, Blocks.field_150364_r);
        }
    }

    void generateLeafNodeBases() {
        int j = this.leafNodes.length;
        int[] aint = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        for (int i = 0; i < j; ++i) {
            int[] aint1 = this.leafNodes[i];
            int[] aint2 = new int[]{aint1[0], aint1[1], aint1[2]};
            aint[1] = aint1[3];
            int k = aint[1] - this.basePos[1];
            if (!this.leafNodeNeedsBase(k)) continue;
            this.placeBlockLine(aint, aint2, Blocks.field_150364_r);
        }
    }

    int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
        int i;
        int[] aint2 = new int[]{0, 0, 0};
        int b1 = 0;
        for (int b0 = 0; b0 < 3; b0 = (int)((byte)(b0 + 1))) {
            aint2[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if (Math.abs(aint2[b0]) <= Math.abs(aint2[b1])) continue;
            b1 = b0;
        }
        if (aint2[b1] == 0) {
            return -1;
        }
        byte b2 = otherCoordPairs[b1];
        byte b3 = otherCoordPairs[b1 + 3];
        int b4 = aint2[b1] > 0 ? 1 : -1;
        double d0 = (double)aint2[b2] / (double)aint2[b1];
        double d1 = (double)aint2[b3] / (double)aint2[b1];
        int[] aint3 = new int[]{0, 0, 0};
        int j = 0;
        try {
            j = aint2[b1] + b4;
            for (i = 0; i != j; i += b4) {
                aint3[b1] = par1ArrayOfInteger[b1] + i;
                aint3[b2] = MathHelper.func_76128_c((double)((double)par1ArrayOfInteger[b2] + (double)i * d0));
                aint3[b3] = MathHelper.func_76128_c((double)((double)par1ArrayOfInteger[b3] + (double)i * d1));
                Block k = this.worldObj.func_147439_a(aint3[0], aint3[1], aint3[2]);
                if (k == Blocks.field_150350_a || k == Blocks.field_150362_t) {
                    continue;
                }
                break;
            }
        }
        catch (Exception e) {
            // empty catch block
        }
        return i == j ? -1 : Math.abs(i);
    }

    boolean validTreeLocation() {
        boolean isValidSoil;
        int[] aint = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] aint1 = new int[]{this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        Block soil = this.worldObj.func_147439_a(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
        boolean bl = isValidSoil = soil != null && soil.canSustainPlant((IBlockAccess)this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2], ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.field_150345_g));
        if (!isValidSoil) {
            return false;
        }
        int j = this.checkBlockLine(aint, aint1);
        if (j == -1) {
            return true;
        }
        if (j < 6) {
            return false;
        }
        this.heightLimit = j;
        return true;
    }

    public void func_76487_a(double par1, double par3, double par5) {
        this.heightLimitLimit = (int)(par1 * 12.0);
        if (par1 > 0.5) {
            this.leafDistanceLimit = 3;
        }
        this.scaleWidth = par3;
        this.leafDensity = par5 * 0.8;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        this.worldObj = par1World;
        long l = par2Random.nextLong();
        this.rand.setSeed(l);
        this.basePos[0] = par3;
        this.basePos[1] = par4;
        this.basePos[2] = par5;
        if (this.heightLimit == 0) {
            this.heightLimit = 11 + this.rand.nextInt(this.heightLimitLimit);
        }
        if (!this.validTreeLocation()) {
            return false;
        }
        this.generateLeafNodeList();
        this.generateLeaves();
        this.generateTrunk();
        this.generateLeafNodeBases();
        return true;
    }
}

