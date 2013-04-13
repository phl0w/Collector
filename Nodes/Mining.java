package Collector.Nodes;

import Collector.Resources.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.methods.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mining extends Node {

    SceneObject rock = null;
    Timer animationCheck = new Timer(5000);

    @Override
    public boolean activate() {
        if (Players.getLocal().getAnimation() == -1) {
            if (!animationCheck.isRunning()) {
                animationCheck.reset();
                rock = null;
            }
        } else {
            animationCheck.reset();
        }
        SceneObject rockNew = SceneEntities.getNearest(Variables.location.getIds(Variables.ore));
        return !Inventory.isFull() && rockNew != null && rockNew.getLocation().distanceTo() < 9 && (Players.getLocal().getAnimation() == -1 && (rock == null || !rock.validate()));
    }

    @Override
    public void execute() {
        System.out.println("Mine");
        rock = SceneEntities.getNearest(Variables.location.getIds(Variables.ore));
        if (rock != null) {
            if (rock.isOnScreen()) {
                if (rock.interact("Mine")) {
                    Timer t = new Timer(3000);
                    while (t.isRunning() && Players.getLocal().getAnimation() == -1)
                        Task.sleep(50);
                }
            } else {
                Camera.turnTo(rock);
            }
        }
    }
}
