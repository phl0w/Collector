package Collector;

import Collector.Nodes.BankWalking;
import Collector.Nodes.Banking;
import Collector.Nodes.MineWalking;
import Collector.Nodes.Mining;
import Collector.Resources.Paint;
import Collector.Resources.SkillCalculations;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;

import java.awt.*;

import static Collector.Resources.Variables.oresMined;

@Manifest(authors = "Dracula", name = "AIO Miner", description = "Mine hard or go home.", version = 1.1)
public class Collector extends ActiveScript implements PaintListener, MessageListener {

    Paint paint = null;

    public void onStart() {
    }

    private Tree container = new Tree(new Node[]{new Banking(), new BankWalking(), new MineWalking(), new Mining()});

    @Override
    public int loop() {
        if (container != null) {
            Node node = container.state();
            if (node != null) {
                container.set(node);
                getContainer().submit(node);
                node.join();
            }
        }
        return 100;
    }

    @Override
    public void messageReceived(MessageEvent m) {
        String s = m.getMessage();
        if (s.contains("You manage")) {
            oresMined++;
        }
    }


    @Override
    public void onRepaint(Graphics g) {
        paint = new Paint(g, new SkillCalculations(Skills.MINING));
        paint.onRepaint(g);
    }
}