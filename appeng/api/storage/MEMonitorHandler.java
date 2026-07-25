/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package appeng.api.storage;

import appeng.api.config.AccessRestriction;
import appeng.api.config.Actionable;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.IMEMonitorHandlerReceiver;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MEMonitorHandler<StackType extends IAEStack>
implements IMEMonitor<StackType> {
    private final IMEInventoryHandler<StackType> internalHandler;
    private final IItemList<StackType> cachedList;
    private final HashMap<IMEMonitorHandlerReceiver<StackType>, Object> listeners = new HashMap();
    protected boolean hasChanged = true;

    protected IMEInventoryHandler<StackType> getHandler() {
        return this.internalHandler;
    }

    protected Iterator<Map.Entry<IMEMonitorHandlerReceiver<StackType>, Object>> getListeners() {
        return this.listeners.entrySet().iterator();
    }

    protected void postChangesToListeners(Iterable<StackType> changes, BaseActionSource src) {
        this.notifyListenersOfChange(changes, src);
    }

    protected void notifyListenersOfChange(Iterable<StackType> diff, BaseActionSource src) {
        this.hasChanged = true;
        Iterator<Map.Entry<IMEMonitorHandlerReceiver<StackType>, Object>> i = this.getListeners();
        while (i.hasNext()) {
            Map.Entry<IMEMonitorHandlerReceiver<StackType>, Object> o = i.next();
            IMEMonitorHandlerReceiver<StackType> receiver = o.getKey();
            if (receiver.isValid(o.getValue())) {
                receiver.postChange(this, diff, src);
                continue;
            }
            i.remove();
        }
    }

    private StackType monitorDifference(IAEStack original, StackType leftOvers, boolean extraction, BaseActionSource src) {
        Object diff = original.copy();
        if (extraction) {
            diff.setStackSize(leftOvers == null ? 0L : -leftOvers.getStackSize());
        } else if (leftOvers != null) {
            diff.decStackSize(leftOvers.getStackSize());
        }
        if (diff.getStackSize() != 0L) {
            this.postChangesToListeners((Iterable<StackType>)ImmutableList.of(diff), src);
        }
        return leftOvers;
    }

    public MEMonitorHandler(IMEInventoryHandler<StackType> t) {
        this.internalHandler = t;
        this.cachedList = t.getChannel().createList();
    }

    public MEMonitorHandler(IMEInventoryHandler<StackType> t, StorageChannel chan) {
        this.internalHandler = t;
        this.cachedList = chan.createList();
    }

    @Override
    public void addListener(IMEMonitorHandlerReceiver<StackType> l, Object verificationToken) {
        this.listeners.put(l, verificationToken);
    }

    @Override
    public void removeListener(IMEMonitorHandlerReceiver<StackType> l) {
        this.listeners.remove(l);
    }

    @Override
    public StackType injectItems(StackType input, Actionable mode, BaseActionSource src) {
        if (mode == Actionable.SIMULATE) {
            return this.getHandler().injectItems(input, mode, src);
        }
        return this.monitorDifference((IAEStack)input.copy(), this.getHandler().injectItems(input, mode, src), false, src);
    }

    @Override
    public StackType extractItems(StackType request, Actionable mode, BaseActionSource src) {
        if (mode == Actionable.SIMULATE) {
            return this.getHandler().extractItems(request, mode, src);
        }
        return this.monitorDifference((IAEStack)request.copy(), this.getHandler().extractItems(request, mode, src), true, src);
    }

    @Override
    public IItemList<StackType> getStorageList() {
        if (this.hasChanged) {
            this.hasChanged = false;
            this.cachedList.resetStatus();
            return this.getAvailableItems((IItemList)this.cachedList);
        }
        return this.cachedList;
    }

    @Override
    public IItemList<StackType> getAvailableItems(IItemList out) {
        return this.getHandler().getAvailableItems(out);
    }

    @Override
    public StorageChannel getChannel() {
        return this.getHandler().getChannel();
    }

    @Override
    public AccessRestriction getAccess() {
        return this.getHandler().getAccess();
    }

    @Override
    public boolean isPrioritized(StackType input) {
        return this.getHandler().isPrioritized(input);
    }

    @Override
    public boolean canAccept(StackType input) {
        return this.getHandler().canAccept(input);
    }

    @Override
    public int getPriority() {
        return this.getHandler().getPriority();
    }

    @Override
    public int getSlot() {
        return this.getHandler().getSlot();
    }

    @Override
    public boolean validForPass(int i) {
        return this.getHandler().validForPass(i);
    }
}

