package PaticleEffectGenerator.Main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParticleEffectManager {

	private static final ParticleEffectManager PARTICLE_EFFECT_MANAGER = new ParticleEffectManager();
	public static ParticleEffectManager getParticleEffectManager()
	{
		return PARTICLE_EFFECT_MANAGER;
	}
	
	private HashMap<String,ParticleEffect> effectMap = new HashMap<>();
	private ParticleEffectManager() {}	
	
	
	public final void addEffect(String name,ParticleEffect pe)
	{
		effectMap.putIfAbsent(name, pe);
	}
	
	public ParticleEffect getParticleEffect(String name)
	{
		return effectMap.get(name);
	}
	
	public void playEffect(String name)
	{
		ParticleEffect pe = getParticleEffect(name);
		if(checkEffect(name, pe)) Bukkit.getScheduler().runTaskAsynchronously(Core.getCore(), pe.copy());
	}
	public void playEffect(String name,Location loc)
	{
		ParticleEffect pe = getParticleEffect(name);
		if(checkEffect(name, pe)) Bukkit.getScheduler().runTaskAsynchronously(Core.getCore(), pe.copy(loc));
	}
	public void playEffect(String name,Player p)
	{
		ParticleEffect pe = getParticleEffect(name);
		if(checkEffect(name, pe)) Bukkit.getScheduler().runTaskAsynchronously(Core.getCore(), pe.copy(p));
	}
	public void playEffect(String name,Location loc,Player p)
	{
		ParticleEffect pe = getParticleEffect(name);
		if(checkEffect(name, pe)) Bukkit.getScheduler().runTaskAsynchronously(Core.getCore(), pe.copy(loc, p));
	}
	
	private boolean checkEffect(String name,ParticleEffect pe)
	{
		if(pe == null) {System.out.println(String.format("Cant play effect %s",name)); return false;}
		return true;
	}
}
