package yaboichips.minersmission.events;

import net.minecraft.util.StringRepresentable;

public enum MissionEvents implements StringRepresentable {
    MOB_EVENT("mob_event"),
    MODDED_MOB_EVENT("modded_mob_event"),
    COLLAPSE_EVENT("collapse_event");

    private final String name;

    MissionEvents(String name){
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
