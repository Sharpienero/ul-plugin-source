package net.sharp.plugins.castleburner.tasks;

import net.runelite.api.ObjectID;
import net.runelite.api.TileItem;
import net.runelite.api.TileObject;
import net.unethicalite.api.entities.TileItems;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.game.Worlds;
import net.unethicalite.api.plugins.Task;

import java.util.List;


public class HopTask implements Task {

    public boolean validate() {
        TileItem log = TileItems.getNearest("Logs");

        if (log == null) return true;

        List<TileObject> fires = TileObjects.getAll(ObjectID.FIRE_26185);
        if (fires.stream().anyMatch(x -> x.getWorldLocation().equals(log.getWorldLocation())))
        {
            return true;
        }

        return false;
    }

    public int execute() {
        Worlds.hopTo(Worlds.getRandom(x -> x.isNormal()));
        return -4;
    }
}
