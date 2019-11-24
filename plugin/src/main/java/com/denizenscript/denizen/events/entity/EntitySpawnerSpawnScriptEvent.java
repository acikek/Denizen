package com.denizenscript.denizen.events.entity;

import com.denizenscript.denizen.objects.EntityTag;
import com.denizenscript.denizen.objects.LocationTag;
import com.denizenscript.denizen.utilities.implementation.BukkitScriptEntryData;
import com.denizenscript.denizen.events.BukkitScriptEvent;
import com.denizenscript.denizencore.objects.ObjectTag;
import com.denizenscript.denizencore.scripts.ScriptEntryData;
import com.denizenscript.denizencore.scripts.containers.ScriptContainer;
import com.denizenscript.denizencore.utilities.CoreUtilities;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class EntitySpawnerSpawnScriptEvent extends BukkitScriptEvent implements Listener {

    // <--[event]
    // @Events
    // spawner spawns entity
    // spawner spawns <entity>
    //
    // @Regex ^on spawner spawns [^\s]+$
    //
    // @Switch in:<area> to only process the event if it occurred within a specified area.
    //
    // @Cancellable true
    //
    // @Triggers when an entity spawns from a monster spawner.
    //
    // @Context
    // <context.entity> returns the EntityTag that spawned.
    // <context.location> returns the LocationTag the entity will spawn at.
    // <context.spawner_location> returns the LocationTag of the monster spawner.
    //
    // -->

    public EntitySpawnerSpawnScriptEvent() {
        instance = this;
    }

    public static EntitySpawnerSpawnScriptEvent instance;
    private EntityTag entity;
    private LocationTag location;
    private LocationTag spawnerLocation;
    public SpawnerSpawnEvent event;

    @Override
    public boolean couldMatch(ScriptContainer scriptContainer, String s) {
        return CoreUtilities.toLowerCase(s).startsWith("spawner spawns");
    }

    @Override
    public boolean matches(ScriptPath path) {

        if (!tryEntity(entity, path.eventArgLowerAt(2))) {
            return false;
        }

        if (!runInCheck(path, location)) {
            return false;
        }
        return super.matches(path);
    }

    @Override
    public String getName() {
        return "SpawnerSpawn";
    }

    @Override
    public ScriptEntryData getScriptEntryData() {
        return new BukkitScriptEntryData(entity);
    }

    @Override
    public ObjectTag getContext(String name) {
        if (name.equals("entity")) {
            return entity;
        }
        else if (name.equals("location")) {
            return location;
        }
        else if (name.equals("spawner_location")) {
            return spawnerLocation;
        }
        return super.getContext(name);
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        Entity entity = event.getEntity();
        this.entity = new EntityTag(entity);
        location = new LocationTag(event.getLocation());
        spawnerLocation = new LocationTag(event.getSpawner().getLocation());
        this.event = event;
        EntityTag.rememberEntity(entity);
        fire(event);
        EntityTag.forgetEntity(entity);
    }
}
