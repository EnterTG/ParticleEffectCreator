package PaticleEffectGenerator.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleEffect implements Runnable{

	public ParticleEffect(List<Effect> effects, Location location, Particle particle,Player player) {
		super();
		this.effects = effects;
		this.location = location;
		this.particle = particle;
		if(location != null)this.sourceLoaction = location.clone();
		if(player != null) this.sourceLoaction = player.getLocation().clone();
		if(location == null && player != null) location = player.getLocation().clone();
		this.player = player;
	}
	private final List<Effect> effects;
	
	public Player player;
	public Location location;
	public Location sourceLoaction;
	public Particle particle;
	private Map<String,Object> objectMap = new HashMap<>();
	public Object getObject(String name)
	{
		return objectMap.get(name);
	}
	public void setObject(String name,Object object)
	{
		objectMap.put(name,object);
	}
	public void removeObject(String name)
	{
		objectMap.remove(name);
	}
	@Override
	public void run()
	{
		System.out.println(toString());
		effects.forEach(e -> e.execute(this));
	}
	
	public ParticleEffect copy()
	{
		return new ParticleEffect(effects,location,particle,player);
	}
	public ParticleEffect copy(Location loc)
	{
		return new ParticleEffect(effects,loc,particle,player);
	}
	public ParticleEffect copy(Player p)
	{
		return new ParticleEffect(effects,p.getLocation(),particle,p);
	}
	public ParticleEffect copy(Location loc,Player p)
	{
		return new ParticleEffect(effects,loc,particle,p);
	}
	
	@Override
	public String toString()
	{
		return String.format("Effects: %s Location: %s Player %s Particle: %s"
			, Arrays.toString(effects.toArray()),location != null ? location.toString() : "null",player != null ? player.getName() : "null",particle.toString());
	}
	
	
}
