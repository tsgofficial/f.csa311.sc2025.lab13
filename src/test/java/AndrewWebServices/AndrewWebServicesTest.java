package AndrewWebServices;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    InMemoryDatabase fakeDatabase;
    RecSys stubRecommender;
    PromoService mockPromo;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        fakeDatabase = new InMemoryDatabase();
        fakeDatabase.addAccount("Scotty", 17214);

        stubRecommender = new RecSys();

        mockPromo = mock(PromoService.class);

        andrewWebService = new AndrewWebServices(fakeDatabase, stubRecommender, mockPromo);
    }

    @Test
    public void testLogIn() {
        assertTrue(andrewWebService.logIn("Scotty", 17214));
        assertFalse(andrewWebService.logIn("Scotty", 12345));
    }

    @Test
    public void testGetRecommendation() {
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        String email = "scotty@example.com";
        andrewWebService.sendPromoEmail(email);
        verify(mockPromo).mailTo(email);
    }

    @Test
    public void testNoSendEmail() {
        verify(mockPromo, never()).mailTo(anyString());
    }
}
