package ea.distribution;

public class LinearDistribution extends Distribution
{
	@Override
	protected double pdf(int t, int T)
	{
		return 2.0 * t / (T * (T + 1));
	}
}
