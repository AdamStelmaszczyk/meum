package ea.distribution;

public class ClassicDistribution extends Distribution
{
	@Override
	protected double pdf(int t, int T)
	{
		return t == T ? 1.0 : 0.0;
	}

	@Override
	public int sample(int T)
	{
		return T;
	}
}
