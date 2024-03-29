package org.dracula.collector.resources;

import org.powerbot.core.script.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Tile;

public class Methods {

    public static boolean walkPath(final Tile[] path, final boolean reverse) {
        if (path[0].getPlane() != Players.getLocal().getLocation().getPlane()) return false;
        if (Game.getClientState() != 11) return true;
        if (Walking.getDestination().distanceTo() >= 7
                && !Walking.getDestination().equals(new Tile(-1, -1, -1))) return true;
        Tile tile = null;
        if (reverse) {
            for (final Tile t : path) {
                if (t.isOnMap()) {
                    tile = t.randomize(1, 1);
                    break;
                }
            }
        } else {
            for (int i = path.length - 1; i >= 0; i--) {
                if (path[i].isOnMap()) {
                    tile = path[i].randomize(1, 1);
                    break;
                }
            }
        }
        return tile != null && (Walking.getDestination().distance(tile) < 6 || Walking.walk(tile));
    }
}
