/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityTNTPrimed
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.List;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileEntropinnyum
extends SubTileGenerating {
    private static final int RANGE = 12;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mana == 0) {
            List tnts = this.supertile.func_145831_w().func_72872_a(EntityTNTPrimed.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 12), (double)(this.supertile.field_145848_d - 12), (double)(this.supertile.field_145849_e - 12), (double)(this.supertile.field_145851_c + 12 + 1), (double)(this.supertile.field_145848_d + 12 + 1), (double)(this.supertile.field_145849_e + 12 + 1)));
            for (EntityTNTPrimed tnt : tnts) {
                if (tnt.field_70516_a != 1 || tnt.field_70128_L || this.supertile.func_145831_w().func_147439_a(MathHelper.func_76128_c((double)tnt.field_70165_t), MathHelper.func_76128_c((double)tnt.field_70163_u), MathHelper.func_76128_c((double)tnt.field_70161_v)).func_149688_o().func_76224_d()) continue;
                if (!this.supertile.func_145831_w().field_72995_K) {
                    tnt.func_70106_y();
                    this.mana += this.getMaxMana();
                    this.supertile.func_145831_w().func_72908_a(tnt.field_70165_t, tnt.field_70163_u, tnt.field_70161_v, "random.explode", 0.2f, (1.0f + (this.supertile.func_145831_w().field_73012_v.nextFloat() - this.supertile.func_145831_w().field_73012_v.nextFloat()) * 0.2f) * 0.7f);
                    this.sync();
                }
                for (int i = 0; i < 50; ++i) {
                    Botania.proxy.sparkleFX(tnt.field_70170_p, tnt.field_70165_t + Math.random() * 4.0 - 2.0, tnt.field_70163_u + Math.random() * 4.0 - 2.0, tnt.field_70161_v + Math.random() * 4.0 - 2.0, 1.0f, (float)Math.random() * 0.25f, (float)Math.random() * 0.25f, (float)(Math.random() * (double)0.65f + 1.25), 12);
                }
                this.supertile.func_145831_w().func_72869_a("hugeexplosion", tnt.field_70165_t, tnt.field_70163_u, tnt.field_70161_v, 1.0, 0.0, 0.0);
                return;
            }
        }
    }

    @Override
    public int getColor() {
        return 0xCB0000;
    }

    @Override
    public int getMaxMana() {
        return 6500;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 12);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.entropinnyum;
    }
}

