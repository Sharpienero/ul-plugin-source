package net.sharp.plugins.chopandburn.tasks;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.coords.WorldPoint;
import net.sharp.plugins.chopandburn.ChopAndBurnConfig;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.plugins.Task;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class ChopAndBurnTask implements Task {

    @Inject
    private ChopAndBurnConfig config;
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }

    public boolean treeAlive() {
        var tree = TileObjects
                .getSurrounding(Players.getLocal().getWorldLocation(), 15, config.tree().getName())
                .stream()
                .min(Comparator.comparing(x -> x.distanceTo(Players.getLocal().getWorldLocation())))
                .orElse(null);

        if (tree != null) return true;

        return false;
    }

    boolean burnInventory = false;
    boolean findLine = false;

    public List<WorldPoint> listOfBurnSpots() {
        List<WorldPoint> list = Arrays.asList(
        new WorldPoint(2330, 3049, 0), //nw 1 2 3 4
        new WorldPoint(2330, 3048, 0),
        new WorldPoint(2330, 3047, 0),
        new WorldPoint(2331, 3046, 0),

        new WorldPoint(2336, 3049, 0), //ne 1 2 3 4
        new WorldPoint(2334, 3048, 0),
        new WorldPoint(2335, 3047, 0),
        new WorldPoint(2335, 3046, 0)
        );

        return list;
    }
}
