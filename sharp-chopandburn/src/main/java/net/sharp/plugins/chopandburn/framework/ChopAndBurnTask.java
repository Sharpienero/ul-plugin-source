package net.sharp.plugins.chopandburn.framework;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.GameObject;
import net.runelite.api.Tile;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.plugins.Task;
import net.unethicalite.api.scene.Tiles;
import net.unethicalite.client.Static;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class ChopAndBurnTask implements Task {
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }

    public boolean isTreeAlive() {
        var tree = TileObjects
                .getSurrounding(Players.getLocal().getWorldLocation(), 15, "Teak")
                .stream()
                .min(Comparator.comparing(x -> x.distanceTo(Players.getLocal().getWorldLocation())))
                .orElse(null);

        return tree != null;
    }

    public List<WorldArea> getLongestWorldArea() {
        var currentLocation = Players.getLocal().getWorldLocation();

        //Get the tiles in a radius of 25
        var surroundingArea = Tiles.getSurrounding(currentLocation, 25);
        var teakWorldArea = new WorldArea(new WorldPoint(2329, 3044,0), new WorldPoint(2335, 3054, 0));

        var collisionMap = Static.getGlobalCollisionMap();
        var startingBurnPathCandidates = Tiles.getSurrounding(Players.getLocal().getWorldLocation(), 15)
                .stream()
                .filter(tile ->
                        new WorldArea(tile.getWorldLocation(), 5, 1)
                                .toWorldPointList()
                                .stream()
                                .allMatch(
                                        x -> TileObjects.getFirstAt(x, a -> a instanceof GameObject) == null
                                        && x.isInArea(teakWorldArea)
                                        && !collisionMap.fullBlock(x)))
                .toArray(Tile[]::new);

        // Create a new WorldPoint
        var startingTileCandidate = Tiles.getSurrounding(Players.getLocal().getWorldLocation(), 15)
                .stream()
                .filter(tile ->
                        new WorldArea(tile.getWorldLocation(), 5, 1)
                                .toWorldPointList()
                                .stream()
                                .allMatch(
                                        x -> TileObjects.getFirstAt(x, a -> a instanceof GameObject) == null
                                                && x.isInArea(teakWorldArea)
                                                && !collisionMap.fullBlock(x)))
                .toArray(Tile[]::new);

        WorldPoint mostViableWorldPointStart = Players.getLocal().getWorldLocation();
        for (Tile wp : startingBurnPathCandidates)
        {

            if (wp.getWorldLocation().getWorldX() > mostViableWorldPointStart.getWorldX() || wp.getWorldLocation().getWorldY() > mostViableWorldPointStart.getWorldY()) {
                mostViableWorldPointStart = new WorldPoint(wp.getWorldX(), wp.getWorldY(), 0);
            }
        }

        var bestPath = TileObjects
                .getSurrounding(currentLocation, 15, "Teak")
                .stream()
                .min(Comparator.comparing(x -> x.distanceTo(Players.getLocal().getWorldLocation())))
                .orElse(null);

        List<WorldArea> list = Arrays.asList();

        return list;
    }

    @Getter
    @Setter
    boolean shouldBurnInventory = false;

    @Getter
    @Setter
    boolean shouldFindLine = false;

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
