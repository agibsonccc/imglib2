/**
 * Copyright (c) 2009--2010, Stephan Preibisch & Stephan Saalfeld
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the Fiji project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Stephan Preibisch & Stephan Saalfeld
 */
package mpicbg.imglib.location;

import mpicbg.imglib.sampler.PositionableRasterSampler;
import mpicbg.imglib.sampler.RasterIterator;

/**
 * The {@link RasterLocalizable} interface can localize itself in an n-dimensional
 * discrete space.  Not only {@link RasterIterator}s can use this 
 * interface, it might be used by much more classes as {@link PositionableRasterSampler}s
 * can take any {@link RasterLocalizable} as input for where they should move to.
 *  
 * @author Stephan Preibisch & Stephan Saalfeld
 *
 */
public interface RasterLocalizable extends Localizable
{
	/**
	 * Write the current position into the passed array.
	 * 
	 * @param location
	 */
	public void localize( int[] position );
	
	/**
	 * Write the current position into the passed array.
	 * 
	 * @param location
	 */
	public void localize( long[] position );
	
	/**
	 * Return the current position in a given dimension.
	 * 
	 * @param dim
	 * @return
	 */
	public int getIntPosition( int dim );
	
	/**
	 * Return the current position in a given dimension.
	 * 
	 * @param dim
	 * @return
	 */
	public long getLongPosition( int dim );
}
