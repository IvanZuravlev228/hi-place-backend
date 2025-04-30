package hi.place.service.impl;

import hi.place.model.Visit;
import hi.place.repository.VisitRepository;
import hi.place.service.VisitService;
import hi.place.util.DateCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final static int HOUR_AS_SECOND = 3600;
    private final VisitRepository visitRepository;

    public void logVisit(String ipAddress, Long userId) {
        Optional<Visit> lastVisit = visitRepository.findTopByIpAddressAndUserIdOrderByVisitTimeDesc(ipAddress, userId);
        Long now = DateCreater.getCurrentTimeAsSeconds();

        if (lastVisit.isPresent() && isRecentVisit(lastVisit.get().getVisitTime(), now)) {
            return;
        }

        Visit visit = new Visit();
        visit.setIpAddress(ipAddress);
        visit.setUserId(userId);
        visit.setVisitTime(now);

        visitRepository.save(visit);
    }

    @Override
    public Integer getVisitCount(Long userId, Long startTime, Long endTime) {
        return visitRepository.countByUserIdAndVisitTimeBetween(userId, startTime, endTime);
    }

    private boolean isRecentVisit(Long lastVisitTime, Long now) {
        return (now - lastVisitTime) < HOUR_AS_SECOND;
    }
}
