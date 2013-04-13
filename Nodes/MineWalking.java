package Collector.Nodes;

import Collector.Resources.Methods;
import Collector.Resources.MineLocation;
import Collector.Resources.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class MineWalking extends Node {

    public boolean activate() {
        return !Inventory.isFull() && !MineLocation.VARROCKWEST_IMINE.contains(Players.getLocal().getLocation());
    }

    @Override
    public void execute() {
        System.out.println("Mine Walking");
        Methods.walkPath(Variables.location.getPath(), true);
    }
}
