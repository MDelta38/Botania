/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buildcraft.api.statements.IStatement
 *  buildcraft.api.statements.IStatementContainer
 *  buildcraft.api.statements.ITriggerExternal
 *  buildcraft.api.statements.ITriggerInternal
 *  buildcraft.api.statements.ITriggerProvider
 *  buildcraft.api.statements.StatementManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.integration.buildcraft;

import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.ITriggerExternal;
import buildcraft.api.statements.ITriggerInternal;
import buildcraft.api.statements.ITriggerProvider;
import buildcraft.api.statements.StatementManager;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.integration.buildcraft.TriggerManaDetector;
import vazkii.botania.common.integration.buildcraft.TriggerManaLevel;
import vazkii.botania.common.integration.buildcraft.TriggerRuneAltarCanCraft;

public class StatementAPIPlugin
implements ITriggerProvider {
    public static final ITriggerExternal triggerManaEmpty = new TriggerManaLevel(TriggerManaLevel.State.EMPTY);
    public static final ITriggerExternal triggerManaContains = new TriggerManaLevel(TriggerManaLevel.State.CONTAINS);
    public static final ITriggerExternal triggerManaSpace = new TriggerManaLevel(TriggerManaLevel.State.SPACE);
    public static final ITriggerExternal triggerManaFull = new TriggerManaLevel(TriggerManaLevel.State.FULL);
    public static final ITriggerInternal triggerManaDetector = new TriggerManaDetector();
    public static final ITriggerExternal triggerRuneAltarCanCraft = new TriggerRuneAltarCanCraft();

    public StatementAPIPlugin() {
        StatementManager.registerStatement((IStatement)triggerManaEmpty);
        StatementManager.registerStatement((IStatement)triggerManaContains);
        StatementManager.registerStatement((IStatement)triggerManaSpace);
        StatementManager.registerStatement((IStatement)triggerManaFull);
        StatementManager.registerStatement((IStatement)triggerManaDetector);
        StatementManager.registerStatement((IStatement)triggerRuneAltarCanCraft);
        StatementManager.registerTriggerProvider((ITriggerProvider)this);
    }

    public Collection<ITriggerInternal> getInternalTriggers(IStatementContainer container) {
        ArrayList<ITriggerInternal> list = new ArrayList<ITriggerInternal>();
        list.add(triggerManaDetector);
        return list;
    }

    public Collection<ITriggerExternal> getExternalTriggers(ForgeDirection side, TileEntity tile) {
        ArrayList<ITriggerExternal> list = new ArrayList<ITriggerExternal>();
        if (tile instanceof IManaBlock) {
            list.add(triggerManaEmpty);
            list.add(triggerManaContains);
            if (tile instanceof IManaReceiver) {
                list.add(triggerManaSpace);
                list.add(triggerManaFull);
            }
        }
        if (tile instanceof TileRuneAltar) {
            list.add(triggerRuneAltarCanCraft);
        }
        return list;
    }
}

