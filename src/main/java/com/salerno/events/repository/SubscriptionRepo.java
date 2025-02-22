package com.salerno.events.repository;
import com.salerno.events.model.Event;
import com.salerno.events.model.Subscription;
import com.salerno.events.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
    public Subscription findByEventAndSubscriber(Event evt, User user);
}
