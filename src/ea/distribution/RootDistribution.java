package ea.distribution;

public class RootDistribution extends Distribution
{
	@Override
	protected double pdf(int t, int T)
	{
		return 6 * Math.sqrt(t) / ((4 * T + 1) * Math.sqrt(T + 1));
	}
}
