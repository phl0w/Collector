package org.dracula.collector.nodes;

import org.dracula.collector.resources.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mining extends Node {

    private static SceneObject rock = null;

    @Override
    public boolean activate() {
        return !Inventory.isFull() && (Players.getLocal().getAnimation() == -1 && (rock == null ?
                (rock = SceneEntities.getNearest(Variables.location.getIds(Variables.ore))) : rock) != null) &&
                rock.getLocation().distanceTo() < 9;
    }

    @Override
    public void execute() {
        System.out.println("Mine");
        if (rock.isOnScreen()) {
            if (rock.interact("Mine")) {
                final Timer t = new Timer(3000);
                while (t.isRunning() && Players.getLocal().getAnimation() == -1)
                    Task.sleep(50);
            }
        } else {
            Camera.turnTo(rock);
        }
    }
}

