package ea.distribution;

public class PowerDistribution extends Distribution
{
	@Override
	protected double pdf(int t, int T)
	{
		return 6.0 * t * t / (T * (T + 1) * (2 * T + 1));
	}
}
