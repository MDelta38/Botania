/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package drunkmafia.thaumicinfusion.common.asm;

import drunkmafia.thaumicinfusion.common.asm.IMethod;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.ClassNode;

public class Interface {
    private final String className;
    private final List<IMethod> methods = new ArrayList<IMethod>();

    public Interface(String className) {
        this.className = className;
    }

    public void addMethod(IMethod method) {
        this.methods.add(method);
    }

    public void injectMethodsIntoClass(ClassNode node) {
        node.interfaces.add(this.className);
        for (IMethod method : this.methods) {
            node.methods.add(method.getMethodNode(node.name));
        }
    }

    public List<IMethod> getMethods() {
        return this.methods;
    }

    public String getClassName() {
        return this.className;
    }
}

