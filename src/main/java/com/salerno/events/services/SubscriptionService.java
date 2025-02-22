package com.salerno.events.services;
import com.salerno.events.dto.SubscriptionResponse;
import com.salerno.events.exception.EventNotFoundException;
import com.salerno.events.exception.SubscriptionConflictException;
import com.salerno.events.exception.UserIndicNotFoundException;
import com.salerno.events.model.Event;
import com.salerno.events.model.Subscription;
import com.salerno.events.model.User;
import com.salerno.events.repository.EventRepo;
import com.salerno.events.repository.SubscriptionRepo;
import com.salerno.events.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepo evtRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubscriptionRepo subRepo;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {

        if (eventName == null || eventName.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty.");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        Event evt = evtRepo.findByPrettyName(eventName);
        if (evt == null) {
            throw new EventNotFoundException("Event '" + eventName + "' does not exist.");
        }

        User userRec = userRepo.findByEmail(user.getEmail());
        if (userRec == null) {
            userRec = userRepo.save(user);
        }

        User indic = null;
        if (userId != null) {
            indic = userRepo.findById(userId)
                    .orElseThrow(() -> new UserIndicNotFoundException("Referrer user with ID " + userId + " does not exist."));
        }

        Optional<Subscription> tmpSub = Optional.ofNullable(subRepo.findByEventAndSubscriber(evt, userRec));
        if (tmpSub.isPresent()) {
            throw new SubscriptionConflictException(
                    "User " + userRec.getName() + " is already registered for the event " + evt.getTitle()
            );
        }

        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);
        subs.setIndication(indic); // Pode ser null

        Subscription res = subRepo.save(subs);

        String confirmationUrl = "http://codecraft.com/subscription/"
                + res.getEvent().getPrettyName() + "/"
                + res.getSubscriber().getId();

        return new SubscriptionResponse(res.getSubscriptionNumber(), confirmationUrl);
    }
}