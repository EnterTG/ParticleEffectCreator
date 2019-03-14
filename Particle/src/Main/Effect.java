package Main;

@FunctionalInterface
public interface Effect {
	void execute(ParticleEffect pe);
}
