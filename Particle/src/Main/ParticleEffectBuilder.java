package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;



public class ParticleEffectBuilder {

	public ParticleEffectBuilder() 
	{
		
	}
	
	private Location loc;
	private Particle par;
	private Player player;
	private List<Effect> effects = new ArrayList<Effect>();
	
	
	public ParticleEffectBuilder setObject(String name,Object object)
	{
		effects.add( e-> e.setObject(name, object));
		return this;
	}
	public ParticleEffectBuilder removeObject(String name)
	{
		effects.add( e-> e.removeObject(name));
		return this;
	}
	
	public ParticleEffectBuilder emitParticle(int amount)
	{
		effects.add( e->
		{
				Location loc = e.location;
				if(loc == null) System.out.println("Location in particle is null");
				e.location.getWorld().spawnParticle(e.particle, loc.getX(), loc.getY(), loc.getZ(), amount);
		});
		return this;
	}
	public ParticleEffectBuilder addNormalVector(int amount)
	{
		effects.add( e->
		{
				if(e.player != null)
				{
					e.location.add(e.player.getLocation().toVector().normalize().multiply(amount));
				}
		});
		return this;
	}
	public ParticleEffectBuilder addVector(Vector v)
	{
		effects.add( e->e.location.add(v));
		return this;
	}
	
	public ParticleEffectBuilder forLoop(int amount,Effect... le)
	{
		effects.add( e->
		{
			for(int i = 0; i < amount;i++)
				Stream.of(le).forEach( c -> c.execute(e));
		});
		return this;
	}
	public ParticleEffectBuilder forLoop(int amount,List<Effect>  le)
	{
		effects.add( e->
		{
			for(int i = 0; i < amount;i++)
				le.forEach( c -> c.execute(e));
		});
		return this;
	}
	public ParticleEffectBuilder changeParticle(Particle v)
	{
		effects.add( e->e.particle = v);
		return this;
	}
	public ParticleEffectBuilder wait(int amount)
	{
		effects.add( e-> {
			try {
				Thread.sleep(amount);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		return this;
	}
	
	public ParticleEffectBuilder customEffect(Effect e)
	{
		effects.add(e);
		return this;
	}
	
	
	public ParticleEffect build()
	{
		if(par == null) throw new IllegalStateException("Partycle type cannot be empty");
		
		return new ParticleEffect(effects, loc, par, player);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Effect> getEffects()
	{
		return effects;
	}
	
	public ParticleEffectBuilder setParticle(Particle v)
	{
		par = v;
		return this;
	}
	public ParticleEffectBuilder setLocation(Location loc)
	{
		this.loc = loc;
		return this;
	}
	public ParticleEffectBuilder setPlayer(Player p)
	{
		this.player = p;
		return this;
	}
}
