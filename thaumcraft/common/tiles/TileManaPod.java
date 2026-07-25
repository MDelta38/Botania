/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.lib.research.ResearchManager;

public class TileManaPod
extends TileThaumcraft
implements IAspectContainer {
    public Aspect aspect = null;

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("aspect"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) {
            nbttagcompound.func_74778_a("aspect", this.aspect.getTag());
        }
    }

    public void checkGrowth() {
        int l = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (l < 7) {
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ++l, 3);
        }
        if (l > 2) {
            if (l == 3) {
                AspectList al = new AspectList();
                if (this.aspect != null) {
                    al.add(this.aspect, 1);
                }
                for (int d = 2; d < 6; ++d) {
                    ForgeDirection dir = ForgeDirection.getOrientation((int)d);
                    int x = this.field_145851_c + dir.offsetX;
                    int y = this.field_145848_d + dir.offsetY;
                    int z = this.field_145849_e + dir.offsetZ;
                    TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
                    if (tile == null || !(tile instanceof TileManaPod) || ((TileManaPod)tile).aspect == null) continue;
                    al.add(((TileManaPod)tile).aspect, 1);
                }
                if (al.size() > 1) {
                    Aspect[] aa = al.getAspects();
                    ArrayList<Aspect> outlist = new ArrayList<Aspect>();
                    for (int i = 0; i < aa.length; ++i) {
                        outlist.add(aa[i]);
                        for (int j = 0; j < aa.length; ++j) {
                            Aspect combo;
                            if (i == j || (combo = ResearchManager.getCombinationResult(aa[i], aa[j])) == null) continue;
                            outlist.add(combo);
                            outlist.add(combo);
                        }
                    }
                    if (outlist.size() > 0) {
                        this.aspect = (Aspect)outlist.get(this.field_145850_b.field_73012_v.nextInt(outlist.size()));
                        this.func_70296_d();
                    }
                }
                if (al.size() >= 1 && this.aspect == null) {
                    this.aspect = al.getAspectsSortedAmount()[0];
                    this.func_70296_d();
                }
            }
            if (this.aspect == null) {
                if (this.field_145850_b.field_73012_v.nextInt(8) == 0) {
                    this.aspect = Aspect.PLANT;
                } else {
                    ArrayList<Aspect> outlist = Aspect.getPrimalAspects();
                    this.aspect = outlist.get(this.field_145850_b.field_73012_v.nextInt(outlist.size()));
                }
                this.func_70296_d();
            }
        }
    }

    @Override
    public AspectList getAspects() {
        return this.aspect != null && this.func_145832_p() == 7 ? new AspectList().add(this.aspect, 1) : null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }
}

