/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.LongHashMap
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.Teleporter$PortalPosition
 *  net.minecraft.world.WorldServer
 */
package thaumcraft.common.lib.world.dim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import thaumcraft.common.config.ConfigBlocks;

public class TeleporterThaumcraft
extends Teleporter {
    private final WorldServer worldServerInstance;
    private final Random random;
    private static final LongHashMap destinationCoordinateCache = new LongHashMap();
    private static final List destinationCoordinateKeys = new ArrayList();

    public TeleporterThaumcraft(WorldServer par1WorldServer) {
        super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random(par1WorldServer.func_72905_C());
    }

    public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8) {
        if (this.worldServerInstance.field_73011_w.field_76574_g != 1) {
            if (!this.func_77184_b(par1Entity, par2, par4, par6, par8)) {
                this.func_85188_a(par1Entity);
                this.func_77184_b(par1Entity, par2, par4, par6, par8);
            }
        } else if (!this.func_77184_b(par1Entity, par2, par4, par6, par8)) {
            int i = MathHelper.func_76128_c((double)par1Entity.field_70165_t);
            int k = MathHelper.func_76128_c((double)par1Entity.field_70161_v);
            int j = this.worldServerInstance.func_72976_f(i, k);
            boolean b0 = true;
            boolean b1 = false;
            par1Entity.func_70012_b((double)i, (double)j + 4.0, (double)k, par1Entity.field_70177_z, 0.0f);
            par1Entity.field_70179_y = 0.0;
            par1Entity.field_70181_x = 0.0;
            par1Entity.field_70159_w = 0.0;
        }
    }

    public boolean func_77184_b(Entity par1Entity, double par2, double par4, double par6, float par8) {
        double d4;
        int short1 = 128;
        double d3 = -1.0;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.func_76128_c((double)par1Entity.field_70165_t);
        int i1 = MathHelper.func_76128_c((double)par1Entity.field_70161_v);
        int chunkX = l >> 4;
        int chunkZ = i1 >> 4;
        String hs = chunkX + ":" + chunkZ + ":" + this.worldServerInstance.field_73011_w.field_76574_g;
        long j1 = hs.hashCode();
        boolean flag = true;
        if (destinationCoordinateCache.func_76161_b(j1)) {
            Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)destinationCoordinateCache.func_76164_a(j1);
            d3 = 0.0;
            i = portalposition.field_71574_a;
            j = portalposition.field_71572_b;
            k = portalposition.field_71573_c;
            portalposition.field_85087_d = this.worldServerInstance.func_82737_E();
            flag = false;
        } else {
            for (int k1 = l - short1; k1 <= l + short1; ++k1) {
                double d5 = (double)k1 + 0.5 - par1Entity.field_70165_t;
                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1) {
                    double d6 = (double)l1 + 0.5 - par1Entity.field_70161_v;
                    for (int i2 = this.worldServerInstance.func_72940_L() - 1; i2 >= 0; --i2) {
                        if (this.worldServerInstance.func_147439_a(k1, i2, l1) != ConfigBlocks.blockEldritchPortal) continue;
                        d4 = (double)i2 + 0.5 - par1Entity.field_70163_u;
                        double d7 = d5 * d5 + d4 * d4 + d6 * d6;
                        if (!(d3 < 0.0) && !(d7 < d3)) continue;
                        d3 = d7;
                        i = k1;
                        j = i2;
                        k = l1;
                    }
                }
            }
        }
        if (d3 >= 0.0) {
            if (flag) {
                destinationCoordinateCache.func_76163_a(j1, (Object)new Teleporter.PortalPosition((Teleporter)this, i, j, k, this.worldServerInstance.func_82737_E()));
                destinationCoordinateKeys.add(j1);
            }
            double d8 = (double)i + 0.5 + (double)(this.worldServerInstance.field_73012_v.nextBoolean() ? 1 : -1);
            double d9 = j;
            d4 = (double)k + 0.5 + (double)(this.worldServerInstance.field_73012_v.nextBoolean() ? 1 : -1);
            par1Entity.field_70179_y = 0.0;
            par1Entity.field_70181_x = 0.0;
            par1Entity.field_70159_w = 0.0;
            par1Entity.func_70012_b(d8, d9, d4, par1Entity.field_70177_z, par1Entity.field_70125_A);
            return true;
        }
        return false;
    }

    public boolean func_85188_a(Entity par1Entity) {
        return true;
    }

    public void func_85189_a(long par1) {
        if (par1 % 100L == 0L) {
            Iterator iterator = destinationCoordinateKeys.iterator();
            long j = par1 - 600L;
            while (iterator.hasNext()) {
                Long olong = (Long)iterator.next();
                Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)destinationCoordinateCache.func_76164_a(olong.longValue());
                if (portalposition != null && portalposition.field_85087_d >= j) continue;
                iterator.remove();
                destinationCoordinateCache.func_76159_d(olong.longValue());
            }
        }
    }
}

