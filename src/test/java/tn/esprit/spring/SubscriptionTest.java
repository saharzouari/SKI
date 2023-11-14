package tn.esprit.spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.controllers.SubscriptionRestController;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionTest {

    @InjectMocks
    private SubscriptionRestController subscriptionController;

    @Mock
    private SubscriptionServicesImpl subscriptionServices;

    @Test
    void testAddSubscription() {
        Subscription subscription = new Subscription();
        when(subscriptionServices.addSubscription(any(Subscription.class))).thenReturn(subscription);

        Subscription result = subscriptionController.addSubscription(subscription);

        assertNotNull(result);
        assertEquals(subscription, result);
        verify(subscriptionServices).addSubscription(any(Subscription.class));
    }

    @Test
    void testGetById() {
        Long subscriptionId = 1L;
        Subscription subscription = new Subscription();
        when(subscriptionServices.retrieveSubscriptionById(subscriptionId)).thenReturn(subscription);

        Subscription result = subscriptionController.getById(subscriptionId);

        assertNotNull(result);
        assertEquals(subscription, result);
        verify(subscriptionServices).retrieveSubscriptionById(subscriptionId);
    }

    @Test
    void testGetSubscriptionsByType() {
        TypeSubscription typeSubscription = TypeSubscription.MONTHLY;
        Set<Subscription> subscriptions = new HashSet<>();
        when(subscriptionServices.getSubscriptionByType(typeSubscription)).thenReturn(subscriptions);

        Set<Subscription> result = subscriptionController.getSubscriptionsByType(typeSubscription);

        assertNotNull(result);
        assertEquals(subscriptions, result);
        verify(subscriptionServices).getSubscriptionByType(typeSubscription);
    }

    @Test
    void testUpdateSubscription() {
        Subscription subscription = new Subscription();
        when(subscriptionServices.updateSubscription(any(Subscription.class))).thenReturn(subscription);

        Subscription result = subscriptionController.updateSubscription(subscription);

        assertNotNull(result);
        assertEquals(subscription, result);
        verify(subscriptionServices).updateSubscription(any(Subscription.class));
    }

    @Test
    void testGetSubscriptionsByDates() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        List<Subscription> subscriptions = Arrays.asList(new Subscription(), new Subscription());
        when(subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate)).thenReturn(subscriptions);

        List<Subscription> result = subscriptionController.getSubscriptionsByDates(startDate, endDate);

        assertNotNull(result);
        assertEquals(subscriptions, result);
        verify(subscriptionServices).retrieveSubscriptionsByDates(startDate, endDate);
    }
}
