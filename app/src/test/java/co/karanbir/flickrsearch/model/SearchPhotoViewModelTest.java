package co.karanbir.flickrsearch.model;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.karanbir.flickrsearch.network.Photo;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SearchPhotoViewModelTest {
    private SearchPhotoViewModel viewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    Observer<PagedList<Photo>> observer;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new SearchPhotoViewModel();
        viewModel.getSearchResult().observeForever(observer);

    }

    @Test
    public void testSearchPhoto() {
        viewModel.searchPhoto("Car");
        assertEquals("Car", viewModel.getQueryLiveData().getValue());

    }

    @After
    public void tearDown() {

    }
}
