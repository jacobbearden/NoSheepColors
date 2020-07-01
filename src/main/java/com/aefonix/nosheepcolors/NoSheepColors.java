package com.aefonix.nosheepcolors;

import org.bukkit.DyeColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;

public class NoSheepColors extends JavaPlugin implements Listener {
  private String color;

  @Override
  public void onEnable() {
    saveDefaultConfig();

    FileConfiguration config = this.getConfig();
    color = config.getString("color");

    this.getServer().getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {}

  @EventHandler
  public void onCreatureSpawn(CreatureSpawnEvent event) {
    if (!event.isCancelled()) {
      Entity entity = event.getEntity();

      if (entity instanceof Sheep) {
        Sheep sheep = (Sheep)entity;
        SpawnReason reason = event.getSpawnReason();

        if (reason != SpawnReason.SPAWNER) {
          if (color.equals("rainbow")) {
            int random = (int)Math.floor(Math.random() * DyeColor.values().length);
            sheep.setColor(DyeColor.values()[random]);
            return;
          }

          if (!color.equals("natural")) {
            sheep.setColor(DyeColor.valueOf(color.toUpperCase()));
          }
        }
      }
    }
  }
}
