package ea;

import java.util.Arrays;

import testing.Main;

/** Solutions are in some sense immutable - you can't change Solution object using its public methods. */
public class Solution
{
	public final double feat[];

	/** Create random solution. */
	public Solution(int dim)
	{
		feat = new double[dim];
		for (int i = 0; i < dim; i++)
		{
			feat[i] = (Main.DOMAIN_MAX - Main.DOMAIN_MIN) * Main.rand.nextDouble() + Main.DOMAIN_MIN;
		}
	}

	/** Deep copy. */
	public Solution(Solution other)
	{
		feat = new double[other.feat.length];
		System.arraycopy(other.feat, 0, feat, 0, feat.length);
	}

	@Override
	public String toString()
	{
		return Arrays.toString(feat);
	}

	/** @param other Second parent. This object is the first parent.
	 * @return New child solution. Doesn't modify this object. */
	public Solution crossover(Solution other)
	{
		final Solution child = new Solution(this);
		for (int i = 0; i < feat.length; i++)
		{
			child.feat[i] = (feat[i] + other.feat[i]) / 2;
		}
		return child;
	}

	/** @return New mutant solution. Doesn't modify this object. */
	public Solution mutate()
	{
		final Solution mutant = new Solution(this);
		for (int i = 0; i < feat.length; i++)
		{
			mutant.feat[i] += Main.rand.nextGaussian();
		}
		return mutant;
	}
}