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
 *  org.apache.commons.lang3.text.WordUtils
 */
package vazkii.botania.common.integration.buildcraft;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerExternal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.commons.lang3.text.WordUtils;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.integration.buildcraft.StatementBase;

public class TriggerManaLevel
extends StatementBase
implements ITriggerExternal {
    private State state;

    public TriggerManaLevel(State state) {
        this.state = state;
    }

    public String getUniqueTag() {
        return "botania:mana" + this.state.name();
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.icon = IconHelper.forName(iconRegister, "triggers/mana" + WordUtils.capitalizeFully((String)this.state.name()));
    }

    public String getDescription() {
        return StatCollector.func_74838_a((String)("botania.triggers.mana" + WordUtils.capitalizeFully((String)this.state.name())));
    }

    public boolean isTriggerActive(TileEntity target, ForgeDirection side, IStatementContainer source, IStatementParameter[] parameters) {
        if (target instanceof IManaBlock) {
            if (this.state == State.EMPTY) {
                return ((IManaBlock)target).getCurrentMana() == 0;
            }
            if (this.state == State.CONTAINS) {
                return ((IManaBlock)target).getCurrentMana() > 0;
            }
            if (target instanceof IManaReceiver) {
                if (this.state == State.SPACE) {
                    return !((IManaReceiver)target).isFull();
                }
                if (this.state == State.FULL) {
                    return ((IManaReceiver)target).isFull();
                }
            }
        }
        return false;
    }

    public static enum State {
        EMPTY,
        CONTAINS,
        SPACE,
        FULL;

    }
}

