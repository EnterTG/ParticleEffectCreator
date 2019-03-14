package Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Core extends JavaPlugin
{
	private static Core CORE;
	public static Core getCore()
	{
		return CORE;
	}
	
	@Override
	public void onEnable()
	{
		CORE = this;
		addTestEffects();
	}
	
	final double slice = 2 * Math.PI / 36;
	public void addTestEffects()
	{
		ParticleEffectManager pem = ParticleEffectManager.getParticleEffectManager();
		
		ParticleEffect firewalk = new ParticleEffectBuilder().setParticle(Particle.FLAME).emitParticle(5).build();
		pem.addEffect("firewalk", firewalk);
		
		Effect docircle = e -> 
		{
			int x = (int)e.getObject("iteration");
			double alfa = slice * x;
			Location loc  = e.sourceLoaction;
			double px = loc.getX() + 0.25 * Math.cos(alfa);
			double pz = loc.getZ() + 0.25 * Math.sin(alfa);
			e.location.setX(px);
			e.location.setZ(pz);
			e.setObject("iteration", x++);
		};
		List<Effect> effects = new ParticleEffectBuilder().customEffect(docircle).emitParticle(1).getEffects();
		ParticleEffect angel = new ParticleEffectBuilder().setParticle(Particle.CRIT).addVector(new Vector(0, 1.5, 0)).setObject("iteration", 0).forLoop(36, effects).build();
		
	}
}
