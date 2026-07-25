/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.events;

public class MENetworkEvent {
    private int visited = 0;
    private boolean canceled = false;

    public void cancel() {
        this.canceled = true;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public int getVisitedObjects() {
        return this.visited;
    }

    public void setVisitedObjects(int v) {
        this.visited = v;
    }
}

