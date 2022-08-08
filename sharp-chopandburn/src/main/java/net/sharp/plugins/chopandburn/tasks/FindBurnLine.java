package net.sharp.plugins.chopandburn.tasks;

import lombok.Getter;
import lombok.Setter;
import net.unethicalite.api.items.Inventory;

import java.util.Locale;

@Getter
@Setter
public class FindBurnLine extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        var hasLogs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));
        return this.findLine && Inventory.isFull() && hasLogs != null;
    }

    @Override
    public int execute() {
        return -1;
    }
}