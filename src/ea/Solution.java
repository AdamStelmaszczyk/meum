package ea;

import java.util.Arrays;

import testing.Main;

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

	public Solution mul(double factor)
	{
		final Solution result = new Solution(this);
		for (int i = 0; i < feat.length; i++)
		{
			result.feat[i] *= factor;
		}
		return result;
	}

	public Solution plus(Solution other)
	{
		final Solution result = new Solution(this);
		for (int i = 0; i < feat.length; i++)
		{
			result.feat[i] += other.feat[i];
		}
		return result;
	}

	@Override
	public String toString()
	{
		return Arrays.toString(feat);
	}
}