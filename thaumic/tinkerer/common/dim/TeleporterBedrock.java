/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.WorldServer
 */
package thaumic.tinkerer.common.dim;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterBedrock
extends Teleporter {
    public TeleporterBedrock(WorldServer w) {
        super(w);
    }

    public void func_85189_a(long par1) {
        super.func_85189_a(par1);
    }

    public boolean func_85188_a(Entity par1Entity) {
        return true;
    }

    public boolean func_77184_b(Entity entity, double par2, double par4, double par6, float par8) {
        return true;
    }

    public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8) {
    }
}

