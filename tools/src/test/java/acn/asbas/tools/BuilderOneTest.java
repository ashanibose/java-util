package acn.asbas.tools;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import acn.asbas.tools.dp.builder.IBuilder;
import acn.asbas.tools.dp.builder.IDirector;
import acn.asbas.tools.dp.builder.impl.BuilderOne;
import acn.asbas.tools.dp.builder.impl.DirectorOne;
import acn.asbas.tools.dp.shared.IProduct;
import acn.asbas.tools.dp.shared.impl.ProductOne;
import static junit.framework.Assert.*;

public class BuilderOneTest {
	private IProduct mockedProduct;
	private IBuilder mockedBuilder;

	@Before
	public void setup() {
		mockedProduct = mock(ProductOne.class);
		mockedBuilder = mock(BuilderOne.class);

		when(mockedProduct.toString()).thenReturn("Success[mocked]");
	}

	@Test
	public void with_out_mock() {
		BuilderOne builder = new BuilderOne();
		IDirector<BuilderOne, ProductOne> director = new DirectorOne(builder);

		director.construct();

		System.out.println(builder.getResult());
		
		assertNotNull(builder.getResult());
	}

	@Test
	public void with_mock() {
	}

}
