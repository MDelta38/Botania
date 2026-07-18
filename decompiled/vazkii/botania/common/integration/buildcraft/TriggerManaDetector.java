/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buildcraft.api.statements.IStatementContainer
 *  buildcraft.api.statements.IStatementParameter
 *  buildcraft.api.statements.ITriggerInternal
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.integration.buildcraft;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerInternal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.integration.buildcraft.StatementBase;

public class TriggerManaDetector
extends StatementBase
implements ITriggerInternal {
    public String getUniqueTag() {
        return "botania:manaDetector";
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.icon = IconHelper.forName(iconRegister, "triggers/manaDetector");
    }

    public String getDescription() {
        return StatCollector.func_74838_a((String)"botania.triggers.manaDetector");
    }

    public boolean isTriggerActive(IStatementContainer source, IStatementParameter[] parameters) {
        int z;
        int y;
        int x;
        boolean output;
        World world = source.getTile().func_145831_w();
        boolean bl = output = world.func_72872_a(IManaBurst.class, AxisAlignedBB.func_72330_a((double)(x = source.getTile().field_145851_c), (double)(y = source.getTile().field_145848_d), (double)(z = source.getTile().field_145849_e), (double)(x + 1), (double)(y + 1), (double)(z + 1))).size() != 0;
        if (output) {
            for (int i = 0; i < 4; ++i) {
                Botania.proxy.sparkleFX(world, (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 1.0f, 0.2f, 0.2f, 0.7f + 0.5f * (float)Math.random(), 5);
            }
        }
        return output;
    }
}

