package net.sharp.plugins.chopandburn.tasks;

import net.runelite.api.ObjectID;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;
import net.sharp.plugins.chopandburn.framework.ChopAndBurnTask;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;

import java.util.List;
import java.util.Locale;

public class BurnTask extends ChopAndBurnTask {

    @Override
    public boolean validate() {
        var tinderbox = Inventory.getFirst("Tinderbox");
        var hasLogs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));

        if (Inventory.isFull())
        {
            setShouldBurnInventory(true);
        }

        //if we have a tinderbox, there's a log nearby, and the inventory is full OR we should burn inventory
        if (((tinderbox != null && hasLogs != null) && Inventory.isFull()) || isShouldBurnInventory()) {
            return true;
        }

        return false;
    }

    public int execute() {
        //Move to first burn spot
        var startPoint = new WorldPoint(2644, 3251, 0);
        var startPointTwo = new WorldPoint(2641, 3252, 0);

        var endPoint = new WorldPoint(2630, 3251, 0);
        var endPointTwo = new WorldPoint(2636, 3252, 0);

        WorldPoint usePoint = startPoint;
        List<TileObject> fires = TileObjects.getAll(ObjectID.FIRE_26185);
        if (!fires.stream().anyMatch(x -> !x.getWorldLocation().equals(startPoint) && !x.getWorldLocation().equals(endPoint)))
        {
            usePoint = startPoint;
            if (!fires.stream().anyMatch(x -> x.getWorldLocation().equals(startPointTwo) && !x.getWorldLocation().equals(endPointTwo)))
            {
                usePoint = startPointTwo;
            }
        }

        //if we're not on the starting point, we should be
        if (!Players.getLocal().getWorldLocation().equals(usePoint) && Inventory.getCount(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs")) == 20 || Inventory.getCount(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs")) == 5)
        {
            // walk to it
            Movement.walkTo(usePoint);
        }

        // if we're on the world point, we should
        if (Players.getLocal().getWorldLocation().equals(usePoint))
        {
            //burn the inventory
            setShouldBurnInventory(true);
        }

        if (isShouldBurnInventory() && !Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            var tinderbox = Inventory.getFirst("Tinderbox");
            if (tinderbox != null)
            {
                var logs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));
                if (logs != null)
                {
                    tinderbox.useOn(logs);
                    return 333;
                }
                else
                {
                    // go back to chopping
                    setShouldBurnInventory(false);
                }
            }
        }

        return -2;
    }
}
