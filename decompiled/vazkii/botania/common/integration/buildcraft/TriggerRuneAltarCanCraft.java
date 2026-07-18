/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buildcraft.api.statements.IStatementContainer
 *  buildcraft.api.statements.IStatementParameter
 *  buildcraft.api.statements.ITriggerExternal
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.integration.buildcraft;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerExternal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.integration.buildcraft.StatementBase;

public class TriggerRuneAltarCanCraft
extends StatementBase
implements ITriggerExternal {
    public String getUniqueTag() {
        return "botania:runeAltarCanCraft";
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.icon = IconHelper.forName(iconRegister, "triggers/runeAltarCanCraft");
    }

    public String getDescription() {
        return StatCollector.func_74838_a((String)"botania.triggers.runeAltarCanCraft");
    }

    public boolean isTriggerActive(TileEntity target, ForgeDirection side, IStatementContainer source, IStatementParameter[] parameters) {
        if (target instanceof TileRuneAltar) {
            return ((TileRuneAltar)target).hasValidRecipe();
        }
        return false;
    }
}

