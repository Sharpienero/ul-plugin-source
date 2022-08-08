package net.sharp.plugins.chopandburn.tasks;

import net.runelite.api.ObjectID;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.sharp.plugins.chopandburn.framework.ChopAndBurnTask;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;

import java.util.*;

public class BurnTask extends ChopAndBurnTask {

    @Override
    public boolean validate() {
        var tinderbox = Inventory.getFirst("Tinderbox");
        var hasLogs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));

        //Check to see if the inventory is full. Set a val in parent task to override bottom
        if (Inventory.isFull() && hasLogs != null)
        {
            setShouldBurnInventory(true);
        }

        if (!Inventory.isFull() && hasLogs != null)
        {
            setShouldBurnInventory(false);
        }

        //if we have a tinderbox, there's a log nearby, and the teak is dead
        if (isShouldBurnInventory() || (tinderbox != null && hasLogs != null && this.isTreeAlive())) {
            return true;
        }

        return false;
    }

    public int execute() {

        if (Players.getLocal().isAnimating() || Players.getLocal().isAnimating()) return 500;

        //Find start of each path
        var points = this.listOfBurnSpots();
        List<WorldPoint> startPoints = Arrays.asList(points.get(4), points.get(5), points.get(6), points.get(7));
        List<WorldPoint> endPoints = Arrays.asList(points.get(0), points.get(1), points.get(2), points.get(3));
        ArrayList<WorldArea> builtAreas = new ArrayList<>(startPoints.size());

        //iterate over both lists simultaneously
        Iterator<WorldPoint> i1 = startPoints.iterator();
        Iterator<WorldPoint> i2 = endPoints.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            var worldArea = new WorldArea(i1.next(), i2.next());
            builtAreas.add(worldArea);
        }

        //use startPoints as the filter for figuring out where we should start firemaking from
        var validBurnStartSpot = TileObjects
                .getSurrounding(Players.getLocal().getWorldLocation(), 15, "Teak")
                .stream()
                .min(Comparator.comparing(x -> x.equals(startPoints.contains(x))))
                .orElse(null);

        List<TileObject> fires = TileObjects.getAll(ObjectID.FIRE_26185);
        var seCornerCounter = 0;
        var nwCornerCounter = 3;
        for (WorldPoint wp : startPoints)
        {
             if (fires.stream().anyMatch(x -> x.getWorldLocation().equals(wp))) {
                 //if there's a fire on a starting point, this is bad.
                 //therefore we cannot make a fire here. Remove it from startPoints
                 startPoints.remove(seCornerCounter);
                 endPoints.remove(nwCornerCounter);
             }
            nwCornerCounter++;
            seCornerCounter++;
        }
        
        if (fires.stream().anyMatch(x -> x.getWorldLocation().equals(startPoints))) {
            // if there's a fire on any of the start tiles, we have to just wait until the fires despawn
            return 1000;
        }

        if (validBurnStartSpot != null)
        {
            if (!Players.getLocal().getWorldLocation().equals(validBurnStartSpot))
            {
                Movement.walkTo(validBurnStartSpot.getWorldLocation());
            }
        }

        var tinderbox = Inventory.getFirst("Tinderbox");
        if (tinderbox != null)
        {
            var logs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));
            if (logs != null)
            {
                tinderbox.useOn(logs);
                return 333;
            }
        }

        return -2;
    }
}
