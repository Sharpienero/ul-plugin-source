package net.sharp.plugins.chopandburn.tasks;

import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Inventory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BurnTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        var tinderbox = Inventory.getFirst("Tinderbox");
        var hasLogs = Inventory.getFirst(ItemID.TEAK_LOGS);

        //Check to see if the inventory is full. Set a val in parent task to override bottom
        if (Inventory.isFull() && Inventory.contains(ItemID.TEAK_LOGS)) {
            this.burnInventory = true;
        } else {
            this.burnInventory = false;
        }

        //if we have a tinderbox, there's a log nearby, and the teak is dead
        if (this.burnInventory || (tinderbox != null && hasLogs != null && !this.teakAlive())) {
            return true;
        }

        return false;
    }

    public int execute() {
        //Find start of each path
        var points = this.listOfBurnSpots();
        List<WorldPoint> startPoints = Arrays.asList(points.get(4), points.get(5), points.get(6), points.get(7));
        List<WorldPoint> endPoints = Arrays.asList(points.get(0), points.get(1), points.get(2), points.get(3));
        List<WorldArea> builtAreas = Arrays.asList();

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

        

        //get valid burn spots
        return -2;
    }
}
