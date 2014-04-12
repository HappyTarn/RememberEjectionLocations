package com.happytarn.RememberEjectionLocations;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {

	public static Logger log;
	public static Main plugin;

	/**
	 * プラグインが有効になったとき
	 */
	public void onEnable() {
		this.log = getLogger();
		this.plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
	}

	/**
	 * プラグインが無効になったとき
	 */
	public void onDisable() {

	}

	@EventHandler
	public void onVehicleExitEvent(VehicleExitEvent event) {
		LivingEntity entity = event.getExited();
		Vehicle vehicle = event.getVehicle();
		if (entity instanceof Player) {
			Player player = (Player) entity;
			Location vehicleLocation = vehicle.getLocation();
			new TestRunnable(player,vehicleLocation).runTaskTimer(this, 2, 20);
		}
	}

	class TestRunnable extends BukkitRunnable {
		Player player;
		Location vehicleLocation;
		public TestRunnable(Player player,Location location) {
			this.player = player;
			this.vehicleLocation = location;
		}

		/**
		 * run()が実行された回数
		 */
		private int repeat = 0;

		/**
		 * タスク処理内容
		 */
		public void run() {
			player.teleport(vehicleLocation);
			cancel();
		}
	}
}
