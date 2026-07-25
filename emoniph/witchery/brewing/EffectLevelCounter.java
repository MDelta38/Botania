/*
 * Decompiled with CFR 0.152.
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.brewing.EffectLevel;

public class EffectLevelCounter {
    private int maxLevel;
    private int currentLevel;
    private int effects;

    public void increaseAvailableLevelIf(EffectLevel incement, EffectLevel ceilingLevel) {
        if (this.maxLevel < ceilingLevel.getLevel()) {
            this.maxLevel += incement.getLevel();
        }
    }

    public int remainingCapactiy() {
        return this.maxLevel - this.currentLevel;
    }

    public int usedCapacity() {
        return this.currentLevel;
    }

    public int getEffectCount() {
        return this.effects;
    }

    public boolean tryConsumeLevel(EffectLevel level) {
        if (this.canConsumeLevel(level)) {
            this.currentLevel += level.getLevel();
            ++this.effects;
            return true;
        }
        return false;
    }

    public boolean canConsumeLevel(EffectLevel level) {
        return level.getLevel() + this.currentLevel <= this.maxLevel;
    }

    public boolean hasEffects() {
        return this.currentLevel > 0;
    }

    public boolean canIncreasePlayerSkill(int currentSkillLevel) {
        if (this.currentLevel > this.maxLevel || this.maxLevel == 0) {
            return false;
        }
        if (currentSkillLevel < 30) {
            return this.maxLevel > 1 && this.currentLevel > 1;
        }
        if (currentSkillLevel < 60) {
            return this.maxLevel >= 4 && this.currentLevel >= 4;
        }
        if (currentSkillLevel < 90) {
            return this.maxLevel >= 6 && this.currentLevel >= 6;
        }
        if (currentSkillLevel != 100) {
            return this.maxLevel >= 8 && this.currentLevel >= 8;
        }
        return false;
    }
}

