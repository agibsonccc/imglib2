package net.imglib2.ops.features.haralick.features;

import net.imglib2.ops.features.annotations.RequiredInput;
import net.imglib2.ops.features.datastructures.AbstractFeature;
import net.imglib2.ops.features.haralick.helpers.CoocPXMinusY;
import net.imglib2.type.numeric.real.DoubleType;

public class DifferenceVariance extends AbstractFeature
{

	@RequiredInput
	CoocPXMinusY coocPXMinusY;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name()
	{
		return "Difference Variance";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DifferenceVariance copy()
	{
		return new DifferenceVariance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DoubleType recompute()
	{

		final double[] pxminusy = coocPXMinusY.get();

		double sum = 0.0d;
		double res = 0.0d;
		for ( int k = 0; k < pxminusy.length; k++ )
		{
			sum += k * pxminusy[ k ];
		}
		for ( int k = 0; k < pxminusy.length; k++ )
		{
			res += ( k - sum ) * pxminusy[ k ];
		}

		return new DoubleType( res );
	}

}