package ea.distribution;

import java.util.HashMap;
import java.util.Map;

import testing.Main;

abstract public class Distribution
{
	public static final String[] names =
	{ "ae", "naeu", "naep", "nael", "naek" };

	private static final Class<?>[] classes =
	{ ClassicDistribution.class, UniformDistribution.class, RootDistribution.class, LinearDistribution.class,
			PowerDistribution.class };

	@SuppressWarnings("serial")
	private final static Map<String, Class<?>> map = new HashMap<String, Class<?>>()
	{
		{
			for (int i = 0; i < names.length; i++)
			{
				put(names[i], classes[i]);
			}
		}
	};

	public static Distribution fromName(String string)
	{
		final Class<?> cls = map.get(string);
		if (cls == null)
		{
			throw new IllegalArgumentException("No such algorithm.");
		}
		Distribution distribution = null;
		try
		{
			distribution = (Distribution) cls.newInstance();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return distribution;
	}

	/** Sample a pseudo-random variable from this distribution using Inverse Transformation Method.
	 * @param T End of range.
	 * @return Pseudo-random integer from [1, T] range. */
	public int sample(int T)
	{
		final double unif = Main.rand.nextDouble();
		int result = 1;
		while (unif > cdf(result, T))
		{
			result++;
		}
		if (result > T)
		{
			result = T;
		}
		return result;
	}

	/** Cumulative distribution function on range [1, T].
	 * @param t Point at which we calculate CDF.
	 * @param T Excluded end of range.
	 * @return CDF(t). */
	protected double cdf(int t, int T)
	{
		double cdf = 0.0;
		for (int i = 1; i <= t; i++)
		{
			cdf += pdf(i, T);
		}
		return cdf;
	}

	/** Probability density function on range [1, T].
	 * @param t Point at which we calculate PDF.
	 * @param T Excluded end of range.
	 * @return PDF(t). */
	abstract protected double pdf(int t, int T);
}
