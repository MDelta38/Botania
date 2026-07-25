/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.ClassPath
 *  com.google.common.reflect.ClassPath$ClassInfo
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 */
package thaumic.tinkerer.common.registry;

import com.google.common.reflect.ClassPath;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.registry.IMultiTileEntityBlock;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ITTinkererRegisterable;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class TTRegistry {
    private ArrayList<Class> itemClasses = new ArrayList();
    private HashMap<Class, ArrayList<Item>> itemRegistry = new HashMap();
    private ArrayList<Class> blockClasses = new ArrayList();
    private HashMap<Class, ArrayList<Block>> blockRegistry = new HashMap();

    public void registerClasses() {
        try {
            ClassPath classPath = ClassPath.from((ClassLoader)this.getClass().getClassLoader());
            for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive("thaumic.tinkerer.common.block")) {
                if (!ITTinkererBlock.class.isAssignableFrom(classInfo.load()) || Modifier.isAbstract(classInfo.load().getModifiers())) continue;
                this.blockClasses.add(classInfo.load());
            }
            for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive("thaumic.tinkerer.common.item")) {
                if (!ITTinkererItem.class.isAssignableFrom(classInfo.load()) || ItemBlock.class.isAssignableFrom(classInfo.load()) || Modifier.isAbstract(classInfo.load().getModifiers())) continue;
                this.itemClasses.add(classInfo.load());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerResearch(ITTinkererRegisterable nextItem) {
        IRegisterableResearch registerableResearch = nextItem.getResearchItem();
        if (registerableResearch != null) {
            registerableResearch.registerResearch();
        }
    }

    public void registerRecipe(ITTinkererRegisterable nextItem) {
        ThaumicTinkererRecipe thaumicTinkererRecipe = nextItem.getRecipeItem();
        if (thaumicTinkererRecipe != null) {
            thaumicTinkererRecipe.registerRecipe();
        }
    }

    public void preInit() {
        this.registerClasses();
        for (Class clazz : this.blockClasses) {
            try {
                Block newBlock = (Block)clazz.newInstance();
                if (!((ITTinkererBlock)newBlock).shouldRegister()) continue;
                newBlock.func_149663_c(((ITTinkererBlock)newBlock).getBlockName());
                ArrayList<Block> blockList = new ArrayList<Block>();
                blockList.add(newBlock);
                if (newBlock == null) {
                    ThaumicTinkerer.log.debug(clazz.getName() + " Returned a null block upon registration");
                    continue;
                }
                if (((ITTinkererBlock)newBlock).getSpecialParameters() != null) {
                    block10: for (Object object : ((ITTinkererBlock)newBlock).getSpecialParameters()) {
                        for (Constructor<?> constructor : clazz.getConstructors()) {
                            if (constructor.getParameterTypes().length <= 0 || !constructor.getParameterTypes()[0].isAssignableFrom(object.getClass())) continue;
                            Block nextBlock = (Block)clazz.getConstructor(object.getClass()).newInstance(object);
                            nextBlock.func_149663_c(((ITTinkererBlock)nextBlock).getBlockName());
                            blockList.add(nextBlock);
                            continue block10;
                        }
                    }
                }
                this.blockRegistry.put(clazz, blockList);
                if (((ITTinkererBlock)newBlock).getItemBlock() == null) continue;
                Item newItem = (Item)((ITTinkererBlock)newBlock).getItemBlock().getConstructor(Block.class).newInstance(newBlock);
                newItem.func_77655_b(((ITTinkererItem)newItem).getItemName());
                ArrayList<Item> arrayList = new ArrayList<Item>();
                arrayList.add(newItem);
                this.itemRegistry.put(((ITTinkererBlock)newBlock).getItemBlock(), arrayList);
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (Class clazz : this.itemClasses) {
            try {
                Item newItem = (Item)clazz.newInstance();
                if (!((ITTinkererItem)newItem).shouldRegister()) continue;
                newItem.func_77655_b(((ITTinkererItem)newItem).getItemName());
                ArrayList<Item> itemList = new ArrayList<Item>();
                itemList.add(newItem);
                if (newItem == null) {
                    ThaumicTinkerer.log.debug(clazz.getName() + " Returned a null item upon registration");
                    continue;
                }
                if (((ITTinkererItem)newItem).getSpecialParameters() != null) {
                    block13: for (Object object : ((ITTinkererItem)newItem).getSpecialParameters()) {
                        for (Constructor<?> constructor : clazz.getConstructors()) {
                            if (constructor.getParameterTypes().length <= 0 || !constructor.getParameterTypes()[0].isAssignableFrom(object.getClass())) continue;
                            Item nextItem = (Item)constructor.newInstance(object);
                            nextItem.func_77655_b(((ITTinkererItem)nextItem).getItemName());
                            itemList.add(nextItem);
                            continue block13;
                        }
                    }
                }
                this.itemRegistry.put(clazz, itemList);
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (ArrayList arrayList : this.blockRegistry.values()) {
            for (Block block : arrayList) {
                if (((ITTinkererBlock)block).getItemBlock() != null) {
                    GameRegistry.registerBlock((Block)block, ((ITTinkererBlock)block).getItemBlock(), (String)((ITTinkererBlock)block).getBlockName());
                } else {
                    GameRegistry.registerBlock((Block)block, (String)((ITTinkererBlock)block).getBlockName());
                }
                if (((ITTinkererBlock)block).getTileEntity() != null) {
                    GameRegistry.registerTileEntity(((ITTinkererBlock)block).getTileEntity(), (String)("ttinkerer:" + ((ITTinkererBlock)block).getBlockName()));
                }
                if (block instanceof IMultiTileEntityBlock) {
                    for (Map.Entry entry : ((IMultiTileEntityBlock)block).getAdditionalTileEntities().entrySet()) {
                        GameRegistry.registerTileEntity((Class)((Class)entry.getKey()), (String)((String)entry.getValue()));
                    }
                }
                if (!((ITTinkererBlock)block).shouldDisplayInTab() || FMLCommonHandler.instance().getSide() != Side.CLIENT) continue;
                ModCreativeTab.INSTANCE.addBlock(block);
            }
        }
    }

    public ArrayList<Item> getItemFromClass(Class clazz) {
        return this.itemRegistry.get(clazz);
    }

    public Item getFirstItemFromClass(Class clazz) {
        return this.itemRegistry.get(clazz) != null ? this.itemRegistry.get(clazz).get(0) : null;
    }

    public Item getItemFromClassAndName(Class clazz, String s) {
        if (this.itemRegistry.get(clazz) == null) {
            return null;
        }
        for (Item i : this.getItemFromClass(clazz)) {
            if (!((ITTinkererItem)i).getItemName().equals(s)) continue;
            return i;
        }
        return null;
    }

    public Block getBlockFromClassAndName(Class clazz, String s) {
        if (this.blockRegistry.get(clazz) == null) {
            return null;
        }
        for (Block i : this.getBlockFromClass(clazz)) {
            if (!((ITTinkererBlock)i).getBlockName().equals(s)) continue;
            return i;
        }
        return null;
    }

    public ArrayList<Block> getBlockFromClass(Class clazz) {
        return this.blockRegistry.get(clazz);
    }

    public Block getFirstBlockFromClass(Class clazz) {
        return this.blockRegistry.get(clazz) != null ? this.blockRegistry.get(clazz).get(0) : null;
    }

    public void init() {
        for (ArrayList<Item> arrayList : this.itemRegistry.values()) {
            for (Item item : arrayList) {
                this.registerRecipe((ITTinkererRegisterable)item);
            }
        }
        for (ArrayList<Item> arrayList : this.blockRegistry.values()) {
            for (Block block : arrayList) {
                this.registerRecipe((ITTinkererRegisterable)block);
            }
        }
        for (ArrayList<Item> arrayList : this.itemRegistry.values()) {
            for (Item item : arrayList) {
                if (item instanceof ItemBlock) continue;
                GameRegistry.registerItem((Item)item, (String)((ITTinkererItem)item).getItemName());
                if (!((ITTinkererItem)item).shouldDisplayInTab() || FMLCommonHandler.instance().getSide() != Side.CLIENT) continue;
                ModCreativeTab.INSTANCE.addItem(item);
            }
        }
        ModCreativeTab.INSTANCE.addAllItemsAndBlocks();
    }

    public void postInit() {
        for (ArrayList<Item> arrayList : this.itemRegistry.values()) {
            for (Item item : arrayList) {
                this.registerResearch((ITTinkererRegisterable)item);
            }
        }
        for (ArrayList<Item> arrayList : this.blockRegistry.values()) {
            for (Block block : arrayList) {
                this.registerResearch((ITTinkererRegisterable)block);
            }
        }
    }
}

