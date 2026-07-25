/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.WorldSavedData
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldSavedData;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.CellLoc;
import thaumcraft.common.lib.world.dim.GenCommon;
import thaumcraft.common.lib.world.dim.MapBossData;
import thaumcraft.common.lib.world.dim.MazeHandler;

public class TileEldritchLock
extends TileThaumcraft {
    public int count = -1;
    int[][] ped = new int[][]{{2, 2, 2}, {0, -1, 1}, {3, 3, 3}};
    byte facing = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.count != -1) {
            ++this.count;
            if (this.count % 5 == 0) {
                this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:pump", 1.0f, 1.0f);
            }
            if (this.count > 100) {
                this.doBossSpawn();
            }
        }
    }

    private void doBossSpawn() {
        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:ice", 1.0f, 1.0f);
        if (!this.field_145850_b.field_72995_K) {
            int cx = this.field_145851_c >> 4;
            int cz = this.field_145849_e >> 4;
            int centerx = this.field_145851_c >> 4;
            int centerz = this.field_145849_e >> 4;
            byte exit = 0;
            for (int a = -2; a <= 2; ++a) {
                for (int b = -2; b <= 2; ++b) {
                    Cell c = MazeHandler.getFromHashMap(new CellLoc(cx + a, cz + b));
                    if (c != null && c.feature == 2) {
                        centerx = cx + a;
                        centerz = cz + b;
                    }
                    if (c == null || c.feature < 2 || c.feature > 5 || !c.north && !c.south && !c.east && !c.west) continue;
                    exit = c.feature;
                }
            }
            MapBossData mbd = (MapBossData)this.field_145850_b.func_72943_a(MapBossData.class, "BossMapData");
            if (mbd == null) {
                mbd = new MapBossData("BossMapData");
                mbd.bossCount = 0;
                mbd.func_76185_a();
                this.field_145850_b.func_72823_a("BossMapData", (WorldSavedData)mbd);
            }
            ++mbd.bossCount;
            if (this.field_145850_b.field_73012_v.nextFloat() < 0.25f) {
                ++mbd.bossCount;
            }
            mbd.func_76185_a();
            switch (mbd.bossCount % 4) {
                case 0: {
                    this.spawnGolemBossRoom(centerx, centerz, exit);
                    break;
                }
                case 1: {
                    this.spawnWardenBossRoom(centerx, centerz, exit);
                    break;
                }
                case 2: {
                    this.spawnCultistBossRoom(centerx, centerz, exit);
                    break;
                }
                case 3: {
                    this.spawnTaintBossRoom(centerx, centerz, exit);
                }
            }
            for (int a = -2; a <= 2; ++a) {
                for (int b = -2; b <= 2; ++b) {
                    for (int c = -2; c <= 2; ++c) {
                        if (this.field_145850_b.func_147439_a(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c) != ConfigBlocks.blockAiry) continue;
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c, 0x400040), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)(this.field_145851_c + a), (double)(this.field_145848_d + b), (double)(this.field_145849_e + c), 32.0));
                        this.field_145850_b.func_147468_f(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c);
                    }
                }
            }
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    private void spawnWardenBossRoom(int cx, int cz, int exit) {
        int b;
        int a;
        for (int i = 0; i < this.field_145850_b.field_73010_i.size(); ++i) {
            EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
            if (!(ep.func_70092_e((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e) < 300.0)) continue;
            ep.func_145747_a((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"tc.boss.warden")));
        }
        int x = cx * 16 + 16;
        int y = 50;
        int z = cz * 16 + 16;
        int x2 = x;
        int z2 = z;
        switch (exit) {
            case 2: {
                x2 += 8;
                z2 += 8;
                break;
            }
            case 3: {
                x2 -= 8;
                z2 += 8;
                break;
            }
            case 4: {
                x2 += 8;
                z2 -= 8;
                break;
            }
            case 5: {
                x2 -= 8;
                z2 -= 8;
            }
        }
        GenCommon.genObelisk(this.field_145850_b, x2, y + 4, z);
        GenCommon.genObelisk(this.field_145850_b, x, y + 4, z2);
        this.field_145850_b.func_147465_d(x2, y + 2, z, ConfigBlocks.blockEldritch, 3, 3);
        this.field_145850_b.func_147465_d(x, y + 2, z2, ConfigBlocks.blockEldritch, 3, 3);
        for (a = -1; a <= 1; ++a) {
            for (b = -1; b <= 1; ++b) {
                int md;
                float rr;
                if (a != 0 && b != 0 && this.field_145850_b.field_73012_v.nextFloat() < 0.9f) {
                    rr = this.field_145850_b.field_73012_v.nextFloat();
                    md = rr < 0.1f ? 2 : (rr < 0.3f ? 1 : 0);
                    this.field_145850_b.func_147465_d(x2 + a, y + 2, z + b, ConfigBlocks.blockLootUrn, md, 3);
                }
                if (a == 0 || b == 0 || !(this.field_145850_b.field_73012_v.nextFloat() < 0.9f)) continue;
                rr = this.field_145850_b.field_73012_v.nextFloat();
                md = rr < 0.1f ? 2 : (rr < 0.3f ? 1 : 0);
                this.field_145850_b.func_147465_d(x + a, y + 2, z2 + b, ConfigBlocks.blockLootUrn, md, 3);
            }
        }
        this.field_145850_b.func_147465_d(x - 2, y + 3, z - 2, ConfigBlocks.blockEldritch, 10, 3);
        this.field_145850_b.func_147465_d(x - 2, y + 3, z + 2, ConfigBlocks.blockEldritch, 10, 3);
        this.field_145850_b.func_147465_d(x + 2, y + 3, z + 2, ConfigBlocks.blockEldritch, 10, 3);
        this.field_145850_b.func_147465_d(x + 2, y + 3, z - 2, ConfigBlocks.blockEldritch, 10, 3);
        this.field_145850_b.func_147465_d(x - 2, y + 2, z - 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
        this.field_145850_b.func_147465_d(x - 2, y + 2, z + 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
        this.field_145850_b.func_147465_d(x + 2, y + 2, z + 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
        this.field_145850_b.func_147465_d(x + 2, y + 2, z - 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
        for (a = 0; a < 3; ++a) {
            for (b = 0; b < 3; ++b) {
                if (this.ped[a][b] < 0) {
                    this.field_145850_b.func_147465_d(x2 - 1 + b, y + 2, z2 - 1 + a, ConfigBlocks.blockEldritch, 4, 3);
                    continue;
                }
                this.field_145850_b.func_147465_d(x2 - 1 + b, y + 2, z2 - 1 + a, ConfigBlocks.blockStairsEldritch, this.ped[a][b], 3);
            }
        }
        EntityEldritchWarden boss = new EntityEldritchWarden(this.field_145850_b);
        double d0 = (double)this.field_145851_c - ((double)x2 + 0.5);
        double d1 = (float)this.field_145848_d - ((float)(y + 3) + boss.func_70047_e());
        double d2 = (double)this.field_145849_e - ((double)z2 + 0.5);
        double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
        float f = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
        float f1 = (float)(-(Math.atan2(d1, d3) * 180.0 / Math.PI));
        boss.func_70012_b((double)x2 + 0.5, y + 3, (double)z2 + 0.5, f, f1);
        boss.func_110161_a(null);
        boss.func_110171_b(x, y + 2, z, 32);
        this.field_145850_b.func_72838_d((Entity)boss);
    }

    private void spawnGolemBossRoom(int cx, int cz, int exit) {
        int b;
        int a;
        for (int i = 0; i < this.field_145850_b.field_73010_i.size(); ++i) {
            EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
            if (!(ep.func_70092_e((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e) < 300.0)) continue;
            ep.func_145747_a((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"tc.boss.golem")));
        }
        int x = cx * 16 + 16;
        int y = 50;
        int z = cz * 16 + 16;
        int x2 = 0;
        int z2 = 0;
        switch (exit) {
            case 2: {
                x2 = 8;
                z2 = 8;
                break;
            }
            case 3: {
                x2 = -8;
                z2 = 8;
                break;
            }
            case 4: {
                x2 = 8;
                z2 = -8;
                break;
            }
            case 5: {
                x2 = -8;
                z2 = -8;
            }
        }
        GenCommon.genObelisk(this.field_145850_b, x + x2, y + 4, z + z2);
        GenCommon.genObelisk(this.field_145850_b, x - x2, y + 4, z + z2);
        GenCommon.genObelisk(this.field_145850_b, x + x2, y + 4, z - z2);
        this.field_145850_b.func_147465_d(x + x2, y + 2, z + z2, ConfigBlocks.blockEldritch, 3, 3);
        this.field_145850_b.func_147465_d(x - x2, y + 2, z + z2, ConfigBlocks.blockEldritch, 3, 3);
        this.field_145850_b.func_147465_d(x + x2, y + 2, z - z2, ConfigBlocks.blockEldritch, 3, 3);
        for (a = 0; a < 3; ++a) {
            for (b = 0; b < 3; ++b) {
                if (this.ped[a][b] < 0) {
                    this.field_145850_b.func_147465_d(x - 1 + b, y + 2, z - 1 + a, ConfigBlocks.blockEldritch, 4, 3);
                    continue;
                }
                this.field_145850_b.func_147465_d(x - 1 + b, y + 2, z - 1 + a, ConfigBlocks.blockStairsEldritch, this.ped[a][b], 3);
            }
        }
        for (a = -10; a <= 10; ++a) {
            for (b = -10; b <= 10; ++b) {
                if (!(a < -2 && b < -2 || a > 2 && b > 2 || a < -2 && b > 2) && (a <= 2 || b >= -2) || !(this.field_145850_b.field_73012_v.nextFloat() < 0.15f) || !this.field_145850_b.func_147437_c(x + a, y + 2, z + b)) continue;
                float rr = this.field_145850_b.field_73012_v.nextFloat();
                int md = rr < 0.05f ? 2 : (rr < 0.2f ? 1 : 0);
                this.field_145850_b.func_147465_d(x + a, y + 2, z + b, this.field_145850_b.field_73012_v.nextFloat() < 0.3f ? ConfigBlocks.blockLootCrate : ConfigBlocks.blockLootUrn, md, 3);
            }
        }
        EntityEldritchGolem boss = new EntityEldritchGolem(this.field_145850_b);
        double d0 = (double)this.field_145851_c - ((double)x + 0.5);
        double d1 = (float)this.field_145848_d - ((float)(y + 3) + boss.func_70047_e());
        double d2 = (double)this.field_145849_e - ((double)z + 0.5);
        double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
        float f = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
        float f1 = (float)(-(Math.atan2(d1, d3) * 180.0 / Math.PI));
        boss.func_70012_b((double)x + 0.5, y + 3, (double)z + 0.5, f, f1);
        boss.func_110161_a(null);
        this.field_145850_b.func_72838_d((Entity)boss);
    }

    private void spawnCultistBossRoom(int cx, int cz, int exit) {
        int b;
        int a;
        for (int i = 0; i < this.field_145850_b.field_73010_i.size(); ++i) {
            EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
            if (!(ep.func_70092_e((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e) < 300.0)) continue;
            ep.func_145747_a((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"tc.boss.crimson")));
        }
        int x = cx * 16 + 16;
        int y = 50;
        int z = cz * 16 + 16;
        for (a = -4; a <= 4; ++a) {
            for (b = -4; b <= 4; ++b) {
                if ((Math.abs(a) == 2 || Math.abs(b) == 2) && this.field_145850_b.field_73012_v.nextBoolean() || (Math.abs(a) == 3 || Math.abs(b) == 3) && this.field_145850_b.field_73012_v.nextFloat() > 0.33f || (Math.abs(a) == 4 || Math.abs(b) == 4) && this.field_145850_b.field_73012_v.nextFloat() > 0.25f) continue;
                this.field_145850_b.func_147465_d(x + b, y + 1, z + a, ConfigBlocks.blockEldritch, 7, 3);
            }
        }
        for (a = 0; a < 5; ++a) {
            for (b = 0; b < 5; ++b) {
                if (a != 0 && a != 4 && b != 0 && b != 4) continue;
                this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 2, z - 8 + a * 4, ConfigBlocks.blockCosmeticSolid, 11, 3);
                this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 3, z - 8 + a * 4, ConfigBlocks.blockEldritch, 5, 3);
                this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 4, z - 8 + a * 4, ConfigBlocks.blockSlabStone, 1, 3);
                this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 10, z - 8 + a * 4, ConfigBlocks.blockCosmeticSolid, 11, 3);
                this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 9, z - 8 + a * 4, ConfigBlocks.blockEldritch, 5, 3);
                this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 8, z - 8 + a * 4, ConfigBlocks.blockSlabStone, 9, 3);
            }
        }
        EntityCultistPortal boss = new EntityCultistPortal(this.field_145850_b);
        boss.func_70012_b((double)x + 0.5, y + 2, (double)z + 0.5, 0.0f, 0.0f);
        this.field_145850_b.func_72838_d((Entity)boss);
    }

    private void spawnTaintBossRoom(int cx, int cz, int exit) {
        for (int i = 0; i < this.field_145850_b.field_73010_i.size(); ++i) {
            EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
            if (!(ep.func_70092_e((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e) < 300.0)) continue;
            ep.func_145747_a((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"tc.boss.taint")));
        }
        int x = cx * 16 + 16;
        int y = 50;
        int z = cz * 16 + 16;
        for (int a = -12; a <= 12; ++a) {
            for (int b = -12; b <= 12; ++b) {
                Utils.setBiomeAt(this.field_145850_b, x + b, z + a, ThaumcraftWorldGenerator.biomeTaint);
                for (int c = 0; c < 9; ++c) {
                    if (!this.field_145850_b.func_147437_c(x + b, y + 2 + c, z + a) || !BlockUtils.isAdjacentToSolidBlock(this.field_145850_b, x + b, y + 2 + c, z + a) || this.field_145850_b.field_73012_v.nextInt(3) == 0) continue;
                    this.field_145850_b.func_147465_d(x + b, y + 2 + c, z + a, ConfigBlocks.blockTaintFibres, this.field_145850_b.field_73012_v.nextInt(4) == 0 ? 1 : 0, 3);
                }
                if ((double)this.field_145850_b.field_73012_v.nextFloat() < 0.15) {
                    this.field_145850_b.func_147465_d(x + b, y + 2, z + a, ConfigBlocks.blockTaint, 0, 3);
                    if ((double)this.field_145850_b.field_73012_v.nextFloat() < 0.2) {
                        this.field_145850_b.func_147465_d(x + b, y + 3, z + a, ConfigBlocks.blockTaint, 0, 3);
                    }
                }
                if ((Math.abs(a) == 4 || Math.abs(b) == 4) && this.field_145850_b.field_73012_v.nextBoolean() || (Math.abs(a) >= 5 || Math.abs(b) >= 5) && this.field_145850_b.field_73012_v.nextFloat() > 0.33f || (Math.abs(a) >= 7 || Math.abs(b) >= 7) && this.field_145850_b.field_73012_v.nextFloat() > 0.25f) continue;
                this.field_145850_b.func_147465_d(x + b, y + 1, z + a, ConfigBlocks.blockTaint, 1, 3);
            }
        }
        EntityTaintacle boss1 = this.field_145850_b.field_73013_u != EnumDifficulty.HARD ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
        boss1.func_70012_b((double)x + 0.5, y + 3, (double)z + 0.5, 0.0f, 0.0f);
        EntityUtils.makeChampion(boss1, true);
        this.field_145850_b.func_72838_d((Entity)boss1);
        EntityTaintacle boss2 = this.field_145850_b.field_73012_v.nextBoolean() ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
        boss2.func_70012_b((double)x + 3.5, y + 3, (double)z + 3.5, 0.0f, 0.0f);
        EntityUtils.makeChampion(boss2, true);
        this.field_145850_b.func_72838_d((Entity)boss2);
        EntityTaintacle boss3 = boss2 instanceof EntityTaintacleGiant ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
        boss3.func_70012_b((double)x - 2.5, y + 3, (double)z + 3.5, 0.0f, 0.0f);
        EntityUtils.makeChampion(boss3, true);
        this.field_145850_b.func_72838_d((Entity)boss3);
        EntityTaintacle boss4 = this.field_145850_b.field_73012_v.nextBoolean() ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
        boss4.func_70012_b((double)x + 3.5, y + 3, (double)z - 2.5, 0.0f, 0.0f);
        EntityUtils.makeChampion(boss4, true);
        this.field_145850_b.func_72838_d((Entity)boss4);
        EntityTaintacle boss5 = boss4 instanceof EntityTaintacleGiant ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
        boss5.func_70012_b((double)x - 2.5, y + 3, (double)z - 2.5, 0.0f, 0.0f);
        EntityUtils.makeChampion(boss5, true);
        this.field_145850_b.func_72838_d((Entity)boss5);
    }

    public double func_145833_n() {
        return 9216.0;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c - 2.25), (double)((double)this.field_145848_d - 2.25), (double)((double)this.field_145849_e - 2.25), (double)((double)this.field_145851_c + 3.25), (double)((double)this.field_145848_d + 3.25), (double)((double)this.field_145849_e + 3.25));
    }

    public byte getFacing() {
        return this.facing;
    }

    public void setFacing(byte face) {
        this.facing = face;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = nbttagcompound.func_74771_c("facing");
        this.count = nbttagcompound.func_74765_d("count");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("facing", this.facing);
        nbttagcompound.func_74777_a("count", (short)this.count);
    }
}

