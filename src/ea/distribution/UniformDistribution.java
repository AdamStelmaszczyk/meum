package ea.distribution;

import testing.Main;

public class UniformDistribution extends Distribution
{
	@Override
	protected double pdf(int t, int T)
	{
		return 1.0 / T;
	}

	@Override
	public int sample(int T)
	{
		return Main.rand.nextInt(T) + 1;
	}
}
