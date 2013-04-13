package Collector.Resources;

/**
 * @Author <Manner>
 * - Edited
 */

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;

public class SkillCalculations {

    private int skill;
    private int startExp;
    private int startLvl;
    private static Timer t = null;

    public SkillCalculations(int skill) {
        this.skill = skill;
        this.startExp = Skills.getExperience(skill);
        this.startLvl = Skills.getRealLevel(skill);
        if (t == null) t = new Timer(0);
    }

    public int oresHour() {
        return (int) Math.round(Variables.oresMined * 3600000d / getTimeElapsed());
    }

    public int getCurrentExp() {
        return Skills.getExperience(skill);
    }

    public int getExpGained() {
        return Skills.getExperience(skill) - startExp;
    }

    public int getLevelsGained() {
        return Skills.getRealLevel(skill) - startLvl;
    }

    public int getExpPerHr() {
        return (int) ((getExpGained()) * 3600000D / t.getElapsed());
    }

    public int getLvl() {
        return Skills.getLevel(skill);
    }

    public String getTTL() {
        if (getExpGained() > 0)
            return Time
                    .format((long) ((Skills.getExperienceRequired(getLvl() + 1)
                            - getCurrentExp()) * 3600000D / getExpPerHr()));
        return "00:00:00";
    }

    public int getPercentToLvl() {
        int lastXPNeed = Skills.getExperienceRequired(getLvl());
        int nextXPNeed = Skills.getExperienceRequired(getLvl() + 1);
        double totalXPNeed = nextXPNeed - lastXPNeed;
        double gainFromLevel = getCurrentExp() - lastXPNeed;
        return (int) ((gainFromLevel / totalXPNeed) * 100);
    }

    public long getTimeElapsed() {
        return t.getElapsed();
    }

    public String getTimeElapsedAsString() {
        return t.toElapsedString();
    }

}
